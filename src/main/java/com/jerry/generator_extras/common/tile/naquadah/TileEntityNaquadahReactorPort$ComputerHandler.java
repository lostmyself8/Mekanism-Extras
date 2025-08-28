package com.jerry.generator_extras.common.tile.naquadah;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(
        target = TileEntityNaquadahReactorPort.class
)
public class TileEntityNaquadahReactorPort$ComputerHandler extends ComputerMethodFactory<TileEntityNaquadahReactorPort> {

    private final String[] NAMES_output = new String[]{"output"};

    private final Class[] TYPES_3db6c47 = new Class[]{Boolean.TYPE};

    public TileEntityNaquadahReactorPort$ComputerHandler() {
        register(MethodData.builder("getMode", TileEntityNaquadahReactorPort$ComputerHandler::getMode_0).returnType(Boolean.TYPE).methodDescription("true -> output, false -> input"));
        register(MethodData.builder("setMode", TileEntityNaquadahReactorPort$ComputerHandler::setMode_1).methodDescription("true -> output, false -> input").arguments(NAMES_output, TYPES_3db6c47));
    }

    public static Object getMode_0(TileEntityNaquadahReactorPort subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getMode());
    }

    public static Object setMode_1(TileEntityNaquadahReactorPort subject, BaseComputerHelper helper)
            throws ComputerException {
        subject.setMode(helper.getBoolean(0));
        return helper.voidResult();
    }
}
