package com.jerry.mekextras;

import com.jerry.mekextras.common.capabilities.ExtraCapabilities;
import com.jerry.mekextras.common.command.builders.ExtraBuilders;
import com.jerry.mekextras.common.config.ExtraConfig;
import com.jerry.mekextras.common.content.matrix.ReinforcedMatrixMultiblockData;
import com.jerry.mekextras.common.content.matrix.ReinforcedMatrixValidator;
import com.jerry.mekextras.common.network.ExtraPacketHandler;
import com.jerry.mekextras.common.registry.*;
import com.mojang.logging.LogUtils;
import mekanism.common.MekanismLang;
import mekanism.common.command.CommandMek;
import mekanism.common.command.builders.BuildCommand;
import mekanism.common.lib.Version;
import mekanism.common.lib.multiblock.MultiblockCache;
import mekanism.common.lib.multiblock.MultiblockManager;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;

@Mod(MekanismExtras.MOD_ID)
public class MekanismExtras {
    public static final String MOD_ID = "mekanism_extras";
    public static final String MOD_NAME = "Mekanism-Extras";
    private final ExtraPacketHandler extraPacketHandler;
    public static MekanismExtras instance;
    public final Version versionNumber;
    public static final MultiblockManager<ReinforcedMatrixMultiblockData> matrixManager = new MultiblockManager<>("inductionMatrix", MultiblockCache::new, ReinforcedMatrixValidator::new);
    private static final Logger LOGGER = LogUtils.getLogger();
    public MekanismExtras(ModContainer modContainer, IEventBus modEventBus) {
        instance = this;
        ExtraConfig.registerConfigs(modContainer);
        NeoForge.EVENT_BUS.addListener(this::registerCommands);
        modEventBus.addListener(ExtraCapabilities::registerCapabilities);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(ExtraConfig::onConfigLoad);
        ExtraItems.register(modEventBus);
        ExtraBlocks.register(modEventBus);
        ExtraDataComponents.register(modEventBus);
        ExtraContainerTypes.register(modEventBus);
        ExtraTileEntityTypes.register(modEventBus);
        ExtraCreativeTabs.register(modEventBus);
        ExtraGases.register(modEventBus);
        ExtraInfuseTypes.register(modEventBus);
        versionNumber = new Version(modContainer);
        extraPacketHandler = new ExtraPacketHandler(modEventBus, versionNumber);
    }

    public static ExtraPacketHandler extraPacketHandler() {
        return instance.extraPacketHandler;
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void registerCommands(RegisterCommandsEvent event) {
        BuildCommand.register("reinforced_matrix", MekanismLang.MATRIX, new ExtraBuilders.ReinforcedMatrixBuilder());
        event.getDispatcher().register(CommandMek.register());
    }

}
