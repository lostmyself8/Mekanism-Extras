package com.jerry.mekanism_extras.common.tile.factory;

import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.annotation.MethodFactory;
import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntitySawingExtraFactory.class)
public class TileEntitySawingExtraFactory$ComputerHandler extends ComputerMethodFactory<TileEntitySawingExtraFactory> {
    private final String[] NAMES_process = new String[]{"process"};

    private final Class[] TYPES_1980e = new Class[]{Integer.TYPE};

    public TileEntitySawingExtraFactory$ComputerHandler() {
        register(MethodData.builder("getSecondaryOutput", TileEntitySawingExtraFactory$ComputerHandler::getSecondaryOutput_1).returnType(ItemStack.class).arguments(NAMES_process, TYPES_1980e));
    }

    public static Object getSecondaryOutput_1(TileEntitySawingExtraFactory subject,
                                              BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getSecondaryOutput(helper.getInt(0)));
    }
}
