package com.jerry.mekanism_extras.common.tile.transmitter;

import mekanism.api.math.FloatingLong;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(
        target = ExtraTileEntityUniversalCable.class
)
public class ExtraTileEntityUniversalCable$ComputerHandler extends ComputerMethodFactory<ExtraTileEntityUniversalCable> {

    public ExtraTileEntityUniversalCable$ComputerHandler() {
        register(MethodData.builder("getBuffer", ExtraTileEntityUniversalCable$ComputerHandler::getBuffer_0).returnType(FloatingLong.class));
        register(MethodData.builder("getCapacity", ExtraTileEntityUniversalCable$ComputerHandler::getCapacity_0).returnType(FloatingLong.class));
        register(MethodData.builder("getNeeded", ExtraTileEntityUniversalCable$ComputerHandler::getNeeded_0).returnType(FloatingLong.class));
        register(MethodData.builder("getFilledPercentage", ExtraTileEntityUniversalCable$ComputerHandler::getFilledPercentage_0).returnType(Double.TYPE));
    }

    public static Object getBuffer_0(ExtraTileEntityUniversalCable subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getBuffer());
    }

    public static Object getCapacity_0(ExtraTileEntityUniversalCable subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getCapacity());
    }

    public static Object getNeeded_0(ExtraTileEntityUniversalCable subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getNeeded());
    }

    public static Object getFilledPercentage_0(ExtraTileEntityUniversalCable subject,
                                               BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getFilledPercentage());
    }
}
