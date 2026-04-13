package com.jerry.mekextras.common.integration.mekaf.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.block.prefab.BlockExtraAdvancedFactoryMachine.BlockExtraAdvancedFactory;
import com.jerry.mekextras.common.integration.mekaf.content.blocktype.ExtraAdvancedFactory;
import com.jerry.mekextras.common.integration.mekaf.item.block.machine.ItemBlockExtraAdvancedFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.fluid.FluidTanksBuilder;
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
import com.jerry.mekaf.common.attachments.containers.chemical.AFChemicalTanksBuilder;
import com.jerry.mekaf.common.attachments.containers.item.AFItemSlotsBuilder;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekaf.common.tile.factory.TileEntityLiquifyingFactory;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ExtraAdvancedFactoryBlocks {

    private ExtraAdvancedFactoryBlocks() {}

    public static final BlockDeferredRegister AF_BLOCKS = new BlockDeferredRegister(MekanismExtras.MOD_ID);

    private static final Table<ExtraFactoryTier, AdvancedFactoryType, BlockRegistryObject<BlockExtraAdvancedFactory<?>, ItemBlockExtraAdvancedFactory>> AF_FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                AF_FACTORIES.put(tier, type, registerAdvancedFactory(ExtraAdvancedFactoryBlockTypes.getExtraAdvancedFactory(tier, type)));
            }
        }
    }

    private static <TILE extends TileEntityExtraAdvancedFactoryBase<?>> BlockRegistryObject<BlockExtraAdvancedFactory<?>, ItemBlockExtraAdvancedFactory> registerAdvancedFactory(ExtraAdvancedFactory<TILE> type) {
        ExtraFactoryTier tier = (ExtraFactoryTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        BlockRegistryObject<BlockExtraAdvancedFactory<?>, ItemBlockExtraAdvancedFactory> factory = registerTieredBlock(tier, "_" + type.getAdvancedFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockExtraAdvancedFactory<>(type), ItemBlockExtraAdvancedFactory::new);
        factory.forItemHolder(holder -> {
            int processes = tier.processes;
            Predicate<ItemStack> recipeItemInputPredicate = switch (type.getAdvancedFactoryType()) {
                case OXIDIZING -> s -> MekanismRecipeType.OXIDIZING.getInputCache().containsInput(null, s);
                case DISSOLVING -> s -> MekanismRecipeType.DISSOLUTION.getInputCache().containsInputA(null, s);
                case PRESSURISED_REACTING -> s -> MekanismRecipeType.REACTION.getInputCache().containsInputA(null, s);
                case LIQUIFYING -> TileEntityLiquifyingFactory::isValidInputStatic;
                case PIGMENT_EXTRACTING -> s -> MekanismRecipeType.PIGMENT_EXTRACTING.getInputCache().containsInput(null, s);
                case PAINTING -> s -> MekanismRecipeType.PAINTING.getInputCache().containsInputA(null, s);
                default -> null;
            };
            Predicate<ChemicalStack> recipeChemicalInputPredicate = switch (type.getAdvancedFactoryType()) {
                case DISSOLVING -> s -> MekanismRecipeType.DISSOLUTION.getInputCache().containsInputB(null, s);
                case WASHING -> s -> MekanismRecipeType.WASHING.getInputCache().containsInputB(null, s);
                case CRYSTALLIZING -> s -> MekanismRecipeType.CRYSTALLIZING.getInputCache().containsInput(null, s);
                case PRESSURISED_REACTING -> s -> MekanismRecipeType.REACTION.getInputCache().containsInputC(null, s);
                case CENTRIFUGING -> s -> MekanismRecipeType.CENTRIFUGING.getInputCache().containsInput(null, s);
                case PAINTING -> s -> MekanismRecipeType.PAINTING.getInputCache().containsInputB(null, s);
                default -> null;
            };
            switch (type.getAdvancedFactoryType()) {
                // 没问题
                case OXIDIZING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                // 化学品输出（多个）
                                .addOutputFactoryTank(processes, TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * processes * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> AFItemSlotsBuilder.builder()
                                // 物品输入（多个）
                                .addInputFactorySlots(processes, recipeItemInputPredicate)
                                .addEnergy()
                                .build());
                // 输入储罐错位，输出储罐气体消失
                case DISSOLVING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                // 化学品输入
                                .addBasic(TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * processes * processes, recipeChemicalInputPredicate)
                                // 化学品输出（多个）
                                .addOutputFactoryTank(processes, TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * processes * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> AFItemSlotsBuilder.builder()
                                .addInputFactorySlots(processes, recipeItemInputPredicate)
                                .addChemicalFillOrConvertSlot(0)
                                .addEnergy()
                                .build());
                // 没问题
                case WASHING -> holder
                        .addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                                .addBasic(TileEntityExtraAdvancedFactoryBase.MAX_FLUID * processes, MekanismRecipeType.WASHING, InputRecipeCache.FluidChemical::containsInputA)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                .addInputFactoryTank(processes, TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * processes, recipeChemicalInputPredicate)
                                .addOutputFactoryTank(processes, TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addFluidFillSlot(0)
                                .addOutput()
                                .addEnergy()
                                .build());
                // 使用工作台合成升级机器导致能量槽错位（mek原生bug）
                case PRESSURISED_REACTING -> holder
                        .addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                                .addBasic(TileEntityExtraAdvancedFactoryBase.MAX_FLUID * processes * processes, MekanismRecipeType.REACTION, InputRecipeCache.ItemFluidChemical::containsInputB)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                .addBasic(TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * processes * processes, recipeChemicalInputPredicate)
                                .addBasic(TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * processes * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeItemInputPredicate)
                                .addEnergy()
                                .build());
                // 使用工作台合成升级机器导致能量槽错位（mek原生bug）
                case CRYSTALLIZING -> holder.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                        .addInputFactoryTank(processes, TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * processes, recipeChemicalInputPredicate)
                        .build()).addAttachmentOnlyContainers(ContainerType.ITEM, () -> AFItemSlotsBuilder.builder()
                                .addOutputFactorySlots(tier.processes)
                                .addEnergy()
                                .build());
                // 没问题
                case CENTRIFUGING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                .addInputFactoryTank(processes, TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * processes, recipeChemicalInputPredicate)
                                .addOutputFactoryTank(processes, TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addEnergy()
                                .build());
                // 偶现升级后槽位不可以的情况
                case LIQUIFYING -> holder
                        .addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                                .addBasic(TileEntityLiquifyingFactory.MAX_FLUID * processes * processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeItemInputPredicate)
                                .addEnergy()
                                .build());
                case PIGMENT_EXTRACTING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                .addOutputFactoryTank(processes, TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * tier.processes)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> AFItemSlotsBuilder.builder()
                                .addInputFactorySlots(processes, recipeItemInputPredicate)
                                .addEnergy()
                                .build());
                case PAINTING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> AFChemicalTanksBuilder.builder()
                                .addInputFactoryTank(tier.processes, TileEntityExtraAdvancedFactoryBase.MAX_CHEMICAL * tier.processes, recipeChemicalInputPredicate)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeItemInputPredicate)
                                .addEnergy()
                                .build());
            }
        });
        return factory;
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IAdvancedTier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        return AF_BLOCKS.register(tier.getAdvanceTier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    /**
     * Retrieves a Factory with a defined tier and recipe type.
     *
     * @param tier - tier to add to the Factory
     * @param type - recipe type to add to the Factory
     * @return factory with defined tier and recipe type
     */
    public static BlockRegistryObject<BlockExtraAdvancedFactory<?>, ItemBlockExtraAdvancedFactory> getExtraAdvancedFactory(@NotNull ExtraFactoryTier tier, @NotNull AdvancedFactoryType type) {
        return AF_FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockExtraAdvancedFactory<?>, ItemBlockExtraAdvancedFactory>[] getExtraAdvancedFactoryBlocks() {
        return AF_FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }
}
