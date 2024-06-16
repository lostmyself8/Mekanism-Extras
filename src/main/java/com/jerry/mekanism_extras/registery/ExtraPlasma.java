package com.jerry.mekanism_extras.registery;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.content.plasma.Plasma;
import com.jerry.mekanism_extras.common.content.plasma.PlasmaConstants;
import com.jerry.mekanism_extras.common.content.plasma.PlasmaDeferredRegister;
import com.jerry.mekanism_extras.common.content.plasma.PlasmaRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraPlasma {

    private ExtraPlasma() {}

    public static final PlasmaDeferredRegister PLASMA = new PlasmaDeferredRegister(MekanismExtras.MODID);
    public static final PlasmaRegistryObject<Plasma> HYDROGEN = PLASMA.register(PlasmaConstants.HYDROGEN);

    public static void register(IEventBus eventBus) {
        PLASMA.register(eventBus);
    }

}
