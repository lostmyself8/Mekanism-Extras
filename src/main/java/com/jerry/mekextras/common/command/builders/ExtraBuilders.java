package com.jerry.mekextras.common.command.builders;

import com.jerry.mekextras.common.registries.ExtraBlocks;

import com.jerry.genextras.common.registries.GenExtraBlocks;

import mekanism.common.command.builders.StructureBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ExtraBuilders {

    private ExtraBuilders() {}

    public static class NaquadahReactorBuilder extends StructureBuilder {

        public NaquadahReactorBuilder() {
            super(9, 9, 9);
        }

        @Override
        protected void build(Level world, BlockPos start, boolean empty) {
            // 从角落往各边忽略cutoff+1个方块
            buildPartialFrame(world, start, 2);
            buildWalls(world, start);
            // 和数组类似，以0开始算
            buildInteriorLayers(world, start, 1, 7, Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(start.offset(4, 8, 4), GenExtraBlocks.NAQUADAH_REACTOR_CONTROLLER.defaultState());
        }

        // 如果使用忽略边建议重写为对应外壳方，否则会出现玻璃占据框架的问题
        // 如果类似SPS限制了Frame的范围可以不重写
        @Override
        protected BlockState getWallBlock(BlockPos pos) {
            return GenExtraBlocks.NAQUADAH_REACTOR_CASING.defaultState();
        }

        @Override
        protected BlockState getCasing() {
            return GenExtraBlocks.NAQUADAH_REACTOR_CASING.defaultState();
        }
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
