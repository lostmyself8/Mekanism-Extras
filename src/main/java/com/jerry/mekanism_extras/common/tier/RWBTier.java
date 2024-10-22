package com.jerry.mekanism_extras.common.tier;

import com.jerry.mekanism_extras.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.api.tier.IAdvanceTier;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;

public enum RWBTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 2_048_000, 20, 4),
    SUPREME(AdvanceTier.SUPREME, 8_192_000, 10, 16),
    COSMIC(AdvanceTier.COSMIC, 32_768_000, 5, 64),
    INFINITE(AdvanceTier.INFINITE, 131_072_000, 5, 256);

    private final long advanceStorage;
    private final int processTicks;
    private final long decayAmount;
    private final AdvanceTier advanceTier;
    private CachedLongValue storageReference;
    private CachedIntValue tickReference;
    private CachedLongValue amountReference;
    RWBTier(AdvanceTier tier, long s, int t, long a) {
        advanceStorage = s;
        processTicks = t;
        decayAmount = a;
        advanceTier = tier;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
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
