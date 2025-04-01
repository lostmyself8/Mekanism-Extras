package com.jerry.mekextras.common.tier;


import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;

public enum QIODriveAdvancedTier implements IAdvancedTier {
    COLLAPSE(AdvancedTier.ABSOLUTE, 128_000_000_000L, 16_384),
    GAMMA(AdvancedTier.SUPREME, 1_048_000_000_000L, 65_536),
    BLACK_HOLE(AdvancedTier.COSMIC, 8_000_000_000_000L, 262_144),
    SINGULARITY(AdvancedTier.INFINITE, 16_000_000_000_000L, 1_048_576);

    private final AdvancedTier baseTier;
    private final long count;
    private final int types;
    private CachedLongValue countReference;
    private CachedIntValue typesReference;

    QIODriveAdvancedTier(AdvancedTier tier, long count, int types) {
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
