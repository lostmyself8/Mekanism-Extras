package com.jerry.mekextras.common.tier.transmitter;

import com.jerry.mekextras.common.config.ExtraConfig;
import mekanism.common.tier.ConductorTier;

public class TCTier {
    public static double getConduction(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteThermodynamicConductorConduction.get();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeThermodynamicConductorConduction.get();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicThermodynamicConductorConduction.get();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteThermodynamicConductorConduction.get();
        };
    }

    public static double getHeatCapacity(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteThermodynamicConductornCapacity.get();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeThermodynamicConductornCapacity.get();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicThermodynamicConductornCapacity.get();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteThermodynamicConductornCapacity.get();
        };
    }

    public static double getConductionInsulation(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteThermodynamicConductornInsulation.get();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeThermodynamicConductornInsulation.get();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicThermodynamicConductornInsulation.get();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteThermodynamicConductornInsulation.get();
        };
    }
}
