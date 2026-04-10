package com.jerry.mekextras.common.integration.mekmm.inventory.slot;

import com.jerry.mekextras.common.integration.mekmm.tile.factory.TileEntityExtraMoreMachineFactory;

import mekanism.api.IContentsListener;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExtraMoreMachineFactoryInputInventorySlot extends InputInventorySlot {

    private final TileEntityExtraMoreMachineFactory<?> factory;

    public static ExtraMoreMachineFactoryInputInventorySlot create(TileEntityExtraMoreMachineFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener,
                                                                   int x, int y) {
        return create(factory, process, outputSlot, null, listener, x, y);
    }

    public static ExtraMoreMachineFactoryInputInventorySlot create(TileEntityExtraMoreMachineFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot,
                                                                   @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputSlot, "Primary output slot cannot be null");
        return new ExtraMoreMachineFactoryInputInventorySlot(factory, process, outputSlot, secondaryOutputSlot, listener, x, y);
    }

    private ExtraMoreMachineFactoryInputInventorySlot(TileEntityExtraMoreMachineFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot,
                                                      @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputSlot, secondaryOutputSlot, false),
                factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    @Override
    public int getLimit(@NotNull ItemStack stack) {
        try {
            return Math.multiplyExact(super.getLimit(stack), 8 << factory.tier.ordinal());
        } catch (ArithmeticException ignored) {
            return Integer.MAX_VALUE;
        }
    }
}
