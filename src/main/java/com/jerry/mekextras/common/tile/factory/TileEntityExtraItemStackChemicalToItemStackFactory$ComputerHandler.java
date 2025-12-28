package com.jerry.mekextras.common.tile.factory;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraItemStackChemicalToItemStackFactory.class)
public class TileEntityExtraItemStackChemicalToItemStackFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraItemStackChemicalToItemStackFactory> {

    public TileEntityExtraItemStackChemicalToItemStackFactory$ComputerHandler() {
        register(MethodData.builder("getChemicalItem", TileEntityExtraItemStackChemicalToItemStackFactory$ComputerHandler::extraSlot$getChemicalItem).returnType(ItemStack.class).methodDescription("Get the contents of the chemical item (extra) slot."));
        register(MethodData.builder("getChemical", TileEntityExtraItemStackChemicalToItemStackFactory$ComputerHandler::chemicalTank$getChemical).returnType(ChemicalStack.class).methodDescription("Get the contents of the chemical tank."));
        register(MethodData.builder("getChemicalCapacity", TileEntityExtraItemStackChemicalToItemStackFactory$ComputerHandler::chemicalTank$getChemicalCapacity).returnType(long.class).methodDescription("Get the capacity of the chemical tank."));
        register(MethodData.builder("getChemicalNeeded", TileEntityExtraItemStackChemicalToItemStackFactory$ComputerHandler::chemicalTank$getChemicalNeeded).returnType(long.class).methodDescription("Get the amount needed to fill the chemical tank."));
        register(MethodData.builder("getChemicalFilledPercentage", TileEntityExtraItemStackChemicalToItemStackFactory$ComputerHandler::chemicalTank$getChemicalFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the chemical tank."));
        register(MethodData.builder("dumpChemical", TileEntityExtraItemStackChemicalToItemStackFactory$ComputerHandler::dumpChemical_0).methodDescription("Empty the contents of the chemical tank into the environment").requiresPublicSecurity());
    }

    public static Object extraSlot$getChemicalItem(
                                                   TileEntityExtraItemStackChemicalToItemStackFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.extraSlot));
    }

    public static Object chemicalTank$getChemical(
                                                  TileEntityExtraItemStackChemicalToItemStackFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.chemicalTank));
    }

    public static Object chemicalTank$getChemicalCapacity(
                                                          TileEntityExtraItemStackChemicalToItemStackFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.chemicalTank));
    }

    public static Object chemicalTank$getChemicalNeeded(
                                                        TileEntityExtraItemStackChemicalToItemStackFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.chemicalTank));
    }

    public static Object chemicalTank$getChemicalFilledPercentage(
                                                                  TileEntityExtraItemStackChemicalToItemStackFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.chemicalTank));
    }

    public static Object dumpChemical_0(TileEntityExtraItemStackChemicalToItemStackFactory subject,
                                        BaseComputerHelper helper) throws ComputerException {
        subject.dumpChemical();
        return helper.voidResult();
    }
}
