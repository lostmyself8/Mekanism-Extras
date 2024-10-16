package com.jerry.mekextras.common.registry;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.api.tier.IAdvanceTier;
import com.jerry.mekextras.common.attachments.containers.chemical.gas.ExtraComponentBackedChemicalTankGasTank;
import com.jerry.mekextras.common.attachments.containers.chemical.infuse_type.ExtraComponentBackedChemicalTankInfusionTank;
import com.jerry.mekextras.common.attachments.containers.chemical.pigment.ExtraComponentBackedChemicalTankPigmentTank;
import com.jerry.mekextras.common.attachments.containers.chemical.slurry.ExtraComponentBackedChemicalTankSlurryTank;
import com.jerry.mekextras.common.attachments.containers.fluid.ExtraComponentBackedFluidTankFluidTank;
import com.jerry.mekextras.common.attachments.containers.item.ExtraComponentBackedBinInventorySlot;
import com.jerry.mekextras.common.block.BlockLargeCapRadioactiveWasteBarrel;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.block.basic.ExtraBlockBin;
import com.jerry.mekextras.common.block.basic.ExtraBlockFluidTank;
import com.jerry.mekextras.common.item.block.*;
import com.jerry.mekextras.common.tier.*;
import com.jerry.mekextras.common.tile.*;
import com.jerry.mekextras.common.block.ExtraBlockEnergyCube;
import com.jerry.mekextras.common.item.block.transmitter.ExtraItemBlockUniversalCable;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityUniversalCable;
import com.jerry.mekextras.common.item.block.transmitter.ExtraItemBlockLogisticalTransporter;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;
import com.jerry.mekextras.common.item.block.transmitter.ExtraItemBlockMechanicalPipe;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityMechanicalPipe;
import com.jerry.mekextras.common.item.block.transmitter.ExtraItemBlockThermodynamicConductor;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityThermodynamicConductor;
import com.jerry.mekextras.common.item.block.transmitter.ExtraItemBlockPressurizedTube;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityPressurizedTube;
import com.jerry.mekextras.common.item.block.machine.ExtraItemBlockFluidTank;
import com.jerry.mekextras.common.tile.machine.TileEntityAdvanceElectricPump;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionPort;
import com.jerry.mekextras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekextras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.gas.GasTanksBuilder;
import mekanism.common.attachments.containers.chemical.infuse.InfusionTanksBuilder;
import mekanism.common.attachments.containers.chemical.merged.MergedTankCreator;
import mekanism.common.attachments.containers.chemical.pigment.PigmentTanksBuilder;
import mekanism.common.attachments.containers.chemical.slurry.SlurryTanksBuilder;
import mekanism.common.attachments.containers.fluid.FluidTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.block.interfaces.IHasDescription;
import mekanism.common.block.prefab.BlockBasicMultiblock;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.resource.BlockResourceInfo;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ExtraBlocks {
    public static final BlockDeferredRegister EXTRA_BLOCKS = new BlockDeferredRegister(MekanismExtras.MOD_ID);

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IAdvanceTier tier, String suffix,
                                                                                                                      Function<MapColor, ? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        return registerTieredBlock(tier, suffix, () -> blockSupplier.apply(tier.getAdvanceTier().getMapColor()), itemCreator);
    }
    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IAdvanceTier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        return EXTRA_BLOCKS.register(tier.getAdvanceTier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(String registerName,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        return EXTRA_BLOCKS.register(registerName, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block & IHasDescription> BlockRegistryObject<BLOCK, ItemBlockTooltip<BLOCK>> registerBlock(String name,
                                                                                                                             Supplier<? extends BLOCK> blockSupplier) {
        return EXTRA_BLOCKS.register(name, blockSupplier, ItemBlockTooltip::new);
    }
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> ABSOLUTE_BIN = registerBin(ExtraBlockTypes.ABSOLUTE_BIN);
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> SUPREME_BIN = registerBin(ExtraBlockTypes.SUPREME_BIN);
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> COSMIC_BIN = registerBin(ExtraBlockTypes.COSMIC_BIN);
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> INFINITE_BIN = registerBin(ExtraBlockTypes.INFINITE_BIN);

    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityReinforcedInductionCasing>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityReinforcedInductionCasing>>> REINFORCED_INDUCTION_CASING = registerBlock("reinforced_induction_casing", () -> new BlockBasicMultiblock<>(ExtraBlockTypes.REINFORCED_INDUCTION_CASING, properties -> properties.mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityReinforcedInductionPort>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityReinforcedInductionPort>>> REINFORCED_INDUCTION_PORT = registerBlock("reinforced_induction_port", () -> new BlockBasicMultiblock<>(ExtraBlockTypes.REINFORCED_INDUCTION_PORT, properties -> properties.mapColor(MapColor.COLOR_LIGHT_GRAY)));

    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>, ExtraItemBlockInductionCell> ABSOLUTE_INDUCTION_CELL = registerInductionCell(ExtraBlockTypes.ABSOLUTE_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>, ExtraItemBlockInductionCell> SUPREME_INDUCTION_CELL = registerInductionCell(ExtraBlockTypes.SUPREME_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>, ExtraItemBlockInductionCell> COSMIC_INDUCTION_CELL = registerInductionCell(ExtraBlockTypes.COSMIC_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>, ExtraItemBlockInductionCell> INFINITE_INDUCTION_CELL = registerInductionCell(ExtraBlockTypes.INFINITE_INDUCTION_CELL);

    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>, ExtraItemBlockInductionProvider> ABSOLUTE_INDUCTION_PROVIDER = registerInductionProvider(ExtraBlockTypes.ABSOLUTE_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>, ExtraItemBlockInductionProvider> SUPREME_INDUCTION_PROVIDER = registerInductionProvider(ExtraBlockTypes.SUPREME_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>, ExtraItemBlockInductionProvider> COSMIC_INDUCTION_PROVIDER = registerInductionProvider(ExtraBlockTypes.COSMIC_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>, ExtraItemBlockInductionProvider> INFINITE_INDUCTION_PROVIDER = registerInductionProvider(ExtraBlockTypes.INFINITE_INDUCTION_PROVIDER);

    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> ABSOLUTE_ENERGY_CUBE = registerEnergyCube(ExtraBlockTypes.ABSOLUTE_ENERGY_CUBE);
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> SUPREME_ENERGY_CUBE = registerEnergyCube(ExtraBlockTypes.SUPREME_ENERGY_CUBE);
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> COSMIC_ENERGY_CUBE = registerEnergyCube(ExtraBlockTypes.COSMIC_ENERGY_CUBE);
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> INFINITE_ENERGY_CUBE = registerEnergyCube(ExtraBlockTypes.INFINITE_ENERGY_CUBE);

    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> ABSOLUTE_FLUID_TANK = registerFluidTank(ExtraBlockTypes.ABSOLUTE_FLUID_TANK);
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> SUPREME_FLUID_TANK = registerFluidTank(ExtraBlockTypes.SUPREME_FLUID_TANK);
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> COSMIC_FLUID_TANK = registerFluidTank(ExtraBlockTypes.COSMIC_FLUID_TANK);
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> INFINITE_FLUID_TANK = registerFluidTank(ExtraBlockTypes.INFINITE_FLUID_TANK);

    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityUniversalCable>, ExtraItemBlockUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = registerUniversalCable("absolute", ExtraBlockTypes.ABSOLUTE_UNIVERSAL_CABLE);
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityUniversalCable>, ExtraItemBlockUniversalCable> SUPREME_UNIVERSAL_CABLE = registerUniversalCable("supreme", ExtraBlockTypes.SUPREME_UNIVERSAL_CABLE);
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityUniversalCable>, ExtraItemBlockUniversalCable> COSMIC_UNIVERSAL_CABLE = registerUniversalCable("cosmic", ExtraBlockTypes.COSMIC_UNIVERSAL_CABLE);
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityUniversalCable>, ExtraItemBlockUniversalCable> INFINITE_UNIVERSAL_CABLE = registerUniversalCable("infinite", ExtraBlockTypes.INFINITE_UNIVERSAL_CABLE);

    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>, ExtraItemBlockMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = registerMechanicalPipe("absolute", ExtraBlockTypes.ABSOLUTE_MECHANICAL_PIPE);
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>, ExtraItemBlockMechanicalPipe> SUPREME_MECHANICAL_PIPE = registerMechanicalPipe("supreme", ExtraBlockTypes.SUPREME_MECHANICAL_PIPE);
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>, ExtraItemBlockMechanicalPipe> COSMIC_MECHANICAL_PIPE = registerMechanicalPipe("cosmic", ExtraBlockTypes.COSMIC_MECHANICAL_PIPE);
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>, ExtraItemBlockMechanicalPipe> INFINITE_MECHANICAL_PIPE = registerMechanicalPipe("infinite", ExtraBlockTypes.INFINITE_MECHANICAL_PIPE);

    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityPressurizedTube>, ExtraItemBlockPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = registerPressurizedTube("absolute", ExtraBlockTypes.ABSOLUTE_PRESSURIZED_TUBE);
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityPressurizedTube>, ExtraItemBlockPressurizedTube> SUPREME_PRESSURIZED_TUBE = registerPressurizedTube("supreme", ExtraBlockTypes.SUPREME_PRESSURIZED_TUBE);
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityPressurizedTube>, ExtraItemBlockPressurizedTube> COSMIC_PRESSURIZED_TUBE = registerPressurizedTube("cosmic", ExtraBlockTypes.COSMIC_PRESSURIZED_TUBE);
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityPressurizedTube>, ExtraItemBlockPressurizedTube> INFINITE_PRESSURIZED_TUBE = registerPressurizedTube("infinite", ExtraBlockTypes.INFINITE_PRESSURIZED_TUBE);

    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter>, ExtraItemBlockLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("absolute", ExtraBlockTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER);
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter>, ExtraItemBlockLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("supreme", ExtraBlockTypes.SUPREME_LOGISTICAL_TRANSPORTER);
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter>, ExtraItemBlockLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("cosmic", ExtraBlockTypes.COSMIC_LOGISTICAL_TRANSPORTER);
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter>, ExtraItemBlockLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("infinite", ExtraBlockTypes.INFINITE_LOGISTICAL_TRANSPORTER);

    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityThermodynamicConductor>, ExtraItemBlockThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("absolute", ExtraBlockTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR);
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityThermodynamicConductor>, ExtraItemBlockThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("supreme", ExtraBlockTypes.SUPREME_THERMODYNAMIC_CONDUCTOR);
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityThermodynamicConductor>, ExtraItemBlockThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("cosmic", ExtraBlockTypes.COSMIC_THERMODYNAMIC_CONDUCTOR);
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityThermodynamicConductor>, ExtraItemBlockThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("infinite", ExtraBlockTypes.INFINITE_THERMODYNAMIC_CONDUCTOR);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> ABSOLUTE_CHEMICAL_TANK = registerChemicalTank(ExtraBlockTypes.ABSOLUTE_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> SUPREME_CHEMICAL_TANK = registerChemicalTank(ExtraBlockTypes.SUPREME_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> COSMIC_CHEMICAL_TANK = registerChemicalTank(ExtraBlockTypes.COSMIC_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> INFINITE_CHEMICAL_TANK = registerChemicalTank(ExtraBlockTypes.INFINITE_CHEMICAL_TANK);

    public static final BlockRegistryObject<BlockLargeCapRadioactiveWasteBarrel, ItemBlockLargeCapRadioactiveWasteBarrel> ABSOLUTE_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlockTypes.ABSOLUTE_RADIOACTIVE_WASTE_BARREL);
    public static final BlockRegistryObject<BlockLargeCapRadioactiveWasteBarrel, ItemBlockLargeCapRadioactiveWasteBarrel> SUPREME_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlockTypes.SUPREME_RADIOACTIVE_WASTE_BARREL);
    public static final BlockRegistryObject<BlockLargeCapRadioactiveWasteBarrel, ItemBlockLargeCapRadioactiveWasteBarrel> COSMIC_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlockTypes.COSMIC_RADIOACTIVE_WASTE_BARREL);
    public static final BlockRegistryObject<BlockLargeCapRadioactiveWasteBarrel, ItemBlockLargeCapRadioactiveWasteBarrel> INFINITE_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlockTypes.INFINITE_RADIOACTIVE_WASTE_BARREL);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityAdvanceElectricPump, Machine<TileEntityAdvanceElectricPump>>, ItemBlockTooltip<BlockTile.BlockTileModel<TileEntityAdvanceElectricPump, Machine<TileEntityAdvanceElectricPump>>>> ADVANCE_ELECTRIC_PUMP =
            EXTRA_BLOCKS.register("advance_electric_pump", () -> new BlockTile.BlockTileModel<>(ExtraBlockTypes.ADVANCE_ELECTRIC_PUMP, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor())), ItemBlockTooltip::new)
                    .forItemHolder(holder -> holder
                            .addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                                    .addBasic(TileEntityAdvanceElectricPump.MAX_FLUID)
                                    .build()
                            ).addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                    .addFluidDrainSlot(0)
                                    .addOutput()
                                    .addEnergy()
                                    .build()
                            )
                    );
    private static BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> registerBin(BlockTypeTile<ExtraTileEntityBin> type) {
        BTier tier = (BTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_bin", color -> new ExtraBlockBin(type, properties -> properties.mapColor(color)), ExtraItemBlockBin::new)
                .forItemHolder(holder -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addSlot(ExtraComponentBackedBinInventorySlot::create)
                        .build()
                ));
    }

    private static BlockRegistryObject<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>, ExtraItemBlockInductionCell> registerInductionCell(BlockTypeTile<ExtraTileEntityInductionCell> type) {
        ICTier tier = (ICTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_induction_cell", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), ExtraItemBlockInductionCell::new);
    }

    private static BlockRegistryObject<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>, ExtraItemBlockInductionProvider> registerInductionProvider(BlockTypeTile<ExtraTileEntityInductionProvider> type) {
        IPTier tier = (IPTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_induction_provider", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), ExtraItemBlockInductionProvider::new);
    }

    private static BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> registerEnergyCube(Machine<ExtraTileEntityEnergyCube> type) {
        ECTier tier = (ECTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_energy_cube", () -> new ExtraBlockEnergyCube(type), ExtraItemBlockEnergyCube::new)
                .forItemHolder(holder -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addEnergy()
                        .addDrainEnergy()
                        .build()
                ));
    }

    private static BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> registerFluidTank(Machine<ExtraTileEntityFluidTank> type) {
        FTTier tier = (FTTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_fluid_tank", () -> new ExtraBlockFluidTank(type), ExtraItemBlockFluidTank::new)
                .forItemHolder(holder -> holder
                        .addAttachedContainerCapabilities(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                                .addTank(ExtraComponentBackedFluidTankFluidTank::create)
                                .build()
                        ).addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addFluidInputSlot(0)
                                .addOutput()
                                .build()
                        )
                );
    }

    private static BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityUniversalCable>, ExtraItemBlockUniversalCable> registerUniversalCable(
            String nameTier, BlockTypeTile<ExtraTileEntityUniversalCable> type) {
        return registerTieredBlock(nameTier + "_universal_cable", () -> new BlockSmallTransmitter<>(type), ExtraItemBlockUniversalCable::new);
    }

    private static BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>, ExtraItemBlockMechanicalPipe> registerMechanicalPipe(
            String nameTier, BlockTypeTile<ExtraTileEntityMechanicalPipe> type) {
        return registerTieredBlock(nameTier + "_mechanical_pipe", () -> new BlockLargeTransmitter<>(type), ExtraItemBlockMechanicalPipe::new);
    }

    private static BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityPressurizedTube>, ExtraItemBlockPressurizedTube> registerPressurizedTube(
            String nameTier, BlockTypeTile<ExtraTileEntityPressurizedTube> type) {
        return registerTieredBlock(nameTier + "_pressurized_tube", () -> new BlockSmallTransmitter<>(type), ExtraItemBlockPressurizedTube::new);
    }

    private static BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter>, ExtraItemBlockLogisticalTransporter> registerLogisticalTransporter(
            String nameTier, BlockTypeTile<ExtraTileEntityLogisticalTransporter> type) {
        return registerTieredBlock(nameTier + "_logistical_transporter", () -> new BlockLargeTransmitter<>(type), ExtraItemBlockLogisticalTransporter::new);
    }

    private static BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityThermodynamicConductor>, ExtraItemBlockThermodynamicConductor> registerThermodynamicConductor(
            String nameTier, BlockTypeTile<ExtraTileEntityThermodynamicConductor> type) {
        return registerTieredBlock(nameTier + "_thermodynamic_conductor", () -> new BlockSmallTransmitter<>(type), ExtraItemBlockThermodynamicConductor::new);
    }

    private static BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> registerChemicalTank(
            Machine<ExtraTileEntityChemicalTank> type) {
        CTTier tier = (CTTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_chemical_tank", color -> new BlockTile.BlockTileModel<>(type, properties -> properties.mapColor(color)), ExtraItemBlockChemicalTank::new)
                .forItemHolder(holder -> {
                            final MergedTankCreator mergedTankCreator = new MergedTankCreator(
                                    ExtraComponentBackedChemicalTankGasTank::create,
                                    ExtraComponentBackedChemicalTankInfusionTank::create,
                                    ExtraComponentBackedChemicalTankPigmentTank::create,
                                    ExtraComponentBackedChemicalTankSlurryTank::create
                            );
                            holder.addAttachedContainerCapabilities(ContainerType.GAS, () -> GasTanksBuilder.builder().addTank(mergedTankCreator).build())
                                    .addAttachedContainerCapabilities(ContainerType.INFUSION, () -> InfusionTanksBuilder.builder().addTank(mergedTankCreator).build())
                                    .addAttachedContainerCapabilities(ContainerType.PIGMENT, () -> PigmentTanksBuilder.builder().addTank(mergedTankCreator).build())
                                    .addAttachedContainerCapabilities(ContainerType.SLURRY, () -> SlurryTanksBuilder.builder().addTank(mergedTankCreator).build())
                                    .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                            .addMergedChemicalDrainSlot(0, 0, 0, 0)
                                            .addMergedChemicalFillSlot(0, 0, 0, 0)
                                            .build()
                                    );
                        }
                );
    }

    private static BlockRegistryObject<BlockLargeCapRadioactiveWasteBarrel, ItemBlockLargeCapRadioactiveWasteBarrel> registryWasteBarrel(
            BlockTypeTile<TileEntityLargeCapRadioactiveWasteBarrel> type) {
        RWBTier tier = (RWBTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_radioactive_waste_barrel", () -> new BlockLargeCapRadioactiveWasteBarrel(type), ItemBlockLargeCapRadioactiveWasteBarrel::new);
    }

    public static void register(IEventBus eventBus) {
        EXTRA_BLOCKS.register(eventBus);
    }
}
