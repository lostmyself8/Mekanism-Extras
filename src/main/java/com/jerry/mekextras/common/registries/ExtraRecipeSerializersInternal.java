package com.jerry.mekextras.common.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.recipe.bin.ExtraBinExtractRecipe;
import com.jerry.mekextras.common.recipe.bin.ExtraBinInsertRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExtraRecipeSerializersInternal {

    private ExtraRecipeSerializersInternal() {}

    public static final DeferredRegister<RecipeSerializer<?>> EXTRA_RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MekanismExtras.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ExtraBinInsertRecipe>> BIN_INSERT = EXTRA_RECIPE_SERIALIZERS.register("bin_insert", () -> new SimpleCraftingRecipeSerializer<>(ExtraBinInsertRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ExtraBinExtractRecipe>> BIN_EXTRACT = EXTRA_RECIPE_SERIALIZERS.register("bin_extract", () -> new SimpleCraftingRecipeSerializer<>(ExtraBinExtractRecipe::new));

    public static void register(IEventBus eventBus) {
        EXTRA_RECIPE_SERIALIZERS.register(eventBus);
    }
}
