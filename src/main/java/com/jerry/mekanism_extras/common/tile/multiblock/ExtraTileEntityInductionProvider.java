package com.jerry.mekanism_extras.common.tile.multiblock;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.tier.IPTier;
import mekanism.api.providers.IBlockProvider;
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
        tier = ExtraAttribute.getAdvanceTier(getBlockType(), IPTier.class);
    }
}
