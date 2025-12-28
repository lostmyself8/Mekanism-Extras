package com.jerry.mekextras.common.block.basic;

import com.jerry.mekextras.common.resource.BlockExtraResourceInfo;
import mekanism.common.block.BlockMekanism;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BlockExtraResource extends BlockMekanism {

    @NotNull
    private final BlockExtraResourceInfo resource;

    //TODO: Isn't as "generic"? So make it be from one BlockType thing?
    public BlockExtraResource(@NotNull BlockExtraResourceInfo resource) {
        super(resource.modifyProperties(Properties.of().requiresCorrectToolForDrops()));
        this.resource = resource;
    }

    @NotNull
    public BlockExtraResourceInfo getResourceInfo() {
        return resource;
    }

    @Override
    public boolean isPortalFrame(BlockState state, BlockGetter world, BlockPos pos) {
        return resource.isPortalFrame();
    }
}