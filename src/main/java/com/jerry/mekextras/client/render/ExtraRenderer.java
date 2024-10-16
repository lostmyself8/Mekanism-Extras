package com.jerry.mekextras.client.render;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.client.render.tileentity.ExtraRenderFluidTank;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.TextureAtlasStitchedEvent;

@EventBusSubscriber(modid = MekanismExtras.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)

public class ExtraRenderer {
    @SubscribeEvent
    public static void onStitch(TextureAtlasStitchedEvent event) {
        ExtraRenderFluidTank.resetCachedModels();
    }
}
