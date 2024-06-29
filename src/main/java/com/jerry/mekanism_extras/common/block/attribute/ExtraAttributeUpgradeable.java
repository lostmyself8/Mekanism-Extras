package com.jerry.mekanism_extras.common.block.attribute;

import mekanism.api.tier.BaseTier;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ExtraAttributeUpgradeable implements Attribute {
    private final Supplier<BlockRegistryObject<?, ?>> upgradeBlock;

    public ExtraAttributeUpgradeable(Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        if (upgradeBlock instanceof MekanismBlocks) {
            this.upgradeBlock = upgradeBlock;
        } else {
            this.upgradeBlock = upgradeBlock;
        }
    }

    @NotNull
    public BlockState upgradeResult(@NotNull BlockState current, @NotNull BaseTier tier) {
        return BlockStateHelper.copyStateData(current, upgradeBlock.get());
    }
}
