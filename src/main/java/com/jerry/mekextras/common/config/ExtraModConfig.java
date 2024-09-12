package com.jerry.mekextras.common.config;

import com.jerry.mekextras.MekanismExtras;
import mekanism.common.config.IMekanismConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;

public class ExtraModConfig extends ModConfig {
    private final IMekanismConfig extraConfig;

    public ExtraModConfig(ModContainer container, IMekanismConfig config) {
        super(config.getConfigType(), config.getConfigSpec(), container, MekanismExtras.MOD_NAME + "/" + config.getFileName() + ".toml");
        this.extraConfig = config;
    }

    public void clearCache(ModConfigEvent event) {
        extraConfig.clearCache(event instanceof ModConfigEvent.Unloading);
    }
}
