package com.jerry.mekanism_extras.common.command;

import com.jerry.mekanism_extras.integration.mekgenerators.genregistry.ExtraGenBlock;
import com.jerry.mekanism_extras.registry.ExtraBlock;
import mekanism.common.command.builders.StructureBuilder;
import mekanism.common.registries.MekanismBlocks;
import mekanism.generators.common.registries.GeneratorsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ExtraBuilders {
    private ExtraBuilders() {

    }

    public static class NaquadahReactorBuilder extends StructureBuilder {

        public NaquadahReactorBuilder() {
            super(5, 5, 5);
        }

        @Override
        protected void build(Level world, BlockPos start, boolean empty) {
            buildPartialFrame(world, start, 1);
            buildWalls(world, start);
            buildInteriorLayers(world, start, 1, 3, Blocks.AIR);
            world.setBlockAndUpdate(start.offset(2, 4, 2), ExtraGenBlock.NAQUADAH_REACTOR_CONTROLLER.getBlock().defaultBlockState());
        }

        @Override
        protected Block getWallBlock(BlockPos pos) {
            return GeneratorsBlocks.REACTOR_GLASS.getBlock();
        }

        @Override
        protected Block getCasing() {
            return ExtraGenBlock.NAQUADAH_REACTOR_CASING.getBlock();
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
