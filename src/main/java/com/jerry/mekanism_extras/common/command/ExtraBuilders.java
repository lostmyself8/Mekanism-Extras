package com.jerry.mekanism_extras.common.command;

import com.jerry.mekanism_extras.registery.ExtraBlock;
import mekanism.common.command.builders.StructureBuilder;
import mekanism.common.registries.MekanismBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ExtraBuilders {
    private ExtraBuilders() {

    }

    public static class ColliderBuilder extends StructureBuilder {

        public ColliderBuilder() {
            super(4, 4, 4);
        }

        @Override
        public void build(Level world, BlockPos start, boolean empty) {
            buildFrame(world, start);
            buildWalls(world, start);
//            world.setBlockAndUpdate(start.offset(6, 1, 1), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(7, 1, 1), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(8, 1, 1), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(4, 1, 2), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(5, 1, 2), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(9, 1, 2), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(10, 1, 2), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(3, 1, 3), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(11, 1, 3), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(2, 1, 4), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(12, 1, 4), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(2, 1, 5), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(12, 1, 5), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(1, 1, 6), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(13, 1, 6), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(1, 1, 7), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(13, 1, 7), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(1, 1, 8), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(13, 1, 8), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(2, 1, 9), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(12, 1, 9), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(2, 1, 10), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(12, 1, 10), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(3, 1, 11), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(11, 1, 11), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(4, 1, 12), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(10, 1, 12), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(5, 1, 12), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(9, 1, 12), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(6, 1, 13), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(7, 1, 13), Blocks.AIR.defaultBlockState());
//            world.setBlockAndUpdate(start.offset(8, 1, 13), Blocks.AIR.defaultBlockState());

        }

        @Override
        public Block getCasing() {
            return ExtraBlock.COLLIDER_CASING.getBlock();
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
            if (empty) {
                buildInteriorLayers(world, start, 1, 16, Blocks.AIR);
            } else {
                buildInteriorLayers(world, start, 1, 15, ExtraBlock.INFINITE_INDUCTION_CELL.getBlock());
                buildInteriorLayer(world, start, 16, ExtraBlock.INFINITE_INDUCTION_PROVIDER.getBlock());
            }
        }

        @Override
        protected Block getCasing() {
            return MekanismBlocks.INDUCTION_CASING.getBlock();
        }
    }
}
