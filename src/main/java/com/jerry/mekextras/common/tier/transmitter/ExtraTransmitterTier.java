package com.jerry.mekextras.common.tier.transmitter;

import com.jerry.mekextras.api.tier.AdvancedTier;

import mekanism.common.tier.CableTier;
import mekanism.common.tier.ConductorTier;
import mekanism.common.tier.PipeTier;
import mekanism.common.tier.TransporterTier;
import mekanism.common.tier.TubeTier;

public final class ExtraTransmitterTier {

    private ExtraTransmitterTier() {}

    public static AdvancedTier getAdvancedTier(CableTier tier) {
        return switch (tier) {
            case BASIC -> AdvancedTier.ABSOLUTE;
            case ADVANCED -> AdvancedTier.SUPREME;
            case ELITE -> AdvancedTier.COSMIC;
            case ULTIMATE -> AdvancedTier.INFINITE;
        };
    }

    public static AdvancedTier getAdvancedTier(PipeTier tier) {
        return switch (tier) {
            case BASIC -> AdvancedTier.ABSOLUTE;
            case ADVANCED -> AdvancedTier.SUPREME;
            case ELITE -> AdvancedTier.COSMIC;
            case ULTIMATE -> AdvancedTier.INFINITE;
        };
    }

    public static AdvancedTier getAdvancedTier(TubeTier tier) {
        return switch (tier) {
            case BASIC -> AdvancedTier.ABSOLUTE;
            case ADVANCED -> AdvancedTier.SUPREME;
            case ELITE -> AdvancedTier.COSMIC;
            case ULTIMATE -> AdvancedTier.INFINITE;
        };
    }

    public static AdvancedTier getAdvancedTier(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> AdvancedTier.ABSOLUTE;
            case ADVANCED -> AdvancedTier.SUPREME;
            case ELITE -> AdvancedTier.COSMIC;
            case ULTIMATE -> AdvancedTier.INFINITE;
        };
    }

    public static AdvancedTier getAdvancedTier(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> AdvancedTier.ABSOLUTE;
            case ADVANCED -> AdvancedTier.SUPREME;
            case ELITE -> AdvancedTier.COSMIC;
            case ULTIMATE -> AdvancedTier.INFINITE;
        };
    }
}
