package com.jerry.mekextras.mixin.client.recipe_viewer.jei;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.client.recipe_viewer.jei.ExtraAFCatalystRegistryHelper;
import com.jerry.mekextras.client.recipe_viewer.jei.ExtraMMCatalystRegistryHelper;

import mekanism.client.recipe_viewer.type.RecipeViewerRecipeType;

import com.jerry.mekmm.client.recipe_viewer.MMRecipeViewerRecipeType;
import com.jerry.mekmm.client.recipe_viewer.jei.MoreMachineJEI;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MoreMachineJEI.class, remap = false)
public class MixinMoreMachineJEI {

    @Inject(method = "registerRecipeCatalysts", at = @At(value = "INVOKE", target = "Lcom/jerry/mekmm/client/recipe_viewer/jei/MMCatalystRegistryHelper;register(Lmezz/jei/api/registration/IRecipeCatalystRegistration;Z[Lmekanism/client/recipe_viewer/type/IRecipeViewerRecipeType;)V", ordinal = 1))
    public void mixinRegisterRecipeCatalysts(IRecipeCatalystRegistration registry, CallbackInfo ci) {
        if (MekanismExtras.hooks.mekmm.isLoaded()) {
            ExtraMMCatalystRegistryHelper.register(registry, false, MMRecipeViewerRecipeType.RECYCLER, MMRecipeViewerRecipeType.PLANTING_STATION, MMRecipeViewerRecipeType.REPLICATOR,
                    MMRecipeViewerRecipeType.FLUID_REPLICATOR, MMRecipeViewerRecipeType.CHEMICAL_REPLICATOR, MMRecipeViewerRecipeType.STAMPING, MMRecipeViewerRecipeType.LATHE, MMRecipeViewerRecipeType.ROLLING_MILL);

            ExtraAFCatalystRegistryHelper.register(registry, false, RecipeViewerRecipeType.OXIDIZING, RecipeViewerRecipeType.CHEMICAL_INFUSING, RecipeViewerRecipeType.DISSOLUTION,
                    RecipeViewerRecipeType.WASHING, RecipeViewerRecipeType.CRYSTALLIZING, RecipeViewerRecipeType.REACTION, RecipeViewerRecipeType.CENTRIFUGING, RecipeViewerRecipeType.NUTRITIONAL_LIQUIFICATION);
        }
    }
}
