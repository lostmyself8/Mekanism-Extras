package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.IAdvancedTier;

public enum ExtraFactoryTier implements IAdvancedTier {

    ABSOLUTE(AdvancedTier.ABSOLUTE, 11),
    SUPREME(AdvancedTier.SUPREME, 13),
    COSMIC(AdvancedTier.COSMIC, 15),
    INFINITE(AdvancedTier.INFINITE, 17);

    public final int processes;
    private final AdvancedTier advancedTier;

    ExtraFactoryTier(AdvancedTier tier, int process) {
        processes = process;
        advancedTier = tier;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
    }
}
