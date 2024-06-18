package com.jerry.mekanism_extras.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.ExtraLang;
import mekanism.common.registration.impl.CreativeTabRegistryObject;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraTab {
    public static void register(IEventBus eventBus) {
        EXTRA_TAB.register(eventBus);
    }

    public static final ExtraCreativeTabDeferredRegister EXTRA_TAB = new ExtraCreativeTabDeferredRegister(MekanismExtras.MODID, ExtraTab::addToExistingTabs);
    public static final CreativeTabRegistryObject MEKANISM_EXTRAS_TAB = EXTRA_TAB.registerMain(ExtraLang.EXTRA_TAB, ExtraItem.INFINITE_CONTROL_CIRCUIT, builder ->
                    builder.displayItems((displayParameters, output) -> {
                        ExtraCreativeTabDeferredRegister.addToDisplay(ExtraItem.EXTRA_ITEM, output);
                        ExtraCreativeTabDeferredRegister.addToDisplay(ExtraBlock.EXTRA_BLOCK, output);
//                        CreativeTabDeferredRegister.addToDisplay(MekanismFluids.FLUIDS, output);
                    })
    );

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent event) {
    }
}
