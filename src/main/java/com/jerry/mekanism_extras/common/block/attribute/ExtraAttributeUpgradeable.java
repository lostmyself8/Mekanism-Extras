package com.jerry.mekanism_extras.common.block.attribute;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;

import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.registration.impl.BlockRegistryObject;

import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public record ExtraAttributeUpgradeable(Supplier<BlockRegistryObject<?, ?>> upgradeBlock) implements ExtraAttribute {

    @NotNull
    public BlockState upgradeResult(@NotNull BlockState current, @NotNull AdvancedTier tier) {
        return BlockStateHelper.copyStateData(current, upgradeBlock.get());
    }
}
