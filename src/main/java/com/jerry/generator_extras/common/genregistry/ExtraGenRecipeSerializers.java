package com.jerry.generator_extras.common.genregistry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.api.recipes.PlasmaEvaporationRecipe;
import com.jerry.mekanism_extras.api.recipes.impl.PlasmaEvaporationIRecipe;
import mekanism.common.registration.impl.RecipeSerializerDeferredRegister;
import mekanism.common.registration.impl.RecipeSerializerRegistryObject;

public class ExtraGenRecipeSerializers {

    public static final RecipeSerializerDeferredRegister GEN_RECIPE_SERIALIZERS = new RecipeSerializerDeferredRegister(MekanismExtras.MODID);

    public static final RecipeSerializerRegistryObject<PlasmaEvaporationRecipe> PLASMA_EVAPORATION
            = GEN_RECIPE_SERIALIZERS.register("plasma_evaporation", () -> new PlasmaEvaporationRecipe.Serializer<>(PlasmaEvaporationIRecipe::new));


}
