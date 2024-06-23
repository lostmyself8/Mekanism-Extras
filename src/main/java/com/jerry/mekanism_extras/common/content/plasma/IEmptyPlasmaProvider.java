package com.jerry.mekanism_extras.common.content.plasma;

import mekanism.api.chemical.IEmptyStackProvider;
import org.jetbrains.annotations.NotNull;

public interface IEmptyPlasmaProvider extends IEmptyStackProvider<Plasma, PlasmaStack> {
    @NotNull
    @Override
    default PlasmaStack getEmptyStack() { return PlasmaStack.EMPTY; }
}
