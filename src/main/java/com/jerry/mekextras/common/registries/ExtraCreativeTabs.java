package com.jerry.mekextras.common.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.ExtraLang;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekextras.common.integration.mekmm.registries.ExtraMoreMachineBlocks;

import com.jerry.genextras.common.registries.GenExtraBlocks;
import com.jerry.genextras.common.registries.GenExtraFluids;
import com.jerry.genextras.common.registries.GenExtraItems;

import mekanism.common.registration.MekanismDeferredHolder;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;

import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class ExtraCreativeTabs {

    public static final CreativeTabDeferredRegister EXTRA_CREATIVE_TABS = new CreativeTabDeferredRegister(MekanismExtras.MOD_ID, ExtraCreativeTabs::addToExistingTabs);

    public static final MekanismDeferredHolder<CreativeModeTab, CreativeModeTab> MEK_EXTRAS = EXTRA_CREATIVE_TABS.registerMain(ExtraLang.MEK_EXTRAS, ExtraItems.INFINITE_CONTROL_CIRCUIT, builder -> builder.displayItems((displayParameters, output) -> {
        CreativeTabDeferredRegister.addToDisplay(ExtraItems.EXTRA_ITEMS, output);
        CreativeTabDeferredRegister.addToDisplay(ExtraBlocks.EXTRA_BLOCKS, output);
        CreativeTabDeferredRegister.addToDisplay(ExtraFluids.EXTRA_FLUIDS, output);
        if (MekanismExtras.hooks.mekmm.isLoaded()) {
            CreativeTabDeferredRegister.addToDisplay(ExtraMoreMachineBlocks.MM_BLOCKS, output);
            CreativeTabDeferredRegister.addToDisplay(ExtraAdvancedFactoryBlocks.AF_BLOCKS, output);
        }
        if (MekanismExtras.hooks.mekanismGenerators.isLoaded()) {
            CreativeTabDeferredRegister.addToDisplay(GenExtraItems.GEN_EXTRA_ITEMS, output);
            CreativeTabDeferredRegister.addToDisplay(GenExtraBlocks.GEN_EXTRA_BLOCKS, output);
            CreativeTabDeferredRegister.addToDisplay(GenExtraFluids.GEN_EXTRA_FLUIDS, output);
        }
    }));

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent buildCreativeModeTabContentsEvent) {}

    public static void register(IEventBus eventBus) {
        EXTRA_CREATIVE_TABS.register(eventBus);
    }
}
