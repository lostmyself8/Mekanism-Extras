package com.jerry.mekextra.common.tier.transmitter;

import com.jerry.mekextra.common.config.ExtraConfig;
import mekanism.common.tier.TubeTier;

public class TTier {
    public static long getTubePullAmount(TubeTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absolutePressurizedTubePullAmount.get();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremePressurizedTubePullAmount.get();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicPressurizedTubePullAmount.get();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infinitePressurizedTubePullAmount.get();
        };
    }

    public static long getTubeCapacity(TubeTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absolutePressurizedTubeCapacity.get();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremePressurizedTubeCapacity.get();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicPressurizedTubeCapacity.get();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infinitePressurizedTubeCapacity.get();
        };
    }
}
