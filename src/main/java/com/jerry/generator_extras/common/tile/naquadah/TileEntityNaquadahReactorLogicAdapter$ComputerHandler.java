package com.jerry.generator_extras.common.tile.naquadah;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;

@MethodFactory(target = TileEntityNaquadahReactorLogicAdapter.class)
public class TileEntityNaquadahReactorLogicAdapter$ComputerHandler extends ComputerMethodFactory<TileEntityNaquadahReactorLogicAdapter> {

    private final String[] NAMES_active = new String[]{"active"};

    private final String[] NAMES_logicType = new String[]{"logicType"};

    private final Class[] TYPES_3db6c47 = new Class[]{Boolean.TYPE};

    private final Class[] TYPES_7061479 = new Class[]{TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic.class};

    public TileEntityNaquadahReactorLogicAdapter$ComputerHandler() {
        register(MethodData.builder("isActiveCooledLogic", TileEntityNaquadahReactorLogicAdapter$ComputerHandler::isActiveCooledLogic_0).returnType(Boolean.TYPE));
        register(MethodData.builder("getLogicMode", TileEntityNaquadahReactorLogicAdapter$ComputerHandler::getLogicMode_0).returnType(TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic.class));
        register(MethodData.builder("setLogicMode", TileEntityNaquadahReactorLogicAdapter$ComputerHandler::setLogicMode_1).arguments(NAMES_logicType, TYPES_7061479));
        register(MethodData.builder("setActiveCooledLogic", TileEntityNaquadahReactorLogicAdapter$ComputerHandler::setActiveCooledLogic_1).arguments(NAMES_active, TYPES_3db6c47));
    }

    public static Object isActiveCooledLogic_0(TileEntityNaquadahReactorLogicAdapter subject,
                                               BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.isActiveCooled());
    }

    public static Object getLogicMode_0(TileEntityNaquadahReactorLogicAdapter subject,
                                        BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getMode());
    }

    public static Object setLogicMode_1(TileEntityNaquadahReactorLogicAdapter subject,
                                        BaseComputerHelper helper) throws ComputerException {
        subject.setLogicTypeFromPacket(helper.getEnum(0, TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic.class));
        return helper.voidResult();
    }

    public static Object setActiveCooledLogic_1(TileEntityNaquadahReactorLogicAdapter subject,
                                                BaseComputerHelper helper) throws ComputerException {
        subject.setActiveCooledLogic(helper.getBoolean(0));
        return helper.voidResult();
    }
}
