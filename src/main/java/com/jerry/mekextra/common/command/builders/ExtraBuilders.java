package com.jerry.mekextra.common.command.builders;

import com.jerry.mekextra.common.registry.ExtraBlocks;
import mekanism.common.command.builders.StructureBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ExtraBuilders {
    private ExtraBuilders() {

    }

    public static class ReinforcedMatrixBuilder extends StructureBuilder {

        public ReinforcedMatrixBuilder() {
            super(18, 18, 18);
        }

        @Override
        public void build(Level world, BlockPos start, boolean empty) {
            buildFrame(world, start);
            buildWalls(world, start);
            if (empty) {
                buildInteriorLayers(world, start, 1, 16, Blocks.AIR);
            } else {
                buildInteriorLayers(world, start, 1, 15, ExtraBlocks.INFINITE_INDUCTION_CELL.getBlock());
                buildInteriorLayer(world, start, 16, ExtraBlocks.INFINITE_INDUCTION_PROVIDER.getBlock());
            }
        }

        @Override
        protected Block getCasing() {
            return ExtraBlocks.REINFORCED_INDUCTION_CASING.getBlock();
        }
    }
}
