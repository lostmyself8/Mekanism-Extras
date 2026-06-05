package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraItemToItemAdvancedFactory.class)
public class TileEntityExtraItemToItemAdvancedFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraItemToItemAdvancedFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class<?>[] TYPES_int = new Class[] { Integer.TYPE };

    public TileEntityExtraItemToItemAdvancedFactory$ComputerHandler() {
        register(MethodData.builder("getInput", TileEntityExtraItemToItemAdvancedFactory$ComputerHandler::getInput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
        register(MethodData.builder("getOutput", TileEntityExtraItemToItemAdvancedFactory$ComputerHandler::getOutput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_int));
    }

    public static Object getInput_1(TileEntityExtraItemToItemAdvancedFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInput(helper.getInt(0)));
    }

    public static Object getOutput_1(TileEntityExtraItemToItemAdvancedFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getOutput(helper.getInt(0)));
    }
}
