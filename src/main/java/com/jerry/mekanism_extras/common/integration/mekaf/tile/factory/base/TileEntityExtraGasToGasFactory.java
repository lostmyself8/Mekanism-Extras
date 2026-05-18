package com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base;

import mekanism.api.Action;
import mekanism.api.IContentsListener;
import mekanism.api.Upgrade;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
import mekanism.common.CommonWorldTickHandler;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.tile.component.ITileComponent;
import mekanism.common.tile.component.config.ConfigInfo;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.ChemicalSlotInfo.GasSlotInfo;
import mekanism.common.tile.component.config.slot.InventorySlotInfo;
import mekanism.common.upgrade.IUpgradeData;
import mekanism.common.util.UpgradeUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

import com.jerry.mekaf.common.upgrade.GasToGasUpgradeData;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.LongSupplier;

public abstract class TileEntityExtraGasToGasFactory<RECIPE extends MekanismRecipe> extends TileEntityExtraAdvancedFactoryBase<RECIPE> {

    private static final long MAX_CHEMICAL = 10_000;

    protected GasToGasProcessInfo[] processInfoSlots;

    IGasTank[] inputTank;
    IGasTank[] outputTank;

    public final List<IGasTank> inputGasTanks;
    public final List<IGasTank> outputGasTanks;

    protected TileEntityExtraGasToGasFactory(IBlockProvider blockProvider, BlockPos pos, BlockState state, List<RecipeError> errorTypes, Set<RecipeError> globalErrorTypes) {
        super(blockProvider, pos, state, errorTypes, globalErrorTypes);
        inputGasTanks = new ArrayList<>();
        outputGasTanks = new ArrayList<>();

        for (GasToGasProcessInfo info : processInfoSlots) {
            inputGasTanks.add(info.inputTank());
            outputGasTanks.add(info.outputTank());
        }

        configComponent.addSupported(TransmissionType.GAS);
        ConfigInfo gasConfig = configComponent.getConfig(TransmissionType.GAS);
        if (gasConfig != null) {
            gasConfig.addSlotInfo(DataType.OUTPUT, new GasSlotInfo(false, true, outputGasTanks));
        }

        ConfigInfo itemConfig = configComponent.getConfig(TransmissionType.ITEM);
        if (itemConfig != null) {
            itemConfig.addSlotInfo(DataType.ENERGY, new InventorySlotInfo(true, true, energySlot));
            itemConfig.fill(DataType.ENERGY);
        }
    }

