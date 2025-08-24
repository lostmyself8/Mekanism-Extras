package com.jerry.mekanism_extras.common.tile.transmitter;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;
import net.minecraftforge.fluids.FluidStack;

@MethodFactory(
        target = ExtraTileEntityMechanicalPipe.class
)
public class ExtraTileEntityMechanicalPipe$ComputerHandler extends ComputerMethodFactory<ExtraTileEntityMechanicalPipe> {

    public ExtraTileEntityMechanicalPipe$ComputerHandler() {
        register(MethodData.builder("getBuffer", ExtraTileEntityMechanicalPipe$ComputerHandler::getBuffer_0).returnType(FluidStack.class));
        register(MethodData.builder("getCapacity", ExtraTileEntityMechanicalPipe$ComputerHandler::getCapacity_0).returnType(Long.TYPE));
        register(MethodData.builder("getNeeded", ExtraTileEntityMechanicalPipe$ComputerHandler::getNeeded_0).returnType(Long.TYPE));
        register(MethodData.builder("getFilledPercentage", ExtraTileEntityMechanicalPipe$ComputerHandler::getFilledPercentage_0).returnType(Double.TYPE));
    }

    public static Object getBuffer_0(ExtraTileEntityMechanicalPipe subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getBuffer());
    }

    public static Object getCapacity_0(ExtraTileEntityMechanicalPipe subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getCapacity());
    }

    public static Object getNeeded_0(ExtraTileEntityMechanicalPipe subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.getNeeded());
    }

    public static Object getFilledPercentage_0(ExtraTileEntityMechanicalPipe subject,
                                               BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getFilledPercentage());
    }
}
