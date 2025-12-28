package com.jerry.mekextras.api.recipes.outputs;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.inventory.IInventorySlot;
import mekanism.api.recipes.SawmillRecipe.ChanceOutput;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.outputs.IOutputHandler;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.IntSupplier;

@NothingNullByDefault
public class ExtraOutputHelper {

    private ExtraOutputHelper() {}

    /**
     * Wrap an inventory slot into an {@link IOutputHandler}.
     *
     * @param slot                Slot to wrap.
     * @param notEnoughSpaceError The error to apply if the output causes the recipe to not be able to perform any
     *                            operations.
     */
    public static IOutputHandler<@NotNull ItemStack> getOutputHandler(IInventorySlot slot, RecipeError notEnoughSpaceError, IntSupplier baselineMaxOperations) {
        Objects.requireNonNull(slot, "Slot cannot be null.");
        Objects.requireNonNull(notEnoughSpaceError, "Not enough space error cannot be null.");
        return new IOutputHandler<>() {

            @Override
            public void handleOutput(ItemStack toOutput, int operations) {
                ExtraOutputHelper.handleOutput(slot, toOutput, operations);
            }

            @Override
            public void calculateOperationsCanSupport(OperationTracker tracker, ItemStack toOutput) {
                ExtraOutputHelper.calculateOperationsCanSupport(tracker, notEnoughSpaceError, slot, toOutput, baselineMaxOperations);
            }
        };
    }

    /**
     * Wraps two inventory slots, a "main" slot, and a "secondary" slot into an {@link IOutputHandler} for handling
     * {@link ChanceOutput}s.
     *
     * @param mainSlot                         Main slot to wrap.
     * @param secondarySlot                    Secondary slot to wrap.
     * @param mainSlotNotEnoughSpaceError      The error to apply if the main output causes the recipe to not be able to
     *                                         perform any operations.
     * @param secondarySlotNotEnoughSpaceError The error to apply if the secondary output causes the recipe to not be
     *                                         able to perform any operations.
     */
    public static IOutputHandler<@NotNull ChanceOutput> getOutputHandler(IInventorySlot mainSlot, RecipeError mainSlotNotEnoughSpaceError,
                                                                         IInventorySlot secondarySlot, RecipeError secondarySlotNotEnoughSpaceError,
                                                                         IntSupplier baselineMaxOperations) {
        Objects.requireNonNull(mainSlot, "Main slot cannot be null.");
        Objects.requireNonNull(secondarySlot, "Secondary/Extra slot cannot be null.");
        Objects.requireNonNull(mainSlotNotEnoughSpaceError, "Main slot not enough space error cannot be null.");
        Objects.requireNonNull(secondarySlotNotEnoughSpaceError, "Secondary/Extra slot not enough space error cannot be null.");
        return new IOutputHandler<>() {

            @Override
            public void handleOutput(ChanceOutput toOutput, int operations) {
                ExtraOutputHelper.handleOutput(mainSlot, toOutput.getMainOutput(), operations);
                // TODO: Batch this into a single addition call, by looping over and calculating things?
                ItemStack secondaryOutput = toOutput.getSecondaryOutput();
                for (int i = 0; i < operations; i++) {
                    ExtraOutputHelper.handleOutput(secondarySlot, secondaryOutput, operations);
                    if (i < operations - 1) {
                        secondaryOutput = toOutput.nextSecondaryOutput();
                    }
                }
            }

            @Override
            public void calculateOperationsCanSupport(OperationTracker tracker, ChanceOutput toOutput) {
                ExtraOutputHelper.calculateOperationsCanSupport(tracker, mainSlotNotEnoughSpaceError, mainSlot, toOutput.getMainOutput(), baselineMaxOperations);
                if (tracker.shouldContinueChecking()) {
                    ExtraOutputHelper.calculateOperationsCanSupport(tracker, secondarySlotNotEnoughSpaceError, secondarySlot, toOutput.getMaxSecondaryOutput(), baselineMaxOperations);
                }
            }
        };
    }

    private static void handleOutput(IInventorySlot inventorySlot, ItemStack toOutput, int operations) {
        if (operations == 0 || toOutput.isEmpty()) {
            return;
        }
        ItemStack output = toOutput.copy();
        if (operations > 1) {
            // If we are doing more than one operation we need to make a copy of our stack and change the amount
            // that we are using the fill the tank with
            output.setCount(output.getCount() * operations);
        }
        inventorySlot.insertItem(output, Action.EXECUTE, AutomationType.INTERNAL);
    }

    private static void calculateOperationsCanSupport(OperationTracker tracker, RecipeError notEnoughSpace, IInventorySlot slot, ItemStack toOutput, IntSupplier baselineMaxOperations) {
        // If our output is empty, we have nothing to add, so we treat it as being able to fit all
        if (!toOutput.isEmpty()) {
            // Make a copy of the stack we are outputting with its maximum size
            // 在这设置最大处理数
            ItemStack output = toOutput.copyWithCount(Math.max(toOutput.getMaxStackSize(), baselineMaxOperations.getAsInt()));
            ItemStack remainder = slot.insertItem(output, Action.SIMULATE, AutomationType.INTERNAL);
            int amountUsed = output.getCount() - remainder.getCount();
            // Divide the amount we can actually use by the amount one output operation is equal to, capping it at the
            // max we were told about
            int operations = amountUsed / toOutput.getCount(); //
            tracker.updateOperations(operations);
            if (operations == 0) {
                if (amountUsed == 0 && slot.getLimit(slot.getStack()) - slot.getCount() > 0) {
                    tracker.addError(RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);
                } else {
                    tracker.addError(notEnoughSpace);
                }
            }
        }
    }
}
