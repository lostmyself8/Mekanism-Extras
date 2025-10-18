package com.jerry.mekextras.common.integration;

import com.jerry.genextras.common.config.GeneratorsExtraConfig;
import com.jerry.genextras.common.registries.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ExtraHooks {

    public record IntegrationInfo(String modid, boolean isLoaded) {

        private IntegrationInfo(String modid, Predicate<String> loadedCheck) {
            this(modid, loadedCheck.test(modid));
        }

        private void sendImc(String method, Supplier<?> toSend) {
            InterModComms.sendTo(modid, method, toSend);
        }

        public ResourceLocation rl(String path) {
            return ResourceLocation.fromNamespaceAndPath(modid, path);
        }

        public void assertLoaded() {
            if (!isLoaded) {
                throw new IllegalStateException(modid + " is not loaded");
            }
        }
    }

    public final IntegrationInfo mekanismGenerators;
    public final IntegrationInfo evolvedMekanism;

    public ExtraHooks() {
        ModList modList = ModList.get();
        //Note: The modlist is null when running tests
        Predicate<String> loadedCheck = modList == null ? modid -> false : modList::isLoaded;
        mekanismGenerators = new IntegrationInfo("mekanismgenerators", loadedCheck);
        evolvedMekanism = new IntegrationInfo("evolvedmekanism", loadedCheck);
    }

    public void hookConstructor(final ModContainer modContainer, final IEventBus modEventBus) {
        if (mekanismGenerators.isLoaded()) {
            //加载配置
            GeneratorsExtraConfig.registerConfigs(modContainer);
            //注册mod资源
            GenExtraItems.GEN_EXTRA_ITEMS.register(modEventBus);
            GenExtraBlocks.GEN_EXTRA_BLOCKS.register(modEventBus);
            GenExtraFluids.GEN_EXTRA_FLUIDS.register(modEventBus);
            GenExtraDataComponents.GEN_EXTRA_DATA_COMPONENTS.register(modEventBus);
            GenExtraContainerTypes.GEN_EXTRA_CONTAINER_TYPE.register(modEventBus);
            GenExtraTileEntityTypes.GEN_EXTRA_TILE_ENTITY_TYPES.register(modEventBus);
            GenExtraChemicals.EXTRA_GEN_CHEMICALS.register(modEventBus);
        }
    }

}
