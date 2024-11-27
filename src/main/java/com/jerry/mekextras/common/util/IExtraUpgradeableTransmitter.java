package com.jerry.mekextras.common.util;

import com.jerry.mekextras.api.tier.IAdvanceTier;
import mekanism.api.tier.ITier;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import org.jetbrains.annotations.NotNull;

public interface IExtraUpgradeableTransmitter<DATA extends TransmitterUpgradeData> {
    DATA getUpgradeData();

    boolean dataTypeMatches(@NotNull TransmitterUpgradeData data);

    void parseUpgradeData(@NotNull DATA data);

    ITier getTier();

    default <TIER extends IAdvanceTier> boolean canUpgrade(TIER alloyTier) {
        return alloyTier.getAdvanceTier().ordinal() == getTier().getBaseTier().ordinal() + 1;
    }
}
