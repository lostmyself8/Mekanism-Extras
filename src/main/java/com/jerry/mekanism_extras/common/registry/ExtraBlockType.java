package com.jerry.mekanism_extras.common.registry;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.config.LoadConfig;
import com.jerry.mekanism_extras.common.tile.machine.ExtraTileEntityElectricPump;
import com.jerry.mekanism_extras.common.tier.BTier;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityBin;
import com.jerry.mekanism_extras.common.tier.CTTier;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.tier.ECTier;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.tier.FTTier;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.common.tile.multiblock.*;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekanism_extras.common.tier.ICTier;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import com.jerry.mekanism_extras.common.tier.IPTier;
import com.jerry.mekanism_extras.common.util.ExtraFloatingLong;
import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;

import java.util.EnumSet;
import java.util.function.Supplier;

public class ExtraBlockType {
    //radioactive waste barrel
    public static final BlockTypeTile<ExtraTileEntityRadioactiveWasteBarrel> EXPAND_RADIOACTIVE_WASTE_BARREL = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraTileEntityTypes.EXPAND_RADIOACTIVE_WASTE_BARREL, MekanismLang.DESCRIPTION_RADIOACTIVE_WASTE_BARREL)
            .with(Attributes.ACTIVE_LIGHT, new AttributeStateFacing(), Attributes.COMPARATOR)
            .withCustomShape(BlockShapes.RADIOACTIVE_WASTE_BARREL)
            .withComputerSupport("radioactiveWasteBarrel")
            .build();
    //hard induction casing
    public static final BlockTypeTile<ExtraTileEntityInductionCasing> INDUCTION_CASING = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraTileEntityTypes.HARD_INDUCTION_CASING, MekanismLang.DESCRIPTION_INDUCTION_CASING)
            .withGui(() -> ExtraContainerTypes.INDUCTION_MATRIX, MekanismLang.MATRIX)
            .with(Attributes.INVENTORY, Attributes.COMPARATOR)
            .externalMultiblock()
            .build();
    //hard induction port
    public static final BlockTypeTile<ExtraTileEntityInductionPort> INDUCTION_PORT = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraTileEntityTypes.HARD_INDUCTION_PORT, MekanismLang.DESCRIPTION_INDUCTION_PORT)
            .withGui(() -> ExtraContainerTypes.INDUCTION_MATRIX, MekanismLang.MATRIX)
            .with(Attributes.INVENTORY, Attributes.COMPARATOR, Attributes.ACTIVE)
            .externalMultiblock()
            .withComputerSupport("inductionPort")
            .build();
    //bin
    public static final Machine<ExtraTileEntityBin> ABSOLUTE_BIN = createBin(BTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_BIN, () -> ExtraBlock.SUPREME_BIN);
    public static final Machine<ExtraTileEntityBin> SUPREME_BIN = createBin(BTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_BIN, () -> ExtraBlock.COSMIC_BIN);
    public static final Machine<ExtraTileEntityBin> COSMIC_BIN = createBin(BTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_BIN, () -> ExtraBlock.INFINITE_BIN);
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
    //fluid tank
    public static final Machine<ExtraTileEntityFluidTank> ABSOLUTE_FLUID_TANK = createFluidTank(FTTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_FLUID_TANK, () -> ExtraBlock.SUPREME_FLUID_TANK);
    public static final Machine<ExtraTileEntityFluidTank> SUPREME_FLUID_TANK = createFluidTank(FTTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_FLUID_TANK, () -> ExtraBlock.COSMIC_FLUID_TANK);
    public static final Machine<ExtraTileEntityFluidTank> COSMIC_FLUID_TANK = createFluidTank(FTTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_FLUID_TANK, () -> ExtraBlock.INFINITE_FLUID_TANK);
    public static final Machine<ExtraTileEntityFluidTank> INFINITE_FLUID_TANK = createFluidTank(FTTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_FLUID_TANK, null);
    //energy cube
    public static final Machine<ExtraTileEntityEnergyCube> ABSOLUTE_ENERGY_CUBE = createEnergyCube(ECTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_ENERGY_CUBE, () -> ExtraBlock.SUPREME_ENERGY_CUBE);
    public static final Machine<ExtraTileEntityEnergyCube> SUPREME_ENERGY_CUBE = createEnergyCube(ECTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_ENERGY_CUBE, () -> ExtraBlock.COSMIC_ENERGY_CUBE);
    public static final Machine<ExtraTileEntityEnergyCube> COSMIC_ENERGY_CUBE = createEnergyCube(ECTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_ENERGY_CUBE, () -> ExtraBlock.INFINITE_ENERGY_CUBE);
    public static final Machine<ExtraTileEntityEnergyCube> INFINITE_ENERGY_CUBE = createEnergyCube(ECTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_ENERGY_CUBE, null);
    //chemical tank
    public static final Machine<ExtraTileEntityChemicalTank> ABSOLUTE_CHEMICAL_TANK = createChemicalTank(CTTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_CHEMICAL_TANK, () -> ExtraBlock.SUPREME_CHEMICAL_TANK);
    public static final Machine<ExtraTileEntityChemicalTank> SUPREME_CHEMICAL_TANK = createChemicalTank(CTTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_CHEMICAL_TANK, () -> ExtraBlock.COSMIC_CHEMICAL_TANK);
    public static final Machine<ExtraTileEntityChemicalTank> COSMIC_CHEMICAL_TANK = createChemicalTank(CTTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_CHEMICAL_TANK, () -> ExtraBlock.INFINITE_CHEMICAL_TANK);
    public static final Machine<ExtraTileEntityChemicalTank> INFINITE_CHEMICAL_TANK = createChemicalTank(CTTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_CHEMICAL_TANK, null);

    // Electric Pump
    public static final Machine<ExtraTileEntityElectricPump> ADVANCE_ELECTRIC_PUMP = Machine.MachineBuilder
            .createMachine(() -> ExtraTileEntityTypes.ADVANCE_ELECTRIC_PUMP, MekanismLang.DESCRIPTION_ELECTRIC_PUMP)
            .withGui(() -> ExtraContainerTypes.ADVANCE_ELECTRIC_PUMP)
            .withEnergyConfig(LoadConfig.extraUsage.advanceElectricPump, LoadConfig.extraStorage.advanceElectricPump)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.FILTER))
            .withCustomShape(BlockShapes.ELECTRIC_PUMP)
            .withComputerSupport("electricPump")
            .replace(Attributes.ACTIVE)
            .build();

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
                .withComputerSupport(tier.getAdvanceTier().getLowerName() +  "EnergyCube")
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
