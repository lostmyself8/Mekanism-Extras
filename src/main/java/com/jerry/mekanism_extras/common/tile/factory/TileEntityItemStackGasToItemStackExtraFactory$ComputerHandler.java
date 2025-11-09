package com.jerry.mekanism_extras.common.tile.factory;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityItemStackGasToItemStackExtraFactory.class)
public class TileEntityItemStackGasToItemStackExtraFactory$ComputerHandler extends ComputerMethodFactory<TileEntityItemStackGasToItemStackExtraFactory> {

    public TileEntityItemStackGasToItemStackExtraFactory$ComputerHandler() {
        register(MethodData.builder("getChemicalItem", TileEntityItemStackGasToItemStackExtraFactory$ComputerHandler::extraSlot$getChemicalItem).returnType(ItemStack.class).methodDescription("Get the contents of the chemical item (extra) slot."));
        register(MethodData.builder("getChemical", TileEntityItemStackGasToItemStackExtraFactory$ComputerHandler::gasTank$getChemical).returnType(ChemicalStack.class).methodDescription("Get the contents of the chemical tank."));
        register(MethodData.builder("getChemicalCapacity", TileEntityItemStackGasToItemStackExtraFactory$ComputerHandler::gasTank$getChemicalCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the chemical tank."));
        register(MethodData.builder("getChemicalNeeded", TileEntityItemStackGasToItemStackExtraFactory$ComputerHandler::gasTank$getChemicalNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the chemical tank."));
        register(MethodData.builder("getChemicalFilledPercentage", TileEntityItemStackGasToItemStackExtraFactory$ComputerHandler::gasTank$getChemicalFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the chemical tank."));
        register(MethodData.builder("dumpChemical", TileEntityItemStackGasToItemStackExtraFactory$ComputerHandler::dumpChemical_0).methodDescription("Empty the contents of the chemical tank into the environment").requiresPublicSecurity());
    }

    public static Object extraSlot$getChemicalItem(
                                                   TileEntityItemStackGasToItemStackExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.extraSlot));
    }

    public static Object gasTank$getChemical(
                                             TileEntityItemStackGasToItemStackExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.gasTank));
    }

    public static Object gasTank$getChemicalCapacity(
                                                     TileEntityItemStackGasToItemStackExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.gasTank));
    }

    public static Object gasTank$getChemicalNeeded(
                                                   TileEntityItemStackGasToItemStackExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.gasTank));
    }

    public static Object gasTank$getChemicalFilledPercentage(
                                                             TileEntityItemStackGasToItemStackExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.gasTank));
    }

    public static Object dumpChemical_0(TileEntityItemStackGasToItemStackExtraFactory subject,
                                        BaseComputerHelper helper) throws ComputerException {
        subject.dumpChemical();
        return helper.voidResult();
    }
}
