package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.common.api.tier.AdvanceTier;
import com.jerry.mekextras.common.api.tier.IAdvanceTier;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.value.CachedFloatingLongValue;
import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public enum IPTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, FloatingLong.createConst(1_048_576_000L)),
    SUPREME(AdvanceTier.SUPREME, FloatingLong.createConst(8_388_608_000L)),
    COSMIC(AdvanceTier.COSMIC, FloatingLong.createConst(67_108_864_000L)),
    INFINITE(AdvanceTier.INFINITE, FloatingLong.createConst(536_870_912_000L));

    private final FloatingLong advanceOutput;
    private final AdvanceTier advanceTier;
    @Nullable
    private CachedFloatingLongValue outputReference;

    IPTier(AdvanceTier tier, FloatingLong out) {
        advanceOutput = out;
        advanceTier = tier;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
    }

    public FloatingLong getOutput() {
        return outputReference == null ? getAdvanceOutput() : outputReference.getOrDefault();
    }

    public FloatingLong getAdvanceOutput() {
        return advanceOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the InductionProviderTier a reference to the actual config value object
     */
    public void setConfigReference(CachedFloatingLongValue outputReference) {
        this.outputReference = outputReference;
    }
}
