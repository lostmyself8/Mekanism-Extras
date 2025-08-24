package com.jerry.mekanism_extras.common.tile.transmitter;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(
        target = ExtraTileEntityPressurizedTube.class
)
public class ExtraTileEntityPressurizedTube$ComputerHandler extends ComputerMethodFactory<ExtraTileEntityPressurizedTube> {

    public ExtraTileEntityPressurizedTube$ComputerHandler() {
        register(MethodData.builder("getBuffer", ExtraTileEntityPressurizedTube$ComputerHandler::getBuffer_0).returnType(ChemicalStack.class));
        register(MethodData.builder("getCapacity", ExtraTileEntityPressurizedTube$ComputerHandler::getCapacity_0).returnType(Long.TYPE));
        register(MethodData.builder("getNeeded", ExtraTileEntityPressurizedTube$ComputerHandler::getNeeded_0).returnType(Long.TYPE));
        register(MethodData.builder("getFilledPercentage", ExtraTileEntityPressurizedTube$ComputerHandler::getFilledPercentage_0).returnType(Double.TYPE));
    }

    public static Object getBuffer_0(ExtraTileEntityPressurizedTube subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getBuffer());
    }

    public static Object getCapacity_0(ExtraTileEntityPressurizedTube subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getCapacity());
    }

    public static Object getNeeded_0(ExtraTileEntityPressurizedTube subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getNeeded());
    }

    public static Object getFilledPercentage_0(ExtraTileEntityPressurizedTube subject,
                                               BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getFilledPercentage());
    }
}
