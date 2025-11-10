package com.jerry.mekanism_extras.common.inventory;

import com.jerry.mekanism_extras.common.inventory.slot.ExtraBinInventorySlot;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockBin;

import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.ItemStackMekanismInventory;

import net.minecraft.world.item.ItemStack;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Getter
public class ExtraBinMekanismInventory extends ItemStackMekanismInventory {

    private ExtraBinInventorySlot binSlot;

    private ExtraBinMekanismInventory(@NotNull ItemStack stack) {
        super(stack);
    }

    @NotNull
    @Override
    protected List<IInventorySlot> getInitialInventory() {
        binSlot = ExtraBinInventorySlot.create(this, ((ExtraItemBlockBin) stack.getItem()).getAdvanceTier());
        return Collections.singletonList(binSlot);
    }

    @Nullable
    public static ExtraBinMekanismInventory create(@NotNull ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem() instanceof ExtraItemBlockBin) {
            return new ExtraBinMekanismInventory(stack);
        }
        return null;
    }
}
