package com.jerry.mekanism_extras.common.content.network.transmitter;

import com.jerry.mekanism_extras.api.tier.ExtraAlloyTier;

import mekanism.api.tier.ITier;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;

import org.jetbrains.annotations.NotNull;

public interface IExtraUpgradeableTransmitter<DATA extends TransmitterUpgradeData> {

    DATA getUpgradeData();

    boolean dataTypeMatches(@NotNull TransmitterUpgradeData data);

    void parseUpgradeData(@NotNull DATA data);

    ITier getTier();

    default boolean canUpgrade(ExtraAlloyTier alloyTier) {
        return alloyTier.getAdvanceTier().ordinal() == getTier().getBaseTier().ordinal() + 1;
    }
}
