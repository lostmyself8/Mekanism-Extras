package com.jerry.mekextras.common.tier;


import com.jerry.mekextras.api.tier.AdvanceTier;
import com.jerry.mekextras.api.tier.IAdvanceTier;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;

public enum QIODriveAdvanceTier implements IAdvanceTier {
    COLLAPSE(AdvanceTier.ABSOLUTE, 128_000_000_000L, 16_384),
    GAMMA(AdvanceTier.SUPREME, 1_048_000_000_000L, 65_536),
    BLACK_HOLE(AdvanceTier.COSMIC, 8_000_000_000_000L, 262_144),
    SINGULARITY(AdvanceTier.INFINITE, 16_000_000_000_000L, 1_048_576);

    private final AdvanceTier baseTier;
    private final long count;
    private final int types;
    private CachedLongValue countReference;
    private CachedIntValue typesReference;

    QIODriveAdvanceTier(AdvanceTier tier, long count, int types) {
        baseTier = tier;
        this.count = count;
        this.types = types;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
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
