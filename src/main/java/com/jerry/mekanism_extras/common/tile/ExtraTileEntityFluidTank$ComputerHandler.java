package com.jerry.mekanism_extras.common.tile;

import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;
import mekanism.common.tile.interfaces.IFluidContainerManager;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

@MethodFactory(
               target = ExtraTileEntityFluidTank.class)
public class ExtraTileEntityFluidTank$ComputerHandler extends ComputerMethodFactory<ExtraTileEntityFluidTank> {

    private final String[] NAMES_mode = new String[] { "mode" };

    private final Class[] TYPES_f8347998 = new Class[] { IFluidContainerManager.ContainerEditMode.class };

    public ExtraTileEntityFluidTank$ComputerHandler() {
        register(MethodData.builder("getStored", ExtraTileEntityFluidTank$ComputerHandler::fluidTank$getStored).returnType(FluidStack.class).methodDescription("Get the contents of the tank."));
        register(MethodData.builder("getCapacity", ExtraTileEntityFluidTank$ComputerHandler::fluidTank$getCapacity).returnType(Integer.TYPE).methodDescription("Get the capacity of the tank."));
        register(MethodData.builder("getNeeded", ExtraTileEntityFluidTank$ComputerHandler::fluidTank$getNeeded).returnType(Integer.TYPE).methodDescription("Get the amount needed to fill the tank."));
        register(MethodData.builder("getFilledPercentage", ExtraTileEntityFluidTank$ComputerHandler::fluidTank$getFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the tank."));
        register(MethodData.builder("getInputItem", ExtraTileEntityFluidTank$ComputerHandler::inputSlot$getInputItem).returnType(ItemStack.class).methodDescription("Get the contents of the input slot."));
        register(MethodData.builder("getOutputItem", ExtraTileEntityFluidTank$ComputerHandler::outputSlot$getOutputItem).returnType(ItemStack.class).methodDescription("Get the contents of the output slot."));
        register(MethodData.builder("getContainerEditMode", ExtraTileEntityFluidTank$ComputerHandler::getContainerEditMode_0).returnType(IFluidContainerManager.ContainerEditMode.class));
        register(MethodData.builder("setContainerEditMode", ExtraTileEntityFluidTank$ComputerHandler::setContainerEditMode_1).requiresPublicSecurity().arguments(NAMES_mode, TYPES_f8347998));
        register(MethodData.builder("incrementContainerEditMode", ExtraTileEntityFluidTank$ComputerHandler::incrementContainerEditMode_0).requiresPublicSecurity());
        register(MethodData.builder("decrementContainerEditMode", ExtraTileEntityFluidTank$ComputerHandler::decrementContainerEditMode_0).requiresPublicSecurity());
    }

    public static Object fluidTank$getStored(ExtraTileEntityFluidTank subject, BaseComputerHelper helper)
                                                                                                          throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getStack(subject.fluidTank));
    }

    public static Object fluidTank$getCapacity(ExtraTileEntityFluidTank subject, BaseComputerHelper helper)
                                                                                                            throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getCapacity(subject.fluidTank));
    }

    public static Object fluidTank$getNeeded(ExtraTileEntityFluidTank subject, BaseComputerHelper helper)
                                                                                                          throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getNeeded(subject.fluidTank));
    }

    public static Object fluidTank$getFilledPercentage(ExtraTileEntityFluidTank subject,
                                                       BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getFilledPercentage(subject.fluidTank));
    }

    public static Object inputSlot$getInputItem(ExtraTileEntityFluidTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.inputSlot));
    }

    public static Object outputSlot$getOutputItem(ExtraTileEntityFluidTank subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.outputSlot));
    }

    public static Object getContainerEditMode_0(ExtraTileEntityFluidTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getContainerEditMode());
    }

    public static Object setContainerEditMode_1(ExtraTileEntityFluidTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        subject.setContainerEditMode(helper.getEnum(0, IFluidContainerManager.ContainerEditMode.class));
        return helper.voidResult();
    }

    public static Object incrementContainerEditMode_0(ExtraTileEntityFluidTank subject,
                                                      BaseComputerHelper helper) throws ComputerException {
        subject.incrementContainerEditMode();
        return helper.voidResult();
    }

    public static Object decrementContainerEditMode_0(ExtraTileEntityFluidTank subject,
                                                      BaseComputerHelper helper) throws ComputerException {
        subject.decrementContainerEditMode();
        return helper.voidResult();
    }
}
