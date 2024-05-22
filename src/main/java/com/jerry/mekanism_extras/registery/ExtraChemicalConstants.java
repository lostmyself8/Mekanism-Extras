package com.jerry.mekanism_extras.registery;

import mekanism.common.base.IChemicalConstant;

public enum ExtraChemicalConstants implements IChemicalConstant {
    MOLTEN_THERMONUCLEAR("molten_thermonuclear", 0x810C0C, 15, 6276.3F, 2000),
    SPECTRUM_PIGMENTS("spectrum_pigments", 0x000000, 15, 124F, 10);
    private final String name;
    private final int color;
    private final int lightLevel;
    private final float temperature;
    private final float density;

    ExtraChemicalConstants(String name, int color, int lightLevel, float temperature, float density) {
        this.name = name;
        this.color = color;
        this.lightLevel = lightLevel;
        this.temperature = temperature;
        this.density = density;
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
