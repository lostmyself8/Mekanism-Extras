package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraItemToMergedFactory.class)
public class TileEntityExtraItemToMergedFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraItemToMergedFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class<?>[] TYPES_int = new Class[] { Integer.TYPE };

    public TileEntityExtraItemToMergedFactory$ComputerHandler() {
        register(MethodData.builder("getInput", TileEntityExtraItemToMergedFactory$ComputerHandler::getInput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraItemToMergedFactory$ComputerHandler::outputTank$getOutput).returnType(ChemicalStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputCapacity", TileEntityExtraItemToMergedFactory$ComputerHandler::outputTank$getOutputCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputNeeded", TileEntityExtraItemToMergedFactory$ComputerHandler::outputTank$getOutputNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputFilledPercentage", TileEntityExtraItemToMergedFactory$ComputerHandler::outputTank$getOutputFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the output.").arguments(NAMES_process, TYPES_int));
    }

    public static Object getInput_1(TileEntityExtraItemToMergedFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object outputTank$getOutput(TileEntityExtraItemToMergedFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getStack(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputCapacity(TileEntityExtraItemToMergedFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputNeeded(TileEntityExtraItemToMergedFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputFilledPercentage(TileEntityExtraItemToMergedFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getOutputTank(helper.getInt(0))));
    }
}
