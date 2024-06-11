package com.jerry.mekanism_extras.common.item.qio;

import mekanism.api.tier.BaseTier;

public enum ExtraQIODriverTier {
    ABSOLUTE(BaseTier.BASIC, 128_000_000_000L, 16384),
    SUPREME(BaseTier.ADVANCED, 1_048_000_000_000L, 65536),
    COSMIC(BaseTier.ELITE, 8_000_000_000_000L, 262144),
    INFINITE(BaseTier.ULTIMATE, 16_000_000_000_000L, 1048576);

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
