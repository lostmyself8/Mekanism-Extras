package com.jerry.mekextras.common.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ExtraTags {

    public static class Items {

        private static void init() {
        }

        private Items() {
        }

        public static final TagKey<Item> NAQUADAH = commonTag("ores/naquadah");
        public static final TagKey<Item> END_NAQUADAH = commonTag("ores/naquadah");

        private static TagKey<Item> commonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
}
