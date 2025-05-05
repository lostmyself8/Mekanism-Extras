package com.jerry.mekanism_extras.common.inventory.slot;

import com.jerry.mekanism_extras.common.tile.factory.TileEntityAdvancedFactory;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.functions.ConstantPredicates;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.InputInventorySlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Predicate;

@NothingNullByDefault
public class StackableInputInventorySlot extends InputInventorySlot {

    private final TileEntityAdvancedFactory<?> factory;

    public static StackableInputInventorySlot at(TileEntityAdvancedFactory<?> factory, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener, int x, int y) {
        return at(factory, ConstantPredicates.alwaysTrue(), isItemValid, listener, x, y);
    }

    public static StackableInputInventorySlot at(TileEntityAdvancedFactory<?> factory, Predicate<@NotNull ItemStack> insertPredicate, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(insertPredicate, "Insertion check cannot be null");
        Objects.requireNonNull(isItemValid, "Item validity check cannot be null");
        return new StackableInputInventorySlot(factory, insertPredicate, isItemValid, listener, x, y);
    }

    protected StackableInputInventorySlot(TileEntityAdvancedFactory<?> factory, Predicate<@NotNull ItemStack> insertPredicate, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener, int x, int y) {
        super(insertPredicate, isItemValid, listener, x, y);
        setSlotType(ContainerSlotType.EXTRA);
        this.factory = factory;
    }

    @Override
    public int getLimit(ItemStack stack) {
        return Item.MAX_STACK_SIZE * factory.tier.processes;
    }
}
