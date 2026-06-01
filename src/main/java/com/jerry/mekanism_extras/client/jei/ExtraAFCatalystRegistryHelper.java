package com.jerry.mekanism_extras.client.jei;

import com.jerry.mekanism_extras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.api.providers.IItemProvider;
import mekanism.client.jei.CatalystRegistryHelper;
import mekanism.client.jei.MekanismJEIRecipeType;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;

public class ExtraAFCatalystRegistryHelper {

    private ExtraAFCatalystRegistryHelper() {}

    public static void register(IRecipeCatalystRegistration registry) {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            CatalystRegistryHelper.register(registry, MekanismJEIRecipeType.OXIDIZING, factory(tier, AdvancedFactoryType.OXIDIZING));
            CatalystRegistryHelper.register(registry, MekanismJEIRecipeType.DISSOLUTION, factory(tier, AdvancedFactoryType.DISSOLVING));
            CatalystRegistryHelper.register(registry, MekanismJEIRecipeType.WASHING, factory(tier, AdvancedFactoryType.WASHING));
            CatalystRegistryHelper.register(registry, MekanismJEIRecipeType.CRYSTALLIZING, factory(tier, AdvancedFactoryType.CRYSTALLIZING));
            CatalystRegistryHelper.register(registry, MekanismJEIRecipeType.REACTION, factory(tier, AdvancedFactoryType.PRESSURISED_REACTING));
            CatalystRegistryHelper.register(registry, MekanismJEIRecipeType.CENTRIFUGING, factory(tier, AdvancedFactoryType.CENTRIFUGING));
            CatalystRegistryHelper.register(registry, MekanismJEIRecipeType.NUTRITIONAL_LIQUIFICATION, factory(tier, AdvancedFactoryType.LIQUIFYING));
            CatalystRegistryHelper.register(registry, MekanismJEIRecipeType.PIGMENT_EXTRACTING, factory(tier, AdvancedFactoryType.PIGMENT_EXTRACTING));
            CatalystRegistryHelper.register(registry, MekanismJEIRecipeType.PAINTING, factory(tier, AdvancedFactoryType.PAINTING));
        }
    }

    private static IItemProvider factory(ExtraFactoryTier tier, AdvancedFactoryType type) {
        return ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, type);
    }
}
