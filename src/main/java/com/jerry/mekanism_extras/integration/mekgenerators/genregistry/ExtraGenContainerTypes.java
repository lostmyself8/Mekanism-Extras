package com.jerry.mekanism_extras.integration.mekgenerators.genregistry;

import com.jerry.generator_extras.common.tile.reactor.TileEntityNaquadahReactorController;
import com.jerry.generator_extras.common.tile.reactor.TileEntityNaquadahReactorLogicAdapter;
import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenContainerTypes {
    public static final ContainerTypeDeferredRegister GEN_CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismExtras.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityNaquadahReactorController>> NAQUADAH_REACTOR_CONTROLLER = GEN_CONTAINER_TYPES.register(ExtraGenBlock.NAQUADAH_REACTOR_CONTROLLER, TileEntityNaquadahReactorController.class);
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityNaquadahReactorController>> NAQUADAH_REACTOR_FUEL = GEN_CONTAINER_TYPES.registerEmpty("naquadah_reactor_fuel", TileEntityNaquadahReactorController.class);
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityNaquadahReactorController>> NAQUADAH_REACTOR_HEAT = GEN_CONTAINER_TYPES.registerEmpty("naquadah_reactor_heat", TileEntityNaquadahReactorController.class);
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityNaquadahReactorLogicAdapter>> NAQUADAH_REACTOR_LOGIC_ADAPTER = GEN_CONTAINER_TYPES.registerEmpty(ExtraGenBlock.NAQUADAH_REACTOR_LOGIC_ADAPTER, TileEntityNaquadahReactorLogicAdapter.class);
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityNaquadahReactorController>> NAQUADAH_REACTOR_STATS = GEN_CONTAINER_TYPES.registerEmpty("naquadah_reactor_stats", TileEntityNaquadahReactorController.class);


    public static void register(IEventBus eventBus) {
        GEN_CONTAINER_TYPES.register(eventBus);
    }
}
