package com.jerry.mekanism_extras.api.tier;

import lombok.Getter;

public enum ExtraAlloyTier implements IAdvancedTier {

    THERMONUCLEAR("thermonuclear", AdvancedTier.SUPREME),
    SHINING("shining", AdvancedTier.COSMIC),
    SPECTRUM("spectrum", AdvancedTier.INFINITE);

    @Getter
    public final String name;
    public final AdvancedTier advancedTier;

    ExtraAlloyTier(String name, AdvancedTier tier) {
        this.advancedTier = tier;
        this.name = name;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
    }
}
