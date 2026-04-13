package com.jerry.mekextras.common.integration.mekaf.tile.factory.base;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraAdvancedFactoryBase.class)
public class TileEntityExtraAdvancedFactoryBase$ComputerHandler extends ComputerMethodFactory<TileEntityExtraAdvancedFactoryBase> {

    private final String[] NAMES_process = new String[] { "process" };

    private final String[] NAMES_enabled = new String[] { "enabled" };

    private final Class[] TYPES_boolean = new Class[] { boolean.class };

    private final Class[] TYPES_int = new Class[] { int.class };

    public TileEntityExtraAdvancedFactoryBase$ComputerHandler() {
        register(MethodData.builder("getEnergyItem", TileEntityExtraAdvancedFactoryBase$ComputerHandler::energySlot$getEnergyItem).returnType(ItemStack.class).methodDescription("Get the contents of the energy slot."));
        register(MethodData.builder("isAutoSortEnabled", TileEntityExtraAdvancedFactoryBase$ComputerHandler::isAutoSortEnabled_0).returnType(boolean.class));
        register(MethodData.builder("getEnergyUsage", TileEntityExtraAdvancedFactoryBase$ComputerHandler::getEnergyUsage_0).returnType(long.class).methodDescription("Get the energy used in the last tick by the machine"));
        register(MethodData.builder("getTicksRequired", TileEntityExtraAdvancedFactoryBase$ComputerHandler::getTicksRequired_0).returnType(int.class).methodDescription("Total number of ticks it takes currently for the recipe to complete"));
        register(MethodData.builder("setAutoSort", TileEntityExtraAdvancedFactoryBase$ComputerHandler::setAutoSort_1).requiresPublicSecurity().arguments(NAMES_enabled, TYPES_boolean));
        register(MethodData.builder("getRecipeProgress", TileEntityExtraAdvancedFactoryBase$ComputerHandler::getRecipeProgress_1).returnType(int.class).arguments(NAMES_process, TYPES_int));
    }

    public static Object energySlot$getEnergyItem(TileEntityExtraAdvancedFactoryBase subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerIInventorySlotWrapper.getStack(subject.energySlot));
    }

    public static Object isAutoSortEnabled_0(TileEntityExtraAdvancedFactoryBase subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.isSorting());
    }

    public static Object getEnergyUsage_0(TileEntityExtraAdvancedFactoryBase subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getLastUsage());
    }

    public static Object getTicksRequired_0(TileEntityExtraAdvancedFactoryBase subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getTicksRequired());
    }

    public static Object setAutoSort_1(TileEntityExtraAdvancedFactoryBase subject, BaseComputerHelper helper) throws ComputerException {
        subject.setAutoSort(helper.getBoolean(0));
        return helper.voidResult();
    }

    public static Object getRecipeProgress_1(TileEntityExtraAdvancedFactoryBase subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getRecipeProgress(helper.getInt(0)));
    }
}
