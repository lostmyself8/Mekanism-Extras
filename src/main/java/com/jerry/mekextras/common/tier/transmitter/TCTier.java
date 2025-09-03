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
            case BASIC -> ExtraConfig.extraTierConfig.absoluteThermodynamicConductorCapacity.get();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeThermodynamicConductorCapacity.get();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicThermodynamicConductorCapacity.get();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteThermodynamicConductorCapacity.get();
        };
    }

    public static double getConductionInsulation(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteThermodynamicConductorInsulation.get();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeThermodynamicConductorInsulation.get();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicThermodynamicConductorInsulation.get();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteThermodynamicConductorInsulation.get();
        };
    }
}
