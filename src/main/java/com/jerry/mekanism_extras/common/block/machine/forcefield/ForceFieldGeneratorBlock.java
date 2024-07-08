package com.jerry.mekanism_extras.common.block.machine.forcefield;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.annotations.ParametersAreNotNullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForceFieldGeneratorBlock extends BaseEntityBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public ForceFieldGeneratorBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.getStateDefinition()
                .any()
                .setValue(POWERED, false));
    }

    @Override
    @NothingNullByDefault
    public @NotNull RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @ParametersAreNotNullByDefault
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ForceFieldGeneratorEntity(pos, state);
    }

    @Override
    @NothingNullByDefault
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        state.setValue(POWERED, level.getBlockEntity(pos).serializeNBT().getBoolean("powered"));
    }


}
