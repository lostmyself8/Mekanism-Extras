package com.jerry.mekanism_extras.common.block.storage.bin;

import mekanism.common.upgrade.IUpgradeData;

public record ExtraBinUpgradeData(boolean redstone, ExtraBinInventorySlot binSlot) implements IUpgradeData {
}
