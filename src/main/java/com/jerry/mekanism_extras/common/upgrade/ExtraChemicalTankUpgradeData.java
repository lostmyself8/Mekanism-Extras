package com.jerry.mekanism_extras.common.upgrade;

import com.jerry.mekanism_extras.common.tile.ExtraTileEntityChemicalTank;

import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.infuse.InfusionStack;
import mekanism.api.chemical.merged.MergedChemicalTank;
import mekanism.api.chemical.pigment.PigmentStack;
import mekanism.api.chemical.slurry.SlurryStack;
import mekanism.common.inventory.slot.chemical.MergedChemicalInventorySlot;
import mekanism.common.tile.component.ITileComponent;
import mekanism.common.tile.interfaces.IRedstoneControl;
import mekanism.common.upgrade.IUpgradeData;

import net.minecraft.nbt.CompoundTag;

import java.util.List;

public class ExtraChemicalTankUpgradeData implements IUpgradeData {

    public final boolean redstone;
    public final IRedstoneControl.RedstoneControl controlType;
    public final MergedChemicalInventorySlot<MergedChemicalTank> drainSlot;
    public final MergedChemicalInventorySlot<MergedChemicalTank> fillSlot;
    public final ExtraTileEntityChemicalTank.GasMode dumping;
    public final GasStack storedGas;
    public final InfusionStack storedInfusion;
    public final PigmentStack storedPigment;
    public final SlurryStack storedSlurry;
    public final CompoundTag components;

    public ExtraChemicalTankUpgradeData(boolean redstone, IRedstoneControl.RedstoneControl controlType, MergedChemicalInventorySlot<MergedChemicalTank> drainSlot,
                                        MergedChemicalInventorySlot<MergedChemicalTank> fillSlot, ExtraTileEntityChemicalTank.GasMode dumping, GasStack storedGas, InfusionStack storedInfusion, PigmentStack storedPigment,
                                        SlurryStack storedSlurry, List<ITileComponent> components) {
        this.redstone = redstone;
        this.controlType = controlType;
        this.drainSlot = drainSlot;
        this.fillSlot = fillSlot;
        this.dumping = dumping;
        this.storedGas = storedGas;
        this.storedInfusion = storedInfusion;
        this.storedPigment = storedPigment;
        this.storedSlurry = storedSlurry;
        this.components = new CompoundTag();
        for (ITileComponent component : components) {
            component.write(this.components);
        }
    }
}
