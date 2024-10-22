package com.jerry.mekanism_extras.client.render;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.client.render.tileentity.ExtraRenderFluidTank;
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
