package com.jerry.mekanism_extras.common.config;

import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedBooleanValue;
import mekanism.common.config.value.CachedFloatingLongValue;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class ExtraStorageConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;

    public final CachedFloatingLongValue advanceElectricPump;
    public final CachedBooleanValue allowRadioactiveChemicalInChemicalTanks;

    public ExtraStorageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Machine Storage Config. This config is synced from server to client.").push("storage");

        advanceElectricPump = CachedFloatingLongValue.define(this, builder, "Base energy storage (Joules).", "electricPump",
                FloatingLong.createConst(400_000));
        allowRadioactiveChemicalInChemicalTanks = CachedBooleanValue.wrap(this, builder.comment("Whether radioactive chemicals are allowed to be stored in Chemical Tanks with a tier of at least Absolute. Spent Nuclear Waste cannot be stored, regardless of this config.")
                .define("allowRadioactiveChemicalInChemicalTanks", true));

        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "extras_machine_storage";
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
