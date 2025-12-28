package com.jerry.mekextras.client.events;

import com.jerry.mekextras.common.tier.TierColor;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

public class ClientTick {

    @SubscribeEvent
    public void onTick(ClientTickEvent.Pre event) {
        TierColor.tick();
    }
}
