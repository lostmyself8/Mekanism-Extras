package com.jerry.mekanism_extras.common.content.plasma;

import com.jerry.mekanism_extras.api.MekanismExtraAPI;
import mekanism.api.NBTConstants;
import mekanism.api.chemical.ChemicalStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PlasmaStack extends ChemicalStack<Plasma> {

    public static final PlasmaStack EMPTY = new PlasmaStack(MekanismExtraAPI.EMPTY_PLASMA, 0);

    public PlasmaStack(IPlasmaProvider plasmaProvider, long amount) {
        super(plasmaProvider.getChemical(), amount);
    }

    public PlasmaStack(PlasmaStack stack, long amount) {
        this(stack.getType(), amount);
    }

    @NotNull
    @Override
    protected IForgeRegistry<Plasma> getRegistry() {
        return MekanismExtraAPI.plasmaRegistry();
    }

    @NotNull
    @Override
    protected Plasma getEmptyChemical() {
        return MekanismExtraAPI.EMPTY_PLASMA;
    }

    public static PlasmaStack readFromNBT(@Nullable CompoundTag nbtTags) {
        if (nbtTags == null || nbtTags.isEmpty()) {
            return EMPTY;
        }
        Plasma type = Plasma.readFromNBT(nbtTags);
        if (type.isEmptyType()) {
            return EMPTY;
        }
        long amount = nbtTags.getLong(NBTConstants.AMOUNT);
        if (amount <= 0) {
            return EMPTY;
        }
        return new PlasmaStack(type, amount);
    }

    public static PlasmaStack readFromPacket(FriendlyByteBuf buf) {
        Plasma plasma = buf.readRegistryIdSafe(Plasma.class);
        if (plasma.isEmptyType()) {
            return EMPTY;
        }
        return new PlasmaStack(plasma, buf.readVarLong());
    }

    @NotNull
    @Override
    public PlasmaStack copy() {
        if (isEmpty()) {
            return EMPTY;
        }
        return new PlasmaStack(this, getAmount());
    }
}
