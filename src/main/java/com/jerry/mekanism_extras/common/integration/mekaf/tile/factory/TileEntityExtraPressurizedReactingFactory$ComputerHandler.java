package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory;

import mekanism.api.chemical.gas.GasStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerFluidTankWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

@MethodFactory(target = TileEntityExtraPressurizedReactingFactory.class)
public class TileEntityExtraPressurizedReactingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraPressurizedReactingFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class<?>[] TYPES_int = new Class[] { Integer.TYPE };

    public TileEntityExtraPressurizedReactingFactory$ComputerHandler() {
        register(MethodData.builder("getInputFluid", TileEntityExtraPressurizedReactingFactory$ComputerHandler::inputFluidTank$getInputFluid).returnType(FluidStack.class).methodDescription("Get the contents of the fluid input."));
        register(MethodData.builder("getInputFluidCapacity", TileEntityExtraPressurizedReactingFactory$ComputerHandler::inputFluidTank$getInputFluidCapacity).returnType(Integer.TYPE).methodDescription("Get the capacity of the fluid input."));
        register(MethodData.builder("getInputFluidNeeded", TileEntityExtraPressurizedReactingFactory$ComputerHandler::inputFluidTank$getInputFluidNeeded).returnType(Integer.TYPE).methodDescription("Get the amount needed to fill the fluid input."));
        register(MethodData.builder("getInputFluidFilledPercentage", TileEntityExtraPressurizedReactingFactory$ComputerHandler::inputFluidTank$getInputFluidFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the fluid input."));
        register(MethodData.builder("getInputGas", TileEntityExtraPressurizedReactingFactory$ComputerHandler::inputGasTank$getInputGas).returnType(GasStack.class).methodDescription("Get the contents of the gas input."));
        register(MethodData.builder("getInputGasCapacity", TileEntityExtraPressurizedReactingFactory$ComputerHandler::inputGasTank$getInputGasCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the gas input."));
        register(MethodData.builder("getInputGasNeeded", TileEntityExtraPressurizedReactingFactory$ComputerHandler::inputGasTank$getInputGasNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the gas input."));
        register(MethodData.builder("getInputGasFilledPercentage", TileEntityExtraPressurizedReactingFactory$ComputerHandler::inputGasTank$getInputGasFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the gas input."));
        register(MethodData.builder("getOutputGas", TileEntityExtraPressurizedReactingFactory$ComputerHandler::outputGasTank$getOutputGas).returnType(GasStack.class).methodDescription("Get the contents of the gas output."));
        register(MethodData.builder("getOutputGasCapacity", TileEntityExtraPressurizedReactingFactory$ComputerHandler::outputGasTank$getOutputGasCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the gas output."));
        register(MethodData.builder("getOutputGasNeeded", TileEntityExtraPressurizedReactingFactory$ComputerHandler::outputGasTank$getOutputGasNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the gas output."));
        register(MethodData.builder("getOutputGasFilledPercentage", TileEntityExtraPressurizedReactingFactory$ComputerHandler::outputGasTank$getOutputGasFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the gas output."));
        register(MethodData.builder("getInput", TileEntityExtraPressurizedReactingFactory$ComputerHandler::getInput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraPressurizedReactingFactory$ComputerHandler::getOutput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("dumpInputs", TileEntityExtraPressurizedReactingFactory$ComputerHandler::dumpInputs_0).methodDescription("Empty the contents of the fluid and gas inputs into the environment").requiresPublicSecurity());
    }

    public static Object inputFluidTank$getInputFluid(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getStack(subject.inputFluidTank));
    }

    public static Object inputFluidTank$getInputFluidCapacity(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getCapacity(subject.inputFluidTank));
    }

    public static Object inputFluidTank$getInputFluidNeeded(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getNeeded(subject.inputFluidTank));
    }

    public static Object inputFluidTank$getInputFluidFilledPercentage(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getFilledPercentage(subject.inputFluidTank));
    }

    public static Object inputGasTank$getInputGas(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getStack(subject.inputGasTank));
    }

    public static Object inputGasTank$getInputGasCapacity(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.inputGasTank));
    }

    public static Object inputGasTank$getInputGasNeeded(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.inputGasTank));
    }

    public static Object inputGasTank$getInputGasFilledPercentage(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.inputGasTank));
    }

    public static Object outputGasTank$getOutputGas(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getStack(subject.outputGasTank));
    }

    public static Object outputGasTank$getOutputGasCapacity(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.outputGasTank));
    }

    public static Object outputGasTank$getOutputGasNeeded(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.outputGasTank));
    }

    public static Object outputGasTank$getOutputGasFilledPercentage(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.outputGasTank));
    }

    public static Object getInput_1(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object getOutput_1(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }

    public static Object dumpInputs_0(TileEntityExtraPressurizedReactingFactory subject, BaseComputerHelper helper) throws ComputerException {
        subject.dump();
        return helper.voidResult();
    }
}
