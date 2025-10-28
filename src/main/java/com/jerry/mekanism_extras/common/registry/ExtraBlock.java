package com.jerry.mekanism_extras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;
import com.jerry.mekanism_extras.common.block.ExtraBlockOre;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekanism_extras.common.block.basic.ExtraBlockResource;
import com.jerry.mekanism_extras.common.block.prefab.BlockAdvancedFactoryMachine;
import com.jerry.mekanism_extras.common.content.blocktype.AdvancedFactory;
import com.jerry.mekanism_extras.common.integration.Addons;
import com.jerry.mekanism_extras.common.item.block.machine.ItemBlockAdvancedFactory;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekanism_extras.common.tile.machine.TileEntityAdvancedElectricPump;
import com.jerry.mekanism_extras.common.block.basic.ExtraBlockBin;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockBin;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityBin;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockChemicalTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.block.ExtraBlockEnergyCube;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockEnergyCube;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.block.basic.ExtraBlockFluidTank;
import com.jerry.mekanism_extras.common.item.block.machine.ExtraItemBlockFluidTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.block.ExtraBlockRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.common.block.transmitter.ExtraBlockUniversalCable;
import com.jerry.mekanism_extras.common.item.block.transmitter.ExtraItemBlockUniversalCable;
import com.jerry.mekanism_extras.common.block.transmitter.ExtraBlockLogisticalTransporter;
import com.jerry.mekanism_extras.common.item.block.transmitter.ExtraItemBlockLogisticalTransporter;
import com.jerry.mekanism_extras.common.block.transmitter.ExtraBlockMechanicalPipe;
import com.jerry.mekanism_extras.common.item.block.transmitter.ExtraItemBlockMechanicalPipe;
import com.jerry.mekanism_extras.common.block.transmitter.ExtraBlockThermodynamicConductor;
import com.jerry.mekanism_extras.common.item.block.transmitter.ExtraItemBlockThermodynamicConductor;
import com.jerry.mekanism_extras.common.block.transmitter.ExtraBlockPressurizedTube;
import com.jerry.mekanism_extras.common.item.block.transmitter.ExtraItemBlockPressurizedTube;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockResource;
import com.jerry.mekanism_extras.common.resource.ExtraBlockResourceInfo;
import com.jerry.mekanism_extras.common.resource.ore.ExtraOreBlockType;
import com.jerry.mekanism_extras.common.resource.ore.ExtraOreType;
import com.jerry.mekanism_extras.common.tile.multiblock.*;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockInductionCell;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockInductionProvider;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.api.tier.ITier;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasDescription;
import mekanism.common.block.prefab.BlockBase;
import mekanism.common.block.prefab.BlockBasicMultiblock;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.item.block.machine.ItemBlockMachine;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.tier.*;
import mekanism.common.util.EnumUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ExtraBlock {
    public static final BlockDeferredRegister EXTRA_BLOCK = new BlockDeferredRegister(MekanismExtras.MODID);

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(String tierName, String suffix, Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return EXTRA_BLOCK.register(tierName + suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(BlockType type, String registerName, Function<MapColor, ? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        ITier tier = Objects.requireNonNull(type.get(AttributeTier.class)).tier();
        return EXTRA_BLOCK.register(registerName, () -> blockSupplier.apply(tier.getBaseTier().getMapColor()), itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock_1(BlockType type, String registerName, Function<MapColor, ? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        IAdvancedTier tier = Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return EXTRA_BLOCK.register(registerName, () -> blockSupplier.apply(tier.getAdvanceTier().getMapColor()), itemCreator);
    }

//    public static final BlockRegistryObject<Block, BlockItem> NAQUADAH_ORE = EXTRA_BLOCK.register("naquadah_ore",
//            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));
//    public static final BlockRegistryObject<Block, BlockItem> END_NAQUADAH_ORE = EXTRA_BLOCK.register("end_naquadah_ore",
//            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE)));

    public static final Map<ExtraOreType, ExtraOreBlockType> ORES = new LinkedHashMap<>();

    private static final Table<AdvancedFactoryTier, FactoryType, BlockRegistryObject<BlockAdvancedFactoryMachine.BlockAdvancedFactory<?>, ItemBlockAdvancedFactory>> FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                if (Addons.EVOLVEDMEKANISM.isLoaded()) {
                    if (type != EMFactoryType.ALLOYING) {
                        FACTORIES.put(tier, type, registerFactory(ExtraBlockType.getAdvancedFactory(tier, type)));
                    }
                } else {
                    FACTORIES.put(tier, type, registerFactory(ExtraBlockType.getAdvancedFactory(tier, type)));
                }
            }
        }
        // ores
        for (ExtraOreType ore : ExtraEnumUtils.EXTRA_ORE_TYPES) {
            ORES.put(ore, registerOre(ore));
        }
    }

    // Resource Blocks
    public static final BlockRegistryObject<ExtraBlockResource, ExtraItemBlockResource> NAQUADAH_BLOCK = registerResourceBlock(ExtraBlockResourceInfo.NAQUADAH);
    public static final BlockRegistryObject<ExtraBlockResource, ExtraItemBlockResource> RAW_NAQUADAH_BLOCK = registerResourceBlock(ExtraBlockResourceInfo.RAW_NAQUADAH);
    public static final BlockRegistryObject<ExtraBlockResource, ExtraItemBlockResource> TUNGSTEN_BLOCK = registerResourceBlock(ExtraBlockResourceInfo.TUNGSTEN);
    public static final BlockRegistryObject<ExtraBlockResource, ExtraItemBlockResource> REFINED_NETHERITE = registerResourceBlock(ExtraBlockResourceInfo.REFINED_NETHERITE);
    // Bins
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> ABSOLUTE_BIN = registerBin("absolute", ExtraBlockType.ABSOLUTE_BIN);
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> SUPREME_BIN = registerBin("supreme", ExtraBlockType.SUPREME_BIN);
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> COSMIC_BIN = registerBin("cosmic", ExtraBlockType.COSMIC_BIN);
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> INFINITE_BIN = registerBin("infinite", ExtraBlockType.INFINITE_BIN);
    // Induction Casing Ports
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityReinforcedInductionCasing>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityReinforcedInductionCasing>>> REINFORCED_INDUCTION_CASING = registerBlock("reinforced_induction_casing", () -> new BlockBasicMultiblock<>(ExtraBlockType.REINFORCED_INDUCTION_CASING, properties -> properties.mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityReinforcedInductionPort>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityReinforcedInductionPort>>> REINFORCED_INDUCTION_PORT = registerBlock("reinforced_induction_port", () -> new BlockBasicMultiblock<>(ExtraBlockType.REINFORCED_INDUCTION_PORT, properties -> properties.mapColor(MapColor.COLOR_LIGHT_GRAY)));
    // Cells
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>, ExtraItemBlockInductionCell> ABSOLUTE_INDUCTION_CELL = registerInductionCell("absolute", ExtraBlockType.ABSOLUTE_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>, ExtraItemBlockInductionCell> SUPREME_INDUCTION_CELL = registerInductionCell("supreme", ExtraBlockType.SUPREME_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>, ExtraItemBlockInductionCell> COSMIC_INDUCTION_CELL = registerInductionCell("cosmic", ExtraBlockType.COSMIC_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>, ExtraItemBlockInductionCell> INFINITE_INDUCTION_CELL = registerInductionCell("infinite", ExtraBlockType.INFINITE_INDUCTION_CELL);
    // Provider
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>, ExtraItemBlockInductionProvider> ABSOLUTE_INDUCTION_PROVIDER = registerInductionProvider("absolute", ExtraBlockType.ABSOLUTE_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>, ExtraItemBlockInductionProvider> SUPREME_INDUCTION_PROVIDER = registerInductionProvider("supreme", ExtraBlockType.SUPREME_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>, ExtraItemBlockInductionProvider> COSMIC_INDUCTION_PROVIDER = registerInductionProvider("cosmic", ExtraBlockType.COSMIC_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>, ExtraItemBlockInductionProvider> INFINITE_INDUCTION_PROVIDER = registerInductionProvider("infinite", ExtraBlockType.INFINITE_INDUCTION_PROVIDER);
    // Fluid Tanks
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> ABSOLUTE_FLUID_TANK = registerFluidTank("absolute", ExtraBlockType.ABSOLUTE_FLUID_TANK);
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> SUPREME_FLUID_TANK = registerFluidTank("supreme", ExtraBlockType.SUPREME_FLUID_TANK);
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> COSMIC_FLUID_TANK = registerFluidTank("cosmic", ExtraBlockType.COSMIC_FLUID_TANK);
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> INFINITE_FLUID_TANK = registerFluidTank("infinite", ExtraBlockType.INFINITE_FLUID_TANK);
    // Energy Cubes
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> ABSOLUTE_ENERGY_CUBE = registerEnergyCube("absolute", ExtraBlockType.ABSOLUTE_ENERGY_CUBE);
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> SUPREME_ENERGY_CUBE = registerEnergyCube("supreme", ExtraBlockType.SUPREME_ENERGY_CUBE);
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> COSMIC_ENERGY_CUBE = registerEnergyCube("cosmic", ExtraBlockType.COSMIC_ENERGY_CUBE);
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> INFINITE_ENERGY_CUBE = registerEnergyCube("infinite", ExtraBlockType.INFINITE_ENERGY_CUBE);
    // Universal Cables
    public static final BlockRegistryObject<ExtraBlockUniversalCable, ExtraItemBlockUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = registerUniversalCable("absolute", CableTier.BASIC);
    public static final BlockRegistryObject<ExtraBlockUniversalCable, ExtraItemBlockUniversalCable> SUPREME_UNIVERSAL_CABLE = registerUniversalCable("supreme", CableTier.ADVANCED);
    public static final BlockRegistryObject<ExtraBlockUniversalCable, ExtraItemBlockUniversalCable> COSMIC_UNIVERSAL_CABLE = registerUniversalCable("cosmic", CableTier.ELITE);
    public static final BlockRegistryObject<ExtraBlockUniversalCable, ExtraItemBlockUniversalCable> INFINITE_UNIVERSAL_CABLE = registerUniversalCable("infinite", CableTier.ULTIMATE);
    // Mechanical Pipes
    public static final BlockRegistryObject<ExtraBlockMechanicalPipe, ExtraItemBlockMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = registerMechanicalPipe("absolute", PipeTier.BASIC);
    public static final BlockRegistryObject<ExtraBlockMechanicalPipe, ExtraItemBlockMechanicalPipe> SUPREME_MECHANICAL_PIPE = registerMechanicalPipe("supreme", PipeTier.ADVANCED);
    public static final BlockRegistryObject<ExtraBlockMechanicalPipe, ExtraItemBlockMechanicalPipe> COSMIC_MECHANICAL_PIPE = registerMechanicalPipe("cosmic", PipeTier.ELITE);
    public static final BlockRegistryObject<ExtraBlockMechanicalPipe, ExtraItemBlockMechanicalPipe> INFINITE_MECHANICAL_PIPE = registerMechanicalPipe("infinite", PipeTier.ULTIMATE);
    // Pressurized Tubes
    public static final BlockRegistryObject<ExtraBlockPressurizedTube, ExtraItemBlockPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = registerPressurizedTube("absolute", TubeTier.BASIC);
    public static final BlockRegistryObject<ExtraBlockPressurizedTube, ExtraItemBlockPressurizedTube> SUPREME_PRESSURIZED_TUBE = registerPressurizedTube("supreme", TubeTier.ADVANCED);
    public static final BlockRegistryObject<ExtraBlockPressurizedTube, ExtraItemBlockPressurizedTube> COSMIC_PRESSURIZED_TUBE = registerPressurizedTube("cosmic", TubeTier.ELITE);
    public static final BlockRegistryObject<ExtraBlockPressurizedTube, ExtraItemBlockPressurizedTube> INFINITE_PRESSURIZED_TUBE = registerPressurizedTube("infinite", TubeTier.ULTIMATE);
    // Logistical Transporters
    public static final BlockRegistryObject<ExtraBlockLogisticalTransporter, ExtraItemBlockLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("absolute", TransporterTier.BASIC);
    public static final BlockRegistryObject<ExtraBlockLogisticalTransporter, ExtraItemBlockLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("supreme", TransporterTier.ADVANCED);
    public static final BlockRegistryObject<ExtraBlockLogisticalTransporter, ExtraItemBlockLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("cosmic", TransporterTier.ELITE);
    public static final BlockRegistryObject<ExtraBlockLogisticalTransporter, ExtraItemBlockLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("infinite", TransporterTier.ULTIMATE);
    // Thermodynamic Conductors
    public static final BlockRegistryObject<ExtraBlockThermodynamicConductor, ExtraItemBlockThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("absolute", ConductorTier.BASIC);
    public static final BlockRegistryObject<ExtraBlockThermodynamicConductor, ExtraItemBlockThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("supreme", ConductorTier.ADVANCED);
    public static final BlockRegistryObject<ExtraBlockThermodynamicConductor, ExtraItemBlockThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("cosmic", ConductorTier.ELITE);
    public static final BlockRegistryObject<ExtraBlockThermodynamicConductor, ExtraItemBlockThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("infinite", ConductorTier.ULTIMATE);
    // Chemical Tanks
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> ABSOLUTE_CHEMICAL_TANK = registerChemicalTank("absolute", ExtraBlockType.ABSOLUTE_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> SUPREME_CHEMICAL_TANK = registerChemicalTank("supreme", ExtraBlockType.SUPREME_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> COSMIC_CHEMICAL_TANK = registerChemicalTank("cosmic", ExtraBlockType.COSMIC_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> INFINITE_CHEMICAL_TANK = registerChemicalTank("infinite", ExtraBlockType.INFINITE_CHEMICAL_TANK);
    // Others
    public static final BlockRegistryObject<ExtraBlockRadioactiveWasteBarrel, ExtraItemBlockRadioactiveWasteBarrel> EXPAND_RADIOACTIVE_WASTE_BARREL = EXTRA_BLOCK.registerDefaultProperties("expand_radioactive_waste_barrel", ExtraBlockRadioactiveWasteBarrel::new, ExtraItemBlockRadioactiveWasteBarrel::new);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityAdvancedElectricPump, Machine<TileEntityAdvancedElectricPump>>, ItemBlockMachine> ADVANCED_ELECTRIC_PUMP = EXTRA_BLOCK.register("advance_electric_pump", () -> new BlockTile.BlockTileModel<>(ExtraBlockType.ADVANCED_ELECTRIC_PUMP, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor())), ItemBlockMachine::new);
    public static final BlockRegistryObject<BlockBase<BlockType>, ItemBlockTooltip<BlockBase<BlockType>>> TUNGSTEN_CASING = registerBlock("tungsten_casing", () -> new BlockBase<>(ExtraBlockType.TUNGSTEN_CASING, properties -> properties.strength(6, 15).mapColor(ExtraBlockResourceInfo.TUNGSTEN.getMapColor())));

    private static ExtraOreBlockType registerOre(ExtraOreType ore) {
        String name = ore.getResource().getRegistrySuffix() + "_ore";
        BlockRegistryObject<ExtraBlockOre, ItemBlockTooltip<ExtraBlockOre>> stoneOre = registerBlock(name, () -> new ExtraBlockOre(ore));
        return new ExtraOreBlockType(stoneOre);
    }

    private static BlockRegistryObject<ExtraBlockResource, ExtraItemBlockResource> registerResourceBlock(ExtraBlockResourceInfo resource) {
        return EXTRA_BLOCK.registerDefaultProperties("block_" + resource.getRegistrySuffix(), () -> new ExtraBlockResource(resource), (block, properties) -> {
            if (!block.getResourceInfo().burnsInFire()) {
                properties = properties.fireResistant();
            }
            return new ExtraItemBlockResource(block, properties);
        });
    }

    private static BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> registerBin(String tileName, BlockTypeTile<ExtraTileEntityBin> type) {
        return registerTieredBlock_1(type, tileName + "_bin", color -> new ExtraBlockBin(type, properties -> properties.mapColor(color)), ExtraItemBlockBin::new);
    }

    private static BlockRegistryObject<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>, ExtraItemBlockInductionCell> registerInductionCell(String tileName, BlockTypeTile<ExtraTileEntityInductionCell> type) {
        return registerTieredBlock(type, tileName + "_induction_cell", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), ExtraItemBlockInductionCell::new);
    }

    private static BlockRegistryObject<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>, ExtraItemBlockInductionProvider> registerInductionProvider(String tileName, BlockTypeTile<ExtraTileEntityInductionProvider> type) {
        return registerTieredBlock(type, tileName + "_induction_provider", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), ExtraItemBlockInductionProvider::new);
    }

    private static BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> registerFluidTank(String tileName, Machine<ExtraTileEntityFluidTank> type) {
        return registerTieredBlock(tileName, "_fluid_tank", () -> new ExtraBlockFluidTank(type), ExtraItemBlockFluidTank::new);
    }

    private static BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> registerEnergyCube(String tileName, Machine<ExtraTileEntityEnergyCube> type) {
        return registerTieredBlock(tileName, "_energy_cube", () -> new ExtraBlockEnergyCube(type), ExtraItemBlockEnergyCube::new);
    }

    private static BlockRegistryObject<ExtraBlockUniversalCable, ExtraItemBlockUniversalCable> registerUniversalCable(String tileName, CableTier tier) {
        return registerTieredBlock(tileName, "_universal_cable", () -> new ExtraBlockUniversalCable(tier), ExtraItemBlockUniversalCable::new);
    }

    private static BlockRegistryObject<ExtraBlockMechanicalPipe, ExtraItemBlockMechanicalPipe> registerMechanicalPipe(String tileName, PipeTier tier) {
        return registerTieredBlock(tileName, "_mechanical_pipe", () -> new ExtraBlockMechanicalPipe(tier), ExtraItemBlockMechanicalPipe::new);
    }

    private static BlockRegistryObject<ExtraBlockPressurizedTube, ExtraItemBlockPressurizedTube> registerPressurizedTube(String tileName, TubeTier tier) {
        return registerTieredBlock(tileName, "_pressurized_tube", () -> new ExtraBlockPressurizedTube(tier), ExtraItemBlockPressurizedTube::new);
    }

    private static BlockRegistryObject<ExtraBlockLogisticalTransporter, ExtraItemBlockLogisticalTransporter> registerLogisticalTransporter(String tileName, TransporterTier tier) {
        return registerTieredBlock(tileName, "_logistical_transporter", () -> new ExtraBlockLogisticalTransporter(tier), ExtraItemBlockLogisticalTransporter::new);
    }

    private static BlockRegistryObject<ExtraBlockThermodynamicConductor, ExtraItemBlockThermodynamicConductor> registerThermodynamicConductor(String tileName, ConductorTier tier) {
        return registerTieredBlock(tileName, "_thermodynamic_conductor", () -> new ExtraBlockThermodynamicConductor(tier), ExtraItemBlockThermodynamicConductor::new);
    }

    private static BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> registerChemicalTank(String tileName, Machine<ExtraTileEntityChemicalTank> type) {
        return registerTieredBlock_1(type, tileName + "_chemical_tank", color -> new BlockTile.BlockTileModel<>(type, properties -> properties.mapColor(color)), ExtraItemBlockChemicalTank::new);
    }

    private static <BLOCK extends Block & IHasDescription> BlockRegistryObject<BLOCK, ItemBlockTooltip<BLOCK>> registerBlock(String name, Supplier<? extends BLOCK> blockSupplier) {
        return EXTRA_BLOCK.registerDefaultProperties(name, blockSupplier, ItemBlockTooltip::new);
    }

    private static <TILE extends TileEntityExtraFactory<?>> BlockRegistryObject<BlockAdvancedFactoryMachine.BlockAdvancedFactory<?>, ItemBlockAdvancedFactory> registerFactory(AdvancedFactory<TILE> type) {
        IAdvancedTier tier = Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier();
        return registerTieredBlock(type, "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockAdvancedFactoryMachine.BlockAdvancedFactory<>(type), ItemBlockAdvancedFactory::new);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(BlockType type, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return registerTieredBlock(Objects.requireNonNull(type.get(ExtraAttributeTier.class)).tier(), suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IAdvancedTier tier, String suffix,
                                                                                                                      Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return EXTRA_BLOCK.register(tier.getAdvanceTier().getLowerName() + suffix, blockSupplier, itemCreator);
    }

    /**
     * Retrieves a Factory with a defined tier and recipe type.
     *
     * @param tier - tier to add to the Factory
     * @param type - recipe type to add to the Factory
     *
     * @return factory with defined tier and recipe type
     */
    public static BlockRegistryObject<BlockAdvancedFactoryMachine.BlockAdvancedFactory<?>, ItemBlockAdvancedFactory> getAdvancedFactory(@NotNull AdvancedFactoryTier tier, @NotNull FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static BlockRegistryObject<BlockAdvancedFactoryMachine.BlockAdvancedFactory<?>, ItemBlockAdvancedFactory>[] getAdvancedFactoryBlocks() {
        return FACTORIES.values().toArray(new BlockRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
//        BLOCKS.register(eventBus);
        EXTRA_BLOCK.register(eventBus);
    }
}
