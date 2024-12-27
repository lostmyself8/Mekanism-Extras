package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvanceTier;
import com.jerry.mekextras.api.tier.IAdvanceTier;

public enum AdvanceFactoryTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 11),
    SUPREME(AdvanceTier.SUPREME, 11),
    COSMIC(AdvanceTier.COSMIC, 13),
    INFINITE(AdvanceTier.INFINITE, 13);

    public final int processes;
    private final AdvanceTier advanceTier;

    AdvanceFactoryTier(AdvanceTier tier, int process) {
        processes = process;
        advanceTier = tier;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
    }
}
