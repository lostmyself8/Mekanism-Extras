package com.jerry.mekanism_extras.common.content.matrix;

import mekanism.api.math.FloatingLong;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = ExtraMatrixMultiblockData.class)
public class ExtraMatrixMultiblockData$ComputerHandler extends ComputerMethodFactory<ExtraMatrixMultiblockData> {

    public ExtraMatrixMultiblockData$ComputerHandler() {
        register(MethodData.builder("getInputItem", ExtraMatrixMultiblockData$ComputerHandler::energyInputSlot$getInputItem).returnType(ItemStack.class).methodDescription("Get the contents of the input slot."));
        register(MethodData.builder("getOutputItem", ExtraMatrixMultiblockData$ComputerHandler::energyOutputSlot$getOutputItem).returnType(ItemStack.class).methodDescription("Get the contents of the output slot."));
        register(MethodData.builder("getTransferCap", ExtraMatrixMultiblockData$ComputerHandler::getTransferCap_0).returnType(FloatingLong.class));
        register(MethodData.builder("getLastInput", ExtraMatrixMultiblockData$ComputerHandler::getLastInput_0).returnType(FloatingLong.class));
        register(MethodData.builder("getLastOutput", ExtraMatrixMultiblockData$ComputerHandler::getLastOutput_0).returnType(FloatingLong.class));
        register(MethodData.builder("getInstalledCells", ExtraMatrixMultiblockData$ComputerHandler::getInstalledCells_0).returnType(Integer.TYPE));
        register(MethodData.builder("getInstalledProviders", ExtraMatrixMultiblockData$ComputerHandler::getInstalledProviders_0).returnType(Integer.TYPE));
    }

    public static Object energyInputSlot$getInputItem(ExtraMatrixMultiblockData subject,
                                                      BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.energyInputSlot));
    }

    public static Object energyOutputSlot$getOutputItem(ExtraMatrixMultiblockData subject,
                                                        BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.energyOutputSlot));
    }

    public static Object getTransferCap_0(ExtraMatrixMultiblockData subject, BaseComputerHelper helper)
                                                                                                        throws ComputerException {
        return helper.convert(subject.getTransferCap());
    }

    public static Object getLastInput_0(ExtraMatrixMultiblockData subject, BaseComputerHelper helper)
                                                                                                      throws ComputerException {
        return helper.convert(subject.getLastInput());
    }

    public static Object getLastOutput_0(ExtraMatrixMultiblockData subject, BaseComputerHelper helper)
                                                                                                       throws ComputerException {
        return helper.convert(subject.getLastOutput());
    }

    public static Object getInstalledCells_0(ExtraMatrixMultiblockData subject, BaseComputerHelper helper)
                                                                                                           throws ComputerException {
        return helper.convert(subject.getCellCount());
    }

    public static Object getInstalledProviders_0(ExtraMatrixMultiblockData subject,
                                                 BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getProviderCount());
    }
}
