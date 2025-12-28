package com.jerry.mekextras.common.tile.transmitter;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(
        target = TileEntityExtraUniversalCable.class
)
public class TileEntityExtraUniversalCable$ComputerHandler extends ComputerMethodFactory<TileEntityExtraUniversalCable> {

    public TileEntityExtraUniversalCable$ComputerHandler() {
        register(MethodData.builder("getBuffer", TileEntityExtraUniversalCable$ComputerHandler::getBuffer_0).returnType(long.class));
        register(MethodData.builder("getCapacity", TileEntityExtraUniversalCable$ComputerHandler::getCapacity_0).returnType(long.class));
        register(MethodData.builder("getNeeded", TileEntityExtraUniversalCable$ComputerHandler::getNeeded_0).returnType(long.class));
        register(MethodData.builder("getFilledPercentage", TileEntityExtraUniversalCable$ComputerHandler::getFilledPercentage_0).returnType(double.class));
    }

    public static Object getBuffer_0(TileEntityExtraUniversalCable subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getBuffer());
    }

    public static Object getCapacity_0(TileEntityExtraUniversalCable subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getCapacity());
    }

    public static Object getNeeded_0(TileEntityExtraUniversalCable subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getNeeded());
    }

    public static Object getFilledPercentage_0(TileEntityExtraUniversalCable subject,
                                               BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getFilledPercentage());
    }
}
