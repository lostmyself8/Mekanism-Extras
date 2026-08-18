package com.jerry.mekextras.common.integration.mekaf.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.block.prefab.BlockExtraAdvancedFactoryMachine.BlockExtraAdvancedFactory;
import com.jerry.mekextras.common.integration.mekaf.item.block.machine.ItemBlockExtraAdvancedFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.*;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;
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
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;

public class ExtraAdvancedFactoryTileEntityTypes {

    private ExtraAdvancedFactoryTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister AF_TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MOD_ID);

    private static final Table<ExtraFactoryTier, AdvancedFactoryType, TileEntityTypeRegistryObject<? extends TileEntityExtraAdvancedFactoryBase<?, ?>>> AF_FACTORIES = HashBasedTable.create();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            registerFactory(tier, AdvancedFactoryType.OXIDIZING, TileEntityExtraItemStackToChemicalStackFactory::new);
            registerFactory(tier, AdvancedFactoryType.DISSOLVING, TileEntityExtraDissolvingFactory::new);
            registerFactory(tier, AdvancedFactoryType.WASHING, TileEntityExtraWashingFactory::new);
            registerFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING, TileEntityExtraPRCFactory::new);
            registerFactory(tier, AdvancedFactoryType.CRYSTALLIZING, TileEntityExtraCrystallizingFactory::new);
            registerFactory(tier, AdvancedFactoryType.CENTRIFUGING, TileEntityExtraCentrifugingFactory::new);
            registerFactory(tier, AdvancedFactoryType.LIQUIFYING, TileEntityExtraLiquifyingFactory::new);
            registerFactory(tier, AdvancedFactoryType.PIGMENT_EXTRACTING, TileEntityExtraItemStackToChemicalStackFactory::new);
            registerFactory(tier, AdvancedFactoryType.PAINTING, TileEntityExtraPaintingFactory::new);
        }
    }

    private static void registerFactory(ExtraFactoryTier tier, AdvancedFactoryType type, ExtraAdvancedBlockEntityFactory<? extends TileEntityExtraAdvancedFactoryBase<?, ?>> factoryConstructor) {
        BlockRegistryObject<BlockExtraAdvancedFactory<?>, ItemBlockExtraAdvancedFactory> block = ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, type);
        TileEntityTypeRegistryObject<? extends TileEntityExtraAdvancedFactoryBase<?, ?>> tileRO = AF_TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> factoryConstructor.create(block, pos, state, type))
                .clientTicker(TileEntityMekanism::tickClient)
                .serverTicker(TileEntityMekanism::tickServer)
                .withSimple(Capabilities.CONFIG_CARD)
                .build();
        AF_FACTORIES.put(tier, type, tileRO);
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityExtraAdvancedFactoryBase<?, ?>> getExtraAdvancedFactoryTile(ExtraFactoryTier tier, AdvancedFactoryType type) {
        return AF_FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityExtraAdvancedFactoryBase<?, ?>>[] getExtraAdvancedFactoryTiles() {
        return AF_FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    @FunctionalInterface
    private interface ExtraAdvancedBlockEntityFactory<BE extends BlockEntity> {

        BE create(Holder<Block> block, BlockPos pos, BlockState state, AdvancedFactoryType type);
    }
}
