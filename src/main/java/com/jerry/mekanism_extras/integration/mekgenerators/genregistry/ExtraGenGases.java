package com.jerry.mekanism_extras.integration.mekgenerators.genregistry;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.chemical.gas.Gas;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenGases {
    private ExtraGenGases() {
    }

    public static final GasDeferredRegister EXTRA_GEN_GASES = new GasDeferredRegister(MekanismExtras.MODID);
    public static final GasRegistryObject<Gas> REACTOR_WASTE = EXTRA_GEN_GASES.register("reactor_waste", 0x161D02);
    public static final GasRegistryObject<Gas> POLONIUM208 = EXTRA_GEN_GASES.register("polonium-208", 0x1B9E7B);

    public static void register(IEventBus eventBus) {
        EXTRA_GEN_GASES.register(eventBus);
    }
}
