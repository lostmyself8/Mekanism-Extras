package com.jerry.mekanism_extras.common.tile;

import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;
import net.minecraft.world.item.ItemStack;

@MethodFactory(
        target = ExtraTileEntityEnergyCube.class
)
public class ExtraTileEntityEnergyCube$ComputerHandler extends ComputerMethodFactory<ExtraTileEntityEnergyCube> {

    public ExtraTileEntityEnergyCube$ComputerHandler() {
        register(MethodData.builder("getChargeItem", ExtraTileEntityEnergyCube$ComputerHandler::chargeSlot$getChargeItem).returnType(ItemStack.class).methodDescription("Get the contents of the charge slot."));
        register(MethodData.builder("getDischargeItem", ExtraTileEntityEnergyCube$ComputerHandler::dischargeSlot$getDischargeItem).returnType(ItemStack.class).methodDescription("Get the contents of the discharge slot."));
    }

    public static Object chargeSlot$getChargeItem(ExtraTileEntityEnergyCube subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.chargeSlot));
    }

    public static Object dischargeSlot$getDischargeItem(ExtraTileEntityEnergyCube subject,
                                                        BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.dischargeSlot));
    }
}
