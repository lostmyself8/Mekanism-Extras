package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.common.config.ExtraConfig;
import mekanism.common.tier.TubeTier;

public class TTier {
    public static long getTubePullAmount(TubeTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absolutePressurizedTubePullAmount.get().longValue();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremePressurizedTubePullAmount.get().longValue();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicPressurizedTubePullAmount.get().longValue();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infinitePressurizedTubePullAmount.get().longValue();
        };
    }

    public static long getTubeCapacity(TubeTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absolutePressurizedTubeCapacity.get().longValue();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremePressurizedTubeCapacity.get().longValue();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicPressurizedTubeCapacity.get().longValue();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infinitePressurizedTubeCapacity.get().longValue();
        };
    }
}
