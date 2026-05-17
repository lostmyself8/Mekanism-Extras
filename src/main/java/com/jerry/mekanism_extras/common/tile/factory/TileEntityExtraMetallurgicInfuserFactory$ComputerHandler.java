package com.jerry.mekanism_extras.common.tile.factory;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraMetallurgicInfuserFactory.class)
public class TileEntityExtraMetallurgicInfuserFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraMetallurgicInfuserFactory> {

    public TileEntityExtraMetallurgicInfuserFactory$ComputerHandler() {
        this.register(MethodData.builder("getInfuseTypeItem", TileEntityExtraMetallurgicInfuserFactory$ComputerHandler::extraSlot$getInfuseTypeItem).returnType(ItemStack.class).methodDescription("Get the contents of the infusion extra input slot."));
        this.register(MethodData.builder("getInfuseType", TileEntityExtraMetallurgicInfuserFactory$ComputerHandler::infusionTank$getInfuseType).returnType(ChemicalStack.class).methodDescription("Get the contents of the infusion buffer."));
        this.register(MethodData.builder("getInfuseTypeCapacity", TileEntityExtraMetallurgicInfuserFactory$ComputerHandler::infusionTank$getInfuseTypeCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the infusion buffer."));
        this.register(MethodData.builder("getInfuseTypeNeeded", TileEntityExtraMetallurgicInfuserFactory$ComputerHandler::infusionTank$getInfuseTypeNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the infusion buffer."));
        this.register(MethodData.builder("getInfuseTypeFilledPercentage", TileEntityExtraMetallurgicInfuserFactory$ComputerHandler::infusionTank$getInfuseTypeFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the infusion buffer."));
        this.register(MethodData.builder("dumpInfuseType", TileEntityExtraMetallurgicInfuserFactory$ComputerHandler::dumpInfuseType_0).methodDescription("Empty the contents of the infusion buffer into the environment").requiresPublicSecurity());
    }

    public static Object extraSlot$getInfuseTypeItem(TileEntityExtraMetallurgicInfuserFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.extraSlot));
    }

    public static Object infusionTank$getInfuseType(TileEntityExtraMetallurgicInfuserFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.infusionTank));
    }

    public static Object infusionTank$getInfuseTypeCapacity(TileEntityExtraMetallurgicInfuserFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.infusionTank));
    }

    public static Object infusionTank$getInfuseTypeNeeded(TileEntityExtraMetallurgicInfuserFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.infusionTank));
    }

    public static Object infusionTank$getInfuseTypeFilledPercentage(TileEntityExtraMetallurgicInfuserFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.infusionTank));
    }

    public static Object dumpInfuseType_0(TileEntityExtraMetallurgicInfuserFactory subject, BaseComputerHelper helper) throws ComputerException {
        subject.dumpInfuseType();
        return helper.voidResult();
    }
}
