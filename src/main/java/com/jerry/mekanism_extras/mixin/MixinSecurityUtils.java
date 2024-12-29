package com.jerry.mekanism_extras.mixin;

import mekanism.api.functions.TriConsumer;
import mekanism.api.security.ISecurityUtils;
import mekanism.common.util.SecurityUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SecurityUtils.class, remap = false)
public abstract class MixinSecurityUtils implements ISecurityUtils {
    @Inject(method = "claimOrOpenGui", at = @At(value = "INVOKE", target = "Lmekanism/common/util/SecurityUtils;canAccessOrDisplayError(Lnet/minecraft/world/entity/player/Player;Lnet/minecraftforge/common/capabilities/ICapabilityProvider;)Z", shift = At.Shift.AFTER), cancellable = true)
    public void claimOrOpenGui(Level level, Player player, InteractionHand hand, TriConsumer<ServerPlayer, InteractionHand, ItemStack> openGui, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getCount() > 1) {
            cir.setReturnValue(InteractionResultHolder.pass(stack));
        }
    }
}
