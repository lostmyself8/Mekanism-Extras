package com.jerry.mekanism_extras;

import com.jerry.generator_extras.common.ExtraGenLang;
import com.jerry.generator_extras.common.content.naquadah.NaquadahReactorCache;
import com.jerry.generator_extras.common.content.plasma.PlasmaEvaporationMultiblockData;
import com.jerry.generator_extras.common.content.plasma.PlasmaEvaporationValidator;
import com.jerry.generator_extras.common.genregistry.*;
import com.jerry.generator_extras.common.recipe.ExtraGenRecipeType;
import com.jerry.mekanism_extras.client.events.ClientTick;
import com.jerry.mekanism_extras.common.ExtraLang;
import com.jerry.mekanism_extras.common.ExtraTag;
import com.jerry.mekanism_extras.common.command.ExtraBuilders;
import com.jerry.mekanism_extras.common.config.LoadConfig;
import com.jerry.generator_extras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.generator_extras.common.content.naquadah.NaquadahReactorValidator;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixValidator;
import com.jerry.mekanism_extras.common.registry.*;
import com.jerry.mekanism_extras.common.integration.Addons;
import com.mojang.logging.LogUtils;
import mekanism.common.command.CommandMek;
import mekanism.common.command.builders.BuildCommand;
import mekanism.common.lib.multiblock.MultiblockCache;
import mekanism.common.lib.multiblock.MultiblockManager;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.nio.file.Path;

@Mod(MekanismExtras.MODID)
public class MekanismExtras {
    public static final String MODID = "mekanism_extras";
    public static final String MOD_NAME = "MekanismExtras";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final MultiblockManager<ExtraMatrixMultiblockData> extraMatrixManager = new MultiblockManager<>("extraInductionMatrix", MultiblockCache::new, ExtraMatrixValidator::new);
    public static final MultiblockManager<NaquadahReactorMultiblockData> naquadahReactorManager = new MultiblockManager<>("naquadahReactor", NaquadahReactorCache::new, NaquadahReactorValidator::new);
    public static final MultiblockManager<PlasmaEvaporationMultiblockData> plasmaEvaporationPlantManager = new MultiblockManager<>("plasmaEvaporationPlant", MultiblockCache::new, PlasmaEvaporationValidator::new);

    public MekanismExtras(FMLJavaModLoadingContext context) {
        IEventBus modEventBus =context.getModEventBus();
        LoadConfig.registerConfigs(context);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::setupBuiltinPack);
        ExtraBlock.register(modEventBus);
        ExtraItem.register(modEventBus);
        ExtraFluids.register(modEventBus);
        ExtraTab.register(modEventBus);
        ExtraTileEntityTypes.register(modEventBus);
        ExtraContainerTypes.register(modEventBus);
        ExtraGases.register(modEventBus);
        ExtraInfuseTypes.register(modEventBus);
        ExtraSlurries.register(modEventBus);
        conditionalRegistry(modEventBus);
        MinecraftForge.EVENT_BUS.register(new ClientTick());
        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MekanismExtras.MODID, path);
    }

    private void registerCommands(RegisterCommandsEvent event) {
        BuildCommand.register("reinforced_matrix", ExtraLang.REINFORCED_MATRIX, new ExtraBuilders.MatrixBuilder());
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            BuildCommand.register("naquadah", ExtraGenLang.NAQUADAH_REACTOR, new ExtraBuilders.NaquadahReactorBuilder());
        }
        event.getDispatcher().register(CommandMek.register());
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ExtraTag::init);
    }

    private static void conditionalRegistry(IEventBus modEventBus) {
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            ExtraGenItem.register(modEventBus);
            ExtraGenBlocks.register(modEventBus);
            ExtraGenFluids.register(modEventBus);
            ExtraGenContainerTypes.register(modEventBus);
            ExtraGenGases.register(modEventBus);
            ExtraGenTileEntityTypes.register(modEventBus);
            ExtraGenRecipeType.EXTRA_GEN_RECIPE_TYPES.register(modEventBus);
            ExtraGenRecipeSerializers.GEN_RECIPE_SERIALIZERS.register(modEventBus);
        }
    }

    private void setupBuiltinPack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {

            Path resourcePath = ModList.get().getModFileById(MekanismExtras.MODID).getFile().findResource("stop_flashing");

            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(MekanismExtras.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath, true);

            PackMetadataSection metadata = new PackMetadataSection(Component.translatable(ExtraLang.STOP_FLASHING_DESC.getTranslationKey()),
                    SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));

            event.addRepositorySource(source ->
                    source.accept(Pack.create(
                            "builtin/stop_flashing",
                            Component.translatable(ExtraLang.STOP_FLASHING.getTranslationKey()),
                            false,
                            string -> pack,
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES),
                                    FeatureFlagSet.of(), pack.isHidden()),
                            PackType.CLIENT_RESOURCES,
                            Pack.Position.TOP,
                            false,
                            PackSource.BUILT_IN)
                    )
            );
        }
    }
}
