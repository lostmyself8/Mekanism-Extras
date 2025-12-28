package com.jerry.mekextras.common.config;

import com.jerry.mekextras.common.config.ExtraConfigTranslations.AdvancedTierTranslations;
import com.jerry.mekextras.common.tier.*;
import com.jerry.mekextras.common.util.ExtraEnumUtils;

import mekanism.api.heat.HeatAPI;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.MekanismConfigTranslations;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Locale;

public class ExtraTierConfig extends BaseMekanismConfig {

    private final ModConfigSpec configSpec;

    public final CachedLongValue absoluteUniversalCableCapacity;
    public final CachedLongValue supremeUniversalCableCapacity;
    public final CachedLongValue cosmicUniversalCableCapacity;
    public final CachedLongValue infiniteUniversalCableCapacity;

    public final CachedLongValue absoluteMechanicalPipeCapacity;
    public final CachedLongValue absoluteMechanicalPipePullAmount;
    public final CachedLongValue supremeMechanicalPipeCapacity;
    public final CachedLongValue supremeMechanicalPipePullAmount;
    public final CachedLongValue cosmicMechanicalPipeCapacity;
    public final CachedLongValue cosmicMechanicalPipePullAmount;
    public final CachedLongValue infiniteMechanicalPipeCapacity;
    public final CachedLongValue infiniteMechanicalPipePullAmount;

    public final CachedLongValue absolutePressurizedTubeCapacity;
    public final CachedLongValue absolutePressurizedTubePullAmount;
    public final CachedLongValue supremePressurizedTubeCapacity;
    public final CachedLongValue supremePressurizedTubePullAmount;
    public final CachedLongValue cosmicPressurizedTubeCapacity;
    public final CachedLongValue cosmicPressurizedTubePullAmount;
    public final CachedLongValue infinitePressurizedTubeCapacity;
    public final CachedLongValue infinitePressurizedTubePullAmount;

    public final CachedLongValue absoluteLogisticalTransporterSpeed;
    public final CachedLongValue absoluteLogisticalTransporterPullAmount;
    public final CachedLongValue supremeLogisticalTransporterSpeed;
    public final CachedLongValue supremeLogisticalTransporterPullAmount;
    public final CachedLongValue cosmicLogisticalTransporterSpeed;
    public final CachedLongValue cosmicLogisticalTransporterPullAmount;
    public final CachedLongValue infiniteLogisticalTransporterSpeed;
    public final CachedLongValue infiniteLogisticalTransporterPullAmount;

    public final CachedLongValue absoluteThermodynamicConductorConduction;
    public final CachedLongValue absoluteThermodynamicConductorCapacity;
    public final CachedLongValue absoluteThermodynamicConductorInsulation;
    public final CachedLongValue supremeThermodynamicConductorConduction;
    public final CachedLongValue supremeThermodynamicConductorCapacity;
    public final CachedLongValue supremeThermodynamicConductorInsulation;
    public final CachedLongValue cosmicThermodynamicConductorConduction;
    public final CachedLongValue cosmicThermodynamicConductorCapacity;
    public final CachedLongValue cosmicThermodynamicConductorInsulation;
    public final CachedLongValue infiniteThermodynamicConductorConduction;
    public final CachedLongValue infiniteThermodynamicConductorCapacity;
    public final CachedLongValue infiniteThermodynamicConductorInsulation;

    ExtraTierConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        addQIODriverCategory(builder);
        // Blocks
        addStoragesCategory(builder);
        // Transmitters
        MekanismConfigTranslations.TIER_TRANSMITTERS.applyToBuilder(builder).push("transmitters");
        MekanismConfigTranslations.TIER_TRANSMITTERS_ENERGY.applyToBuilder(builder).push("energy");
        this.absoluteUniversalCableCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_UNIVERSAL_CABLE_CAPACITY, "absoluteCapacity", 65536000L, 1, Long.MAX_VALUE);
        this.supremeUniversalCableCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_UNIVERSAL_CABLE_CAPACITY, "supremeCapacity", 524288000L, 1, Long.MAX_VALUE);
        this.cosmicUniversalCableCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_UNIVERSAL_CABLE_CAPACITY, "cosmicCapacity", 4194304000L, 1, Long.MAX_VALUE);
        this.infiniteUniversalCableCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_UNIVERSAL_CABLE_CAPACITY, "infiniteCapacity", 33554432000L, 1, Long.MAX_VALUE);
        builder.pop();

        MekanismConfigTranslations.TIER_TRANSMITTERS_FLUID.applyToBuilder(builder).push("fluid");
        this.absoluteMechanicalPipeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_MECHANICAL_PIPE_CAPACITY, "absoluteCapacity", 1_024_000L, 1, Long.MAX_VALUE);
        this.absoluteMechanicalPipePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_MECHANICAL_PIPE_PULL_AMOUNT, "absolutePullAmount", 256_000, 1, Integer.MAX_VALUE);
        this.supremeMechanicalPipeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_MECHANICAL_PIPE_CAPACITY, "supremeCapacity", 8_192_000L, 1, Long.MAX_VALUE);
        this.supremeMechanicalPipePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_MECHANICAL_PIPE_PULL_AMOUNT, "supremePullAmount", 2_048_000, 1, Integer.MAX_VALUE);
        this.cosmicMechanicalPipeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_MECHANICAL_PIPE_CAPACITY, "cosmicCapacity", 65_536_000L, 1, Long.MAX_VALUE);
        this.cosmicMechanicalPipePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_MECHANICAL_PIPE_PULL_AMOUNT, "cosmicPullAmount", 16_384_000, 1, Integer.MAX_VALUE);
        this.infiniteMechanicalPipeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_MECHANICAL_PIPE_CAPACITY, "infiniteCapacity", 524_288_000L, 1, Long.MAX_VALUE);
        this.infiniteMechanicalPipePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_MECHANICAL_PIPE_PULL_AMOUNT, "infinitePullAmount", 131_072_000, 1, Integer.MAX_VALUE);
        builder.pop();

        MekanismConfigTranslations.TIER_TRANSMITTERS_CHEMICAL.applyToBuilder(builder).push("chemical");
        this.absolutePressurizedTubeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_PRESSURIZED_TUBE_CAPACITY, "absoluteCapacity", 8_192_000L, 1, Long.MAX_VALUE);
        this.absolutePressurizedTubePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_PRESSURIZED_TUBE_PULL_AMOUNT, "absolutePullAmount", 2_048_000L, 1, Long.MAX_VALUE);
        this.supremePressurizedTubeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_PRESSURIZED_TUBE_CAPACITY, "supremeCapacity", 65_536_000L, 1, Long.MAX_VALUE);
        this.supremePressurizedTubePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_PRESSURIZED_TUBE_PULL_AMOUNT, "supremePullAmount", 16_384_000L, 1, Long.MAX_VALUE);
        this.cosmicPressurizedTubeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_PRESSURIZED_TUBE_CAPACITY, "cosmicCapacity", 524_288_000L, 1, Long.MAX_VALUE);
        this.cosmicPressurizedTubePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_PRESSURIZED_TUBE_PULL_AMOUNT, "cosmicPullAmount", 131_072_000L, 1, Long.MAX_VALUE);
        this.infinitePressurizedTubeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_PRESSURIZED_TUBE_CAPACITY, "infiniteCapacity", 4_194_304_000L, 1, Long.MAX_VALUE);
        this.infinitePressurizedTubePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_PRESSURIZED_TUBE_PULL_AMOUNT, "infinitePullAmount", 1_048_576_000L, 1, Long.MAX_VALUE);
        builder.pop();

        MekanismConfigTranslations.TIER_TRANSMITTERS_ITEM.applyToBuilder(builder).push("item");
        this.absoluteLogisticalTransporterSpeed = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_LOGISTICAL_TRANSPORTER_SPEED, "absoluteSpeed", 55, 1, Integer.MAX_VALUE);
        this.absoluteLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_LOGISTICAL_TRANSPORTER_PULL_AMOUNT, "absolutePullAmount", 128, 1, Integer.MAX_VALUE);
        this.supremeLogisticalTransporterSpeed = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_LOGISTICAL_TRANSPORTER_SPEED, "supremeSpeed", 60, 1, Integer.MAX_VALUE);
        this.supremeLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_LOGISTICAL_TRANSPORTER_PULL_AMOUNT, "supremePullAmount", 256, 1, Integer.MAX_VALUE);
        this.cosmicLogisticalTransporterSpeed = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_LOGISTICAL_TRANSPORTER_SPEED, "cosmicSpeed", 70, 1, Integer.MAX_VALUE);
        this.cosmicLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_LOGISTICAL_TRANSPORTER_PULL_AMOUNT, "cosmicPullAmount", 512, 1, Integer.MAX_VALUE);
        this.infiniteLogisticalTransporterSpeed = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_LOGISTICAL_TRANSPORTER_SPEED, "infiniteSpeed", 100, 1, Integer.MAX_VALUE);
        this.infiniteLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_LOGISTICAL_TRANSPORTER_PULL_AMOUNT, "infinitePullAmount", 1024, 1, Integer.MAX_VALUE);
        builder.pop();

        MekanismConfigTranslations.TIER_TRANSMITTERS_HEAT.applyToBuilder(builder).push("heat");
        this.absoluteThermodynamicConductorConduction = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_THERMODYNAMIC_CONDUCTOR_CONDUCTION, "absoluteConduction", 10L, 1, Long.MAX_VALUE);
        this.absoluteThermodynamicConductorCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_THERMODYNAMIC_CONDUCTOR_CAPACITY, "absoluteCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.absoluteThermodynamicConductorInsulation = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_THERMODYNAMIC_CONDUCTOR_INSULATION, "absoluteInsulation", 400000L, 1, Long.MAX_VALUE);
        this.supremeThermodynamicConductorConduction = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_THERMODYNAMIC_CONDUCTOR_CONDUCTION, "supremeConduction", 15L, 1, Long.MAX_VALUE);
        this.supremeThermodynamicConductorCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_THERMODYNAMIC_CONDUCTOR_CAPACITY, "supremeCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.supremeThermodynamicConductorInsulation = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_THERMODYNAMIC_CONDUCTOR_INSULATION, "supremeInsulation", 800000L, 1, Long.MAX_VALUE);
        this.cosmicThermodynamicConductorConduction = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_THERMODYNAMIC_CONDUCTOR_CONDUCTION, "cosmicConduction", 20L, 1, Long.MAX_VALUE);
        this.cosmicThermodynamicConductorCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_THERMODYNAMIC_CONDUCTOR_CAPACITY, "cosmicCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.cosmicThermodynamicConductorInsulation = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_THERMODYNAMIC_CONDUCTOR_INSULATION, "cosmicInsulation", 1000000L, 1, Long.MAX_VALUE);
        this.infiniteThermodynamicConductorConduction = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_THERMODYNAMIC_CONDUCTOR_CONDUCTION, "infiniteConduction", 25L, 1, Long.MAX_VALUE);
        this.infiniteThermodynamicConductorCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_THERMODYNAMIC_CONDUCTOR_CAPACITY, "infiniteCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.infiniteThermodynamicConductorInsulation = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_THERMODYNAMIC_CONDUCTOR_INSULATION, "infiniteInsulation", 4000000L, 1, Long.MAX_VALUE);
        builder.pop();
        builder.pop();

        configSpec = builder.build();
    }

    private void addQIODriverCategory(ModConfigSpec.Builder builder) {
        ExtraConfigTranslations.TIER_QIO_DRIVER.applyToBuilder(builder).push("qio_drivers");
        for (ExtraQIODriveTier tier : ExtraEnumUtils.QIO_DRIVE_TIERS) {
            AdvancedTierTranslations translations = AdvancedTierTranslations.create(tier);
            String tierName = tier.getAdvanceTier().getSimpleName().toLowerCase(Locale.ROOT);
            CachedLongValue countReference = CachedLongValue.wrap(this, translations.first().applyToBuilder(builder)
                    .defineInRange(tierName + "Count", tier.getMaxCount(), 1, Long.MAX_VALUE));
            CachedIntValue typesReference = CachedIntValue.wrap(this, translations.second().applyToBuilder(builder)
                    .defineInRange(tierName + "Type", tier.getMaxTypes(), 1, Integer.MAX_VALUE));
            tier.setConfigReference(countReference, typesReference);
        }
        builder.pop();
    }

    private void addStoragesCategory(ModConfigSpec.Builder builder) {
        addBinCategory(builder);
        addInductionCategory(builder);
        addEnergyCubeCategory(builder);
        addFluidTankCategory(builder);
        addGasTankCategory(builder);
        addRadioactiveBarrelCategory(builder);
    }

    private void addBinCategory(ModConfigSpec.Builder builder) {
        MekanismConfigTranslations.TIER_BIN.applyToBuilder(builder).push("bins");
        for (BTier tier : ExtraEnumUtils.BIN_TIERS) {
            AdvancedTierTranslations translations = AdvancedTierTranslations.create(tier);
            String tierName = tier.getAdvanceTier().getSimpleName().toLowerCase(Locale.ROOT);
            CachedIntValue storageReference = CachedIntValue.wrap(this, translations.first().applyToBuilder(builder)
                    .defineInRange(tierName + "Storage", tier.getAdvanceStorage(), 1, Integer.MAX_VALUE));
            tier.setConfigReference(storageReference);
        }
        builder.pop();
    }

    private void addInductionCategory(ModConfigSpec.Builder builder) {
        MekanismConfigTranslations.TIER_INDUCTION.applyToBuilder(builder).push("induction");
        for (ICTier tier : ExtraEnumUtils.INDUCTION_CELL_TIERS) {
            AdvancedTierTranslations translations = AdvancedTierTranslations.create(tier);
            String tierName = tier.getAdvanceTier().getSimpleName().toLowerCase(Locale.ROOT);
            CachedLongValue storageReference = CachedLongValue.wrap(this, translations.first().applyToBuilder(builder)
                    .defineInRange(tierName + "Storage", tier.getAdvanceMaxEnergy(), 1, Long.MAX_VALUE));
            tier.setConfigReference(storageReference);
        }
        for (IPTier tier : ExtraEnumUtils.INDUCTION_PROVIDER_TIERS) {
            AdvancedTierTranslations translations = AdvancedTierTranslations.create(tier);
            String tierName = tier.getAdvanceTier().getSimpleName().toLowerCase(Locale.ROOT);
            CachedLongValue outputReference = CachedLongValue.wrap(this, translations.second().applyToBuilder(builder)
                    .defineInRange(tierName + "Output", tier.getAdvanceOutput(), 1, Long.MAX_VALUE));
            tier.setConfigReference(outputReference);
        }
        builder.pop();
    }

    private void addEnergyCubeCategory(ModConfigSpec.Builder builder) {
        MekanismConfigTranslations.TIER_ENERGY_CUBE.applyToBuilder(builder).push("energy_cubes");
        for (ECTier tier : ExtraEnumUtils.ENERGY_CUBE_TIERS) {
            AdvancedTierTranslations translations = AdvancedTierTranslations.create(tier);
            String tierName = tier.getAdvanceTier().getSimpleName().toLowerCase(Locale.ROOT);
            CachedLongValue storageReference = CachedLongValue.wrap(this, translations.first().applyToBuilder(builder)
                    .defineInRange(tierName + "Storage", tier.getAdvanceMaxEnergy(), 1, Long.MAX_VALUE));
            CachedLongValue outputReference = CachedLongValue.wrap(this, translations.second().applyToBuilder(builder)
                    .defineInRange(tierName + "Output", tier.getAdvanceOutput(), 1, Long.MAX_VALUE));
            tier.setConfigReference(storageReference, outputReference);
        }
        builder.pop();
    }

    private void addFluidTankCategory(ModConfigSpec.Builder builder) {
        MekanismConfigTranslations.TIER_FLUID_TANK.applyToBuilder(builder).push("fluid_tanks");
        for (FTTier tier : ExtraEnumUtils.FLUID_TANK_TIERS) {
            AdvancedTierTranslations translations = AdvancedTierTranslations.create(tier);
            String tierName = tier.getAdvanceTier().getSimpleName().toLowerCase(Locale.ROOT);
            CachedIntValue storageReference = CachedIntValue.wrap(this, translations.first().applyToBuilder(builder)
                    .defineInRange(tierName + "Storage", tier.getAdvanceStorage(), 1, Integer.MAX_VALUE));
            CachedIntValue outputReference = CachedIntValue.wrap(this, translations.second().applyToBuilder(builder)
                    .defineInRange(tierName + "Output", tier.getAdvanceOutput(), 1, Integer.MAX_VALUE));
            tier.setConfigReference(storageReference, outputReference);
        }
        builder.pop();
    }

    private void addGasTankCategory(ModConfigSpec.Builder builder) {
        MekanismConfigTranslations.TIER_CHEMICAL_TANK.applyToBuilder(builder).push("chemical_tanks");
        for (CTTier tier : ExtraEnumUtils.CHEMICAL_TANK_TIERS) {
            AdvancedTierTranslations translations = AdvancedTierTranslations.create(tier);
            String tierName = tier.getAdvanceTier().getSimpleName().toLowerCase(Locale.ROOT);
            CachedLongValue storageReference = CachedLongValue.wrap(this, translations.first().applyToBuilder(builder)
                    .defineInRange(tierName + "Storage", tier.getAdvanceStorage(), 1, Long.MAX_VALUE));
            CachedLongValue outputReference = CachedLongValue.wrap(this, translations.second().applyToBuilder(builder)
                    .defineInRange(tierName + "Output", tier.getAdvanceOutput(), 1, Long.MAX_VALUE));
            tier.setConfigReference(storageReference, outputReference);
        }
        builder.pop();
    }

    private void addRadioactiveBarrelCategory(ModConfigSpec.Builder builder) {
        ExtraConfigTranslations.TIER_RADIOACTIVE_BARREL.applyToBuilder(builder).push("radioactive_barrels");
        for (RWBTier tier : ExtraEnumUtils.RADIOACTIVE_BARREL_TIER) {
            AdvancedTierTranslations translations = AdvancedTierTranslations.create(tier);
            String tierName = tier.getAdvanceTier().getSimpleName().toLowerCase(Locale.ROOT);
            CachedLongValue storageReference = CachedLongValue.wrap(this, translations.first().applyToBuilder(builder)
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Storage", tier.getAdvanceStorage(), 1, Long.MAX_VALUE));
            CachedIntValue tickReference = CachedIntValue.wrap(this, translations.second().applyToBuilder(builder)
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "ProcessTicks", tier.getAdvanceProcessTicks(), 1, Integer.MAX_VALUE));
            CachedLongValue amountReference = CachedLongValue.wrap(this, translations.third().applyToBuilder(builder)
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "DecayAmount", tier.getAdvanceDecayAmount(), 0, Long.MAX_VALUE));
            tier.setConfigReference(storageReference, tickReference, amountReference);
        }
        builder.pop();
    }

    @Override
    public String getFileName() {
        return "tiers";
    }

    @Override
    public String getTranslation() {
        return "Tier Config";
    }

    @Override
    public ModConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
