package com.jerry.mekanism_extras.mixin.item.block;

import mekanism.common.block.BlockEnergyCube;
import mekanism.common.item.block.ItemBlockEnergyCube;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.item.interfaces.IItemSustainedInventory;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemBlockEnergyCube.class, remap = false)
public abstract class MixinItemBlockEnergyCube extends ItemBlockTooltip<BlockEnergyCube> implements IItemSustainedInventory, CreativeTabDeferredRegister.ICustomCreativeTabContents {
    public MixinItemBlockEnergyCube(BlockEnergyCube block, Properties properties) {
        super(block, properties);
    }

    @Inject(method = "isBarVisible", at = @At(value = "RETURN"), cancellable = true)
    public void isBarVisible(@NotNull ItemStack stack, @NotNull CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(stack.getCount() == 1);
    }

}
