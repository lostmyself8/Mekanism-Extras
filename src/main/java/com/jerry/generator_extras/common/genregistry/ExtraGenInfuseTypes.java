package com.jerry.generator_extras.common.genregistry;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.chemical.infuse.InfuseType;
import mekanism.common.registration.impl.InfuseTypeDeferredRegister;
import mekanism.common.registration.impl.InfuseTypeRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenInfuseTypes {

    private ExtraGenInfuseTypes() {}

    public static final InfuseTypeDeferredRegister EXTRA_GEN_INFUSE_TYPES = new InfuseTypeDeferredRegister(MekanismExtras.MODID);

    public static final InfuseTypeRegistryObject<InfuseType> HEAT_INSULATING_COATING = EXTRA_GEN_INFUSE_TYPES.register("heat_insulating_coating", 0xB3AF08);

    public static void register(IEventBus bus) {
        EXTRA_GEN_INFUSE_TYPES.register(bus);
    }
}
