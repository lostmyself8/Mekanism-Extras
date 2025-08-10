package com.jerry.genextras.common.tile.naquadah;

import com.jerry.genextras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.genextras.common.registries.GenExtraBlocks;
import com.jerry.mekextras.MekanismExtras;
import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.common.tile.prefab.TileEntityMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityNaquadahReactorCasing extends TileEntityMultiblock<NaquadahReactorMultiblockData> {

    public TileEntityNaquadahReactorCasing(BlockPos pos, BlockState state) {
        this(GenExtraBlocks.NAQUADAH_REACTOR_CASING, pos, state);
    }

    public TileEntityNaquadahReactorCasing(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    public NaquadahReactorMultiblockData createMultiblock() {
        return new NaquadahReactorMultiblockData(this);
    }

    @Override
    public MultiblockManager<NaquadahReactorMultiblockData> getManager() {
        return MekanismExtras.naquadahReactorManager;
    }

    @Override
    public boolean canBeMaster() {
        return false;
    }

    public void setInjectionRateFromPacket(int rate) {
        NaquadahReactorMultiblockData multiblock = getMultiblock();
        if (multiblock.isFormed()) {
            multiblock.setInjectionRate(Mth.clamp(rate - (rate % 2), 0, NaquadahReactorMultiblockData.MAX_INJECTION));
            markForSave();
        }
    }
}
