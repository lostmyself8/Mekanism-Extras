package com.jerry.mekanism_extras.common.tier;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;

public enum AdvancedFactoryTier implements IAdvancedTier {

    ABSOLUTE(AdvancedTier.ABSOLUTE, 11),
    SUPREME(AdvancedTier.SUPREME, 13),
    COSMIC(AdvancedTier.COSMIC, 15),
    INFINITE(AdvancedTier.INFINITE, 17);

    public final int processes;
    private final AdvancedTier advancedTier;

    AdvancedFactoryTier(AdvancedTier tier, int process) {
        processes = process;
        advancedTier = tier;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
    }
}
