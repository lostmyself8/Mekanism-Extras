package com.jerry.mekanism_extras.common.util;

import mekanism.api.math.FloatingLong;
import mekanism.api.math.FloatingLongSupplier;

import org.jetbrains.annotations.NotNull;

public class ExtraFloatingLong implements FloatingLongSupplier {

    private final FloatingLong number;

    public ExtraFloatingLong(FloatingLong number) {
        this.number = number;
    }

    @Override
    public @NotNull FloatingLong get() {
        return number;
    }
}
