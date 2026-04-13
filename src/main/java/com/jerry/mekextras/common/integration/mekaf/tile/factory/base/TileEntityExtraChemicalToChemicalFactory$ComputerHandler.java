package com.jerry.mekextras.common.integration.mekaf.tile.factory.base;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(target = TileEntityExtraChemicalToChemicalFactory.class)
public class TileEntityExtraChemicalToChemicalFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraChemicalToChemicalFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class[] TYPES_int = new Class[] { int.class };

    public TileEntityExtraChemicalToChemicalFactory$ComputerHandler() {
        register(MethodData.builder("getInput", TileEntityExtraChemicalToChemicalFactory$ComputerHandler::getInput_1).returnType(ChemicalStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputCapacity", TileEntityExtraChemicalToChemicalFactory$ComputerHandler::inputTank$getInputCapacity).returnType(int.class).methodDescription("Get the capacity of the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputNeeded", TileEntityExtraChemicalToChemicalFactory$ComputerHandler::inputTank$getInputNeeded).returnType(int.class).methodDescription("Get the amount needed to fill the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputFilledPercentage", TileEntityExtraChemicalToChemicalFactory$ComputerHandler::inputTank$getInputFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraChemicalToChemicalFactory$ComputerHandler::getOutput_1).returnType(ChemicalStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputCapacity", TileEntityExtraChemicalToChemicalFactory$ComputerHandler::outputTank$getOutputCapacity).returnType(long.class).methodDescription("Get the capacity of the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputNeeded", TileEntityExtraChemicalToChemicalFactory$ComputerHandler::outputTank$getOutputNeeded).returnType(long.class).methodDescription("Get the amount needed to fill the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputFilledPercentage", TileEntityExtraChemicalToChemicalFactory$ComputerHandler::outputTank$getOutputFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the output.").arguments(NAMES_process, TYPES_int));
    }

    public static Object getInput_1(TileEntityExtraChemicalToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object inputTank$getInputCapacity(TileEntityExtraChemicalToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getInputTank(helper.getInt(0))));
    }

    public static Object inputTank$getInputNeeded(TileEntityExtraChemicalToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getInputTank(helper.getInt(0))));
    }

    public static Object inputTank$getInputFilledPercentage(TileEntityExtraChemicalToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getInputTank(helper.getInt(0))));
    }

    public static Object getOutput_1(TileEntityExtraChemicalToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }

    public static Object outputTank$getOutputCapacity(TileEntityExtraChemicalToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputNeeded(TileEntityExtraChemicalToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputFilledPercentage(TileEntityExtraChemicalToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getOutputTank(helper.getInt(0))));
    }
}
