package com.jerry.mekextras.mixin;

import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.resource.ore.ExtraOreType;

import mekanism.common.registries.MekanismBlocks;
import mekanism.common.resource.ore.OreBlockType;
import mekanism.common.resource.ore.OreType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MekanismBlocks.class, remap = false)
public class MixinMekanismBlocks {

    @Inject(method = "registerOre", at = @At(value = "HEAD"), cancellable = true)
    private static void mixinRegisterOre(OreType ore, CallbackInfoReturnable<OreBlockType> cir) {
        if (ore == ExtraOreType.NAQUADAH) {
            // 不能直接返回null，但事实上mekanism在注册方块时ExtraBlocks.ORES为空，但不知道为什么可以运行
            cir.setReturnValue(ExtraBlocks.ORES.get(ore));
            cir.cancel();
        }
    }
}
