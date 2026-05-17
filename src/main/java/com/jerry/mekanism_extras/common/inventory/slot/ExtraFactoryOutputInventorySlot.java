package com.jerry.mekanism_extras.common.inventory.slot;

import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;

import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.BasicInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class ExtraFactoryOutputInventorySlot extends BasicInventorySlot {

    private final TileEntityExtraFactory<?> factory;

    public static ExtraFactoryOutputInventorySlot at(TileEntityExtraFactory<?> factory, @Nullable IContentsListener listener, int x, int y) {
        return new ExtraFactoryOutputInventorySlot(factory, listener, x, y);
    }

    private ExtraFactoryOutputInventorySlot(TileEntityExtraFactory<?> factory, @Nullable IContentsListener listener, int x, int y) {
        super(alwaysTrueBi, internalOnly, alwaysTrue, listener, x, y);
        setSlotType(ContainerSlotType.OUTPUT);
        this.factory = factory;
    }

    @Override
    public int getLimit(ItemStack stack) {
        try {
            return Math.multiplyExact(super.getLimit(stack), 8 << factory.tier.ordinal());
        } catch (ArithmeticException ignored) {
            return Integer.MAX_VALUE;
        }
    }
}
