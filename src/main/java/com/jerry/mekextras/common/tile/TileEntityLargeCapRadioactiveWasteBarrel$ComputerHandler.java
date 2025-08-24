package com.jerry.mekextras.common.tile;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(target = TileEntityLargeCapRadioactiveWasteBarrel.class)
public class TileEntityLargeCapRadioactiveWasteBarrel$ComputerHandler extends ComputerMethodFactory<TileEntityLargeCapRadioactiveWasteBarrel> {

    public TileEntityLargeCapRadioactiveWasteBarrel$ComputerHandler() {
        register(MethodData.builder("getStored", TileEntityLargeCapRadioactiveWasteBarrel$ComputerHandler::chemicalTank$getStored).returnType(ChemicalStack.class).methodDescription("Get the contents of the barrel."));
        register(MethodData.builder("getCapacity", TileEntityLargeCapRadioactiveWasteBarrel$ComputerHandler::chemicalTank$getCapacity).returnType(long.class).methodDescription("Get the capacity of the barrel."));
        register(MethodData.builder("getNeeded", TileEntityLargeCapRadioactiveWasteBarrel$ComputerHandler::chemicalTank$getNeeded).returnType(long.class).methodDescription("Get the amount needed to fill the barrel."));
        register(MethodData.builder("getFilledPercentage", TileEntityLargeCapRadioactiveWasteBarrel$ComputerHandler::chemicalTank$getFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the barrel."));
    }

    public static Object chemicalTank$getStored(TileEntityLargeCapRadioactiveWasteBarrel subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.chemicalTank));
    }

    public static Object chemicalTank$getCapacity(TileEntityLargeCapRadioactiveWasteBarrel subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.chemicalTank));
    }

    public static Object chemicalTank$getNeeded(TileEntityLargeCapRadioactiveWasteBarrel subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.chemicalTank));
    }

    public static Object chemicalTank$getFilledPercentage(TileEntityLargeCapRadioactiveWasteBarrel subject,
                                                          BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.chemicalTank));
    }
}
