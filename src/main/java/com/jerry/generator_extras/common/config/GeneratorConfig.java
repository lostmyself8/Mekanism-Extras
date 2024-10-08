package com.jerry.generator_extras.common.config;

import com.jerry.generator_extras.common.content.reactor.NaquadahReactorMultiblockData;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedDoubleValue;
import mekanism.common.config.value.CachedFloatingLongValue;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.config.ModConfig;

public class GeneratorConfig extends BaseMekanismConfig {
    private static final String NAQUADAH_CATEGORY = "naquadah_reactor";
    private static final String HOHLRAUM_CATEGORY = "naquadah_hohlraum";

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

    public GeneratorConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Mekanism Extras(generator part) Config. This config is synced between server and client.").push("generators_extras");
        energyPerReactorFuel = CachedFloatingLongValue.define(this, builder, "Affects the Injection Rate, Max Temp, and Ignition Temp.",
                "energyPerFusionFuel", FloatingLong.createConst(10_000_000));
        builder.pop();

        builder.comment("Reactor Settings").push(NAQUADAH_CATEGORY);
        reactorThermocoupleEfficiency = CachedDoubleValue.wrap(this, builder.comment("The fraction of the heat dissipated from the case that is converted to Joules.")
                .defineInRange("thermocoupleEfficiency", 0.1D, 0D, 1D));
        reactorCasingThermalConductivity = CachedDoubleValue.wrap(this, builder.comment("The fraction fraction of heat from the casing that can be transferred to all sources that are not water. Will impact max heat, heat transfer to thermodynamic conductors, and power generation.")
                .defineInRange("casingThermalConductivity", 0.1D, 0.001D, 1D));
        reactorWaterHeatingRatio = CachedDoubleValue.wrap(this, builder.comment("The fraction of the heat from the casing that is dissipated to water when water medium is in use. Will impact max heat, and polonium generation.")
                .defineInRange("waterHeatingRatio", 0.3D, 0D, 1D));
        reactorFuelCapacity = CachedLongValue.wrap(this, builder.comment("Amount of fuel (mB) that the reactor reactor can store.")
                .defineInRange("fuelCapacity", FluidType.BUCKET_VOLUME, 2, 1_000L * FluidType.BUCKET_VOLUME));
        reactorEnergyCapacity = CachedFloatingLongValue.define(this, builder, "Amount of energy (J) the reactor reactor can store.",
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
