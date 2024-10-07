package com.jerry.mekanism_extras.common.config;

import com.jerry.mekanism_extras.integration.Addons;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;

import static com.jerry.generator_extras.common.config.GenLoadConfig.generatorConfig;

public class LoadConfig {
    private LoadConfig() {

    }

    public static final ExtraStorageConfig extraStorage = new ExtraStorageConfig();
    public static final ExtraConfig extraConfig = new ExtraConfig();
    public static final ExtraUsageConfig extraUsage = new ExtraUsageConfig();
    public static final ExtraWorldConfig worldConfig = new ExtraWorldConfig();

    public static void registerConfigs(ModLoadingContext modLoadingContext) {
        ModContainer modContainer = modLoadingContext.getActiveContainer();
        ExtraConfigHelper.registerConfig(modContainer, extraStorage);
        ExtraConfigHelper.registerConfig(modContainer, extraConfig);
        ExtraConfigHelper.registerConfig(modContainer, extraUsage);
        ExtraConfigHelper.registerConfig(modContainer, worldConfig);
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            ExtraConfigHelper.registerConfig(modContainer, generatorConfig);
        }
    }
}
