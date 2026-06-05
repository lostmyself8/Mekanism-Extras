package com.jerry.mekanism_extras.common.integration.mekaf.registries;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.integration.mekaf.inventory.container.ExtraAdvancedFactoryContainer;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;

import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraAdvancedFactoryContainerTypes {

    private ExtraAdvancedFactoryContainerTypes() {}

    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismExtras.MOD_ID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityExtraAdvancedFactoryBase<?>>> ADVANCED_FACTORY = CONTAINER_TYPES.register("advanced_factory", factoryClass(), ExtraAdvancedFactoryContainer::new);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Class<TileEntityExtraAdvancedFactoryBase<?>> factoryClass() {
        return (Class) TileEntityExtraAdvancedFactoryBase.class;
    }

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}
