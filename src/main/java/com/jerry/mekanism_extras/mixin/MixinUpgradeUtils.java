package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.common.registry.ExtraItem;

import mekanism.api.Upgrade;
import mekanism.common.util.UpgradeUtils;

import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = UpgradeUtils.class, remap = false)
public abstract class MixinUpgradeUtils {

    @Inject(method = "getStack(Lmekanism/api/Upgrade;I)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "HEAD"), cancellable = true)
    private static void getItem(Upgrade upgrade, int count, CallbackInfoReturnable<ItemStack> cir) {
        switch (upgrade.toString()) {
            case "STACK" -> cir.setReturnValue(ExtraItem.STACK.getItemStack(count));
            case "IONIC_MEMBRANE" -> cir.setReturnValue(ExtraItem.IONIC_MEMBRANE.getItemStack(count));
            case "CREATIVE" -> cir.setReturnValue(ExtraItem.CREATIVE.getItemStack(count));
            // default -> throw new IllegalStateException(String.valueOf(ExtraUpgrade.STACK.ordinal()));
        }
    }
}
