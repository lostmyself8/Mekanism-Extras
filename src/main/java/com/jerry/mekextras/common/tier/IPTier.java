package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvanceTier;
import com.jerry.mekextras.api.tier.IAdvanceTier;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.config.value.CachedLongValue;
import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public enum IPTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 1_048_576_000L),
    SUPREME(AdvanceTier.SUPREME, 8_388_608_000L),
    COSMIC(AdvanceTier.COSMIC, 67_108_864_000L),
    INFINITE(AdvanceTier.INFINITE, 536_870_912_000L);

    private final long advanceOutput;
    private final AdvanceTier advanceTier;
    @Nullable
    private CachedLongValue outputReference;

    IPTier(AdvanceTier tier, long out) {
        advanceOutput = out;
        advanceTier = tier;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
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
