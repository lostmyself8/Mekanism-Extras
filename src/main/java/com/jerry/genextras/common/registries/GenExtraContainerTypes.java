package com.jerry.genextras.common.registries;

import com.jerry.mekextras.MekanismExtras;

import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorController;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorLogicAdapter;

import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

public class GenExtraContainerTypes {

    private GenExtraContainerTypes() {}

    public static final ContainerTypeDeferredRegister GEN_EXTRA_CONTAINER_TYPE = new ContainerTypeDeferredRegister(MekanismExtras.MOD_ID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityNaquadahReactorController>> NAQUADAH_REACTOR_CONTROLLER = GEN_EXTRA_CONTAINER_TYPE.custom(GenExtraBlocks.NAQUADAH_REACTOR_CONTROLLER, TileEntityNaquadahReactorController.class).offset(5, 0).build();
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityNaquadahReactorController>> NAQUADAH_REACTOR_FUEL = GEN_EXTRA_CONTAINER_TYPE.registerEmpty("naquadah_reactor_fuel", TileEntityNaquadahReactorController.class);
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityNaquadahReactorController>> NAQUADAH_REACTOR_HEAT = GEN_EXTRA_CONTAINER_TYPE.registerEmpty("naquadah_reactor_heat", TileEntityNaquadahReactorController.class);
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityNaquadahReactorLogicAdapter>> NAQUADAH_REACTOR_LOGIC_ADAPTER = GEN_EXTRA_CONTAINER_TYPE.registerEmpty(GenExtraBlocks.NAQUADAH_REACTOR_LOGIC_ADAPTER, TileEntityNaquadahReactorLogicAdapter.class);
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityNaquadahReactorController>> NAQUADAH_REACTOR_STATS = GEN_EXTRA_CONTAINER_TYPE.registerEmpty("naquadah_reactor_stats", TileEntityNaquadahReactorController.class);
}
