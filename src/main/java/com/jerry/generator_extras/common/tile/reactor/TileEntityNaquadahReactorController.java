package com.jerry.generator_extras.common.tile.reactor;

import com.jerry.generator_extras.common.content.reactor.NaquadahReactorMultiblockData;
import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.integration.energy.EnergyCompatUtils;
import mekanism.common.tile.base.SubstanceType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class TileEntityNaquadahReactorController extends TileEntityNaquadahReactorCasing {
    public TileEntityNaquadahReactorController(BlockPos pos, BlockState state) {
        super(ExtraGenBlocks.NAQUADAH_REACTOR_CONTROLLER, pos, state);
        //Never allow the gas handler, fluid handler, or energy cap to be enabled here even though internally we can handle both of them
        addDisabledCapabilities(Capabilities.GAS_HANDLER, ForgeCapabilities.FLUID_HANDLER, Capabilities.HEAT_HANDLER);
        addDisabledCapabilities(EnergyCompatUtils.getEnabledEnergyCapabilities());
        addSemiDisabledCapability(ForgeCapabilities.ITEM_HANDLER, () -> !getMultiblock().isFormed());
        delaySupplier = NO_DELAY;
    }

    @Override
    protected boolean onUpdateServer(NaquadahReactorMultiblockData multiblock) {
        boolean needsPacket = super.onUpdateServer(multiblock);
        setActive(multiblock.isFormed());
        return needsPacket;
    }

    @Override
    protected boolean canPlaySound() {
        NaquadahReactorMultiblockData multiblock = getMultiblock();
        return multiblock.isFormed() && multiblock.isBurning();
    }

    @Override
    public boolean canBeMaster() {
        return true;
    }

    @Override
    public boolean handles(SubstanceType type) {
        if (type == SubstanceType.GAS || type == SubstanceType.FLUID || type == SubstanceType.HEAT) {
            return false;
        }
        return super.handles(type);
    }
}
