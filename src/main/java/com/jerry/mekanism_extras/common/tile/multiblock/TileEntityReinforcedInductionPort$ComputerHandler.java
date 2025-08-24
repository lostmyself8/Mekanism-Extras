package com.jerry.mekanism_extras.common.tile.multiblock;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(target = TileEntityReinforcedInductionPort.class)
public class TileEntityReinforcedInductionPort$ComputerHandler extends ComputerMethodFactory<TileEntityReinforcedInductionPort> {

    private final String[] NAMES_output = new String[]{"output"};

    private final Class[] TYPES_3db6c47 = new Class[]{Boolean.TYPE};

    public TileEntityReinforcedInductionPort$ComputerHandler() {
        register(MethodData.builder("getMode", TileEntityReinforcedInductionPort$ComputerHandler::getMode_0).returnType(Boolean.TYPE).methodDescription("true -> output, false -> input."));
        register(MethodData.builder("setMode", TileEntityReinforcedInductionPort$ComputerHandler::setMode_1).methodDescription("true -> output, false -> input").arguments(NAMES_output, TYPES_3db6c47));
    }

    public static Object getMode_0(TileEntityReinforcedInductionPort subject, BaseComputerHelper helper) throws
            ComputerException {
        return helper.convert(subject.getMode());
    }

    public static Object setMode_1(TileEntityReinforcedInductionPort subject, BaseComputerHelper helper) throws
            ComputerException {
        subject.setMode(helper.getBoolean(0));
        return helper.voidResult();
    }
}
