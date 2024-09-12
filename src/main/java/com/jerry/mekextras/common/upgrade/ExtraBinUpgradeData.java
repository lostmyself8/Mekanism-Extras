package com.jerry.mekextras.common.upgrade;


import com.jerry.mekextras.common.inventory.slot.ExtraBinInventorySlot;
import mekanism.common.upgrade.IUpgradeData;

public record ExtraBinUpgradeData(boolean redstone, ExtraBinInventorySlot binSlot) implements IUpgradeData {
}
