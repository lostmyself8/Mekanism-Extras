package com.jerry.mekanism_extras.common.inventory.slot;

import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
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

    private static AdvancedFactoryTier isTier = AdvancedFactoryTier.ABSOLUTE;

    public static StackableInputInventorySlot at(AdvancedFactoryTier tier, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener, int x, int y) {
        return at(tier, ConstantPredicates.alwaysTrue(), isItemValid, listener, x, y);
    }

    public static StackableInputInventorySlot at(AdvancedFactoryTier tier, Predicate<@NotNull ItemStack> insertPredicate, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener,
                                                 int x, int y) {
        Objects.requireNonNull(insertPredicate, "Insertion check cannot be null");
        Objects.requireNonNull(isItemValid, "Item validity check cannot be null");
        return new StackableInputInventorySlot(tier, insertPredicate, isItemValid, listener, x, y);
    }

    protected StackableInputInventorySlot(AdvancedFactoryTier tier, Predicate<@NotNull ItemStack> insertPredicate, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener, int x, int y) {
        super(insertPredicate, isItemValid, listener, x, y);
        isTier = tier;
        setSlotType(ContainerSlotType.EXTRA);
    }

    @Override
    public int getLimit(ItemStack stack) {
        return Item.MAX_STACK_SIZE * isTier.processes;
    }


}
