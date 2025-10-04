package com.jerry.generator_extras.common.config;

import com.jerry.generator_extras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.generator_extras.common.content.plasma.PlasmaEvaporationMultiblockData;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.*;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.config.ModConfig;

public class GeneratorConfig extends BaseMekanismConfig {
    private static final String NAQUADAH_CATEGORY = "naquadah_reactor";
    private static final String HOHLRAUM_CATEGORY = "naquadah_hohlraum";
    private static final String PLASMA_EVAPORATION_CATEGORY = "plasma_evaporation";

    private final ForgeConfigSpec configSpec;

    public final CachedFloatingLongValue energyPerReactorFuel;

    public final CachedDoubleValue reactorThermocoupleEfficiency;
    public final CachedDoubleValue reactorCasingThermalConductivity;
    public final CachedDoubleValue reactorWaterHeatingRatio;
    public final CachedLongValue reactorFuelCapacity;
    public final CachedFloatingLongValue reactorEnergyCapacity;
    public final CachedIntValue reactorWaterPerInjection;
    public final CachedLongValue reactorSteamPerInjection;

    public final CachedLongValue hohlraumMaxGas;
    public final CachedLongValue hohlraumFillRate;

    public final CachedDoubleValue plasmaEvaporationTempMultiplier;
    public final CachedDoubleValue plasmaEvaporationHeatCapacity;
    public final CachedIntValue plasmaEvaporationFluidPerTank;
    public final CachedIntValue plasmaEvaporationOutputFluidTankCapacity;
    public final CachedIntValue plasmaEvaporationPlasmaPerTank;
    public final CachedIntValue plasmaEvaporationOutputPlasmaTankCapacity;
    public final CachedDoubleValue plasmaEvaporationPlasmaConsumeRatio;
    public final CachedIntValue plasmaEvaporationHeatPerInputFluid;
    public final CachedBooleanValue plasmaEvaporationIdleHeatDissipationEnabled;
    public final CachedIntValue plasmaEvaporationIdleHeatDissipation;

    public GeneratorConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Mekanism Extras(generator part) Config. This config is synced between server and client.").push("generators_extras");
        energyPerReactorFuel = CachedFloatingLongValue.define(this, builder, "Affects the Injection Rate, Max Temp, and Ignition Temp.",
                "energyPerFusionFuel", FloatingLong.createConst(10_000_000));
        builder.pop();

        builder.comment("Reactor Settings").push(NAQUADAH_CATEGORY);
        reactorThermocoupleEfficiency = CachedDoubleValue.wrap(this, builder.comment("The fraction of the heat dissipated from the case that is converted to Joules.")
                .defineInRange("thermocoupleEfficiency", 0.1D, 0D, 1D));
        reactorCasingThermalConductivity = CachedDoubleValue.wrap(this, builder.comment("The fraction of heat from the casing that can be transferred to all sources that are not water. Will impact max heat, heat transfer to thermodynamic conductors, and power generation.")
                .defineInRange("casingThermalConductivity", 0.1D, 0.001D, 1D));
        reactorWaterHeatingRatio = CachedDoubleValue.wrap(this, builder.comment("The fraction of the heat from the casing that is dissipated to water when water medium is in use. Will impact max heat, and polonium generation.")
                .defineInRange("waterHeatingRatio", 0.3D, 0D, 1D));
        reactorFuelCapacity = CachedLongValue.wrap(this, builder.comment("Amount of fuel (mB) that the naquadah reactor can store.")
                .defineInRange("fuelCapacity", FluidType.BUCKET_VOLUME, 2, 1_000L * FluidType.BUCKET_VOLUME));
        reactorEnergyCapacity = CachedFloatingLongValue.define(this, builder, "Amount of energy (J) the naquadah reactor can store.",
                "energyCapacity", FloatingLong.createConst(10_000_000_000L), CachedFloatingLongValue.POSITIVE);
        int baseMaxFissile = 1_000 * FluidType.BUCKET_VOLUME;
        reactorWaterPerInjection = CachedIntValue.wrap(this, builder.comment("Amount of water (mB) per injection rate that the naquadah reactor can store. Max = injectionRate * waterPerInjection")
                .defineInRange("waterPerInjection", 1_000 * FluidType.BUCKET_VOLUME, 1, Integer.MAX_VALUE / NaquadahReactorMultiblockData.MAX_INJECTION));
        reactorSteamPerInjection = CachedLongValue.wrap(this, builder.comment("Amount of polonium (mB) per injection rate that the naquadah reactor can store. Max = injectionRate * poloniumPerInjection")
                .defineInRange("poloniumPerInjection", 100L * baseMaxFissile, 1, Long.MAX_VALUE / NaquadahReactorMultiblockData.MAX_INJECTION));
        builder.pop();

