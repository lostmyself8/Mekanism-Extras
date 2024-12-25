package com.jerry.mekanism_extras.mixin.item;

import mekanism.common.item.CapabilityItem;
import mekanism.common.item.ItemEnergized;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemEnergized.class, remap = false)
public abstract class MixinItemEnergized extends CapabilityItem implements CreativeTabDeferredRegister.ICustomCreativeTabContents {
    protected MixinItemEnergized(Properties properties) {
        super(properties);
    }

    @ModifyArg(method = "<init>(Lmekanism/api/math/FloatingLongSupplier;Lmekanism/api/math/FloatingLongSupplier;Ljava/util/function/Predicate;Ljava/util/function/Predicate;Lnet/minecraft/world/item/Item$Properties;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;stacksTo(I)Lnet/minecraft/world/item/Item$Properties;"))
    private static int stack(int p_41488_) {
        return 64;
    }

    @Inject(method = "isBarVisible", at = @At(value = "RETURN"), cancellable = true)
    public void isBarVisible(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(stack.getCount() == 1);
    }
}
