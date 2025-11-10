package com.jerry.mekanism_extras.common.tier;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;

import mekanism.common.config.value.CachedIntValue;

import lombok.Getter;

public enum BTier implements IAdvancedTier {

    ABSOLUTE(AdvancedTier.ABSOLUTE, 1_048_576),// x4
    SUPREME(AdvancedTier.SUPREME, 8_388_608),// x8
    COSMIC(AdvancedTier.COSMIC, 134_217_728),// x16
    INFINITE(AdvancedTier.INFINITE, Integer.MAX_VALUE); // x16

    @Getter
    private final int advanceStorage;
    private final AdvancedTier advancedTier;
    private CachedIntValue storageReference;

    BTier(AdvancedTier tier, int s) {
        advancedTier = tier;
        advanceStorage = s;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
    }

    public int getStorage() {
        return storageReference == null ? getAdvanceStorage() : storageReference.getOrDefault();
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the BinTier a reference to the actual config value object
     */
    public void setConfigReference(CachedIntValue storageReference) {
        this.storageReference = storageReference;
    }
}
