package com.jerry.mekanism_extras.common.block.storage.bin;

import mekanism.api.tier.BaseTier;
import mekanism.api.tier.ITier;
import mekanism.common.config.value.CachedIntValue;

public enum BTier implements ITier {
    ABSOLUTE(BaseTier.BASIC, 2_097_152),
    SUPREME(BaseTier.ADVANCED, 16_777_216),
    COSMIC(BaseTier.ELITE, 134_217_728),
    INFINITE(BaseTier.ULTIMATE, 1_073_741_824);

    private final int baseStorage;
    private final BaseTier baseTier;
    private CachedIntValue storageReference;

    BTier(BaseTier tier, int s) {
        baseTier = tier;
        baseStorage = s;
    }

    @Override
    public BaseTier getBaseTier() {
        return baseTier;
    }

    public int getStorage() {
        return storageReference == null ? getBaseStorage() : storageReference.getOrDefault();
    }

    public int getBaseStorage() {
        return baseStorage;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the BinTier a reference to the actual config value object
     */
    public void setConfigReference(CachedIntValue storageReference) {
        this.storageReference = storageReference;
    }
}
