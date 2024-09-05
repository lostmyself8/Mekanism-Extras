package com.jerry.mekanism_extras.common.tier;

import com.jerry.mekanism_extras.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.api.tier.IAdvanceTier;
import mekanism.common.config.value.CachedIntValue;

public enum BTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 2_097_152),
    SUPREME(AdvanceTier.SUPREME, 16_777_216),
    COSMIC(AdvanceTier.COSMIC, 134_217_728),
    INFINITE(AdvanceTier.INFINITE, 1_073_741_824);

    private final int advanceStorage;
    private final AdvanceTier advanceTier;
    private CachedIntValue storageReference;

    BTier(AdvanceTier tier, int s) {
        advanceTier = tier;
        advanceStorage = s;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
    }

    public int getStorage() {
        return storageReference == null ? getAdvanceStorage() : storageReference.getOrDefault();
    }

    public int getAdvanceStorage() {
        return advanceStorage;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the BinTier a reference to the actual config value object
     */
    public void setConfigReference(CachedIntValue storageReference) {
        this.storageReference = storageReference;
    }
}
