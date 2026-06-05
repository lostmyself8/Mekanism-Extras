package com.jerry.mekanism_extras.common.integration.mekmm.tile;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraPlantingFactory.class)
public class TileEntityExtraPlantingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraPlantingFactory> {

    public TileEntityExtraPlantingFactory$ComputerHandler() {
        register(MethodData.builder("getChemicalItem", TileEntityExtraPlantingFactory$ComputerHandler::gasSlot$getChemicalItem).returnType(ItemStack.class).methodDescription("Get the contents of the chemical item (extra) slot."));
        register(MethodData.builder("getChemical", TileEntityExtraPlantingFactory$ComputerHandler::gasTank$getChemical).returnType(ChemicalStack.class).methodDescription("Get the contents of the chemical tank."));
        register(MethodData.builder("getChemicalCapacity", TileEntityExtraPlantingFactory$ComputerHandler::gasTank$getChemicalCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the chemical tank."));
        register(MethodData.builder("getChemicalNeeded", TileEntityExtraPlantingFactory$ComputerHandler::gasTank$getChemicalNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the chemical tank."));
        register(MethodData.builder("getChemicalFilledPercentage", TileEntityExtraPlantingFactory$ComputerHandler::gasTank$getChemicalFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the chemical tank."));
        register(MethodData.builder("dumpChemical", TileEntityExtraPlantingFactory$ComputerHandler::dumpChemical_0).methodDescription("Empty the contents of the chemical tank into the environment").requiresPublicSecurity());
    }

    public static Object gasSlot$getChemicalItem(TileEntityExtraPlantingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.gasSlot));
    }

    public static Object gasTank$getChemical(TileEntityExtraPlantingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.gasTank));
    }

    public static Object gasTank$getChemicalCapacity(TileEntityExtraPlantingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.gasTank));
    }

    public static Object gasTank$getChemicalNeeded(TileEntityExtraPlantingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.gasTank));
    }

    public static Object gasTank$getChemicalFilledPercentage(TileEntityExtraPlantingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.gasTank));
    }

    public static Object dumpChemical_0(TileEntityExtraPlantingFactory subject, BaseComputerHelper helper) throws ComputerException {
        subject.dump();
        return helper.voidResult();
    }
}
