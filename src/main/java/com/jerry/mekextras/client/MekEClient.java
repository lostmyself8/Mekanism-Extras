package com.jerry.mekextras.client;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.ExtraLang;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@Mod(value = MekanismExtras.MOD_ID, dist = Dist.CLIENT)
public class MekEClient {

    public MekEClient(IEventBus eventBus) {
        eventBus.addListener(MekEClient::initResourcePackFinder);
    }

    private static void initResourcePackFinder(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addPackFinders(
                    MekanismExtras.rl("stop_flashing"),
                    PackType.CLIENT_RESOURCES,
                    Component.translatable(ExtraLang.STOP_FLASHING.getTranslationKey()),
                    PackSource.BUILT_IN,
                    false,
                    Pack.Position.TOP);
        }
    }
}
