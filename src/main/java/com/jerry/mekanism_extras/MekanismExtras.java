package com.jerry.mekanism_extras;

import com.jerry.generator_extras.common.content.reactor.NaquadahReactorCache;
import com.jerry.mekanism_extras.client.events.ClientTick;
import com.jerry.mekanism_extras.common.ExtraTag;
import com.jerry.mekanism_extras.common.command.ExtraBuilders;
import com.jerry.mekanism_extras.common.config.LoadConfig;
import com.jerry.generator_extras.common.content.reactor.NaquadahReactorMultiblockData;
import com.jerry.generator_extras.common.content.reactor.NaquadahReactorValidator;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixValidator;
import com.jerry.mekanism_extras.integration.Addons;
import com.jerry.mekanism_extras.integration.mekgenerators.genregistry.*;
import com.jerry.mekanism_extras.registry.*;
import com.mojang.logging.LogUtils;
import mekanism.common.MekanismLang;
import mekanism.common.command.CommandMek;
import mekanism.common.command.builders.BuildCommand;
import mekanism.common.lib.multiblock.MultiblockCache;
import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MekanismExtras.MODID)
public class MekanismExtras {
    public static final String MODID = "mekanism_extras";
    public static final String MOD_NAME = "MekanismExtras";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final MultiblockManager<ExtraMatrixMultiblockData> extraMatrixManager = new MultiblockManager<>("extraInductionMatrix", MultiblockCache::new, ExtraMatrixValidator::new);
    public static final MultiblockManager<NaquadahReactorMultiblockData> naquadahReactorManager = new MultiblockManager<>("naquadahReactor", NaquadahReactorCache::new, NaquadahReactorValidator::new);

    public MekanismExtras() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        LoadConfig.registerConfigs(ModLoadingContext.get());
        modEventBus.addListener(this::commonSetup);
        ExtraBlock.register(modEventBus);
        ExtraItem.register(modEventBus);
        ExtraTab.register(modEventBus);
        ExtraTileEntityTypes.register(modEventBus);
        ExtraContainerTypes.register(modEventBus);
        ExtraGases.register(modEventBus);
        ExtraInfuseTypes.register(modEventBus);
        ExtraSlurries.register(modEventBus);
        requireRegistry(modEventBus);
        MinecraftForge.EVENT_BUS.register(new ClientTick());
        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MekanismExtras.MODID, path);
    }

    private void registerCommands(RegisterCommandsEvent event) {
        BuildCommand.register("extra_matrix", MekanismLang.MATRIX, new ExtraBuilders.MatrixBuilder());
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            BuildCommand.register("naquadah_reactor", GeneratorsLang.FUSION_REACTOR, new ExtraBuilders.NaquadahReactorBuilder());
        }
        event.getDispatcher().register(CommandMek.register());
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ExtraTag::init);
    }

    private static void requireRegistry(IEventBus modEventBus) {
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            ExtraGenBlock.register(modEventBus);
            ExtraGenContainerTypes.register(modEventBus);
            ExtraGenTileEntityTypes.register(modEventBus);
        }
    }
}
