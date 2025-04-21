package com.jerry.mekextras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekextras.api.ExtraUpgrade;
import com.jerry.mekextras.common.block.attribute.AdvancedAttributeUpgradeSupport;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekextras.common.content.blocktype.AdvancedFactory;
import com.jerry.mekextras.common.content.blocktype.AdvancedMachine;
import com.jerry.mekextras.common.tier.*;
import com.jerry.mekextras.common.tile.*;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityTransmitter;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityUniversalCable;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityMechanicalPipe;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityThermodynamicConductor;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityPressurizedTube;
import com.jerry.mekextras.common.tile.machine.TileEntityAdvanceElectricPump;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionPort;
import com.jerry.mekextras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekextras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
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

    private static final Table<AdvancedFactoryTier, FactoryType, AdvancedFactory<?>> FACTORIES = HashBasedTable.create();

    // Enrichment Chamber
    public static final AdvancedMachine.AdvancedFactoryMachine<TileEntityEnrichmentChamber> ENRICHMENT_CHAMBER = AdvancedMachine.AdvancedMachineBuilder
            .createAdvancedFactoryMachine(() -> MekanismTileEntityTypes.ENRICHMENT_CHAMBER, MekanismLang.DESCRIPTION_ENRICHMENT_CHAMBER, FactoryType.ENRICHING)
            .withGui(() -> MekanismContainerTypes.ENRICHMENT_CHAMBER)
            .withSound(MekanismSounds.ENRICHMENT_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.enrichmentChamber, MekanismConfig.storage.enrichmentChamber)
            .with(AttributeSideConfig.ELECTRIC_MACHINE)
            .withComputerSupport("enrichmentChamber")
            .build();
    // Crusher
    public static final AdvancedMachine.AdvancedFactoryMachine<TileEntityCrusher> CRUSHER = AdvancedMachine.AdvancedMachineBuilder
            .createAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CRUSHER, MekanismLang.DESCRIPTION_CRUSHER, FactoryType.CRUSHING)
            .withGui(() -> MekanismContainerTypes.CRUSHER)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MekanismConfig.usage.crusher, MekanismConfig.storage.crusher)
            .with(AttributeSideConfig.ELECTRIC_MACHINE)
            .withComputerSupport("crusher")
            .build();
    // Energized Smelter
    public static final AdvancedMachine.AdvancedFactoryMachine<TileEntityEnergizedSmelter> ENERGIZED_SMELTER = AdvancedMachine.AdvancedMachineBuilder
            .createAdvancedFactoryMachine(() -> MekanismTileEntityTypes.ENERGIZED_SMELTER, MekanismLang.DESCRIPTION_ENERGIZED_SMELTER, FactoryType.SMELTING)
            .withGui(() -> MekanismContainerTypes.ENERGIZED_SMELTER)
            .withSound(MekanismSounds.ENERGIZED_SMELTER)
            .withEnergyConfig(MekanismConfig.usage.energizedSmelter, MekanismConfig.storage.energizedSmelter)
            .with(AttributeSideConfig.ELECTRIC_MACHINE)
            .withComputerSupport("energizedSmelter")
            .build();
    // Precision Sawmill
    public static final AdvancedMachine.AdvancedFactoryMachine<TileEntityPrecisionSawmill> PRECISION_SAWMILL = AdvancedMachine.AdvancedMachineBuilder
            .createAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PRECISION_SAWMILL, MekanismLang.DESCRIPTION_PRECISION_SAWMILL, FactoryType.SAWING)
            .withGui(() -> MekanismContainerTypes.PRECISION_SAWMILL)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MekanismConfig.usage.precisionSawmill, MekanismConfig.storage.precisionSawmill)
            .withSideConfig(TransmissionType.ITEM, TransmissionType.ENERGY)
            .withComputerSupport("precisionSawmill")
            .build();
    // Osmium Compressor
    public static final AdvancedMachine.AdvancedFactoryMachine<TileEntityOsmiumCompressor> OSMIUM_COMPRESSOR = AdvancedMachine.AdvancedMachineBuilder
            .createAdvancedFactoryMachine(() -> MekanismTileEntityTypes.OSMIUM_COMPRESSOR, MekanismLang.DESCRIPTION_OSMIUM_COMPRESSOR, FactoryType.COMPRESSING)
            .withGui(() -> MekanismContainerTypes.OSMIUM_COMPRESSOR)
            .withSound(MekanismSounds.OSMIUM_COMPRESSOR)
            .withEnergyConfig(MekanismConfig.usage.osmiumCompressor, MekanismConfig.storage.osmiumCompressor)
            .with(AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE)
            .withComputerSupport("osmiumCompressor")
            .build();
    // Combiner
    public static final AdvancedMachine.AdvancedFactoryMachine<TileEntityCombiner> COMBINER = AdvancedMachine.AdvancedMachineBuilder
            .createAdvancedFactoryMachine(() -> MekanismTileEntityTypes.COMBINER, MekanismLang.DESCRIPTION_COMBINER, FactoryType.COMBINING)
            .withGui(() -> MekanismContainerTypes.COMBINER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(MekanismConfig.usage.combiner, MekanismConfig.storage.combiner)
            .withSideConfig(TransmissionType.ITEM, TransmissionType.ENERGY)
            .withComputerSupport("combiner")
            .build();
    // Metallurgic Infuser
    public static final AdvancedMachine.AdvancedFactoryMachine<TileEntityMetallurgicInfuser> METALLURGIC_INFUSER = AdvancedMachine.AdvancedMachineBuilder
            .createAdvancedFactoryMachine(() -> MekanismTileEntityTypes.METALLURGIC_INFUSER, MekanismLang.DESCRIPTION_METALLURGIC_INFUSER, FactoryType.INFUSING)
            .withGui(() -> MekanismContainerTypes.METALLURGIC_INFUSER)
            .withSound(MekanismSounds.METALLURGIC_INFUSER)
            .withEnergyConfig(MekanismConfig.usage.metallurgicInfuser, MekanismConfig.storage.metallurgicInfuser)
            .with(AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE)
            .withCustomShape(BlockShapes.METALLURGIC_INFUSER)
            .withComputerSupport("metallurgicInfuser")
            .build();
    // Purification Chamber
    public static final AdvancedMachine.AdvancedFactoryMachine<TileEntityPurificationChamber> PURIFICATION_CHAMBER = AdvancedMachine.AdvancedMachineBuilder
            .createAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PURIFICATION_CHAMBER, MekanismLang.DESCRIPTION_PURIFICATION_CHAMBER, FactoryType.PURIFYING)
            .withGui(() -> MekanismContainerTypes.PURIFICATION_CHAMBER)
            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.purificationChamber, MekanismConfig.storage.purificationChamber)
            .with(AdvancedAttributeUpgradeSupport.ADVANCED_ADVANCED_MACHINE_UPGRADES)
            .with(AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE)
            .withComputerSupport("purificationChamber")
            .build();
    // Chemical Injection Chamber
    public static final AdvancedMachine.AdvancedFactoryMachine<TileEntityChemicalInjectionChamber> CHEMICAL_INJECTION_CHAMBER = AdvancedMachine.AdvancedMachineBuilder
            .createAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_INJECTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_INJECTION_CHAMBER, FactoryType.INJECTING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_INJECTION_CHAMBER)
            .withSound(MekanismSounds.CHEMICAL_INJECTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalInjectionChamber, MekanismConfig.storage.chemicalInjectionChamber)
            .with(AdvancedAttributeUpgradeSupport.ADVANCED_ADVANCED_MACHINE_UPGRADES)
            .with(AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE)
            .withComputerSupport("chemicalInjectionChamber")
            .build();

    // Electric Pump
    public static final Machine<TileEntityAdvanceElectricPump> ADVANCE_ELECTRIC_PUMP = Machine.MachineBuilder
            .createMachine(() -> ExtraTileEntityTypes.ADVANCE_ELECTRIC_PUMP, MekanismLang.DESCRIPTION_ELECTRIC_PUMP)
            .withGui(() -> ExtraContainerTypes.ADVANCE_ELECTRIC_PUMP)
            .withEnergyConfig(MekanismConfig.usage.electricPump, MekanismConfig.storage.electricPump)
            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.FILTER, ExtraUpgrade.IONIC_MEMBRANE)
            .withCustomShape(BlockShapes.ELECTRIC_PUMP)
            .withComputerSupport("electricPump")
            .replace(Attributes.ACTIVE)
            .build();

    // Bins
    public static final Machine<ExtraTileEntityBin> ABSOLUTE_BIN = createBin(BTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_BIN, () -> ExtraBlocks.SUPREME_BIN);
    public static final Machine<ExtraTileEntityBin> SUPREME_BIN = createBin(BTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_BIN, () -> ExtraBlocks.COSMIC_BIN);
    public static final Machine<ExtraTileEntityBin> COSMIC_BIN = createBin(BTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_BIN, () -> ExtraBlocks.INFINITE_BIN);
    public static final Machine<ExtraTileEntityBin> INFINITE_BIN = createBin(BTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_BIN, null);

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
    public static final BlockTypeTile<ExtraTileEntityInductionCell> ABSOLUTE_INDUCTION_CELL = createInductionCell(ICTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_INDUCTION_CELL);
    public static final BlockTypeTile<ExtraTileEntityInductionCell> SUPREME_INDUCTION_CELL = createInductionCell(ICTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_INDUCTION_CELL);
    public static final BlockTypeTile<ExtraTileEntityInductionCell> COSMIC_INDUCTION_CELL = createInductionCell(ICTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_INDUCTION_CELL);
    public static final BlockTypeTile<ExtraTileEntityInductionCell> INFINITE_INDUCTION_CELL = createInductionCell(ICTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_INDUCTION_CELL);

    // Induction Provider
    public static final BlockTypeTile<ExtraTileEntityInductionProvider> ABSOLUTE_INDUCTION_PROVIDER = createInductionProvider(IPTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_INDUCTION_PROVIDER);
    public static final BlockTypeTile<ExtraTileEntityInductionProvider> SUPREME_INDUCTION_PROVIDER = createInductionProvider(IPTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_INDUCTION_PROVIDER);
    public static final BlockTypeTile<ExtraTileEntityInductionProvider> COSMIC_INDUCTION_PROVIDER = createInductionProvider(IPTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_INDUCTION_PROVIDER);
    public static final BlockTypeTile<ExtraTileEntityInductionProvider> INFINITE_INDUCTION_PROVIDER = createInductionProvider(IPTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_INDUCTION_PROVIDER);

    // Energy Cubes
    public static final Machine<ExtraTileEntityEnergyCube> ABSOLUTE_ENERGY_CUBE = createEnergyCube(ECTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_ENERGY_CUBE, () -> ExtraBlocks.SUPREME_ENERGY_CUBE);
    public static final Machine<ExtraTileEntityEnergyCube> SUPREME_ENERGY_CUBE = createEnergyCube(ECTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_ENERGY_CUBE, () -> ExtraBlocks.COSMIC_ENERGY_CUBE);
    public static final Machine<ExtraTileEntityEnergyCube> COSMIC_ENERGY_CUBE = createEnergyCube(ECTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_ENERGY_CUBE, () -> ExtraBlocks.INFINITE_ENERGY_CUBE);
    public static final Machine<ExtraTileEntityEnergyCube> INFINITE_ENERGY_CUBE = createEnergyCube(ECTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_ENERGY_CUBE, null);

    // Fluid Tanks
    public static final Machine<ExtraTileEntityFluidTank> ABSOLUTE_FLUID_TANK = createFluidTank(FTTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_FLUID_TANK, () -> ExtraBlocks.SUPREME_FLUID_TANK);
    public static final Machine<ExtraTileEntityFluidTank> SUPREME_FLUID_TANK = createFluidTank(FTTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_FLUID_TANK, () -> ExtraBlocks.COSMIC_FLUID_TANK);
    public static final Machine<ExtraTileEntityFluidTank> COSMIC_FLUID_TANK = createFluidTank(FTTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_FLUID_TANK, () -> ExtraBlocks.INFINITE_FLUID_TANK);
    public static final Machine<ExtraTileEntityFluidTank> INFINITE_FLUID_TANK = createFluidTank(FTTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_FLUID_TANK, null);

    // Chemical Tanks
    public static final Machine<ExtraTileEntityChemicalTank> ABSOLUTE_CHEMICAL_TANK = createChemicalTank(CTTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_CHEMICAL_TANK, () -> ExtraBlocks.SUPREME_CHEMICAL_TANK);
    public static final Machine<ExtraTileEntityChemicalTank> SUPREME_CHEMICAL_TANK = createChemicalTank(CTTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_CHEMICAL_TANK, () -> ExtraBlocks.COSMIC_CHEMICAL_TANK);
    public static final Machine<ExtraTileEntityChemicalTank> COSMIC_CHEMICAL_TANK = createChemicalTank(CTTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_CHEMICAL_TANK, () -> ExtraBlocks.INFINITE_CHEMICAL_TANK);
    public static final Machine<ExtraTileEntityChemicalTank> INFINITE_CHEMICAL_TANK = createChemicalTank(CTTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_CHEMICAL_TANK, null);

    public static final BlockTypeTile<TileEntityLargeCapRadioactiveWasteBarrel> ABSOLUTE_RADIOACTIVE_WASTE_BARREL = createWasteBarrel(RWBTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_RADIOACTIVE_WASTE_BARREL, () -> ExtraBlocks.SUPREME_RADIOACTIVE_WASTE_BARREL);
    public static final BlockTypeTile<TileEntityLargeCapRadioactiveWasteBarrel> SUPREME_RADIOACTIVE_WASTE_BARREL = createWasteBarrel(RWBTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_RADIOACTIVE_WASTE_BARREL, () -> ExtraBlocks.COSMIC_RADIOACTIVE_WASTE_BARREL);
    public static final BlockTypeTile<TileEntityLargeCapRadioactiveWasteBarrel> COSMIC_RADIOACTIVE_WASTE_BARREL = createWasteBarrel(RWBTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_RADIOACTIVE_WASTE_BARREL, () -> ExtraBlocks.INFINITE_RADIOACTIVE_WASTE_BARREL);
    public static final BlockTypeTile<TileEntityLargeCapRadioactiveWasteBarrel> INFINITE_RADIOACTIVE_WASTE_BARREL = createWasteBarrel(RWBTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_RADIOACTIVE_WASTE_BARREL, null);

    //Transmitters
    public static final BlockTypeTile<ExtraTileEntityUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = createCable(CableTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_UNIVERSAL_CABLE);
    public static final BlockTypeTile<ExtraTileEntityUniversalCable> SUPREME_UNIVERSAL_CABLE = createCable(CableTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_UNIVERSAL_CABLE);
    public static final BlockTypeTile<ExtraTileEntityUniversalCable> COSMIC_UNIVERSAL_CABLE = createCable(CableTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_UNIVERSAL_CABLE);
    public static final BlockTypeTile<ExtraTileEntityUniversalCable> INFINITE_UNIVERSAL_CABLE = createCable(CableTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_UNIVERSAL_CABLE);

    public static final BlockTypeTile<ExtraTileEntityMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = createPipe(PipeTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_MECHANICAL_PIPE);
    public static final BlockTypeTile<ExtraTileEntityMechanicalPipe> SUPREME_MECHANICAL_PIPE = createPipe(PipeTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_MECHANICAL_PIPE);
    public static final BlockTypeTile<ExtraTileEntityMechanicalPipe> COSMIC_MECHANICAL_PIPE = createPipe(PipeTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_MECHANICAL_PIPE);
    public static final BlockTypeTile<ExtraTileEntityMechanicalPipe> INFINITE_MECHANICAL_PIPE = createPipe(PipeTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_MECHANICAL_PIPE);

    public static final BlockTypeTile<ExtraTileEntityPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = createTube(TubeTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_PRESSURIZED_TUBE);
    public static final BlockTypeTile<ExtraTileEntityPressurizedTube> SUPREME_PRESSURIZED_TUBE = createTube(TubeTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_PRESSURIZED_TUBE);
    public static final BlockTypeTile<ExtraTileEntityPressurizedTube> COSMIC_PRESSURIZED_TUBE = createTube(TubeTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_PRESSURIZED_TUBE);
    public static final BlockTypeTile<ExtraTileEntityPressurizedTube> INFINITE_PRESSURIZED_TUBE = createTube(TubeTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_PRESSURIZED_TUBE);

    public static final BlockTypeTile<ExtraTileEntityLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER);
    public static final BlockTypeTile<ExtraTileEntityLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_LOGISTICAL_TRANSPORTER);
    public static final BlockTypeTile<ExtraTileEntityLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_LOGISTICAL_TRANSPORTER);
    public static final BlockTypeTile<ExtraTileEntityLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_LOGISTICAL_TRANSPORTER);

    public static final BlockTypeTile<ExtraTileEntityThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR);
    public static final BlockTypeTile<ExtraTileEntityThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_THERMODYNAMIC_CONDUCTOR);
    public static final BlockTypeTile<ExtraTileEntityThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_THERMODYNAMIC_CONDUCTOR);
    public static final BlockTypeTile<ExtraTileEntityThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_THERMODYNAMIC_CONDUCTOR);

    static {
        for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                FACTORIES.put(tier, type, AdvancedFactory.AdvancedFactoryBuilder.createFactory(() -> ExtraTileEntityTypes.getAdvancedFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static AdvancedFactory<?> getAdvancedFactory(AdvancedFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    private static <TILE extends ExtraTileEntityInductionCell> BlockTypeTile<TILE> createInductionCell(ICTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_INDUCTION_CELL)
                .withEnergyConfig(tier::getMaxEnergy)
                .with(new ExtraAttributeTier<>(tier))
                .internalMultiblock()
                .build();
    }

    private static <TILE extends ExtraTileEntityInductionProvider> BlockTypeTile<TILE> createInductionProvider(IPTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_INDUCTION_PROVIDER)
                .with(new ExtraAttributeTier<>(tier))
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

    private static <TILE extends ExtraTileEntityEnergyCube> Machine<TILE> createEnergyCube(ECTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_ENERGY_CUBE)
                .withGui(() -> ExtraContainerTypes.EXTRA_ENERGY_CUBE)
                .withEnergyConfig(tier::getMaxEnergy)
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock), new AttributeStateFacing(BlockStateProperties.FACING))
                .withSideConfig(TransmissionType.ENERGY, TransmissionType.ITEM)
                .without(AttributeParticleFX.class, AttributeStateActive.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier.getAdvanceTier().getLowerName() + "EnergyCube")
                .build();
    }

    private static <TILE extends ExtraTileEntityFluidTank> Machine<TILE> createFluidTank(FTTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_FLUID_TANK)
                .withGui(() -> ExtraContainerTypes.EXTRA_FLUID_TANK)
                .withCustomShape(BlockShapes.FLUID_TANK)
                .with(new ExtraAttributeTier<>(tier), new ExtraAttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, AttributeStateFacing.class, Attributes.AttributeRedstone.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier.getAdvanceTier().getLowerName() +  "FluidTank")
                .build();
    }

    private static <TILE extends ExtraTileEntityChemicalTank> Machine<TILE> createChemicalTank(CTTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
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

    private static BlockTypeTile<ExtraTileEntityUniversalCable> createCable(CableTier tier, Supplier<TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_CABLE);
    }

    private static BlockTypeTile<ExtraTileEntityMechanicalPipe> createPipe(PipeTier tier, Supplier<TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_PIPE);
    }

    private static BlockTypeTile<ExtraTileEntityPressurizedTube> createTube(TubeTier tier, Supplier<TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_TUBE);
    }

    private static BlockTypeTile<ExtraTileEntityLogisticalTransporter> createTransporter(TransporterTier tier, Supplier<TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_TRANSPORTER);
    }

    private static BlockTypeTile<ExtraTileEntityThermodynamicConductor> createConductor(ConductorTier tier, Supplier<TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_CONDUCTOR);
    }

    private static <TILE extends ExtraTileEntityTransmitter> BlockTypeTile<TILE> createTransmitter(ITier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, ILangEntry description) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, description)
                .with(new AttributeTier<>(tier))
                .build();
    }
}
