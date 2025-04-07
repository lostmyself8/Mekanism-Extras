package com.jerry.mekanism_extras.mixin;

import mekanism.common.block.BlockEnergyCube;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = BlockEnergyCube.class)
public class MixinBlockEnergyCube {
    @ModifyArg(method = "getShape", at = @At(value = "INVOKE", target = "Lmekanism/common/util/WorldUtils;getTileEntity(Ljava/lang/Class;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Z)Lnet/minecraft/world/level/block/entity/BlockEntity;"), index = 3)
    private boolean preventWarnLog(boolean original) {
        return false;
    }
}
