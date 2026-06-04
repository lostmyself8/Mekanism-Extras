package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base;

import mekanism.api.chemical.gas.GasStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(target = TileEntityExtraGasToGasFactory.class)
public class TileEntityExtraGasToGasFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraGasToGasFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class<?>[] TYPES_int = new Class[] { Integer.TYPE };

    public TileEntityExtraGasToGasFactory$ComputerHandler() {
        register(MethodData.builder("getInput", TileEntityExtraGasToGasFactory$ComputerHandler::getInput_1).returnType(GasStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputCapacity", TileEntityExtraGasToGasFactory$ComputerHandler::inputTank$getInputCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputNeeded", TileEntityExtraGasToGasFactory$ComputerHandler::inputTank$getInputNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputFilledPercentage", TileEntityExtraGasToGasFactory$ComputerHandler::inputTank$getInputFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraGasToGasFactory$ComputerHandler::getOutput_1).returnType(GasStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputCapacity", TileEntityExtraGasToGasFactory$ComputerHandler::outputTank$getOutputCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputNeeded", TileEntityExtraGasToGasFactory$ComputerHandler::outputTank$getOutputNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputFilledPercentage", TileEntityExtraGasToGasFactory$ComputerHandler::outputTank$getOutputFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the output.").arguments(NAMES_process, TYPES_int));
    }

    public static Object getInput_1(TileEntityExtraGasToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object inputTank$getInputCapacity(TileEntityExtraGasToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getInputTank(helper.getInt(0))));
    }

    public static Object inputTank$getInputNeeded(TileEntityExtraGasToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getInputTank(helper.getInt(0))));
    }

    public static Object inputTank$getInputFilledPercentage(TileEntityExtraGasToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getInputTank(helper.getInt(0))));
    }

    public static Object getOutput_1(TileEntityExtraGasToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }

    public static Object outputTank$getOutputCapacity(TileEntityExtraGasToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputNeeded(TileEntityExtraGasToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputFilledPercentage(TileEntityExtraGasToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getOutputTank(helper.getInt(0))));
    }
}
