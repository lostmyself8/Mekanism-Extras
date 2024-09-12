package com.jerry.mekextras.common.capabilities;

import com.jerry.mekextras.common.api.IExtraAlloyInteraction;
import mekanism.common.Mekanism;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.jetbrains.annotations.Nullable;

public class ExtraCapabilities {
    private ExtraCapabilities() {

    }
    public static final BlockCapability<IExtraAlloyInteraction, @Nullable Direction> EXTRA_ALLOY_INTERACTION = BlockCapability.createSided(Mekanism.rl("extra_alloy_interaction"), IExtraAlloyInteraction.class);

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {

    }
}
