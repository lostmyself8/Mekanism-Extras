package com.jerry.mekanism_extras.common.integration.mekmm.tile;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraReplicatingFactory.class)
public class TileEntityExtraReplicatingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraReplicatingFactory> {

    public TileEntityExtraReplicatingFactory$ComputerHandler() {
        register(MethodData.builder("getChemicalItem", TileEntityExtraReplicatingFactory$ComputerHandler::gasSlot$getChemicalItem).returnType(ItemStack.class).methodDescription("Get the contents of the chemical item (extra) slot."));
        register(MethodData.builder("getChemical", TileEntityExtraReplicatingFactory$ComputerHandler::gasTank$getChemical).returnType(ChemicalStack.class).methodDescription("Get the contents of the chemical tank."));
        register(MethodData.builder("getChemicalCapacity", TileEntityExtraReplicatingFactory$ComputerHandler::gasTank$getChemicalCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the chemical tank."));
        register(MethodData.builder("getChemicalNeeded", TileEntityExtraReplicatingFactory$ComputerHandler::gasTank$getChemicalNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the chemical tank."));
        register(MethodData.builder("getChemicalFilledPercentage", TileEntityExtraReplicatingFactory$ComputerHandler::gasTank$getChemicalFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the chemical tank."));
        register(MethodData.builder("dumpChemical", TileEntityExtraReplicatingFactory$ComputerHandler::dumpChemical_0).methodDescription("Empty the contents of the chemical tank into the environment").requiresPublicSecurity());
    }

    public static Object gasSlot$getChemicalItem(TileEntityExtraReplicatingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.gasSlot));
    }

    public static Object gasTank$getChemical(TileEntityExtraReplicatingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.gasTank));
    }

    public static Object gasTank$getChemicalCapacity(TileEntityExtraReplicatingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.gasTank));
    }

    public static Object gasTank$getChemicalNeeded(TileEntityExtraReplicatingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.gasTank));
    }

    public static Object gasTank$getChemicalFilledPercentage(TileEntityExtraReplicatingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.gasTank));
    }

    public static Object dumpChemical_0(TileEntityExtraReplicatingFactory subject, BaseComputerHelper helper) throws ComputerException {
        subject.dump();
        return helper.voidResult();
    }
}
