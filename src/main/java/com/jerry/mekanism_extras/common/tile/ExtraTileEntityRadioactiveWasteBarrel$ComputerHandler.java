package com.jerry.mekanism_extras.common.tile;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(target = ExtraTileEntityRadioactiveWasteBarrel.class)
public class ExtraTileEntityRadioactiveWasteBarrel$ComputerHandler extends ComputerMethodFactory<ExtraTileEntityRadioactiveWasteBarrel> {

    public ExtraTileEntityRadioactiveWasteBarrel$ComputerHandler() {
        register(MethodData.builder("getStored", ExtraTileEntityRadioactiveWasteBarrel$ComputerHandler::gasTank$getStored).returnType(ChemicalStack.class).methodDescription("Get the contents of the barrel."));
        register(MethodData.builder("getCapacity", ExtraTileEntityRadioactiveWasteBarrel$ComputerHandler::gasTank$getCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the barrel."));
        register(MethodData.builder("getNeeded", ExtraTileEntityRadioactiveWasteBarrel$ComputerHandler::gasTank$getNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the barrel."));
        register(MethodData.builder("getFilledPercentage", ExtraTileEntityRadioactiveWasteBarrel$ComputerHandler::gasTank$getFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the barrel."));
    }

    public static Object gasTank$getStored(ExtraTileEntityRadioactiveWasteBarrel subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.gasTank));
    }

    public static Object gasTank$getCapacity(ExtraTileEntityRadioactiveWasteBarrel subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.gasTank));
    }

    public static Object gasTank$getNeeded(ExtraTileEntityRadioactiveWasteBarrel subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.gasTank));
    }

    public static Object gasTank$getFilledPercentage(ExtraTileEntityRadioactiveWasteBarrel subject,
                                                          BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.gasTank));
    }
}
