package com.jerry.mekextra.common.tier.transmitter;

import com.jerry.mekextra.common.config.ExtraConfig;
import mekanism.common.tier.TransporterTier;

public class TPTier {
    public static int getSpeed(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> (int) ExtraConfig.extraTierConfig.absoluteLogisticalTransporterSpeed.get();
            case ADVANCED -> (int) ExtraConfig.extraTierConfig.supremeLogisticalTransporterSpeed.get();
            case ELITE -> (int) ExtraConfig.extraTierConfig.cosmicLogisticalTransporterSpeed.get();
            case ULTIMATE -> (int) ExtraConfig.extraTierConfig.infiniteLogisticalTransporterSpeed.get();
        };
    }

    public static int getPullAmount(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> (int) ExtraConfig.extraTierConfig.absoluteLogisticalTransporterPullAmount.get();
            case ADVANCED -> (int) ExtraConfig.extraTierConfig.supremeLogisticalTransporterPullAmount.get();
            case ELITE -> (int) ExtraConfig.extraTierConfig.cosmicLogisticalTransporterPullAmount.get();
            case ULTIMATE -> (int) ExtraConfig.extraTierConfig.infiniteLogisticalTransporterPullAmount.get();
        };
    }
}
