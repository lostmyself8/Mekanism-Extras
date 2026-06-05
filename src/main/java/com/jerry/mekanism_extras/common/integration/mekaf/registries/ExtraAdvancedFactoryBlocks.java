package com.jerry.mekanism_extras.common.integration.mekaf.registries;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekanism_extras.common.integration.mekaf.block.prefab.BlockExtraAdvancedFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.content.blocktype.ExtraAdvancedFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.item.block.machine.ItemBlockExtraAdvancedFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ExtraAdvancedFactoryBlocks {

    private ExtraAdvancedFactoryBlocks() {}

    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(MekanismExtras.MOD_ID);

    private static final Table<ExtraFactoryTier, AdvancedFactoryType, BlockRegistryObject<BlockExtraAdvancedFactory<?>, ItemBlockExtraAdvancedFactory>> FACTORIES = HashBasedTable.create();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerFactory(ExtraAdvancedFactoryBlockTypes.getExtraAdvancedFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityExtraAdvancedFactoryBase<?>> BlockRegistryObject<BlockExtraAdvancedFactory<?>, ItemBlockExtraAdvancedFactory> registerFactory(ExtraAdvancedFactory<TILE> type) {
        return registerTieredBlock(type, "_" + type.getAdvancedFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockExtraAdvancedFactory<>(type), ItemBlockExtraAdvancedFactory::new);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(BlockType type, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return registerTieredBlock(Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier(), suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IAdvancedTier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return BLOCKS.register(tier.getAdvanceTier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    /**
     * Retrieves a Factory with a defined tier and recipe type.
     *
     * @param tier - tier to add to the Factory
     * @param type - recipe type to add to the Factory
     *
     * @return factory with defined tier and recipe type
     */
    public static BlockRegistryObject<BlockExtraAdvancedFactory<?>, ItemBlockExtraAdvancedFactory> getExtraAdvancedFactory(@NotNull ExtraFactoryTier tier, @NotNull AdvancedFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockExtraAdvancedFactory<?>, ItemBlockExtraAdvancedFactory>[] getExtraAdvancedFactoryBlocks() {
        return FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
