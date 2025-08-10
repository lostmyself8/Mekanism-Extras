package com.jerry.genextras.common;

import com.jerry.mekextras.MekanismExtras;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.Chemical;
import net.minecraft.tags.TagKey;

public class GeneratorExtraTags {

    private GeneratorExtraTags() {
    }

    public static class Chemicals {

        private Chemicals() {
        }

        public static final TagKey<Chemical> RICH_NAQUADAH_FUEL = tag("rich_naquadah_fuel");
        public static final TagKey<Chemical> RICH_URANIUM_FUEL = tag("rich_uranium_fuel");
        public static final TagKey<Chemical> NAQUADAH_URANIUM_FUEL = tag("naquadah_uranium_fuel");

        private static TagKey<Chemical> tag(String name) {
            return TagKey.create(MekanismAPI.CHEMICAL_REGISTRY_NAME, MekanismExtras.rl(name));
        }
    }
}
