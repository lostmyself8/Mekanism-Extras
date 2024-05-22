package com.jerry.mekanism_extras.common.block.transmitter.tube;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.tier.TubeTier;

public class TTier {
    public static long getTubePullAmount(TubeTier tier) {
        return switch (tier) {
            case BASIC -> MekanismExtras.getConfig().absolutePressurizedTubePullAmount.get().longValue();
            case ADVANCED -> MekanismExtras.getConfig().supremePressurizedTubePullAmount.get().longValue();
            case ELITE -> MekanismExtras.getConfig().cosmicPressurizedTubePullAmount.get().longValue();
            case ULTIMATE -> MekanismExtras.getConfig().infinitePressurizedTubePullAmount.get().longValue();
        };
    }

    public static long getTubeCapacity(TubeTier tier) {
        return switch (tier) {
            case BASIC -> MekanismExtras.getConfig().absolutePressurizedTubeCapacity.get().longValue();
            case ADVANCED -> MekanismExtras.getConfig().supremePressurizedTubeCapacity.get().longValue();
            case ELITE -> MekanismExtras.getConfig().cosmicPressurizedTubeCapacity.get().longValue();
            case ULTIMATE -> MekanismExtras.getConfig().infinitePressurizedTubeCapacity.get().longValue();
        };
    }
}
