package com.jerry.mekanism_extras.common.block.transmitter.logisticaltransporter;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.tier.TransporterTier;

public class TPTier {
    public static int getSpeed(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> MekanismExtras.getConfig().absoluteLogisticalTransporterSpeed.get().intValue();
            case ADVANCED -> MekanismExtras.getConfig().supremeLogisticalTransporterSpeed.get().intValue();
            case ELITE -> MekanismExtras.getConfig().cosmicLogisticalTransporterSpeed.get().intValue();
            case ULTIMATE -> MekanismExtras.getConfig().infiniteLogisticalTransporterSpeed.get().intValue();
        };
    }

    public static int getPullAmount(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> MekanismExtras.getConfig().absoluteLogisticalTransporterPullAmount.get().intValue();
            case ADVANCED -> MekanismExtras.getConfig().supremeLogisticalTransporterPullAmount.get().intValue();
            case ELITE -> MekanismExtras.getConfig().cosmicLogisticalTransporterPullAmount.get().intValue();
            case ULTIMATE -> MekanismExtras.getConfig().infiniteLogisticalTransporterPullAmount.get().intValue();
        };
    }
}
