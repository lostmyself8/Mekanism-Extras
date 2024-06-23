package com.jerry.mekanism_extras.common.content.plasma;

import mekanism.api.NBTConstants;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.chemical.gas.GasStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public interface IPlasmaTank extends IChemicalTank<Plasma, PlasmaStack>, IEmptyPlasmaProvider {
    @Override
    default PlasmaStack createStack(PlasmaStack stored, long size) {
        return new PlasmaStack(stored, size);
    }

    @Override
    default void deserializeNBT(CompoundTag nbt) {
        if (nbt.contains(NBTConstants.STORED, Tag.TAG_COMPOUND)) {
            setStackUnchecked(PlasmaStack.readFromNBT(nbt.getCompound(NBTConstants.STORED)));
        }
    }
}
