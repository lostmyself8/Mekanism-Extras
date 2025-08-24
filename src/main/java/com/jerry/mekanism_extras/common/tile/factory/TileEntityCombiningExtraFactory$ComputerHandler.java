package com.jerry.mekanism_extras.common.tile.factory;

import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;
import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityCombiningExtraFactory.class)
public class TileEntityCombiningExtraFactory$ComputerHandler extends ComputerMethodFactory<TileEntityCombiningExtraFactory> {

    public TileEntityCombiningExtraFactory$ComputerHandler() {
        register(MethodData.builder("getSecondaryInput", TileEntityCombiningExtraFactory$ComputerHandler::extraSlot$getSecondaryInput).returnType(ItemStack.class).methodDescription("Get the contents of the secondary input slot."));
    }

    public static Object extraSlot$getSecondaryInput(TileEntityCombiningExtraFactory subject,
                                                     BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.extraSlot));
    }
}
