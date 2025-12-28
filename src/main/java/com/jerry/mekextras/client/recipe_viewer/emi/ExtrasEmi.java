package com.jerry.mekextras.client.recipe_viewer.emi;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.ExtraLang;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.registries.ExtraFluids;
import com.jerry.mekextras.common.registries.ExtraItems;

import com.jerry.genextras.common.GenExtraLang;
import com.jerry.genextras.common.registries.GenExtraFluids;

import mekanism.client.recipe_viewer.emi.MekanismEmi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.stack.EmiStack;

import java.util.List;

@EmiEntrypoint
public class ExtrasEmi implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        addCategories(registry);

        MekanismEmi.registerItemSubtypes(registry, ExtraItems.EXTRA_ITEMS.getEntries());
        MekanismEmi.registerItemSubtypes(registry, ExtraBlocks.EXTRA_BLOCKS.getSecondaryEntries());
    }

    private void addCategories(EmiRegistry registry) {
        registry.addRecipe(new EmiInfoRecipe(List.of(EmiStack.of(ExtraFluids.RICH_NAQUADAH_FUEL.value())), List.of(
                ExtraLang.RECIPE_VIEWER_INFO_RICH_NAQUADAH_FUEL.translate()), MekanismExtras.rl("info/rich_naquadah_fuel")));
        registry.addRecipe(new EmiInfoRecipe(List.of(EmiStack.of(ExtraFluids.RICH_URANIUM_FUEL.value())), List.of(
                ExtraLang.RECIPE_VIEWER_INFO_RICH_URANIUM_FUEL.translate()), MekanismExtras.rl("info/rich_uranium_fuel")));
        if (MekanismExtras.hooks.mekanismGenerators.isLoaded()) {
            registry.addRecipe(new EmiInfoRecipe(List.of(EmiStack.of(GenExtraFluids.POLONIUM_CONTAINING_SOLUTION.value())), List.of(
                    GenExtraLang.RECIPE_VIEWER_INFO_POLONIUM_CONTAINING_SOLUTION.translate()), MekanismExtras.rl("info/polonium_containing_solution")));
        }
    }
}
