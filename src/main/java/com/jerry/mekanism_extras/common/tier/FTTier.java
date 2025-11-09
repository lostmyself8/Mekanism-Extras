package com.jerry.mekanism_extras.common.tier;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;

import mekanism.common.config.value.CachedIntValue;

public enum FTTier implements IAdvancedTier {

    ABSOLUTE(AdvancedTier.ABSOLUTE, 4_096_000, 2_048_000),// x16
    SUPREME(AdvancedTier.SUPREME, 32_768_000, 16_384_000),// x8
    COSMIC(AdvancedTier.COSMIC, 262_144_000, 131_072_000),// x8
    INFINITE(AdvancedTier.INFINITE, 2_097_152_000, 1_048_576_000);// x8

    private final int advanceStorage;
    private final int advanceOutput;
    private final AdvancedTier advancedTier;
    private CachedIntValue storageReference;
    private CachedIntValue outputReference;

    FTTier(AdvancedTier tier, int s, int o) {
        advanceStorage = s;
        advanceOutput = o;
        advancedTier = tier;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
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
