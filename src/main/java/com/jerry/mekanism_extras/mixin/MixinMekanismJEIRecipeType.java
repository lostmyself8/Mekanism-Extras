package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.MekanismExtras;

import mekanism.client.jei.MekanismJEIRecipeType;

import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(value = MekanismJEIRecipeType.class, remap = false)
public abstract class MixinMekanismJEIRecipeType {

    @Inject(method = "findType", at = @At("HEAD"), cancellable = true)
    private static void meke$findEvaporationRecipe(ResourceLocation name, CallbackInfoReturnable<MekanismJEIRecipeType<?>> cir) {
        if (Objects.equals(name, MekanismExtras.rl("plasma_evaporation_controller"))) {
            cir.setReturnValue(MekanismJEIRecipeType.EVAPORATING);
        }
    }
}
