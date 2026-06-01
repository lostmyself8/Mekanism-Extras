package com.jerry.mekanism_extras.common.integration.mekmm.tile;

import mekanism.api.math.FloatingLong;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraMoreMachineFactory.class)
public class TileEntityExtraMoreMachineFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraMoreMachineFactory> {

    private final String[] NAMES_process = new String[] { "process" };
    private final String[] NAMES_enabled = new String[] { "enabled" };
    private final Class[] TYPES_3db6c47 = new Class[] { Boolean.TYPE };
    private final Class<?>[] TYPES_1980e = new Class[] { Integer.TYPE };

    public TileEntityExtraMoreMachineFactory$ComputerHandler() {
        register(MethodData.builder("getEnergyItem", TileEntityExtraMoreMachineFactory$ComputerHandler::energySlot$getEnergyItem).returnType(ItemStack.class).methodDescription("Get the contents of the energy slot."));
        register(MethodData.builder("isAutoSortEnabled", TileEntityExtraMoreMachineFactory$ComputerHandler::isAutoSortEnabled_0).returnType(Boolean.TYPE));
        register(MethodData.builder("getEnergyUsage", TileEntityExtraMoreMachineFactory$ComputerHandler::getEnergyUsage_0).returnType(FloatingLong.class).methodDescription("Get the energy used in the last tick by the machine"));
        register(MethodData.builder("getTicksRequired", TileEntityExtraMoreMachineFactory$ComputerHandler::getTicksRequired_0).returnType(Integer.TYPE).methodDescription("Total number of ticks it takes currently for the recipe to complete"));
        register(MethodData.builder("setAutoSort", TileEntityExtraMoreMachineFactory$ComputerHandler::setAutoSort_1).requiresPublicSecurity().arguments(NAMES_enabled, TYPES_3db6c47));
        register(MethodData.builder("getRecipeProgress", TileEntityExtraMoreMachineFactory$ComputerHandler::getRecipeProgress_1).returnType(Integer.TYPE).arguments(NAMES_process, TYPES_1980e));
        register(MethodData.builder("getInput", TileEntityExtraMoreMachineFactory$ComputerHandler::getInput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_1980e));
        register(MethodData.builder("getOutput", TileEntityExtraMoreMachineFactory$ComputerHandler::getOutput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_1980e));
    }

    public static Object energySlot$getEnergyItem(TileEntityExtraMoreMachineFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.energySlot));
    }

    public static Object isAutoSortEnabled_0(TileEntityExtraMoreMachineFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.isSorting());
    }

    public static Object getEnergyUsage_0(TileEntityExtraMoreMachineFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getLastUsage());
    }

    public static Object getTicksRequired_0(TileEntityExtraMoreMachineFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getTicksRequired());
    }

    public static Object setAutoSort_1(TileEntityExtraMoreMachineFactory subject, BaseComputerHelper helper) throws ComputerException {
        subject.setAutoSort(helper.getBoolean(0));
        return helper.voidResult();
    }

    public static Object getRecipeProgress_1(TileEntityExtraMoreMachineFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getRecipeProgress(helper.getInt(0)));
    }

    public static Object getInput_1(TileEntityExtraMoreMachineFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object getOutput_1(TileEntityExtraMoreMachineFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }
}
