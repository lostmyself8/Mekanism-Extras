package com.jerry.mekanism_extras.common.integration.mekmm.registries;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekanism_extras.common.integration.mekmm.block.prefab.BlockExtraMoreMachineFactory;
import com.jerry.mekanism_extras.common.integration.mekmm.content.blocktype.ExtraMoreMachineFactory;
import com.jerry.mekanism_extras.common.integration.mekmm.item.block.machine.ItemBlockExtraMoreMachineFactory;
import com.jerry.mekanism_extras.common.integration.mekmm.tile.TileEntityExtraMoreMachineFactory;
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
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ExtraMoreMachineBlocks {

    private ExtraMoreMachineBlocks() {}

    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(MekanismExtras.MOD_ID);

    private static final Table<ExtraFactoryTier, MoreMachineFactoryType, BlockRegistryObject<BlockExtraMoreMachineFactory<?>, ItemBlockExtraMoreMachineFactory>> FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerFactory(ExtraMoreMachineBlockTypes.getExtraMoreMachineFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityExtraMoreMachineFactory<?>> BlockRegistryObject<BlockExtraMoreMachineFactory<?>, ItemBlockExtraMoreMachineFactory> registerFactory(ExtraMoreMachineFactory<TILE> type) {
        return registerTieredBlock(type, "_" + type.getMoreMachineFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockExtraMoreMachineFactory<>(type), ItemBlockExtraMoreMachineFactory::new);
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
    public static BlockRegistryObject<BlockExtraMoreMachineFactory<?>, ItemBlockExtraMoreMachineFactory> getExtraMoreMachineFactory(@NotNull ExtraFactoryTier tier, @NotNull MoreMachineFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockExtraMoreMachineFactory<?>, ItemBlockExtraMoreMachineFactory>[] getExtraMoreMachineFactoryBlocks() {
        return FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
