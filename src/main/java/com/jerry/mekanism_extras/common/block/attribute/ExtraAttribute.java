package com.jerry.mekanism_extras.common.block.attribute;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;

import mekanism.common.block.attribute.Attribute;

import net.minecraft.world.level.block.Block;

import org.jetbrains.annotations.Nullable;

public interface ExtraAttribute extends Attribute {

    @Nullable
    @SuppressWarnings("unchecked")
    static <TIER extends IAdvancedTier> TIER getTier(Block block, Class<TIER> tierClass) {
        ExtraAttributeTier<TIER> attr = Attribute.get(block, ExtraAttributeTier.class);
        return attr == null ? null : attr.tier();
    }

    @Nullable
    static AdvancedTier getAdvanceTier(Block block) {
        ExtraAttributeTier<?> attr = Attribute.get(block, ExtraAttributeTier.class);
        return attr == null ? null : attr.tier().getAdvanceTier();
    }
}
