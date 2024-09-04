package com.jerry.generator_extras.common.config;

import com.jerry.mekanism_extras.common.config.ExtraConfigHelper;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;

public class GenLoadConfig {
    private GenLoadConfig() {

    }

    public static final ReactorConfig reactorConfig = new ReactorConfig();

    public static void registerConfigs(ModLoadingContext modLoadingContext) {
        ModContainer modContainer = modLoadingContext.getActiveContainer();
        ExtraConfigHelper.registerConfig(modContainer, reactorConfig);
    }
}
