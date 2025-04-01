package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import mekanism.common.config.value.CachedIntValue;

public enum BTier implements IAdvancedTier {
    ABSOLUTE(AdvancedTier.ABSOLUTE, 2_097_152),
    SUPREME(AdvancedTier.SUPREME, 16_777_216),
    COSMIC(AdvancedTier.COSMIC, 134_217_728),
    INFINITE(AdvancedTier.INFINITE, 1_073_741_824);

    private final int advanceStorage;
    private final AdvancedTier advancedTier;
    private CachedIntValue storageReference;
    BTier(AdvancedTier advancedTier, int i) {
        this.advancedTier = advancedTier;
        this.advanceStorage = i;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
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
