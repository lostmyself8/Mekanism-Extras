package com.jerry.generator_extras.common.genregistry;

import com.jerry.mekanism_extras.MekanismExtras;

import com.jerry.generator_extras.common.item.ItemNquadahHohlraum;

import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenItem {

    private ExtraGenItem() {}

    public static final ItemDeferredRegister EXTRA_GEN_ITEMS = new ItemDeferredRegister(MekanismExtras.MODID);
    public static final ItemRegistryObject<ItemNquadahHohlraum> HOHLRAUM = EXTRA_GEN_ITEMS.register("naquadah_hohlraum", ItemNquadahHohlraum::new);
    public static final ItemRegistryObject<Item> HEAT_INSULATING_MATERIAL = EXTRA_GEN_ITEMS.register("heat_insulating_material");
    public static final ItemRegistryObject<Item> HEAT_INSULATING_MATERIAL_PIECE = EXTRA_GEN_ITEMS.register("heat_insulating_material_piece");
    public static final ItemRegistryObject<Item> ENRICHED_HEAT_INSULATING_MATERIAL = EXTRA_GEN_ITEMS.register("enriched_heat_insulating_material");

    public static void register(IEventBus eventBus) {
        EXTRA_GEN_ITEMS.register(eventBus);
    }
}
