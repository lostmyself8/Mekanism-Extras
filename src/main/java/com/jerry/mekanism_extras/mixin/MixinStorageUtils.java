package com.jerry.mekanism_extras.mixin;

import mekanism.common.util.StorageUtils;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = StorageUtils.class, remap = false)
public class MixinStorageUtils {

    @Inject(method = "getEnergyBarWidth", at = @At(value = "TAIL"), cancellable = true)
    private static void getEnergyBarWidth(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (stack.getCount() > 1) {
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "getBarWidth", at = @At(value = "TAIL"), cancellable = true)
    private static void getBarWidth(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (stack.getCount() > 1) {
            cir.setReturnValue(0);
        }
    }
}
