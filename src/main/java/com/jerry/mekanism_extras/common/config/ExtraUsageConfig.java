package com.jerry.mekanism_extras.common.config;

import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class ExtraUsageConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;

    public final CachedFloatingLongValue advanceElectricPump;

    public ExtraUsageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Machine Energy Usage Config. This config is synced from server to client.").push("usage");

        advanceElectricPump = CachedFloatingLongValue.define(this, builder, "Energy per operation tick (Joules).", "advanceElectricPump",
                FloatingLong.createConst(1_000));

        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "extra_machine_usage";
    }

    @Override
    public ForgeConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }

    @Override
    public boolean addToContainer() {
        return false;
    }
}
