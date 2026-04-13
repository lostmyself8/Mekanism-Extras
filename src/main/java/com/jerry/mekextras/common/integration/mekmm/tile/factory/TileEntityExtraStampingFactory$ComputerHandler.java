package com.jerry.mekextras.common.integration.mekmm.tile.factory;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraStampingFactory.class)
public class TileEntityExtraStampingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraStampingFactory> {

    public TileEntityExtraStampingFactory$ComputerHandler() {
        register(MethodData.builder("getMoldInput", TileEntityExtraStampingFactory$ComputerHandler::moldSlot$getMoldInput).returnType(ItemStack.class).methodDescription("Get the contents of the mold input slot."));
    }

    public static Object moldSlot$getMoldInput(TileEntityExtraStampingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerIInventorySlotWrapper.getStack(subject.extraSlot));
    }
}
