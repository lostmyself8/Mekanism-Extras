package com.jerry.generator_extras.common.tile.plasma;

import com.jerry.generator_extras.common.content.plasma.PlasmaEvaporationMultiblockData;
import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.lib.multiblock.IMultiblockEjector;
import mekanism.common.tile.base.SubstanceType;
import mekanism.common.util.ChemicalUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public class TileEntityPlasmaEvaporationVent
        extends TileEntityPlasmaEvaporationBlock
        implements IMultiblockEjector {

    private Set<Direction> outputDirections = Collections.emptySet();

    public TileEntityPlasmaEvaporationVent(BlockPos pos, BlockState state) {
        super(ExtraGenBlocks.PLASMA_EVAPORATION_VENT, pos, state);
    }

    @NotNull
    @Override
    public IChemicalTankHolder<Gas, GasStack, IGasTank> getInitialGasTanks(IContentsListener listener) {
        return side -> {
            PlasmaEvaporationMultiblockData multiblock = getMultiblock();
            return multiblock.isFormed() ? Collections.singletonList(multiblock.plasmaOutputTank) : Collections.emptyList();
        };
    }

    @Override
    protected boolean onUpdateServer(PlasmaEvaporationMultiblockData multiblock) {
        boolean needsPacket = super.onUpdateServer(multiblock);
        if (multiblock.isFormed()) {
            ChemicalUtil.emit(outputDirections, multiblock.plasmaOutputTank, this);
        }
        return needsPacket;
    }

    @Override
    public boolean persists(SubstanceType type) {
        //Do not handle fluid when it comes to syncing it/saving this tile to disk
        if (type == SubstanceType.GAS) {
            return false;
        }
        return super.persists(type);
    }

    @Override
    public void setEjectSides(Set<Direction> sides) {
        outputDirections = sides;
    }

}
