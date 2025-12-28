package com.jerry.mekextras.client.recipe_viewer.jei;

import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;

import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;

import java.util.List;

public class ExtraCatalystRegistryHelper {

    private ExtraCatalystRegistryHelper() {}

    public static void register(IRecipeCatalystRegistration registry, IRecipeViewerRecipeType<?>... categories) {
        for (IRecipeViewerRecipeType<?> category : categories) {
            register(registry, MekanismJEI.genericRecipeType(category), category.workstations());
        }
    }

    public static void register(IRecipeCatalystRegistration registry, RecipeType<?> recipeType, List<ItemLike> workstations) {
        for (ItemLike workstation : workstations) {
            Item item = workstation.asItem();
            // 这里会多注册一个最低级的原版机器，所以注释掉就好了
            // registry.addRecipeCatalyst(item, recipeType);
            if (item instanceof BlockItem blockItem) {
                AttributeFactoryType factoryType = Attribute.get(blockItem.getBlock(), AttributeFactoryType.class);
                if (factoryType != null) {
                    for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
                        registry.addRecipeCatalyst(ExtraBlocks.getExtraFactory(tier, factoryType.getFactoryType()), recipeType);
                    }
                }
            }
        }
    }
}
