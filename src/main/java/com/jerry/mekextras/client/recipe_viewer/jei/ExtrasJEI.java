package com.jerry.mekextras.client.recipe_viewer.jei;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.ExtraLang;
import com.jerry.mekextras.common.registries.ExtraFluids;

import com.jerry.genextras.common.GenExtraLang;
import com.jerry.genextras.common.registries.GenExtraFluids;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mekanism.client.recipe_viewer.type.RecipeViewerRecipeType;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidType;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;

@JeiPlugin
@NothingNullByDefault
public class ExtrasJEI implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        // 不能使用MekanismExtras.rl()，原因见MekanismJEI.class
        return ResourceLocation.fromNamespaceAndPath(MekanismExtras.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (!MekanismJEI.shouldLoad()) {
            return;
        }
        registration.addIngredientInfo(ExtraFluids.RICH_NAQUADAH_FUEL.asStack(FluidType.BUCKET_VOLUME), NeoForgeTypes.FLUID_STACK,
                ExtraLang.RECIPE_VIEWER_INFO_RICH_NAQUADAH_FUEL.translate());
        registration.addIngredientInfo(ExtraFluids.RICH_URANIUM_FUEL.asStack(FluidType.BUCKET_VOLUME), NeoForgeTypes.FLUID_STACK,
                ExtraLang.RECIPE_VIEWER_INFO_RICH_URANIUM_FUEL.translate());
        if (MekanismExtras.hooks.mekanismGenerators.isLoaded()) {
            registration.addIngredientInfo(GenExtraFluids.POLONIUM_CONTAINING_SOLUTION.asStack(FluidType.BUCKET_VOLUME), NeoForgeTypes.FLUID_STACK,
                    GenExtraLang.RECIPE_VIEWER_INFO_POLONIUM_CONTAINING_SOLUTION.translate());
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        if (!MekanismJEI.shouldLoad()) {
            return;
        }
        // 只是添加JEI的侧面栏的显示
        ExtraCatalystRegistryHelper.register(registry, RecipeViewerRecipeType.ENRICHING, RecipeViewerRecipeType.CRUSHING, RecipeViewerRecipeType.COMBINING,
                RecipeViewerRecipeType.PURIFYING, RecipeViewerRecipeType.COMPRESSING, RecipeViewerRecipeType.INJECTING, RecipeViewerRecipeType.SAWING,
                RecipeViewerRecipeType.METALLURGIC_INFUSING, RecipeViewerRecipeType.SMELTING, RecipeViewerRecipeType.CHEMICAL_CONVERSION);

        ExtraCatalystRegistryHelper.register(registry, RecipeTypes.SMELTING, RecipeViewerRecipeType.VANILLA_SMELTING.workstations());
    }
}
