package com.jerry.mekanism_extras.common.registry;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.tier.*;
import com.jerry.mekanism_extras.common.tile.*;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityTransmitter;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityUniversalCable;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityMechanicalPipe;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityThermodynamicConductor;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityPressurizedTube;
import com.jerry.mekanism_extras.common.tile.machine.TileEntityAdvanceElectricPump;
import com.jerry.mekanism_extras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import com.jerry.mekanism_extras.common.tile.multiblock.TileEntityReinforcedInductionPort;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import mekanism.api.Upgrade;
import mekanism.api.text.ILangEntry;
import mekanism.api.tier.ITier;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.Supplier;

public class ExtraBlockTypes {
    // Electric Pump
    public static final Machine<TileEntityAdvanceElectricPump> ADVANCE_ELECTRIC_PUMP = Machine.MachineBuilder
            .createMachine(() -> ExtraTileEntityTypes.ADVANCE_ELECTRIC_PUMP, MekanismLang.DESCRIPTION_ELECTRIC_PUMP)
            .withGui(() -> ExtraContainerTypes.ADVANCE_ELECTRIC_PUMP)
            .withEnergyConfig(MekanismConfig.usage.electricPump, MekanismConfig.storage.electricPump)
            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.FILTER)
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
