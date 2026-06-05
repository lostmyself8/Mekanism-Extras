package com.jerry.mekanism_extras.common.tile.factory;

import com.jerry.mekanism_extras.api.recipes.outputs.ExtraOutputHelper;
import com.jerry.mekanism_extras.common.inventory.slot.ExtraFactoryInputInventorySlot;
import com.jerry.mekanism_extras.common.inventory.slot.ExtraFactoryOutputInventorySlot;

import mekanism.api.IContentsListener;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.inventory.warning.WarningTracker;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public abstract class TileEntityExtraItemToItemFactory<RECIPE extends MekanismRecipe> extends TileEntityExtraFactory<RECIPE> {

    protected IInputHandler<@NotNull ItemStack>[] inputHandlers;
    protected IOutputHandler<@NotNull ItemStack>[] outputHandlers;

    protected TileEntityExtraItemToItemFactory(IBlockProvider blockProvider, BlockPos pos, BlockState state, List<RecipeError> errorTypes, Set<RecipeError> globalErrorTypes) {
        super(blockProvider, pos, state, errorTypes, globalErrorTypes);
    }

    protected void addSlots(InventorySlotHelper builder, IContentsListener listener, IContentsListener updateSortingListener) {
        inputHandlers = new IInputHandler[tier.processes];
        outputHandlers = new IOutputHandler[tier.processes];
        processInfoSlots = new ProcessInfo[tier.processes];
        int baseX = 27;
        int baseXMult = 19;

        for (int i = 0; i < tier.processes; ++i) {
            int xPos = baseX + i * baseXMult;
            ExtraFactoryOutputInventorySlot outputSlot = ExtraFactoryOutputInventorySlot.at(this, updateSortingListener, xPos, 57);
            ExtraFactoryInputInventorySlot inputSlot = ExtraFactoryInputInventorySlot.create(this, i, outputSlot, recipeCacheLookupMonitors[i], xPos, 13);
            int index = i;
            builder.addSlot(inputSlot).tracksWarnings(slot -> slot.warning(WarningTracker.WarningType.NO_MATCHING_RECIPE, getWarningCheck(RecipeError.NOT_ENOUGH_INPUT, index)));
            builder.addSlot(outputSlot).tracksWarnings(slot -> slot.warning(WarningTracker.WarningType.NO_SPACE_IN_OUTPUT, getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index)));
            inputHandlers[i] = InputHelper.getInputHandler(inputSlot, RecipeError.NOT_ENOUGH_INPUT);
            outputHandlers[i] = ExtraOutputHelper.getOutputHandler(outputSlot, RecipeError.NOT_ENOUGH_OUTPUT_SPACE, this::getBaselineMaxOperations);
            processInfoSlots[i] = new ProcessInfo(i, inputSlot, outputSlot, null);
        }
    }
}
