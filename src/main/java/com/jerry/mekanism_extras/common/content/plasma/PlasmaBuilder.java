package com.jerry.mekanism_extras.common.content.plasma;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

@NothingNullByDefault
public class PlasmaBuilder extends ChemicalBuilder<Plasma, PlasmaBuilder> {

    protected PlasmaBuilder(ResourceLocation texture) { super(texture); }

    public static PlasmaBuilder builder() {
        return builder(new ResourceLocation(MekanismExtras.MODID, "liquid/liquid"));
    }

    public static PlasmaBuilder builder(ResourceLocation texture) {
        return new PlasmaBuilder(Objects.requireNonNull(texture));
    }

}
