package com.jerry.mekanism_extras.util;

import com.jerry.mekanism_extras.api.MekanismExtraAPI;
import com.jerry.mekanism_extras.common.content.plasma.Plasma;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

public final class ChemicalTagHelper {
    @NotNull
    public static <CHEMICAL extends Chemical<CHEMICAL>> ChemicalTags<CHEMICAL> createChemicalTag(ResourceKey<? extends Registry<CHEMICAL>> registryKeySupplier, Supplier<IForgeRegistry<CHEMICAL>> registrySupplier) {
        try {
            Constructor<?> cons = ChemicalTags.class.getDeclaredConstructor(ResourceKey.class, Supplier.class);
            cons.setAccessible(true);
            return (ChemicalTags<CHEMICAL>) cons.newInstance(registryKeySupplier, registrySupplier);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static final ChemicalTags<Plasma> PLASMA = createChemicalTag(MekanismExtraAPI.PLASMA_REGISTRY_NAME, MekanismExtraAPI::plasmaRegistry);
}

