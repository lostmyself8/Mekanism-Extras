package com.jerry.generator_extras.common.config;

import com.jerry.mekanism_extras.common.config.ExtraConfigHelper;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;

public class GenLoadConfig {
    private GenLoadConfig() {

    }

    public static final GeneratorConfig generatorConfig = new GeneratorConfig();

    public static void registerConfigs(ModLoadingContext modLoadingContext) {
        ModContainer modContainer = modLoadingContext.getActiveContainer();
        ExtraConfigHelper.registerConfig(modContainer, generatorConfig);
    }
}
