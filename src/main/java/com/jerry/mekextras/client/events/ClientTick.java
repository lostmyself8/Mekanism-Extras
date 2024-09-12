package com.jerry.mekextras.client.events;

import com.jerry.mekextras.common.tier.TierColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.TickEvent;

public class ClientTick {
    @SubscribeEvent
    public void onTickClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            TierColor.tick();
        }
    }
}
