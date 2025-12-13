package com.jerry.mekextras.common.integration.mekaf.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.integration.mekaf.inventory.container.tile.ExtraAdvancedFactoryContainer;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraAdvancedFactoryBase;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

public class ExtraAdvancedFactoryContainerTypes {

    private ExtraAdvancedFactoryContainerTypes() {}

    public static final ContainerTypeDeferredRegister AF_CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismExtras.MOD_ID);

    public static ContainerTypeRegistryObject<MekanismTileContainer<TileEntityExtraAdvancedFactoryBase<?>>> ADVANCED_FACTORY = AF_CONTAINER_TYPES.register("advanced_factory", advancedFactoryClass(), ExtraAdvancedFactoryContainer::new);

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Class<TileEntityExtraAdvancedFactoryBase<?>> advancedFactoryClass() {
        return (Class) TileEntityExtraAdvancedFactoryBase.class;
    }
}
