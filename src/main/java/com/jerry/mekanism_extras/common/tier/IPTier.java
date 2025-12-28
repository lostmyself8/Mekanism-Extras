package com.jerry.mekanism_extras.common.tier;

import mekanism.api.math.FloatingLong;
import mekanism.api.tier.BaseTier;
import mekanism.api.tier.ITier;
import mekanism.common.config.value.CachedFloatingLongValue;

import org.jetbrains.annotations.Nullable;

public enum IPTier implements ITier {

    ABSOLUTE(BaseTier.BASIC, FloatingLong.createConst(1_048_576_000L)),
    SUPREME(BaseTier.ADVANCED, FloatingLong.createConst(8_388_608_000L)),
    COSMIC(BaseTier.ELITE, FloatingLong.createConst(67_108_864_000L)),
    INFINITE(BaseTier.ULTIMATE, FloatingLong.createConst(536_870_912_000L));

    private final FloatingLong baseOutput;
    private final BaseTier baseTier;
    @Nullable
    private CachedFloatingLongValue outputReference;

    IPTier(BaseTier tier, FloatingLong out) {
        baseOutput = out;
        baseTier = tier;
    }

    @Override
    public BaseTier getBaseTier() {
        return baseTier;
    }

    public FloatingLong getOutput() {
        return outputReference == null ? getBaseOutput() : outputReference.getOrDefault();
    }

    public FloatingLong getBaseOutput() {
        return baseOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the InductionProviderTier a reference to the actual config
     * value object
     */
    public void setConfigReference(CachedFloatingLongValue outputReference) {
        this.outputReference = outputReference;
    }
}
