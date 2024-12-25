package com.jerry.mekanism_extras.mixin.item.block;

import mekanism.common.block.basic.BlockBin;
import mekanism.common.item.block.ItemBlockBin;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.item.interfaces.IItemSustainedInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = ItemBlockBin.class, remap = false)
public abstract class MixinItemBlockBin extends ItemBlockTooltip<BlockBin> implements IItemSustainedInventory {
    public MixinItemBlockBin(BlockBin block, Properties properties) {
        super(block, properties);
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;stacksTo(I)Lnet/minecraft/world/item/Item$Properties;"))
    private static int stack(int p_41488_) {
        return 64;
    }
}
