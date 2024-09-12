package com.jerry.mekextras.common.network;

import com.jerry.mekextras.common.network.to_server.button.ExtraPacketTileButtonPress;
import mekanism.common.lib.Version;
import mekanism.common.network.BasePacketHandler;
import mekanism.common.network.to_client.configuration.SyncAllSecurityData;
import net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.OnGameConfigurationEvent;

public class ExtraPacketHandler extends BasePacketHandler {
    public ExtraPacketHandler(IEventBus modEventBus, String modId, Version version) {
        super(modEventBus, modId, version);
        modEventBus.addListener(OnGameConfigurationEvent.class, event -> {
            ServerConfigurationPacketListener listener = event.getListener();
            event.register(new SyncAllSecurityData(listener));
        });
    }

    @Override
    protected void registerClientToServer(PacketRegistrar registrar) {
        registrar.play(ExtraPacketTileButtonPress.ID, ExtraPacketTileButtonPress::new);
    }

    @Override
    protected void registerServerToClient(PacketRegistrar registrar) {

    }
}
