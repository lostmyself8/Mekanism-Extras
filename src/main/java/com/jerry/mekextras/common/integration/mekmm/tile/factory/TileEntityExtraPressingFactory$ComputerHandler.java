package com.jerry.mekextras.common.integration.mekmm.tile.factory;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraPressingFactory.class)
public class TileEntityExtraPressingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraPressingFactory> {

    public TileEntityExtraPressingFactory$ComputerHandler() {
        register(MethodData.builder("getSecondaryInput", TileEntityExtraPressingFactory$ComputerHandler::secondarySlot$getSecondaryInput).returnType(ItemStack.class).methodDescription("Get the contents of the secondary input slot."));
        register(MethodData.builder("getTertiaryInput", TileEntityExtraPressingFactory$ComputerHandler::tertiarySlot$getTertiaryInput).returnType(ItemStack.class).methodDescription("Get the contents of the tertiary input slot."));
    }

    public static Object secondarySlot$getSecondaryInput(TileEntityExtraPressingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerIInventorySlotWrapper.getStack(subject.secondarySlot));
    }

    public static Object tertiarySlot$getTertiaryInput(TileEntityExtraPressingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerIInventorySlotWrapper.getStack(subject.tertiarySlot));
    }
}
