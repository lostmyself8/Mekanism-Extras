package com.jerry.mekanism_extras.common.block.attribute;

import com.jerry.mekanism_extras.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.api.tier.IAdvanceTier;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public interface ExtraAttribute extends Attribute {

    @Nullable
    static <TIER extends IAdvanceTier> TIER getAdvanceTier(Block block, Class<TIER> tierClass) {
        ExtraAttributeTier<?> attr = Attribute.get(block, ExtraAttributeTier.class);
        return attr == null ? null : tierClass.cast(attr.tier());
    }

    @Nullable
    static AdvanceTier getAdvanceTier(Block block) {
        ExtraAttributeTier<?> attr = Attribute.get(block, ExtraAttributeTier.class);
        return attr == null ? null : attr.tier().getAdvanceTier();
    }
}
