package com.jerry.mekextras.common.tile;

import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;
import mekanism.common.tile.interfaces.IFluidContainerManager;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

@MethodFactory(
               target = TileEntityExtraFluidTank.class)
public class TileEntityExtraFluidTank$ComputerHandler extends ComputerMethodFactory<TileEntityExtraFluidTank> {

    private final String[] NAMES_mode = new String[] { "mode" };

    private final Class[] TYPES_f8347998 = new Class[] { IFluidContainerManager.ContainerEditMode.class };

    public TileEntityExtraFluidTank$ComputerHandler() {
        register(MethodData.builder("getStored", TileEntityExtraFluidTank$ComputerHandler::fluidTank$getStored).returnType(FluidStack.class).methodDescription("Get the contents of the tank."));
        register(MethodData.builder("getCapacity", TileEntityExtraFluidTank$ComputerHandler::fluidTank$getCapacity).returnType(int.class).methodDescription("Get the capacity of the tank."));
        register(MethodData.builder("getNeeded", TileEntityExtraFluidTank$ComputerHandler::fluidTank$getNeeded).returnType(int.class).methodDescription("Get the amount needed to fill the tank."));
        register(MethodData.builder("getFilledPercentage", TileEntityExtraFluidTank$ComputerHandler::fluidTank$getFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the tank."));
        register(MethodData.builder("getInputItem", TileEntityExtraFluidTank$ComputerHandler::inputSlot$getInputItem).returnType(ItemStack.class).methodDescription("Get the contents of the input slot."));
        register(MethodData.builder("getOutputItem", TileEntityExtraFluidTank$ComputerHandler::outputSlot$getOutputItem).returnType(ItemStack.class).methodDescription("Get the contents of the output slot."));
        register(MethodData.builder("getContainerEditMode", TileEntityExtraFluidTank$ComputerHandler::getContainerEditMode_0).returnType(IFluidContainerManager.ContainerEditMode.class));
        register(MethodData.builder("setContainerEditMode", TileEntityExtraFluidTank$ComputerHandler::setContainerEditMode_1).requiresPublicSecurity().arguments(NAMES_mode, TYPES_f8347998));
        register(MethodData.builder("incrementContainerEditMode", TileEntityExtraFluidTank$ComputerHandler::incrementContainerEditMode_0).requiresPublicSecurity());
        register(MethodData.builder("decrementContainerEditMode", TileEntityExtraFluidTank$ComputerHandler::decrementContainerEditMode_0).requiresPublicSecurity());
    }

    public static Object fluidTank$getStored(TileEntityExtraFluidTank subject, BaseComputerHelper helper)
                                                                                                          throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getStack(subject.fluidTank));
    }

    public static Object fluidTank$getCapacity(TileEntityExtraFluidTank subject, BaseComputerHelper helper)
                                                                                                            throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getCapacity(subject.fluidTank));
    }

    public static Object fluidTank$getNeeded(TileEntityExtraFluidTank subject, BaseComputerHelper helper)
                                                                                                          throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getNeeded(subject.fluidTank));
    }

    public static Object fluidTank$getFilledPercentage(TileEntityExtraFluidTank subject,
                                                       BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getFilledPercentage(subject.fluidTank));
    }

    public static Object inputSlot$getInputItem(TileEntityExtraFluidTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.inputSlot));
    }

    public static Object outputSlot$getOutputItem(TileEntityExtraFluidTank subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.outputSlot));
    }

    public static Object getContainerEditMode_0(TileEntityExtraFluidTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getContainerEditMode());
    }

    public static Object setContainerEditMode_1(TileEntityExtraFluidTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        subject.setContainerEditMode(helper.getEnum(0, IFluidContainerManager.ContainerEditMode.class));
        return helper.voidResult();
    }

    public static Object incrementContainerEditMode_0(TileEntityExtraFluidTank subject,
                                                      BaseComputerHelper helper) throws ComputerException {
        subject.incrementContainerEditMode();
        return helper.voidResult();
    }

    public static Object decrementContainerEditMode_0(TileEntityExtraFluidTank subject,
                                                      BaseComputerHelper helper) throws ComputerException {
        subject.decrementContainerEditMode();
        return helper.voidResult();
    }
}
