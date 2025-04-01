package com.jerry.mekanism_extras.common.block.attribute;

import com.jerry.mekanism_extras.api.tier.IAdvancedTier;
import mekanism.common.MekanismLang;
import mekanism.common.content.blocktype.BlockType;

import java.util.HashMap;
import java.util.Map;

public record ExtraAttributeTier<TIER extends IAdvancedTier>(TIER tier) implements ExtraAttribute {
    private static final Map<IAdvancedTier, BlockType> typeCache = new HashMap<>();

    public static <T extends IAdvancedTier> BlockType getPassthroughType(T tier) {
        return typeCache.computeIfAbsent(tier, t -> BlockType.BlockTypeBuilder.createBlock(MekanismLang.EMPTY).with(new ExtraAttribute[]{new ExtraAttributeTier<>(t)}).build());
    }
}
