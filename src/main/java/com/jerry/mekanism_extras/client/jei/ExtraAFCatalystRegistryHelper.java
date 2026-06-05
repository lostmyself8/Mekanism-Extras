package com.jerry.mekanism_extras.client.jei;

import com.jerry.mekanism_extras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.api.providers.IItemProvider;
import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.registries.MekanismBlocks;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;

public class ExtraAFCatalystRegistryHelper {

    private ExtraAFCatalystRegistryHelper() {}

    public static void register(IRecipeCatalystRegistration registry) {
        register(registry, false, MekanismJEIRecipeType.OXIDIZING, MekanismJEIRecipeType.DISSOLUTION, MekanismJEIRecipeType.WASHING,
                MekanismJEIRecipeType.CRYSTALLIZING, MekanismJEIRecipeType.REACTION, MekanismJEIRecipeType.CENTRIFUGING,
                MekanismJEIRecipeType.NUTRITIONAL_LIQUIFICATION, MekanismJEIRecipeType.PIGMENT_EXTRACTING, MekanismJEIRecipeType.PAINTING);
    }

    public static void register(IRecipeCatalystRegistration registry, boolean needOrdinary, MekanismJEIRecipeType<?>... categories) {
        for (MekanismJEIRecipeType<?> category : categories) {
            RecipeType<?> recipeType = MekanismJEI.recipeType(category);
            if (category == MekanismJEIRecipeType.OXIDIZING) {
                register(registry, recipeType, MekanismBlocks.CHEMICAL_OXIDIZER, AdvancedFactoryType.OXIDIZING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.DISSOLUTION) {
                register(registry, recipeType, MekanismBlocks.CHEMICAL_DISSOLUTION_CHAMBER, AdvancedFactoryType.DISSOLVING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.WASHING) {
                register(registry, recipeType, MekanismBlocks.CHEMICAL_WASHER, AdvancedFactoryType.WASHING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.CRYSTALLIZING) {
                register(registry, recipeType, MekanismBlocks.CHEMICAL_CRYSTALLIZER, AdvancedFactoryType.CRYSTALLIZING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.REACTION) {
                register(registry, recipeType, MekanismBlocks.PRESSURIZED_REACTION_CHAMBER, AdvancedFactoryType.PRESSURISED_REACTING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.CENTRIFUGING) {
                register(registry, recipeType, MekanismBlocks.ISOTOPIC_CENTRIFUGE, AdvancedFactoryType.CENTRIFUGING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.NUTRITIONAL_LIQUIFICATION) {
                register(registry, recipeType, MekanismBlocks.NUTRITIONAL_LIQUIFIER, AdvancedFactoryType.LIQUIFYING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.PIGMENT_EXTRACTING) {
                register(registry, recipeType, MekanismBlocks.PIGMENT_EXTRACTOR, AdvancedFactoryType.PIGMENT_EXTRACTING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.PAINTING) {
                register(registry, recipeType, MekanismBlocks.PAINTING_MACHINE, AdvancedFactoryType.PAINTING, needOrdinary);
            }
        }
    }

    public static void register(IRecipeCatalystRegistration registry, RecipeType<?> recipeType, IItemProvider workstation, AdvancedFactoryType type, boolean needOrdinary) {
        if (needOrdinary) {
            registry.addRecipeCatalyst(workstation.getItemStack(), recipeType);
        }
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            registry.addRecipeCatalyst(factory(tier, type).getItemStack(), recipeType);
        }
    }

    private static IItemProvider factory(ExtraFactoryTier tier, AdvancedFactoryType type) {
        return ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, type);
    }
}
