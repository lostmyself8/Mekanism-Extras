package com.jerry.mekanism_extras.lib;

import net.minecraft.nbt.CompoundTag;

public record BlockInfo(
        CompoundTag nbt,
        CompoundTag state
) {
    public boolean isBlockEntity() {
        return !nbt.isEmpty();
    }
}
