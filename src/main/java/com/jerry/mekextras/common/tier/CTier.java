package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.common.config.ExtraConfig;
import mekanism.api.math.FloatingLong;
import mekanism.common.tier.CableTier;

public class CTier {
    public static FloatingLong getCapacityAsFloatingLong(CableTier tier) {
        if (tier == null) return FloatingLong.create(8000L);
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteUniversalCableCapacity.get();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeUniversalCableCapacity.get();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicUniversalCableCapacity.get();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteUniversalCableCapacity.get();
        };
    }
}
