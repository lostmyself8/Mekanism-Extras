package com.jerry.mekanism_extras.common.block.attribute;

import com.jerry.mekanism_extras.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.api.tier.IAdvanceTier;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public interface ExtraAttribute extends Attribute {
    @Nullable
    @SuppressWarnings("unchecked")
    static <TIER extends IAdvanceTier> TIER getAdvanceTier(Block block, Class<TIER> tierClass) {
        ExtraAttributeTier<TIER> attr = Attribute.get(block, ExtraAttributeTier.class);
        return attr == null ? null : attr.tier();
    }

    @Nullable
    static AdvanceTier getAdvanceTier(Block block) {
        ExtraAttributeTier<?> attr = Attribute.get(block, ExtraAttributeTier.class);
        return attr == null ? null : attr.tier().getAdvanceTier();
    }
}
