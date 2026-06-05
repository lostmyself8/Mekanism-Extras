package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraMergedToItemFactory.class)
public class TileEntityExtraMergedToItemFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraMergedToItemFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class<?>[] TYPES_int = new Class[] { Integer.TYPE };

    public TileEntityExtraMergedToItemFactory$ComputerHandler() {
        register(MethodData.builder("getInput", TileEntityExtraMergedToItemFactory$ComputerHandler::inputTank$getInput).returnType(ChemicalStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputCapacity", TileEntityExtraMergedToItemFactory$ComputerHandler::inputTank$getInputCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputNeeded", TileEntityExtraMergedToItemFactory$ComputerHandler::inputTank$getInputNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getInputFilledPercentage", TileEntityExtraMergedToItemFactory$ComputerHandler::inputTank$getInputFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the input.").arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraMergedToItemFactory$ComputerHandler::getOutput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
    }

    public static Object inputTank$getInput(TileEntityExtraMergedToItemFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getStack(subject.getInputTank(helper.getInt(0))));
    }

    public static Object inputTank$getInputCapacity(TileEntityExtraMergedToItemFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.getInputTank(helper.getInt(0))));
    }

    public static Object inputTank$getInputNeeded(TileEntityExtraMergedToItemFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.getInputTank(helper.getInt(0))));
    }

    public static Object inputTank$getInputFilledPercentage(TileEntityExtraMergedToItemFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.getInputTank(helper.getInt(0))));
    }

    public static Object getOutput_1(TileEntityExtraMergedToItemFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }
}
