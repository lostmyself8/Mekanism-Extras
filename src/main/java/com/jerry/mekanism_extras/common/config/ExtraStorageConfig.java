package com.jerry.mekanism_extras.common.config;

import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class ExtraStorageConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;

    public final CachedFloatingLongValue advanceElectricPump;

    public ExtraStorageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Machine Energy Storage Config. This config is synced from server to client.").push("storage");

        advanceElectricPump = CachedFloatingLongValue.define(this, builder, "Base energy storage (Joules).", "electricPump",
                FloatingLong.createConst(400_000));

        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "extra_machine_storage";
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
