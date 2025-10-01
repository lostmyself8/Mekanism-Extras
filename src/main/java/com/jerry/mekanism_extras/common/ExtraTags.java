package com.jerry.mekanism_extras.common;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.resource.ExtraBlockResourceInfo;
import com.jerry.mekanism_extras.common.resource.ExtraResource;
import com.jerry.mekanism_extras.common.resource.ore.ExtraOreType;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import mekanism.api.chemical.ChemicalTags;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.infuse.InfuseType;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.common.resource.IResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.tags.LazyTagLookup;
import mekanism.common.util.EnumUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ExtraTags {

    public static void init() {
        Items.init();
        Fluids.init();
        Gases.init();
        InfuseTypes.init();
        Slurries.init();
    }

    public static class Items {

        private static void init() {
        }

        private Items() {
        }

        public static final Table<ResourceType, ExtraResource, TagKey<Item>> PROCESSED_RESOURCES = HashBasedTable.create();
        public static final Map<IResource, TagKey<Item>> PROCESSED_RESOURCE_BLOCKS = new HashMap<>();
        public static final Map<ExtraOreType, TagKey<Item>> ORES = new EnumMap<>(ExtraOreType.class);

        static {
            for (ExtraResource resource : ExtraEnumUtils.EXTRA_RESOURCES) {
                for (ResourceType type : EnumUtils.RESOURCE_TYPES) {
                    String res = type.getBaseTagPath() + "/" + resource.getRegistrySuffix();
                    if (type.isVanilla() || type == ResourceType.DUST) {
                        PROCESSED_RESOURCES.put(type, resource, forgeTag(res));
                    } else {
                        PROCESSED_RESOURCES.put(type, resource, tag(res));
                    }
                }
                if (!resource.isVanilla()) {
                    PROCESSED_RESOURCE_BLOCKS.put(resource, forgeTag("storage_blocks/" + resource.getRegistrySuffix()));
                    ExtraBlockResourceInfo rawResource = resource.getRawResourceBlockInfo();
                    if (rawResource != null) {
                        PROCESSED_RESOURCE_BLOCKS.put(rawResource, forgeTag("storage_blocks/" + rawResource.getRegistrySuffix()));
                    }
                }
            }
            for (ExtraOreType ore : ExtraEnumUtils.EXTRA_ORE_TYPES) {
                ORES.put(ore, forgeTag("ores/" + ore.getResource().getRegistrySuffix()));
            }
        }

        public static final TagKey<Item> NAQUADAH = forgeTag("ores/naquadah");
        public static final TagKey<Item> END_NAQUADAH = forgeTag("ores/naquadah");
        public static final TagKey<Item> TUNGSTEN = forgeTag("ores/tungsten");

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
        }

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(MekanismExtras.rl(name));
        }
    }

    public static class Fluids {
        private static void init() {
        }

        private Fluids() {
        }

        public static final TagKey<Fluid> NAQUADAH_TETRAFLUORIDE = forgeTag("naquadah_tetrafluoride");
        public static final TagKey<Fluid> FLUORINATED_NAQUADAH_URANIUM_FUEL = forgeTag("fluorinated_naquadah_uranium_fuel");
        public static final TagKey<Fluid> RICH_NAQUADAH_FUEL = forgeTag("rich_naquadah_fuel");
        public static final TagKey<Fluid> RICH_URANIUM_FUEL = forgeTag("rich_uranium_fuel");

        public static final TagKey<Fluid> POLONIUM_CONTAINING_SOLUTION = forgeTag("polonium_containing_solution");
        public static final LazyTagLookup<Fluid> LAZY_POLONIUM_CONTAINING_SOLUTION = LazyTagLookup.create(ForgeRegistries.FLUIDS, POLONIUM_CONTAINING_SOLUTION);

        private static TagKey<Fluid> forgeTag(String name) {
            return FluidTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
        }

        private static TagKey<Fluid> tag(String name) {
            return FluidTags.create(MekanismExtras.rl(name));
        }
    }

    public static class Gases {

        private static void init() {
        }

        private Gases() {
        }

        public static final TagKey<Gas> MOLTEN_THERMONUCLEAR = tag("molten_thermonuclear");
        public static final TagKey<Gas> NAQUADAH_TETRAFLUORIDE = tag("naquadah_tetrafluoride");
        public static final TagKey<Gas> FLUORINATED_NAQUADAH_URANIUM_FUEL = tag("fluorinated_naquadah_uranium_fuel");
        public static final TagKey<Gas> RICH_NAQUADAH_FUEL = tag("rich_naquadah_fuel");
        public static final LazyTagLookup<Gas> RICH_NAQUADAH_FUEL_LOOKUP = LazyTagLookup.create(ChemicalTags.GAS, RICH_NAQUADAH_FUEL);
        public static final TagKey<Gas> RICH_URANIUM_FUEL = tag("rich_uranium_fuel");
        public static final LazyTagLookup<Gas> RICH_URANIUM_FUEL_LOOKUP = LazyTagLookup.create(ChemicalTags.GAS, RICH_URANIUM_FUEL);
        public static final TagKey<Gas> NAQUADAH_URANIUM_FUEL = tag("naquadah_uranium_fuel");
        public static final LazyTagLookup<Gas> NAQUADAH_URANIUM_FUEL_LOOKUP = LazyTagLookup.create(ChemicalTags.GAS, NAQUADAH_URANIUM_FUEL);

        private static TagKey<Gas> tag(String name) {
            return ChemicalTags.GAS.tag(MekanismExtras.rl(name));
        }
    }

    public static class InfuseTypes {

        private static void init() {
        }

        private InfuseTypes() {
        }

        public static final TagKey<InfuseType> RADIANCE = tag("radiance");
        public static final TagKey<InfuseType> THERMONUCLEAR = tag("thermonuclear");
        public static final TagKey<InfuseType> SHINING = tag("shining");
        public static final TagKey<InfuseType> SPECTRUM = tag("spectrum");

        private static TagKey<InfuseType> tag(String name) {
            return ChemicalTags.INFUSE_TYPE.tag(MekanismExtras.rl(name));
        }
    }

    public static class Slurries {

        private static void init() {
        }

        private Slurries() {
        }

        public static final TagKey<Slurry> DIRTY = tag("dirty");
        public static final LazyTagLookup<Slurry> DIRTY_LOOKUP = LazyTagLookup.create(ChemicalTags.SLURRY, DIRTY);
        public static final TagKey<Slurry> CLEAN = tag("clean");

        private static TagKey<Slurry> tag(String name) {
            return ChemicalTags.SLURRY.tag(MekanismExtras.rl(name));
        }
    }
}
