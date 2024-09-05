package com.jerry.mekanism_extras.client.events;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.tier.TierColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MekanismExtras.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientTick {

    @SubscribeEvent
    public void onTickClientTick(TickEvent.ClientTickEvent event) {
        TierColor.tick();
    }
}
