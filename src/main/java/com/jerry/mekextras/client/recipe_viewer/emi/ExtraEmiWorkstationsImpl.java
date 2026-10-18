package com.jerry.mekextras.client.recipe_viewer.emi;

import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekextras.common.integration.mekmm.registries.ExtraMoreMachineBlocks;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;

import mekanism.common.block.attribute.Attribute;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekmm.common.block.attribute.MoreMachineAttributeFactoryType;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/** Resolves optional Mekanism More Machine factory registrations after its loaded check. */
public class ExtraEmiWorkstationsImpl implements ExtraEmiWorkstations {

    @Override
    public void register(EmiRegistry registry, EmiRecipeCategory category, List<ItemLike> workstations) {
        Set<Item> factories = new HashSet<>();
        for (ItemLike workstation : workstations) {
            if (workstation.asItem() instanceof BlockItem blockItem) {
                MoreMachineAttributeFactoryType moreMachineType = Attribute.get(blockItem.getBlock(), MoreMachineAttributeFactoryType.class);
                AttributeAdvancedFactoryType advancedType = Attribute.get(blockItem.getBlock(), AttributeAdvancedFactoryType.class);
                for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
                    if (moreMachineType != null) {
                        Item factory = Objects.requireNonNull(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier,
                                moreMachineType.getMoreMachineFactoryType()), "Missing extra MM factory: " + tier + "/" + moreMachineType.getMoreMachineFactoryType()).asItem();
                        if (factories.add(factory)) {
                            registry.addWorkstation(category, EmiStack.of(factory));
                        }
                    }
                    if (advancedType != null) {
                        Item factory = Objects.requireNonNull(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier,
                                advancedType.getAdvancedFactoryType()), "Missing extra advanced factory: " + tier + "/" + advancedType.getAdvancedFactoryType()).asItem();
                        if (factories.add(factory)) {
                            registry.addWorkstation(category, EmiStack.of(factory));
                        }
                    }
                }
            }
        }
    }
}
