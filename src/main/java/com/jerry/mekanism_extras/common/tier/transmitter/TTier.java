package com.jerry.mekanism_extras.common.tier.transmitter;

import com.jerry.mekanism_extras.common.config.LoadConfig;
import mekanism.common.tier.TubeTier;

public class TTier {
    public static long getTubePullAmount(TubeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.extraConfig.absolutePressurizedTubePullAmount.get().longValue();
            case ADVANCED -> LoadConfig.extraConfig.supremePressurizedTubePullAmount.get().longValue();
            case ELITE -> LoadConfig.extraConfig.cosmicPressurizedTubePullAmount.get().longValue();
            case ULTIMATE -> LoadConfig.extraConfig.infinitePressurizedTubePullAmount.get().longValue();
        };
    }

    public static long getTubeCapacity(TubeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.extraConfig.absolutePressurizedTubeCapacity.get().longValue();
            case ADVANCED -> LoadConfig.extraConfig.supremePressurizedTubeCapacity.get().longValue();
            case ELITE -> LoadConfig.extraConfig.cosmicPressurizedTubeCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.extraConfig.infinitePressurizedTubeCapacity.get().longValue();
        };
    }
}
