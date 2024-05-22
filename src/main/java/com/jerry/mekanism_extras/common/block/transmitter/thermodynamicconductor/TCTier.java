package com.jerry.mekanism_extras.common.block.transmitter.thermodynamicconductor;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.tier.ConductorTier;
public class TCTier {
    public static long getConduction(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> MekanismExtras.getConfig().absoluteThermodynamicConductorConduction.get().longValue();
            case ADVANCED -> MekanismExtras.getConfig().supremeThermodynamicConductorConduction.get().longValue();
            case ELITE -> MekanismExtras.getConfig().cosmicThermodynamicConductorConduction.get().longValue();
            case ULTIMATE -> MekanismExtras.getConfig().infiniteThermodynamicConductorConduction.get().longValue();
        };
    }

    public static long getHeatCapacity(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> MekanismExtras.getConfig().absoluteThermodynamicConductornCapacity.get().longValue();
            case ADVANCED -> MekanismExtras.getConfig().supremeThermodynamicConductornCapacity.get().longValue();
            case ELITE -> MekanismExtras.getConfig().cosmicThermodynamicConductornCapacity.get().longValue();
            case ULTIMATE -> MekanismExtras.getConfig().infiniteThermodynamicConductornCapacity.get().longValue();
        };
    }

    public static long getConductionInsulation(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> MekanismExtras.getConfig().absoluteThermodynamicConductornInsulation.get().longValue();
            case ADVANCED -> MekanismExtras.getConfig().supremeThermodynamicConductornInsulation.get().longValue();
            case ELITE -> MekanismExtras.getConfig().cosmicThermodynamicConductornInsulation.get().longValue();
            case ULTIMATE -> MekanismExtras.getConfig().infiniteThermodynamicConductornInsulation.get().longValue();
        };
    }
}
