package com.jerry.mekanism_extras.common.block.transmitter.logisticaltransporter;

import com.jerry.mekanism_extras.common.config.LoadConfig;
import mekanism.common.tier.TransporterTier;

public class TPTier {
    public static int getSpeed(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.extraConfig.absoluteLogisticalTransporterSpeed.get().intValue();
            case ADVANCED -> LoadConfig.extraConfig.supremeLogisticalTransporterSpeed.get().intValue();
            case ELITE -> LoadConfig.extraConfig.cosmicLogisticalTransporterSpeed.get().intValue();
            case ULTIMATE -> LoadConfig.extraConfig.infiniteLogisticalTransporterSpeed.get().intValue();
        };
    }

    public static int getPullAmount(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.extraConfig.absoluteLogisticalTransporterPullAmount.get().intValue();
            case ADVANCED -> LoadConfig.extraConfig.supremeLogisticalTransporterPullAmount.get().intValue();
            case ELITE -> LoadConfig.extraConfig.cosmicLogisticalTransporterPullAmount.get().intValue();
            case ULTIMATE -> LoadConfig.extraConfig.infiniteLogisticalTransporterPullAmount.get().intValue();
        };
    }
}
