package com.jerry.mekextras.common.inventory.slot;

import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@NothingNullByDefault
public class ExtraFactoryInputInventorySlot extends InputInventorySlot {

    private final TileEntityExtraFactory<?> factory;

    public static ExtraFactoryInputInventorySlot create(TileEntityExtraFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener,
                                                        int x, int y) {
        return create(factory, process, outputSlot, null, listener, x, y);
    }

    public static ExtraFactoryInputInventorySlot create(TileEntityExtraFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot,
                                                        @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputSlot, "Primary output slot cannot be null");
        return new ExtraFactoryInputInventorySlot(factory, process, outputSlot, secondaryOutputSlot, listener, x, y);
    }

    private ExtraFactoryInputInventorySlot(TileEntityExtraFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot,
                                           @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputSlot, secondaryOutputSlot, false),
                factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    @Override
    public int getLimit(ItemStack stack) {
        return switch (factory.tier) {
            case ABSOLUTE -> super.getLimit(stack) * 8;
            case SUPREME -> super.getLimit(stack) * 16;
            case COSMIC -> super.getLimit(stack) * 32;
            case INFINITE -> super.getLimit(stack) * 64;
        };
    }
}
