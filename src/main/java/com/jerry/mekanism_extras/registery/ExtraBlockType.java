package com.jerry.mekanism_extras.registery;

import com.jerry.mekanism_extras.common.block.storage.bin.BTier;
import com.jerry.mekanism_extras.common.block.storage.bin.ExtraTileEntityBin;
import com.jerry.mekanism_extras.common.block.storage.chemicaltank.CTTier;
import com.jerry.mekanism_extras.common.block.storage.chemicaltank.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.block.storage.energycube.ECTier;
import com.jerry.mekanism_extras.common.block.storage.energycube.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.FTTier;
import com.jerry.mekanism_extras.common.block.storage.radioactivewastebarrel.ExtraTileEntityRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.util.ExtraFloatingLong;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.EnergyCubeTier;

import java.util.function.Supplier;

public class ExtraBlockType {
    // Radioactive Waste Barrel
    public static final BlockTypeTile<ExtraTileEntityRadioactiveWasteBarrel> EXPAND_RADIOACTIVE_WASTE_BARREL = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraTileEntityTypes.EXPAND_RADIOACTIVE_WASTE_BARREL, MekanismLang.DESCRIPTION_RADIOACTIVE_WASTE_BARREL)
            .with(Attributes.ACTIVE_LIGHT, new AttributeStateFacing(), Attributes.COMPARATOR)
            .withCustomShape(BlockShapes.RADIOACTIVE_WASTE_BARREL)
            .withComputerSupport("radioactiveWasteBarrel")
            .build();
    //bin
    public static final Machine<ExtraTileEntityBin> ABSOLUTE_BIN = createBin(BTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_BIN, null);
    public static final Machine<ExtraTileEntityBin> SUPREME_BIN = createBin(BTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_BIN, null);
    public static final Machine<ExtraTileEntityBin> COSMIC_BIN = createBin(BTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_BIN, null);
    public static final Machine<ExtraTileEntityBin> INFINITE_BIN = createBin(BTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_BIN, null);
    //fluid tank
    public static final Machine<ExtraTileEntityFluidTank> ABSOLUTE_FLUID_TANK = createFluidTank(FTTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_FLUID_TANK, null);
    public static final Machine<ExtraTileEntityFluidTank> SUPREME_FLUID_TANK = createFluidTank(FTTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_FLUID_TANK, null);
    public static final Machine<ExtraTileEntityFluidTank> COSMIC_FLUID_TANK = createFluidTank(FTTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_FLUID_TANK, null);
    public static final Machine<ExtraTileEntityFluidTank> INFINITE_FLUID_TANK = createFluidTank(FTTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_FLUID_TANK, null);
    //energy cube
    public static final Machine<ExtraTileEntityEnergyCube> ABSOLUTE_ENERGY_CUBE = createEnergyCube(EnergyCubeTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_ENERGY_CUBE, null);
    public static final Machine<ExtraTileEntityEnergyCube> SUPREME_ENERGY_CUBE = createEnergyCube(EnergyCubeTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_ENERGY_CUBE, null);
    public static final Machine<ExtraTileEntityEnergyCube> COSMIC_ENERGY_CUBE = createEnergyCube(EnergyCubeTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_ENERGY_CUBE, null);
    public static final Machine<ExtraTileEntityEnergyCube> INFINITE_ENERGY_CUBE = createEnergyCube(EnergyCubeTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_ENERGY_CUBE, null);
    //chemical tank
    public static final Machine<ExtraTileEntityChemicalTank> ABSOLUTE_CHEMICAL_TANK = createChemicalTank(CTTier.ABSOLUTE, () -> ExtraTileEntityTypes.ABSOLUTE_CHEMICAL_TANK, null);
    public static final Machine<ExtraTileEntityChemicalTank> SUPREME_CHEMICAL_TANK = createChemicalTank(CTTier.SUPREME, () -> ExtraTileEntityTypes.SUPREME_CHEMICAL_TANK, null);
    public static final Machine<ExtraTileEntityChemicalTank> COSMIC_CHEMICAL_TANK = createChemicalTank(CTTier.COSMIC, () -> ExtraTileEntityTypes.COSMIC_CHEMICAL_TANK, null);
    public static final Machine<ExtraTileEntityChemicalTank> INFINITE_CHEMICAL_TANK = createChemicalTank(CTTier.INFINITE, () -> ExtraTileEntityTypes.INFINITE_CHEMICAL_TANK, null);

    private static <TILE extends ExtraTileEntityBin> Machine<TILE> createBin(BTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_BIN)
                .with(new AttributeTier<>(tier), new AttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, Attributes.AttributeSecurity.class, AttributeUpgradeSupport.class, Attributes.AttributeRedstone.class)
                .withComputerSupport(tier, "Bin")
                .build();
    }

    private static <TILE extends ExtraTileEntityFluidTank> Machine<TILE> createFluidTank(FTTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_FLUID_TANK)
                .withGui(() -> ExtraContainerTypes.FLUID_TANK)
                .withCustomShape(BlockShapes.FLUID_TANK)
                .with(new AttributeTier<>(tier), new AttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, AttributeStateFacing.class, Attributes.AttributeRedstone.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier, "FluidTank")
                .build();
    }

    private static <TILE extends ExtraTileEntityEnergyCube> Machine<TILE> createEnergyCube(EnergyCubeTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_ENERGY_CUBE)
                .withGui(() -> ExtraContainerTypes.ENERGY_CUBE)
                .withEnergyConfig(new ExtraFloatingLong(ECTier.getMaxEnergy(tier)))
                .with(new AttributeTier<>(tier), new AttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, AttributeStateActive.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier, "EnergyCube")
                .build();
    }

    private static <TILE extends ExtraTileEntityChemicalTank> Machine<TILE> createChemicalTank(CTTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        return Machine.MachineBuilder.createMachine(tile, MekanismLang.DESCRIPTION_CHEMICAL_TANK)
                .withGui(() -> ExtraContainerTypes.CHEMICAL_TANK)
                .withCustomShape(BlockShapes.CHEMICAL_TANK)
                .with(new AttributeTier<>(tier), new AttributeUpgradeable(upgradeBlock))
                .without(AttributeParticleFX.class, AttributeStateActive.class, AttributeUpgradeSupport.class)
                .withComputerSupport(tier, "ChemicalTank")
                .build();
    }
}
