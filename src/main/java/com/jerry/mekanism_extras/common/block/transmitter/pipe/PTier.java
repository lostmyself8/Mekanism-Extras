package com.jerry.mekanism_extras.common.block.transmitter.pipe;

import com.jerry.mekanism_extras.common.config.LoadConfig;
import mekanism.common.tier.PipeTier;

public class PTier {
    public static int getPipePullAmount(PipeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.extraConfig.absoluteMechanicalPipePullAmount.get().intValue();
            case ADVANCED -> LoadConfig.extraConfig.supremeMechanicalPipePullAmount.get().intValue();
            case ELITE -> LoadConfig.extraConfig.cosmicMechanicalPipePullAmount.get().intValue();
            case ULTIMATE -> LoadConfig.extraConfig.infiniteMechanicalPipePullAmount.get().intValue();
        };
    }

    public static long getPipeCapacity(PipeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.extraConfig.absoluteMechanicalPipeCapacity.get().longValue();
            case ADVANCED -> LoadConfig.extraConfig.supremeMechanicalPipeCapacity.get().longValue();
            case ELITE -> LoadConfig.extraConfig.cosmicMechanicalPipeCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.extraConfig.infiniteMechanicalPipeCapacity.get().longValue();
        };
    }
}
