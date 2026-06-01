package com.jerry.mekanism_extras.common.integration.mekmm.tile;

import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraStampingFactory.class)
public class TileEntityExtraStampingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraStampingFactory> {

    public TileEntityExtraStampingFactory$ComputerHandler() {
        register(MethodData.builder("getSecondaryInput", TileEntityExtraStampingFactory$ComputerHandler::moldSlot$getSecondaryInput).returnType(ItemStack.class).methodDescription("Get the contents of the secondary input slot."));
    }

    public static Object moldSlot$getSecondaryInput(TileEntityExtraStampingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.moldSlot));
    }
}
