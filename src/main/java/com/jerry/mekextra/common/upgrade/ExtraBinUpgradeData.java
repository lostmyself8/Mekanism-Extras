package com.jerry.mekextra.common.upgrade;


import com.jerry.mekextra.common.inventory.slot.ExtraBinInventorySlot;
import mekanism.common.upgrade.IUpgradeData;

public record ExtraBinUpgradeData(boolean redstone, ExtraBinInventorySlot binSlot) implements IUpgradeData {
}
