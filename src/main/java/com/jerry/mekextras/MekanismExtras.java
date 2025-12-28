package com.jerry.mekextras;

import com.jerry.genextras.common.content.naquadah.NaquadahReactorCache;
import com.jerry.genextras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.genextras.common.content.naquadah.NaquadahReactorValidator;
import com.jerry.genextras.common.registries.GenExtraFluids;
import com.jerry.mekextras.common.ExtraLang;
import com.jerry.mekextras.common.capabilities.ExtraCapabilities;
import com.jerry.mekextras.common.command.builders.ExtraBuilders;
import com.jerry.mekextras.common.config.ExtraConfig;
import com.jerry.mekextras.common.content.matrix.ReinforcedMatrixMultiblockData;
import com.jerry.mekextras.common.content.matrix.ReinforcedMatrixValidator;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryContainerTypes;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryTileEntityTypes;
import com.jerry.mekextras.common.integration.mekmm.registries.ExtraMoreMachineBlocks;
import com.jerry.mekextras.common.integration.mekmm.registries.ExtraMoreMachineContainerTypes;
import com.jerry.mekextras.common.integration.mekmm.registries.ExtraMoreMachineTileEntityTypes;
import com.jerry.mekextras.common.network.ExtraPacketHandler;
import com.jerry.mekextras.common.registries.*;
import com.jerry.mekextras.common.integration.ExtraHooks;
import com.mojang.logging.LogUtils;
import mekanism.common.base.IModModule;
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
public class MekanismExtras implements IModModule {

    public static final String MOD_ID = "mekanism_extras";
    public static final String MOD_NAME = "MekanismExtras";
    public static final String FOLD_NAME = "Mekanism-Extras";

    private final ExtraPacketHandler extraPacketHandler;

    public static MekanismExtras instance;

    public static ExtraHooks hooks = new ExtraHooks();

    public final Version versionNumber;

    public static final MultiblockManager<ReinforcedMatrixMultiblockData> matrixManager = new MultiblockManager<>("reinforcedInductionMatrix", MultiblockCache::new, ReinforcedMatrixValidator::new);
    public static final MultiblockManager<NaquadahReactorMultiblockData> naquadahReactorManager = new MultiblockManager<>("naquadahReactor", NaquadahReactorCache::new, NaquadahReactorValidator::new);

    public static final Logger LOGGER = LogUtils.getLogger();

    public MekanismExtras(ModContainer modContainer, IEventBus modEventBus) {
        instance = this;
        versionNumber = new Version(modContainer);
        ExtraConfig.registerConfigs(modContainer);
        NeoForge.EVENT_BUS.addListener(this::registerCommands);
        modEventBus.addListener(ExtraCapabilities::registerCapabilities);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(ExtraConfig::onConfigLoad);
        ExtraItems.register(modEventBus);
        ExtraBlocks.register(modEventBus);
        ExtraFluids.register(modEventBus);
        ExtraContainerTypes.register(modEventBus);
        ExtraTileEntityTypes.register(modEventBus);
        ExtraCreativeTabs.register(modEventBus);
        ExtraRecipeSerializersInternal.register(modEventBus);
        ExtraChemicals.register(modEventBus);

        //MoreMachine
        registerMoreMachineFactory(modEventBus);
        registerAdvancedFactory(modEventBus);

        extraPacketHandler = new ExtraPacketHandler(modEventBus, versionNumber);
        hooks.hookConstructor(modContainer, modEventBus);
    }

    public static ExtraPacketHandler extraPacketHandler() {
        return instance.extraPacketHandler;
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ExtraFluids.EXTRA_FLUIDS.registerBucketDispenserBehavior();
            if (hooks.mekanismGenerators.isLoaded()){
                GenExtraFluids.GEN_EXTRA_FLUIDS.registerBucketDispenserBehavior();
            }
        });
    }

    private void registerCommands(RegisterCommandsEvent event) {
        BuildCommand.register("reinforced_matrix", ExtraLang.REINFORCED_MATRIX, new ExtraBuilders.ReinforcedMatrixBuilder());
        if (hooks.mekanismGenerators.isLoaded()){
            BuildCommand.register("naquadah", ExtraLang.NAQUADAH_REACTOR, new ExtraBuilders.NaquadahReactorBuilder());
        }
        event.getDispatcher().register(CommandMek.register());
    }

    private void registerAdvancedFactory(IEventBus modEventBus) {
        if (hooks.mekmm.isLoaded()) {
            ExtraAdvancedFactoryBlocks.AF_BLOCKS.register(modEventBus);
            ExtraAdvancedFactoryContainerTypes.AF_CONTAINER_TYPES.register(modEventBus);
            ExtraAdvancedFactoryTileEntityTypes.AF_TILE_ENTITY_TYPES.register(modEventBus);
        }
    }

    private void registerMoreMachineFactory(IEventBus modEventBus) {
        if (hooks.mekmm.isLoaded()) {
            ExtraMoreMachineBlocks.MM_BLOCKS.register(modEventBus);
            ExtraMoreMachineContainerTypes.MM_CONTAINER_TYPES.register(modEventBus);
            ExtraMoreMachineTileEntityTypes.MM_TILE_ENTITY_TYPES.register(modEventBus);
        }
    }

    @Override
    public Version getVersion() {
        return versionNumber;
    }

    @Override
    public String getName() {
        return "Extras";
    }
}
