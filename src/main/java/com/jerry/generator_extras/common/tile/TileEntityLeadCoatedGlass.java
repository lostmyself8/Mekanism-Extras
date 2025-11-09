package com.jerry.generator_extras.common.tile;

import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;

import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.common.tile.prefab.TileEntityStructuralMultiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityLeadCoatedGlass extends TileEntityStructuralMultiblock {

    public TileEntityLeadCoatedGlass(BlockPos pos, BlockState state) {
        super(ExtraGenBlocks.LEAD_COATED_GLASS, pos, state);
    }

    @Override
    public boolean canInterface(MultiblockManager<?> manager) {
        return true;
    }

    @Override
    public boolean structuralGuiAccessAllowed() {
        return true;
    }
}
