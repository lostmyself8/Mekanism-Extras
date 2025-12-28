package com.jerry.genextras.common.registries;

import com.jerry.mekextras.MekanismExtras;

import com.jerry.genextras.common.tile.naquadah.*;

import mekanism.common.capabilities.Capabilities;
import mekanism.common.integration.energy.EnergyCompatUtils;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

public class GenExtraTileEntityTypes {

    private GenExtraTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister GEN_EXTRA_TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MOD_ID);

    // Naquadah Reactor
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorController> NAQUADAH_REACTOR_CONTROLLER = GEN_EXTRA_TILE_ENTITY_TYPES
            .mekBuilder(GenExtraBlocks.NAQUADAH_REACTOR_CONTROLLER, TileEntityNaquadahReactorController::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIGURABLE)
            // Never allow the gas handler, fluid handler, or energy cap to be enabled here even though internally we
            // can handle both of them
            .without(Capabilities.CHEMICAL.block(), Capabilities.FLUID.block(), Capabilities.HEAT)
            .without(EnergyCompatUtils.getLoadedEnergyCapabilities())
            .build();
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorCasing> NAQUADAH_REACTOR_CASING = GEN_EXTRA_TILE_ENTITY_TYPES
            .mekBuilder(GenExtraBlocks.NAQUADAH_REACTOR_CASING, TileEntityNaquadahReactorCasing::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIGURABLE)
            .build();
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorPort> NAQUADAH_REACTOR_PORT = GEN_EXTRA_TILE_ENTITY_TYPES
            .mekBuilder(GenExtraBlocks.NAQUADAH_REACTOR_PORT, TileEntityNaquadahReactorPort::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIGURABLE)
            .build();
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorLogicAdapter> NAQUADAH_REACTOR_LOGIC_ADAPTER = GEN_EXTRA_TILE_ENTITY_TYPES
            .mekBuilder(GenExtraBlocks.NAQUADAH_REACTOR_LOGIC_ADAPTER, TileEntityNaquadahReactorLogicAdapter::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIGURABLE)
            .withSimple(Capabilities.CONFIG_CARD)
            .build();
    public static final TileEntityTypeRegistryObject<TileEntityLeadCoatedLaserFocusMatrix> LEAD_COATED_LASER_FOCUS_MATRIX = GEN_EXTRA_TILE_ENTITY_TYPES
            .mekBuilder(GenExtraBlocks.LEAD_COATED_LASER_FOCUS_MATRIX, TileEntityLeadCoatedLaserFocusMatrix::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.LASER_RECEPTOR)
            .withSimple(Capabilities.CONFIGURABLE)
            .build();
}
