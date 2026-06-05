package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base;

import mekanism.api.chemical.pigment.PigmentStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraItemToPigmentFactory.class)
public class TileEntityExtraItemToPigmentFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraItemToPigmentFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class<?>[] TYPES_int = new Class[] { Integer.TYPE };

    public TileEntityExtraItemToPigmentFactory$ComputerHandler() {
        register(MethodData.builder("getInput", TileEntityExtraItemToPigmentFactory$ComputerHandler::getInput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraItemToPigmentFactory$ComputerHandler::getOutput_1).returnType(PigmentStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputCapacity", TileEntityExtraItemToPigmentFactory$ComputerHandler::outputTank$getOutputCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputNeeded", TileEntityExtraItemToPigmentFactory$ComputerHandler::outputTank$getOutputNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the output.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutputFilledPercentage", TileEntityExtraItemToPigmentFactory$ComputerHandler::outputTank$getOutputFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the output.").arguments(NAMES_process, TYPES_int));
    }

    public static Object getInput_1(TileEntityExtraItemToPigmentFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object getOutput_1(TileEntityExtraItemToPigmentFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }

    public static Object outputTank$getOutputCapacity(TileEntityExtraItemToPigmentFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputNeeded(TileEntityExtraItemToPigmentFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getOutputTank(helper.getInt(0))));
    }

    public static Object outputTank$getOutputFilledPercentage(TileEntityExtraItemToPigmentFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getOutputTank(helper.getInt(0))));
    }
}
