package com.jerry.mekanism_extras.common.config;

import com.jerry.mekanism_extras.MekanismExtras;

import mekanism.common.config.IMekanismConfig;

import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ExtraConfigHelper {

    private ExtraConfigHelper() {}

    public static final Path CONFIG_DIR = FMLPaths.getOrCreateGameRelativePath(FMLPaths.CONFIGDIR.get().resolve(MekanismExtras.MOD_NAME));

    /**
     * Creates a mod config so that {@link net.minecraftforge.fml.config.ConfigTracker} will track it and sync server
     * configs from server to client.
     */
    public static void registerConfig(ModContainer modContainer, IMekanismConfig config) {
        ExtraModConfig modConfig = new ExtraModConfig(modContainer, config);
        if (config.addToContainer()) {
            modContainer.addConfig(modConfig);
        }
    }
}
