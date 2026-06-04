package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory;

import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraItemToMergedFactory;

import mekanism.api.IContentsListener;
import mekanism.api.Upgrade;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.chemical.infuse.InfusionStack;
import mekanism.api.chemical.merged.MergedChemicalTank;
import mekanism.api.chemical.pigment.PigmentStack;
import mekanism.api.chemical.slurry.SlurryStack;
import mekanism.api.math.MathUtils;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.recipes.ChemicalDissolutionRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.cache.ChemicalDissolutionCachedRecipe;
import mekanism.api.recipes.inputs.ILongInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.common.Mekanism;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerChemicalTankWrapper;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.inventory.slot.chemical.GasInventorySlot;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.recipe.IMekanismRecipeTypeProvider;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.IDoubleRecipeLookupHandler.ItemChemicalRecipeLookupHandler;
import mekanism.common.recipe.lookup.cache.InputRecipeCache.ItemChemical;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.component.config.ConfigInfo;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.ChemicalSlotInfo.GasSlotInfo;
import mekanism.common.tile.component.config.slot.InventorySlotInfo;
import mekanism.common.tile.interfaces.IHasDumpButton;
import mekanism.common.upgrade.IUpgradeData;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.StatUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import com.jerry.mekaf.common.upgrade.ItemGasToMergedUpgradeData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TileEntityExtraDissolvingFactory extends TileEntityExtraItemToMergedFactory<ChemicalDissolutionRecipe> implements IHasDumpButton, ItemChemicalRecipeLookupHandler<Gas, GasStack, ChemicalDissolutionRecipe> {

    private static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_ENERGY_REDUCED_RATE,
            RecipeError.NOT_ENOUGH_INPUT,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT,
            RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);
    private static final Set<RecipeError> GLOBAL_ERROR_TYPES = Set.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT);
    private static final long MAX_CHEMICAL = 10_000L;

    @WrappingComputerMethod(wrapper = ComputerChemicalTankWrapper.class, methodNames = { "getChemicalInput", "getChemicalInputCapacity", "getChemicalInputNeeded", "getChemicalInputFilledPercentage" }, docPlaceholder = "chemical input tank")
    public IGasTank injectTank;

    public double injectUsage = 1.0;
    private final ILongInputHandler<GasStack> gasInputHandler;

    @WrappingComputerMethod(wrapper = ComputerIInventorySlotWrapper.class, methodNames = { "getInputChemicalItem" }, docPlaceholder = "chemical input item slot")
    GasInventorySlot gasInputSlot;

    public TileEntityExtraDissolvingFactory(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, GLOBAL_ERROR_TYPES);

        ConfigInfo itemConfig = configComponent.getConfig(TransmissionType.ITEM);
        if (itemConfig != null) {
            itemConfig.addSlotInfo(DataType.EXTRA, new InventorySlotInfo(true, true, gasInputSlot));
            itemConfig.setDefaults();
        }

        ConfigInfo gasConfig = configComponent.getConfig(TransmissionType.GAS);
        if (gasConfig != null) {
            gasConfig.addSlotInfo(DataType.INPUT, new GasSlotInfo(true, false, injectTank));
            List<IGasTank> ioTank = new ArrayList<>(List.of(injectTank));
            ioTank.addAll(outputGasTanks);
            gasConfig.addSlotInfo(DataType.INPUT_OUTPUT, new GasSlotInfo(true, true, ioTank));
        }

        ejectorComponent = new TileComponentEjector(this);
        ejectorComponent.setOutputData(configComponent, TransmissionType.ITEM, TransmissionType.GAS, TransmissionType.INFUSION, TransmissionType.PIGMENT, TransmissionType.SLURRY)
                .setCanTankEject(tank -> tank != injectTank);
        gasInputHandler = InputHelper.getConstantInputHandler(injectTank);
    }

    @Override
    protected void addGasTanks(ChemicalTankHelper<Gas, GasStack, IGasTank> builder, IContentsListener listener, IContentsListener updateSortingListener) {
        super.addGasTanks(builder, listener, updateSortingListener);
        builder.addTank(injectTank = ChemicalTankBuilder.GAS.input(MAX_CHEMICAL * tier.processes, this::containsRecipeB, markAllMonitorsChanged(listener)));
    }

    @Override
    protected void addSlots(InventorySlotHelper builder, IContentsListener listener, IContentsListener updateSortingListener) {
        super.addSlots(builder, listener, updateSortingListener);
        builder.addSlot(gasInputSlot = GasInventorySlot.fillOrConvert(injectTank, this::getLevel, listener, 7, 70));
    }

    @Override
    public IGasTank getGasTankBar() {
        return injectTank;
    }

    @Override
    protected void handleExtrasFuel() {
        gasInputSlot.fillTankOrConvert();
    }

    @Override
    public boolean hasExtrasResourceBar() {
        return true;
    }

    @Override
    protected boolean isCachedRecipeValid(@Nullable CachedRecipe<ChemicalDissolutionRecipe> cached, @NotNull ItemStack stack) {
        return cached != null && cached.getRecipe().getItemInput().testType(stack);
    }

    @Nullable
    protected ChemicalDissolutionRecipe findRecipe(int process, @NotNull ItemStack fallbackInput, @NotNull MergedChemicalTank outputTanks) {
        return getRecipeType().getInputCache().findTypeBasedRecipe(level, fallbackInput, injectTank.getStack(), recipe -> {
            ChemicalStack<?> stack = recipe.getOutput(fallbackInput, injectTank.getStack()).getChemicalStack();
            if (stack instanceof GasStack output) {
                return output.isTypeEqual(output);
            } else if (stack instanceof InfusionStack output) {
                return output.isTypeEqual(output);
            } else if (stack instanceof PigmentStack output) {
                return output.isTypeEqual(output);
            } else if (stack instanceof SlurryStack output) {
                return output.isTypeEqual(output);
            }
            return false;
        });
    }

    @Override
    public boolean isValidInputItem(@NotNull ItemStack stack) {
        return containsRecipeA(stack);
    }

    protected int getNeededInput(ChemicalDissolutionRecipe recipe, ItemStack inputStack) {
        return MathUtils.clampToInt(recipe.getItemInput().getNeededAmount(inputStack));
    }

    @NotNull
    public IMekanismRecipeTypeProvider<ChemicalDissolutionRecipe, ItemChemical<Gas, GasStack, ChemicalDissolutionRecipe>> getRecipeType() {
        return MekanismRecipeType.DISSOLUTION;
    }

    @Nullable
    public ChemicalDissolutionRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(itemInputHandlers[cacheIndex], gasInputHandler);
    }

    @NotNull
    public CachedRecipe<ChemicalDissolutionRecipe> createNewCachedRecipe(@NotNull ChemicalDissolutionRecipe recipe, int cacheIndex) {
        return new ChemicalDissolutionCachedRecipe(recipe, recheckAllRecipeErrors[cacheIndex], itemInputHandlers[cacheIndex], gasInputHandler, () -> (long) StatUtils.inversePoisson(injectUsage), mergedOutputHandlers[cacheIndex])
                .setErrorsChanged(errors -> errorTracker.onErrorsChanged(errors, cacheIndex))
                .setCanHolderFunction(() -> MekanismUtils.canFunction(this))
                .setActive(active -> setActiveState(active, cacheIndex))
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setRequiredTicks(this::getTicksRequired)
                .setOnFinish(this::markForSave)
                .setOperatingTicksChanged(operatingTicks -> progress[cacheIndex] = operatingTicks)
                .setBaselineMaxOperations(this::getBaselineMaxOperations);
    }

    @Override
    public void recalculateUpgrades(Upgrade upgrade) {
        super.recalculateUpgrades(upgrade);
        if (upgrade == Upgrade.GAS || upgrade == Upgrade.SPEED) {
            injectUsage = MekanismUtils.getGasPerTickMeanMultiplier(this);
        }
    }

    @Override
    public void parseUpgradeData(@NotNull IUpgradeData upgradeData) {
        if (upgradeData instanceof ItemGasToMergedUpgradeData data) {
            super.parseUpgradeData(upgradeData);
            injectTank.deserializeNBT(data.inputTank.serializeNBT());
            gasInputSlot.deserializeNBT(data.gasSlot.serializeNBT());
        } else {
            Mekanism.logger.warn("Unhandled upgrade data.", new Throwable());
        }
    }

    @Nullable
    public ItemGasToMergedUpgradeData getUpgradeData() {
        return new ItemGasToMergedUpgradeData(redstone, getControlType(), getEnergyContainer(), progress, energySlot, gasInputSlot, inputItemSlots, injectTank, outputChemicalTanks, isSorting(), getComponents());
    }

    public void dump() {
        injectTank.setEmpty();
    }
}
