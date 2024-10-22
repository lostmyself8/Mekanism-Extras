package com.jerry.mekanism_extras.common.tier;

import com.jerry.mekanism_extras.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.api.tier.IAdvanceTier;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.config.value.CachedLongValue;
import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public enum ICTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 32_768_000_000_000L),
    SUPREME(AdvanceTier.SUPREME, 262_144_000_000_000L),
    COSMIC(AdvanceTier.COSMIC, 2_097_152_000_000_000L),
    INFINITE(AdvanceTier.INFINITE, Long.MAX_VALUE);

    private final long advanceMaxEnergy;
    private final AdvanceTier advanceTier;
    @Nullable
    private CachedLongValue storageReference;

    ICTier(AdvanceTier tier, long max) {
        advanceMaxEnergy = max;
        advanceTier = tier;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
    }

    public long getMaxEnergy() {
        return storageReference == null ? getAdvanceMaxEnergy() : storageReference.getOrDefault();
    }

    public long getAdvanceMaxEnergy() {
        return advanceMaxEnergy;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the InductionCellTier a reference to the actual config value object
     */
    public void setConfigReference(CachedLongValue storageReference) {
        this.storageReference = storageReference;
    }
}
