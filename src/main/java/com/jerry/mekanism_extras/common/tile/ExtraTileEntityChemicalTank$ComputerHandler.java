package com.jerry.mekanism_extras.common.tile;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(
               target = ExtraTileEntityChemicalTank.class)
public class ExtraTileEntityChemicalTank$ComputerHandler extends ComputerMethodFactory<ExtraTileEntityChemicalTank> {

    private final String[] NAMES_mode = new String[] { "mode" };

    private final Class[] TYPES_ef806282 = new Class[] { ExtraTileEntityChemicalTank.GasMode.class };

    public ExtraTileEntityChemicalTank$ComputerHandler() {
        register(MethodData.builder("getDumpingMode", ExtraTileEntityChemicalTank$ComputerHandler::getDumpingMode_0).returnType(ExtraTileEntityChemicalTank.GasMode.class).methodDescription("Get the current Dumping configuration"));
        register(MethodData.builder("getDrainItem", ExtraTileEntityChemicalTank$ComputerHandler::drainSlot$getDrainItem).returnType(ItemStack.class).methodDescription("Get the contents of the drain slot."));
        register(MethodData.builder("getFillItem", ExtraTileEntityChemicalTank$ComputerHandler::fillSlot$getFillItem).returnType(ItemStack.class).methodDescription("Get the contents of the fill slot."));
        register(MethodData.builder("getStored", ExtraTileEntityChemicalTank$ComputerHandler::getCurrentTank$getStored).returnType(ChemicalStack.class).methodDescription("Get the contents of the tank."));
        register(MethodData.builder("getCapacity", ExtraTileEntityChemicalTank$ComputerHandler::getCurrentTank$getCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the tank."));
        register(MethodData.builder("getNeeded", ExtraTileEntityChemicalTank$ComputerHandler::getCurrentTank$getNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the tank."));
        register(MethodData.builder("getFilledPercentage", ExtraTileEntityChemicalTank$ComputerHandler::getCurrentTank$getFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the tank."));
        register(MethodData.builder("setDumpingMode", ExtraTileEntityChemicalTank$ComputerHandler::setDumpingMode_1).methodDescription("Set the Dumping mode of the tank").requiresPublicSecurity().arguments(NAMES_mode, TYPES_ef806282));
        register(MethodData.builder("incrementDumpingMode", ExtraTileEntityChemicalTank$ComputerHandler::incrementDumpingMode_0).methodDescription("Advance the Dumping mode to the next configuration in the list").requiresPublicSecurity());
        register(MethodData.builder("decrementDumpingMode", ExtraTileEntityChemicalTank$ComputerHandler::decrementDumpingMode_0).methodDescription("Descend the Dumping mode to the previous configuration in the list").requiresPublicSecurity());
    }

    public static Object getDumpingMode_0(ExtraTileEntityChemicalTank subject, BaseComputerHelper helper)
                                                                                                          throws ComputerException {
        return helper.convert(subject.dumping);
    }

    public static Object drainSlot$getDrainItem(ExtraTileEntityChemicalTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.drainSlot));
    }

    public static Object fillSlot$getFillItem(ExtraTileEntityChemicalTank subject,
                                              BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.fillSlot));
    }

    public static Object getCurrentTank$getStored(ExtraTileEntityChemicalTank subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.getCurrentTank()));
    }

    public static Object getCurrentTank$getCapacity(ExtraTileEntityChemicalTank subject,
                                                    BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.getCurrentTank()));
    }

    public static Object getCurrentTank$getNeeded(ExtraTileEntityChemicalTank subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.getCurrentTank()));
    }

    public static Object getCurrentTank$getFilledPercentage(ExtraTileEntityChemicalTank subject,
                                                            BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.getCurrentTank()));
    }

    public static Object setDumpingMode_1(ExtraTileEntityChemicalTank subject, BaseComputerHelper helper)
                                                                                                          throws ComputerException {
        subject.setDumpingMode(helper.getEnum(0, ExtraTileEntityChemicalTank.GasMode.class));
        return helper.voidResult();
    }

    public static Object incrementDumpingMode_0(ExtraTileEntityChemicalTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        subject.incrementDumpingMode();
        return helper.voidResult();
    }

    public static Object decrementDumpingMode_0(ExtraTileEntityChemicalTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        subject.decrementDumpingMode();
        return helper.voidResult();
    }
}
