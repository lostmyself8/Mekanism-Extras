package com.jerry.mekanism_extras.common.tile.machine;

import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

@MethodFactory(target = TileEntityAdvancedElectricPump.class)
public class TileEntityAdvancedElectricPump$ComputerHandler extends ComputerMethodFactory<TileEntityAdvancedElectricPump> {

    public TileEntityAdvancedElectricPump$ComputerHandler() {
        register(MethodData.builder("getFluid", TileEntityAdvancedElectricPump$ComputerHandler::fluidTank$getFluid).returnType(FluidStack.class).methodDescription("Get the contents of the buffer tank."));
        register(MethodData.builder("getFluidCapacity", TileEntityAdvancedElectricPump$ComputerHandler::fluidTank$getFluidCapacity).returnType(Integer.TYPE).methodDescription("Get the capacity of the buffer tank."));
        register(MethodData.builder("getFluidNeeded", TileEntityAdvancedElectricPump$ComputerHandler::fluidTank$getFluidNeeded).returnType(Integer.TYPE).methodDescription("Get the amount needed to fill the buffer tank."));
        register(MethodData.builder("getFluidFilledPercentage", TileEntityAdvancedElectricPump$ComputerHandler::fluidTank$getFluidFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the buffer tank."));
        register(MethodData.builder("getInputItem", TileEntityAdvancedElectricPump$ComputerHandler::inputSlot$getInputItem).returnType(ItemStack.class).methodDescription("Get the contents of the input slot."));
        register(MethodData.builder("getOutputItem", TileEntityAdvancedElectricPump$ComputerHandler::outputSlot$getOutputItem).returnType(ItemStack.class).methodDescription("Get the contents of the output slot."));
        register(MethodData.builder("getEnergyItem", TileEntityAdvancedElectricPump$ComputerHandler::energySlot$getEnergyItem).returnType(ItemStack.class).methodDescription("Get the contents of the energy slot."));
        register(MethodData.builder("reset", TileEntityAdvancedElectricPump$ComputerHandler::reset_0).requiresPublicSecurity());
    }

    public static Object fluidTank$getFluid(TileEntityAdvancedElectricPump subject, BaseComputerHelper helper)
                                                                                                               throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getStack(subject.fluidTank));
    }

    public static Object fluidTank$getFluidCapacity(TileEntityAdvancedElectricPump subject,
                                                    BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getCapacity(subject.fluidTank));
    }

    public static Object fluidTank$getFluidNeeded(TileEntityAdvancedElectricPump subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getNeeded(subject.fluidTank));
    }

    public static Object fluidTank$getFluidFilledPercentage(TileEntityAdvancedElectricPump subject,
                                                            BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getFilledPercentage(subject.fluidTank));
    }

    public static Object inputSlot$getInputItem(TileEntityAdvancedElectricPump subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.inputSlot));
    }

    public static Object outputSlot$getOutputItem(TileEntityAdvancedElectricPump subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.outputSlot));
    }

    public static Object energySlot$getEnergyItem(TileEntityAdvancedElectricPump subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.energySlot));
    }

    public static Object reset_0(TileEntityAdvancedElectricPump subject, BaseComputerHelper helper) throws ComputerException {
        subject.resetPump();
        return helper.voidResult();
    }
}
