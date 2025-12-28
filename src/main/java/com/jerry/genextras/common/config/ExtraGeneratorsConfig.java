package com.jerry.genextras.common.config;

import com.jerry.genextras.common.content.naquadah.NaquadahReactorMultiblockData;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedDoubleValue;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.fluids.FluidType;

public class ExtraGeneratorsConfig extends BaseMekanismConfig {

    private static final String NAQUADAH_CATEGORY = "naquadah_reactor";
    private static final String HOHLRAUM_CATEGORY = "naquadah_hohlraum";

    private final ModConfigSpec configSpec;

    public final CachedLongValue energyPerReactorFuel;

    public final CachedDoubleValue reactorThermocoupleEfficiency;
    public final CachedDoubleValue reactorCasingThermalConductivity;
    public final CachedDoubleValue reactorWaterHeatingRatio;
    public final CachedLongValue reactorFuelCapacity;
    public final CachedLongValue reactorEnergyCapacity;
    public final CachedIntValue reactorWaterPerInjection;
    public final CachedLongValue reactorSteamPerInjection;

    public final CachedLongValue hohlraumMaxGas;
    public final CachedLongValue hohlraumFillRate;

    public ExtraGeneratorsConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        GenExtraConfigTranslations.SERVER_HOHLRAUM.applyToBuilder(builder).push(HOHLRAUM_CATEGORY);
        hohlraumMaxGas = CachedLongValue.wrap(this, GenExtraConfigTranslations.SERVER_HOHLRAUM_CAPACITY.applyToBuilder(builder)
                .defineInRange("maxGas", 100, 1, Long.MAX_VALUE));
        hohlraumFillRate = CachedLongValue.wrap(this, GenExtraConfigTranslations.SERVER_HOHLRAUM_FILL_RATE.applyToBuilder(builder)
                .defineInRange("fillRate", 1, 1, Long.MAX_VALUE));
        builder.pop();

        GenExtraConfigTranslations.SERVER_REACTOR.applyToBuilder(builder).push(NAQUADAH_CATEGORY);
        energyPerReactorFuel = CachedLongValue.definePositive(this, builder, GenExtraConfigTranslations.SERVER_REACTOR_FUEL_ENERGY,
                "energyPerFusionFuel", 10_000_000);
        reactorThermocoupleEfficiency = CachedDoubleValue.wrap(this, GenExtraConfigTranslations.SERVER_REACTOR_THERMOCOUPLE_EFFICIENCY.applyToBuilder(builder)
                .defineInRange("thermocoupleEfficiency", 0.1D, 0D, 1D));
        reactorCasingThermalConductivity = CachedDoubleValue.wrap(this, GenExtraConfigTranslations.SERVER_REACTOR_THERMAL_CONDUCTIVITY.applyToBuilder(builder)
                .defineInRange("casingThermalConductivity", 0.1D, 0.001D, 1D));
        reactorWaterHeatingRatio = CachedDoubleValue.wrap(this, GenExtraConfigTranslations.SERVER_REACTOR_HEATING_RATE.applyToBuilder(builder)
                .defineInRange("waterHeatingRatio", 0.3D, 0D, 1D));
        reactorFuelCapacity = CachedLongValue.wrap(this, GenExtraConfigTranslations.SERVER_REACTOR_FUEL_CAPACITY.applyToBuilder(builder)
                .defineInRange("fuelCapacity", FluidType.BUCKET_VOLUME, 2, 1_000L * FluidType.BUCKET_VOLUME));
        reactorEnergyCapacity = CachedLongValue.define(this, builder, GenExtraConfigTranslations.SERVER_REACTOR_ENERGY_CAPACITY,
                "energyCapacity", 10_000_000_000L, 1, Long.MAX_VALUE);
        int baseMaxFissile = 1_000 * FluidType.BUCKET_VOLUME;
        reactorWaterPerInjection = CachedIntValue.wrap(this, GenExtraConfigTranslations.SERVER_REACTOR_WATER_INJECTION.applyToBuilder(builder)
                .defineInRange("waterPerInjection", 1_000 * FluidType.BUCKET_VOLUME, 1, Integer.MAX_VALUE / NaquadahReactorMultiblockData.MAX_INJECTION));
        reactorSteamPerInjection = CachedLongValue.wrap(this, GenExtraConfigTranslations.SERVER_REACTOR_STEAM_INJECTION.applyToBuilder(builder)
                .defineInRange("poloniumPerInjection", 100L * baseMaxFissile, 1, Long.MAX_VALUE / NaquadahReactorMultiblockData.MAX_INJECTION));
        builder.pop();

        this.configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "generators";
    }

    @Override
    public String getTranslation() {
        return "General Config";
    }

    @Override
    public ModConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
