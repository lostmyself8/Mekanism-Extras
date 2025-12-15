package com.jerry.mekextras.common.integration.mekmm.inventory.slot;

import com.jerry.mekextras.common.integration.mekmm.tile.factory.TileEntityExtraMoreMachineFactory;
import mekanism.api.IContentsListener;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExtraMoreMachineFactoryInputInventorySlot extends InputInventorySlot {

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
    }
}
