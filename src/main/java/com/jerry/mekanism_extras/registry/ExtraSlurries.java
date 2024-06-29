package com.jerry.mekanism_extras.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.resource.ExtraResource;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.common.registration.impl.SlurryDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraSlurries {
    private ExtraSlurries() {
    }

    private static final ExtraResource resource = ExtraResource.NAQUADAH;
    public static final SlurryDeferredRegister EXTRA_SLURRIES = new SlurryDeferredRegister(MekanismExtras.MODID);

    public static final SlurryRegistryObject<Slurry, Slurry> DIRTY_AND_CLEAN_SLURRIES_NAQUADAH = EXTRA_SLURRIES.register("naquadah",
            builder -> builder.tint(resource.getTint()).ore(resource.getOreTag()));
    public static void register(IEventBus eventBus) {
        EXTRA_SLURRIES.register(eventBus);
    }
}
