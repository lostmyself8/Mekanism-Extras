package com.jerry.mekanism_extras.common.tier;

import com.jerry.mekanism_extras.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.api.tier.IAdvanceTier;

public enum AdvancedFactoryTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 11),
    SUPREME(AdvanceTier.SUPREME, 13),
    COSMIC(AdvanceTier.COSMIC, 15),
    INFINITE(AdvanceTier.INFINITE, 17);

    public final int processes;
    private final AdvanceTier advanceTier;

    AdvancedFactoryTier(AdvanceTier tier, int process) {
        processes = process;
        advanceTier = tier;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
    }
}
