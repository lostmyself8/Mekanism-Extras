package com.jerry.generator_extras.common.tile.plasma;

import com.jerry.generator_extras.common.content.plasma.PlasmaEvaporationMultiblockData;
import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;
import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.common.tile.prefab.TileEntityMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityPlasmaEvaporationBlock
        extends TileEntityMultiblock<PlasmaEvaporationMultiblockData> {

    public TileEntityPlasmaEvaporationBlock(BlockPos pos, BlockState state) {
        this(ExtraGenBlocks.PLASMA_EVAPORATION_BLOCK, pos, state);
    }

    public TileEntityPlasmaEvaporationBlock(IBlockProvider provider, BlockPos pos, BlockState state) {
        super(provider, pos, state);
    }

    @Override
    public PlasmaEvaporationMultiblockData createMultiblock() {
        return new PlasmaEvaporationMultiblockData(this);
    }

    @Override
    public MultiblockManager<PlasmaEvaporationMultiblockData> getManager() {
        return MekanismExtras.plasmaEvaporationPlantManager;
    }

    @Override
    public boolean canBeMaster() {
        return false;
    }
}
