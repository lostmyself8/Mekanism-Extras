package com.jerry.mekanism_extras.common.registry;

import mekanism.common.base.IChemicalConstant;

public enum ExtraChemicalConstants implements IChemicalConstant {
    // The alpha value of the color must be added, or JEI doesn't render the gas
    MOLTEN_THERMONUCLEAR("molten_thermonuclear", 0xFF810C0C, 15, 6276.3F, 2_300),
    NAQUADAH_HEXAFLUORIDE("naquadah_hexafluoride", 0xFFC8C8C8, 0, 300F, 4_690),
    FLUORINATED_NAQUADAH_URANIUM_FUEL("fluorinated_naquadah_uranium_fuel", 0xFFB1B30C, 0, 800F, 6_520),
    NAQUADAH_URANIUM_FUEL("naquadah_uranium_fuel", 0xFF837906, 0, 1100F, 5_930),
    RICH_NAQUADAH_FUEL("rich_naquadah_fuel", 0xFFEFEFEF, 0, 3350F, 4_450),
    RICH_URANIUM_FUEL("rich_uranium_fuel", 0xFF6C864A, 0, 350F, 4_850),
    POLONIUM208("polonium-208", 0xFF1B9E7B, 0, 8532.8F, 4530),
    HELIUM_PLASMA("helium_plasma", 0xFFFF8306, 0, 12_000F, 0.1785F),
    LITHIUM_PLASMA("lithium_plasma", 0xFFCC0000, 0, 18_000F, 5340F),
    IRON_PLASMA("iron_plasma", 0xFF808080, 0, 30_000F, 6_600F),
    OSMIUM_PLASMA("osmium_plasma", 0xFF7AC3C3, 0, 40_000F, 22_000F),
    PLUTONIUM_PLASMA("plutonium_plasma", 0xFF25A7B0, 0, 64_000F, 9_000F),
    HELIUM("helium", 0xFFFF8306, 0, 5.35F, 0.1785F),
    SUPERHEATED_HELIUM("superheated_helium", 0xFFCA6909, 0, 1_500F, 0.15F),
    VAPORIZED_IRON("vaporized_iron", 0xFF9F9F9F, 0, 1_539F, 7_000F),
    ;

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
