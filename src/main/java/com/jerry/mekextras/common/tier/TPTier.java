package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.common.config.ExtraConfig;
import mekanism.common.tier.TransporterTier;

public class TPTier {
    public static int getSpeed(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteLogisticalTransporterSpeed.get().intValue();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeLogisticalTransporterSpeed.get().intValue();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicLogisticalTransporterSpeed.get().intValue();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteLogisticalTransporterSpeed.get().intValue();
        };
    }

    public static int getPullAmount(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> ExtraConfig.extraTierConfig.absoluteLogisticalTransporterPullAmount.get().intValue();
            case ADVANCED -> ExtraConfig.extraTierConfig.supremeLogisticalTransporterPullAmount.get().intValue();
            case ELITE -> ExtraConfig.extraTierConfig.cosmicLogisticalTransporterPullAmount.get().intValue();
            case ULTIMATE -> ExtraConfig.extraTierConfig.infiniteLogisticalTransporterPullAmount.get().intValue();
        };
    }
}
