package com.jerry.mekextras.common.integration.mekmm.tile.factory;

import com.jerry.mekextras.api.recipes.outputs.ExtraOutputHelper;
import com.jerry.mekextras.common.integration.mekmm.inventory.slot.ExtraMoreMachineFactoryInputInventorySlot;
import com.jerry.mekextras.common.integration.mekmm.inventory.slot.ExtraMoreMachineFactoryOutputInventorySlot;

import mekanism.api.IContentsListener;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.recipe.lookup.monitor.FactoryRecipeCacheLookupMonitor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public abstract class TileEntityExtraMoreMachineItemToItemFactory<RECIPE extends MekanismRecipe<?>> extends TileEntityExtraMoreMachineFactory<RECIPE> {

    protected IInputHandler<@NotNull ItemStack>[] inputHandlers;
    protected IOutputHandler<@NotNull ItemStack>[] outputHandlers;

    protected TileEntityExtraMoreMachineItemToItemFactory(Holder<Block> blockProvider, BlockPos pos, BlockState state, List<RecipeError> errorTypes, Set<RecipeError> globalErrorTypes) {
        super(blockProvider, pos, state, errorTypes, globalErrorTypes);
    }

    @Override
    protected void addSlots(InventorySlotHelper builder, IContentsListener listener, IContentsListener updateSortingListener) {
        inputHandlers = new IInputHandler[tier.processes];
        outputHandlers = new IOutputHandler[tier.processes];
        processInfoSlots = new ProcessInfo[tier.processes];
        RecipeError primaryInputError = getPrimaryInputError();
        for (int i = 0; i < tier.processes; i++) {
            FactoryRecipeCacheLookupMonitor<RECIPE> lookupMonitor = recipeCacheLookupMonitors[i];
            IContentsListener updateSortingAndUnpause = () -> {
                updateSortingListener.onContentsChanged();
                lookupMonitor.unpause();
            };
            ExtraMoreMachineFactoryOutputInventorySlot outputSlot = ExtraMoreMachineFactoryOutputInventorySlot.at(this, updateSortingAndUnpause, getXPos(i), 57);
            // Note: As we are an item factory that has comparator's based on items we can just use the monitor as a
            // listener directly
            ExtraMoreMachineFactoryInputInventorySlot inputSlot = ExtraMoreMachineFactoryInputInventorySlot.create(this, i, outputSlot, recipeCacheLookupMonitors[i], getXPos(i), 13);
            int index = i;
            builder.addSlot(inputSlot).tracksWarnings(slot -> slot.warning(WarningType.NO_MATCHING_RECIPE, getWarningCheck(primaryInputError, index)));
            builder.addSlot(outputSlot).tracksWarnings(slot -> slot.warning(WarningType.NO_SPACE_IN_OUTPUT, getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index)));
            inputHandlers[i] = InputHelper.getInputHandler(inputSlot, primaryInputError);
            outputHandlers[i] = ExtraOutputHelper.getOutputHandler(outputSlot, RecipeError.NOT_ENOUGH_OUTPUT_SPACE, this::getOperationsPerTick);
            processInfoSlots[i] = new ProcessInfo(i, inputSlot, outputSlot, null);
        }
    }

    protected RecipeError getPrimaryInputError() {
        return RecipeError.NOT_ENOUGH_INPUT;
    }
}
