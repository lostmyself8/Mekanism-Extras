package com.jerry.mekextras.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedLongValue;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ExtraUsageConfig extends BaseMekanismConfig {

    private final ModConfigSpec configSpec;

    public final CachedLongValue advanceElectricPump;

    public ExtraUsageConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        advanceElectricPump = CachedLongValue.definePositive(this, builder, ExtraConfigTranslations.ENERGY_USAGE_ADVANCED_PUMP, "advancedElectricPump", 1_000L);

        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "machine-usage";
    }

    @Override
    public String getTranslation() {
        return "Usage Config";
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
