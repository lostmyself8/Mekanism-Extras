package com.jerry.generator_extras.common.genregistry;

import com.jerry.generator_extras.common.item.ItemNquadahHohlraum;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.registration.impl.ExtraItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenItem {
    private ExtraGenItem() {
    }
    public static final ExtraItemDeferredRegister EXTRA_GEN_ITEMS = new ExtraItemDeferredRegister(MekanismExtras.MODID);
    public static final ItemRegistryObject<ItemNquadahHohlraum> HOHLRAUM = EXTRA_GEN_ITEMS.register("naquadah_hohlraum", ItemNquadahHohlraum::new);

    public static void register(IEventBus eventBus) {
        EXTRA_GEN_ITEMS.register(eventBus);
    }
}
