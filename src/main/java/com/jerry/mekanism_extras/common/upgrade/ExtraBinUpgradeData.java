package com.jerry.mekanism_extras.common.upgrade;

import com.jerry.mekanism_extras.common.inventory.slot.ExtraBinInventorySlot;

import mekanism.common.upgrade.IUpgradeData;

public record ExtraBinUpgradeData(boolean redstone, ExtraBinInventorySlot binSlot) implements IUpgradeData {}
