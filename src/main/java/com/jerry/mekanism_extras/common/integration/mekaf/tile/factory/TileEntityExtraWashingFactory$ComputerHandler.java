package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerFluidTankWrapper;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

@MethodFactory(target = TileEntityExtraWashingFactory.class)
public class TileEntityExtraWashingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraWashingFactory> {

    public TileEntityExtraWashingFactory$ComputerHandler() {
        register(MethodData.builder("getFluid", TileEntityExtraWashingFactory$ComputerHandler::fluidTank$getFluid).returnType(FluidStack.class).methodDescription("Get the contents of the fluid tank."));
        register(MethodData.builder("getFluidCapacity", TileEntityExtraWashingFactory$ComputerHandler::fluidTank$getFluidCapacity).returnType(Integer.TYPE).methodDescription("Get the capacity of the fluid tank."));
        register(MethodData.builder("getFluidNeeded", TileEntityExtraWashingFactory$ComputerHandler::fluidTank$getFluidNeeded).returnType(Integer.TYPE).methodDescription("Get the amount needed to fill the fluid tank."));
        register(MethodData.builder("getFluidFilledPercentage", TileEntityExtraWashingFactory$ComputerHandler::fluidTank$getFluidFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the fluid tank."));
        register(MethodData.builder("getFluidItemInput", TileEntityExtraWashingFactory$ComputerHandler::fluidInputSlot$getFluidItemInput).returnType(ItemStack.class).methodDescription("Get the contents of the fluid item input slot."));
        register(MethodData.builder("getFluidItemOutput", TileEntityExtraWashingFactory$ComputerHandler::fluidOutputSlot$getFluidItemOutput).returnType(ItemStack.class).methodDescription("Get the contents of the fluid item output slot."));
        register(MethodData.builder("dumpFluid", TileEntityExtraWashingFactory$ComputerHandler::dumpFluid_0).methodDescription("Empty the contents of the fluid tank into the environment").requiresPublicSecurity());
    }

    public static Object fluidTank$getFluid(TileEntityExtraWashingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getStack(subject.fluidTank));
    }

    public static Object fluidTank$getFluidCapacity(TileEntityExtraWashingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getCapacity(subject.fluidTank));
    }

    public static Object fluidTank$getFluidNeeded(TileEntityExtraWashingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getNeeded(subject.fluidTank));
    }

    public static Object fluidTank$getFluidFilledPercentage(TileEntityExtraWashingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerFluidTankWrapper.getFilledPercentage(subject.fluidTank));
    }

    public static Object fluidInputSlot$getFluidItemInput(TileEntityExtraWashingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerIInventorySlotWrapper.getStack(subject.fluidInputSlot));
    }

    public static Object fluidOutputSlot$getFluidItemOutput(TileEntityExtraWashingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerIInventorySlotWrapper.getStack(subject.fluidOutputSlot));
    }

    public static Object dumpFluid_0(TileEntityExtraWashingFactory subject, BaseComputerHelper helper) throws ComputerException {
        subject.dump();
        return helper.voidResult();
    }
}
