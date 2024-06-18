package com.jerry.mekanism_extras.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.chemical.gas.Gas;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGases {
    private ExtraGases() {
    }

    public static final GasDeferredRegister EXTRA_GASES = new GasDeferredRegister(MekanismExtras.MODID);
    public static final GasRegistryObject<Gas> MOLTEN_THERMONUCLEAR = EXTRA_GASES.register(ExtraChemicalConstants.MOLTEN_THERMONUCLEAR);
    public static final GasRegistryObject<Gas> SPECTRUM_PIGMENTS = EXTRA_GASES.register(ExtraChemicalConstants.SPECTRUM_PIGMENTS);

    public static void register(IEventBus eventBus) {
        EXTRA_GASES.register(eventBus);
    }
}
