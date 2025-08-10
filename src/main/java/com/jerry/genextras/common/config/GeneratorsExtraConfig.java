package com.jerry.genextras.common.config;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.config.ExtraConfigHelper;
import mekanism.common.config.IMekanismConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.event.config.ModConfigEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GeneratorsExtraConfig {

    private GeneratorsExtraConfig() {
    }

    private static final Map<IConfigSpec, IMekanismConfig> KNOWN_CONFIGS = new HashMap<>();
    public static final ExtraGeneratorsConfig extraGenerators = new ExtraGeneratorsConfig();

    public static void registerConfigs(ModContainer modContainer) {
        ExtraConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, extraGenerators);
    }

    public static void onConfigLoad(ModConfigEvent configEvent) {
        ExtraConfigHelper.onConfigLoad(configEvent, MekanismExtras.MOD_ID, KNOWN_CONFIGS);
    }

    public static Collection<IMekanismConfig> getConfigs() {
        return Collections.unmodifiableCollection(KNOWN_CONFIGS.values());
    }
}
