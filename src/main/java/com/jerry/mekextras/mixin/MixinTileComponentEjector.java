package com.jerry.mekextras.mixin;

import com.jerry.mekextras.api.ExtraUpgrade;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.component.config.ConfigInfo;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileComponentEjector.class, remap = false)
public class MixinTileComponentEjector {

    @Shadow
    @Final
    private TileEntityMekanism tile;

    @Shadow
    private int tickDelay;

    @Inject(method = "outputItems", at = @At(value = "TAIL"))
    public void mixinGetEnergyPerTick(Direction facing, ConfigInfo info, CallbackInfo ci) {
        if (tile.supportsUpgrade(ExtraUpgrade.CREATIVE)) {
             if (tile.getComponent().isUpgradeInstalled(ExtraUpgrade.CREATIVE)) {
                 tickDelay = 0;
             }
        }
    }
}
