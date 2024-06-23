package com.jerry.mekanism_extras.common.block.machine.forcefield;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.annotations.ParametersAreNotNullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ForceFieldGeneratorBlock extends BaseEntityBlock {
    public ForceFieldGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    @NothingNullByDefault
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @ParametersAreNotNullByDefault
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ForceFieldGeneratorEntity(pos, state);
    }
}
