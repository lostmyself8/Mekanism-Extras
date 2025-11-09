package com.jerry.mekanism_extras.common.command;

import com.jerry.mekanism_extras.common.registry.ExtraBlock;

import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;

import mekanism.common.command.builders.StructureBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

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
            buildInteriorLayers(world, start, 1, 7, Blocks.AIR);
            world.setBlockAndUpdate(start.offset(4, 8, 4), ExtraGenBlocks.NAQUADAH_REACTOR_CONTROLLER.getBlock().defaultBlockState());
        }

        // 如果使用忽略边建议重写为对应外壳方，否则会出现玻璃占据框架的问题
        // 如果类似SPS限制了Frame的范围可以不重写
        @Override
        protected Block getWallBlock(BlockPos pos) {
            return ExtraGenBlocks.NAQUADAH_REACTOR_CASING.getBlock();
        }

        @Override
        protected Block getCasing() {
            return ExtraGenBlocks.NAQUADAH_REACTOR_CASING.getBlock();
        }
    }

    public static class MatrixBuilder extends StructureBuilder {

        public MatrixBuilder() {
            super(18, 18, 18);
        }

        @Override
        public void build(Level world, BlockPos start, boolean empty) {
            buildFrame(world, start);
            buildWalls(world, start);
            // 如果内部为空
            if (empty) {
                buildInteriorLayers(world, start, 1, 16, Blocks.AIR);
            } else {
                // 数组中[1]-[15]感应元件
                buildInteriorLayers(world, start, 1, 15, ExtraBlock.INFINITE_INDUCTION_CELL.getBlock());
                // [16]放置感应供应器
                buildInteriorLayer(world, start, 16, ExtraBlock.INFINITE_INDUCTION_PROVIDER.getBlock());
            }
        }

        @Override
        protected Block getCasing() {
            return ExtraBlock.REINFORCED_INDUCTION_CASING.getBlock();
        }
    }

    public static class PlasmaEvaporationPlantBuilder extends StructureBuilder {

        public PlasmaEvaporationPlantBuilder() {
            super(6, 36, 6);
        }

        @Override
        protected void build(Level world, BlockPos start, boolean empty) {
            buildFrame(world, start);
            buildWalls(world, start);
            buildInteriorLayers(world, start, 1, 34, Blocks.AIR);
            buildInteriorLayer(world, start, 18, ExtraGenBlocks.PLASMA_INSULATION_LAYER.getBlock());
            world.setBlockAndUpdate(start.offset(1, 1, 0), ExtraGenBlocks.PLASMA_EVAPORATION_CONTROLLER.getBlock().defaultBlockState());
        }

        @Override
        protected Block getCasing() {
            return ExtraGenBlocks.PLASMA_EVAPORATION_BLOCK.getBlock();
        }

        @Override
        protected Block getRoofBlock(BlockPos pos) {
            return ExtraGenBlocks.PLASMA_EVAPORATION_VENT.getBlock();
        }
    }
}
