package com.jerry.mekextras.common.network;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.network.to_client.transmitter.ExtraPacketTransporterBatch;
import com.jerry.mekextras.common.network.to_client.transmitter.ExtraPacketTransporterSync;
import com.jerry.mekextras.common.network.to_server.ExtraPacketGuiInteract;
import com.jerry.mekextras.common.network.to_server.button.ExtraPacketTileButtonPress;

import com.jerry.genextras.common.network.to_server.PacketGenExtraGuiInteract;

import mekanism.common.lib.Version;
import mekanism.common.network.BasePacketHandler;
import mekanism.common.network.to_client.configuration.SyncAllSecurityData;

import net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterConfigurationTasksEvent;

public class ExtraPacketHandler extends BasePacketHandler {

    public ExtraPacketHandler(IEventBus modEventBus, Version version) {
        super(modEventBus, version);
        modEventBus.addListener(RegisterConfigurationTasksEvent.class, event -> {
            ServerConfigurationPacketListener listener = event.getListener();
            event.register(new SyncAllSecurityData(listener));
        });
    }

    @Override
    protected void registerClientToServer(PacketRegistrar registrar) {
        registrar.play(ExtraPacketGuiInteract.TYPE, ExtraPacketGuiInteract.STREAM_CODEC);
        if (MekanismExtras.hooks.mekanismGenerators.isLoaded()) {
            registrar.play(PacketGenExtraGuiInteract.TYPE, PacketGenExtraGuiInteract.STREAM_CODEC);
        }

        // Button Press
        registrar.play(ExtraPacketTileButtonPress.TYPE, ExtraPacketTileButtonPress.STREAM_CODEC);
    }

    @Override
    protected void registerServerToClient(PacketRegistrar registrar) {
        registrar.play(ExtraPacketTransporterSync.TYPE, ExtraPacketTransporterSync.STREAM_CODEC);
        registrar.play(ExtraPacketTransporterBatch.TYPE, ExtraPacketTransporterBatch.STREAM_CODEC);
    }
}
