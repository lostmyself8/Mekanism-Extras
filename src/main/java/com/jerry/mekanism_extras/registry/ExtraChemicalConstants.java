package com.jerry.mekanism_extras.registry;

import mekanism.common.base.IChemicalConstant;

public enum ExtraChemicalConstants implements IChemicalConstant {
    //一定要加FF不然jei不会有流体贴图
    MOLTEN_THERMONUCLEAR("molten_thermonuclear", 0xFF810C0C, 15, 6276.3F, 2_300),
    SILICON_TETRAFLUORIDE("silicon_tetrafluoride", 0xFFC8C8C8, 0, 100F, 4_690),
    FLUORINATED_SILICON_URANIUM_FUEL("fluorinated_silicon_uranium_fuel", 0xFFB1B30C, 0, 500F, 6_520),
    SILICON_URANIUM_FUEL("silicon_uranium_fuel", 0xFF837906, 0, 300F, 5_930),
    RICH_SILICON_LIQUID_FUEL("rich_silicon_liquid_fuel", 0xFFEFEFEF, 0, 83F, 4_450),
    RICH_URANIUM_LIQUID_FUEL("rich_uranium_liquid_fuel", 0xFF6C864A, 0, 83F, 4_850),
    FISSILE_FUEL("fissile_fuel", 0xFF2E332F, 0, 684F, 4530),
    SPECTRUM_PIGMENTS("spectrum_pigments", 0xFF000000, 0, 124F, 10);
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
