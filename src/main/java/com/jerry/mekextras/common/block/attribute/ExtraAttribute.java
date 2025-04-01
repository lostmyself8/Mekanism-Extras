package com.jerry.mekextras.common.block.attribute;

import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public interface ExtraAttribute extends Attribute {

    @Nullable
    static <TIER extends IAdvancedTier> TIER getAdvanceTier(Holder<Block> block, Class<TIER> tierClass) {
        return getAdvanceTier(block.value(), tierClass);
    }

    @Nullable
    static <TIER extends IAdvancedTier> TIER getAdvanceTier(Block block, Class<TIER> tierClass) {
        ExtraAttributeTier<?> attr = Attribute.get(block, ExtraAttributeTier.class);
        return attr == null ? null : tierClass.cast(attr.tier());
    }

    @Nullable
    static AdvancedTier getAdvanceTier(Block block) {
        ExtraAttributeTier<?> attr = Attribute.get(block, ExtraAttributeTier.class);
        return attr == null ? null : attr.tier().getAdvanceTier();
    }
}
