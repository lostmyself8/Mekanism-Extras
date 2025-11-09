package com.jerry.generator_extras.common.tile.plasma;

import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;

import mekanism.common.tile.prefab.TileEntityInternalMultiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityPlasmaInsulationLayer extends TileEntityInternalMultiblock {

    public TileEntityPlasmaInsulationLayer(BlockPos pos, BlockState state) {
        super(ExtraGenBlocks.PLASMA_INSULATION_LAYER, pos, state);
    }
}
