package com.jerry.mekanism_extras.common.integration.mekaf.registries;

import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine.ExtraMachineBuilder;
import com.jerry.mekanism_extras.common.integration.mekaf.content.blocktype.ExtraAdvancedFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.content.blocktype.ExtraAdvancedFactory.ExtraAdvancedFactoryBuilder;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tile.machine.TileEntityChemicalCrystallizer;
import mekanism.common.tile.machine.TileEntityChemicalDissolutionChamber;
import mekanism.common.tile.machine.TileEntityChemicalOxidizer;
import mekanism.common.tile.machine.TileEntityChemicalWasher;
import mekanism.common.tile.machine.TileEntityIsotopicCentrifuge;
import mekanism.common.tile.machine.TileEntityNutritionalLiquifier;
import mekanism.common.tile.machine.TileEntityPaintingMachine;
import mekanism.common.tile.machine.TileEntityPigmentExtractor;
import mekanism.common.tile.machine.TileEntityPressurizedReactionChamber;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;

import java.util.EnumSet;

public class ExtraAdvancedFactoryBlockTypes {

    private ExtraAdvancedFactoryBlockTypes() {}

    private static final Table<ExtraFactoryTier, AdvancedFactoryType, ExtraAdvancedFactory<?>> FACTORIES = HashBasedTable.create();

    public static final ExtraFactoryMachine<TileEntityChemicalOxidizer> CHEMICAL_OXIDIZER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_OXIDIZER, MekanismLang.DESCRIPTION_CHEMICAL_OXIDIZER, AdvancedFactoryType.OXIDIZING)
            .withSound(MekanismSounds.CHEMICAL_OXIDIZER)
            .withEnergyConfig(MekanismConfig.usage.oxidationChamber, MekanismConfig.storage.oxidationChamber)
            .build();

    public static final ExtraFactoryMachine<TileEntityChemicalDissolutionChamber> CHEMICAL_DISSOLUTION_CHAMBER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_DISSOLUTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_DISSOLUTION_CHAMBER, AdvancedFactoryType.DISSOLVING)
            .withSound(MekanismSounds.CHEMICAL_DISSOLUTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalDissolutionChamber, MekanismConfig.storage.chemicalDissolutionChamber)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS))
            .build();

    public static final ExtraFactoryMachine<TileEntityChemicalWasher> CHEMICAL_WASHER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_WASHER, MekanismLang.DESCRIPTION_CHEMICAL_WASHER, AdvancedFactoryType.WASHING)
            .withSound(MekanismSounds.CHEMICAL_WASHER)
            .withEnergyConfig(MekanismConfig.usage.chemicalWasher, MekanismConfig.storage.chemicalWasher)
            .build();

    public static final ExtraFactoryMachine<TileEntityChemicalCrystallizer> CHEMICAL_CRYSTALLIZER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_CRYSTALLIZER, MekanismLang.DESCRIPTION_CHEMICAL_CRYSTALLIZER, AdvancedFactoryType.CRYSTALLIZING)
            .withSound(MekanismSounds.CHEMICAL_CRYSTALLIZER)
            .withEnergyConfig(MekanismConfig.usage.chemicalCrystallizer, MekanismConfig.storage.chemicalCrystallizer)
            .build();

    public static final ExtraFactoryMachine<TileEntityPressurizedReactionChamber> PRESSURIZED_REACTION_CHAMBER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PRESSURIZED_REACTION_CHAMBER, MekanismLang.DESCRIPTION_PRESSURIZED_REACTION_CHAMBER, AdvancedFactoryType.PRESSURISED_REACTING)
            .withSound(MekanismSounds.PRESSURIZED_REACTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.pressurizedReactionBase, MekanismConfig.storage.pressurizedReactionBase)
            .build();

    public static final ExtraFactoryMachine<TileEntityIsotopicCentrifuge> ISOTOPIC_CENTRIFUGE = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.ISOTOPIC_CENTRIFUGE, MekanismLang.DESCRIPTION_ISOTOPIC_CENTRIFUGE, AdvancedFactoryType.CENTRIFUGING)
            .withEnergyConfig(MekanismConfig.usage.isotopicCentrifuge, MekanismConfig.storage.isotopicCentrifuge)
            .withSound(MekanismSounds.ISOTOPIC_CENTRIFUGE)
            .build();

    public static final ExtraFactoryMachine<TileEntityNutritionalLiquifier> NUTRITIONAL_LIQUIFIER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.NUTRITIONAL_LIQUIFIER, MekanismLang.DESCRIPTION_NUTRITIONAL_LIQUIFIER, AdvancedFactoryType.LIQUIFYING)
            .withEnergyConfig(MekanismConfig.usage.nutritionalLiquifier, MekanismConfig.storage.nutritionalLiquifier)
            .withSound(MekanismSounds.NUTRITIONAL_LIQUIFIER)
            .build();

    public static final ExtraFactoryMachine<TileEntityPigmentExtractor> PIGMENT_EXTRACTOR = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PIGMENT_EXTRACTOR, MekanismLang.DESCRIPTION_PIGMENT_EXTRACTOR, AdvancedFactoryType.PIGMENT_EXTRACTING)
            .withSound(MekanismSounds.PIGMENT_EXTRACTOR)
            .withEnergyConfig(MekanismConfig.usage.pigmentExtractor, MekanismConfig.storage.pigmentExtractor)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING))
            .build();

    public static final ExtraFactoryMachine<TileEntityPaintingMachine> PAINTING_MACHINE = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PAINTING_MACHINE, MekanismLang.DESCRIPTION_PAINTING_MACHINE, AdvancedFactoryType.PAINTING)
            .withSound(MekanismSounds.PAINTING_MACHINE)
            .withEnergyConfig(MekanismConfig.usage.paintingMachine, MekanismConfig.storage.paintingMachine)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING))
            .build();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                FACTORIES.put(tier, type, ExtraAdvancedFactoryBuilder.createAdvancedFactory(() -> ExtraAdvancedFactoryTileEntityTypes.getExtraAdvancedFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static ExtraAdvancedFactory<?> getExtraAdvancedFactory(ExtraFactoryTier tier, AdvancedFactoryType type) {
        return FACTORIES.get(tier, type);
    }
}
