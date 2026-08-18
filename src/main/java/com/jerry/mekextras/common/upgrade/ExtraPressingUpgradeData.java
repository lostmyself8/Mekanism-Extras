package com.jerry.mekextras.common.upgrade;

import mekanism.api.energy.IEnergyContainer;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import mekanism.common.tile.component.ITileComponent;
import mekanism.common.tile.interfaces.IRedstoneControl.RedstoneControl;
import mekanism.common.upgrade.MachineUpgradeData;

import net.minecraft.core.HolderLookup;

import java.util.List;

public class ExtraPressingUpgradeData extends MachineUpgradeData {

    public final InputInventorySlot secondarySlot;
    public final InputInventorySlot tertiarySlot;

    public ExtraPressingUpgradeData(HolderLookup.Provider provider, boolean redstone, RedstoneControl controlType, IEnergyContainer energyContainer, int[] progress,
                                    EnergyInventorySlot energySlot, InputInventorySlot secondarySlot, InputInventorySlot tertiarySlot, List<IInventorySlot> inputSlots,
                                    List<IInventorySlot> outputSlots, boolean sorting, List<ITileComponent> components) {
        super(provider, redstone, controlType, energyContainer, progress, energySlot, inputSlots, outputSlots, sorting, components);
        this.secondarySlot = secondarySlot;
        this.tertiarySlot = tertiarySlot;
    }
}
