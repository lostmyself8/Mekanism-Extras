package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.common.config.ExtraConfig;
import mekanism.common.tier.PipeTier;

public class PTier {
    public static int getPipePullAmount(PipeTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteMechanicalPipePullAmount.get().intValue();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeMechanicalPipePullAmount.get().intValue();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicMechanicalPipePullAmount.get().intValue();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteMechanicalPipePullAmount.get().intValue();
        };
    }

    public static long getPipeCapacity(PipeTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteMechanicalPipeCapacity.get().longValue();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeMechanicalPipeCapacity.get().longValue();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicMechanicalPipeCapacity.get().longValue();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteMechanicalPipeCapacity.get().longValue();
        };
    }
}
