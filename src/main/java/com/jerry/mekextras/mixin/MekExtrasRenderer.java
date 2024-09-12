package com.jerry.mekextras.mixin;

import com.jerry.mekextras.client.render.tileentity.ExtraRenderFluidTank;
import com.jerry.mekextras.client.render.transmitter.ExtraRenderMechanicalPipe;
import mekanism.client.render.MekanismRenderer;
import net.neoforged.neoforge.client.event.TextureAtlasStitchedEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MekanismRenderer.class, remap = false)
public class MekExtrasRenderer {

    @Inject(method = "onStitch", at = @At(value = "INVOKE", target = "Lmekanism/client/render/transmitter/RenderMechanicalPipe;onStitch()V"))
    private static void onExtraStitch(TextureAtlasStitchedEvent event, CallbackInfo ci) {
        ExtraRenderFluidTank.resetCachedModels();
        ExtraRenderMechanicalPipe.onStitch();
    }
}
