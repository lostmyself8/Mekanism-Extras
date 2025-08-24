package com.jerry.mekanism_extras.common.tile.factory;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;
import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityMetallurgicInfuserExtraFactory.class)
public class TileEntityMetallurgicInfuserExtraFactory$ComputerHandler extends ComputerMethodFactory<TileEntityMetallurgicInfuserExtraFactory> {

    public TileEntityMetallurgicInfuserExtraFactory$ComputerHandler() {
        this.register(MethodData.builder("getInfuseTypeItem", TileEntityMetallurgicInfuserExtraFactory$ComputerHandler::extraSlot$getInfuseTypeItem).returnType(ItemStack.class).methodDescription("Get the contents of the infusion extra input slot."));
        this.register(MethodData.builder("getInfuseType", TileEntityMetallurgicInfuserExtraFactory$ComputerHandler::infusionTank$getInfuseType).returnType(ChemicalStack.class).methodDescription("Get the contents of the infusion buffer."));
        this.register(MethodData.builder("getInfuseTypeCapacity", TileEntityMetallurgicInfuserExtraFactory$ComputerHandler::infusionTank$getInfuseTypeCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the infusion buffer."));
        this.register(MethodData.builder("getInfuseTypeNeeded", TileEntityMetallurgicInfuserExtraFactory$ComputerHandler::infusionTank$getInfuseTypeNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the infusion buffer."));
        this.register(MethodData.builder("getInfuseTypeFilledPercentage", TileEntityMetallurgicInfuserExtraFactory$ComputerHandler::infusionTank$getInfuseTypeFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the infusion buffer."));
        this.register(MethodData.builder("dumpInfuseType", TileEntityMetallurgicInfuserExtraFactory$ComputerHandler::dumpInfuseType_0).methodDescription("Empty the contents of the infusion buffer into the environment").requiresPublicSecurity());
    }

    public static Object extraSlot$getInfuseTypeItem(TileEntityMetallurgicInfuserExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.extraSlot));
    }

    public static Object infusionTank$getInfuseType(TileEntityMetallurgicInfuserExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.infusionTank));
    }

    public static Object infusionTank$getInfuseTypeCapacity(TileEntityMetallurgicInfuserExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.infusionTank));
    }

    public static Object infusionTank$getInfuseTypeNeeded(TileEntityMetallurgicInfuserExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.infusionTank));
    }

    public static Object infusionTank$getInfuseTypeFilledPercentage(TileEntityMetallurgicInfuserExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.infusionTank));
    }

    public static Object dumpInfuseType_0(TileEntityMetallurgicInfuserExtraFactory subject, BaseComputerHelper helper) throws ComputerException {
        subject.dumpInfuseType();
        return helper.voidResult();
    }
}
