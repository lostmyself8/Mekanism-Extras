package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory;

import mekanism.api.chemical.gas.GasStack;
import mekanism.common.integration.computer.BaseComputerHelper;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.ComputerMethodFactory;
import mekanism.common.integration.computer.MethodData;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper;
import mekanism.common.integration.computer.annotation.MethodFactory;

import net.minecraft.world.item.ItemStack;

@MethodFactory(target = TileEntityExtraDissolvingFactory.class)
public class TileEntityExtraDissolvingFactory$ComputerHandler extends ComputerMethodFactory<TileEntityExtraDissolvingFactory> {

    public TileEntityExtraDissolvingFactory$ComputerHandler() {
        register(MethodData.builder("getChemicalInput", TileEntityExtraDissolvingFactory$ComputerHandler::injectTank$getChemicalInput).returnType(GasStack.class).methodDescription("Get the contents of the chemical input tank."));
        register(MethodData.builder("getChemicalInputCapacity", TileEntityExtraDissolvingFactory$ComputerHandler::injectTank$getChemicalInputCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the chemical input tank."));
        register(MethodData.builder("getChemicalInputNeeded", TileEntityExtraDissolvingFactory$ComputerHandler::injectTank$getChemicalInputNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the chemical input tank."));
        register(MethodData.builder("getChemicalInputFilledPercentage", TileEntityExtraDissolvingFactory$ComputerHandler::injectTank$getChemicalInputFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the chemical input tank."));
        register(MethodData.builder("getInputChemicalItem", TileEntityExtraDissolvingFactory$ComputerHandler::gasInputSlot$getInputChemicalItem).returnType(ItemStack.class).methodDescription("Get the contents of the chemical input item slot."));
        register(MethodData.builder("dumpChemical", TileEntityExtraDissolvingFactory$ComputerHandler::dumpChemical_0).methodDescription("Empty the contents of the chemical input tank into the environment").requiresPublicSecurity());
    }

    public static Object injectTank$getChemicalInput(TileEntityExtraDissolvingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getStack(subject.injectTank));
    }

    public static Object injectTank$getChemicalInputCapacity(TileEntityExtraDissolvingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getCapacity(subject.injectTank));
    }

    public static Object injectTank$getChemicalInputNeeded(TileEntityExtraDissolvingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getNeeded(subject.injectTank));
    }

    public static Object injectTank$getChemicalInputFilledPercentage(TileEntityExtraDissolvingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerChemicalTankWrapper.getFilledPercentage(subject.injectTank));
    }

    public static Object gasInputSlot$getInputChemicalItem(TileEntityExtraDissolvingFactory subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(ComputerIInventorySlotWrapper.getStack(subject.gasInputSlot));
    }

    public static Object dumpChemical_0(TileEntityExtraDissolvingFactory subject, BaseComputerHelper helper) throws ComputerException {
        subject.dump();
        return helper.voidResult();
    }
}
