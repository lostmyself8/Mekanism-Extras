package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.common.api.tier.AdvanceTier;
import com.jerry.mekextras.common.api.tier.IAdvanceTier;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.value.CachedFloatingLongValue;
import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public enum ICTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, FloatingLong.createConst(32_768_000_000_000L)),
    SUPREME(AdvanceTier.SUPREME, FloatingLong.createConst(262_144_000_000_000L)),
    COSMIC(AdvanceTier.COSMIC, FloatingLong.createConst(2_097_152_000_000_000L)),
    INFINITE(AdvanceTier.INFINITE, FloatingLong.createConst(Long.MAX_VALUE));

    private final FloatingLong advanceMaxEnergy;
    private final AdvanceTier advanceTier;
    @Nullable
    private CachedFloatingLongValue storageReference;

    ICTier(AdvanceTier tier, FloatingLong max) {
        advanceMaxEnergy = max;
        advanceTier = tier;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
    }

    public FloatingLong getMaxEnergy() {
        return storageReference == null ? getAdvanceMaxEnergy() : storageReference.getOrDefault();
    }

    public FloatingLong getAdvanceMaxEnergy() {
        return advanceMaxEnergy;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the InductionCellTier a reference to the actual config value object
     */
    public void setConfigReference(CachedFloatingLongValue storageReference) {
        this.storageReference = storageReference;
    }
}
