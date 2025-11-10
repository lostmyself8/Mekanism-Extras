package com.jerry.mekanism_extras.common.tier;

import mekanism.api.tier.BaseTier;

import lombok.Getter;

public enum ExtraQIODriverTier {

    COLLAPSE(BaseTier.BASIC, 512_000_000_000L, 131_072),// x32 x16
    GAMMA(BaseTier.ADVANCED, 65_536_000_000_000L, 2_097_152),// x128 x16
    BLACK_HOLE(BaseTier.ELITE, 33_554_432_000_000_000L, 33_554_432),// x512 x16
    SINGULARITY(BaseTier.ULTIMATE, Long.MAX_VALUE, Integer.MAX_VALUE);

    @Getter
    private final BaseTier baseTier;
    private final long count;
    private final int types;

    ExtraQIODriverTier(BaseTier tier, long count, int types) {
        baseTier = tier;
        this.count = count;
        this.types = types;
    }

    public long getMaxCount() {
        return count;
    }

    public int getMaxTypes() {
        return types;
    }
}
