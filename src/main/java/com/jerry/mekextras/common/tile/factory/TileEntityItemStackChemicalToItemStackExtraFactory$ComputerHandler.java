package com.jerry.mekextras.common.tile.factory;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;
import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityItemStackChemicalToItemStackExtraFactory.class)
public class TileEntityItemStackChemicalToItemStackExtraFactory$ComputerHandler extends ComputerMethodFactory<TileEntityItemStackChemicalToItemStackExtraFactory> {
    public TileEntityItemStackChemicalToItemStackExtraFactory$ComputerHandler() {
        register(MethodData.builder("getChemicalItem", TileEntityItemStackChemicalToItemStackExtraFactory$ComputerHandler::extraSlot$getChemicalItem).returnType(ItemStack.class).methodDescription("Get the contents of the chemical item (extra) slot."));
        register(MethodData.builder("getChemical", TileEntityItemStackChemicalToItemStackExtraFactory$ComputerHandler::chemicalTank$getChemical).returnType(ChemicalStack.class).methodDescription("Get the contents of the chemical tank."));
        register(MethodData.builder("getChemicalCapacity", TileEntityItemStackChemicalToItemStackExtraFactory$ComputerHandler::chemicalTank$getChemicalCapacity).returnType(long.class).methodDescription("Get the capacity of the chemical tank."));
        register(MethodData.builder("getChemicalNeeded", TileEntityItemStackChemicalToItemStackExtraFactory$ComputerHandler::chemicalTank$getChemicalNeeded).returnType(long.class).methodDescription("Get the amount needed to fill the chemical tank."));
        register(MethodData.builder("getChemicalFilledPercentage", TileEntityItemStackChemicalToItemStackExtraFactory$ComputerHandler::chemicalTank$getChemicalFilledPercentage).returnType(double.class).methodDescription("Get the filled percentage of the chemical tank."));
        register(MethodData.builder("dumpChemical", TileEntityItemStackChemicalToItemStackExtraFactory$ComputerHandler::dumpChemical_0).methodDescription("Empty the contents of the chemical tank into the environment").requiresPublicSecurity());
    }

    public static Object extraSlot$getChemicalItem(
            TileEntityItemStackChemicalToItemStackExtraFactory subject, BaseComputerHelper helper) throws
            ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.extraSlot));
    }

    public static Object chemicalTank$getChemical(
            TileEntityItemStackChemicalToItemStackExtraFactory subject, BaseComputerHelper helper) throws
            ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.chemicalTank));
    }

    public static Object chemicalTank$getChemicalCapacity(
            TileEntityItemStackChemicalToItemStackExtraFactory subject, BaseComputerHelper helper) throws
            ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.chemicalTank));
    }

    public static Object chemicalTank$getChemicalNeeded(
            TileEntityItemStackChemicalToItemStackExtraFactory subject, BaseComputerHelper helper) throws
            ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.chemicalTank));
    }

    public static Object chemicalTank$getChemicalFilledPercentage(
            TileEntityItemStackChemicalToItemStackExtraFactory subject, BaseComputerHelper helper) throws
            ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.chemicalTank));
    }

    public static Object dumpChemical_0(TileEntityItemStackChemicalToItemStackExtraFactory subject,
                                        BaseComputerHelper helper) throws ComputerException {
        subject.dumpChemical();
        return helper.voidResult();
    }
}
