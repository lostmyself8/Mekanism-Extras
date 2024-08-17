package com.jerry.mekanism_extras.mixin;

import com.jerry.generator_extras.common.network.to_server.ExtraPacketGeneratorsGuiButtonPress;
import com.jerry.mekanism_extras.integration.Addons;
import mekanism.common.network.BasePacketHandler;
import mekanism.generators.common.network.GeneratorsPacketHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GeneratorsPacketHandler.class, remap = false)
public abstract class MixinGeneratorsPacketHandler extends BasePacketHandler {
    @Inject(method = "initialize", at = @At("HEAD"))
    private void initialize(CallbackInfo ci) {
        if (Addons.MEKANISMGENERATORS.isLoaded()) registerClientToServer(ExtraPacketGeneratorsGuiButtonPress.class, ExtraPacketGeneratorsGuiButtonPress::decode);
    }
}
