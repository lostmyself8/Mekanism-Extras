package com.jerry.generator_extras.common.genregistries;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.registries.ExtraChemicalConstants;

import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.attribute.GasAttributes.*;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenGases {

    private ExtraGenGases() {}

    public static final GasDeferredRegister EXTRA_GEN_GASES = new GasDeferredRegister(MekanismExtras.MOD_ID);

    public static final GasRegistryObject<Gas> POLONIUM_CONTAINING_STEAM = EXTRA_GEN_GASES.register("polonium_containing_steam", 0x1B9E7B);
    public static final GasRegistryObject<Gas> POLONIUM208 = EXTRA_GEN_GASES.register(ExtraChemicalConstants.POLONIUM208);

    public static void register(IEventBus eventBus) {
        EXTRA_GEN_GASES.register(eventBus);
    }
}
