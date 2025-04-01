package com.jerry.mekanism_extras.common.block.attribute;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ExtraAttributeUpgradeable implements ExtraAttribute {
    private final Supplier<BlockRegistryObject<?, ?>> upgradeBlock;

    public ExtraAttributeUpgradeable(Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        this.upgradeBlock = upgradeBlock;
    }

    @NotNull
    public BlockState upgradeResult(@NotNull BlockState current, @NotNull AdvancedTier tier) {
        return BlockStateHelper.copyStateData(current, upgradeBlock.get());
    }
}
