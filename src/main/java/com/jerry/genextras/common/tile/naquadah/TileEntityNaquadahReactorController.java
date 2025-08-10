package com.jerry.genextras.common.tile.naquadah;

import com.jerry.genextras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.genextras.common.registries.GenExtraBlocks;
import mekanism.common.attachments.containers.ContainerType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityNaquadahReactorController extends TileEntityNaquadahReactorCasing {

    public TileEntityNaquadahReactorController(BlockPos pos, BlockState state) {
        super(GenExtraBlocks.NAQUADAH_REACTOR_CONTROLLER, pos, state);
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
    public boolean persists(ContainerType<?, ?, ?> type) {
        if (type == ContainerType.CHEMICAL || type == ContainerType.FLUID || type == ContainerType.HEAT) {
            return false;
        }
        return super.persists(type);
    }
}
