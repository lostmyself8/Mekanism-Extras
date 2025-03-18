package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvanceTier;
import com.jerry.mekextras.api.tier.IAdvanceTier;

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
