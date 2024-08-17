package com.jerry.mekanism_extras.client.render;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.client.render.tileentity.ExtraRenderFluidTank;
import com.jerry.mekanism_extras.client.render.transmitter.ExtraRenderMechanicalPipe;
import mekanism.client.render.MekanismRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MekanismExtras.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExtraRenderer extends MekanismRenderer {

    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Post event) {
        ExtraRenderFluidTank.resetCachedModels();
        ExtraRenderMechanicalPipe.onStitch();
    }
}
