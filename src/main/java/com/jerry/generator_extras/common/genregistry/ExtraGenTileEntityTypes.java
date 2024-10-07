package com.jerry.generator_extras.common.genregistry;

import com.jerry.generator_extras.common.tile.reactor.*;
import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import com.jerry.generator_extras.common.tile.TileEntityLeadCoatedGlass;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenTileEntityTypes {
    public static final TileEntityTypeDeferredRegister GEN_TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MODID);
    //Naquadah Reactor
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorController> NAQUADAH_REACTOR_CONTROLLER = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.NAQUADAH_REACTOR_CONTROLLER, TileEntityNaquadahReactorController::new);
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorCasing> NAQUADAH_REACTOR_CASING = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.NAQUADAH_REACTOR_CASING, TileEntityNaquadahReactorCasing::new);
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorLogicAdapter> NAQUADAH_REACTOR_LOGIC_ADAPTER = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.NAQUADAH_REACTOR_LOGIC_ADAPTER, TileEntityNaquadahReactorLogicAdapter::new);
    public static final TileEntityTypeRegistryObject<TileEntityNaquadahReactorPort> NAQUADAH_REACTOR_PORT = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.NAQUADAH_REACTOR_PORT, TileEntityNaquadahReactorPort::new);
    public static final TileEntityTypeRegistryObject<TileEntityLeadCoatedGlass> LEAD_COATED_GLASS = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.LEAD_COATED_GLASS, TileEntityLeadCoatedGlass::new);
    public static final TileEntityTypeRegistryObject<TileEntityLeadCoatedLaserFocusMatrix> LEAD_COATED_LASER_FOCUS_MATRIX = GEN_TILE_ENTITY_TYPES.register(ExtraGenBlocks.LEAD_COATED_LASER_FOCUS_MATRIX, TileEntityLeadCoatedLaserFocusMatrix::new);
    public static void register(IEventBus eventBus) {
        GEN_TILE_ENTITY_TYPES.register(eventBus);
    }
}
