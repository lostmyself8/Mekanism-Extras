package com.jerry.generator_extras.common.content.naquadah;

import com.jerry.generator_extras.common.config.GenLoadConfig;
import com.jerry.generator_extras.common.item.ItemNquadahHohlraum;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorCasing;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorPort;
import com.jerry.mekanism_extras.common.registry.ExtraGases;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.NBTConstants;
import mekanism.api.chemical.gas.IGasHandler;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.heat.HeatAPI;
import mekanism.api.heat.IHeatCapacitor;
import mekanism.api.math.FloatingLong;
import mekanism.api.math.MathUtils;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.chemical.multiblock.MultiblockChemicalTankBuilder;
import mekanism.common.capabilities.energy.VariableCapacityEnergyContainer;
import mekanism.common.capabilities.fluid.VariableCapacityFluidTank;
import mekanism.common.capabilities.heat.ITileHeatHandler;
import mekanism.common.capabilities.heat.VariableHeatCapacitor;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.integration.computer.annotation.SyntheticComputerMethod;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.inventory.container.sync.dynamic.ContainerSync;
import mekanism.common.lib.multiblock.IValveHandler;
import mekanism.common.lib.multiblock.MultiblockData;
import com.jerry.generator_extras.common.genregistry.ExtraGenGases;
import mekanism.common.registries.MekanismGases;
import mekanism.common.tags.MekanismTags;
import mekanism.common.util.HeatUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.NBTUtils;
import mekanism.common.util.WorldUtils;
import com.jerry.mekanism_extras.common.ExtraTags;
import mekanism.generators.common.slot.ReactorInventorySlot;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class NaquadahReactorMultiblockData extends MultiblockData implements IValveHandler {
    public static final String HEAT_TAB = "heat";
    public static final String FUEL_TAB = "fuel";
    public static final String STATS_TAB = "stats";

    public static final int MAX_INJECTION = 98;//this is the effective cap in the GUI, as text field is limited to 2 chars
    //Reaction characteristics
    private static final double burnTemperature = 400_000_000;
    private static final double burnRatio = 1;
    //Thermal characteristics
    private static final double plasmaHeatCapacity = 100;
    private static final double caseHeatCapacity = 1;
    private static final double inverseInsulation = 100_000;
    //Heat transfer metrics
    private static final double plasmaCaseConductivity = 0.2;

    private final Set<ITileHeatHandler> heatHandlers = new ObjectOpenHashSet<>();

    @ContainerSync
    private boolean burning = false;

    @ContainerSync
    public IEnergyContainer energyContainer;
    public IHeatCapacitor heatCapacitor;

    @ContainerSync(tags = HEAT_TAB)
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerFluidTankWrapper.class, methodNames = {"getWater", "getWaterCapacity", "getWaterNeeded", "getWaterFilledPercentage"}, docPlaceholder = "water tank")
    public IExtendedFluidTank waterTank;
    @ContainerSync(tags = HEAT_TAB)
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getSteam", "getSteamCapacity", "getSteamNeeded", "getSteamFilledPercentage"}, docPlaceholder = "steam tank")
    public IGasTank steamTank;

    private double biomeAmbientTemp;
    @ContainerSync(tags = HEAT_TAB)
    private double lastPlasmaTemperature;
    @ContainerSync
    private double lastCaseTemperature;
    @ContainerSync
    @SyntheticComputerMethod(getter = "getEnvironmentalLoss")
    public double lastEnvironmentLoss;
    @ContainerSync
    @SyntheticComputerMethod(getter = "getTransferLoss")
    public double lastTransferLoss;

    @ContainerSync(tags = FUEL_TAB)
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getNaquadah", "getNaquadahCapacity", "getNaquadahNeeded", "getNaquadahFilledPercentage"}, docPlaceholder = "naquadah tank")
    public IGasTank naquadahTank;
    @ContainerSync(tags = FUEL_TAB)
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getUranium", "getUraniumCapacity", "getUraniumNeeded", "getUraniumFilledPercentage"}, docPlaceholder = "uranium tank")
    public IGasTank uraniumTank;
    @ContainerSync(tags = FUEL_TAB)
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getNqUFuel", "getNqUFuelCapacity", "getNqUFuelNeeded", "getNqUFuelFilledPercentage"}, docPlaceholder = "fuel tank")
    public IGasTank fuelTank;
    @ContainerSync(tags = {FUEL_TAB, HEAT_TAB, STATS_TAB}, getter = "getInjectionRate", setter = "setInjectionRate")
    private int injectionRate = 2;
    @ContainerSync(tags = {FUEL_TAB, HEAT_TAB, STATS_TAB})
    private long lastBurned;

    public double plasmaTemperature;

    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getHohlraum", docPlaceholder = "Hohlraum slot")
    final ReactorInventorySlot reactorSlot;

    private boolean clientBurning;
    private double clientTemp;

    private int maxWater;
    private long maxSteam;

    private AABB deathZone;

    public NaquadahReactorMultiblockData(TileEntityNaquadahReactorCasing tile) {
        super(tile);
        //Default biome temp to the ambient temperature at the block we are at
        biomeAmbientTemp = HeatAPI.getAmbientTemp(tile.getLevel(), tile.getTilePos());
        lastPlasmaTemperature = biomeAmbientTemp;
        lastCaseTemperature = biomeAmbientTemp;
        plasmaTemperature = biomeAmbientTemp;
        gasTanks.add(naquadahTank = MultiblockChemicalTankBuilder.GAS.input(this, GenLoadConfig.generatorConfig.reactorFuelCapacity,
                ExtraTags.Gases.RICH_NAQUADAH_FUEL_LOOKUP::contains, this));
        gasTanks.add(uraniumTank = MultiblockChemicalTankBuilder.GAS.input(this, GenLoadConfig.generatorConfig.reactorFuelCapacity,
                ExtraTags.Gases.RICH_URANIUM_FUEL_LOOKUP::contains, this));
        gasTanks.add(fuelTank = MultiblockChemicalTankBuilder.GAS.input(this, GenLoadConfig.generatorConfig.reactorFuelCapacity,
                ExtraTags.Gases.NAQUADAH_URANIUM_FUEL_LOOKUP::contains, createSaveAndComparator()));
        gasTanks.add(steamTank = MultiblockChemicalTankBuilder.GAS.output(this, this::getMaxSteam, gas -> gas == ExtraGenGases.POLONIUM_CONTAINING_STEAM.getChemical() || gas == MekanismGases.STEAM.getChemical(), this));
        fluidTanks.add(waterTank = VariableCapacityFluidTank.input(this, this::getMaxWater, fluid -> MekanismTags.Fluids.WATER_LOOKUP.contains(fluid.getFluid()), this));
        energyContainers.add(energyContainer = VariableCapacityEnergyContainer.output(GenLoadConfig.generatorConfig.reactorEnergyCapacity, this));
        heatCapacitors.add(heatCapacitor = VariableHeatCapacitor.create(caseHeatCapacity, NaquadahReactorMultiblockData::getInverseConductionCoefficient,
                () -> inverseInsulation, () -> biomeAmbientTemp, this));
        inventorySlots.add(reactorSlot = ReactorInventorySlot.at(stack -> stack.getItem() instanceof ItemNquadahHohlraum, this, 80, 39));
    }

    @Override
    public void onCreated(Level world) {
        super.onCreated(world);
        for (ValveData data : valves) {
            BlockEntity tile = WorldUtils.getTileEntity(world, data.location);
            if (tile instanceof TileEntityNaquadahReactorPort port) {
                heatHandlers.add(port);
            }
        }
        biomeAmbientTemp = calculateAverageAmbientTemperature(world);
        deathZone = new AABB(getMinPos().offset(1, 1, 1), getMaxPos());
    }

    @Override
    public void readUpdateTag(CompoundTag tag) {
        super.readUpdateTag(tag);
        NBTUtils.setDoubleIfPresent(tag, NBTConstants.PLASMA_TEMP, this::setLastPlasmaTemp);
        NBTUtils.setBooleanIfPresent(tag, NBTConstants.BURNING, this::setBurning);
    }

    @Override
    public void writeUpdateTag(CompoundTag tag) {
        super.writeUpdateTag(tag);
        tag.putDouble(NBTConstants.PLASMA_TEMP, getLastPlasmaTemp());
        tag.putBoolean(NBTConstants.BURNING, isBurning());
    }

    public void addTemperatureFromEnergyInput(FloatingLong energyAdded) {
        if (isBurning()) {
            setPlasmaTemp(getPlasmaTemp() + energyAdded.divide(plasmaHeatCapacity).doubleValue());
        } else {
            setPlasmaTemp(getPlasmaTemp() + energyAdded.divide(plasmaHeatCapacity).multiply(10).doubleValue());
        }
    }

    private boolean hasHohlraum() {
        if (!reactorSlot.isEmpty()) {
            ItemStack hohlraum = reactorSlot.getStack();
            if (hohlraum.getItem() instanceof ItemNquadahHohlraum) {
                Optional<IGasHandler> capability = hohlraum.getCapability(Capabilities.GAS_HANDLER).resolve();
                if (capability.isPresent()) {
                    IGasHandler gasHandlerItem = capability.get();
                    if (gasHandlerItem.getTanks() > 0) {
                        //Validate something didn't go terribly wrong, and we actually do have the tank we expect to have
                        return gasHandlerItem.getChemicalInTank(0).getAmount() == gasHandlerItem.getTankCapacity(0);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean tick(Level world) {
        boolean needsPacket = super.tick(world);
        long fuelBurned = 0;
        //Only thermal transfer happens unless we're hot enough to burn.
        if (getPlasmaTemp() >= burnTemperature) {
            //If we're not burning, yet we need a hohlraum to ignite
            if (!burning && hasHohlraum() && injectionRate >= 4) {
                //消耗黑体（点燃反应堆）
                vaporiseHohlraum();
            }

            //Only inject fuel if we're burning
            if (isBurning()) {
                injectFuel();
                fuelBurned = burnFuel();
                if (fuelBurned == 0) {
                    setBurning(false);
                }
            }
        } else {
            setBurning(false);
        }

        //正常情况都为
        if (lastBurned != fuelBurned) {
            lastBurned = fuelBurned;
        }

        //Perform the heat transfer calculations
        transferHeat();
        updateHeatCapacitors(null);
        updateTemperatures();

        if (isBurning()) {
            kill(world);
        }

        if (isBurning() != clientBurning || Math.abs(getLastPlasmaTemp() - clientTemp) > 1_000_000) {
            clientBurning = isBurning();
            clientTemp = getLastPlasmaTemp();
            needsPacket = true;
        }
        return needsPacket;
    }

    public void updateTemperatures() {
        lastPlasmaTemperature = getPlasmaTemp();
        lastCaseTemperature = heatCapacitor.getTemperature();
    }

    private void kill(Level world) {
        if (world.getRandom().nextInt() % 20 != 0) {
            return;
        }
        List<Entity> entitiesToDie = getWorld().getEntitiesOfClass(Entity.class, deathZone);

        for (Entity entity : entitiesToDie) {
            entity.hurt(entity.damageSources().magic(), 50_000F);
        }
    }

    private void vaporiseHohlraum() {
        ItemStack hohlraum = reactorSlot.getStack();
        Optional<IGasHandler> capability = hohlraum.getCapability(Capabilities.GAS_HANDLER).resolve();
        if (capability.isPresent()) {
            IGasHandler gasHandlerItem = capability.get();
            if (gasHandlerItem.getTanks() > 0) {
                fuelTank.insert(gasHandlerItem.getChemicalInTank(0), Action.EXECUTE, AutomationType.INTERNAL);
                lastPlasmaTemperature = getPlasmaTemp();
                reactorSlot.setEmpty();
                //点燃反应堆
                setBurning(true);
            }
        }
    }

    //往燃料槽添加燃料
    private void injectFuel() {
        long amountNeeded = fuelTank.getNeeded();
        long amountAvailable = 2 * Math.min(naquadahTank.getStored(), uraniumTank.getStored());
        long amountToInject = Math.min(amountNeeded, Math.min(amountAvailable, injectionRate));
        amountToInject -= amountToInject % 2;
        long injectingAmount = amountToInject / 2;
        MekanismUtils.logMismatchedStackSize(naquadahTank.shrinkStack(injectingAmount, Action.EXECUTE), injectingAmount);
        MekanismUtils.logMismatchedStackSize(uraniumTank.shrinkStack(injectingAmount, Action.EXECUTE), injectingAmount);
        fuelTank.insert(ExtraGases.NAQUADAH_URANIUM_FUEL.getStack(amountToInject), Action.EXECUTE, AutomationType.INTERNAL);
    }

    //消耗燃料
    private long burnFuel() {
        //(lastPlasmaTemperature - burnTemperature) * burnRatio < 0 值为 0
        //(lastPlasmaTemperature - burnTemperature) * burnRatio >= 0 值为 (lastPlasmaTemperature - burnTemperature) * burnRatio 和 fuelTank.getStored() 中较小的一个
        //当温度低于点燃温度返回0，即后反应堆停机
        long fuelBurned = MathUtils.clampToLong(Mth.clamp((lastPlasmaTemperature - burnTemperature) * burnRatio, 0, fuelTank.getStored()));
        MekanismUtils.logMismatchedStackSize(fuelTank.shrinkStack(fuelBurned, Action.EXECUTE), fuelBurned);
        setPlasmaTemp(getPlasmaTemp() + GenLoadConfig.generatorConfig.energyPerReactorFuel.get().multiply(fuelBurned).divide(plasmaHeatCapacity).doubleValue());
        return fuelBurned;
    }

    private void transferHeat() {
        //Transfer from plasma to casing
        double plasmaCaseHeat = plasmaCaseConductivity * (lastPlasmaTemperature - lastCaseTemperature);
        if (Math.abs(plasmaCaseHeat) > HeatAPI.EPSILON) {
            setPlasmaTemp(getPlasmaTemp() - plasmaCaseHeat / plasmaHeatCapacity);
            heatCapacitor.handleHeat(plasmaCaseHeat);
        }

        //Transfer from casing to fissile if necessary
        double caseWaterHeat = GenLoadConfig.generatorConfig.reactorWaterHeatingRatio.get() * (lastCaseTemperature - biomeAmbientTemp);
        if (Math.abs(caseWaterHeat) > HeatAPI.EPSILON) {
            int waterToSteam = (int) (HeatUtils.getSteamEnergyEfficiency() * caseWaterHeat / HeatUtils.getWaterThermalEnthalpy());
            waterToSteam = Math.min(waterToSteam, Math.min(waterTank.getFluidAmount(), MathUtils.clampToInt(steamTank.getNeeded())));
            if (waterToSteam > 0) {
                MekanismUtils.logMismatchedStackSize(waterTank.shrinkStack(waterToSteam, Action.EXECUTE), waterToSteam);
                if (isBurning()) {
                    steamTank.insert(ExtraGenGases.POLONIUM_CONTAINING_STEAM.getStack(waterToSteam), Action.EXECUTE, AutomationType.INTERNAL);
                } else {
                    steamTank.insert(MekanismGases.STEAM.getStack(waterToSteam), Action.EXECUTE, AutomationType.INTERNAL);
                }
                caseWaterHeat = waterToSteam * HeatUtils.getWaterThermalEnthalpy() / HeatUtils.getSteamEnergyEfficiency();
                heatCapacitor.handleHeat(-caseWaterHeat);
            }
        }

        HeatAPI.HeatTransfer heatTransfer = simulate();
        lastEnvironmentLoss = heatTransfer.environmentTransfer();
        lastTransferLoss = heatTransfer.adjacentTransfer();

        //Passive energy generation
        double caseAirHeat = GenLoadConfig.generatorConfig.reactorCasingThermalConductivity.get() * (lastCaseTemperature - biomeAmbientTemp);
        if (Math.abs(caseAirHeat) > HeatAPI.EPSILON) {
            heatCapacitor.handleHeat(-caseAirHeat);
            energyContainer.insert(FloatingLong.create(caseAirHeat * GenLoadConfig.generatorConfig.reactorThermocoupleEfficiency.get()), Action.EXECUTE, AutomationType.INTERNAL);
        }
    }

    @NotNull
    @Override
    public HeatAPI.HeatTransfer simulate() {
        double environmentTransfer = 0;
        double adjacentTransfer = 0;
        for (ITileHeatHandler source : heatHandlers) {
            HeatAPI.HeatTransfer heatTransfer = source.simulate();
            adjacentTransfer += heatTransfer.adjacentTransfer();
            environmentTransfer += heatTransfer.environmentTransfer();
        }
        return new HeatAPI.HeatTransfer(adjacentTransfer, environmentTransfer);
    }

    public void setLastPlasmaTemp(double temp) {
        lastPlasmaTemperature = temp;
    }

    @ComputerMethod(nameOverride = "getPlasmaTemperature")
    public double getLastPlasmaTemp() {
        return lastPlasmaTemperature;
    }

    @ComputerMethod(nameOverride = "getCaseTemperature")
    public double getLastCaseTemp() {
        return lastCaseTemperature;
    }

    public double getPlasmaTemp() {
        return plasmaTemperature;
    }

    public void setPlasmaTemp(double temp) {
        if (plasmaTemperature != temp) {
            plasmaTemperature = temp;
            markDirty();
        }
    }

    @ComputerMethod
    public int getInjectionRate() {
        return injectionRate;
    }

    public void setInjectionRate(int rate) {
        if (injectionRate != rate) {
            injectionRate = rate;
            maxWater = injectionRate * GenLoadConfig.generatorConfig.reactorWaterPerInjection.get();
            maxSteam = injectionRate * GenLoadConfig.generatorConfig.reactorSteamPerInjection.get();
            if (getWorld() != null && !isRemote()) {
                if (!waterTank.isEmpty()) {
                    waterTank.setStackSize(Math.min(waterTank.getFluidAmount(), waterTank.getCapacity()), Action.EXECUTE);
                }
                if (!steamTank.isEmpty()) {
                    steamTank.setStackSize(Math.min(steamTank.getStored(), steamTank.getCapacity()), Action.EXECUTE);
                }
            }
            markDirty();
        }
    }

    public int getMaxWater() {
        return maxWater;
    }

    public long getMaxSteam() {
        return maxSteam;
    }

    public boolean isBurning() {
        return burning;
    }

    public void setBurning(boolean burn) {
        if (burning != burn) {
            burning = burn;
            markDirty();
        }
    }

    public double getCaseTemp() {
        return heatCapacitor.getTemperature();
    }

    @Override
    protected int getMultiblockRedstoneLevel() {
        return MekanismUtils.redstoneLevelFromContents(fuelTank.getStored(), fuelTank.getCapacity());
    }

    @ComputerMethod(methodDescription = "true -> fissile cooled, false -> air cooled")
    public int getMinInjectionRate(boolean active) {
        double k = active ? GenLoadConfig.generatorConfig.reactorWaterHeatingRatio.get() : 0;
        double caseAirConductivity = GenLoadConfig.generatorConfig.reactorCasingThermalConductivity.get();
        double aMin = burnTemperature * burnRatio * plasmaCaseConductivity * (k + caseAirConductivity) /
                (GenLoadConfig.generatorConfig.energyPerReactorFuel.get().doubleValue() * burnRatio * (plasmaCaseConductivity + k + caseAirConductivity) -
                        plasmaCaseConductivity * (k + caseAirConductivity));
        return (int) (2 * Math.ceil(aMin / 2D));
    }

    @ComputerMethod(methodDescription = "true -> fissile cooled, false -> air cooled")
    public double getMaxPlasmaTemperature(boolean active) {
        double k = active ? GenLoadConfig.generatorConfig.reactorWaterHeatingRatio.get() : 0;
        double caseAirConductivity = GenLoadConfig.generatorConfig.reactorCasingThermalConductivity.get();
        long injectionRate = Math.max(this.injectionRate, lastBurned);
        return injectionRate * GenLoadConfig.generatorConfig.energyPerReactorFuel.get().doubleValue() / plasmaCaseConductivity *
                (plasmaCaseConductivity + k + caseAirConductivity) / (k + caseAirConductivity);
    }

    @ComputerMethod(methodDescription = "true -> fissile cooled, false -> air cooled")
    public double getMaxCasingTemperature(boolean active) {
        double k = active ? GenLoadConfig.generatorConfig.reactorWaterHeatingRatio.get() : 0;
        long injectionRate = Math.max(this.injectionRate, lastBurned);
        return GenLoadConfig.generatorConfig.energyPerReactorFuel.get().multiply(injectionRate)
                .divide(k + GenLoadConfig.generatorConfig.reactorCasingThermalConductivity.get()).doubleValue();
    }

    @ComputerMethod(methodDescription = "true -> fissile cooled, false -> air cooled")
    public double getIgnitionTemperature(boolean active) {
        double k = active ? GenLoadConfig.generatorConfig.reactorWaterHeatingRatio.get() : 0;
        double caseAirConductivity = GenLoadConfig.generatorConfig.reactorCasingThermalConductivity.get();
        double energyPerReactorFuel = GenLoadConfig.generatorConfig.energyPerReactorFuel.get().doubleValue();
        return burnTemperature * energyPerReactorFuel * burnRatio * (plasmaCaseConductivity + k + caseAirConductivity) /
                (energyPerReactorFuel * burnRatio * (plasmaCaseConductivity + k + caseAirConductivity) - plasmaCaseConductivity * (k + caseAirConductivity));
    }

    public FloatingLong getPassiveGeneration(boolean active, boolean current) {
        double temperature = current ? getLastCaseTemp() : getMaxCasingTemperature(active);
        return FloatingLong.create(GenLoadConfig.generatorConfig.reactorThermocoupleEfficiency.get() *
                GenLoadConfig.generatorConfig.reactorCasingThermalConductivity.get() * temperature);
    }

    public long getSteamPerTick(boolean current) {
        double temperature = current ? getLastCaseTemp() : getMaxCasingTemperature(true);
        return MathUtils.clampToLong(HeatUtils.getSteamEnergyEfficiency() * GenLoadConfig.generatorConfig.reactorWaterHeatingRatio.get() * temperature / HeatUtils.getWaterThermalEnthalpy());
    }

    private static double getInverseConductionCoefficient() {
        return 1 / GenLoadConfig.generatorConfig.reactorCasingThermalConductivity.get();
    }

    //Computer related methods
    @ComputerMethod(nameOverride = "setInjectionRate")
    void computerSetInjectionRate(int rate) throws ComputerException {
        if (rate < 0 || rate > MAX_INJECTION) {
            //Validate bounds even though we can clamp
            throw new ComputerException("Injection Rate '%d' is out of range must be an even number between 0 and %d. (Inclusive)", rate, MAX_INJECTION);
        } else if (rate % 2 != 0) {
            //Validate it is even
            throw new ComputerException("Injection Rate '%d' must be an even number between 0 and %d. (Inclusive)", rate, MAX_INJECTION);
        }
        setInjectionRate(rate);
    }

    @ComputerMethod
    FloatingLong getPassiveGeneration(boolean active) {
        return getPassiveGeneration(active, false);
    }

    @ComputerMethod
    FloatingLong getProductionRate() {
        return getPassiveGeneration(false, true);
    }
    //End computer related methods
}
