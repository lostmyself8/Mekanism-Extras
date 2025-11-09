package com.jerry.mekanism_extras.common.block.basic;

import com.jerry.mekanism_extras.common.resource.ExtraBlockResourceInfo;

import mekanism.common.block.BlockMekanism;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

public class ExtraBlockResource extends BlockMekanism {

    @NotNull
    private final ExtraBlockResourceInfo resource;

    // TODO: Isn't as "generic"? So make it be from one BlockType thing?
    public ExtraBlockResource(@NotNull ExtraBlockResourceInfo resource) {
        super(resource.modifyProperties(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()));
        this.resource = resource;
    }

    @NotNull
    public ExtraBlockResourceInfo getResourceInfo() {
        return resource;
    }

    @Override
    public boolean isPortalFrame(BlockState state, BlockGetter world, BlockPos pos) {
        return resource.isPortalFrame();
    }
}
