package com.jerry.mekextras.client.recipe_viewer.emi;

import net.minecraft.world.level.ItemLike;

import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;

import java.util.List;

/** Adds the extra MM and advanced factory tiers to their existing EMI recipe categories. */
public interface ExtraEmiWorkstations {

    /**
     * Resolves factory attributes from the category's original machines so all extra tiers share the real category
     * instance.
     *
     * @param registry     registration destination for this EMI reload
     * @param category     existing category whose recipes the factories can process
     * @param workstations original machines used to identify the supported factory types
     */
    void register(EmiRegistry registry, EmiRecipeCategory category, List<ItemLike> workstations);
}
