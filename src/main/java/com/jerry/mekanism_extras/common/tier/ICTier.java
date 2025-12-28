package com.jerry.mekanism_extras.common.tier;

import mekanism.api.math.FloatingLong;
import mekanism.api.tier.BaseTier;
import mekanism.api.tier.ITier;
import mekanism.common.config.value.CachedFloatingLongValue;

import org.jetbrains.annotations.Nullable;

public enum ICTier implements ITier {

    ABSOLUTE(BaseTier.BASIC, FloatingLong.createConst(32_768_000_000_000L)),
    SUPREME(BaseTier.ADVANCED, FloatingLong.createConst(262_144_000_000_000L)),
    COSMIC(BaseTier.ELITE, FloatingLong.createConst(2_097_152_000_000_000L)),
    INFINITE(BaseTier.ULTIMATE, FloatingLong.createConst(Long.MAX_VALUE));

    private final FloatingLong baseMaxEnergy;
    private final BaseTier baseTier;
    @Nullable
    private CachedFloatingLongValue storageReference;

    ICTier(BaseTier tier, FloatingLong max) {
        baseMaxEnergy = max;
        baseTier = tier;
    }

    @Override
    public BaseTier getBaseTier() {
        return baseTier;
    }

    public FloatingLong getMaxEnergy() {
        return storageReference == null ? getBaseMaxEnergy() : storageReference.getOrDefault();
    }

    public FloatingLong getBaseMaxEnergy() {
        return baseMaxEnergy;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the InductionCellTier a reference to the actual config value
     * object
     */
    public void setConfigReference(CachedFloatingLongValue storageReference) {
        this.storageReference = storageReference;
    }
}
