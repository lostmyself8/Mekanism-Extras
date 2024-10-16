package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvanceTier;
import com.jerry.mekextras.api.tier.IAdvanceTier;
import mekanism.common.config.value.CachedIntValue;

public enum FTTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 512_000, 128_000),
    SUPREME(AdvanceTier.SUPREME, 1_024_000, 512_000),
    COSMIC(AdvanceTier.COSMIC, 2_048_000, 2_048_000),
    INFINITE(AdvanceTier.INFINITE, 4_096_000, 8_192_00);

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

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the FluidTankTier a reference to the actual config value object
     */
    public void setConfigReference(CachedIntValue storageReference, CachedIntValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
