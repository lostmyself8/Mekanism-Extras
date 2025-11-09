package com.jerry.generator_extras.common.genregistry;

import com.jerry.mekanism_extras.MekanismExtras;

import com.jerry.generator_extras.common.tile.TileEntityLeadCoatedGlass;
import com.jerry.generator_extras.common.tile.naquadah.*;
import com.jerry.generator_extras.common.tile.plasma.*;

import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenTileEntityTypes {

    public static final TileEntityTypeDeferredRegister GEN_TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MODID);
    // Naquadah Reactor
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorController> NAQUADAH_REACTOR_CONTROLLER = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.NAQUADAH_REACTOR_CONTROLLER, TileEntityNaquadahReactorController::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorCasing> NAQUADAH_REACTOR_CASING = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.NAQUADAH_REACTOR_CASING, TileEntityNaquadahReactorCasing::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorLogicAdapter> NAQUADAH_REACTOR_LOGIC_ADAPTER = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.NAQUADAH_REACTOR_LOGIC_ADAPTER, TileEntityNaquadahReactorLogicAdapter::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorPort> NAQUADAH_REACTOR_PORT = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.NAQUADAH_REACTOR_PORT, TileEntityNaquadahReactorPort::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityLeadCoatedGlass> LEAD_COATED_GLASS = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.LEAD_COATED_GLASS, TileEntityLeadCoatedGlass::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityLeadCoatedLaserFocusMatrix> LEAD_COATED_LASER_FOCUS_MATRIX = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.LEAD_COATED_LASER_FOCUS_MATRIX, TileEntityLeadCoatedLaserFocusMatrix::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);

    // Plasma Evaporation Plant
    public static final TileEntityTypeRegistryObject<TileEntityPlasmaEvaporationBlock> PLASMA_EVAPORATION_BLOCK = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.PLASMA_EVAPORATION_BLOCK, TileEntityPlasmaEvaporationBlock::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityPlasmaEvaporationController> PLASMA_EVAPORATION_CONTROLLER = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.PLASMA_EVAPORATION_CONTROLLER, TileEntityPlasmaEvaporationController::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityPlasmaEvaporationValve> PLASMA_EVAPORATION_VALVE = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.PLASMA_EVAPORATION_VALVE, TileEntityPlasmaEvaporationValve::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityPlasmaInsulationLayer> PLASMA_INSULATION_LAYER = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.PLASMA_INSULATION_LAYER, TileEntityPlasmaInsulationLayer::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityPlasmaEvaporationVent> PLASMA_EVAPORATION_VENT = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.PLASMA_EVAPORATION_VENT, TileEntityPlasmaEvaporationVent::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityFusionReactorPlasmaExtractingPort> FUSION_REACTOR_PLASMA_EXTRACTING_PORT = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.FUSION_REACTOR_PLASMA_EXTRACTING_PORT, TileEntityFusionReactorPlasmaExtractingPort::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);

    public static void register(IEventBus eventBus) {
        GEN_TILE_ENTITY_TYPES.register(eventBus);
    }
}
