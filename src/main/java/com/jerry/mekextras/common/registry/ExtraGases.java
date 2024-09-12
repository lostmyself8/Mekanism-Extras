package com.jerry.mekextras.common.registry;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.ExtraChemicalConstants;
import mekanism.api.chemical.gas.Gas;
import mekanism.common.registration.impl.DeferredChemical;
import mekanism.common.registration.impl.GasDeferredRegister;
import net.neoforged.bus.api.IEventBus;

public class ExtraGases {
    private ExtraGases() {

    }

    public static final GasDeferredRegister EXTRA_GASES = new GasDeferredRegister(MekanismExtras.MOD_ID);

    public static final DeferredChemical.DeferredGas<Gas> MOLTEN_THERMONUCLEAR = EXTRA_GASES.register(ExtraChemicalConstants.MOLTEN_THERMONUCLEAR);

    public static void register(IEventBus eventBus) {
        EXTRA_GASES.register(eventBus);
    }
}
