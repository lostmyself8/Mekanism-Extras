package com.jerry.generator_extras.common.genregistry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.api.recipes.PlasmaEvaporationRecipe;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import mekanism.common.recipe.lookup.cache.InputRecipeCache.*;
import mekanism.common.registration.impl.RecipeTypeDeferredRegister;
import mekanism.common.registration.impl.RecipeTypeRegistryObject;
import net.minecraftforge.fml.common.Mod.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

@EventBusSubscriber(modid = MekanismExtras.MODID)
public class ExtraGenRecipeTypes {

    public static final RecipeTypeDeferredRegister GEN_RECIPE_TYPES = new RecipeTypeDeferredRegister(MekanismExtras.MODID);

    public static final RecipeTypeRegistryObject<PlasmaEvaporationRecipe, FluidChemical<Gas, GasStack, PlasmaEvaporationRecipe>> PLASMA_EVAPORATION
            = register("plasma_evaporation", recipeType -> new FluidChemical<>(recipeType, PlasmaEvaporationRecipe::getInputFluid, PlasmaEvaporationRecipe::getInputGas));

    @SuppressWarnings("unchecked")
    private static <RECIPE extends MekanismRecipe, INPUT_CACHE extends IInputRecipeCache> RecipeTypeRegistryObject<RECIPE, INPUT_CACHE> register(String name,
                                                                                                                                                 Function<MekanismRecipeType<RECIPE, INPUT_CACHE>, INPUT_CACHE> inputCacheCreator) {
        Constructor<MekanismRecipeType> constructor;
        try {
            // Hope it can be public!
            constructor = MekanismRecipeType.class.getDeclaredConstructor(String.class, Function.class);
            constructor.setAccessible(true);
            return GEN_RECIPE_TYPES.register(name, () -> {
                try {
                    return constructor.newInstance(name, inputCacheCreator);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
