package com.jerry.mekextras.common.integration.mekaf.registries;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeSupport;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraMachineBuilder;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekextras.common.integration.mekaf.content.blocktype.ExtraAdvancedFactory;
import com.jerry.mekextras.common.integration.mekaf.content.blocktype.ExtraAdvancedFactory.ExtraAdvancedFactoryBuilder;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.AttributeHasBounding;
import mekanism.common.config.MekanismConfig;
import mekanism.common.registries.MekanismContainerTypes;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tile.machine.*;

public class ExtraAdvancedFactoryBlockTypes {

    private ExtraAdvancedFactoryBlockTypes() {}

    private static final Table<ExtraFactoryTier, AdvancedFactoryType, ExtraAdvancedFactory<?>> AF_FACTORIES = HashBasedTable.create();

    // Chemical Oxidizer
    public static final ExtraFactoryMachine<TileEntityChemicalOxidizer> CHEMICAL_OXIDIZER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_OXIDIZER, MekanismLang.DESCRIPTION_CHEMICAL_OXIDIZER, AdvancedFactoryType.OXIDIZING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_OXIDIZER)
            .withSound(MekanismSounds.CHEMICAL_OXIDIZER)
            .withEnergyConfig(MekanismConfig.usage.chemicalOxidizer, MekanismConfig.storage.chemicalOxidizer)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    // Chemical Dissolution Chamber
    public static final ExtraFactoryMachine<TileEntityChemicalDissolutionChamber> CHEMICAL_DISSOLUTION_CHAMBER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_DISSOLUTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_DISSOLUTION_CHAMBER, AdvancedFactoryType.DISSOLVING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_DISSOLUTION_CHAMBER)
            .withSound(MekanismSounds.CHEMICAL_DISSOLUTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalDissolutionChamber, MekanismConfig.storage.chemicalDissolutionChamber)
            .with(ExtraAttributeUpgradeSupport.EXTRA_ADVANCED_MACHINE_UPGRADES)
            .build();

    // Chemical Infuser
    public static final ExtraFactoryMachine<TileEntityChemicalInfuser> CHEMICAL_INFUSER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_INFUSER, MekanismLang.DESCRIPTION_CHEMICAL_INFUSER, AdvancedFactoryType.CHEMICAL_INFUSING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_INFUSER)
            .withSound(MekanismSounds.CHEMICAL_INFUSER)
            .withEnergyConfig(MekanismConfig.usage.chemicalInfuser, MekanismConfig.storage.chemicalInfuser)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_NO_STACK_UPGRADES)
            .build();

    // Chemical Washer
    public static final ExtraFactoryMachine<TileEntityChemicalWasher> CHEMICAL_WASHER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_WASHER, MekanismLang.DESCRIPTION_CHEMICAL_WASHER, AdvancedFactoryType.WASHING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_WASHER)
            .withSound(MekanismSounds.CHEMICAL_WASHER)
            .withEnergyConfig(MekanismConfig.usage.chemicalWasher, MekanismConfig.storage.chemicalWasher)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_NO_STACK_UPGRADES)
            .build();

    // Pressurized Reaction Chamber
    public static final ExtraFactoryMachine<TileEntityPressurizedReactionChamber> PRESSURIZED_REACTION_CHAMBER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PRESSURIZED_REACTION_CHAMBER, MekanismLang.DESCRIPTION_PRESSURIZED_REACTION_CHAMBER, AdvancedFactoryType.PRESSURISED_REACTING)
            .withGui(() -> MekanismContainerTypes.PRESSURIZED_REACTION_CHAMBER)
            .withSound(MekanismSounds.PRESSURIZED_REACTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.pressurizedReactionBase, MekanismConfig.storage.pressurizedReactionBase)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    // Chemical Crystallizer
    public static final ExtraFactoryMachine<TileEntityChemicalCrystallizer> CHEMICAL_CRYSTALLIZER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_CRYSTALLIZER, MekanismLang.DESCRIPTION_CHEMICAL_CRYSTALLIZER, AdvancedFactoryType.CRYSTALLIZING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_CRYSTALLIZER)
            .withSound(MekanismSounds.CHEMICAL_CRYSTALLIZER)
            .withEnergyConfig(MekanismConfig.usage.chemicalCrystallizer, MekanismConfig.storage.chemicalCrystallizer)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    // Isotopic Centrifuge
    public static final ExtraFactoryMachine<TileEntityIsotopicCentrifuge> ISOTOPIC_CENTRIFUGE = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.ISOTOPIC_CENTRIFUGE, MekanismLang.DESCRIPTION_ISOTOPIC_CENTRIFUGE, AdvancedFactoryType.CENTRIFUGING)
            .withGui(() -> MekanismContainerTypes.ISOTOPIC_CENTRIFUGE)
            .withEnergyConfig(MekanismConfig.usage.isotopicCentrifuge, MekanismConfig.storage.isotopicCentrifuge)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_NO_STACK_UPGRADES)
            .withSound(MekanismSounds.ISOTOPIC_CENTRIFUGE)
            .with(AttributeHasBounding.ABOVE_ONLY)
            .build();

    // Nutritional Liquifier
    public static final ExtraFactoryMachine<TileEntityNutritionalLiquifier> NUTRITIONAL_LIQUIFIER = ExtraMachineBuilder
            .createExtraAdvancedFactoryMachine(() -> MekanismTileEntityTypes.NUTRITIONAL_LIQUIFIER, MekanismLang.DESCRIPTION_NUTRITIONAL_LIQUIFIER, AdvancedFactoryType.LIQUIFYING)
            .withGui(() -> MekanismContainerTypes.NUTRITIONAL_LIQUIFIER)
            .withEnergyConfig(MekanismConfig.usage.nutritionalLiquifier, MekanismConfig.storage.nutritionalLiquifier)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .withSound(MekanismSounds.NUTRITIONAL_LIQUIFIER)
            .build();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                AF_FACTORIES.put(tier, type, ExtraAdvancedFactoryBuilder.createAdvancedFactory(() -> ExtraAdvancedFactoryTileEntityTypes.getAdvancedFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static ExtraAdvancedFactory<?> getExtraAdvancedFactory(ExtraFactoryTier tier, AdvancedFactoryType type) {
        return AF_FACTORIES.get(tier, type);
    }
}
