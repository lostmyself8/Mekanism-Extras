package com.jerry.mekanism_extras.common.inventory.slot;

import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;

import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.BasicInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class AdvancedFactoryOutputInventorySlot extends BasicInventorySlot {

    private final TileEntityExtraFactory<?> factory;

    public static AdvancedFactoryOutputInventorySlot at(TileEntityExtraFactory<?> factory, @Nullable IContentsListener listener, int x, int y) {
        return new AdvancedFactoryOutputInventorySlot(factory, listener, x, y);
    }

    private AdvancedFactoryOutputInventorySlot(TileEntityExtraFactory<?> factory, @Nullable IContentsListener listener, int x, int y) {
        super(alwaysTrueBi, internalOnly, alwaysTrue, listener, x, y);
        setSlotType(ContainerSlotType.OUTPUT);
        this.factory = factory;
    }

    @Override
    public int getLimit(ItemStack stack) {
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
