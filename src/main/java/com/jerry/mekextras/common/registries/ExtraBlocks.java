package com.jerry.mekextras.common.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import com.jerry.mekextras.common.attachments.containers.chemical.ExtraComponentBackedChemicalTankTank;
import com.jerry.mekextras.common.attachments.containers.fluid.ExtraComponentBackedFluidTankFluidTank;
import com.jerry.mekextras.common.attachments.containers.item.ExtraComponentBackedBinInventorySlot;
import com.jerry.mekextras.common.block.BlockExtraEnergyCube;
import com.jerry.mekextras.common.block.BlockLargeCapRadioactiveWasteBarrel;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.block.basic.BlockExtraBin;
import com.jerry.mekextras.common.block.basic.BlockExtraFluidTank;
import com.jerry.mekextras.common.block.basic.BlockExtraResource;
import com.jerry.mekextras.common.block.prefab.BlockExtraFactoryMachine.BlockExtraFactory;
import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.item.block.*;
import com.jerry.mekextras.common.item.block.machine.ItemBlockExtraFactory;
import com.jerry.mekextras.common.item.block.machine.ItemBlockExtraFluidTank;
import com.jerry.mekextras.common.item.block.transmitter.ItemBlockExtraLogisticalTransporter;
import com.jerry.mekextras.common.item.block.transmitter.ItemBlockExtraMechanicalPipe;
import com.jerry.mekextras.common.item.block.transmitter.ItemBlockExtraPressurizedTube;
import com.jerry.mekextras.common.item.block.transmitter.ItemBlockExtraThermodynamicConductor;
import com.jerry.mekextras.common.item.block.transmitter.ItemBlockExtraUniversalCable;
import com.jerry.mekextras.common.resource.BlockExtraResourceInfo;
import com.jerry.mekextras.common.resource.ExtraResource;
import com.jerry.mekextras.common.resource.ore.ExtraOreType;
import com.jerry.mekextras.common.tier.*;
import com.jerry.mekextras.common.tile.*;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekextras.common.tile.machine.TileEntityAdvancedElectricPump;
import com.jerry.mekextras.common.tile.multiblock.TileEntityExtraInductionCell;
import com.jerry.mekextras.common.tile.multiblock.TileEntityExtraInductionProvider;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionPort;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraLogisticalTransporter;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraMechanicalPipe;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraPressurizedTube;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraThermodynamicConductor;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraUniversalCable;
import com.jerry.mekextras.common.util.ExtraEnumUtils;

