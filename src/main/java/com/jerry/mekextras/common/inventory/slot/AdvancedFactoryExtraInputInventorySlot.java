package com.jerry.mekextras.common.inventory.slot;

import com.jerry.mekextras.common.tier.AdvancedFactoryTier;
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
public class AdvancedFactoryExtraInputInventorySlot extends InputInventorySlot {

    private static AdvancedFactoryTier isTier = AdvancedFactoryTier.ABSOLUTE;

    public static AdvancedFactoryExtraInputInventorySlot at(AdvancedFactoryTier tier, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener, int x, int y) {
        return at(tier, ConstantPredicates.alwaysTrue(), isItemValid, listener, x, y);
    }

    public static AdvancedFactoryExtraInputInventorySlot at(AdvancedFactoryTier tier, Predicate<@NotNull ItemStack> insertPredicate, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener,
                                                            int x, int y) {
        Objects.requireNonNull(insertPredicate, "Insertion check cannot be null");
        Objects.requireNonNull(isItemValid, "Item validity check cannot be null");
        return new AdvancedFactoryExtraInputInventorySlot(tier, insertPredicate, isItemValid, listener, x, y);
    }

    protected AdvancedFactoryExtraInputInventorySlot(AdvancedFactoryTier tier, Predicate<@NotNull ItemStack> insertPredicate, Predicate<@NotNull ItemStack> isItemValid, @Nullable IContentsListener listener, int x, int y) {
        super(insertPredicate, isItemValid, listener, x, y);
        isTier = tier;
        setSlotType(ContainerSlotType.EXTRA);
    }

    @Override
    public int getLimit(ItemStack stack) {
        return Item.DEFAULT_MAX_STACK_SIZE * isTier.processes;
    }


}
