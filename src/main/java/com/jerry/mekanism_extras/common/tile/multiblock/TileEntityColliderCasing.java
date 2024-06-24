package com.jerry.mekanism_extras.common.tile.multiblock;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.content.collider.ColliderMultiblockData;
import com.jerry.mekanism_extras.registry.ExtraBlock;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.common.tile.prefab.TileEntityMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityColliderCasing extends TileEntityMultiblock<ColliderMultiblockData> {
    public TileEntityColliderCasing(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    public TileEntityColliderCasing(BlockPos pos, BlockState state) {
        this(ExtraBlock.COLLIDER_CASING, pos, state);
    }

    @Override
    public ColliderMultiblockData createMultiblock() {
        return new ColliderMultiblockData(this);
    }

    @Override
    public MultiblockManager<ColliderMultiblockData> getManager() {
        return MekanismExtras.colliderManager;
    }
}
