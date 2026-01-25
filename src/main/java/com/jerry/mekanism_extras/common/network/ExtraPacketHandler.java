package com.jerry.mekanism_extras.common.network;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.network.to_server.ExtraPacketGuiButtonPress;
import com.jerry.mekanism_extras.common.network.to_server.ExtraPacketGuiInteract;

import mekanism.common.network.BasePacketHandler;

import net.minecraftforge.network.simple.SimpleChannel;

public class ExtraPacketHandler extends BasePacketHandler {

    private final SimpleChannel netHandler = createChannel(MekanismExtras.rl(MekanismExtras.MODID), MekanismExtras.instance.versionNumber);

    @Override
    protected SimpleChannel getChannel() {
        return netHandler;
    }

    @Override
    public void initialize() {
        registerClientToServer(ExtraPacketGuiInteract.class, ExtraPacketGuiInteract::decode);
        registerClientToServer(ExtraPacketGuiButtonPress.class, ExtraPacketGuiButtonPress::decode);
    }
}
