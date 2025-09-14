package com.jerry.generator_extras.common.recipe;

import com.jerry.generator_extras.common.recipe.lookup.cache.ExtraGenInputRecipeCache.SingleFluid;
import com.jerry.generator_extras.common.registration.impl.ExtraGenRecipeTypeDeferredRegister;
import com.jerry.generator_extras.common.registration.impl.ExtraGenRecipeTypeRegistryObject;
import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.recipes.FluidToFluidRecipe;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.client.MekanismClient;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ExtraGenRecipeType<RECIPE extends MekanismRecipe, INPUT_CACHE extends IInputRecipeCache>
        implements RecipeType<RECIPE>, IExtraGenRecipeTypeProvider<RECIPE, INPUT_CACHE> {

    public static final ExtraGenRecipeTypeDeferredRegister EXTRA_GEN_RECIPE_TYPES
            = new ExtraGenRecipeTypeDeferredRegister(MekanismExtras.MODID);

    public static final ExtraGenRecipeTypeRegistryObject<FluidToFluidRecipe, SingleFluid<FluidToFluidRecipe>> PLASMA_EVAPORATION
            = register("plasma_evaporating", recipeType -> new SingleFluid<>(recipeType, FluidToFluidRecipe::getInput));

    private static <RECIPE extends MekanismRecipe, INPUT_CACHE extends IInputRecipeCache> ExtraGenRecipeTypeRegistryObject<RECIPE, INPUT_CACHE> register(String name,
                                                                                                                                                 Function<ExtraGenRecipeType<RECIPE, INPUT_CACHE>, INPUT_CACHE> inputCacheCreator) {
        return EXTRA_GEN_RECIPE_TYPES.register(name, () -> new ExtraGenRecipeType<>(name, inputCacheCreator));
    }

    private List<RECIPE> cachedRecipes = Collections.emptyList();
    private final ResourceLocation registryName;
    private final INPUT_CACHE inputCache;

    public static void clearCache() {
        for (IExtraGenRecipeTypeProvider<?, ?> recipeTypeProvider : EXTRA_GEN_RECIPE_TYPES.getAllRecipeTypes()) {
            recipeTypeProvider.getExtraGenRecipeType().clearCaches();
        }
    }

    private ExtraGenRecipeType(String name, Function<ExtraGenRecipeType<RECIPE, INPUT_CACHE>, INPUT_CACHE> inputCacheCreator) {
        this.registryName = MekanismExtras.rl(name);
        this.inputCache = inputCacheCreator.apply(this);
    }

    @Override
    public String toString() {
        return registryName.toString();
    }

    @Override
    public ResourceLocation getRegistryName() {
        return registryName;
    }

    @Override
    public ExtraGenRecipeType<RECIPE, INPUT_CACHE> getExtraGenRecipeType() {
        return this;
    }

    private void clearCaches() {
        cachedRecipes = Collections.emptyList();
        inputCache.clear();
    }

    @Override
    public INPUT_CACHE getInputCache() {
        return inputCache;
    }

    @NotNull
    @Override
    public List<RECIPE> getRecipes(@Nullable Level world) {
        if (world == null) {
            if (FMLEnvironment.dist.isClient()) {
                world = MekanismClient.tryGetClientWorld();
            } else {
                world = ServerLifecycleHooks.getCurrentServer().overworld();
            }
            if (world == null) {
                return Collections.emptyList();
            }
        }
        if (cachedRecipes.isEmpty()) {
            RecipeManager recipeManager = world.getRecipeManager();
            List<RECIPE> recipes = recipeManager.getAllRecipesFor(this);
            cachedRecipes = recipes.stream()
                    .filter(recipe -> !recipe.isIncomplete())
                    .toList();
        }
        return cachedRecipes;
    }

    /**
     * Helper for getting a recipe from a world's recipe manager.
     */
    public static <C extends Container, RECIPE_TYPE extends Recipe<C>> Optional<RECIPE_TYPE> getRecipeFor(RecipeType<RECIPE_TYPE> recipeType, C inventory, Level level) {
        return level.getRecipeManager().getRecipeFor(recipeType, inventory, level)
                .filter(recipe -> recipe.isSpecial() || !recipe.isIncomplete());
    }

    /**
     * Helper for getting a recipe from a world's recipe manager.
     */
    public static Optional<? extends Recipe<?>> byKey(Level level, ResourceLocation id) {
        return level.getRecipeManager().byKey(id)
                .filter(recipe -> recipe.isSpecial() || !recipe.isIncomplete());
    }
}
