package com.jerry.mekextras.mixin.client.recipe_viewer.emi;

import com.jerry.mekextras.client.recipe_viewer.emi.ExtraEmiWorkstations;
import com.jerry.mekextras.client.recipe_viewer.emi.ExtraEmiWorkstationsImpl;

import net.minecraft.world.level.ItemLike;

import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Pseudo
@Mixin(targets = "com.jerry.mekmm.client.recipe_viewer.emi.MoreMachineEMI", remap = false)
public abstract class MixinMoreMachineEmi {

    @Inject(method = "addWorkstations", at = @At("TAIL"))
    private static void mekanism_extras$addWorkstations(EmiRegistry registry, EmiRecipeCategory category, List<ItemLike> workstations, CallbackInfo ci) {
        ExtraEmiWorkstations extraWorkstations = new ExtraEmiWorkstationsImpl();
        extraWorkstations.register(registry, category, workstations);
    }
}
