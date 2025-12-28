package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.IAdvancedTier;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.config.value.CachedLongValue;

import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public enum ICTier implements IAdvancedTier {

    ABSOLUTE(AdvancedTier.ABSOLUTE, 32_768_000_000_000L),
    SUPREME(AdvancedTier.SUPREME, 262_144_000_000_000L),
    COSMIC(AdvancedTier.COSMIC, 2_097_152_000_000_000L),
    INFINITE(AdvancedTier.INFINITE, Long.MAX_VALUE);

    private final long advanceMaxEnergy;
    private final AdvancedTier advancedTier;
    @Nullable
    private CachedLongValue storageReference;

    ICTier(AdvancedTier tier, long max) {
        advanceMaxEnergy = max;
        advancedTier = tier;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
    }

    public long getMaxEnergy() {
        return storageReference == null ? getAdvanceMaxEnergy() : storageReference.getOrDefault();
    }

    public long getAdvanceMaxEnergy() {
        return advanceMaxEnergy;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the InductionCellTier a reference to the actual config value
     * object
     */
    public void setConfigReference(CachedLongValue storageReference) {
        this.storageReference = storageReference;
    }
}
