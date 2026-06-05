package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base;

import mekanism.api.chemical.gas.GasStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraItemToGasFactory.class)
public class TileEntityExtraItemToGasFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraItemToGasFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class<?>[] TYPES_int = new Class[] { Integer.TYPE };

    public TileEntityExtraItemToGasFactory$ComputerHandler() {
        register(MethodData.builder("getInput", TileEntityExtraItemToGasFactory$ComputerHandler::getInput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraItemToGasFactory$ComputerHandler::getOutput_1).returnType(GasStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputCapacity", TileEntityExtraItemToGasFactory$ComputerHandler::outputTank$getOutputCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputNeeded", TileEntityExtraItemToGasFactory$ComputerHandler::outputTank$getOutputNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputFilledPercentage", TileEntityExtraItemToGasFactory$ComputerHandler::outputTank$getOutputFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the output.").arguments(NAMES_process, TYPES_int));
    }

    public static Object getInput_1(TileEntityExtraItemToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object getOutput_1(TileEntityExtraItemToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }

    public static Object outputTank$getOutputCapacity(TileEntityExtraItemToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputNeeded(TileEntityExtraItemToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputFilledPercentage(TileEntityExtraItemToGasFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getOutputTank(helper.getInt(0))));
    }
}
