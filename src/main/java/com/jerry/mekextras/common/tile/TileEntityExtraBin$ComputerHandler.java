package com.jerry.mekextras.common.tile;

import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(
               target = TileEntityExtraBin.class)
public class TileEntityExtraBin$ComputerHandler extends ComputerMethodFactory<TileEntityExtraBin> {

    public TileEntityExtraBin$ComputerHandler() {
        register(MethodData.builder("getStored", TileEntityExtraBin$ComputerHandler::binSlot$getStored).returnType(ItemStack.class).methodDescription("Get the contents of the bin."));
        register(MethodData.builder("getCapacity", TileEntityExtraBin$ComputerHandler::getCapacity_0).returnType(int.class).methodDescription("Get the maximum number of items the bin can contain."));
        register(MethodData.builder("isLocked", TileEntityExtraBin$ComputerHandler::isLocked_0).returnType(boolean.class).methodDescription("If true, the Bin is locked to a particular item type."));
        register(MethodData.builder("getLock", TileEntityExtraBin$ComputerHandler::getLock_0).returnType(ItemStack.class).methodDescription("Get the type of item the Bin is locked to (or Air if not locked)"));
        register(MethodData.builder("lock", TileEntityExtraBin$ComputerHandler::lock_0).methodDescription("Lock the Bin to the currently stored item type. The Bin must not be creative, empty, or already locked"));
        register(MethodData.builder("unlock", TileEntityExtraBin$ComputerHandler::unlock_0).methodDescription("Unlock the Bin's fixed item type. The Bin must not be creative, or already unlocked"));
    }

    public static Object binSlot$getStored(TileEntityExtraBin subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.binSlot));
    }

    public static Object getCapacity_0(TileEntityExtraBin subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getCapacity());
    }

    public static Object isLocked_0(TileEntityExtraBin subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.isLocked());
    }

    public static Object getLock_0(TileEntityExtraBin subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getLock());
    }

    public static Object lock_0(TileEntityExtraBin subject, BaseComputerHelper helper) throws ComputerException {
        subject.lock();
        return helper.voidResult();
    }

    public static Object unlock_0(TileEntityExtraBin subject, BaseComputerHelper helper) throws ComputerException {
        subject.unlock();
        return helper.voidResult();
    }
}
