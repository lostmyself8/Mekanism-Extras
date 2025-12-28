package com.jerry.mekextras.common.registries;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.api.ExtraUpgrade;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeSupport;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekextras.common.config.ExtraConfig;
import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.content.blocktype.ExtraFactory.ExtraFactoryBuilder;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraMachineBuilder;
import com.jerry.mekextras.common.tier.*;
import com.jerry.mekextras.common.tile.*;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraTransmitter;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraUniversalCable;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraLogisticalTransporter;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraMechanicalPipe;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraThermodynamicConductor;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraPressurizedTube;
import com.jerry.mekextras.common.tile.machine.TileEntityAdvancedElectricPump;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionPort;
import com.jerry.mekextras.common.tile.multiblock.TileEntityExtraInductionCell;
import com.jerry.mekextras.common.tile.multiblock.TileEntityExtraInductionProvider;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.api.Upgrade;
import mekanism.api.text.ILangEntry;
import mekanism.api.tier.ITier;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.blocktype.*;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.registries.MekanismContainerTypes;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tier.*;
import mekanism.common.tile.machine.*;
import mekanism.common.util.EnumUtils;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.Supplier;

public class ExtraBlockTypes {

    private static final Table<ExtraFactoryTier, FactoryType, ExtraFactory<?>> FACTORIES = HashBasedTable.create();

