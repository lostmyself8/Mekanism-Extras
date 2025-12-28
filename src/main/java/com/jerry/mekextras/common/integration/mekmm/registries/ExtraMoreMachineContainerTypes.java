package com.jerry.mekextras.common.integration.mekmm.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.integration.mekmm.inventory.container.tile.ExtraMoreMachineFactoryContainer;
import com.jerry.mekextras.common.integration.mekmm.tile.factory.TileEntityExtraMoreMachineFactory;

import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

public class ExtraMoreMachineContainerTypes {

    private ExtraMoreMachineContainerTypes() {}

    public static final ContainerTypeDeferredRegister MM_CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismExtras.MOD_ID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityExtraMoreMachineFactory<?>>> MORE_MACHINE_FACTORY = MM_CONTAINER_TYPES.register("more_machine_factory", factoryClass(), ExtraMoreMachineFactoryContainer::new);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Class<TileEntityExtraMoreMachineFactory<?>> factoryClass() {
        return (Class) TileEntityExtraMoreMachineFactory.class;
    }
}
