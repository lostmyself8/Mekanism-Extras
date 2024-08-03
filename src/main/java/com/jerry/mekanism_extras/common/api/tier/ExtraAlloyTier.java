package com.jerry.mekanism_extras.common.api.tier;

public enum ExtraAlloyTier implements IAdvanceTier{
    THERMONUCLEAR("thermonuclear", AdvanceTier.SUPREME),
    SHINING("shining", AdvanceTier.COSMIC),
    SPECTRUM("spectrum", AdvanceTier.INFINITE);

    public final String name;
    public final AdvanceTier advanceTier;

    ExtraAlloyTier(String name, AdvanceTier tier) {
        this.advanceTier = tier;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
    }
}
