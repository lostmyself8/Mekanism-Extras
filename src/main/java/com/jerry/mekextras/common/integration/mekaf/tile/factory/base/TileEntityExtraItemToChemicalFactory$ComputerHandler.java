package com.jerry.mekextras.common.integration.mekaf.tile.factory.base;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraItemToChemicalFactory.class)
public class TileEntityExtraItemToChemicalFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraItemToChemicalFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class[] TYPES_int = new Class[] { int.class };

    public TileEntityExtraItemToChemicalFactory$ComputerHandler() {
        register(MethodData.builder("getInput", TileEntityExtraItemToChemicalFactory$ComputerHandler::getInput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraItemToChemicalFactory$ComputerHandler::getOutput_1).returnType(ChemicalStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputCapacity", TileEntityExtraItemToChemicalFactory$ComputerHandler::outputTank$getOutputCapacity).returnType(long.class).methodDescription("Get the capacity of the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputNeeded", TileEntityExtraItemToChemicalFactory$ComputerHandler::outputTank$getOutputNeeded).returnType(long.class).methodDescription("Get the amount needed to fill the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputFilledPercentage", TileEntityExtraItemToChemicalFactory$ComputerHandler::outputTank$getOutputFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the output.").arguments(NAMES_process, TYPES_int));
    }

    public static Object getInput_1(TileEntityExtraItemToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object getOutput_1(TileEntityExtraItemToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }

    public static Object outputTank$getOutputCapacity(TileEntityExtraItemToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputNeeded(TileEntityExtraItemToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputFilledPercentage(TileEntityExtraItemToChemicalFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getOutputTank(helper.getInt(0))));
    }
}
