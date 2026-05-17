package com.jerry.mekanism_extras.common.integration.mekaf.inventory.slot;

import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;

import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.BasicInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class ExtraAdvancedFactoryOutputInventorySlot extends BasicInventorySlot {

    private final TileEntityExtraAdvancedFactoryBase<?> factory;

    public static ExtraAdvancedFactoryOutputInventorySlot at(TileEntityExtraAdvancedFactoryBase<?> factory, @Nullable IContentsListener listener, int x, int y) {
        return new ExtraAdvancedFactoryOutputInventorySlot(factory, listener, x, y);
    }

    private ExtraAdvancedFactoryOutputInventorySlot(TileEntityExtraAdvancedFactoryBase<?> factory, @Nullable IContentsListener listener, int x, int y) {
        super(alwaysTrueBi, internalOnly, alwaysTrue, listener, x, y);
        this.setSlotType(ContainerSlotType.OUTPUT);
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
