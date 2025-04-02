package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.common.network.to_server.ExtraPacketGuiButtonPress;
import com.jerry.mekanism_extras.common.network.to_server.ExtraPacketGuiInteract;
import mekanism.common.network.BasePacketHandler;
import mekanism.common.network.PacketHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PacketHandler.class, remap = false)
public abstract class MixinPacketHandler extends BasePacketHandler {
    @Inject(method = "initialize", at = @At("HEAD"))
    private void initialize(CallbackInfo ci) {
        registerClientToServer(ExtraPacketGuiInteract.class, ExtraPacketGuiInteract::decode);
        registerClientToServer(ExtraPacketGuiButtonPress.class, ExtraPacketGuiButtonPress::decode);
    }
}
