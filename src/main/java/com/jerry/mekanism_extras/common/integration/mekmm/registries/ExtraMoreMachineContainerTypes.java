package com.jerry.mekanism_extras.common.integration.mekmm.registries;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.integration.mekmm.inventory.container.ExtraMoreMachineFactoryContainer;
import com.jerry.mekanism_extras.common.integration.mekmm.tile.TileEntityExtraMoreMachineFactory;

import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraMoreMachineContainerTypes {

    private ExtraMoreMachineContainerTypes() {}

    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismExtras.MOD_ID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityExtraMoreMachineFactory<?>>> MORE_MACHINE_FACTORY = CONTAINER_TYPES.register("more_machine_factory", factoryClass(), ExtraMoreMachineFactoryContainer::new);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Class<TileEntityExtraMoreMachineFactory<?>> factoryClass() {
        return (Class) TileEntityExtraMoreMachineFactory.class;
    }

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}
