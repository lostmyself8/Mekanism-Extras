package com.jerry.mekanism_extras.common.block.transmitter.pipe;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.tier.PipeTier;

public class PTier {
    public static int getPipePullAmount(PipeTier tier) {
        return switch (tier) {
            case BASIC -> MekanismExtras.getConfig().absoluteMechanicalPipePullAmount.get().intValue();
            case ADVANCED -> MekanismExtras.getConfig().supremeMechanicalPipePullAmount.get().intValue();
            case ELITE -> MekanismExtras.getConfig().cosmicMechanicalPipePullAmount.get().intValue();
            case ULTIMATE -> MekanismExtras.getConfig().infiniteMechanicalPipePullAmount.get().intValue();
        };
    }

    public static long getPipeCapacity(PipeTier tier) {
        return switch (tier) {
            case BASIC -> MekanismExtras.getConfig().absoluteMechanicalPipeCapacity.get().longValue();
            case ADVANCED -> MekanismExtras.getConfig().supremeMechanicalPipeCapacity.get().longValue();
            case ELITE -> MekanismExtras.getConfig().cosmicMechanicalPipeCapacity.get().longValue();
            case ULTIMATE -> MekanismExtras.getConfig().infiniteMechanicalPipeCapacity.get().longValue();
        };
    }
}
