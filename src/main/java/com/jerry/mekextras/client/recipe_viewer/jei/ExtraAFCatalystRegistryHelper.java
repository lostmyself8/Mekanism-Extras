package com.jerry.mekextras.client.recipe_viewer.jei;

import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;

import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.block.attribute.Attribute;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;

import java.util.List;

public class ExtraAFCatalystRegistryHelper {

    private ExtraAFCatalystRegistryHelper() {}

    public static void register(IRecipeCatalystRegistration registry, IRecipeViewerRecipeType<?>... categories) {
        for (IRecipeViewerRecipeType<?> category : categories) {
            register(registry, MekanismJEI.genericRecipeType(category), category.workstations());
        }
    }

    public static void register(IRecipeCatalystRegistration registry, RecipeType<?> recipeType, List<ItemLike> workstations) {
        for (ItemLike workstation : workstations) {
            Item item = workstation.asItem();
            if (item instanceof BlockItem blockItem) {
                AttributeAdvancedFactoryType factoryType = Attribute.get(blockItem.getBlock(), AttributeAdvancedFactoryType.class);
                if (factoryType != null) {
                    for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
                        registry.addRecipeCatalyst(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, factoryType.getAdvancedFactoryType()), recipeType);
                    }
                }
            }
        }
    }
}
