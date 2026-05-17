package com.jerry.mekanism_extras.common.registries;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.ExtraLang;
import com.jerry.mekanism_extras.common.integration.Addons;

import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;
import com.jerry.generator_extras.common.genregistry.ExtraGenFluids;
import com.jerry.generator_extras.common.genregistry.ExtraGenItem;

import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.registration.impl.CreativeTabRegistryObject;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraCreativeTab {

    public static void register(IEventBus eventBus) {
        EXTRA_TAB.register(eventBus);
    }

    public static final CreativeTabDeferredRegister EXTRA_TAB = new CreativeTabDeferredRegister(MekanismExtras.MOD_ID, ExtraCreativeTab::addToExistingTabs);
    public static final CreativeTabRegistryObject MEKANISM_EXTRAS_TAB = EXTRA_TAB.registerMain(ExtraLang.EXTRA_TAB, ExtraItems.INFINITE_CONTROL_CIRCUIT, builder -> builder.displayItems((displayParameters, output) -> {
        CreativeTabDeferredRegister.addToDisplay(ExtraItems.EXTRA_ITEMS, output);
        CreativeTabDeferredRegister.addToDisplay(ExtraBlocks.EXTRA_BLOCKS, output);
        CreativeTabDeferredRegister.addToDisplay(ExtraFluids.EXTRA_FLUIDS, output);
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            CreativeTabDeferredRegister.addToDisplay(ExtraGenItem.EXTRA_GEN_ITEMS, output);
            CreativeTabDeferredRegister.addToDisplay(ExtraGenBlocks.EXTRA_GEN_BLOCK, output);
            CreativeTabDeferredRegister.addToDisplay(ExtraGenFluids.EXTRA_GEN_FLUIDS, output);
        }
    }));

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent event) {}
}
