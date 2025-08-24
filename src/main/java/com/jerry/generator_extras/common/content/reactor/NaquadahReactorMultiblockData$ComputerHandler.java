package com.jerry.generator_extras.common.content.reactor;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.math.FloatingLong;
import mekanism.common.integration.computer.*;
import mekanism.common.integration.computer.annotation.MethodFactory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

@MethodFactory(target = NaquadahReactorMultiblockData.class)
public class NaquadahReactorMultiblockData$ComputerHandler extends ComputerMethodFactory<NaquadahReactorMultiblockData> {

    private final String[] NAMES_rate = new String[]{"rate"};

    private final String[] NAMES_active = new String[]{"active"};

    private final Class[] TYPES_3db6c47 = new Class[]{Boolean.TYPE};

    private final Class[] TYPES_1980e = new Class[]{Integer.TYPE};

    public NaquadahReactorMultiblockData$ComputerHandler() {
        register(MethodData.builder("getWater", NaquadahReactorMultiblockData$ComputerHandler::waterTank$getWater).returnType(FluidStack.class).methodDescription("Get the contents of the water tank."));
        register(MethodData.builder("getWaterCapacity", NaquadahReactorMultiblockData$ComputerHandler::waterTank$getWaterCapacity).returnType(Integer.TYPE).methodDescription("Get the capacity of the water tank."));
        register(MethodData.builder("getWaterNeeded", NaquadahReactorMultiblockData$ComputerHandler::waterTank$getWaterNeeded).returnType(Integer.TYPE).methodDescription("Get the amount needed to fill the water tank."));
        register(MethodData.builder("getWaterFilledPercentage", NaquadahReactorMultiblockData$ComputerHandler::waterTank$getWaterFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the water tank."));
        register(MethodData.builder("getSteam", NaquadahReactorMultiblockData$ComputerHandler::steamTank$getSteam).returnType(ChemicalStack.class).methodDescription("Get the contents of the steam tank."));
        register(MethodData.builder("getSteamCapacity", NaquadahReactorMultiblockData$ComputerHandler::steamTank$getSteamCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the steam tank."));
        register(MethodData.builder("getSteamNeeded", NaquadahReactorMultiblockData$ComputerHandler::steamTank$getSteamNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the steam tank."));
        register(MethodData.builder("getSteamFilledPercentage", NaquadahReactorMultiblockData$ComputerHandler::steamTank$getSteamFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the steam tank."));
        register(MethodData.builder("getEnvironmentalLoss", NaquadahReactorMultiblockData$ComputerHandler::getEnvironmentalLoss_0).returnType(Double.TYPE));
        register(MethodData.builder("getTransferLoss", NaquadahReactorMultiblockData$ComputerHandler::getTransferLoss_0).returnType(Double.TYPE));
        register(MethodData.builder("getNaquadah", NaquadahReactorMultiblockData$ComputerHandler::naquadahTank$getNaquadah).returnType(ChemicalStack.class).methodDescription("Get the contents of the naquadah tank."));
        register(MethodData.builder("getNaquadahCapacity", NaquadahReactorMultiblockData$ComputerHandler::naquadahTank$getNaquadahCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the naquadah tank."));
        register(MethodData.builder("getNaquadahNeeded", NaquadahReactorMultiblockData$ComputerHandler::naquadahTank$getNaquadahNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the naquadah tank."));
        register(MethodData.builder("getNaquadahFilledPercentage", NaquadahReactorMultiblockData$ComputerHandler::naquadahTank$getNaquadahFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the naquadah tank."));
        register(MethodData.builder("getUranium", NaquadahReactorMultiblockData$ComputerHandler::uraniumTank$getUranium).returnType(ChemicalStack.class).methodDescription("Get the contents of the uranium tank."));
        register(MethodData.builder("getUraniumCapacity", NaquadahReactorMultiblockData$ComputerHandler::uraniumTank$getUraniumCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the uranium tank."));
        register(MethodData.builder("getUraniumNeeded", NaquadahReactorMultiblockData$ComputerHandler::uraniumTank$getUraniumNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the uranium tank."));
        register(MethodData.builder("getUraniumFilledPercentage", NaquadahReactorMultiblockData$ComputerHandler::uraniumTank$getUraniumFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the uranium tank."));
        register(MethodData.builder("getNqUFuel", NaquadahReactorMultiblockData$ComputerHandler::fuelTank$getNqUFuel).returnType(ChemicalStack.class).methodDescription("Get the contents of the fuel tank."));
        register(MethodData.builder("getNqUFuelCapacity", NaquadahReactorMultiblockData$ComputerHandler::fuelTank$getNqUFuelCapacity).returnType(Long.TYPE).methodDescription("Get the capacity of the fuel tank."));
        register(MethodData.builder("getNqUFuelNeeded", NaquadahReactorMultiblockData$ComputerHandler::fuelTank$getNqUFuelNeeded).returnType(Long.TYPE).methodDescription("Get the amount needed to fill the fuel tank."));
        register(MethodData.builder("getNqUFuelFilledPercentage", NaquadahReactorMultiblockData$ComputerHandler::fuelTank$getNqUFuelFilledPercentage).returnType(Double.TYPE).methodDescription("Get the filled percentage of the fuel tank."));
        register(MethodData.builder("getHohlraum", NaquadahReactorMultiblockData$ComputerHandler::reactorSlot$getHohlraum).returnType(ItemStack.class).methodDescription("Get the contents of the Hohlraum slot."));
        register(MethodData.builder("getPlasmaTemperature", NaquadahReactorMultiblockData$ComputerHandler::getPlasmaTemperature_0).returnType(Double.TYPE));
        register(MethodData.builder("getCaseTemperature", NaquadahReactorMultiblockData$ComputerHandler::getCaseTemperature_0).returnType(Double.TYPE));
        register(MethodData.builder("getInjectionRate", NaquadahReactorMultiblockData$ComputerHandler::getInjectionRate_0).returnType(Integer.TYPE));
        register(MethodData.builder("isIgnited", NaquadahReactorMultiblockData$ComputerHandler::isIgnited_0).returnType(boolean.class).methodDescription("Checks if a reaction is occurring."));
        register(MethodData.builder("getMinInjectionRate", NaquadahReactorMultiblockData$ComputerHandler::getMinInjectionRate_1).returnType(Integer.TYPE).methodDescription("true -> water cooled, false -> air cooled").arguments(NAMES_active, TYPES_3db6c47));
        register(MethodData.builder("getMaxPlasmaTemperature", NaquadahReactorMultiblockData$ComputerHandler::getMaxPlasmaTemperature_1).returnType(Double.TYPE).methodDescription("true -> water cooled, false -> air cooled").arguments(NAMES_active, TYPES_3db6c47));
        register(MethodData.builder("getMaxCasingTemperature", NaquadahReactorMultiblockData$ComputerHandler::getMaxCasingTemperature_1).returnType(Double.TYPE).methodDescription("true -> water cooled, false -> air cooled").arguments(NAMES_active, TYPES_3db6c47));
        register(MethodData.builder("getIgnitionTemperature", NaquadahReactorMultiblockData$ComputerHandler::getIgnitionTemperature_1).returnType(Double.TYPE).methodDescription("true -> water cooled, false -> air cooled").arguments(NAMES_active, TYPES_3db6c47));
        register(MethodData.builder("setInjectionRate", NaquadahReactorMultiblockData$ComputerHandler::setInjectionRate_1).arguments(NAMES_rate, TYPES_1980e));
        register(MethodData.builder("getPassiveGeneration", NaquadahReactorMultiblockData$ComputerHandler::getPassiveGeneration_1).returnType(FloatingLong.class).arguments(NAMES_active, TYPES_3db6c47));
        register(MethodData.builder("getProductionRate", NaquadahReactorMultiblockData$ComputerHandler::getProductionRate_0).returnType(FloatingLong.class));
    }

    public static Object waterTank$getWater(NaquadahReactorMultiblockData subject,
                                            BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getStack(subject.waterTank));
    }

    public static Object waterTank$getWaterCapacity(NaquadahReactorMultiblockData subject,
                                                    BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getCapacity(subject.waterTank));
    }

    public static Object waterTank$getWaterNeeded(NaquadahReactorMultiblockData subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getNeeded(subject.waterTank));
    }

    public static Object waterTank$getWaterFilledPercentage(NaquadahReactorMultiblockData subject,
                                                            BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerFluidTankWrapper.getFilledPercentage(subject.waterTank));
    }

    public static Object steamTank$getSteam(NaquadahReactorMultiblockData subject,
                                            BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.steamTank));
    }

    public static Object steamTank$getSteamCapacity(NaquadahReactorMultiblockData subject,
                                                    BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.steamTank));
    }

    public static Object steamTank$getSteamNeeded(NaquadahReactorMultiblockData subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.steamTank));
    }

    public static Object steamTank$getSteamFilledPercentage(NaquadahReactorMultiblockData subject,
                                                            BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.steamTank));
    }

    public static Object getEnvironmentalLoss_0(NaquadahReactorMultiblockData subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.lastEnvironmentLoss);
    }

    public static Object getTransferLoss_0(NaquadahReactorMultiblockData subject,
                                           BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.lastTransferLoss);
    }

    public static Object naquadahTank$getNaquadah(NaquadahReactorMultiblockData subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.naquadahTank));
    }

    public static Object naquadahTank$getNaquadahCapacity(NaquadahReactorMultiblockData subject,
                                                          BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.naquadahTank));
    }

    public static Object naquadahTank$getNaquadahNeeded(NaquadahReactorMultiblockData subject,
                                                        BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.naquadahTank));
    }

