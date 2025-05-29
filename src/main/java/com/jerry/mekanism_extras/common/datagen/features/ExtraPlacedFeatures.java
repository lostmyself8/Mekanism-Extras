//package com.jerry.mekanism_extras.common.datagen.features;
//
//import com.jerry.mekanism_extras.MekanismExtras;
//import net.minecraft.core.Holder;
//import net.minecraft.core.HolderGetter;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.data.worldgen.BootstapContext;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.levelgen.VerticalAnchor;
//import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraft.world.level.levelgen.placement.*;
//
//import java.util.List;
//
//public class ExtraPlacedFeatures {
//    public static ResourceKey<PlacedFeature> NAQUADAH_ORE_KEY = createKey("naquadah_ore_placed");
//    public static ResourceKey<PlacedFeature> END_NAQUADAH_ORE_KEY = createKey("end_naquadah_ore_placed");
//
//    public static void bootStrap(BootstapContext<PlacedFeature> context) {
//        HolderGetter<ConfiguredFeature<?, ?>> lookup = context.lookup(Registries.CONFIGURED_FEATURE);
//        register(context, NAQUADAH_ORE_KEY, lookup.getOrThrow(ExtraOreFeatures.NAQUADAH_ORE_KEY),
//                ExtraOrePlacement.commonOrePlacement(4,
//                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-63), VerticalAnchor.belowTop(-60))));
//
//        register(context, END_NAQUADAH_ORE_KEY, lookup.getOrThrow(ExtraOreFeatures.END_NAQUADAH_ORE_KEY),
//                ExtraOrePlacement.commonOrePlacement(8,
//                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(15), VerticalAnchor.belowTop(25))));
//    }
//
//    private static ResourceKey<PlacedFeature> createKey(String name) {
//        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MekanismExtras.MODID, name));
//    }
//
//    private static void register(BootstapContext<PlacedFeature> context,
//                                 ResourceKey<PlacedFeature> key,
//                                 Holder<ConfiguredFeature<?, ?>> configured, List<PlacementModifier> modifiers) {
//        context.register(key, new PlacedFeature(configured, List.copyOf(modifiers)));
//    }
//
//    static class ExtraOrePlacement {
//        private static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier1) {
//            return List.of(modifier, InSquarePlacement.spread(), modifier1, BiomeFilter.biome());
//        }
//        public static List<PlacementModifier> commonOrePlacement(int i, PlacementModifier modifier) {
//            return orePlacement(CountPlacement.of(i), modifier);
//        }
//        public static List<PlacementModifier> rareOrePlacement(int i, PlacementModifier modifier) {
//            return orePlacement(RarityFilter.onAverageOnceEvery(i), modifier);
//        }
//
//    }
//}
