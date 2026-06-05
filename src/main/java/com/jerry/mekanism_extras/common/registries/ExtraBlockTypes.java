package com.jerry.mekanism_extras.common.registries;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekanism_extras.common.ExtraLang;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.config.LoadConfig;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraFactory;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine.ExtraMachineBuilder;
import com.jerry.mekanism_extras.common.integration.Addons;
import com.jerry.mekanism_extras.common.tier.*;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityBin;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.common.tile.machine.TileEntityAdvancedElectricPump;
import com.jerry.mekanism_extras.common.tile.multiblock.*;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import com.jerry.mekanism_extras.common.util.ExtraFloatingLong;

import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.blocktype.*;
import mekanism.common.content.blocktype.BlockType.BlockTypeBuilder;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tile.machine.*;
import mekanism.common.util.EnumUtils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;

import java.util.EnumSet;
import java.util.function.Supplier;

public class ExtraBlockTypes {

    private static final Table<ExtraFactoryTier, FactoryType, ExtraFactory<?>> FACTORIES = HashBasedTable.create();

    // Enrichment Chamber
    public static final ExtraFactoryMachine<TileEntityEnrichmentChamber> ENRICHMENT_CHAMBER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.ENRICHMENT_CHAMBER, MekanismLang.DESCRIPTION_ENRICHMENT_CHAMBER, FactoryType.ENRICHING)
            .withSound(MekanismSounds.ENRICHMENT_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.enrichmentChamber, MekanismConfig.storage.enrichmentChamber)
            .build();
    // Crusher
    public static final ExtraFactoryMachine<TileEntityCrusher> CRUSHER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.CRUSHER, MekanismLang.DESCRIPTION_CRUSHER, FactoryType.CRUSHING)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MekanismConfig.usage.crusher, MekanismConfig.storage.crusher)
            .build();
    // Energized Smelter
    public static final ExtraFactoryMachine<TileEntityEnergizedSmelter> ENERGIZED_SMELTER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.ENERGIZED_SMELTER, MekanismLang.DESCRIPTION_ENERGIZED_SMELTER, FactoryType.SMELTING)
            .withSound(MekanismSounds.ENERGIZED_SMELTER)
            .withEnergyConfig(MekanismConfig.usage.energizedSmelter, MekanismConfig.storage.energizedSmelter)
            .build();
    // Precision Sawmill
    public static final ExtraFactoryMachine<TileEntityPrecisionSawmill> PRECISION_SAWMILL = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.PRECISION_SAWMILL, MekanismLang.DESCRIPTION_PRECISION_SAWMILL, FactoryType.SAWING)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MekanismConfig.usage.precisionSawmill, MekanismConfig.storage.precisionSawmill)
            .build();
    // Osmium Compressor
    public static final ExtraFactoryMachine<TileEntityOsmiumCompressor> OSMIUM_COMPRESSOR = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.OSMIUM_COMPRESSOR, MekanismLang.DESCRIPTION_OSMIUM_COMPRESSOR, FactoryType.COMPRESSING)
            .withSound(MekanismSounds.OSMIUM_COMPRESSOR)
            .withEnergyConfig(MekanismConfig.usage.osmiumCompressor, MekanismConfig.storage.osmiumCompressor)
            .build();
    // Combiner
    public static final ExtraFactoryMachine<TileEntityCombiner> COMBINER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.COMBINER, MekanismLang.DESCRIPTION_COMBINER, FactoryType.COMBINING)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(MekanismConfig.usage.combiner, MekanismConfig.storage.combiner)
            .build();
    // Metallurgic Infuser
    public static final ExtraFactoryMachine<TileEntityMetallurgicInfuser> METALLURGIC_INFUSER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.METALLURGIC_INFUSER, MekanismLang.DESCRIPTION_METALLURGIC_INFUSER, FactoryType.INFUSING)
            .withSound(MekanismSounds.METALLURGIC_INFUSER)
            .withEnergyConfig(MekanismConfig.usage.metallurgicInfuser, MekanismConfig.storage.metallurgicInfuser)
            .build();
    // Purification Chamber
    public static final ExtraFactoryMachine<TileEntityPurificationChamber> PURIFICATION_CHAMBER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.PURIFICATION_CHAMBER, MekanismLang.DESCRIPTION_PURIFICATION_CHAMBER, FactoryType.PURIFYING)
            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.purificationChamber, MekanismConfig.storage.purificationChamber)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // Chemical Injection Chamber
    public static final ExtraFactoryMachine<TileEntityChemicalInjectionChamber> CHEMICAL_INJECTION_CHAMBER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_INJECTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_INJECTION_CHAMBER, FactoryType.INJECTING)
            .withSound(MekanismSounds.CHEMICAL_INJECTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalInjectionChamber, MekanismConfig.storage.chemicalInjectionChamber)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // Radioactive Waste Barrel
    public static final BlockTypeTile<ExtraTileEntityRadioactiveWasteBarrel> EXPAND_RADIOACTIVE_WASTE_BARREL = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraTileEntityTypes.EXPAND_RADIOACTIVE_WASTE_BARREL, MekanismLang.DESCRIPTION_RADIOACTIVE_WASTE_BARREL)
            .with(Attributes.ACTIVE_LIGHT, new AttributeStateFacing(), Attributes.COMPARATOR)
            .withCustomShape(BlockShapes.RADIOACTIVE_WASTE_BARREL)
            .withComputerSupport("radioactiveWasteBarrel")
            .build();
    // Hard Induction Casing
    public static final BlockTypeTile<TileEntityReinforcedInductionCasing> REINFORCED_INDUCTION_CASING = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraTileEntityTypes.REINFORCED_INDUCTION_CASING, MekanismLang.DESCRIPTION_INDUCTION_CASING)
            .withGui(() -> ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX, MekanismLang.MATRIX)
            .with(Attributes.INVENTORY, Attributes.COMPARATOR)
            .externalMultiblock()
            .build();
    // Hard Induction Port
    public static final BlockTypeTile<TileEntityReinforcedInductionPort> REINFORCED_INDUCTION_PORT = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraTileEntityTypes.REINFORCED_INDUCTION_PORT, MekanismLang.DESCRIPTION_INDUCTION_PORT)
            .withGui(() -> ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX, MekanismLang.MATRIX)
            .with(Attributes.INVENTORY, Attributes.COMPARATOR, Attributes.ACTIVE)
            .externalMultiblock()
            .withComputerSupport("reinforcedInductionPort")
            .build();
    // Bin
    public static final Machine<ExtraTileEntityBin> ABSOLUTE_BIN = createBin(BTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_BIN, () -> ExtraBlocks.SUPREME_BIN);
    public static final Machine<ExtraTileEntityBin> SUPREME_BIN = createBin(BTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_BIN, () -> ExtraBlocks.COSMIC_BIN);
    public static final Machine<ExtraTileEntityBin> COSMIC_BIN = createBin(BTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_BIN, () -> ExtraBlocks.INFINITE_BIN);
    public static final Machine<ExtraTileEntityBin> INFINITE_BIN = createBin(BTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_BIN, null);
    // Induction Cells
    public static final BlockTypeTile<ExtraTileEntityInductionCell> ABSOLUTE_INDUCTION_CELL = createInductionCell(ICTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_INDUCTION_CELL);
    public static final BlockTypeTile<ExtraTileEntityInductionCell> SUPREME_INDUCTION_CELL = createInductionCell(ICTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_INDUCTION_CELL);
    public static final BlockTypeTile<ExtraTileEntityInductionCell> COSMIC_INDUCTION_CELL = createInductionCell(ICTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_INDUCTION_CELL);
    public static final BlockTypeTile<ExtraTileEntityInductionCell> INFINITE_INDUCTION_CELL = createInductionCell(ICTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_INDUCTION_CELL);
    // Induction Provide
    public static final BlockTypeTile<ExtraTileEntityInductionProvider> ABSOLUTE_INDUCTION_PROVIDER = createInductionProvider(IPTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_INDUCTION_PROVIDER);
    public static final BlockTypeTile<ExtraTileEntityInductionProvider> SUPREME_INDUCTION_PROVIDER = createInductionProvider(IPTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_INDUCTION_PROVIDER);
    public static final BlockTypeTile<ExtraTileEntityInductionProvider> COSMIC_INDUCTION_PROVIDER = createInductionProvider(IPTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_INDUCTION_PROVIDER);
    public static final BlockTypeTile<ExtraTileEntityInductionProvider> INFINITE_INDUCTION_PROVIDER = createInductionProvider(IPTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_INDUCTION_PROVIDER);
    // Fluid Tank
    public static final Machine<ExtraTileEntityFluidTank> ABSOLUTE_FLUID_TANK = createFluidTank(FTTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_FLUID_TANK, () -> ExtraBlocks.SUPREME_FLUID_TANK);
    public static final Machine<ExtraTileEntityFluidTank> SUPREME_FLUID_TANK = createFluidTank(FTTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_FLUID_TANK, () -> ExtraBlocks.COSMIC_FLUID_TANK);
    public static final Machine<ExtraTileEntityFluidTank> COSMIC_FLUID_TANK = createFluidTank(FTTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_FLUID_TANK, () -> ExtraBlocks.INFINITE_FLUID_TANK);
    public static final Machine<ExtraTileEntityFluidTank> INFINITE_FLUID_TANK = createFluidTank(FTTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_FLUID_TANK, null);
    // Energy Cube
    public static final Machine<ExtraTileEntityEnergyCube> ABSOLUTE_ENERGY_CUBE = createEnergyCube(ECTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_ENERGY_CUBE, () -> ExtraBlocks.SUPREME_ENERGY_CUBE);
    public static final Machine<ExtraTileEntityEnergyCube> SUPREME_ENERGY_CUBE = createEnergyCube(ECTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_ENERGY_CUBE, () -> ExtraBlocks.COSMIC_ENERGY_CUBE);
    public static final Machine<ExtraTileEntityEnergyCube> COSMIC_ENERGY_CUBE = createEnergyCube(ECTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_ENERGY_CUBE, () -> ExtraBlocks.INFINITE_ENERGY_CUBE);
    public static final Machine<ExtraTileEntityEnergyCube> INFINITE_ENERGY_CUBE = createEnergyCube(ECTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_ENERGY_CUBE, null);
    // Chemical Tank
    public static final Machine<ExtraTileEntityChemicalTank> ABSOLUTE_CHEMICAL_TANK = createChemicalTank(CTTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_CHEMICAL_TANK, () -> ExtraBlocks.SUPREME_CHEMICAL_TANK);
    public static final Machine<ExtraTileEntityChemicalTank> SUPREME_CHEMICAL_TANK = createChemicalTank(CTTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_CHEMICAL_TANK, () -> ExtraBlocks.COSMIC_CHEMICAL_TANK);
    public static final Machine<ExtraTileEntityChemicalTank> COSMIC_CHEMICAL_TANK = createChemicalTank(CTTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_CHEMICAL_TANK, () -> ExtraBlocks.INFINITE_CHEMICAL_TANK);
    public static final Machine<ExtraTileEntityChemicalTank> INFINITE_CHEMICAL_TANK = createChemicalTank(CTTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_CHEMICAL_TANK, null);
    // Electric Pump
    public static final Machine<TileEntityAdvancedElectricPump> ADVANCED_ELECTRIC_PUMP = Machine.MachineBuilder
            .createMachine(() -> ExtraTileEntityTypes.ADVANCED_ELECTRIC_PUMP, MekanismLang.DESCRIPTION_ELECTRIC_PUMP)
            .withGui(() -> ExtraContainerTypes.ADVANCED_ELECTRIC_PUMP)
            .withEnergyConfig(LoadConfig.extraUsage.advanceElectricPump, LoadConfig.extraStorage.advanceElectricPump)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.FILTER, ExtraUpgrade.IONIC_MEMBRANE))
            .withCustomShape(BlockShapes.ELECTRIC_PUMP)
            .withComputerSupport("advancedElectricPump")
            .replace(Attributes.ACTIVE)
            .build();
    // Tungsten Casing
    public static final BlockType TUNGSTEN_CASING = BlockTypeBuilder
            .createBlock(ExtraLang.DESCRIPTION_TUNGSTEN_CASING)
            .build();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                if (Addons.EVOLVEDMEKANISM.isLoaded()) {
                    if (type != EMFactoryType.ALLOYING) {
                        FACTORIES.put(tier, type, ExtraFactory.AdvancedFactoryBuilder.createFactory(() -> ExtraTileEntityTypes.getExtraFactoryTile(tier, type), type, tier).build());
                    }
                } else {
                    FACTORIES.put(tier, type, ExtraFactory.AdvancedFactoryBuilder.createFactory(() -> ExtraTileEntityTypes.getExtraFactoryTile(tier, type), type, tier).build());
                }
            }
        }
    }

    public static ExtraFactory<?> getAdvancedFactory(ExtraFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    private static <TILE extends ExtraTileEntityInductionCell> BlockTypeTile<TILE> createInductionCell(ICTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_INDUCTION_CELL)
                .withEnergyConfig(tier::getMaxEnergy)
                .with(new AttributeTier<>(tier))
                .internalMultiblock()
                .build();
    }

    private static <TILE extends ExtraTileEntityInductionProvider> BlockTypeTile<TILE> createInductionProvider(IPTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_INDUCTION_PROVIDER)
                .with(new AttributeTier<>(tier))
                .internalMultiblock()
                .build();
    }

    private static <TILE extends ExtraTileEntityBin> Machine<TILE> createBin(BTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_BIN)
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, Attributes.AttributeSecurity.class, AttributeUpgradeSupport.class, Attributes.AttributeRedstone.class)
                .withComputerSupport(tier.getAdvanceTier().getLowerName() + "Bin")
                .build();
    }

    private static <TILE extends ExtraTileEntityFluidTank> Machine<TILE> createFluidTank(FTTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_FLUID_TANK)
                .withGui(() -> ExtraContainerTypes.FLUID_TANK)
                .withCustomShape(BlockShapes.FLUID_TANK)
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, AttributeStateFacing.class, Attributes.AttributeRedstone.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier.getAdvanceTier().getLowerName() + "FluidTank")
                .build();
    }

    private static <TILE extends ExtraTileEntityEnergyCube> Machine<TILE> createEnergyCube(ECTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_ENERGY_CUBE)
                .withGui(() -> ExtraContainerTypes.ENERGY_CUBE)
                .withEnergyConfig(new ExtraFloatingLong(tier.getMaxEnergy()))
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, AttributeStateActive.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier.getAdvanceTier().getLowerName() + "EnergyCube")
                .build();
    }

    private static <TILE extends ExtraTileEntityChemicalTank> Machine<TILE> createChemicalTank(CTTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_CHEMICAL_TANK)
                .withGui(() -> ExtraContainerTypes.CHEMICAL_TANK)
                .withCustomShape(BlockShapes.CHEMICAL_TANK)
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, AttributeStateActive.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier.getAdvanceTier().getLowerName() + "ChemicalTank")
                .build();
    }
}
