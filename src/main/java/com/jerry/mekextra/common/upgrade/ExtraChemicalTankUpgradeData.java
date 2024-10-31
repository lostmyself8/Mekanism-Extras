package com.jerry.mekextra.common.upgrade;

import com.jerry.mekextra.common.tile.ExtraTileEntityChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.tile.component.ITileComponent;
import mekanism.common.tile.interfaces.IRedstoneControl;
import mekanism.common.upgrade.IUpgradeData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

import java.util.List;

public class ExtraChemicalTankUpgradeData implements IUpgradeData {
    public final boolean redstone;
    public final IRedstoneControl.RedstoneControl controlType;
    public final ChemicalInventorySlot drainSlot;
    public final ChemicalInventorySlot fillSlot;
    public final ExtraTileEntityChemicalTank.GasMode dumping;
    public final ChemicalStack storedChemical;
    public final CompoundTag components;

    public ExtraChemicalTankUpgradeData(HolderLookup.Provider provider, boolean redstone, IRedstoneControl.RedstoneControl controlType, ChemicalInventorySlot drainSlot, ChemicalInventorySlot fillSlot, ExtraTileEntityChemicalTank.GasMode dumping, ChemicalStack storedChemical, List<ITileComponent> components) {
        this.redstone = redstone;
        this.controlType = controlType;
        this.drainSlot = drainSlot;
        this.fillSlot = fillSlot;
        this.dumping = dumping;
        this.storedChemical = storedChemical;
        this.components = new CompoundTag();
        for (ITileComponent component : components) {
            component.write(this.components, provider);
        }
    }
}
