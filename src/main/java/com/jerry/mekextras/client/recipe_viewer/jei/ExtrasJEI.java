package com.jerry.mekextras.client.recipe_viewer.jei;

import com.jerry.mekextras.MekanismExtras;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mekanism.client.recipe_viewer.type.RecipeViewerRecipeType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
@NothingNullByDefault
public class ExtrasJEI implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        // 不能使用MekanismExtras.rl()，原因见MekanismJEI.class
        return ResourceLocation.fromNamespaceAndPath(MekanismExtras.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        if (!MekanismJEI.shouldLoad()) {
            return;
        }
        // 只是添加JEI的侧面栏的显
        ExtraCatalystRegistryHelper.register(registry, RecipeViewerRecipeType.ENRICHING, RecipeViewerRecipeType.CRUSHING, RecipeViewerRecipeType.COMBINING,
                RecipeViewerRecipeType.PURIFYING, RecipeViewerRecipeType.COMPRESSING, RecipeViewerRecipeType.INJECTING, RecipeViewerRecipeType.SAWING,
                RecipeViewerRecipeType.METALLURGIC_INFUSING, RecipeViewerRecipeType.SMELTING, RecipeViewerRecipeType.CHEMICAL_CONVERSION);

        ExtraCatalystRegistryHelper.register(registry, RecipeTypes.SMELTING, RecipeViewerRecipeType.VANILLA_SMELTING.workstations());
    }
}
