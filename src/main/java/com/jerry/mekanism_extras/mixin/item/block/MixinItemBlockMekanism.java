package com.jerry.mekanism_extras.mixin.item.block;

import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeEnergy;
import mekanism.common.item.block.ItemBlockMekanism;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemBlockMekanism.class, remap = false)
public abstract class MixinItemBlockMekanism<BLOCK extends Block> extends BlockItem {

    @Final
    @Shadow
    private BLOCK block;

    public MixinItemBlockMekanism(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
    }

    @Inject(method = "exposesEnergyCap", at = @At(value = "RETURN"), cancellable = true)
    protected void exposesEnergyCap(ItemStack stack, @NotNull CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(Attribute.has(block, AttributeEnergy.class));
    }
}
