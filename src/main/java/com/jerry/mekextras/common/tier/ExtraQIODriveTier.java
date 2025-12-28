package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.IAdvancedTier;

import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;

public enum ExtraQIODriveTier implements IAdvancedTier {

    COLLAPSE(AdvancedTier.ABSOLUTE, 512_000_000_000L, 131_072),// x32 x16
    GAMMA(AdvancedTier.SUPREME, 65_536_000_000_000L, 2_097_152),// x128 x16
    BLACK_HOLE(AdvancedTier.COSMIC, 33_554_432_000_000_000L, 33_554_432),// x512 x16
    SINGULARITY(AdvancedTier.INFINITE, Long.MAX_VALUE, Integer.MAX_VALUE);

    private final AdvancedTier baseTier;
    private final long count;
    private final int types;
    private CachedLongValue countReference;
    private CachedIntValue typesReference;

    ExtraQIODriveTier(AdvancedTier tier, long count, int types) {
        baseTier = tier;
        this.count = count;
        this.types = types;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return baseTier;
    }

    public long getCount() {
        return countReference == null ? getMaxCount() : countReference.getOrDefault();
    }

    public int getTypes() {
        return typesReference == null ? getMaxTypes() : typesReference.getOrDefault();
    }

    public long getMaxCount() {
        return count;
    }

    public int getMaxTypes() {
        return types;
    }

    public void setConfigReference(CachedLongValue countReference, CachedIntValue typesReference) {
        this.countReference = countReference;
        this.typesReference = typesReference;
    }
}
