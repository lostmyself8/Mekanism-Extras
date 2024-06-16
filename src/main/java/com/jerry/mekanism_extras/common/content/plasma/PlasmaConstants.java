package com.jerry.mekanism_extras.common.content.plasma;

import mekanism.common.base.IChemicalConstant;

public enum PlasmaConstants implements IChemicalConstant {
    HYDROGEN("hydrogen", 0xFFFFFFFF, 6_000);

    private final String name;
    private final int color;
    private final int lightLevel;
    private final float temperature;
    private final float density;

    PlasmaConstants(String name, int color, float temperature) {
        this.name = name;
        this.color = color;
        this.lightLevel = 15;
        this.temperature = temperature;
        this.density = 1_400;  // Damn how can I know the density of plasma????? use the density of sun instead
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public float getTemperature() {
        return temperature;
    }

    @Override
    public float getDensity() {
        return density;
    }

    @Override
    public int getLightLevel() {
        return lightLevel;
    }
}
