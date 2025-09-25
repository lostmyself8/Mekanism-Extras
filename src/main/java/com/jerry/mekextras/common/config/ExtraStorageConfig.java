package com.jerry.mekextras.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedLongValue;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ExtraStorageConfig extends BaseMekanismConfig {

    private final ModConfigSpec configSpec;

    public final CachedLongValue advanceElectricPump;

    public ExtraStorageConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        advanceElectricPump = CachedLongValue.definedMin(this, builder, ExtraConfigTranslations.ENERGY_STORAGE_ADVANCED_PUMP, "advancedElectricPump",
                400_000L, 1);

        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "machine-storage";
    }

    @Override
    public String getTranslation() {
        return "Storage Config";
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
