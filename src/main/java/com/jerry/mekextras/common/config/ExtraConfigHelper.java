package com.jerry.mekextras.common.config;

import com.jerry.mekextras.MekanismExtras;
import mekanism.common.config.IMekanismConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ConfigTracker;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ExtraConfigHelper {

    private ExtraConfigHelper() {

    }

    public static final Path CONFIG_DIR = FMLPaths.getOrCreateGameRelativePath(FMLPaths.CONFIGDIR.get().resolve(MekanismExtras.MOD_NAME));

    /**
     * Creates a mod config so that {@link ConfigTracker} will track it and sync server configs from server to client.
     */
    public static void registerConfig(ModContainer modContainer, IMekanismConfig config) {
        ExtraModConfig modConfig = new ExtraModConfig(modContainer, config);
        if (config.addToContainer()) {
            modContainer.addConfig(modConfig);
        }
    }
}
