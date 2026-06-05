package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraPaintingFactory.class)
public class TileEntityExtraPaintingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraPaintingFactory> {

    public TileEntityExtraPaintingFactory$ComputerHandler() {
        register(MethodData.builder("getPigmentInput", TileEntityExtraPaintingFactory$ComputerHandler::pigmentTank$getPigmentInput).returnType(ChemicalStack.class).methodDescription("Get the contents of the pigment tank."));
        register(MethodData.builder("getPigmentInputCapacity", TileEntityExtraPaintingFactory$ComputerHandler::pigmentTank$getPigmentInputCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the pigment tank."));
        register(MethodData.builder("getPigmentInputNeeded", TileEntityExtraPaintingFactory$ComputerHandler::pigmentTank$getPigmentInputNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the pigment tank."));
        register(MethodData.builder("getPigmentInputFilledPercentage", TileEntityExtraPaintingFactory$ComputerHandler::pigmentTank$getPigmentInputFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the pigment tank."));
        register(MethodData.builder("getInputPigmentItem", TileEntityExtraPaintingFactory$ComputerHandler::pigmentInputSlot$getInputPigmentItem).returnType(ItemStack.class).methodDescription("Get the contents of the pigment slot."));
        register(MethodData.builder("dumpPigment", TileEntityExtraPaintingFactory$ComputerHandler::dumpPigment_0).methodDescription("Empty the contents of the pigment tank into the environment").requiresPublicSecurity());
    }

    public static Object pigmentTank$getPigmentInput(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.pigmentTank));
    }

    public static Object pigmentTank$getPigmentInputCapacity(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.pigmentTank));
    }

    public static Object pigmentTank$getPigmentInputNeeded(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.pigmentTank));
    }

    public static Object pigmentTank$getPigmentInputFilledPercentage(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.pigmentTank));
    }

    public static Object pigmentInputSlot$getInputPigmentItem(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.pigmentInputSlot));
    }

    public static Object dumpPigment_0(TileEntityExtraPaintingFactory subject, BaseComputerHelper helper) throws ComputerException {
        subject.dump();
        return helper.voidResult();
    }
}
