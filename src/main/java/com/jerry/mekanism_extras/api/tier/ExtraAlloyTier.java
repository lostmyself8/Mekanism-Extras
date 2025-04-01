package com.jerry.mekanism_extras.api.tier;

public enum ExtraAlloyTier implements IAdvancedTier {
    THERMONUCLEAR("thermonuclear", AdvancedTier.SUPREME),
    SHINING("shining", AdvancedTier.COSMIC),
    SPECTRUM("spectrum", AdvancedTier.INFINITE);

    public final String name;
    public final AdvancedTier advancedTier;

    ExtraAlloyTier(String name, AdvancedTier tier) {
        this.advancedTier = tier;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
    }
}
