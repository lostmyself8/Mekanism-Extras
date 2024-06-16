package com.jerry.mekanism_extras.api;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.content.plasma.EmptyPlasma;
import com.jerry.mekanism_extras.common.content.plasma.Plasma;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class MekanismExtraAPI {
    private static <T> ResourceKey<Registry<T>> registryKey(@SuppressWarnings("unused") Class<T> compileTimeTypeValidator, String path) {
        return ResourceKey.createRegistryKey(new ResourceLocation(MekanismExtras.MODID, path));
    }

    public static final ResourceKey<Registry<Plasma>> PLASMA_REGISTRY_NAME
            = registryKey(Plasma.class, "plasma");

    private static IForgeRegistry<Plasma> PLASMA_REGISTRY;

    public static Plasma EMPTY_PLASMA;

    static {
        try {
            EMPTY_PLASMA = new EmptyPlasma();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static IForgeRegistry<Plasma> plasmaRegistry() {
        if (PLASMA_REGISTRY == null) {
            PLASMA_REGISTRY = RegistryManager.ACTIVE.getRegistry(PLASMA_REGISTRY_NAME);
        }
        return PLASMA_REGISTRY;
    }
}
