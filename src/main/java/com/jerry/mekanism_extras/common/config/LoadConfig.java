package com.jerry.mekanism_extras.common.config;

import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;

public class LoadConfig {
    private LoadConfig() {

    }
    public static final ExtraConfig extraConfig = new ExtraConfig();
    public static final ExtraWorldConfig worldConfig = new ExtraWorldConfig();

    public static void registerConfigs(ModLoadingContext modLoadingContext) {
        ModContainer modContainer = modLoadingContext.getActiveContainer();
        ExtraConfigHelper.registerConfig(modContainer, extraConfig);
        ExtraConfigHelper.registerConfig(modContainer, worldConfig);
    }
}
