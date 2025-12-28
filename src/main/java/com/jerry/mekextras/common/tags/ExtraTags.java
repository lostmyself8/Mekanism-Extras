package com.jerry.mekextras.common.tags;

import com.jerry.mekextras.MekanismExtras;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ExtraTags {

    public static class Items {

        private Items() {}

        public static final TagKey<Item> NAQUADAH = commonTag("ores/naquadah");
        public static final TagKey<Item> END_NAQUADAH = commonTag("ores/naquadah");

        public static final TagKey<Item> CIRCUITS = commonTag("circuits");
        public static final TagKey<Item> CIRCUITS_ABSOLUTE = commonTag("circuits/absolute");
        public static final TagKey<Item> CIRCUITS_SUPREME = commonTag("circuits/supreme");
        public static final TagKey<Item> CIRCUITS_COSMIC = commonTag("circuits/cosmic");
        public static final TagKey<Item> CIRCUITS_INFINITE = commonTag("circuits/infinite");

        public static final TagKey<Item> ALLOYS = tag("alloys");
        public static final TagKey<Item> ALLOYS_RADIANCE = tag("alloys/radiance");
        public static final TagKey<Item> ALLOYS_THERMONUCLEAR = tag("alloys/thermonuclear");
        public static final TagKey<Item> ALLOYS_SHINING = tag("alloys/shining");
        public static final TagKey<Item> ALLOYS_SPECTRUM = tag("alloys/spectrum");
        // NeoForge alloy tags
        public static final TagKey<Item> COMMON_ALLOYS = commonTag("alloys");
        public static final TagKey<Item> ALLOYS_ABSOLUTE = commonTag("alloys/absolute");
        public static final TagKey<Item> ALLOYS_SUPREME = commonTag("alloys/supreme");
        public static final TagKey<Item> ALLOYS_COSMIC = commonTag("alloys/cosmic");
        public static final TagKey<Item> ALLOYS_INFINITE = commonTag("alloys/infinite");

        public static final TagKey<Item> ENRICHED = tag("enriched");
        public static final TagKey<Item> ENRICHED_OSMIUM = tag("enriched/osmium");
        public static final TagKey<Item> ENRICHED_LEAD = tag("enriched/lead");
        public static final TagKey<Item> ENRICHED_RADIANCE = tag("enriched/radiance");
        public static final TagKey<Item> ENRICHED_THERMONUCLEAR = tag("enriched/thermonuclear");
        public static final TagKey<Item> ENRICHED_SHINING = tag("enriched/shining");
        public static final TagKey<Item> ENRICHED_SPECTRUM = tag("enriched/spectrum");

        private static TagKey<Item> commonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(MekanismExtras.rl(name));
        }
    }
}
