package com.jerry.mekextras.mixin.client;

import com.jerry.mekextras.client.render.tileentity.RenderExtraFluidTank;
import com.jerry.mekextras.client.render.transmitter.RenderExtraMechanicalPipe;
import mekanism.client.render.MekanismRenderer;
import net.neoforged.neoforge.client.event.TextureAtlasStitchedEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MekanismRenderer.class, remap = false)
public class MixinMekanismRenderer {

    @Inject(method = "onStitch", at = @At(value = "INVOKE", target = "Lmekanism/client/render/transmitter/RenderMechanicalPipe;onStitch()V"))
    private static void onExtraStitch(TextureAtlasStitchedEvent event, CallbackInfo ci) {
        RenderExtraFluidTank.resetCachedModels();
        RenderExtraMechanicalPipe.onStitch();
    }
}
