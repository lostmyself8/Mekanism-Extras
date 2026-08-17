package com.jerry.mekextras.common.integration.mekmm.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.integration.mekmm.MoreMachineIntegrationCompat;
import com.jerry.mekextras.common.integration.mekmm.block.prefab.ExtraMoreMachineBlockFactoryMachine.BlockExtraMoreMachineFactory;
import com.jerry.mekextras.common.integration.mekmm.content.blocktype.ExtraMoreMachineFactory;
import com.jerry.mekextras.common.integration.mekmm.item.block.machine.ItemBlockExtraMoreMachineFactory;
import com.jerry.mekextras.common.integration.mekmm.tile.factory.TileEntityExtraMoreMachineFactory;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;

import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.InputRecipeCache;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.recipe.MoreMachineRecipeType;
import com.jerry.mekmm.common.tile.factory.TileEntityReplicatingFactory;
import com.jerry.mekmm.common.tile.machine.TileEntityPlantingStation;
import com.jerry.mekmm.common.tile.machine.TileEntityReplicator;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ExtraMoreMachineBlocks {

    private ExtraMoreMachineBlocks() {}

    public static final BlockDeferredRegister MM_BLOCKS = new BlockDeferredRegister(MekanismExtras.MOD_ID);

    private static final Table<ExtraFactoryTier, MoreMachineFactoryType, BlockRegistryObject<BlockExtraMoreMachineFactory<?>, ItemBlockExtraMoreMachineFactory>> MM_FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (MoreMachineFactoryType type : MoreMachineIntegrationCompat.SUPPORTED_FACTORY_TYPES) {
                MM_FACTORIES.put(tier, type, registerMoreMachineFactory(ExtraMoreMachineBlockTypes.getExtraMoreMachineFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityExtraMoreMachineFactory<?>> BlockRegistryObject<BlockExtraMoreMachineFactory<?>, ItemBlockExtraMoreMachineFactory> registerMoreMachineFactory(ExtraMoreMachineFactory<TILE> type) {
        ExtraFactoryTier tier = (ExtraFactoryTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        BlockRegistryObject<BlockExtraMoreMachineFactory<?>, ItemBlockExtraMoreMachineFactory> factory = registerTieredBlock(tier, "_" + type.getMoreMachineFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockExtraMoreMachineFactory<>(type), ItemBlockExtraMoreMachineFactory::new);
        factory.forItemHolder(holder -> {
            int processes = tier.processes;
            Predicate<ItemStack> recipeInputPredicate = switch (type.getMoreMachineFactoryType()) {
                case RECYCLING -> s -> MoreMachineRecipeType.RECYCLING.getInputCache().containsInput(null, s);
                case PLANTING_STATION -> s -> MoreMachineRecipeType.PLANTING_STATION.getInputCache().containsInputA(null, s);
                case CNC_STAMPING -> s -> MoreMachineRecipeType.STAMPING.getInputCache().containsInputA(null, s);
                case CNC_LATHING -> s -> MoreMachineRecipeType.LATHING.getInputCache().containsInput(null, s);
                case CNC_ROLLING_MILL -> s -> MoreMachineRecipeType.ROLLING_MILL.getInputCache().containsInput(null, s);
                case REPLICATING -> TileEntityReplicator::isValidItemInput;
                default -> throw MoreMachineIntegrationCompat.unsupportedFactoryType(type.getMoreMachineFactoryType());
            };
            switch (type.getMoreMachineFactoryType()) {
                case CNC_STAMPING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addInput(MekanismRecipeType.COMBINING, InputRecipeCache.DoubleItem::containsInputB)
                        .addEnergy()
                        .build());
                case CNC_LATHING, CNC_ROLLING_MILL, RECYCLING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addEnergy()
                        .build());
                case PLANTING_STATION -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                                .addBasic(TileEntityPlantingStation.MAX_GAS * processes * processes, switch (type.getMoreMachineFactoryType()) {
                                    case PLANTING_STATION -> MoreMachineRecipeType.PLANTING_STATION;
                                    default -> throw new IllegalStateException("Factory type doesn't have a known gas recipe.");
                                }, InputRecipeCache.ItemChemical::containsInputB)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeInputPredicate, true)
                                .addChemicalFillOrConvertSlot(1)
                                .addEnergy()
                                .build());
                case REPLICATING -> holder.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                        .addBasic(TileEntityReplicatingFactory.MAX_GAS * processes * processes, TileEntityReplicatingFactory::isValidChemicalInput)
                        .build()).addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeInputPredicate)
                                .addChemicalFillOrConvertSlot(0)
                                .addEnergy()
                                .build());
            }
        });
        return factory;
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IAdvancedTier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        return MM_BLOCKS.register(tier.getAdvanceTier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    /**
     * Retrieves a Factory with a defined tier and recipe type.
     *
     * @param tier - tier to add to the Factory
     * @param type - recipe type to add to the Factory
     * @return factory with defined tier and recipe type
     */
    public static BlockRegistryObject<BlockExtraMoreMachineFactory<?>, ItemBlockExtraMoreMachineFactory> getExtraMoreMachineFactory(@NotNull ExtraFactoryTier tier, @NotNull MoreMachineFactoryType type) {
        return MM_FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockExtraMoreMachineFactory<?>, ItemBlockExtraMoreMachineFactory>[] getExtraMoreMachineFactoryBlocks() {
        return MM_FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }
}
