package com.jerry.generator_extras.common.genregistries;

import com.jerry.mekanism_extras.MekanismExtras;

import com.jerry.generator_extras.common.item.ItemNquadahHohlraum;

import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenItem {

    private ExtraGenItem() {}

    public static final ItemDeferredRegister EXTRA_GEN_ITEMS = new ItemDeferredRegister(MekanismExtras.MOD_ID);
    public static final ItemRegistryObject<ItemNquadahHohlraum> HOHLRAUM = EXTRA_GEN_ITEMS.register("naquadah_hohlraum", ItemNquadahHohlraum::new);

    public static void register(IEventBus eventBus) {
        EXTRA_GEN_ITEMS.register(eventBus);
    }
}
