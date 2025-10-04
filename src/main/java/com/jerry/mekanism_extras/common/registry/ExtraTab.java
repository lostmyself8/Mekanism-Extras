package com.jerry.mekanism_extras.common.registry;

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

public class ExtraTab {
    public static void register(IEventBus eventBus) {
        EXTRA_TAB.register(eventBus);
    }

    public static final CreativeTabDeferredRegister EXTRA_TAB = new CreativeTabDeferredRegister(MekanismExtras.MODID, ExtraTab::addToExistingTabs);
    public static final CreativeTabRegistryObject MEKANISM_EXTRAS_TAB = EXTRA_TAB.registerMain(ExtraLang.EXTRA_TAB, ExtraItem.INFINITE_CONTROL_CIRCUIT, builder ->
                    builder.displayItems((displayParameters, output) -> {
                        CreativeTabDeferredRegister.addToDisplay(ExtraItem.EXTRA_ITEMS, output);
                        CreativeTabDeferredRegister.addToDisplay(ExtraBlock.EXTRA_BLOCK, output);
                        CreativeTabDeferredRegister.addToDisplay(ExtraFluids.EXTRA_FLUIDS, output);
                        if (Addons.MEKANISMGENERATORS.isLoaded()) {
                            CreativeTabDeferredRegister.addToDisplay(ExtraGenItem.EXTRA_GEN_ITEMS, output);
                            CreativeTabDeferredRegister.addToDisplay(ExtraGenBlocks.EXTRA_GEN_BLOCK, output);
                            CreativeTabDeferredRegister.addToDisplay(ExtraGenFluids.EXTRA_GEN_FLUIDS, output);
                        }
                    })
    );

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent event) {
    }
}
