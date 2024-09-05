package com.jerry.mekanism_extras.common.block.attribute;

import com.jerry.mekanism_extras.api.tier.IAdvanceTier;
import mekanism.common.MekanismLang;
import mekanism.common.content.blocktype.BlockType;

import java.util.HashMap;
import java.util.Map;

public record ExtraAttributeTier<TIER extends IAdvanceTier>(TIER tier) implements ExtraAttribute {
    private static final Map<IAdvanceTier, BlockType> typeCache = new HashMap<>();

    public ExtraAttributeTier(TIER tier) {
        this.tier = tier;
    }

    public static <T extends IAdvanceTier> BlockType getPassthroughType(T tier) {
        return typeCache.computeIfAbsent(tier, t -> BlockType.BlockTypeBuilder.createBlock(MekanismLang.EMPTY).with(new ExtraAttribute[]{new ExtraAttributeTier<>(t)}).build());
    }
}
