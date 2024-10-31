package com.jerry.mekextra.common.registry;

import com.jerry.mekextra.MekanismExtras;
import com.jerry.mekextra.common.ExtraLang;
import mekanism.common.registration.MekanismDeferredHolder;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class ExtraCreativeTabs {
    public static final CreativeTabDeferredRegister EXTRA_CREATIVE_TABS = new CreativeTabDeferredRegister(MekanismExtras.MOD_ID, ExtraCreativeTabs::addToExistingTabs);

    public static final MekanismDeferredHolder<CreativeModeTab, CreativeModeTab> MEK_EXTRAS = EXTRA_CREATIVE_TABS.registerMain(ExtraLang.MEK_EXTRAS, ExtraItems.INFINITE_CONTROL_CIRCUIT, builder ->
                    builder.displayItems((displayParameters, output) -> {
                                CreativeTabDeferredRegister.addToDisplay(ExtraItems.EXTRA_ITEMS, output);
                                CreativeTabDeferredRegister.addToDisplay(ExtraBlocks.EXTRA_BLOCKS, output);
                            })
    );

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent buildCreativeModeTabContentsEvent) {
    }

    public static void register(IEventBus eventBus) {
        EXTRA_CREATIVE_TABS.register(eventBus);
    }
}
