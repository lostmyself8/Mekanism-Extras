package com.jerry.mekextras.common.command.builders;

import com.jerry.mekextras.common.registries.ExtraBlocks;
import mekanism.common.command.builders.StructureBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

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
                buildInteriorLayers(world, start, 1, 16, Blocks.AIR.defaultBlockState());
            } else {
                buildInteriorLayers(world, start, 1, 15, ExtraBlocks.INFINITE_INDUCTION_CELL.defaultState());
                buildInteriorLayer(world, start, 16, ExtraBlocks.INFINITE_INDUCTION_PROVIDER.defaultState());
            }
        }

        @Override
        protected BlockState getCasing() {
            return ExtraBlocks.REINFORCED_INDUCTION_CASING.defaultState();
        }
    }
}
