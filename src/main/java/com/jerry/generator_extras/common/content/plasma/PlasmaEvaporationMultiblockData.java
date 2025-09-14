package com.jerry.generator_extras.common.content.plasma;

import com.jerry.generator_extras.common.config.GenLoadConfig;
import com.jerry.generator_extras.common.recipe.ExtraGenRecipeType;
import com.jerry.generator_extras.common.recipe.IExtraGenRecipeTypeProvider;
import com.jerry.generator_extras.common.recipe.lookup.IExtraGenSingleRecipeLookupHandler.FluidRecipeLookupHandler;
import com.jerry.generator_extras.common.recipe.lookup.cache.ExtraGenInputRecipeCache;
import com.jerry.generator_extras.common.recipe.lookup.monitor.ExtraGenRecipeCacheLookupMonitor;
import com.jerry.generator_extras.common.tile.plasma.TileEntityPlasmaEvaporationBlock;
import com.jerry.generator_extras.common.tile.plasma.TileEntityPlasmaEvaporationVent;
import com.jerry.mekanism_extras.api.ExtraNBTConstants;
import com.jerry.mekanism_extras.api.gas.attribute.ExtraGasAttributes.*;
import mekanism.api.Action;
import mekanism.api.Coord4D;
import mekanism.api.NBTConstants;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.chemical.gas.attribute.GasAttributes.Radiation;
import mekanism.api.heat.HeatAPI;
import mekanism.api.radiation.IRadiationManager;
import mekanism.api.recipes.FluidToFluidRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.OneInputCachedRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
import mekanism.common.capabilities.chemical.multiblock.MultiblockChemicalTankBuilder;
import mekanism.common.capabilities.fluid.BasicFluidTank;
import mekanism.common.capabilities.fluid.VariableCapacityFluidTank;
import mekanism.common.capabilities.heat.VariableHeatCapacitor;
import mekanism.common.config.MekanismConfig;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.SyntheticComputerMethod;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.container.sync.dynamic.ContainerSync;
import mekanism.common.inventory.slot.FluidInventorySlot;
import mekanism.common.inventory.slot.OutputInventorySlot;
import mekanism.common.lib.multiblock.IValveHandler;
import mekanism.common.lib.multiblock.MultiblockData;
import mekanism.common.tile.prefab.TileEntityRecipeMachine;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.NBTUtils;
import mekanism.common.util.WorldUtils;
import mekanism.generators.common.content.turbine.TurbineMultiblockData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BooleanSupplier;

public class PlasmaEvaporationMultiblockData
        extends MultiblockData
        implements IValveHandler, FluidRecipeLookupHandler<FluidToFluidRecipe> {

    private static final List<CachedRecipe.OperationTracker.RecipeError> TRACKED_ERROR_TYPES = List.of(
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT,
            CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT
    );
    public static final int MAX_HEIGHT = 36;

    @ContainerSync
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerFluidTankWrapper.class, methodNames = {"getInput", "getInputCapacity", "getInputNeeded", "getInputFilledPercentage"}, docPlaceholder = "input tank")
    public BasicFluidTank inputTank;
    @ContainerSync
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerFluidTankWrapper.class, methodNames = {"getOutput", "getOutputCapacity", "getOutputNeeded", "getOutputFilledPercentage"}, docPlaceholder = "output tank")
    public BasicFluidTank outputTank;
    @ContainerSync
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getPlasmaInput", "getPlasmaInputCapacity", "getPlasmaInputNeeded", "getPlasmaInputFilledPercentage"}, docPlaceholder = "plasma input tank")
    public IGasTank plasmaInputTank;
    @ContainerSync
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getPlasmaOutput", "getPlasmaOutputCapacity", "getPlasmaOutputNeeded", "getPlasmaOutputFilledPercentage"}, docPlaceholder = "plasma output tank")
    public IGasTank plasmaOutputTank;
    @ContainerSync
    public VariableHeatCapacitor heatCapacitor;

    private double biomeAmbientTemp;
    private double tempMultiplier;

    private int inputTankCapacity;
    private int inputPlasmaTankCapacity;
    public float prevScale;
    @ContainerSync
    @SyntheticComputerMethod(getter = "getProductionAmount")
    public double lastGain;
    @ContainerSync
    @SyntheticComputerMethod(getter = "getPlasmaConsumption")
    public double lastPlasmaConsumption;

    private final ExtraGenRecipeCacheLookupMonitor<FluidToFluidRecipe> recipeCacheLookupMonitor;
    private final BooleanSupplier recheckAllRecipeErrors;
    @ContainerSync
    private final boolean[] trackedErrors = new boolean[TRACKED_ERROR_TYPES.size()];

    private final IOutputHandler<@NotNull FluidStack> outputHandler;
    private final IInputHandler<@NotNull FluidStack> inputHandler;

    // TODO: add item input and output slot
