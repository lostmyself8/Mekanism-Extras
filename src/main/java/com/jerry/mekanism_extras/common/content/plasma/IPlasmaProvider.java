package com.jerry.mekanism_extras.common.content.plasma;

import mekanism.api.providers.IChemicalProvider;
import org.jetbrains.annotations.NotNull;

public interface IPlasmaProvider extends IChemicalProvider<Plasma> {
    @NotNull
    @Override
    default PlasmaStack getStack(long size) {
        return new PlasmaStack(getChemical(), size);
    }
}
