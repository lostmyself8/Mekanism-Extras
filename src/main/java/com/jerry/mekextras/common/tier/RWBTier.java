package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.IAdvancedTier;

import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;

public enum RWBTier implements IAdvancedTier {

    ABSOLUTE(AdvancedTier.ABSOLUTE, 2_048_000, 20, 4),
    SUPREME(AdvancedTier.SUPREME, 8_192_000, 10, 16),
    COSMIC(AdvancedTier.COSMIC, 32_768_000, 5, 64),
    INFINITE(AdvancedTier.INFINITE, 131_072_000, 5, 256);

    private final long advanceStorage;
    private final int processTicks;
    private final long decayAmount;
    private final AdvancedTier advancedTier;
    private CachedLongValue storageReference;
    private CachedIntValue tickReference;
    private CachedLongValue amountReference;

    RWBTier(AdvancedTier tier, long s, int t, long a) {
        advanceStorage = s;
        processTicks = t;
        decayAmount = a;
        advancedTier = tier;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
    }

    public long getStorage() {
        return storageReference == null ? getAdvanceStorage() : storageReference.getOrDefault();
    }

    public int getProcessTicks() {
        return tickReference == null ? getAdvanceProcessTicks() : tickReference.getOrDefault();
    }

    public long getDecayAmount() {
        return amountReference == null ? getAdvanceDecayAmount() : amountReference.getOrDefault();
    }

    public long getAdvanceStorage() {
        return advanceStorage;
    }

    public int getAdvanceProcessTicks() {
        return processTicks;
    }

    public long getAdvanceDecayAmount() {
        return decayAmount;
    }

    public void setConfigReference(CachedLongValue storageReference, CachedIntValue tickReference, CachedLongValue amountReference) {
        this.storageReference = storageReference;
        this.tickReference = tickReference;
        this.amountReference = amountReference;
    }
}
