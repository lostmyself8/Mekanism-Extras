package com.jerry.generator_extras.common.registration.impl;

import com.jerry.generator_extras.common.recipe.ExtraGenRecipeType;
import com.jerry.generator_extras.common.recipe.IExtraGenRecipeTypeProvider;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import mekanism.common.registration.WrappedDeferredRegister;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class ExtraGenRecipeTypeDeferredRegister extends WrappedDeferredRegister<RecipeType<?>> {

    private final List<IExtraGenRecipeTypeProvider<?, ?>> recipeTypes = new ArrayList<>();

    public ExtraGenRecipeTypeDeferredRegister(String modid) {
        super(modid, ForgeRegistries.RECIPE_TYPES);
    }

    public <RECIPE extends MekanismRecipe, INPUT_CACHE extends IInputRecipeCache> ExtraGenRecipeTypeRegistryObject<RECIPE, INPUT_CACHE> register(String name,
                                                                                                                                                 Supplier<? extends ExtraGenRecipeType<RECIPE, INPUT_CACHE>> sup) {
        ExtraGenRecipeTypeRegistryObject<RECIPE, INPUT_CACHE> registeredRecipeType = register(name, sup, ExtraGenRecipeTypeRegistryObject::new);
        recipeTypes.add(registeredRecipeType);
        return registeredRecipeType;
    }

    public List<IExtraGenRecipeTypeProvider<?, ?>> getAllRecipeTypes() {
        return Collections.unmodifiableList(recipeTypes);
    }
}
