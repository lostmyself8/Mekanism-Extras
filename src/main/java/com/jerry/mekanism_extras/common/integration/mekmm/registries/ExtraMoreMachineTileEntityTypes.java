package com.jerry.mekanism_extras.common.integration.mekmm.registries;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.integration.mekmm.tile.*;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.tile.factory.*;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import net.minecraftforge.eventbus.api.IEventBus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;

public class ExtraMoreMachineTileEntityTypes {

    private ExtraMoreMachineTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MOD_ID);

    private static final Table<ExtraFactoryTier, MoreMachineFactoryType, TileEntityTypeRegistryObject<? extends TileEntityExtraMoreMachineFactory<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            FACTORIES.put(tier, MoreMachineFactoryType.RECYCLING, TILE_ENTITY_TYPES.register(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.RECYCLING), (pos, state) -> new TileEntityExtraRecyclingFactory(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.RECYCLING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.PLANTING, TILE_ENTITY_TYPES.register(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.PLANTING), (pos, state) -> new TileEntityExtraPlantingFactory(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.PLANTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.CNC_STAMPING, TILE_ENTITY_TYPES.register(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_STAMPING), (pos, state) -> new TileEntityExtraStampingFactory(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_STAMPING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.CNC_LATHING, TILE_ENTITY_TYPES.register(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_LATHING), (pos, state) -> new TileEntityExtraItemStackToItemStackMoreMachineFactory(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_LATHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.CNC_ROLLING_MILL, TILE_ENTITY_TYPES.register(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_ROLLING_MILL), (pos, state) -> new TileEntityExtraItemStackToItemStackMoreMachineFactory(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.CNC_ROLLING_MILL), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.REPLICATING, TILE_ENTITY_TYPES.register(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.REPLICATING), (pos, state) -> new TileEntityExtraReplicatingFactory(ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, MoreMachineFactoryType.REPLICATING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityExtraMoreMachineFactory<?>> getExtraMoreMachineFactoryTile(ExtraFactoryTier tier, MoreMachineFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityExtraMoreMachineFactory<?>>[] getExtraMoreMachineFactoryTiles() {
        return FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