    @Override
    protected void addGasTanks(ChemicalTankHelper<Gas, GasStack, IGasTank> builder, IContentsListener listener, IContentsListener updateSortingListener) {
        inputTank = new IGasTank[tier.processes];
        outputTank = new IGasTank[tier.processes];
        gasInputHandlers = new IInputHandler[tier.processes];
        gasOutputHandlers = new IOutputHandler[tier.processes];
        processInfoSlots = new GasToGasProcessInfo[tier.processes];
        for (int i = 0; i < tier.processes; i++) {
            int index = i;
            outputTank[i] = ChemicalTankBuilder.GAS.output(MAX_CHEMICAL * tier.processes, listener);
            inputTank[i] = ChemicalTankBuilder.GAS.create(MAX_CHEMICAL * tier.processes, ChemicalTankHelper.radioactiveInputTankPredicate(() -> outputTank[index]),
                    (stack, type) -> isValidInputChemical(stack.getStack(1L)), stack -> isChemicalValidForTank(stack.getStack(1L)) && inputProducesOutput(index, stack.getStack(1L), outputTank[index], false),
                    ChemicalAttributeValidator.ALWAYS_ALLOW, recipeCacheLookupMonitors[index]);
            builder.addTank(inputTank[i]);
            builder.addTank(outputTank[i]);
            gasInputHandlers[i] = InputHelper.getInputHandler(inputTank[i], RecipeError.NOT_ENOUGH_INPUT);
            gasOutputHandlers[i] = OutputHelper.getOutputHandler(outputTank[i], RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
            processInfoSlots[i] = new GasToGasProcessInfo(i, inputTank[i], outputTank[i]);
        }
    }

    public boolean inputProducesOutput(int process, @NotNull GasStack fallbackInput, @NotNull IGasTank outputTank, boolean updateCache) {
        return outputTank.isEmpty() || getRecipeForInput(process, fallbackInput, outputTank, updateCache) != null;
    }

    @Contract("null, _ -> false")
    protected abstract boolean isCachedRecipeValid(@Nullable CachedRecipe<RECIPE> cached, @NotNull GasStack stack);

    @Nullable
    protected RECIPE getRecipeForInput(int process, @NotNull GasStack fallbackInput, @NotNull IGasTank outputTank, boolean updateCache) {
        if (!CommonWorldTickHandler.flushTagAndRecipeCaches) {
            // If our recipe caches are valid, grab our cached recipe and see if it is still valid
            CachedRecipe<RECIPE> cached = getCachedRecipe(process);
            if (isCachedRecipeValid(cached, fallbackInput)) {
                // Our input matches the recipe we have cached for this slot
                return cached.getRecipe();
            }
        }
        // If there is no cached item input, or it doesn't match our fallback then it is an out of date cache, so we
        // ignore the fact that we have a cache
        RECIPE foundRecipe = findRecipe(process, fallbackInput, outputTank);
        if (foundRecipe == null) {
            // We could not find any valid recipe for the given item that matches the items in the current output slots
            return null;
        }
        if (updateCache) {
            // If we want to update the cache, then create a new cache with the recipe we found and update the cache
            recipeCacheLookupMonitors[process].updateCachedRecipe(foundRecipe);
        }
        return foundRecipe;
    }

    @Nullable
    protected abstract RECIPE findRecipe(int process, @NotNull GasStack fallbackInput, @NotNull IGasTank outputTanks);

    public abstract boolean isChemicalValidForTank(@NotNull GasStack stack);

    /**
     * Like isItemValidForSlot makes no assumptions about current stored types
     */
    public abstract boolean isValidInputChemical(@NotNull GasStack stack);

    protected abstract int getNeededInput(RECIPE recipe, GasStack inputStack);

    @Override
    public void recalculateUpgrades(Upgrade upgrade) {
        super.recalculateUpgrades(upgrade);
        if (upgrade == Upgrade.SPEED) {
            baselineMaxOperations = (int) Math.pow(2, upgradeComponent.getUpgrades(Upgrade.SPEED));
        }
    }

    // 更改加速升级的显示的，默认是10x，气体工厂是256x，当然只有速度升级需要更改
    @NotNull
    @Override
    public List<Component> getInfo(@NotNull Upgrade upgrade) {
        return upgrade == Upgrade.SPEED ? UpgradeUtils.getExpScaledInfo(this, upgrade) : super.getInfo(upgrade);
    }

    public void parseUpgradeData(@NotNull IUpgradeData upgradeData) {
        if (upgradeData instanceof GasToGasUpgradeData data) {
            redstone = data.redstone;
            setControlType(data.controlType);
            getEnergyContainer().setEnergy(data.energyContainer.getEnergy());
            sorting = data.sorting;
            energySlot.deserializeNBT(data.energySlot.serializeNBT());
            System.arraycopy(data.progress, 0, progress, 0, data.progress.length);

            for (int i = 0; i < data.inputTanks.size(); i++) {
                inputGasTanks.get(i).deserializeNBT(data.inputTanks.get(i).serializeNBT());
            }

            for (int i = 0; i < data.outputTanks.size(); i++) {
                outputGasTanks.get(i).setStack(data.outputTanks.get(i).getStack());
            }

            for (ITileComponent component : getComponents()) {
                component.read(data.components);
            }
        } else {
            super.parseUpgradeData(upgradeData);
        }
    }

    @Override
    public int TankCount() {
        return 2;
    }

    @Override
    protected void sortInventoryOrTank() {
        Map<GasStack, GasToGasRecipeProcessInfo> processes = new HashMap<>();
        List<GasToGasProcessInfo> emptyProcesses = new ArrayList<>();
        for (GasToGasProcessInfo processInfo : processInfoSlots) {
            IGasTank inputTank = processInfo.inputTank();
            if (inputTank.isEmpty()) {
                emptyProcesses.add(processInfo);
            } else {
                GasStack inputStack = inputTank.getStack();
                GasToGasRecipeProcessInfo recipeProcessInfo = processes.computeIfAbsent(inputStack, i -> new GasToGasRecipeProcessInfo());
                recipeProcessInfo.processes.add(processInfo);
                recipeProcessInfo.totalAmount += inputStack.getAmount();
                if (recipeProcessInfo.lazyMinPerTank == null && !CommonWorldTickHandler.flushTagAndRecipeCaches) {
                    CachedRecipe<RECIPE> cachedRecipe = getCachedRecipe(processInfo.process());
                    if (isCachedRecipeValid(cachedRecipe, inputStack)) {
                        recipeProcessInfo.lazyMinPerTank = () -> Math.max(1, getNeededInput(cachedRecipe.getRecipe(), inputStack));
                    }
                }
            }
        }
        if (processes.isEmpty()) {
            return;
        }
        for (Map.Entry<GasStack, GasToGasRecipeProcessInfo> entry : processes.entrySet()) {
            GasToGasRecipeProcessInfo recipeProcessInfo = entry.getValue();
            if (recipeProcessInfo.lazyMinPerTank == null) {
                recipeProcessInfo.lazyMinPerTank = () -> {
                    GasStack item = entry.getKey();
                    GasStack largerInput = new GasStack(item, Math.min(MAX_CHEMICAL * tier.processes, recipeProcessInfo.totalAmount));
                    GasToGasProcessInfo processInfo = recipeProcessInfo.processes.get(0);
                    RECIPE recipe = getRecipeForInput(processInfo.process(), largerInput, processInfo.outputTank(), true);
                    if (recipe != null) {
                        return Math.max(1, getNeededInput(recipe, largerInput));
                    }
                    return 1;
                };
            }
        }
        if (!emptyProcesses.isEmpty()) {
            addEmptyTanksAsTargets(processes, emptyProcesses);
        }
        distributeItems(processes);
    }

    protected void addEmptyTanksAsTargets(Map<GasStack, GasToGasRecipeProcessInfo> processes, List<GasToGasProcessInfo> emptyProcesses) {
        for (Entry<GasStack, GasToGasRecipeProcessInfo> entry : processes.entrySet()) {
            GasToGasRecipeProcessInfo recipeProcessInfo = entry.getValue();
            long minPerTank = recipeProcessInfo.getMinPerTank();
            long maxTanks = recipeProcessInfo.totalAmount / minPerTank;
            if (maxTanks <= 1) {
                continue;
            }
            int processAmount = recipeProcessInfo.processes.size();
            if (maxTanks <= processAmount) {
                continue;
            }
            GasStack sourceStack = entry.getKey();
            long emptyToAdd = maxTanks - processAmount;
            int added = 0;
            List<GasToGasProcessInfo> toRemove = new ArrayList<>();
            for (GasToGasProcessInfo emptyProcess : emptyProcesses) {
                if (inputProducesOutput(emptyProcess.process(), sourceStack, emptyProcess.outputTank(), true)) {
                    recipeProcessInfo.processes.add(emptyProcess);
                    toRemove.add(emptyProcess);
                    added++;
                    if (added >= emptyToAdd) {
                        break;
                    }
                }
            }
            emptyProcesses.removeAll(toRemove);
            if (emptyProcesses.isEmpty()) {
                break;
            }
        }
    }

    protected void distributeItems(Map<GasStack, GasToGasRecipeProcessInfo> processes) {
        for (Map.Entry<GasStack, GasToGasRecipeProcessInfo> entry : processes.entrySet()) {
            GasToGasRecipeProcessInfo recipeProcessInfo = entry.getValue();
            long processAmount = recipeProcessInfo.processes.size();
            if (processAmount == 1) {
                continue;
            }
            GasStack item = entry.getKey();
            long maxAmount = MAX_CHEMICAL * tier.processes;
            long numberPerTank = recipeProcessInfo.totalAmount / processAmount;
            if (numberPerTank == maxAmount) {
                continue;
            }
            long remainder = recipeProcessInfo.totalAmount % processAmount;
            long minPerTank = recipeProcessInfo.getMinPerTank();
            if (minPerTank > 1) {
                long perSlotRemainder = numberPerTank % minPerTank;
                if (perSlotRemainder > 0) {
                    numberPerTank -= perSlotRemainder;
                    remainder += perSlotRemainder * processAmount;
                }
                if (numberPerTank + minPerTank > maxAmount) {
                    minPerTank = maxAmount - numberPerTank;
                }
            }
            for (int i = 0; i < processAmount; i++) {
                GasToGasProcessInfo processInfo = recipeProcessInfo.processes.get(i);
                IGasTank inputTank = processInfo.inputTank();
                long sizeForTank = numberPerTank;
                if (remainder > 0) {
                    if (remainder > minPerTank) {
                        sizeForTank += minPerTank;
                        remainder -= minPerTank;
                    } else {
                        sizeForTank += remainder;
                        remainder = 0;
                    }
                }
                if (inputTank.isEmpty()) {
                    if (sizeForTank > 0) {
                        inputTank.setStack(new GasStack(item, sizeForTank));
                    }
                } else {
                    if (sizeForTank == 0) {
                        inputTank.setEmpty();
                    } else if (inputTank.getStack().getAmount() != sizeForTank) {
                        inputTank.setStackSize(sizeForTank, Action.EXECUTE);
                    }
                }
            }
        }
    }

    public record GasToGasProcessInfo(int process, @NotNull IGasTank inputTank, @NotNull IGasTank outputTank) {}

    public static class GasToGasRecipeProcessInfo {

        private final List<GasToGasProcessInfo> processes = new ArrayList<>();
        @Nullable
        private LongSupplier lazyMinPerTank;
        private long minPerTank = 1L;
        private long totalAmount;

        public long getMinPerTank() {
            if (lazyMinPerTank != null) {
                minPerTank = lazyMinPerTank.getAsLong();
                lazyMinPerTank = null;
            }

            return minPerTank;
        }
    }
}
