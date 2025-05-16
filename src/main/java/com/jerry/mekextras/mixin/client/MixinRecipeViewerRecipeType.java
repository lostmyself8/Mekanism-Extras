package com.jerry.mekextras.mixin.client;

import com.jerry.mekextras.common.registries.ExtraBlocks;
import mekanism.api.recipes.ItemStackToEnergyRecipe;
import mekanism.client.recipe_viewer.type.RecipeViewerRecipeType;
import mekanism.client.recipe_viewer.type.SimpleRVRecipeType;
import mekanism.common.MekanismLang;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.util.MekanismUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RecipeViewerRecipeType.class, remap = false)
public abstract class MixinRecipeViewerRecipeType {

    @Final
    @Shadow
    @Mutable
    public static SimpleRVRecipeType<?, ItemStackToEnergyRecipe, ?> ENERGY_CONVERSION;

    // 使JEI侧栏显示高级能量立方（物品到能量）
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyEnergyConversion(CallbackInfo ci) {
        ENERGY_CONVERSION = new SimpleRVRecipeType<>(MekanismRecipeType.ENERGY_CONVERSION, ItemStackToEnergyRecipe.class, MekanismLang.CONVERSION_ENERGY, MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "energy.png"), -20, -12, 132, 62,
                MekanismBlocks.BASIC_ENERGY_CUBE, MekanismBlocks.ADVANCED_ENERGY_CUBE, MekanismBlocks.ELITE_ENERGY_CUBE, MekanismBlocks.ULTIMATE_ENERGY_CUBE, ExtraBlocks.ABSOLUTE_ENERGY_CUBE, ExtraBlocks.SUPREME_ENERGY_CUBE, ExtraBlocks.COSMIC_ENERGY_CUBE, ExtraBlocks.INFINITE_ENERGY_CUBE);
    }
}
