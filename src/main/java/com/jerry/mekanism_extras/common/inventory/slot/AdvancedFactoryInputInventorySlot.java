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

    private final TileEntityAdvancedFactory<?> factory;

    public static AdvancedFactoryInputInventorySlot create(TileEntityAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener, int x, int y) {
        return create(factory, process, outputSlot, null, listener, x, y);
    }

    public static AdvancedFactoryInputInventorySlot create(TileEntityAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputSlot, "Primary output slot cannot be null");
        return new AdvancedFactoryInputInventorySlot(factory, process, outputSlot, secondaryOutputSlot, listener, x, y);
    }

    private AdvancedFactoryInputInventorySlot(TileEntityAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isValidInputItem(stack) && factory.inputProducesOutput(process, stack, outputSlot, secondaryOutputSlot, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public void setStackUnchecked(@NotNull ItemStack stack) {
        super.setStackUnchecked(stack);
    }

    @Override
    public int getLimit(@NotNull ItemStack stack) {
        if (factory != null) {
            return switch (factory.tier) {
                case ABSOLUTE -> super.getLimit(stack) * 8;
                case SUPREME -> super.getLimit(stack) * 16;
                case COSMIC -> super.getLimit(stack) * 32;
                case INFINITE -> super.getLimit(stack) * 64;
            };
        }
        return super.getLimit(stack);
    }
}
