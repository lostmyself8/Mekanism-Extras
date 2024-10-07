package com.jerry.mekanism_extras.common.tier;

import com.jerry.mekanism_extras.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.api.tier.IAdvanceTier;
import mekanism.common.config.value.CachedIntValue;

public enum FTTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 4_096_000, 2_048_000),
    SUPREME(AdvanceTier.SUPREME, 32_768_000, 16_384_000),
    COSMIC(AdvanceTier.COSMIC, 262_144_000, 131_072_000),
    INFINITE(AdvanceTier.INFINITE, 2_097_152_000, 1_048_576_000);
    private final int advanceStorage;
    private final int advanceOutput;
    private final AdvanceTier advanceTier;
    private CachedIntValue storageReference;
    private CachedIntValue outputReference;

    FTTier(AdvanceTier tier, int s, int o) {
        advanceStorage = s;
        advanceOutput = o;
        advanceTier = tier;
    }
    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
    }

    public int getStorage() {
        return storageReference == null ? getAdvanceStorage() : storageReference.getOrDefault();
    }

    public int getOutput() {
        return outputReference == null ? getAdvanceOutput() : outputReference.getOrDefault();
    }

    public int getAdvanceStorage() {
        return advanceStorage;
    }

    public int getAdvanceOutput() {
        return advanceOutput;
    }

    public void setConfigReference(CachedIntValue storageReference, CachedIntValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
