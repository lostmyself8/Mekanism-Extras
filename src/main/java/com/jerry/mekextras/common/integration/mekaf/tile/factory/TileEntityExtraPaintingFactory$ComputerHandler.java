package com.jerry.mekextras.common.integration.mekaf.tile.factory;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraPaintingFactory.class)
public class TileEntityExtraPaintingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraPaintingFactory> {

    public TileEntityExtraPaintingFactory$ComputerHandler() {
        register(MethodData.builder("getChemicalInput", TileEntityExtraPaintingFactory$ComputerHandler::pigmentTank$getChemicalInput).returnType(ChemicalStack.class).methodDescription("Get the contents of the chemical tank."));
        register(MethodData.builder("getChemicalInputCapacity", TileEntityExtraPaintingFactory$ComputerHandler::pigmentTank$getChemicalInputCapacity).returnType(long.class).methodDescription("Get the capacity of the chemical tank."));
        register(MethodData.builder("getChemicalInputNeeded", TileEntityExtraPaintingFactory$ComputerHandler::pigmentTank$getChemicalInputNeeded).returnType(long.class).methodDescription("Get the amount needed to fill the chemical tank."));
        register(MethodData.builder("getChemicalInputFilledPercentage", TileEntityExtraPaintingFactory$ComputerHandler::pigmentTank$getChemicalInputFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the chemical tank."));
        register(MethodData.builder("getInputChemicalItem", TileEntityExtraPaintingFactory$ComputerHandler::pigmentInputSlot$getInputChemicalItem).returnType(ItemStack.class).methodDescription("Get the contents of the chemical slot."));
    }

    public static Object pigmentTank$getChemicalInput(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getStack(subject.chemicalTank));
    }

    public static Object pigmentTank$getChemicalInputCapacity(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.chemicalTank));
    }

    public static Object pigmentTank$getChemicalInputNeeded(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.chemicalTank));
    }

    public static Object pigmentTank$getChemicalInputFilledPercentage(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.chemicalTank));
    }

    public static Object pigmentInputSlot$getInputChemicalItem(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerIInventorySlotWrapper.getStack(subject.chemicalInputSlot));
    }
}
