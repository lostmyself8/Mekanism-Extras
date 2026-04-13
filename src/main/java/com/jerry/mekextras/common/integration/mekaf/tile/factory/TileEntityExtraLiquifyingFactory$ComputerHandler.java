package com.jerry.mekextras.common.integration.mekaf.tile.factory;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerFluidTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

@MethodFactory(target = TileEntityExtraLiquifyingFactory.class)
public class TileEntityExtraLiquifyingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraLiquifyingFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class[] TYPES_int = new Class[] { int.class };

    public TileEntityExtraLiquifyingFactory$ComputerHandler() {
        register(MethodData.builder("getFluidOutput", TileEntityExtraLiquifyingFactory$ComputerHandler::fluidTank$getOutput).returnType(FluidStack.class).methodDescription("Get the contents of the output tank."));
        register(MethodData.builder("getFluidOutputCapacity", TileEntityExtraLiquifyingFactory$ComputerHandler::fluidTank$getOutputCapacity).returnType(int.class).methodDescription("Get the capacity of the output tank."));
        register(MethodData.builder("getFluidOutputNeeded", TileEntityExtraLiquifyingFactory$ComputerHandler::fluidTank$getOutputNeeded).returnType(int.class).methodDescription("Get the amount needed to fill the output tank."));
        register(MethodData.builder("getFluidOutputFilledPercentage", TileEntityExtraLiquifyingFactory$ComputerHandler::fluidTank$getOutputFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the output tank."));
        register(MethodData.builder("getInput", TileEntityExtraLiquifyingFactory$ComputerHandler::getInput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraLiquifyingFactory$ComputerHandler::getOutput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
    }

    public static Object fluidTank$getOutput(TileEntityExtraLiquifyingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getStack(subject.fluidTank));
    }

    public static Object fluidTank$getOutputCapacity(TileEntityExtraLiquifyingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getCapacity(subject.fluidTank));
    }

    public static Object fluidTank$getOutputNeeded(TileEntityExtraLiquifyingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getNeeded(subject.fluidTank));
    }

    public static Object fluidTank$getOutputFilledPercentage(TileEntityExtraLiquifyingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getFilledPercentage(subject.fluidTank));
    }

    public static Object getInput_1(TileEntityExtraLiquifyingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object getOutput_1(TileEntityExtraLiquifyingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }
}
