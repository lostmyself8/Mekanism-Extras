package com.jerry.mekextras.common.tile;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;
import mekanism.common.tile.TileEntityChemicalTank.GasMode;

import net.minecraft.world.item.ItemStack;

@MethodFactory(
               target = TileEntityExtraChemicalTank.class)
public class TileEntityExtraChemicalTank$ComputerHandler extends ComputerMethodFactory<TileEntityExtraChemicalTank> {

    private final String[] NAMES_mode = new String[] { "mode" };

    private final Class[] TYPES_ef806282 = new Class[] { GasMode.class };

    public TileEntityExtraChemicalTank$ComputerHandler() {
        register(MethodData.builder("getDumpingMode", TileEntityExtraChemicalTank$ComputerHandler::getDumpingMode_0).returnType(GasMode.class).methodDescription("Get the current Dumping configuration"));
        register(MethodData.builder("getDrainItem", TileEntityExtraChemicalTank$ComputerHandler::drainSlot$getDrainItem).returnType(ItemStack.class).methodDescription("Get the contents of the drain slot."));
        register(MethodData.builder("getFillItem", TileEntityExtraChemicalTank$ComputerHandler::fillSlot$getFillItem).returnType(ItemStack.class).methodDescription("Get the contents of the fill slot."));
        register(MethodData.builder("getStored", TileEntityExtraChemicalTank$ComputerHandler::getCurrentTank$getStored).returnType(ChemicalStack.class).methodDescription("Get the contents of the tank."));
        register(MethodData.builder("getCapacity", TileEntityExtraChemicalTank$ComputerHandler::getCurrentTank$getCapacity).returnType(long.class).methodDescription("Get the capacity of the tank."));
        register(MethodData.builder("getNeeded", TileEntityExtraChemicalTank$ComputerHandler::getCurrentTank$getNeeded).returnType(long.class).methodDescription("Get the amount needed to fill the tank."));
        register(MethodData.builder("getFilledPercentage", TileEntityExtraChemicalTank$ComputerHandler::getCurrentTank$getFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the tank."));
        register(MethodData.builder("setDumpingMode", TileEntityExtraChemicalTank$ComputerHandler::setDumpingMode_1).methodDescription("Set the Dumping mode of the tank").requiresPublicSecurity().arguments(NAMES_mode, TYPES_ef806282));
        register(MethodData.builder("incrementDumpingMode", TileEntityExtraChemicalTank$ComputerHandler::incrementDumpingMode_0).methodDescription("Advance the Dumping mode to the next configuration in the list").requiresPublicSecurity());
        register(MethodData.builder("decrementDumpingMode", TileEntityExtraChemicalTank$ComputerHandler::decrementDumpingMode_0).methodDescription("Descend the Dumping mode to the previous configuration in the list").requiresPublicSecurity());
    }

    public static Object getDumpingMode_0(TileEntityExtraChemicalTank subject, BaseComputerHelper helper)
                                                                                                          throws ComputerException {
        return helper.convert(subject.dumping);
    }

    public static Object drainSlot$getDrainItem(TileEntityExtraChemicalTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.drainSlot));
    }

    public static Object fillSlot$getFillItem(TileEntityExtraChemicalTank subject,
                                              BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.fillSlot));
    }

    public static Object getCurrentTank$getStored(TileEntityExtraChemicalTank subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.getCurrentTank()));
    }

    public static Object getCurrentTank$getCapacity(TileEntityExtraChemicalTank subject,
                                                    BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.getCurrentTank()));
    }

    public static Object getCurrentTank$getNeeded(TileEntityExtraChemicalTank subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.getCurrentTank()));
    }

    public static Object getCurrentTank$getFilledPercentage(TileEntityExtraChemicalTank subject,
                                                            BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.getCurrentTank()));
    }

    public static Object setDumpingMode_1(TileEntityExtraChemicalTank subject, BaseComputerHelper helper)
                                                                                                          throws ComputerException {
        subject.setDumpingMode(helper.getEnum(0, GasMode.class));
        return helper.voidResult();
    }

    public static Object incrementDumpingMode_0(TileEntityExtraChemicalTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        subject.incrementDumpingMode();
        return helper.voidResult();
    }

    public static Object decrementDumpingMode_0(TileEntityExtraChemicalTank subject,
                                                BaseComputerHelper helper) throws ComputerException {
        subject.decrementDumpingMode();
        return helper.voidResult();
    }
}
