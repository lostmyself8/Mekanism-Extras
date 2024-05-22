package com.jerry.mekanism_extras.registery;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.block.storage.bin.ExtraBlockBin;
import com.jerry.mekanism_extras.common.block.storage.bin.ExtraItemBlockBin;
import com.jerry.mekanism_extras.common.block.storage.bin.ExtraTileEntityBin;
import com.jerry.mekanism_extras.common.block.storage.chemicaltank.ExtraItemBlockChemicalTank;
import com.jerry.mekanism_extras.common.block.storage.chemicaltank.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.block.storage.energycube.ExtraBlockEnergyCube;
import com.jerry.mekanism_extras.common.block.storage.energycube.ExtraItemBlockEnergyCube;
import com.jerry.mekanism_extras.common.block.storage.energycube.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.ExtraBlockFluidTank;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.ExtraItemBlockFluidTank;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.block.storage.radioactivewastebarrel.ExtraBlockRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.common.block.storage.radioactivewastebarrel.ExtraItemBlockRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.common.block.transmitter.cable.ExtraBlockUniversalCable;
import com.jerry.mekanism_extras.common.block.transmitter.cable.ExtraItemBlockUniversalCable;
import com.jerry.mekanism_extras.common.block.transmitter.logisticaltransporter.ExtraBlockLogisticalTransporter;
import com.jerry.mekanism_extras.common.block.transmitter.logisticaltransporter.ExtraItemBlockLogisticalTransporter;
import com.jerry.mekanism_extras.common.block.transmitter.pipe.ExtraBlockMechanicalPipe;
import com.jerry.mekanism_extras.common.block.transmitter.pipe.ExtraItemBlockMechanicalPipe;
import com.jerry.mekanism_extras.common.block.transmitter.thermodynamicconductor.ExtraBlockThermodynamicConductor;
import com.jerry.mekanism_extras.common.block.transmitter.thermodynamicconductor.ExtraItemBlockThermodynamicConductor;
import com.jerry.mekanism_extras.common.block.transmitter.tube.ExtraBlockPressurizedTube;
import com.jerry.mekanism_extras.common.block.transmitter.tube.ExtraItemBlockPressurizedTube;
import mekanism.api.tier.ITier;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.tier.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Function;
import java.util.function.Supplier;

