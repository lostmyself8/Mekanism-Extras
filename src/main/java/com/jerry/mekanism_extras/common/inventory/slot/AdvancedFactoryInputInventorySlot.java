package com.jerry.mekanism_extras.common.inventory.slot;

import com.jerry.mekanism_extras.common.tile.factory.TileEntityAdvancedFactory;
import mekanism.api.IContentsListener;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class AdvancedFactoryInputInventorySlot extends InputInventorySlot {

    public static AdvancedFactoryInputInventorySlot create(TileEntityAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener,
                                                           int x, int y) {
        return create(factory, process, outputSlot, null, listener, x, y);
    }

    public static AdvancedFactoryInputInventorySlot create(TileEntityAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot,
                                                   @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputSlot, "Primary output slot cannot be null");
        return new AdvancedFactoryInputInventorySlot(factory, process, outputSlot, secondaryOutputSlot, listener, x, y);
    }

    private AdvancedFactoryInputInventorySlot(TileEntityAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot,
                                      @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isValidInputItem(stack) && factory.inputProducesOutput(process, stack, outputSlot, secondaryOutputSlot, false),
                factory::isValidInputItem, listener, x, y);
    }

    public void setStackUnchecked(@NotNull ItemStack stack) {
        super.setStackUnchecked(stack);
    }
}
