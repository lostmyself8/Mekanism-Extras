package com.jerry.mekextras.mixin;

import mekanism.api.Upgrade;
import mekanism.common.tile.component.TileComponentUpgrade;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileComponentUpgrade.class, remap = false)
public class Mixiniudhq {

    @Inject(method = "removeUpgrade", at = @At(value = "HEAD"))
    public void removeUpgrade(Upgrade upgrade, boolean removeAll, CallbackInfo ci) {
        System.out.println(Upgrade.BY_ID.apply(6));
    }
}
