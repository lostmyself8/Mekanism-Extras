package com.jerry.mekanism_extras.common.util;

import mekanism.api.math.FloatingLong;
import mekanism.api.math.FloatingLongSupplier;

import org.jetbrains.annotations.NotNull;

public record ExtraFloatingLong(FloatingLong number) implements FloatingLongSupplier {

    @Override
    public @NotNull FloatingLong get() {
        return number;
    }
}
