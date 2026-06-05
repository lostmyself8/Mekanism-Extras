package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base;

import mekanism.api.math.FloatingLong;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraAdvancedFactoryBase.class)
public class TileEntityExtraAdvancedFactoryBase$ComputerHandler extends ComputerMethodFactory<TileEntityExtraAdvancedFactoryBase> {

    private final String[] NAMES_process = new String[] { "process" };
    private final String[] NAMES_enabled = new String[] { "enabled" };
    private final Class[] TYPES_3db6c47 = new Class[] { Boolean.TYPE };
    private final Class<?>[] TYPES_1980e = new Class[] { Integer.TYPE };

    public TileEntityExtraAdvancedFactoryBase$ComputerHandler() {
        register(MethodData.builder("getEnergyItem", TileEntityExtraAdvancedFactoryBase$ComputerHandler::energySlot$getEnergyItem).returnType(ItemStack.class).methodDescription("Get the contents of the energy slot."));
        register(MethodData.builder("isAutoSortEnabled", TileEntityExtraAdvancedFactoryBase$ComputerHandler::isAutoSortEnabled_0).returnType(Boolean.TYPE));
        register(MethodData.builder("getEnergyUsage", TileEntityExtraAdvancedFactoryBase$ComputerHandler::getEnergyUsage_0).returnType(FloatingLong.class).methodDescription("Get the energy used in the last tick by the machine"));
        register(MethodData.builder("getTicksRequired", TileEntityExtraAdvancedFactoryBase$ComputerHandler::getTicksRequired_0).returnType(Integer.TYPE).methodDescription("Total number of ticks it takes currently for the recipe to complete"));
        register(MethodData.builder("setAutoSort", TileEntityExtraAdvancedFactoryBase$ComputerHandler::setAutoSort_1).requiresPublicSecurity().arguments(NAMES_enabled, TYPES_3db6c47));
        register(MethodData.builder("getRecipeProgress", TileEntityExtraAdvancedFactoryBase$ComputerHandler::getRecipeProgress_1).returnType(Integer.TYPE).arguments(NAMES_process, TYPES_1980e));
    }

    public static Object energySlot$getEnergyItem(TileEntityExtraAdvancedFactoryBase subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.energySlot));
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