//    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getInputItemInput", docPlaceholder = "input side's input slot")
//    final FluidInventorySlot inputInputSlot;
//    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getInputItemOutput", docPlaceholder = "input side's output slot")
//    final OutputInventorySlot outputInputSlot;
//    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutputItemInput", docPlaceholder = "output side's input slot")
//    final FluidInventorySlot inputOutputSlot;
//    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutputItemOutput", docPlaceholder = "output side's output slot")
//    final OutputInventorySlot outputOutputSlot;

    @ContainerSync
    public int lowerVolume; // fluid volume
    @ContainerSync
    public int higherVolume; // plasma volume
    public int insulationLayerY;

    @ContainerSync
    @SyntheticComputerMethod(getter = "getVents")
    public int vents;
    private List<VentData> ventData = Collections.emptyList();

    public PlasmaEvaporationMultiblockData(TileEntityPlasmaEvaporationBlock tile) {
        super(tile);
        recipeCacheLookupMonitor = new ExtraGenRecipeCacheLookupMonitor<>(this);
        recheckAllRecipeErrors = TileEntityRecipeMachine.shouldRecheckAllErrors(tile);
        //Default biome temp to the ambient temperature at the block we are at
        biomeAmbientTemp = HeatAPI.getAmbientTemp(tile.getLevel(), tile.getTilePos());
        fluidTanks.add(inputTank = VariableCapacityFluidTank.input(this, this::getMaxFluid, this::containsRecipe, createSaveAndComparator(recipeCacheLookupMonitor)));
        fluidTanks.add(outputTank = VariableCapacityFluidTank.output(this, GenLoadConfig.generatorConfig.plasmaEvaporationOutputFluidTankCapacity, BasicFluidTank.alwaysTrue, this));
        inputHandler = InputHelper.getInputHandler(inputTank, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_INPUT);
        outputHandler = OutputHelper.getOutputHandler(outputTank, CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
//        inventorySlots.add(inputInputSlot = FluidInventorySlot.fill(inputTank, this, 28, 20));
//        inventorySlots.add(outputInputSlot = OutputInventorySlot.at(this, 28, 51));
//        inventorySlots.add(inputOutputSlot = FluidInventorySlot.drain(outputTank, this, 132, 20));
//        inventorySlots.add(outputOutputSlot = OutputInventorySlot.at(this, 132, 51));
//        inputInputSlot.setSlotType(ContainerSlotType.INPUT);
//        inputOutputSlot.setSlotType(ContainerSlotType.INPUT);
        gasTanks.add(plasmaInputTank = MultiblockChemicalTankBuilder.GAS.input(this, () -> inputPlasmaTankCapacity,
                gas -> gas.has(Heatant.class), this));
        gasTanks.add(plasmaOutputTank = MultiblockChemicalTankBuilder.GAS.output(this, GenLoadConfig.generatorConfig.plasmaEvaporationOutputPlasmaTankCapacity::get,
                gas -> true, this));
        heatCapacitors.add(heatCapacitor = VariableHeatCapacitor.create(GenLoadConfig.generatorConfig.plasmaEvaporationHeatCapacity.get() * 3, () -> biomeAmbientTemp, this));
    }

    @Override
    public void onCreated(Level world) {
        super.onCreated(world);
        biomeAmbientTemp = calculateAverageAmbientTemperature(world);
        // update the heat capacity now that we've read
        heatCapacitor.setHeatCapacity(MekanismConfig.general.evaporationHeatCapacity.get() * height(), true);
    }

    @Override
    public boolean tick(Level world) {
        boolean needsPacket = super.tick(world);
        // update temperature
        updateHeatCapacitors(null);
        consumePlasmaAndHeatUp();
        //After we update the heat capacitors, update our temperature multiplier
        tempMultiplier = getTemperature() * GenLoadConfig.generatorConfig.plasmaEvaporationTempMultiplier.get() *
                ((double) height() / MAX_HEIGHT);
//        inputOutputSlot.drainTank(outputOutputSlot);
//        inputInputSlot.fillTank(outputInputSlot);
        recipeCacheLookupMonitor.updateAndProcess();
        float scale = MekanismUtils.getScale(prevScale, inputTank);
        if (scale != prevScale) {
            prevScale = scale;
            needsPacket = true;
        }
        return needsPacket;
    }

    @Override
    public void readUpdateTag(CompoundTag tag) {
        super.readUpdateTag(tag);
        NBTUtils.setFluidStackIfPresent(tag, NBTConstants.FLUID_STORED, fluid -> inputTank.setStack(fluid));
        NBTUtils.setFloatIfPresent(tag, NBTConstants.SCALE, scale -> prevScale = scale);
        NBTUtils.setGasStackIfPresent(tag, NBTConstants.GAS_STORED, gas -> plasmaInputTank.setStack(gas));
        NBTUtils.setGasStackIfPresent(tag, NBTConstants.GAS_STORED_ALT, gas -> plasmaOutputTank.setStack(gas));
        NBTUtils.setIntIfPresent(tag, NBTConstants.LOWER_VOLUME, value -> lowerVolume = value);
        NBTUtils.setIntIfPresent(tag, ExtraNBTConstants.HIGHER_VOLUME, value -> higherVolume = value);
        readValves(tag);
    }

    @Override
    public void writeUpdateTag(CompoundTag tag) {
        super.writeUpdateTag(tag);
        tag.put(NBTConstants.FLUID_STORED, inputTank.getFluid().writeToNBT(new CompoundTag()));
        tag.putFloat(NBTConstants.SCALE, prevScale);
        tag.put(NBTConstants.GAS_STORED, plasmaInputTank.getStack().write(new CompoundTag()));
        tag.put(NBTConstants.GAS_STORED_ALT, plasmaOutputTank.getStack().write(new CompoundTag()));
        tag.putInt(NBTConstants.LOWER_VOLUME, lowerVolume);
        tag.putInt(ExtraNBTConstants.HIGHER_VOLUME, higherVolume);
        writeValves(tag);
    }

    @Override
    public void setVolume(int volume) {
        if (getVolume() != volume) {
            super.setVolume(volume);
            inputTankCapacity = lowerVolume * GenLoadConfig.generatorConfig.plasmaEvaporationFluidPerTank.get();
            inputPlasmaTankCapacity = higherVolume * GenLoadConfig.generatorConfig.plasmaEvaporationPlasmaPerTank.get();
        }
    }

    public int getMaxFluid() {
        return inputTankCapacity;
    }

    @NotNull
    @Override
    public IExtraGenRecipeTypeProvider<FluidToFluidRecipe, ExtraGenInputRecipeCache.SingleFluid<FluidToFluidRecipe>> getRecipeType() {
        return ExtraGenRecipeType.PLASMA_EVAPORATION;
    }

    @Nullable
    @Override
    public FluidToFluidRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(inputHandler);
    }

    @Override
    public void clearRecipeErrors(int cacheIndex) {
        Arrays.fill(trackedErrors, false);
    }

    @NotNull
    @Override
    public CachedRecipe<FluidToFluidRecipe> createNewCachedRecipe(@NotNull FluidToFluidRecipe recipe, int cacheIndex) {
        return OneInputCachedRecipe.fluidToFluid(recipe, recheckAllRecipeErrors, inputHandler, outputHandler)
                .setErrorsChanged(errors -> {
                    for (int i = 0; i < trackedErrors.length; i++) {
                        trackedErrors[i] = errors.contains(TRACKED_ERROR_TYPES.get(i));
                    }
                })
                .setActive(active -> {
                    //TODO: Make the numbers for lastGain be based on how much the recipe provides as an output rather than "assuming" it is 1 mB
                    // Also fix that the numbers don't quite accurately reflect the values as we modify number of operations, and not have a fractional
                    // amount
                    if (active) {
                        if (tempMultiplier > 0 && tempMultiplier < 1) {
                            lastGain = 1F / (int) Math.ceil(1 / tempMultiplier);
                        } else {
                            lastGain = tempMultiplier;
                        }
                        lastPlasmaConsumption = lastGain / GenLoadConfig.generatorConfig.plasmaEvaporationPlasmaConsumeRatio.get();
                    } else {
                        lastGain = 0;
                        lastPlasmaConsumption = 0;
                    }
                })
                .setRequiredTicks(() -> tempMultiplier > 0 && tempMultiplier < 1 ? (int) Math.ceil(1 / tempMultiplier) : 1)
                .setBaselineMaxOperations(() -> tempMultiplier > 0 && tempMultiplier < 1 ? 1 : (int) tempMultiplier);
    }

    public boolean hasWarning(CachedRecipe.OperationTracker.RecipeError error) {
        int errorIndex = TRACKED_ERROR_TYPES.indexOf(error);
        if (errorIndex == -1) {
            // Something went wrong
            return false;
        }
        return trackedErrors[errorIndex];
    }

    @Override
    public Level getHandlerWorld() {
        return getWorld();
    }

    public double getTemperature() {
        return (double) plasmaInputTank.getStored() / 10_000 *
                plasmaInputTank.getType().get(Heatant.class).getTemperature();
    }

    public int lowerHeight() {
        return lowerVolume / 36;
    }

    public int higherHeight() {
        return higherVolume / 36;
    }

    private void consumePlasmaAndHeatUp() {
        // Try to consume plasma
        long consumed = (long) (inputTank.getFluidAmount() / GenLoadConfig.generatorConfig.plasmaEvaporationPlasmaConsumeRatio.get());
        double inc = consumed - plasmaOutputTank.getNeeded();
        Gas output = plasmaInputTank.getType().get(Heatant.class).getSuperheatedGas();
        boolean isOutputRadioactive = output.has(Radiation.class);

        // Heat up, then shrink the stack in case that the stack is empty after
        // shrinking as we need to get the input's type
        heatCapacitor.handleHeat(consumed * plasmaInputTank.getType().get(Heatant.class).getTemperature());
        plasmaInputTank.shrinkStack(consumed, Action.EXECUTE);
        if (output == plasmaOutputTank.getType()) {
            if (inc > 0) {
                // The plasma overflowed and we need to check its radioactivity
                if (isOutputRadioactive) {
                    tryRadiateEnvironment(output, inc);
                }
            }
            plasmaOutputTank.growStack(consumed, Action.EXECUTE);
        } else if (plasmaOutputTank.isEmpty()) {
            // Type doesn't match because it's empty
            if (inc > 0) {
                // The plasma overflowed and we need to check its radioactivity
                if (isOutputRadioactive) {
                    tryRadiateEnvironment(output, inc);
                }
            }
            plasmaOutputTank.setStack(output.getStack(consumed));
        } else {
            // Plasma input doesn't produce our expected output, leak it
            if (isOutputRadioactive) {
                tryRadiateEnvironment(output, consumed);
            }
        }
    }

    private void tryRadiateEnvironment(Gas gas, double amount) {
        IRadiationManager manager = IRadiationManager.INSTANCE;
        if (!gas.has(Radiation.class)) return;
        if (manager.isRadiationEnabled()) {
            manager.radiate(new Coord4D(this.getBounds().getCenter(), this.getWorld()),
                    gas.get(Radiation.class).getRadioactivity() * amount);
        }
    }

    public void updateVentData(List<VentData> vents) {
        this.ventData = vents;
        this.vents = this.ventData.size();
    }

    @Override
    protected void updateEjectors(Level world) {
        super.updateEjectors(world);
        for (VentData data : this.ventData) {
            TileEntityPlasmaEvaporationVent vent = WorldUtils.getTileEntity(TileEntityPlasmaEvaporationVent.class, world, data.location);
            if (vent != null) {
                Set<Direction> sides = SIDE_REFERENCES.computeIfAbsent(data.side, Collections::singleton);
                vent.setEjectSides(sides);
            }
        }
    }

    public record VentData(BlockPos location, Direction side) {
    }
}
