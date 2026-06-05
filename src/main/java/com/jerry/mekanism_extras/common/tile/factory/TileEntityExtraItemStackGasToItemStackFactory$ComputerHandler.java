package com.jerry.mekanism_extras.common.tile.factory;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraItemStackGasToItemStackFactory.class)
public class TileEntityExtraItemStackGasToItemStackFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraItemStackGasToItemStackFactory> {

    public TileEntityExtraItemStackGasToItemStackFactory$ComputerHandler() {
        register(MethodData.builder("getChemicalItem", TileEntityExtraItemStackGasToItemStackFactory$ComputerHandler::extraSlot$getChemicalItem).returnType(ItemStack.class).methodDescription("Get the contents of the chemical item (extra) slot."));
        register(MethodData.builder("getChemical", TileEntityExtraItemStackGasToItemStackFactory$ComputerHandler::gasTank$getChemical).returnType(ChemicalStack.class).methodDescription("Get the contents of the chemical tank."));
        register(MethodData.builder("getChemicalCapacity", TileEntityExtraItemStackGasToItemStackFactory$ComputerHandler::gasTank$getChemicalCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the chemical tank."));
        register(MethodData.builder("getChemicalNeeded", TileEntityExtraItemStackGasToItemStackFactory$ComputerHandler::gasTank$getChemicalNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the chemical tank."));
        register(MethodData.builder("getChemicalFilledPercentage", TileEntityExtraItemStackGasToItemStackFactory$ComputerHandler::gasTank$getChemicalFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the chemical tank."));
        register(MethodData.builder("dumpChemical", TileEntityExtraItemStackGasToItemStackFactory$ComputerHandler::dumpChemical_0).methodDescription("Empty the contents of the chemical tank into the environment").requiresPublicSecurity());
    }

    public static Object extraSlot$getChemicalItem(
                                                   TileEntityExtraItemStackGasToItemStackFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.extraSlot));
    }

    public static Object gasTank$getChemical(
                                             TileEntityExtraItemStackGasToItemStackFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.gasTank));
    }

    public static Object gasTank$getChemicalCapacity(
                                                     TileEntityExtraItemStackGasToItemStackFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.gasTank));
    }

    public static Object gasTank$getChemicalNeeded(
                                                   TileEntityExtraItemStackGasToItemStackFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.gasTank));
    }

    public static Object gasTank$getChemicalFilledPercentage(
                                                             TileEntityExtraItemStackGasToItemStackFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.gasTank));
    }

    public static Object dumpChemical_0(TileEntityExtraItemStackGasToItemStackFactory subject,
                                        BaseComputerHelper helper) throws ComputerException {
        subject.dumpChemical();
        return helper.voidResult();
    }
}
