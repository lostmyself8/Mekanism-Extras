package com.jerry.mekanism_extras.common.tile.multiblock.provider;

import mekanism.api.providers.IBlockProvider;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.tile.prefab.TileEntityInternalMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ExtraTileEntityInductionProvider extends TileEntityInternalMultiblock {
    public IPTier tier;

    public ExtraTileEntityInductionProvider(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    protected void presetVariables() {
        super.presetVariables();
        tier = Attribute.getTier(getBlockType(), IPTier.class);
    }
}
