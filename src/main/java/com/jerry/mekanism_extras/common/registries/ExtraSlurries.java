package com.jerry.mekanism_extras.common.registries;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.resource.ExtraResource;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.common.registration.impl.SlurryDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraSlurries {

    private ExtraSlurries() {}

    private static final ExtraResource resource = ExtraResource.NAQUADAH;
    public static final SlurryDeferredRegister EXTRA_GEN_SLURRIES = new SlurryDeferredRegister(MekanismExtras.MOD_ID);

    public static final SlurryRegistryObject<Slurry, Slurry> DIRTY_AND_CLEAN_SLURRIES_NAQUADAH = EXTRA_GEN_SLURRIES.register("naquadah",
            builder -> builder.tint(resource.getTint()).ore(resource.getOreTag()));

    public static void register(IEventBus eventBus) {
        EXTRA_GEN_SLURRIES.register(eventBus);
    }
}
