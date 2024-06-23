package com.jerry.mekanism_extras.util;

import com.jerry.mekanism_extras.common.block.machine.forcefield.ForceFieldGeneratorFrequency;
import mekanism.common.lib.frequency.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class FrequencyTypeHelper {
    @NotNull
    public static <FREQ extends Frequency> FrequencyType<FREQ> createFrequencyType(String typeName, BiFunction<String, UUID, FREQ> creationFunc, Supplier<FREQ> baseCreationFunc) {
        try {
            Constructor<?> freqTypeCons = FrequencyType.class
                    .getDeclaredConstructor(String.class, BiFunction.class, Supplier.class, FrequencyManagerWrapper.Type.class, IdentitySerializer.class);
            freqTypeCons.setAccessible(true);
            return (FrequencyType<FREQ>) freqTypeCons.newInstance(typeName, creationFunc, baseCreationFunc, FrequencyManagerWrapper.Type.PUBLIC_PRIVATE, IdentitySerializer.NAME);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static final FrequencyType<ForceFieldGeneratorFrequency> FORCEFIELD
            = createFrequencyType("ForceField", ForceFieldGeneratorFrequency::new, ForceFieldGeneratorFrequency::new);  // not the same constructor!
}
