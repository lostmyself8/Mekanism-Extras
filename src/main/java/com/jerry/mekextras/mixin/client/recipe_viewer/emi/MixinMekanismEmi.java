package com.jerry.mekextras.mixin.client.recipe_viewer.emi;

import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import mekanism.client.recipe_viewer.emi.MekanismEmi;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = MekanismEmi.class, remap = false)
public abstract class MixinMekanismEmi implements EmiPlugin {

    @Inject(method = "addWorkstations", at = @At(value = "TAIL"))
    private static void mixinAddWorkstations(EmiRegistry registry, EmiRecipeCategory category, List<ItemLike> workstations, CallbackInfo ci) {
        for (ItemLike workstation : workstations) {
            Item item = workstation.asItem();
            if (item instanceof BlockItem blockItem) {
                AttributeFactoryType factoryType = Attribute.get(blockItem.getBlock(), AttributeFactoryType.class);
                if (factoryType != null) {
                    for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
                        registry.addWorkstation(category, EmiStack.of(ExtraBlocks.getExtraFactory(tier, factoryType.getFactoryType())));
                    }
                }
            }
        }
    }
}
