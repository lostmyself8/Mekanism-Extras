package com.jerry.mekextras.common.tile.factory;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraSawingFactory.class)
public class TileEntityExtraSawingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraSawingFactory> {

    private final String[] NAMES_process = new String[] { "process" };

    private final Class[] TYPES_1980e = new Class[] { int.class };

    public TileEntityExtraSawingFactory$ComputerHandler() {
        register(MethodData.builder("getSecondaryOutput", TileEntityExtraSawingFactory$ComputerHandler::getSecondaryOutput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_1980e));
    }

    public static Object getSecondaryOutput_1(TileEntityExtraSawingFactory subject,
                                              BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getSecondaryOutput(helper.getInt(0)));
    }
}
