package com.jerry.mekanism_extras.common.content.plasma;

import com.jerry.mekanism_extras.api.MekanismExtraAPI;
import com.jerry.mekanism_extras.api.NBTConstants;
import com.jerry.mekanism_extras.util.ChemicalTagHelper;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalUtils;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class Plasma extends Chemical<Plasma> implements IPlasmaProvider {

    public Plasma(PlasmaBuilder builder) {
        super(builder, ChemicalTagHelper.PLASMA);
    }

    public static Plasma readFromNBT(@Nullable CompoundTag nbtTags) {
        return ChemicalUtils.readChemicalFromNBT(nbtTags, MekanismExtraAPI.EMPTY_PLASMA, NBTConstants.PLASMA_NAME, Plasma::getFromRegistry);
    }

    public static Plasma getFromRegistry(@Nullable ResourceLocation name) {
        return ChemicalUtils.readChemicalFromRegistry(name, MekanismExtraAPI.EMPTY_PLASMA, MekanismExtraAPI.plasmaRegistry());
    }

    @Override
    public CompoundTag write(CompoundTag nbtTags) {
        nbtTags.putString(NBTConstants.PLASMA_NAME, getRegistryName().toString());
        return nbtTags;
    }

    @Override
    public String toString() {
        return "[Plasma: " + getRegistryName() + "]";
    }

    @Override
    public final boolean isEmptyType() {
        return this == MekanismExtraAPI.EMPTY_PLASMA;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public final ResourceLocation getRegistryName() {
        //May be null if called before the object is registered
        IForgeRegistry<Plasma> registry = MekanismExtraAPI.plasmaRegistry();
        return registry == null ? null : registry.getKey(this);
    }

    @Override
    protected String getDefaultTranslationKey() {
        return Util.makeDescriptionId("plasma", getRegistryName());
    }
}
