package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.common.config.ExtraConfig;
import mekanism.common.tier.ConductorTier;

public class TCTier {
    public static double getConduction(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteThermodynamicConductorConduction.get().doubleValue();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeThermodynamicConductorConduction.get().doubleValue();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicThermodynamicConductorConduction.get().doubleValue();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteThermodynamicConductorConduction.get().doubleValue();
        };
    }

    public static double getHeatCapacity(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteThermodynamicConductornCapacity.get().doubleValue();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeThermodynamicConductornCapacity.get().doubleValue();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicThermodynamicConductornCapacity.get().doubleValue();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteThermodynamicConductornCapacity.get().doubleValue();
        };
    }

    public static double getConductionInsulation(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteThermodynamicConductornInsulation.get().doubleValue();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeThermodynamicConductornInsulation.get().doubleValue();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicThermodynamicConductornInsulation.get().doubleValue();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteThermodynamicConductornInsulation.get().doubleValue();
        };
    }
}
