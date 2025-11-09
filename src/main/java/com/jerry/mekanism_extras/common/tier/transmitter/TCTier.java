package com.jerry.mekanism_extras.common.tier.transmitter;

import com.jerry.mekanism_extras.common.config.LoadConfig;

import mekanism.common.tier.ConductorTier;

public class TCTier {

    public static long getConduction(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.extraConfig.absoluteThermodynamicConductorConduction.get().longValue();
            case ADVANCED -> LoadConfig.extraConfig.supremeThermodynamicConductorConduction.get().longValue();
            case ELITE -> LoadConfig.extraConfig.cosmicThermodynamicConductorConduction.get().longValue();
            case ULTIMATE -> LoadConfig.extraConfig.infiniteThermodynamicConductorConduction.get().longValue();
        };
    }

    public static long getHeatCapacity(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.extraConfig.absoluteThermodynamicConductornCapacity.get().longValue();
            case ADVANCED -> LoadConfig.extraConfig.supremeThermodynamicConductornCapacity.get().longValue();
            case ELITE -> LoadConfig.extraConfig.cosmicThermodynamicConductornCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.extraConfig.infiniteThermodynamicConductornCapacity.get().longValue();
        };
    }

    public static long getConductionInsulation(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.extraConfig.absoluteThermodynamicConductornInsulation.get().longValue();
            case ADVANCED -> LoadConfig.extraConfig.supremeThermodynamicConductornInsulation.get().longValue();
            case ELITE -> LoadConfig.extraConfig.cosmicThermodynamicConductornInsulation.get().longValue();
            case ULTIMATE -> LoadConfig.extraConfig.infiniteThermodynamicConductornInsulation.get().longValue();
        };
    }
}
