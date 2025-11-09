package com.jerry.generator_extras.common.tile.plasma;

import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;

import mekanism.api.IContentsListener;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.lib.multiblock.IMultiblockEjector;
import mekanism.common.tile.base.SubstanceType;
import mekanism.common.util.ChemicalUtil;
import mekanism.common.util.MekanismUtils;
import mekanism.generators.common.content.fusion.FusionReactorMultiblockData;
import mekanism.generators.common.tile.fusion.TileEntityFusionReactorBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;

public class TileEntityFusionReactorPlasmaExtractingPort
                                                         extends TileEntityFusionReactorBlock
                                                         implements IMultiblockEjector {

    private Set<Direction> outputDirections = Collections.emptySet();

    public TileEntityFusionReactorPlasmaExtractingPort(BlockPos pos, BlockState state) {
        super(ExtraGenBlocks.FUSION_REACTOR_PLASMA_EXTRACTING_PORT, pos, state);
        delaySupplier = NO_DELAY;
    }

    @Override
    public void setEjectSides(Set<Direction> sides) {
        outputDirections = sides;
    }

    @Override
    public @Nullable IChemicalTankHolder<Gas, GasStack, IGasTank> getInitialGasTanks(IContentsListener listener) {
        return side -> Collections.singletonList(((IFusionPlasmaHolder) getMultiblock()).getPlasmaTank());
    }

    @Override
    public boolean persists(SubstanceType type) {
        if (type == SubstanceType.GAS || type == SubstanceType.FLUID || type == SubstanceType.ENERGY || type == SubstanceType.HEAT) {
            return false;
        }
        return super.persists(type);
    }

    @Override
    public int getCurrentRedstoneLevel() {
        IGasTank plasma = ((IFusionPlasmaHolder) getMultiblock()).getPlasmaTank();
        return MekanismUtils.redstoneLevelFromContents(plasma.getStored(), plasma.getCapacity());
    }

    @Override
    protected boolean onUpdateServer(FusionReactorMultiblockData multiblock) {
        boolean needsPacket = super.onUpdateServer(multiblock);
        if (getActive() && multiblock.isFormed()) {
            ChemicalUtil.emit(outputDirections, ((IFusionPlasmaHolder) multiblock).getPlasmaTank(), this);
        }
        return needsPacket;
    }

    // Methods relating to IComputerTile
    @Override
    public boolean exposesMultiblockToComputer() {
        return false;
    }

    @ComputerMethod(methodDescription = "true -> output, false -> input")
    boolean getMode() {
        return getActive();
    }

    @ComputerMethod(methodDescription = "true -> output, false -> input")
    void setMode(boolean output) {
        setActive(output);
    }
    // End methods IComputerTile
}
