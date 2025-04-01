package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.config.value.CachedLongValue;
import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public enum IPTier implements IAdvancedTier {
    ABSOLUTE(AdvancedTier.ABSOLUTE, 1_048_576_000L),
    SUPREME(AdvancedTier.SUPREME, 8_388_608_000L),
    COSMIC(AdvancedTier.COSMIC, 67_108_864_000L),
    INFINITE(AdvancedTier.INFINITE, 536_870_912_000L);

    private final long advanceOutput;
    private final AdvancedTier advancedTier;
    @Nullable
    private CachedLongValue outputReference;

    IPTier(AdvancedTier tier, long out) {
        advanceOutput = out;
        advancedTier = tier;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
    }

    public long getOutput() {
        return outputReference == null ? getAdvanceOutput() : outputReference.getOrDefault();
    }

    public long getAdvanceOutput() {
        return advanceOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the InductionProviderTier a reference to the actual config value object
     */
    public void setConfigReference(CachedLongValue outputReference) {
        this.outputReference = outputReference;
    }
}
