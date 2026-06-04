package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base;

import mekanism.api.chemical.slurry.SlurryStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(target = TileEntityExtraSlurryToSlurryFactory.class)
public class TileEntityExtraSlurryToSlurryFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraSlurryToSlurryFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class<?>[] TYPES_int = new Class[] { Integer.TYPE };

    public TileEntityExtraSlurryToSlurryFactory$ComputerHandler() {
        register(MethodData.builder("getInput", TileEntityExtraSlurryToSlurryFactory$ComputerHandler::getInput_1).returnType(SlurryStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputCapacity", TileEntityExtraSlurryToSlurryFactory$ComputerHandler::inputTank$getInputCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputNeeded", TileEntityExtraSlurryToSlurryFactory$ComputerHandler::inputTank$getInputNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputFilledPercentage", TileEntityExtraSlurryToSlurryFactory$ComputerHandler::inputTank$getInputFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraSlurryToSlurryFactory$ComputerHandler::getOutput_1).returnType(SlurryStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputCapacity", TileEntityExtraSlurryToSlurryFactory$ComputerHandler::outputTank$getOutputCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputNeeded", TileEntityExtraSlurryToSlurryFactory$ComputerHandler::outputTank$getOutputNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputFilledPercentage", TileEntityExtraSlurryToSlurryFactory$ComputerHandler::outputTank$getOutputFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the output.").arguments(NAMES_process, TYPES_int));
    }

    public static Object getInput_1(TileEntityExtraSlurryToSlurryFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object inputTank$getInputCapacity(TileEntityExtraSlurryToSlurryFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getInputTank(helper.getInt(0))));
    }

    public static Object inputTank$getInputNeeded(TileEntityExtraSlurryToSlurryFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getInputTank(helper.getInt(0))));
    }

    public static Object inputTank$getInputFilledPercentage(TileEntityExtraSlurryToSlurryFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getInputTank(helper.getInt(0))));
    }

    public static Object getOutput_1(TileEntityExtraSlurryToSlurryFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }

    public static Object outputTank$getOutputCapacity(TileEntityExtraSlurryToSlurryFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputNeeded(TileEntityExtraSlurryToSlurryFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputFilledPercentage(TileEntityExtraSlurryToSlurryFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getOutputTank(helper.getInt(0))));
    }
}
