package com.jerry.mekextras.common.datagen;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.datagen.features.ExtraOreFeatures;
import com.jerry.mekextras.common.datagen.features.ExtraPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ExtraWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ExtraOreFeatures::bootStrap)
            .add(Registries.PLACED_FEATURE, ExtraPlacedFeatures::bootStrap);
    public ExtraWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MekanismExtras.MOD_ID));
    }
}
