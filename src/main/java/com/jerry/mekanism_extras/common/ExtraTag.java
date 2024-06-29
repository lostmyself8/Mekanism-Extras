package com.jerry.mekanism_extras.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ExtraTag {

    public static void init() {
        Items.init();
    }

    public static class Items {

        private static void init() {
        }

        private Items() {
        }

        public static final TagKey<Item> NAQUADAH = forgeTag("ores/naquadah");
        public static final TagKey<Item> END_NAQUADAH = forgeTag("ores/naquadah");

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

    }
}
