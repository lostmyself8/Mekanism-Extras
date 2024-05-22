package com.jerry.mekanism_extras.common.block.transmitter.cable;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.math.FloatingLong;
import mekanism.common.tier.CableTier;

public class CTier {
    public static FloatingLong getCapacityAsFloatingLong(CableTier tier) {
        if (tier == null) return FloatingLong.create(8000L);
        return switch (tier) {
            case BASIC -> MekanismExtras.getConfig().absoluteUniversalCableCapacity.get();
            case ADVANCED -> MekanismExtras.getConfig().supremeUniversalCableCapacity.get();
            case ELITE -> MekanismExtras.getConfig().cosmicUniversalCableCapacity.get();
            case ULTIMATE -> MekanismExtras.getConfig().infiniteUniversalCableCapacity.get();
        };
    }
}
