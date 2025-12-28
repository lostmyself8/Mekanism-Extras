package com.jerry.mekextras.common.tile.transmitter;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.neoforged.neoforge.fluids.FluidStack;

@MethodFactory(
               target = TileEntityExtraMechanicalPipe.class)
public class TileEntityExtraMechanicalPipe$ComputerHandler extends ComputerMethodFactory<TileEntityExtraMechanicalPipe> {

    public TileEntityExtraMechanicalPipe$ComputerHandler() {
        register(MethodData.builder("getBuffer", TileEntityExtraMechanicalPipe$ComputerHandler::getBuffer_0).returnType(FluidStack.class));
        register(MethodData.builder("getCapacity", TileEntityExtraMechanicalPipe$ComputerHandler::getCapacity_0).returnType(long.class));
        register(MethodData.builder("getNeeded", TileEntityExtraMechanicalPipe$ComputerHandler::getNeeded_0).returnType(long.class));
        register(MethodData.builder("getFilledPercentage", TileEntityExtraMechanicalPipe$ComputerHandler::getFilledPercentage_0).returnType(double.class));
    }

    public static Object getBuffer_0(TileEntityExtraMechanicalPipe subject, BaseComputerHelper helper)
                                                                                                       throws ComputerException {
        return helper.convert(subject.getBuffer());
    }

    public static Object getCapacity_0(TileEntityExtraMechanicalPipe subject, BaseComputerHelper helper)
                                                                                                         throws ComputerException {
        return helper.convert(subject.getCapacity());
    }

    public static Object getNeeded_0(TileEntityExtraMechanicalPipe subject, BaseComputerHelper helper)
                                                                                                       throws ComputerException {
        return helper.convert(subject.getNeeded());
    }

    public static Object getFilledPercentage_0(TileEntityExtraMechanicalPipe subject,
                                               BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getFilledPercentage());
    }
}
