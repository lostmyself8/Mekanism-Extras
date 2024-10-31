package com.jerry.mekextra.common.tier.transmitter;

import com.jerry.mekextra.common.config.ExtraConfig;
import mekanism.common.tier.PipeTier;

public class PTier {
    public static int getPipePullAmount(PipeTier tier) {
        return switch (tier) {
            case BASIC -> (int) ExtraConfig.extraTierConfig.absoluteMechanicalPipePullAmount.get();
            case ADVANCED -> (int) ExtraConfig.extraTierConfig.supremeMechanicalPipePullAmount.get();
            case ELITE -> (int) ExtraConfig.extraTierConfig.cosmicMechanicalPipePullAmount.get();
            case ULTIMATE -> (int) ExtraConfig.extraTierConfig.infiniteMechanicalPipePullAmount.get();
        };
    }

    public static long getPipeCapacity(PipeTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteMechanicalPipeCapacity.get();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeMechanicalPipeCapacity.get();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicMechanicalPipeCapacity.get();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteMechanicalPipeCapacity.get();
        };
    }
}
