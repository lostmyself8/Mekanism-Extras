package com.jerry.generator_extras.common.recipe;

import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface IExtraGenRecipeTypeProvider<RECIPE extends MekanismRecipe, INPUT_CACHE extends IInputRecipeCache> {

    default ResourceLocation getRegistryName() {
        return getExtraGenRecipeType().getRegistryName();
    }

    ExtraGenRecipeType<RECIPE, INPUT_CACHE> getExtraGenRecipeType();

    default INPUT_CACHE getInputCache() {
        return getExtraGenRecipeType().getInputCache();
    }

    @NotNull
    default List<RECIPE> getRecipes(@Nullable Level world) {
        return getExtraGenRecipeType().getRecipes(world);
    }

    default Stream<RECIPE> stream(@Nullable Level world) {
        return getRecipes(world).stream();
    }

    /**
     * Finds the first recipe that matches the given criteria, or null if no matching recipe is found. Prefer using the find recipe methods in {@link #getInputCache()}.
     */
    @Nullable
    default RECIPE findFirst(@Nullable Level world, Predicate<RECIPE> matchCriteria) {
        return stream(world).filter(matchCriteria).findFirst().orElse(null);
    }

    /**
     * Checks if this recipe type contains a recipe that matches the given criteria. Prefer using the contains recipe methods in {@link #getInputCache()}.
     */
    default boolean contains(@Nullable Level world, Predicate<RECIPE> matchCriteria) {
        return stream(world).anyMatch(matchCriteria);
    }

}
