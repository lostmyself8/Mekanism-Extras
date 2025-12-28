package com.jerry.mekextras.common.tile;

import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;
import net.minecraft.world.item.ItemStack;

@MethodFactory(
        target = TileEntityExtraEnergyCube.class
)
public class TileEntityExtraEnergyCube$ComputerHandler extends ComputerMethodFactory<TileEntityExtraEnergyCube> {

    public TileEntityExtraEnergyCube$ComputerHandler() {
        register(MethodData.builder("getChargeItem", TileEntityExtraEnergyCube$ComputerHandler::chargeSlot$getChargeItem).returnType(ItemStack.class).methodDescription("Get the contents of the charge slot."));
        register(MethodData.builder("getDischargeItem", TileEntityExtraEnergyCube$ComputerHandler::dischargeSlot$getDischargeItem).returnType(ItemStack.class).methodDescription("Get the contents of the discharge slot."));
    }

    public static Object chargeSlot$getChargeItem(TileEntityExtraEnergyCube subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.chargeSlot));
    }

    public static Object dischargeSlot$getDischargeItem(TileEntityExtraEnergyCube subject,
                                                        BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.dischargeSlot));
    }
}
