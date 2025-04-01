package com.jerry.mekanism_extras.client.jei;

import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.providers.IItemProvider;
import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;

public class ExtraCatalystRegistryHelper {

    private ExtraCatalystRegistryHelper() {
    }

    public static void register(IRecipeCatalystRegistration registry, IBlockProvider mekanismBlock, MekanismJEIRecipeType<?>... additionalCategories) {
        MekanismJEIRecipeType<?>[] categories = new MekanismJEIRecipeType<?>[additionalCategories.length + 1];
        categories[0] = MekanismJEIRecipeType.findType(mekanismBlock.getRegistryName());
        System.arraycopy(additionalCategories, 0, categories, 1, additionalCategories.length);
        registerRecipeItem(registry, mekanismBlock, categories);
    }

    public static void registerRecipeItem(IRecipeCatalystRegistration registry, IItemProvider mekanismItem, MekanismJEIRecipeType<?>... categories) {
        registerRecipeItem(registry, mekanismItem, MekanismJEI.recipeType(categories));
    }

    public static void registerRecipeItem(IRecipeCatalystRegistration registry, IItemProvider mekanismItem, MekanismJEIRecipeType<?> category,
                                          RecipeType<?>... additionalCategories) {
        RecipeType<?>[] categories = new RecipeType<?>[additionalCategories.length + 1];
        categories[0] = MekanismJEI.recipeType(category);
        System.arraycopy(additionalCategories, 0, categories, 1, additionalCategories.length);
        registerRecipeItem(registry, mekanismItem, categories);
    }

    public static void registerRecipeItem(IRecipeCatalystRegistration registry, IItemProvider mekanismItem, RecipeType<?>... categories) {
        //这里会多注册一个最低级的原版机器，所以注释掉就好了
//        registry.addRecipeCatalyst(mekanismItem.getItemStack(), categories);
        if (mekanismItem instanceof IBlockProvider mekanismBlock) {
            Attribute.ifPresent(mekanismBlock.getBlock(), AttributeFactoryType.class, attr -> {
                for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
                    registry.addRecipeCatalyst(ExtraBlock.getAdvancedFactory(tier, attr.getFactoryType()).getItemStack(), categories);
                }
            });
        }
    }

    public static void register(IRecipeCatalystRegistration registry, MekanismJEIRecipeType<?> category, IItemProvider... catalysts) {
        RecipeType<?> recipeType = MekanismJEI.recipeType(category);
        for (IItemProvider catalyst : catalysts) {
            registry.addRecipeCatalyst(catalyst.getItemStack(), recipeType);
        }
    }
}
