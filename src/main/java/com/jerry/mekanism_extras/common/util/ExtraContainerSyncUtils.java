package com.jerry.mekanism_extras.common.util;

import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.slot.InventoryContainerSlot;
import mekanism.common.inventory.container.sync.SyncableInt;
import mekanism.common.inventory.container.sync.SyncableItemStack;

import net.minecraft.world.item.ItemStack;

public final class ExtraContainerSyncUtils {

    private static final int VANILLA_SAFE_STACK_SIZE = Byte.MAX_VALUE;

    private ExtraContainerSyncUtils() {}

    public static void trackLargeInventorySlots(MekanismContainer container) {
        for (InventoryContainerSlot containerSlot : container.getInventoryContainerSlots()) {
            if (containerSlot.getMaxStackSize() > VANILLA_SAFE_STACK_SIZE) {
                trackLargeInventorySlot(container, containerSlot);
            }
        }
    }

    private static void trackLargeInventorySlot(MekanismContainer container, InventoryContainerSlot containerSlot) {
        container.track(SyncableItemStack.create(() -> sanitizeForItemSync(containerSlot.getItem()), stack -> {
            if (stack.isEmpty()) {
                containerSlot.set(ItemStack.EMPTY);
                return;
            }
            int currentCount = containerSlot.getItem().isEmpty() ? stack.getCount() : containerSlot.getItem().getCount();
            containerSlot.set(stack.copyWithCount(currentCount));
        }));
        container.track(SyncableInt.create(() -> containerSlot.getItem().getCount(), count -> {
            ItemStack stack = containerSlot.getItem();
            if (!stack.isEmpty()) {
                stack.setCount(count);
            }
        }));
    }

    private static ItemStack sanitizeForItemSync(ItemStack stack) {
        return stack.isEmpty() ? ItemStack.EMPTY : stack.copyWithCount(1);
    }
}
