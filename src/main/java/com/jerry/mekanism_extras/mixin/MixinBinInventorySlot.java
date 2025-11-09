package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockBin;

import mekanism.api.IContentsListener;
import mekanism.common.inventory.slot.BasicInventorySlot;
import mekanism.common.inventory.slot.BinInventorySlot;
import mekanism.common.item.block.ItemBlockBin;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(value = BinInventorySlot.class, remap = false)
public abstract class MixinBinInventorySlot extends BasicInventorySlot {

    @Final
    @Shadow
    @Mutable
    private static Predicate<ItemStack> validator;

    protected MixinBinInventorySlot(Predicate<@NotNull ItemStack> canExtract, Predicate<@NotNull ItemStack> canInsert, Predicate<@NotNull ItemStack> validator, @Nullable IContentsListener listener, int x, int y) {
        super(canExtract, canInsert, validator, listener, x, y);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyValidator(CallbackInfo ci) {
        validator = stack -> !(stack.getItem() instanceof ExtraItemBlockBin) && !(stack.getItem() instanceof ItemBlockBin);
    }
}
