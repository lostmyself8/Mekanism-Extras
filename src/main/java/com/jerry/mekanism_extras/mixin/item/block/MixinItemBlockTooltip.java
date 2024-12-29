package com.jerry.mekanism_extras.mixin.item.block;

import mekanism.common.block.interfaces.IHasDescription;
import mekanism.common.item.block.ItemBlockMekanism;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = mekanism.common.item.block.ItemBlockTooltip.class, remap = false)
public abstract class MixinItemBlockTooltip<BLOCK extends Block & IHasDescription> extends ItemBlockMekanism<BLOCK> {
    public MixinItemBlockTooltip(@NotNull BLOCK block, Properties properties) {
        super(block, properties);
    }

    @ModifyArg(method = "<init>(Lnet/minecraft/world/level/block/Block;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;stacksTo(I)Lnet/minecraft/world/item/Item$Properties;"))
    private static int stack(int stack) {
        return 64;
    }
}
