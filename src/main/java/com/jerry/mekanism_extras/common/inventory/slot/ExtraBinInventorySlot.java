package com.jerry.mekanism_extras.common.inventory.slot;

import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockBin;
import com.jerry.mekanism_extras.common.tier.BTier;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.NBTConstants;
import mekanism.common.inventory.container.slot.InventoryContainerSlot;
import mekanism.common.inventory.slot.BasicInventorySlot;
import mekanism.common.item.block.ItemBlockBin;
import mekanism.common.util.NBTUtils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Predicate;

public class ExtraBinInventorySlot extends BasicInventorySlot {

    private static final Predicate<@NotNull ItemStack> validator = stack -> !(stack.getItem() instanceof ExtraItemBlockBin || stack.getItem() instanceof ItemBlockBin);

    public static ExtraBinInventorySlot create(@Nullable IContentsListener listener, BTier tier) {
        Objects.requireNonNull(tier, "Bin tier cannot be null");
        return new ExtraBinInventorySlot(listener, tier);
    }

    private final boolean isCreative;
    private ItemStack lockStack = ItemStack.EMPTY;

    private ExtraBinInventorySlot(@Nullable IContentsListener listener, BTier tier) {
        super(tier.getStorage(), alwaysTrueBi, alwaysTrueBi, validator, listener, 0, 0);
        isCreative = false;
        obeyStackLimit = false;
    }

    @Override
    public @NotNull ItemStack insertItem(@NotNull ItemStack stack, @NotNull Action action, @NotNull AutomationType automationType) {
        if (isEmpty()) {
            if (isLocked() && !ItemHandlerHelper.canItemStacksStack(lockStack, stack)) {
                // When locked, we need to make sure the correct item type is being inserted
                return stack;
            } else if (isCreative && action.execute() && automationType != AutomationType.EXTERNAL) {
                // If a player manually inserts into a creative bin, that is empty we need to allow setting the type,
                // Note: We check that it is not external insertion because an empty creative bin acts as a "void" for
                // automation
                ItemStack simulatedRemainder = super.insertItem(stack, Action.SIMULATE, automationType);
                if (simulatedRemainder.isEmpty()) {
                    // If we are able to insert it then set perform the action of setting it to full
                    setStackUnchecked(stack.copyWithCount(getLimit(stack)));
                }
                return simulatedRemainder;
            }
        }
        return super.insertItem(stack, action.combine(!isCreative), automationType);
    }

    @Override
    public @NotNull ItemStack extractItem(int amount, Action action, @NotNull AutomationType automationType) {
        return super.extractItem(amount, action.combine(!isCreative), automationType);
    }

    /**
     * {@inheritDoc}
     *
     * Note: We are only patching {@link #setStackSize(int, Action)}, as both {@link #growStack(int, Action)} and
     * {@link #shrinkStack(int, Action)} are wrapped through
     * this method.
     */
    @Override
    public int setStackSize(int amount, Action action) {
        return super.setStackSize(amount, action.combine(!isCreative));
    }

    @Nullable
    @Override
    public InventoryContainerSlot createContainerSlot() {
        return null;
    }

    /**
     * Gets the "bottom" stack for the bin, this is the stack that can be extracted/interacted with directly.
     *
     * @return The "bottom" stack for the bin
     *
     * @apiNote The returned stack can be safely modified.
     */
    public ItemStack getBottomStack() {
        if (isEmpty()) {
            return ItemStack.EMPTY;
        }
        return current.copyWithCount(Math.min(getCount(), current.getMaxStackSize()));
    }

    /**
     * Modifies the lock state of the slot.
     *
     * @param lock if the slot should be locked
     *
     * @return if the lock state was modified
     */
    public boolean setLocked(boolean lock) {
        // Don't lock if:
        // - We are a creative bin
        // - We already have the same state as the one we're supposed to switch to
        // - We were asked to lock, but we're empty
        if (isCreative || isLocked() == lock || (lock && isEmpty())) {
            return false;
        }
        lockStack = lock ? current.copyWithCount(1) : ItemStack.EMPTY;
        return true;
    }

    /**
     * For use by upgrade recipes, do not use this in place of {@link #setLocked(boolean)}
     */
    public void setLockStack(@NotNull ItemStack stack) {
        lockStack = stack.copyWithCount(1);
    }

    public boolean isLocked() {
        return !lockStack.isEmpty();
    }

    public ItemStack getRenderStack() {
        return isLocked() ? getLockStack() : getStack();
    }

    public ItemStack getLockStack() {
        return lockStack;
    }

    @Override
    public @NotNull CompoundTag serializeNBT() {
        CompoundTag nbt = super.serializeNBT();
        if (isLocked()) {
            nbt.put(NBTConstants.LOCK_STACK, lockStack.serializeNBT());
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundTag nbt) {
        NBTUtils.setItemStackOrEmpty(nbt, NBTConstants.LOCK_STACK, s -> this.lockStack = s);
        super.deserializeNBT(nbt);
    }
}
