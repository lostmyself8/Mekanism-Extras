package com.jerry.generator_extras.common.tile.plasma;

import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityPlasmaEvaporationController extends TileEntityPlasmaEvaporationBlock {

    public TileEntityPlasmaEvaporationController(BlockPos pos, BlockState state) {
        super(ExtraGenBlocks.PLASMA_EVAPORATION_CONTROLLER, pos, state);
    }
}
