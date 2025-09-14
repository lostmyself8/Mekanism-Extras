package com.jerry.generator_extras.common.registration.impl;

import com.jerry.generator_extras.common.recipe.ExtraGenRecipeType;
import com.jerry.generator_extras.common.recipe.IExtraGenRecipeTypeProvider;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import mekanism.common.registration.WrappedRegistryObject;
import net.minecraftforge.registries.RegistryObject;

public class ExtraGenRecipeTypeRegistryObject<RECIPE extends MekanismRecipe, INPUT_CACHE extends IInputRecipeCache>
        extends WrappedRegistryObject<ExtraGenRecipeType<RECIPE, INPUT_CACHE>>
        implements IExtraGenRecipeTypeProvider<RECIPE, INPUT_CACHE> {

    public ExtraGenRecipeTypeRegistryObject(RegistryObject<ExtraGenRecipeType<RECIPE, INPUT_CACHE>> registryObject) {
        super(registryObject);
    }

    @Override
    public ExtraGenRecipeType<RECIPE, INPUT_CACHE> getExtraGenRecipeType() {
        return get();
    }
}