public class ExtraBlock {
    public static final BlockDeferredRegister EXTRA_BLOCK = new BlockDeferredRegister(MekanismExtras.MODID);

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(String tierName, String suffix, Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return EXTRA_BLOCK.register(tierName + suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(String registerName, Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return EXTRA_BLOCK.register(registerName, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(BlockType type, String registerName, Function<MapColor, ? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        ITier tier = type.get(AttributeTier.class).tier();
        return EXTRA_BLOCK.register(registerName, () -> blockSupplier.apply(tier.getBaseTier().getMapColor()), itemCreator);
    }

    //bin
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> ABSOLUTE_BIN = registerBin("absolute", ExtraBlockType.ABSOLUTE_BIN);
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> SUPREME_BIN = registerBin("supreme", ExtraBlockType.SUPREME_BIN);
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> COSMIC_BIN = registerBin("cosmic", ExtraBlockType.COSMIC_BIN);
    public static final BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> INFINITE_BIN = registerBin("infinite", ExtraBlockType.INFINITE_BIN);
    //fluid tank
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> ABSOLUTE_FLUID_TANK = registerFluidTank("absolute", ExtraBlockType.ABSOLUTE_FLUID_TANK);
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> SUPREME_FLUID_TANK = registerFluidTank("supreme", ExtraBlockType.SUPREME_FLUID_TANK);
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> COSMIC_FLUID_TANK = registerFluidTank("cosmic", ExtraBlockType.COSMIC_FLUID_TANK);
    public static final BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> INFINITE_FLUID_TANK = registerFluidTank("infinite", ExtraBlockType.INFINITE_FLUID_TANK);
    //energy cube
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> ABSOLUTE_ENERGY_CUBE = registerEnergyCube("absolute", ExtraBlockType.ABSOLUTE_ENERGY_CUBE);
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> SUPREME_ENERGY_CUBE = registerEnergyCube("supreme", ExtraBlockType.SUPREME_ENERGY_CUBE);
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> COSMIC_ENERGY_CUBE = registerEnergyCube("cosmic", ExtraBlockType.COSMIC_ENERGY_CUBE);
    public static final BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> INFINITE_ENERGY_CUBE = registerEnergyCube("infinite", ExtraBlockType.INFINITE_ENERGY_CUBE);
    //universal cable
    public static final BlockRegistryObject<ExtraBlockUniversalCable, ExtraItemBlockUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = registerUniversalCable("absolute", CableTier.BASIC);
    public static final BlockRegistryObject<ExtraBlockUniversalCable, ExtraItemBlockUniversalCable> SUPREME_UNIVERSAL_CABLE = registerUniversalCable("supreme", CableTier.ADVANCED);
    public static final BlockRegistryObject<ExtraBlockUniversalCable, ExtraItemBlockUniversalCable> COSMIC_UNIVERSAL_CABLE = registerUniversalCable("cosmic", CableTier.ELITE);
    public static final BlockRegistryObject<ExtraBlockUniversalCable, ExtraItemBlockUniversalCable> INFINITE_UNIVERSAL_CABLE = registerUniversalCable("infinite", CableTier.ULTIMATE);
    //mechanical pipe
    public static final BlockRegistryObject<ExtraBlockMechanicalPipe, ExtraItemBlockMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = registerMechanicalPipe("absolute", PipeTier.BASIC);
    public static final BlockRegistryObject<ExtraBlockMechanicalPipe, ExtraItemBlockMechanicalPipe> SUPREME_MECHANICAL_PIPE = registerMechanicalPipe("supreme", PipeTier.ADVANCED);
    public static final BlockRegistryObject<ExtraBlockMechanicalPipe, ExtraItemBlockMechanicalPipe> COSMIC_MECHANICAL_PIPE = registerMechanicalPipe("cosmic", PipeTier.ELITE);
    public static final BlockRegistryObject<ExtraBlockMechanicalPipe, ExtraItemBlockMechanicalPipe> INFINITE_MECHANICAL_PIPE = registerMechanicalPipe("infinite", PipeTier.ULTIMATE);
    //pressurized tube
    public static final BlockRegistryObject<ExtraBlockPressurizedTube, ExtraItemBlockPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = registerPressurizedTube("absolute", TubeTier.BASIC);
    public static final BlockRegistryObject<ExtraBlockPressurizedTube, ExtraItemBlockPressurizedTube> SUPREME_PRESSURIZED_TUBE = registerPressurizedTube("supreme", TubeTier.ADVANCED);
    public static final BlockRegistryObject<ExtraBlockPressurizedTube, ExtraItemBlockPressurizedTube> COSMIC_PRESSURIZED_TUBE = registerPressurizedTube("cosmic", TubeTier.ELITE);
    public static final BlockRegistryObject<ExtraBlockPressurizedTube, ExtraItemBlockPressurizedTube> INFINITE_PRESSURIZED_TUBE = registerPressurizedTube("infinite", TubeTier.ULTIMATE);
    //logistical transporter
    public static final BlockRegistryObject<ExtraBlockLogisticalTransporter, ExtraItemBlockLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("absolute", TransporterTier.BASIC);
    public static final BlockRegistryObject<ExtraBlockLogisticalTransporter, ExtraItemBlockLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("supreme", TransporterTier.ADVANCED);
    public static final BlockRegistryObject<ExtraBlockLogisticalTransporter, ExtraItemBlockLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("cosmic", TransporterTier.ELITE);
    public static final BlockRegistryObject<ExtraBlockLogisticalTransporter, ExtraItemBlockLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("infinite", TransporterTier.ULTIMATE);
    //thermodynamic conductor
    public static final BlockRegistryObject<ExtraBlockThermodynamicConductor, ExtraItemBlockThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("absolute", ConductorTier.BASIC);
    public static final BlockRegistryObject<ExtraBlockThermodynamicConductor, ExtraItemBlockThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("supreme", ConductorTier.ADVANCED);
    public static final BlockRegistryObject<ExtraBlockThermodynamicConductor, ExtraItemBlockThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("cosmic", ConductorTier.ELITE);
    public static final BlockRegistryObject<ExtraBlockThermodynamicConductor, ExtraItemBlockThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("infinite", ConductorTier.ULTIMATE);
    //chemical tank
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> ABSOLUTE_CHEMICAL_TANK = registerChemicalTank("absolute", ExtraBlockType.ABSOLUTE_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> SUPREME_CHEMICAL_TANK = registerChemicalTank("supreme", ExtraBlockType.SUPREME_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> COSMIC_CHEMICAL_TANK = registerChemicalTank("cosmic", ExtraBlockType.COSMIC_CHEMICAL_TANK);
    public static final BlockRegistryObject<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>, ExtraItemBlockChemicalTank> INFINITE_CHEMICAL_TANK = registerChemicalTank("infinite", ExtraBlockType.INFINITE_CHEMICAL_TANK);
    //
    public static final BlockRegistryObject<ExtraBlockRadioactiveWasteBarrel, ExtraItemBlockRadioactiveWasteBarrel> EXPAND_RADIOACTIVE_WASTE_BARREL = EXTRA_BLOCK.registerDefaultProperties("expand_radioactive_waste_barrel", ExtraBlockRadioactiveWasteBarrel::new, ExtraItemBlockRadioactiveWasteBarrel::new);

    private static BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> registerBin(String tileName, BlockTypeTile<ExtraTileEntityBin> type) {
        return registerTieredBlock(type, tileName + "_bin", color -> new ExtraBlockBin(type, properties -> properties.mapColor(color)), ExtraItemBlockBin::new);
    }

    private static BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> registerFluidTank(String tileName, Machine<ExtraTileEntityFluidTank> type) {
        return registerTieredBlock(tileName + "_fluid_tank", () -> new ExtraBlockFluidTank(type), ExtraItemBlockFluidTank::new);
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
        return registerTieredBlock(type, tileName + "_chemical_tank", color -> new BlockTile.BlockTileModel<>(type, properties -> properties.mapColor(color)), ExtraItemBlockChemicalTank::new);
    }

    public static void register(IEventBus eventBus) {
        EXTRA_BLOCK.register(eventBus);
    }
}