        builder.comment("Hohlraum Settings").push(HOHLRAUM_CATEGORY);
        hohlraumMaxGas = CachedLongValue.wrap(this, builder.comment("Hohlraum capacity in mB.")
                .defineInRange("maxGas", 100, 1, Long.MAX_VALUE));
        hohlraumFillRate = CachedLongValue.wrap(this, builder.comment("Amount of Si-U Fuel Hohlraum can accept per tick.")
                .defineInRange("fillRate", 1, 1, Long.MAX_VALUE));
        builder.pop();

        builder.comment("Plasma Evaporation Settings").push(PLASMA_EVAPORATION_CATEGORY);
        plasmaEvaporationTempMultiplier = CachedDoubleValue.wrap(this, builder.comment("Temperature to amount produced ratio for Plasma Evaporation Plant.")
                .defineInRange("tempMultiplier", 0.8, 0.001, 1_000_000));
        plasmaEvaporationHeatCapacity = CachedDoubleValue.wrap(this, builder.comment("Heat capacity of Plasma Evaporation Plant layers (increases amount of energy needed to increase temperature).")
                .defineInRange("heatCapacity", 1000D, 1, 1_000_000));
        plasmaEvaporationFluidPerTank = CachedIntValue.wrap(this, builder.comment("Amount of fluid (mB) that each block of the Plasma Evaporation Plant contributes to the input fluid tank capacity. Max = volume * fluidPerTank")
                .defineInRange("fluidPerTank", 512 * FluidType.BUCKET_VOLUME, 1, Integer.MAX_VALUE / (PlasmaEvaporationMultiblockData.MAX_HEIGHT * 16)));
        plasmaEvaporationOutputFluidTankCapacity = CachedIntValue.wrap(this, builder.comment("Amount of output fluid (mB) that the evaporation plant can store.")
                .defineInRange("outputFluidTankCapacity", 10000 * FluidType.BUCKET_VOLUME, 1, Integer.MAX_VALUE));
        plasmaEvaporationPlasmaPerTank = CachedIntValue.wrap(this, builder.comment("Amount of gas (mB) that each block of the Plasma Evaporation Plant contributes to the input plasma tank capacity. Max = volume * fluidPerTank")
                .defineInRange("plasmaPerTank", 10 * FluidType.BUCKET_VOLUME, 1, Integer.MAX_VALUE / (PlasmaEvaporationMultiblockData.MAX_HEIGHT * 16)));
        plasmaEvaporationOutputPlasmaTankCapacity = CachedIntValue.wrap(this, builder.comment("Amount of output gas (mB) that the Plasma Evaporation Plant can store.")
                .defineInRange("outputPlasmaTankCapacity", 5000 * FluidType.BUCKET_VOLUME, 1, Integer.MAX_VALUE));
        plasmaEvaporationPlasmaConsumeRatio = CachedDoubleValue.wrap(this, builder.comment("Ratio of fluid consumption rate (mB/t) and plasma consumption rate (mB/t).")
                .defineInRange("consumptionRatio", 100, 0.001, 1_000_000));
        plasmaEvaporationHeatPerInputFluid = CachedIntValue.wrap(this, builder.comment("Heat consumed for processing 1 mB of fluid.")
                .defineInRange("heatPerInputFluid", 10, 1, 1_000_000));
        plasmaEvaporationIdleHeatDissipationEnabled = CachedBooleanValue.wrap(this, builder.comment("If disabled, Plasma Evaporation Plant would not dissipate any heat while idle.")
                .define("idleHeatDissipationEnabled", true));
        plasmaEvaporationIdleHeatDissipation = CachedIntValue.wrap(this, builder.comment("Heat dissipation while the Plasma Evaporation Plant is idle. Setting this to 0 has the same effect as disabling heat dissipation.")
                .defineInRange("idleHeatDissipation", 10000, 0, 1_000_000));
        builder.pop();

        this.configSpec = builder.build();
    }
    @Override
    public String getFileName() {
        return "generator_extras";
    }

    @Override
    public ForgeConfigSpec getConfigSpec() {
        return this.configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
