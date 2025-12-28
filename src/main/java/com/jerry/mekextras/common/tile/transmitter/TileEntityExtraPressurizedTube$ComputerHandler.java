package com.jerry.mekextras.common.tile.transmitter;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(
        target = TileEntityExtraPressurizedTube.class
)
public class TileEntityExtraPressurizedTube$ComputerHandler extends ComputerMethodFactory<TileEntityExtraPressurizedTube> {

    public TileEntityExtraPressurizedTube$ComputerHandler() {
        register(MethodData.builder("getBuffer", TileEntityExtraPressurizedTube$ComputerHandler::getBuffer_0).returnType(ChemicalStack.class));
        register(MethodData.builder("getCapacity", TileEntityExtraPressurizedTube$ComputerHandler::getCapacity_0).returnType(long.class));
        register(MethodData.builder("getNeeded", TileEntityExtraPressurizedTube$ComputerHandler::getNeeded_0).returnType(long.class));
        register(MethodData.builder("getFilledPercentage", TileEntityExtraPressurizedTube$ComputerHandler::getFilledPercentage_0).returnType(double.class));
    }

    public static Object getBuffer_0(TileEntityExtraPressurizedTube subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getBuffer());
    }

    public static Object getCapacity_0(TileEntityExtraPressurizedTube subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getCapacity());
    }

    public static Object getNeeded_0(TileEntityExtraPressurizedTube subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getNeeded());
    }

    public static Object getFilledPercentage_0(TileEntityExtraPressurizedTube subject,
                                               BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getFilledPercentage());
    }
}
