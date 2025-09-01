package com.jerry.mekextras.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.MekanismConfigTranslations;
import mekanism.common.config.value.CachedIntValue;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.fluids.FluidType;

public class ExtraGeneralConfig extends BaseMekanismConfig {

    private final ModConfigSpec configSpec;
    //Pump
    public final CachedIntValue pumpHeavyWaterAmount;

    ExtraGeneralConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        MekanismConfigTranslations.GENERAL_PUMP.applyToBuilder(builder).push("pump");
        pumpHeavyWaterAmount = CachedIntValue.wrap(this, ExtraConfigTranslations.GENERAL_ADVANCED_PUMP_HEAVY_WATER.applyToBuilder(builder)
                .defineInRange("heavyWaterAmount", FluidType.BUCKET_VOLUME / 10, 1, FluidType.BUCKET_VOLUME));
        builder.pop();

        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "general";
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
