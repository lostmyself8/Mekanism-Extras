package com.jerry.mekextras.client.recipe_viewer.jei;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.registries.MekanismBlocks;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class ExtraAFCatalystRegistryHelper {

    private ExtraAFCatalystRegistryHelper() {}

    /**
     *与Mekanism的CatalystRegistryHelper中的register方法功能是一致的，只是多了一个需否需要基础机器的参数，
     * 平常使用可以直接填true，对于“ENERGY_CONVERSION”或“CHEMICAL_CONVERSION”
     * 可能得填写false。
     *
     * @param needOrdinary 是否需要注册最基础的机器
     */
    public static void register(IRecipeCatalystRegistration registry, boolean needOrdinary, IRecipeViewerRecipeType<?>... categories) {
        for (IRecipeViewerRecipeType<?> category : categories) {
            register(registry, MekanismJEI.genericRecipeType(category), category.workstations(), needOrdinary);
        }
    }

    public static void register(IRecipeCatalystRegistration registry, RecipeType<?> recipeType, List<ItemLike> workstations, boolean needOrdinary) {
        for (ItemLike workstation : workstations) {
            Item item = workstation.asItem();
            if (needOrdinary) {
                registry.addRecipeCatalyst(item, recipeType);
            }
            for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
                if (workstation == MekanismBlocks.CHEMICAL_OXIDIZER) {
                    registry.addRecipeCatalyst(ExtraAdvancedFactoryBlocks.getAdvancedFactory(tier, AdvancedFactoryType.OXIDIZING), recipeType);
                } else if (workstation == MekanismBlocks.CHEMICAL_INFUSER) {
                    registry.addRecipeCatalyst(ExtraAdvancedFactoryBlocks.getAdvancedFactory(tier, AdvancedFactoryType.CHEMICAL_INFUSING), recipeType);
                } else if (workstation == MekanismBlocks.CHEMICAL_DISSOLUTION_CHAMBER) {
                    registry.addRecipeCatalyst(ExtraAdvancedFactoryBlocks.getAdvancedFactory(tier, AdvancedFactoryType.DISSOLVING), recipeType);
                } else if (workstation == MekanismBlocks.CHEMICAL_WASHER) {
                    registry.addRecipeCatalyst(ExtraAdvancedFactoryBlocks.getAdvancedFactory(tier, AdvancedFactoryType.WASHING), recipeType);
                } else if (workstation == MekanismBlocks.CHEMICAL_CRYSTALLIZER) {
                    registry.addRecipeCatalyst(ExtraAdvancedFactoryBlocks.getAdvancedFactory(tier, AdvancedFactoryType.CRYSTALLIZING), recipeType);
                } else if (workstation == MekanismBlocks.PRESSURIZED_REACTION_CHAMBER) {
                    registry.addRecipeCatalyst(ExtraAdvancedFactoryBlocks.getAdvancedFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING), recipeType);
                } else if (workstation == MekanismBlocks.ISOTOPIC_CENTRIFUGE) {
                    registry.addRecipeCatalyst(ExtraAdvancedFactoryBlocks.getAdvancedFactory(tier, AdvancedFactoryType.CENTRIFUGING), recipeType);
                } else if (workstation == MekanismBlocks.NUTRITIONAL_LIQUIFIER) {
                    registry.addRecipeCatalyst(ExtraAdvancedFactoryBlocks.getAdvancedFactory(tier, AdvancedFactoryType.LIQUIFYING), recipeType);
                }
            }
        }
    }
}
