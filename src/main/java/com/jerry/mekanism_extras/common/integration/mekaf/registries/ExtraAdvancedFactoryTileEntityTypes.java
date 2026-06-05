package com.jerry.mekanism_extras.common.integration.mekaf.registries;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraCentrifugingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraCrystallizingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraDissolvingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraLiquifyingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraOxidizingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraPaintingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraPigmentExtractingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraPressurizedReactingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraWashingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import net.minecraftforge.eventbus.api.IEventBus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;

public class ExtraAdvancedFactoryTileEntityTypes {

    private ExtraAdvancedFactoryTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MOD_ID);

    private static final Table<ExtraFactoryTier, AdvancedFactoryType, TileEntityTypeRegistryObject<? extends TileEntityExtraAdvancedFactoryBase<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            FACTORIES.put(tier, AdvancedFactoryType.OXIDIZING, TILE_ENTITY_TYPES.register(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.OXIDIZING), (pos, state) -> new TileEntityExtraOxidizingFactory(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.OXIDIZING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.DISSOLVING, TILE_ENTITY_TYPES.register(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.DISSOLVING), (pos, state) -> new TileEntityExtraDissolvingFactory(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.DISSOLVING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.WASHING, TILE_ENTITY_TYPES.register(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.WASHING), (pos, state) -> new TileEntityExtraWashingFactory(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.WASHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.CRYSTALLIZING, TILE_ENTITY_TYPES.register(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.CRYSTALLIZING), (pos, state) -> new TileEntityExtraCrystallizingFactory(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.CRYSTALLIZING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.PRESSURISED_REACTING, TILE_ENTITY_TYPES.register(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING), (pos, state) -> new TileEntityExtraPressurizedReactingFactory(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.CENTRIFUGING, TILE_ENTITY_TYPES.register(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.CENTRIFUGING), (pos, state) -> new TileEntityExtraCentrifugingFactory(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.CENTRIFUGING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.LIQUIFYING, TILE_ENTITY_TYPES.register(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.LIQUIFYING), (pos, state) -> new TileEntityExtraLiquifyingFactory(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.LIQUIFYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.PIGMENT_EXTRACTING, TILE_ENTITY_TYPES.register(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.PIGMENT_EXTRACTING), (pos, state) -> new TileEntityExtraPigmentExtractingFactory(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.PIGMENT_EXTRACTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.PAINTING, TILE_ENTITY_TYPES.register(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.PAINTING), (pos, state) -> new TileEntityExtraPaintingFactory(ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(tier, AdvancedFactoryType.PAINTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityExtraAdvancedFactoryBase<?>> getExtraAdvancedFactoryTile(ExtraFactoryTier tier, AdvancedFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityExtraAdvancedFactoryBase<?>>[] getExtraAdvancedFactoryTiles() {
        return FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
