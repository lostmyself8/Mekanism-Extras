package com.jerry.mekanism_extras.common.tier.transmitter;

import com.jerry.mekanism_extras.common.config.LoadConfig;
import mekanism.api.math.FloatingLong;
import mekanism.common.tier.CableTier;

public class CTier {
    public static FloatingLong getCapacityAsFloatingLong(CableTier tier) {
        if (tier == null) return FloatingLong.create(8000L);
        return switch (tier) {
            case BASIC -> LoadConfig.extraConfig.absoluteUniversalCableCapacity.get();
            case ADVANCED -> LoadConfig.extraConfig.supremeUniversalCableCapacity.get();
            case ELITE -> LoadConfig.extraConfig.cosmicUniversalCableCapacity.get();
            case ULTIMATE -> LoadConfig.extraConfig.infiniteUniversalCableCapacity.get();
        };
    }
}