    public static Object naquadahTank$getNaquadahFilledPercentage(
            NaquadahReactorMultiblockData subject, BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.naquadahTank));
    }

    public static Object uraniumTank$getUranium(NaquadahReactorMultiblockData subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.uraniumTank));
    }

    public static Object uraniumTank$getUraniumCapacity(NaquadahReactorMultiblockData subject,
                                                        BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.uraniumTank));
    }

    public static Object uraniumTank$getUraniumNeeded(NaquadahReactorMultiblockData subject,
                                                      BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.uraniumTank));
    }

    public static Object uraniumTank$getUraniumFilledPercentage(NaquadahReactorMultiblockData subject,
                                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.uraniumTank));
    }

    public static Object fuelTank$getNqUFuel(NaquadahReactorMultiblockData subject,
                                             BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getStack(subject.fuelTank));
    }

    public static Object fuelTank$getNqUFuelCapacity(NaquadahReactorMultiblockData subject,
                                                     BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getCapacity(subject.fuelTank));
    }

    public static Object fuelTank$getNqUFuelNeeded(NaquadahReactorMultiblockData subject,
                                                   BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getNeeded(subject.fuelTank));
    }

    public static Object fuelTank$getNqUFuelFilledPercentage(NaquadahReactorMultiblockData subject,
                                                             BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.getFilledPercentage(subject.fuelTank));
    }

    public static Object reactorSlot$getHohlraum(NaquadahReactorMultiblockData subject,
                                                 BaseComputerHelper helper) throws ComputerException {
        return helper.convert(SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.getStack(subject.reactorSlot));
    }

    public static Object getPlasmaTemperature_0(NaquadahReactorMultiblockData subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getLastPlasmaTemp());
    }

    public static Object getCaseTemperature_0(NaquadahReactorMultiblockData subject,
                                              BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getLastCaseTemp());
    }

    public static Object getInjectionRate_0(NaquadahReactorMultiblockData subject,
                                            BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getInjectionRate());
    }

    public static Object isIgnited_0(NaquadahReactorMultiblockData subject, BaseComputerHelper helper)
            throws ComputerException {
        return helper.convert(subject.isBurning());
    }

    public static Object getMinInjectionRate_1(NaquadahReactorMultiblockData subject,
                                               BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getMinInjectionRate(helper.getBoolean(0)));
    }

    public static Object getMaxPlasmaTemperature_1(NaquadahReactorMultiblockData subject,
                                                   BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getMaxPlasmaTemperature(helper.getBoolean(0)));
    }

    public static Object getMaxCasingTemperature_1(NaquadahReactorMultiblockData subject,
                                                   BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getMaxCasingTemperature(helper.getBoolean(0)));
    }

    public static Object getIgnitionTemperature_1(NaquadahReactorMultiblockData subject,
                                                  BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getIgnitionTemperature(helper.getBoolean(0)));
    }

    public static Object setInjectionRate_1(NaquadahReactorMultiblockData subject,
                                            BaseComputerHelper helper) throws ComputerException {
        subject.computerSetInjectionRate(helper.getInt(0));
        return helper.voidResult();
    }

    public static Object getPassiveGeneration_1(NaquadahReactorMultiblockData subject,
                                                BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getPassiveGeneration(helper.getBoolean(0)));
    }

    public static Object getProductionRate_0(NaquadahReactorMultiblockData subject,
                                             BaseComputerHelper helper) throws ComputerException {
        return helper.convert(subject.getProductionRate());
    }
}
