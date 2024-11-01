//package com.jerry.mekanism_extras.datagen;
//
//import com.jerry.mekanism_extras.MekanismExtras;
//import com.jerry.mekanism_extras.registry.ExtraOreFeatures;
//import com.jerry.mekanism_extras.registry.ExtraPlacedFeatures;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.core.RegistrySetBuilder;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.data.PackOutput;
//import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
//
//import java.util.Set;
//import java.util.concurrent.CompletableFuture;
//
//public class ExtraWorldGenProvider extends DatapackBuiltinEntriesProvider {
//
//    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
//            .add(Registries.CONFIGURED_FEATURE, ExtraOreFeatures::bootStrap)
//            .add(Registries.PLACED_FEATURE, ExtraPlacedFeatures::bootStrap);
//    public ExtraWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
//        super(output, registries, BUILDER, Set.of(MekanismExtras.MODID));
//    }
//}
