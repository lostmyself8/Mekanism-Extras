package com.jerry.mekextras.common.tile.multiblock;

import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.tier.IPTier;

import mekanism.common.tile.prefab.TileEntityInternalMultiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityExtraInductionProvider extends TileEntityInternalMultiblock {

    public IPTier tier;

    public TileEntityExtraInductionProvider(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    protected void presetVariables() {
        super.presetVariables();
        tier = ExtraAttribute.getAdvancedTier(getBlockHolder(), IPTier.class);
    }
}
