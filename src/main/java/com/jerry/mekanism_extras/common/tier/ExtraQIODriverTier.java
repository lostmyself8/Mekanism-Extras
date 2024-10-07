package com.jerry.mekanism_extras.common.tier;

import mekanism.api.tier.BaseTier;

public enum ExtraQIODriverTier {
    COLLAPSE(BaseTier.BASIC, 128_000_000_000L, 16384),
    GAMMA(BaseTier.ADVANCED, 1_048_000_000_000L, 65536),
    BLACK_HOLE(BaseTier.ELITE, 8_000_000_000_000L, 262144),
    SINGULARITY(BaseTier.ULTIMATE, 16_000_000_000_000L, 1048576);

    private final BaseTier baseTier;
    private final long count;
    private final int types;

    ExtraQIODriverTier(BaseTier tier, long count, int types) {
        baseTier = tier;
        this.count = count;
        this.types = types;
    }

    public BaseTier getBaseTier() {
        return baseTier;
    }

    public long getMaxCount() {
        return count;
    }

    public int getMaxTypes() {
        return types;
    }
}
