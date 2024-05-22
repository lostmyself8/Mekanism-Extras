package com.jerry.mekanism_extras;

import com.jerry.mekanism_extras.client.events.ClientTick;
import com.jerry.mekanism_extras.config.ExtraConfig;
import com.jerry.mekanism_extras.registery.*;
import com.mojang.logging.LogUtils;
import mekanism.common.config.MekanismConfigHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MekanismExtras.MODID)
public class MekanismExtras {
    public static final String MODID = "mekanism_extras";
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ExtraConfig config = new ExtraConfig();

    public MekanismExtras() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModContainer modContainer = ModLoadingContext.get().getActiveContainer();
        MekanismConfigHelper.registerConfig(modContainer, config);
        ExtraBlock.register(modEventBus);
        ExtraItem.register(modEventBus);
        ExtraTab.register(modEventBus);
        ExtraTileEntityTypes.register(modEventBus);
        ExtraContainerTypes.register(modEventBus);
        ExtraGases.register(modEventBus);
        ExtraInfuseTypes.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new ClientTick());
    }

    public static ExtraConfig getConfig() {
        return config;
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MekanismExtras.MODID, path);
    }
}
