package com.jerry.mekextras.common.config;

import net.neoforged.fml.ModContainer;

public class ExtraConfig {

    private ExtraConfig() {

    }

    public static final ExtraTierConfig extraTierConfig = new ExtraTierConfig();
    public static final ExtraGeneralConfig extraGeneralConfig = new ExtraGeneralConfig();

    public static void registerConfigs(ModContainer modContainer) {
        ExtraConfigHelper.registerConfig(modContainer, extraTierConfig);
        ExtraConfigHelper.registerConfig(modContainer, extraGeneralConfig);
    }
}
