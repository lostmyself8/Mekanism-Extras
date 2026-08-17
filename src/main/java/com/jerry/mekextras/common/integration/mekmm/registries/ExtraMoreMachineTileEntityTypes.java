package com.jerry.mekextras.common.integration.mekmm.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.integration.mekmm.block.prefab.ExtraMoreMachineBlockFactoryMachine.BlockExtraMoreMachineFactory;
import com.jerry.mekextras.common.integration.mekmm.item.block.machine.ItemBlockExtraMoreMachineFactory;
import com.jerry.mekextras.common.integration.mekmm.tile.factory.*;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;

import mekanism.common.capabilities.Capabilities;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.tile.factory.TileEntityMoreMachineFactory;

public class ExtraMoreMachineTileEntityTypes {

    private ExtraMoreMachineTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister MM_TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MOD_ID);

    private static final Table<ExtraFactoryTier, MoreMachineFactoryType, TileEntityTypeRegistryObject<? extends TileEntityExtraMoreMachineFactory<?>>> MM_FACTORIES = HashBasedTable.create();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            registerFactory(tier, MoreMachineFactoryType.RECYCLING, TileEntityExtraRecyclingFactory::new);
            registerFactory(tier, MoreMachineFactoryType.PLANTING_STATION, TileEntityExtraPlantingFactory::new);
            registerFactory(tier, MoreMachineFactoryType.CNC_STAMPING, TileEntityExtraStampingFactory::new);
            registerFactory(tier, MoreMachineFactoryType.CNC_LATHING, TileEntityExtraMoreMachineItemStackToItemStackFactory::new);
            registerFactory(tier, MoreMachineFactoryType.CNC_ROLLING_MILL, TileEntityExtraMoreMachineItemStackToItemStackFactory::new);
            registerFactory(tier, MoreMachineFactoryType.PRESSING, TileEntityExtraPressingFactory::new);
            registerFactory(tier, MoreMachineFactoryType.REPLICATING, TileEntityExtraReplicatingFactory::new);
        }
    }

    private static void registerFactory(ExtraFactoryTier tier, MoreMachineFactoryType type, MMBlockEntityFactory<? extends TileEntityExtraMoreMachineFactory<?>> factoryConstructor) {
        BlockRegistryObject<BlockExtraMoreMachineFactory<?>, ItemBlockExtraMoreMachineFactory> block = ExtraMoreMachineBlocks.getExtraMoreMachineFactory(tier, type);
        TileEntityTypeRegistryObject<? extends TileEntityExtraMoreMachineFactory<?>> tileRO = MM_TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> factoryConstructor.create(block, pos, state))
                .clientTicker(TileEntityMekanism::tickClient)
                .serverTicker(TileEntityMekanism::tickServer)
                .withSimple(Capabilities.CONFIG_CARD)
                .build();
        MM_FACTORIES.put(tier, type, tileRO);
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityExtraMoreMachineFactory<?>> getExtraMoreMachineFactoryTile(ExtraFactoryTier tier, MoreMachineFactoryType type) {
        return MM_FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityMoreMachineFactory<?>>[] getExtraMoreMachineFactoryTiles() {
        return MM_FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    @FunctionalInterface
    private interface MMBlockEntityFactory<BE extends BlockEntity> {

        BE create(Holder<Block> block, BlockPos pos, BlockState state);
    }
}
