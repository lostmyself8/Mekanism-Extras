package com.jerry.mekextra.common.attachments.containers.item;

import com.jerry.mekextra.common.inventory.slot.ExtraBinInventorySlot;
import com.jerry.mekextra.common.item.block.ExtraItemBlockBin;
import com.jerry.mekextra.common.tier.BTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.SerializationConstants;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.attachments.LockData;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.item.AttachedItems;
import mekanism.common.attachments.containers.item.ComponentBackedInventorySlot;
import mekanism.common.inventory.slot.BasicInventorySlot;
import mekanism.common.registries.MekanismDataComponents;
import mekanism.common.util.NBTUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@NothingNullByDefault
public class ExtraComponentBackedBinInventorySlot extends ComponentBackedInventorySlot {

    private final boolean isCreative;

    public static ExtraComponentBackedBinInventorySlot create(ContainerType<?, ?, ?> ignored, ItemStack attachedTo, int tankIndex) {
        if (!(attachedTo.getItem() instanceof ExtraItemBlockBin item)) {
            throw new IllegalStateException("Attached to should always be a bin item");
        }
        return new ExtraComponentBackedBinInventorySlot(attachedTo, tankIndex, item.getAdvanceTier());
    }

    private ExtraComponentBackedBinInventorySlot(ItemStack attachedTo, int slotIndex, BTier tier) {
        super(attachedTo, slotIndex, BasicInventorySlot.alwaysTrueBi, BasicInventorySlot.alwaysTrueBi, ExtraBinInventorySlot.validator);
        isCreative = false;
    }

    @Override
    public @NotNull ItemStack insertItem(@NotNull AttachedItems attachedItems, ItemStack current, @NotNull ItemStack stack, @NotNull Action action, @NotNull AutomationType automationType) {
        if (current.isEmpty()) {
            ItemStack lockStack = getLockStack();
            if (!lockStack.isEmpty() && !ItemStack.isSameItemSameComponents(lockStack, stack)) {
                // When locked, we need to make sure the correct item type is being inserted
                return stack;
            } else if (isCreative && action.execute() && automationType != AutomationType.EXTERNAL) {
                //If a player manually inserts into a creative bin, that is empty we need to allow setting the type,
                // Note: We check that it is not external insertion because an empty creative bin acts as a "void" for automation
                ItemStack simulatedRemainder = super.insertItem(attachedItems, current, stack, Action.SIMULATE, automationType);
                if (simulatedRemainder.isEmpty()) {
                    //If we are able to insert it then set perform the action of setting it to full
                    setContents(attachedItems, stack.copyWithCount(getLimit(stack)));
                }
                return simulatedRemainder;
            }
        }
        return super.insertItem(attachedItems, current, stack, action.combine(!isCreative), automationType);
    }

    @Override
    public @NotNull ItemStack extractItem(int amount, Action action, @NotNull AutomationType automationType) {
        return super.extractItem(amount, action.combine(!isCreative), automationType);
    }

    /**
     * {@inheritDoc}
     *
     * Note: We are only patching {@link #setStackSize(AttachedItems, ItemStack, int, Action)}, as both {@link #growStack(int, Action)} and
     * {@link #shrinkStack(int, Action)} are wrapped through this method.
     */
    @Override
    protected int setStackSize(@NotNull AttachedItems attachedItems, @NotNull ItemStack current, int amount, Action action) {
        return super.setStackSize(attachedItems, current, amount, action.combine(!isCreative));
    }

    /**
     * @see ExtraBinInventorySlot#getBottomStack()
     */
    public ItemStack getBottomStack() {
        ItemStack current = getStack();
        if (current.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return current.copyWithCount(Math.min(current.getCount(), current.getMaxStackSize()));
    }

    /**
     * For use by upgrade recipes
     *
     * @see ExtraBinInventorySlot#setLockStack(ItemStack)
     */
    public void setLockStack(@NotNull ItemStack stack) {
        if (stack.isEmpty()) {
            attachedTo.remove(MekanismDataComponents.LOCK);
        } else {
            attachedTo.set(MekanismDataComponents.LOCK, new LockData(stack.copyWithCount(1)));
        }
    }

    public ItemStack getLockStack() {
        return attachedTo.getOrDefault(MekanismDataComponents.LOCK, LockData.EMPTY).lock();
    }

    @Override
    public @NotNull CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag nbt = super.serializeNBT(provider);
        ItemStack lockStack = getLockStack();
        if (!lockStack.isEmpty()) {
            nbt.put(SerializationConstants.LOCK_STACK, lockStack.save(provider));
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag nbt) {
        NBTUtils.setItemStackOrEmpty(provider, nbt, SerializationConstants.LOCK_STACK, this::setLockStack);
        super.deserializeNBT(provider, nbt);
    }
}
