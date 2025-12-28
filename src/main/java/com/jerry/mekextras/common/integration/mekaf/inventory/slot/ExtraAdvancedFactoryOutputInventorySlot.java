package com.jerry.mekextras.common.integration.mekaf.inventory.slot;

import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraAdvancedBase;

import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.functions.ConstantPredicates;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.BasicInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class ExtraAdvancedFactoryOutputInventorySlot extends BasicInventorySlot {

    private final TileEntityExtraAdvancedBase<?> factory;

    public static ExtraAdvancedFactoryOutputInventorySlot at(TileEntityExtraAdvancedBase<?> factory, @Nullable IContentsListener listener, int x, int y) {
        return new ExtraAdvancedFactoryOutputInventorySlot(factory, listener, x, y);
    }

    private ExtraAdvancedFactoryOutputInventorySlot(TileEntityExtraAdvancedBase<?> factory, @Nullable IContentsListener listener, int x, int y) {
        super(ConstantPredicates.alwaysTrueBi(), ConstantPredicates.internalOnly(), ConstantPredicates.alwaysTrue(), listener, x, y);
        setSlotType(ContainerSlotType.OUTPUT);
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
