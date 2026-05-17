package com.jerry.mekanism_extras.common.tile.factory;

import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraCombiningFactory.class)
public class TileEntityExtraCombiningFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraCombiningFactory> {

    public TileEntityExtraCombiningFactory$ComputerHandler() {
        register(MethodData.builder("getSecondaryInput", TileEntityExtraCombiningFactory$ComputerHandler::extraSlot$getSecondaryInput).returnType(ItemStack.class).methodDescription("Get the contents of the secondary input slot."));
    }

    public static Object extraSlot$getSecondaryInput(TileEntityExtraCombiningFactory subject,
                                                     BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.extraSlot));
    }
}
