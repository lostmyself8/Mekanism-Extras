package com.jerry.mekanism_extras.common.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.config.IMekanismConfig;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.config.ConfigFileTypeHandler;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.function.Function;

public class ExtraModConfig extends ModConfig {

    private static final ExtraConfigFileTypeHandler EXTRA_TOML = new ExtraConfigFileTypeHandler();

    private final IMekanismConfig mekanismExtraConfig;

    public ExtraModConfig(ModContainer container, IMekanismConfig config) {
        super(config.getConfigType(), config.getConfigSpec(), container, MekanismExtras.MOD_NAME + "/" + config.getFileName() + ".toml");
        this.mekanismExtraConfig = config;
    }

    @Override
    public ConfigFileTypeHandler getHandler() {
        return EXTRA_TOML;
    }

    public void clearCache(ModConfigEvent event) {
        mekanismExtraConfig.clearCache(event instanceof ModConfigEvent.Unloading);
    }

    private static class ExtraConfigFileTypeHandler extends ConfigFileTypeHandler {

        private static Path getPath(Path configBasePath) {
            //Intercept server config path reading for Mekanism configs and reroute it to the normal config directory
            if (configBasePath.endsWith("serverconfig")) {
                return FMLPaths.CONFIGDIR.get();
            }
            return configBasePath;
        }

        @Override
        public Function<ModConfig, CommentedFileConfig> reader(Path configBasePath) {
            return super.reader(getPath(configBasePath));
        }

        @Override
        public void unload(Path configBasePath, ModConfig config) {
            super.unload(getPath(configBasePath), config);
        }
    }
}
