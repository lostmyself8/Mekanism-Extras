package com.jerry.generator_extras.common.genregistry;

import com.jerry.generator_extras.common.item.ItemNquadahHohlraum;
import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenItem {
    private ExtraGenItem() {
    }
    public static final ItemDeferredRegister EXTRA_GEN_ITEMS = new ItemDeferredRegister(MekanismExtras.MODID);
    public static final ItemRegistryObject<ItemNquadahHohlraum> HOHLRAUM = EXTRA_GEN_ITEMS.register("naquadah_hohlraum", ItemNquadahHohlraum::new);

    public static void register(IEventBus eventBus) {
        EXTRA_GEN_ITEMS.register(eventBus);
    }
}
