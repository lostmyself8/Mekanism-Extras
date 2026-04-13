package com.jerry.mekextras.common.integration.mekaf.tile.factory;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerFluidTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

@MethodFactory(target = TileEntityExtraPRCFactory.class)
public class TileEntityExtraPRCFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraPRCFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class[] TYPES_int = new Class[] { int.class };

    public TileEntityExtraPRCFactory$ComputerHandler() {
        register(MethodData.builder("getInputFluid", TileEntityExtraPRCFactory$ComputerHandler::inputFluidTank$getInputFluid).returnType(FluidStack.class).methodDescription("Get the contents of the fluid input."));
        register(MethodData.builder("getInputFluidCapacity", TileEntityExtraPRCFactory$ComputerHandler::inputFluidTank$getInputFluidCapacity).returnType(int.class).methodDescription("Get the capacity of the fluid input."));
        register(MethodData.builder("getInputFluidNeeded", TileEntityExtraPRCFactory$ComputerHandler::inputFluidTank$getInputFluidNeeded).returnType(int.class).methodDescription("Get the amount needed to fill the fluid input."));
        register(MethodData.builder("getInputFluidFilledPercentage", TileEntityExtraPRCFactory$ComputerHandler::inputFluidTank$getInputFluidFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the fluid input."));
        register(MethodData.builder("getInputGas", TileEntityExtraPRCFactory$ComputerHandler::inputGasTank$getInputGas).returnType(ChemicalStack.class).methodDescription("Get the contents of the gas input."));
        register(MethodData.builder("getInputGasCapacity", TileEntityExtraPRCFactory$ComputerHandler::inputGasTank$getInputGasCapacity).returnType(long.class).methodDescription("Get the capacity of the gas input."));
        register(MethodData.builder("getInputGasNeeded", TileEntityExtraPRCFactory$ComputerHandler::inputGasTank$getInputGasNeeded).returnType(long.class).methodDescription("Get the amount needed to fill the gas input."));
        register(MethodData.builder("getInputGasFilledPercentage", TileEntityExtraPRCFactory$ComputerHandler::inputGasTank$getInputGasFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the gas input."));
        register(MethodData.builder("getOutputGas", TileEntityExtraPRCFactory$ComputerHandler::outputGasTank$getOutputGas).returnType(ChemicalStack.class).methodDescription("Get the contents of the gas output."));
        register(MethodData.builder("getOutputGasCapacity", TileEntityExtraPRCFactory$ComputerHandler::outputGasTank$getOutputGasCapacity).returnType(long.class).methodDescription("Get the capacity of the gas output."));
        register(MethodData.builder("getOutputGasNeeded", TileEntityExtraPRCFactory$ComputerHandler::outputGasTank$getOutputGasNeeded).returnType(long.class).methodDescription("Get the amount needed to fill the gas output."));
        register(MethodData.builder("getOutputGasFilledPercentage", TileEntityExtraPRCFactory$ComputerHandler::outputGasTank$getOutputGasFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the gas output."));
        register(MethodData.builder("getInput", TileEntityExtraPRCFactory$ComputerHandler::getInput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraPRCFactory$ComputerHandler::getOutput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
    }

    public static Object inputFluidTank$getInputFluid(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getStack(subject.inputFluidTank));
    }

    public static Object inputFluidTank$getInputFluidCapacity(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getCapacity(subject.inputFluidTank));
    }

    public static Object inputFluidTank$getInputFluidNeeded(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getNeeded(subject.inputFluidTank));
    }

    public static Object inputFluidTank$getInputFluidFilledPercentage(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getFilledPercentage(subject.inputFluidTank));
    }

    public static Object inputGasTank$getInputGas(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getStack(subject.inputChemicalTank));
    }

    public static Object inputGasTank$getInputGasCapacity(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.inputChemicalTank));
    }

    public static Object inputGasTank$getInputGasNeeded(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.inputChemicalTank));
    }

    public static Object inputGasTank$getInputGasFilledPercentage(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.inputChemicalTank));
    }

    public static Object outputGasTank$getOutputGas(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getStack(subject.outputChemicalTank));
    }

    public static Object outputGasTank$getOutputGasCapacity(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.outputChemicalTank));
    }

    public static Object outputGasTank$getOutputGasNeeded(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.outputChemicalTank));
    }

    public static Object outputGasTank$getOutputGasFilledPercentage(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.outputChemicalTank));
    }

    public static Object getInput_1(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object getOutput_1(TileEntityExtraPRCFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }
}
