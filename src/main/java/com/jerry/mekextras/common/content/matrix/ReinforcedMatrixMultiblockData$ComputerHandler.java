package com.jerry.mekextras.common.content.matrix;

import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = ReinforcedMatrixMultiblockData.class)
public class ReinforcedMatrixMultiblockData$ComputerHandler extends ComputerMethodFactory<ReinforcedMatrixMultiblockData> {

    public ReinforcedMatrixMultiblockData$ComputerHandler() {
        register(MethodData.builder("getInputItem", ReinforcedMatrixMultiblockData$ComputerHandler::energyInputSlot$getInputItem).returnType(ItemStack.class).methodDescription("Get the contents of the input slot."));
        register(MethodData.builder("getOutputItem", ReinforcedMatrixMultiblockData$ComputerHandler::energyOutputSlot$getOutputItem).returnType(ItemStack.class).methodDescription("Get the contents of the output slot."));
        register(MethodData.builder("getTransferCap", ReinforcedMatrixMultiblockData$ComputerHandler::getTransferCap_0).returnType(long.class));
        register(MethodData.builder("getLastInput", ReinforcedMatrixMultiblockData$ComputerHandler::getLastInput_0).returnType(long.class));
        register(MethodData.builder("getLastOutput", ReinforcedMatrixMultiblockData$ComputerHandler::getLastOutput_0).returnType(long.class));
        register(MethodData.builder("getInstalledCells", ReinforcedMatrixMultiblockData$ComputerHandler::getInstalledCells_0).returnType(int.class));
        register(MethodData.builder("getInstalledProviders", ReinforcedMatrixMultiblockData$ComputerHandler::getInstalledProviders_0).returnType(int.class));
    }

    public static Object energyInputSlot$getInputItem(ReinforcedMatrixMultiblockData subject,
                                                      BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.energyInputSlot));
    }

    public static Object energyOutputSlot$getOutputItem(ReinforcedMatrixMultiblockData subject,
                                                        BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.energyOutputSlot));
    }

    public static Object getTransferCap_0(ReinforcedMatrixMultiblockData subject, BaseComputerHelper helper)
                                                                                                             throws ComputerException {
        return helper.convert(subject.getTransferCap());
    }

    public static Object getLastInput_0(ReinforcedMatrixMultiblockData subject, BaseComputerHelper helper)
                                                                                                           throws ComputerException {
        return helper.convert(subject.getLastInput());
    }

    public static Object getLastOutput_0(ReinforcedMatrixMultiblockData subject, BaseComputerHelper helper)
                                                                                                            throws ComputerException {
        return helper.convert(subject.getLastOutput());
    }

    public static Object getInstalledCells_0(ReinforcedMatrixMultiblockData subject, BaseComputerHelper helper)
                                                                                                                throws ComputerException {
        return helper.convert(subject.getCellCount());
    }

    public static Object getInstalledProviders_0(ReinforcedMatrixMultiblockData subject,
                                                 BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getProviderCount());
    }
}
