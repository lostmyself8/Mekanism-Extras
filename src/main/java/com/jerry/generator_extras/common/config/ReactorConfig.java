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

public class ReactorConfig extends BaseMekanismConfig {
    private static final String NAQUADAH_CATEGORY = "naquadah_reactor";

    private final ForgeConfigSpec configSpec;

    public final CachedFloatingLongValue energyPerReactorFuel;

    public final CachedDoubleValue reactorThermocoupleEfficiency;
    public final CachedDoubleValue reactorCasingThermalConductivity;
    public final CachedDoubleValue reactorFissileHeatingRatio;
    public final CachedLongValue reactorFuelCapacity;
    public final CachedFloatingLongValue reactorEnergyCapacity;
    public final CachedIntValue reactorFissilePerInjection;
    public final CachedLongValue reactorPoloniumPerInjection;

    public ReactorConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Mekanism Extras(generator part) Config. This config is synced between server and client.").push("generators_extras");
        energyPerReactorFuel = CachedFloatingLongValue.define(this, builder, "Affects the Injection Rate, Max Temp, and Ignition Temp.",
                "energyPerFusionFuel", FloatingLong.createConst(10_000_000));
        builder.pop();

        builder.comment("Reactor Settings").push(NAQUADAH_CATEGORY);
        reactorThermocoupleEfficiency = CachedDoubleValue.wrap(this, builder.comment("The fraction of the heat dissipated from the case that is converted to Joules.")
                .defineInRange("thermocoupleEfficiency", 0.05D, 0D, 1D));
        reactorCasingThermalConductivity = CachedDoubleValue.wrap(this, builder.comment("The fraction fraction of heat from the casing that can be transferred to all sources that are not fissile. Will impact max heat, heat transfer to thermodynamic conductors, and power generation.")
                .defineInRange("casingThermalConductivity", 0.1D, 0.001D, 1D));
        reactorFissileHeatingRatio = CachedDoubleValue.wrap(this, builder.comment("The fraction of the heat from the casing that is dissipated to fissile when fissile medium is in use. Will impact max heat, and polonium generation.")
                .defineInRange("fissileHeatingRatio", 0.3D, 0D, 1D));
        reactorFuelCapacity = CachedLongValue.wrap(this, builder.comment("Amount of fuel (mB) that the reactor reactor can store.")
                .defineInRange("fuelCapacity", (long) FluidType.BUCKET_VOLUME, 2, 1_000L * FluidType.BUCKET_VOLUME));
        reactorEnergyCapacity = CachedFloatingLongValue.define(this, builder, "Amount of energy (J) the reactor reactor can store.",
                "energyCapacity", FloatingLong.createConst(1_000_000_000), CachedFloatingLongValue.POSITIVE);
        int baseMaxFissile = 1_000 * FluidType.BUCKET_VOLUME;
        reactorFissilePerInjection = CachedIntValue.wrap(this, builder.comment("Amount of fissile (mB) per injection rate that the reactor reactor can store. Max = injectionRate * fissilePerInjection")
                .defineInRange("fissilePerInjection", 1_000 * FluidType.BUCKET_VOLUME, 1, Integer.MAX_VALUE / NaquadahReactorMultiblockData.MAX_INJECTION));
        reactorPoloniumPerInjection = CachedLongValue.wrap(this, builder.comment("Amount of polonium (mB) per injection rate that the reactor reactor can store. Max = injectionRate * poloniumPerInjection")
                .defineInRange("poloniumPerInjection", 100L * baseMaxFissile, 1, Long.MAX_VALUE / NaquadahReactorMultiblockData.MAX_INJECTION));
        builder.pop();

        this.configSpec = builder.build();
    }
    @Override
    public String getFileName() {
        return "reactor";
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
