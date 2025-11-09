package com.jerry.mekanism_extras.common.capabilities;

import com.jerry.mekanism_extras.api.IExtraAlloyInteraction;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ExtraCapabilities {

    private ExtraCapabilities() {}

    public static final Capability<IExtraAlloyInteraction> EXTRA_ALLOY_INTERACTION = CapabilityManager.get(new CapabilityToken<>() {});
}