import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.fluid.FluidTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.block.BlockOre;
import mekanism.common.block.interfaces.IHasDescription;
import mekanism.common.block.prefab.BlockBasicMultiblock;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.item.block.ItemBlockMekanism;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.InputRecipeCache;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.resource.IResource;
import mekanism.common.resource.ore.OreBlockType;
import mekanism.common.resource.ore.OreType;
import mekanism.common.tile.machine.TileEntityMetallurgicInfuser;
import mekanism.common.tile.prefab.TileEntityAdvancedElectricMachine;
import mekanism.common.util.EnumUtils;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ExtraBlocks {

    public static final BlockDeferredRegister EXTRA_BLOCKS = new BlockDeferredRegister(MekanismExtras.MOD_ID);

    public static final Map<IResource, BlockRegistryObject<?, ?>> PROCESSED_RESOURCE_BLOCKS = new LinkedHashMap<>();
    public static final Map<OreType, OreBlockType> ORES = new LinkedHashMap<>();

    private static final Table<ExtraFactoryTier, FactoryType, BlockRegistryObject<BlockExtraFactory<?>, ItemBlockExtraFactory>> FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                if (MekanismExtras.hooks.evolvedMekanism.isLoaded()) {
                    if (type != EMFactoryType.ALLOYING) {
                        FACTORIES.put(tier, type, registerFactory(ExtraBlockTypes.getAdvancedFactory(tier, type)));
                    }
                } else {
                    FACTORIES.put(tier, type, registerFactory(ExtraBlockTypes.getAdvancedFactory(tier, type)));
                }
            }
        }
        // resource blocks
        for (ExtraResource resource : ExtraEnumUtils.EXTRA_RESOURCES) {
            if (resource.getResourceBlockInfo() != null) {
                PROCESSED_RESOURCE_BLOCKS.put(resource, registerResourceBlock(resource.getResourceBlockInfo()));
            }
            BlockExtraResourceInfo rawResource = resource.getRawResourceBlockInfo();
            if (rawResource != null) {
                PROCESSED_RESOURCE_BLOCKS.put(rawResource, registerResourceBlock(rawResource));
            }
        }
        // ores
        for (OreType ore : EnumUtils.ORE_TYPES) {
            if (ore == ExtraOreType.NAQUADAH) {
                ORES.put(ore, registerOre(ore));
            }
        }
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IAdvancedTier tier, String suffix,
                                                                                                                      Function<MapColor, ? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        return registerTieredBlock(tier, suffix, () -> blockSupplier.apply(tier.getAdvanceTier().getMapColor()), itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IAdvancedTier tier, String suffix,
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

    public static final BlockRegistryObject<BlockExtraBin, ItemBlockExtraBin> ABSOLUTE_BIN = registerBin(ExtraBlockTypes.ABSOLUTE_BIN);
    public static final BlockRegistryObject<BlockExtraBin, ItemBlockExtraBin> SUPREME_BIN = registerBin(ExtraBlockTypes.SUPREME_BIN);
    public static final BlockRegistryObject<BlockExtraBin, ItemBlockExtraBin> COSMIC_BIN = registerBin(ExtraBlockTypes.COSMIC_BIN);
    public static final BlockRegistryObject<BlockExtraBin, ItemBlockExtraBin> INFINITE_BIN = registerBin(ExtraBlockTypes.INFINITE_BIN);

    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityReinforcedInductionCasing>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityReinforcedInductionCasing>>> REINFORCED_INDUCTION_CASING = registerBlock("reinforced_induction_casing", () -> new BlockBasicMultiblock<>(ExtraBlockTypes.REINFORCED_INDUCTION_CASING, properties -> properties.mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityReinforcedInductionPort>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityReinforcedInductionPort>>> REINFORCED_INDUCTION_PORT = registerBlock("reinforced_induction_port", () -> new BlockBasicMultiblock<>(ExtraBlockTypes.REINFORCED_INDUCTION_PORT, properties -> properties.mapColor(MapColor.COLOR_LIGHT_GRAY)));

    public static final BlockRegistryObject<BlockTile<TileEntityExtraInductionCell, BlockTypeTile<TileEntityExtraInductionCell>>, ItemBlockExtraInductionCell> ABSOLUTE_INDUCTION_CELL = registerInductionCell(ExtraBlockTypes.ABSOLUTE_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityExtraInductionCell, BlockTypeTile<TileEntityExtraInductionCell>>, ItemBlockExtraInductionCell> SUPREME_INDUCTION_CELL = registerInductionCell(ExtraBlockTypes.SUPREME_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityExtraInductionCell, BlockTypeTile<TileEntityExtraInductionCell>>, ItemBlockExtraInductionCell> COSMIC_INDUCTION_CELL = registerInductionCell(ExtraBlockTypes.COSMIC_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityExtraInductionCell, BlockTypeTile<TileEntityExtraInductionCell>>, ItemBlockExtraInductionCell> INFINITE_INDUCTION_CELL = registerInductionCell(ExtraBlockTypes.INFINITE_INDUCTION_CELL);

    public static final BlockRegistryObject<BlockTile<TileEntityExtraInductionProvider, BlockTypeTile<TileEntityExtraInductionProvider>>, ItemBlockExtraInductionProvider> ABSOLUTE_INDUCTION_PROVIDER = registerInductionProvider(ExtraBlockTypes.ABSOLUTE_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityExtraInductionProvider, BlockTypeTile<TileEntityExtraInductionProvider>>, ItemBlockExtraInductionProvider> SUPREME_INDUCTION_PROVIDER = registerInductionProvider(ExtraBlockTypes.SUPREME_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityExtraInductionProvider, BlockTypeTile<TileEntityExtraInductionProvider>>, ItemBlockExtraInductionProvider> COSMIC_INDUCTION_PROVIDER = registerInductionProvider(ExtraBlockTypes.COSMIC_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityExtraInductionProvider, BlockTypeTile<TileEntityExtraInductionProvider>>, ItemBlockExtraInductionProvider> INFINITE_INDUCTION_PROVIDER = registerInductionProvider(ExtraBlockTypes.INFINITE_INDUCTION_PROVIDER);

    public static final BlockRegistryObject<BlockExtraEnergyCube, ItemBlockExtraEnergyCube> ABSOLUTE_ENERGY_CUBE = registerEnergyCube(ExtraBlockTypes.ABSOLUTE_ENERGY_CUBE);
    public static final BlockRegistryObject<BlockExtraEnergyCube, ItemBlockExtraEnergyCube> SUPREME_ENERGY_CUBE = registerEnergyCube(ExtraBlockTypes.SUPREME_ENERGY_CUBE);
    public static final BlockRegistryObject<BlockExtraEnergyCube, ItemBlockExtraEnergyCube> COSMIC_ENERGY_CUBE = registerEnergyCube(ExtraBlockTypes.COSMIC_ENERGY_CUBE);
    public static final BlockRegistryObject<BlockExtraEnergyCube, ItemBlockExtraEnergyCube> INFINITE_ENERGY_CUBE = registerEnergyCube(ExtraBlockTypes.INFINITE_ENERGY_CUBE);

    public static final BlockRegistryObject<BlockExtraFluidTank, ItemBlockExtraFluidTank> ABSOLUTE_FLUID_TANK = registerFluidTank(ExtraBlockTypes.ABSOLUTE_FLUID_TANK);
    public static final BlockRegistryObject<BlockExtraFluidTank, ItemBlockExtraFluidTank> SUPREME_FLUID_TANK = registerFluidTank(ExtraBlockTypes.SUPREME_FLUID_TANK);
    public static final BlockRegistryObject<BlockExtraFluidTank, ItemBlockExtraFluidTank> COSMIC_FLUID_TANK = registerFluidTank(ExtraBlockTypes.COSMIC_FLUID_TANK);
    public static final BlockRegistryObject<BlockExtraFluidTank, ItemBlockExtraFluidTank> INFINITE_FLUID_TANK = registerFluidTank(ExtraBlockTypes.INFINITE_FLUID_TANK);

    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraUniversalCable>, ItemBlockExtraUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = registerUniversalCable("absolute", ExtraBlockTypes.ABSOLUTE_UNIVERSAL_CABLE);
    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraUniversalCable>, ItemBlockExtraUniversalCable> SUPREME_UNIVERSAL_CABLE = registerUniversalCable("supreme", ExtraBlockTypes.SUPREME_UNIVERSAL_CABLE);
    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraUniversalCable>, ItemBlockExtraUniversalCable> COSMIC_UNIVERSAL_CABLE = registerUniversalCable("cosmic", ExtraBlockTypes.COSMIC_UNIVERSAL_CABLE);
    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraUniversalCable>, ItemBlockExtraUniversalCable> INFINITE_UNIVERSAL_CABLE = registerUniversalCable("infinite", ExtraBlockTypes.INFINITE_UNIVERSAL_CABLE);

    public static final BlockRegistryObject<BlockLargeTransmitter<TileEntityExtraMechanicalPipe>, ItemBlockExtraMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = registerMechanicalPipe("absolute", ExtraBlockTypes.ABSOLUTE_MECHANICAL_PIPE);
    public static final BlockRegistryObject<BlockLargeTransmitter<TileEntityExtraMechanicalPipe>, ItemBlockExtraMechanicalPipe> SUPREME_MECHANICAL_PIPE = registerMechanicalPipe("supreme", ExtraBlockTypes.SUPREME_MECHANICAL_PIPE);
    public static final BlockRegistryObject<BlockLargeTransmitter<TileEntityExtraMechanicalPipe>, ItemBlockExtraMechanicalPipe> COSMIC_MECHANICAL_PIPE = registerMechanicalPipe("cosmic", ExtraBlockTypes.COSMIC_MECHANICAL_PIPE);
    public static final BlockRegistryObject<BlockLargeTransmitter<TileEntityExtraMechanicalPipe>, ItemBlockExtraMechanicalPipe> INFINITE_MECHANICAL_PIPE = registerMechanicalPipe("infinite", ExtraBlockTypes.INFINITE_MECHANICAL_PIPE);

    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraPressurizedTube>, ItemBlockExtraPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = registerPressurizedTube("absolute", ExtraBlockTypes.ABSOLUTE_PRESSURIZED_TUBE);
    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraPressurizedTube>, ItemBlockExtraPressurizedTube> SUPREME_PRESSURIZED_TUBE = registerPressurizedTube("supreme", ExtraBlockTypes.SUPREME_PRESSURIZED_TUBE);
    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraPressurizedTube>, ItemBlockExtraPressurizedTube> COSMIC_PRESSURIZED_TUBE = registerPressurizedTube("cosmic", ExtraBlockTypes.COSMIC_PRESSURIZED_TUBE);
    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraPressurizedTube>, ItemBlockExtraPressurizedTube> INFINITE_PRESSURIZED_TUBE = registerPressurizedTube("infinite", ExtraBlockTypes.INFINITE_PRESSURIZED_TUBE);

    public static final BlockRegistryObject<BlockLargeTransmitter<TileEntityExtraLogisticalTransporter>, ItemBlockExtraLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("absolute", ExtraBlockTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER);
    public static final BlockRegistryObject<BlockLargeTransmitter<TileEntityExtraLogisticalTransporter>, ItemBlockExtraLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("supreme", ExtraBlockTypes.SUPREME_LOGISTICAL_TRANSPORTER);
    public static final BlockRegistryObject<BlockLargeTransmitter<TileEntityExtraLogisticalTransporter>, ItemBlockExtraLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("cosmic", ExtraBlockTypes.COSMIC_LOGISTICAL_TRANSPORTER);
    public static final BlockRegistryObject<BlockLargeTransmitter<TileEntityExtraLogisticalTransporter>, ItemBlockExtraLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("infinite", ExtraBlockTypes.INFINITE_LOGISTICAL_TRANSPORTER);

    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraThermodynamicConductor>, ItemBlockExtraThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("absolute", ExtraBlockTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR);
    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraThermodynamicConductor>, ItemBlockExtraThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("supreme", ExtraBlockTypes.SUPREME_THERMODYNAMIC_CONDUCTOR);
    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraThermodynamicConductor>, ItemBlockExtraThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("cosmic", ExtraBlockTypes.COSMIC_THERMODYNAMIC_CONDUCTOR);
    public static final BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraThermodynamicConductor>, ItemBlockExtraThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("infinite", ExtraBlockTypes.INFINITE_THERMODYNAMIC_CONDUCTOR);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityExtraChemicalTank, Machine<TileEntityExtraChemicalTank>>, ItemBlockExtraChemicalTank> ABSOLUTE_CHEMICAL_TANK = registerChemicalTank(ExtraBlockTypes.ABSOLUTE_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityExtraChemicalTank, Machine<TileEntityExtraChemicalTank>>, ItemBlockExtraChemicalTank> SUPREME_CHEMICAL_TANK = registerChemicalTank(ExtraBlockTypes.SUPREME_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityExtraChemicalTank, Machine<TileEntityExtraChemicalTank>>, ItemBlockExtraChemicalTank> COSMIC_CHEMICAL_TANK = registerChemicalTank(ExtraBlockTypes.COSMIC_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityExtraChemicalTank, Machine<TileEntityExtraChemicalTank>>, ItemBlockExtraChemicalTank> INFINITE_CHEMICAL_TANK = registerChemicalTank(ExtraBlockTypes.INFINITE_CHEMICAL_TANK);

    public static final BlockRegistryObject<BlockLargeCapRadioactiveWasteBarrel, ItemBlockLargeCapRadioactiveWasteBarrel> ABSOLUTE_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlockTypes.ABSOLUTE_RADIOACTIVE_WASTE_BARREL);
    public static final BlockRegistryObject<BlockLargeCapRadioactiveWasteBarrel, ItemBlockLargeCapRadioactiveWasteBarrel> SUPREME_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlockTypes.SUPREME_RADIOACTIVE_WASTE_BARREL);
    public static final BlockRegistryObject<BlockLargeCapRadioactiveWasteBarrel, ItemBlockLargeCapRadioactiveWasteBarrel> COSMIC_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlockTypes.COSMIC_RADIOACTIVE_WASTE_BARREL);
    public static final BlockRegistryObject<BlockLargeCapRadioactiveWasteBarrel, ItemBlockLargeCapRadioactiveWasteBarrel> INFINITE_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlockTypes.INFINITE_RADIOACTIVE_WASTE_BARREL);

    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityAdvancedElectricPump, Machine<TileEntityAdvancedElectricPump>>, ItemBlockTooltip<BlockTile.BlockTileModel<TileEntityAdvancedElectricPump, Machine<TileEntityAdvancedElectricPump>>>> ADVANCED_ELECTRIC_PUMP = EXTRA_BLOCKS.register("advanced_electric_pump", () -> new BlockTile.BlockTileModel<>(ExtraBlockTypes.ADVANCED_ELECTRIC_PUMP, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor())), ItemBlockTooltip::new)
            .forItemHolder(holder -> holder
                    .addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                            .addBasic(TileEntityAdvancedElectricPump.MAX_FLUID)
                            .build())
                    .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                            .addFluidDrainSlot(0)
                            .addOutput()
                            .addEnergy()
                            .build()));

    private static BlockRegistryObject<BlockExtraResource, ItemBlockMekanism<BlockExtraResource>> registerResourceBlock(BlockExtraResourceInfo resource) {
        return EXTRA_BLOCKS.register("block_" + resource.getRegistrySuffix(), () -> new BlockExtraResource(resource), (block, properties) -> {
            if (!block.getResourceInfo().burnsInFire()) {
                properties = properties.fireResistant();
            }
            return new ItemBlockMekanism<>(block, properties);
        });
    }

    public static OreBlockType registerOre(OreType ore) {
        String name = ore.getResource().getRegistrySuffix() + "_ore";
        BlockRegistryObject<BlockOre, ItemBlockTooltip<BlockOre>> stoneOre = registerBlock(name, () -> new BlockOre(ore));
        BlockRegistryObject<BlockOre, ItemBlockTooltip<BlockOre>> endOre = EXTRA_BLOCKS.register("end_" + name,
                () -> new BlockOre(ore, BlockBehaviour.Properties.ofLegacyCopy(stoneOre.value()).mapColor(MapColor.SAND)
                        .strength(3.0F, 9.0F).sound(SoundType.STONE)),
                ItemBlockTooltip::new);
        return new OreBlockType(stoneOre, endOre);
    }

    private static BlockRegistryObject<BlockExtraBin, ItemBlockExtraBin> registerBin(BlockTypeTile<TileEntityExtraBin> type) {
        BTier tier = (BTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_bin", color -> new BlockExtraBin(type, properties -> properties.mapColor(color)), ItemBlockExtraBin::new)
                .forItemHolder(holder -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addSlot(ExtraComponentBackedBinInventorySlot::create)
                        .build()));
    }

    private static BlockRegistryObject<BlockTile<TileEntityExtraInductionCell, BlockTypeTile<TileEntityExtraInductionCell>>, ItemBlockExtraInductionCell> registerInductionCell(BlockTypeTile<TileEntityExtraInductionCell> type) {
        ICTier tier = (ICTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_induction_cell", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), ItemBlockExtraInductionCell::new);
    }

    private static BlockRegistryObject<BlockTile<TileEntityExtraInductionProvider, BlockTypeTile<TileEntityExtraInductionProvider>>, ItemBlockExtraInductionProvider> registerInductionProvider(BlockTypeTile<TileEntityExtraInductionProvider> type) {
        IPTier tier = (IPTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_induction_provider", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), ItemBlockExtraInductionProvider::new);
    }

    private static BlockRegistryObject<BlockExtraEnergyCube, ItemBlockExtraEnergyCube> registerEnergyCube(Machine<TileEntityExtraEnergyCube> type) {
        ECTier tier = (ECTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_energy_cube", () -> new BlockExtraEnergyCube(type), ItemBlockExtraEnergyCube::new)
                .forItemHolder(holder -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addEnergy()
                        .addDrainEnergy()
                        .build()));
    }

    private static BlockRegistryObject<BlockExtraFluidTank, ItemBlockExtraFluidTank> registerFluidTank(Machine<TileEntityExtraFluidTank> type) {
        FTTier tier = (FTTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_fluid_tank", () -> new BlockExtraFluidTank(type), ItemBlockExtraFluidTank::new)
                .forItemHolder(holder -> holder
                        .addAttachedContainerCapabilities(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                                .addTank(ExtraComponentBackedFluidTankFluidTank::create)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addFluidInputSlot(0)
                                .addOutput()
                                .build()));
    }

    private static BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraUniversalCable>, ItemBlockExtraUniversalCable> registerUniversalCable(
                                                                                                                                                  String nameTier, BlockTypeTile<TileEntityExtraUniversalCable> type) {
        return registerTieredBlock(nameTier + "_universal_cable", () -> new BlockSmallTransmitter<>(type), ItemBlockExtraUniversalCable::new);
    }

    private static BlockRegistryObject<BlockLargeTransmitter<TileEntityExtraMechanicalPipe>, ItemBlockExtraMechanicalPipe> registerMechanicalPipe(
                                                                                                                                                  String nameTier, BlockTypeTile<TileEntityExtraMechanicalPipe> type) {
        return registerTieredBlock(nameTier + "_mechanical_pipe", () -> new BlockLargeTransmitter<>(type), ItemBlockExtraMechanicalPipe::new);
    }

    private static BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraPressurizedTube>, ItemBlockExtraPressurizedTube> registerPressurizedTube(
                                                                                                                                                     String nameTier, BlockTypeTile<TileEntityExtraPressurizedTube> type) {
        return registerTieredBlock(nameTier + "_pressurized_tube", () -> new BlockSmallTransmitter<>(type), ItemBlockExtraPressurizedTube::new);
    }

    private static BlockRegistryObject<BlockLargeTransmitter<TileEntityExtraLogisticalTransporter>, ItemBlockExtraLogisticalTransporter> registerLogisticalTransporter(
                                                                                                                                                                       String nameTier, BlockTypeTile<TileEntityExtraLogisticalTransporter> type) {
        return registerTieredBlock(nameTier + "_logistical_transporter", () -> new BlockLargeTransmitter<>(type), ItemBlockExtraLogisticalTransporter::new);
    }

    private static BlockRegistryObject<BlockSmallTransmitter<TileEntityExtraThermodynamicConductor>, ItemBlockExtraThermodynamicConductor> registerThermodynamicConductor(
                                                                                                                                                                          String nameTier, BlockTypeTile<TileEntityExtraThermodynamicConductor> type) {
        return registerTieredBlock(nameTier + "_thermodynamic_conductor", () -> new BlockSmallTransmitter<>(type), ItemBlockExtraThermodynamicConductor::new);
    }

    private static BlockRegistryObject<BlockTile.BlockTileModel<TileEntityExtraChemicalTank, Machine<TileEntityExtraChemicalTank>>, ItemBlockExtraChemicalTank> registerChemicalTank(
                                                                                                                                                                                     Machine<TileEntityExtraChemicalTank> type) {
        CTTier tier = (CTTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_chemical_tank", color -> new BlockTile.BlockTileModel<>(type, properties -> properties.mapColor(color)), ItemBlockExtraChemicalTank::new)
                .forItemHolder(holder -> holder
                        .addAttachedContainerCapabilities(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                                .addTank(ExtraComponentBackedChemicalTankTank::create).build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addChemicalDrainSlot(0)
                                .addChemicalFillSlot(0)
                                .build()));
    }

    private static BlockRegistryObject<BlockLargeCapRadioactiveWasteBarrel, ItemBlockLargeCapRadioactiveWasteBarrel> registryWasteBarrel(
                                                                                                                                         BlockTypeTile<TileEntityLargeCapRadioactiveWasteBarrel> type) {
        RWBTier tier = (RWBTier) Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(tier, "_radioactive_waste_barrel", () -> new BlockLargeCapRadioactiveWasteBarrel(type), ItemBlockLargeCapRadioactiveWasteBarrel::new);
    }

    private static <TILE extends TileEntityExtraFactory<?>> BlockRegistryObject<BlockExtraFactory<?>, ItemBlockExtraFactory> registerFactory(ExtraFactory<TILE> type) {
        ExtraFactoryTier tier = (ExtraFactoryTier) type.get(ExtraAttributeTier.class).tier();
        BlockRegistryObject<BlockExtraFactory<?>, ItemBlockExtraFactory> factory = registerTieredBlock(tier, "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockExtraFactory<>(type), ItemBlockExtraFactory::new);
        factory.forItemHolder(holder -> {
            int processes = tier.processes;
            Predicate<ItemStack> recipeInputPredicate = switch (type.getFactoryType()) {
                case SMELTING -> s -> MekanismRecipeType.SMELTING.getInputCache().containsInput(null, s);
                case ENRICHING -> s -> MekanismRecipeType.ENRICHING.getInputCache().containsInput(null, s);
                case CRUSHING -> s -> MekanismRecipeType.CRUSHING.getInputCache().containsInput(null, s);
                case COMPRESSING -> s -> MekanismRecipeType.COMPRESSING.getInputCache().containsInputA(null, s);
                case COMBINING -> s -> MekanismRecipeType.COMBINING.getInputCache().containsInputA(null, s);
                case PURIFYING -> s -> MekanismRecipeType.PURIFYING.getInputCache().containsInputA(null, s);
                case INJECTING -> s -> MekanismRecipeType.INJECTING.getInputCache().containsInputA(null, s);
                case INFUSING -> s -> MekanismRecipeType.METALLURGIC_INFUSING.getInputCache().containsInputA(null, s);
                case SAWING -> s -> MekanismRecipeType.SAWING.getInputCache().containsInput(null, s);
            };
            switch (type.getFactoryType()) {
                case SMELTING, ENRICHING, CRUSHING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addEnergy()
                        .build());
                case COMPRESSING, INJECTING, PURIFYING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                                .addBasic(TileEntityAdvancedElectricMachine.MAX_GAS * processes * processes, switch (type.getFactoryType()) {
                                    case COMPRESSING -> MekanismRecipeType.COMPRESSING;
                                    case INJECTING -> MekanismRecipeType.INJECTING;
                                    case PURIFYING -> MekanismRecipeType.PURIFYING;
                                    default -> throw new IllegalStateException("Factory type doesn't have a known gas recipe");
                                }, InputRecipeCache.ItemChemical::containsInputB)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeInputPredicate)
                                .addChemicalFillOrConvertSlot(0)
                                .addEnergy()
                                .build());
                case COMBINING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate)
                        .addInput(MekanismRecipeType.COMBINING, InputRecipeCache.DoubleItem::containsInputB)
                        .addEnergy()
                        .build());
                case INFUSING -> holder
                        .addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                                .addBasic(TileEntityMetallurgicInfuser.MAX_INFUSE * processes * processes, MekanismRecipeType.METALLURGIC_INFUSING, InputRecipeCache.ItemChemical::containsInputB)
                                .build())
                        .addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                                .addBasicFactorySlots(processes, recipeInputPredicate)
                                .addInfusionFillOrConvertSlot(0)
                                .addEnergy()
                                .build());
                case SAWING -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                        .addBasicFactorySlots(processes, recipeInputPredicate, true)
                        .addEnergy()
                        .build());
            }

        });
        return factory;
    }

    /**
     * Retrieves a Factory with a defined tier and recipe type.
     *
     * @param tier - tier to add to the Factory
     * @param type - recipe type to add to the Factory
     * @return factory with defined tier and recipe type
     */
    public static BlockRegistryObject<BlockExtraFactory<?>, ItemBlockExtraFactory> getExtraFactory(@NotNull ExtraFactoryTier tier, @NotNull FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockExtraFactory<?>, ItemBlockExtraFactory>[] getExtraFactoryBlocks() {
        return FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        EXTRA_BLOCKS.register(eventBus);
    }
}
