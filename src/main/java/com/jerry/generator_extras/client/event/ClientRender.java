package com.jerry.generator_extras.client.event;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.integration.Addons;

import com.jerry.generator_extras.common.genregistries.ExtraGenFluids;

import mekanism.client.ClientRegistrationUtil;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MekanismExtras.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRender {

    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            // Bucket Item Color
            ClientRegistrationUtil.registerBucketColorHandler(event, ExtraGenFluids.EXTRA_GEN_FLUIDS);
        }
    }
}