    // Enrichment Chamber
    public static final ExtraFactoryMachine<TileEntityEnrichmentChamber> ENRICHMENT_CHAMBER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.ENRICHMENT_CHAMBER, MekanismLang.DESCRIPTION_ENRICHMENT_CHAMBER, FactoryType.ENRICHING)
            .withGui(() -> MekanismContainerTypes.ENRICHMENT_CHAMBER)
            .withSound(MekanismSounds.ENRICHMENT_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.enrichmentChamber, MekanismConfig.storage.enrichmentChamber)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();
    // Crusher
    public static final ExtraFactoryMachine<TileEntityCrusher> CRUSHER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.CRUSHER, MekanismLang.DESCRIPTION_CRUSHER, FactoryType.CRUSHING)
            .withGui(() -> MekanismContainerTypes.CRUSHER)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MekanismConfig.usage.crusher, MekanismConfig.storage.crusher)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();
    // Energized Smelter
    public static final ExtraFactoryMachine<TileEntityEnergizedSmelter> ENERGIZED_SMELTER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.ENERGIZED_SMELTER, MekanismLang.DESCRIPTION_ENERGIZED_SMELTER, FactoryType.SMELTING)
            .withGui(() -> MekanismContainerTypes.ENERGIZED_SMELTER)
            .withSound(MekanismSounds.ENERGIZED_SMELTER)
            .withEnergyConfig(MekanismConfig.usage.energizedSmelter, MekanismConfig.storage.energizedSmelter)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();
    // Precision Sawmill
    public static final ExtraFactoryMachine<TileEntityPrecisionSawmill> PRECISION_SAWMILL = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.PRECISION_SAWMILL, MekanismLang.DESCRIPTION_PRECISION_SAWMILL, FactoryType.SAWING)
            .withGui(() -> MekanismContainerTypes.PRECISION_SAWMILL)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MekanismConfig.usage.precisionSawmill, MekanismConfig.storage.precisionSawmill)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();
    // Osmium Compressor
    public static final ExtraFactoryMachine<TileEntityOsmiumCompressor> OSMIUM_COMPRESSOR = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.OSMIUM_COMPRESSOR, MekanismLang.DESCRIPTION_OSMIUM_COMPRESSOR, FactoryType.COMPRESSING)
            .withGui(() -> MekanismContainerTypes.OSMIUM_COMPRESSOR)
            .withSound(MekanismSounds.OSMIUM_COMPRESSOR)
            .withEnergyConfig(MekanismConfig.usage.osmiumCompressor, MekanismConfig.storage.osmiumCompressor)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();
    // Combiner
    public static final ExtraFactoryMachine<TileEntityCombiner> COMBINER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.COMBINER, MekanismLang.DESCRIPTION_COMBINER, FactoryType.COMBINING)
            .withGui(() -> MekanismContainerTypes.COMBINER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(MekanismConfig.usage.combiner, MekanismConfig.storage.combiner)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();
    // Metallurgic Infuser
    public static final ExtraFactoryMachine<TileEntityMetallurgicInfuser> METALLURGIC_INFUSER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.METALLURGIC_INFUSER, MekanismLang.DESCRIPTION_METALLURGIC_INFUSER, FactoryType.INFUSING)
            .withGui(() -> MekanismContainerTypes.METALLURGIC_INFUSER)
            .withSound(MekanismSounds.METALLURGIC_INFUSER)
            .withEnergyConfig(MekanismConfig.usage.metallurgicInfuser, MekanismConfig.storage.metallurgicInfuser)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();
    // Purification Chamber
    public static final ExtraFactoryMachine<TileEntityPurificationChamber> PURIFICATION_CHAMBER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.PURIFICATION_CHAMBER, MekanismLang.DESCRIPTION_PURIFICATION_CHAMBER, FactoryType.PURIFYING)
            .withGui(() -> MekanismContainerTypes.PURIFICATION_CHAMBER)
            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.purificationChamber, MekanismConfig.storage.purificationChamber)
            .with(ExtraAttributeUpgradeSupport.EXTRA_ADVANCED_MACHINE_UPGRADES)
            .build();
    // Chemical Injection Chamber
    public static final ExtraFactoryMachine<TileEntityChemicalInjectionChamber> CHEMICAL_INJECTION_CHAMBER = ExtraMachineBuilder
            .createExtraFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_INJECTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_INJECTION_CHAMBER, FactoryType.INJECTING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_INJECTION_CHAMBER)
            .withSound(MekanismSounds.CHEMICAL_INJECTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalInjectionChamber, MekanismConfig.storage.chemicalInjectionChamber)
            .with(ExtraAttributeUpgradeSupport.EXTRA_ADVANCED_MACHINE_UPGRADES)
            .build();

    // Electric Pump
    public static final Machine<TileEntityAdvancedElectricPump> ADVANCED_ELECTRIC_PUMP = Machine.MachineBuilder
            .createMachine(() -> ExtraTileEntityTypes.ADVANCED_ELECTRIC_PUMP, MekanismLang.DESCRIPTION_ELECTRIC_PUMP)
            .withGui(() -> ExtraContainerTypes.ADVANCE_ELECTRIC_PUMP)
            .withEnergyConfig(ExtraConfig.extraUsageConfig.advanceElectricPump, ExtraConfig.extraStorageConfig.advanceElectricPump)
            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.FILTER, ExtraUpgrade.IONIC_MEMBRANE)
            .withCustomShape(BlockShapes.ELECTRIC_PUMP)
            .withComputerSupport("advancedElectricPump")
            .replace(Attributes.ACTIVE)
            .build();

    // Bins
    public static final Machine<TileEntityExtraBin> ABSOLUTE_BIN = createBin(BTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_BIN, () -> ExtraBlocks.SUPREME_BIN);
    public static final Machine<TileEntityExtraBin> SUPREME_BIN = createBin(BTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_BIN, () -> ExtraBlocks.COSMIC_BIN);
    public static final Machine<TileEntityExtraBin> COSMIC_BIN = createBin(BTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_BIN, () -> ExtraBlocks.INFINITE_BIN);
    public static final Machine<TileEntityExtraBin> INFINITE_BIN = createBin(BTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_BIN, null);

    // Induction Casing
    public static final BlockTypeTile<TileEntityReinforcedInductionCasing> REINFORCED_INDUCTION_CASING = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraTileEntityTypes.REINFORCED_INDUCTION_CASING, MekanismLang.DESCRIPTION_INDUCTION_CASING)
            .withGui(() -> ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX, MekanismLang.MATRIX)
            .with(Attributes.INVENTORY, Attributes.COMPARATOR)
            .externalMultiblock()
            .build();
    // Induction Port
    public static final BlockTypeTile<TileEntityReinforcedInductionPort> REINFORCED_INDUCTION_PORT = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraTileEntityTypes.REINFORCED_INDUCTION_PORT, MekanismLang.DESCRIPTION_INDUCTION_PORT)
            .withGui(() -> ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX, MekanismLang.MATRIX)
            .with(Attributes.INVENTORY, Attributes.COMPARATOR, Attributes.ACTIVE)
            .externalMultiblock()
            .withComputerSupport("reinforcedInductionPort")
            .build();

    // Induction Cells
    public static final BlockTypeTile<TileEntityExtraInductionCell> ABSOLUTE_INDUCTION_CELL = createInductionCell(ICTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_INDUCTION_CELL);
    public static final BlockTypeTile<TileEntityExtraInductionCell> SUPREME_INDUCTION_CELL = createInductionCell(ICTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_INDUCTION_CELL);
    public static final BlockTypeTile<TileEntityExtraInductionCell> COSMIC_INDUCTION_CELL = createInductionCell(ICTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_INDUCTION_CELL);
    public static final BlockTypeTile<TileEntityExtraInductionCell> INFINITE_INDUCTION_CELL = createInductionCell(ICTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_INDUCTION_CELL);

    // Induction Provider
    public static final BlockTypeTile<TileEntityExtraInductionProvider> ABSOLUTE_INDUCTION_PROVIDER = createInductionProvider(IPTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_INDUCTION_PROVIDER);
    public static final BlockTypeTile<TileEntityExtraInductionProvider> SUPREME_INDUCTION_PROVIDER = createInductionProvider(IPTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_INDUCTION_PROVIDER);
    public static final BlockTypeTile<TileEntityExtraInductionProvider> COSMIC_INDUCTION_PROVIDER = createInductionProvider(IPTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_INDUCTION_PROVIDER);
    public static final BlockTypeTile<TileEntityExtraInductionProvider> INFINITE_INDUCTION_PROVIDER = createInductionProvider(IPTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_INDUCTION_PROVIDER);

    // Energy Cubes
    public static final Machine<TileEntityExtraEnergyCube> ABSOLUTE_ENERGY_CUBE = createEnergyCube(ECTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_ENERGY_CUBE, () -> ExtraBlocks.SUPREME_ENERGY_CUBE);
    public static final Machine<TileEntityExtraEnergyCube> SUPREME_ENERGY_CUBE = createEnergyCube(ECTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_ENERGY_CUBE, () -> ExtraBlocks.COSMIC_ENERGY_CUBE);
    public static final Machine<TileEntityExtraEnergyCube> COSMIC_ENERGY_CUBE = createEnergyCube(ECTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_ENERGY_CUBE, () -> ExtraBlocks.INFINITE_ENERGY_CUBE);
    public static final Machine<TileEntityExtraEnergyCube> INFINITE_ENERGY_CUBE = createEnergyCube(ECTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_ENERGY_CUBE, null);

    // Fluid Tanks
    public static final Machine<TileEntityExtraFluidTank> ABSOLUTE_FLUID_TANK = createFluidTank(FTTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_FLUID_TANK, () -> ExtraBlocks.SUPREME_FLUID_TANK);
    public static final Machine<TileEntityExtraFluidTank> SUPREME_FLUID_TANK = createFluidTank(FTTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_FLUID_TANK, () -> ExtraBlocks.COSMIC_FLUID_TANK);
    public static final Machine<TileEntityExtraFluidTank> COSMIC_FLUID_TANK = createFluidTank(FTTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_FLUID_TANK, () -> ExtraBlocks.INFINITE_FLUID_TANK);
    public static final Machine<TileEntityExtraFluidTank> INFINITE_FLUID_TANK = createFluidTank(FTTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_FLUID_TANK, null);

    // Chemical Tanks
    public static final Machine<TileEntityExtraChemicalTank> ABSOLUTE_CHEMICAL_TANK = createChemicalTank(CTTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_CHEMICAL_TANK, () -> ExtraBlocks.SUPREME_CHEMICAL_TANK);
    public static final Machine<TileEntityExtraChemicalTank> SUPREME_CHEMICAL_TANK = createChemicalTank(CTTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_CHEMICAL_TANK, () -> ExtraBlocks.COSMIC_CHEMICAL_TANK);
    public static final Machine<TileEntityExtraChemicalTank> COSMIC_CHEMICAL_TANK = createChemicalTank(CTTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_CHEMICAL_TANK, () -> ExtraBlocks.INFINITE_CHEMICAL_TANK);
    public static final Machine<TileEntityExtraChemicalTank> INFINITE_CHEMICAL_TANK = createChemicalTank(CTTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_CHEMICAL_TANK, null);

    public static final BlockTypeTile<TileEntityLargeCapRadioactiveWasteBarrel> ABSOLUTE_RADIOACTIVE_WASTE_BARREL = createWasteBarrel(RWBTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_RADIOACTIVE_WASTE_BARREL, () -> ExtraBlocks.SUPREME_RADIOACTIVE_WASTE_BARREL);
    public static final BlockTypeTile<TileEntityLargeCapRadioactiveWasteBarrel> SUPREME_RADIOACTIVE_WASTE_BARREL = createWasteBarrel(RWBTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_RADIOACTIVE_WASTE_BARREL, () -> ExtraBlocks.COSMIC_RADIOACTIVE_WASTE_BARREL);
    public static final BlockTypeTile<TileEntityLargeCapRadioactiveWasteBarrel> COSMIC_RADIOACTIVE_WASTE_BARREL = createWasteBarrel(RWBTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_RADIOACTIVE_WASTE_BARREL, () -> ExtraBlocks.INFINITE_RADIOACTIVE_WASTE_BARREL);
    public static final BlockTypeTile<TileEntityLargeCapRadioactiveWasteBarrel> INFINITE_RADIOACTIVE_WASTE_BARREL = createWasteBarrel(RWBTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_RADIOACTIVE_WASTE_BARREL, null);

    //Transmitters
    public static final BlockTypeTile<TileEntityExtraUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = createCable(CableTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_UNIVERSAL_CABLE);
    public static final BlockTypeTile<TileEntityExtraUniversalCable> SUPREME_UNIVERSAL_CABLE = createCable(CableTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_UNIVERSAL_CABLE);
    public static final BlockTypeTile<TileEntityExtraUniversalCable> COSMIC_UNIVERSAL_CABLE = createCable(CableTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_UNIVERSAL_CABLE);
    public static final BlockTypeTile<TileEntityExtraUniversalCable> INFINITE_UNIVERSAL_CABLE = createCable(CableTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_UNIVERSAL_CABLE);

    public static final BlockTypeTile<TileEntityExtraMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = createPipe(PipeTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_MECHANICAL_PIPE);
    public static final BlockTypeTile<TileEntityExtraMechanicalPipe> SUPREME_MECHANICAL_PIPE = createPipe(PipeTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_MECHANICAL_PIPE);
    public static final BlockTypeTile<TileEntityExtraMechanicalPipe> COSMIC_MECHANICAL_PIPE = createPipe(PipeTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_MECHANICAL_PIPE);
    public static final BlockTypeTile<TileEntityExtraMechanicalPipe> INFINITE_MECHANICAL_PIPE = createPipe(PipeTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_MECHANICAL_PIPE);

    public static final BlockTypeTile<TileEntityExtraPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = createTube(TubeTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_PRESSURIZED_TUBE);
    public static final BlockTypeTile<TileEntityExtraPressurizedTube> SUPREME_PRESSURIZED_TUBE = createTube(TubeTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_PRESSURIZED_TUBE);
    public static final BlockTypeTile<TileEntityExtraPressurizedTube> COSMIC_PRESSURIZED_TUBE = createTube(TubeTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_PRESSURIZED_TUBE);
    public static final BlockTypeTile<TileEntityExtraPressurizedTube> INFINITE_PRESSURIZED_TUBE = createTube(TubeTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_PRESSURIZED_TUBE);

    public static final BlockTypeTile<TileEntityExtraLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER);
    public static final BlockTypeTile<TileEntityExtraLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_LOGISTICAL_TRANSPORTER);
    public static final BlockTypeTile<TileEntityExtraLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_LOGISTICAL_TRANSPORTER);
    public static final BlockTypeTile<TileEntityExtraLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_LOGISTICAL_TRANSPORTER);

    public static final BlockTypeTile<TileEntityExtraThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR);
    public static final BlockTypeTile<TileEntityExtraThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_THERMODYNAMIC_CONDUCTOR);
    public static final BlockTypeTile<TileEntityExtraThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_THERMODYNAMIC_CONDUCTOR);
    public static final BlockTypeTile<TileEntityExtraThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_THERMODYNAMIC_CONDUCTOR);

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                if (MekanismExtras.hooks.evolvedMekanism.isLoaded()) {
                    if (type != EMFactoryType.ALLOYING) {
                        FACTORIES.put(tier, type, ExtraFactoryBuilder.createFactory(() -> ExtraTileEntityTypes.getAdvancedFactoryTile(tier, type), type, tier).build());
                    }
                }
                FACTORIES.put(tier, type, ExtraFactoryBuilder.createFactory(() -> ExtraTileEntityTypes.getAdvancedFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static ExtraFactory<?> getAdvancedFactory(ExtraFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    private static <TILE extends TileEntityExtraInductionCell> BlockTypeTile<TILE> createInductionCell(ICTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_INDUCTION_CELL)
                .withEnergyConfig(tier::getMaxEnergy)
                .with(new ExtraAttributeTier<>(tier))
                .internalMultiblock()
                .build();
    }

    private static <TILE extends TileEntityExtraInductionProvider> BlockTypeTile<TILE> createInductionProvider(IPTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_INDUCTION_PROVIDER)
                .with(new ExtraAttributeTier<>(tier))
                .internalMultiblock()
                .build();
    }

    private static <TILE extends TileEntityExtraBin> Machine<TILE> createBin(BTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_BIN)
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, Attributes.AttributeSecurity.class, AttributeUpgradeSupport.class, Attributes.AttributeRedstone.class)
                .withComputerSupport(tier.getAdvanceTier().getLowerName() + "Bin")
                .build();
    }

    private static <TILE extends TileEntityExtraEnergyCube> Machine<TILE> createEnergyCube(ECTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_ENERGY_CUBE)
                .withGui(() -> ExtraContainerTypes.EXTRA_ENERGY_CUBE)
                .withEnergyConfig(tier::getMaxEnergy)
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock), new AttributeStateFacing(BlockStateProperties.FACING))
                .withSideConfig(TransmissionType.ENERGY, TransmissionType.ITEM)
                .without(AttributeParticleFX.class, AttributeStateActive.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier.getAdvanceTier().getLowerName() + "EnergyCube")
                .build();
    }

    private static <TILE extends TileEntityExtraFluidTank> Machine<TILE> createFluidTank(FTTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_FLUID_TANK)
                .withGui(() -> ExtraContainerTypes.EXTRA_FLUID_TANK)
                .withCustomShape(BlockShapes.FLUID_TANK)
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, AttributeStateFacing.class, Attributes.AttributeRedstone.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier.getAdvanceTier().getLowerName() + "FluidTank")
                .build();
    }

    private static <TILE extends TileEntityExtraChemicalTank> Machine<TILE> createChemicalTank(CTTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_CHEMICAL_TANK)
                .withGui(() -> ExtraContainerTypes.EXTRA_CHEMICAL_TANK)
                .withCustomShape(BlockShapes.CHEMICAL_TANK)
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock))
                .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.ITEM)
                .without(AttributeParticleFX.class, AttributeStateActive.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier.getAdvanceTier().getLowerName() + "ChemicalTank")
                .build();
    }

    private static <TILE extends TileEntityLargeCapRadioactiveWasteBarrel> BlockTypeTile<TILE> createWasteBarrel(RWBTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_RADIOACTIVE_WASTE_BARREL)
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock), Attributes.ACTIVE_LIGHT, new AttributeStateFacing(), Attributes.COMPARATOR)
                .withCustomShape(BlockShapes.RADIOACTIVE_WASTE_BARREL)
                .withComputerSupport("largeCapRadioactiveWasteBarrel")
                .build();
    }

    private static BlockTypeTile<TileEntityExtraUniversalCable> createCable(CableTier tier, Supplier<TileEntityTypeRegistryObject<TileEntityExtraUniversalCable>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_CABLE);
    }

    private static BlockTypeTile<TileEntityExtraMechanicalPipe> createPipe(PipeTier tier, Supplier<TileEntityTypeRegistryObject<TileEntityExtraMechanicalPipe>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_PIPE);
    }

    private static BlockTypeTile<TileEntityExtraPressurizedTube> createTube(TubeTier tier, Supplier<TileEntityTypeRegistryObject<TileEntityExtraPressurizedTube>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_TUBE);
    }

    private static BlockTypeTile<TileEntityExtraLogisticalTransporter> createTransporter(TransporterTier tier, Supplier<TileEntityTypeRegistryObject<TileEntityExtraLogisticalTransporter>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_TRANSPORTER);
    }

    private static BlockTypeTile<TileEntityExtraThermodynamicConductor> createConductor(ConductorTier tier, Supplier<TileEntityTypeRegistryObject<TileEntityExtraThermodynamicConductor>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_CONDUCTOR);
    }

    private static <TILE extends TileEntityExtraTransmitter> BlockTypeTile<TILE> createTransmitter(ITier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, ILangEntry description) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, description)
                .with(new AttributeTier<>(tier))
                .build();
    }
}
