package com.jerry.mekextras.common.util;

import com.jerry.mekextras.MekanismExtras;

import mekanism.api.inventory.IInventorySlot;

import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class ExtraUpgradeDataTransfers {

    private ExtraUpgradeDataTransfers() {}

    public static void copyProgress(@NotNull BlockEntity tile, @NotNull String role, int @NotNull [] source, int @NotNull [] target) {
        int entries = matchingEntries(tile, role, source.length, target.length);
        System.arraycopy(source, 0, target, 0, entries);
        if (entries < target.length) {
            Arrays.fill(target, entries, target.length, 0);
        }
    }

    public static void copyInputSlots(@NotNull HolderLookup.Provider provider, @NotNull BlockEntity tile, @NotNull String role,
                                      @NotNull List<? extends IInventorySlot> source, @NotNull List<? extends IInventorySlot> target) {
        int entries = matchingEntries(tile, role, source.size(), target.size());
        for (int i = 0; i < entries; i++) {
            target.get(i).deserializeNBT(provider, source.get(i).serializeNBT(provider));
        }
    }

    public static void copyOutputSlots(@NotNull BlockEntity tile, @NotNull String role, @NotNull List<? extends IInventorySlot> source,
                                       @NotNull List<? extends IInventorySlot> target) {
        int entries = matchingEntries(tile, role, source.size(), target.size());
        for (int i = 0; i < entries; i++) {
            target.get(i).setStack(source.get(i).getStack());
        }
    }

    private static int matchingEntries(@NotNull BlockEntity tile, @NotNull String role, int sourceCount, int targetCount) {
        if (sourceCount != targetCount) {
            ResourceLocation dimension = tile.getLevel() == null ? null : tile.getLevel().dimension().location();
            MekanismExtras.LOGGER.warn("Upgrade data {} count mismatch for block {} at {} in {}: source={}, target={}. Copying matching entries only.",
                    role, tile.getBlockState().getBlock(), tile.getBlockPos(), dimension, sourceCount, targetCount);
        }
        return Math.min(sourceCount, targetCount);
    }
}
