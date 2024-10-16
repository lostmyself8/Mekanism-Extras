package com.jerry.mekextras.common.config;

import com.jerry.mekextras.common.tier.*;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import mekanism.api.heat.HeatAPI;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedLongValue;
import mekanism.common.config.value.CachedIntValue;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Locale;

public class ExtraTierConfig extends BaseMekanismConfig {
    private static final String EXTRA_ITEMS_CATEGORY = "Items";
    private static final String QIO_DRIVER_CATEGORY = "qio_drivers";
    private static final String EXTRA_STORAGES_CATEGORY = "Storages";
    private static final String ENERGY_CUBE_CATEGORY = "energy_cubes";
    private static final String FLUID_TANK_CATEGORY = "fluid_tanks";
    private static final String CHEMICAL_TANK_CATEGORY = "chemical_tanks";
    private static final String BIN_CATEGORY = "bins";
    private static final String INDUCTION_CATEGORY = "induction";
    private static final String RADIOACTIVE_CATEGORY = "radioactive_waste_barrel";
    private static final String EXTRA_TRANSMITTER_CATEGORY = "transmitters";
    private static final String EXTRA_ENERGY_CATEGORY = "energy";
    private static final String EXTRA_FLUID_CATEGORY = "fluid";
    private static final String EXTRA_CHEMICAL_CATEGORY = "chemical";
    private static final String EXTRA_ITEM_CATEGORY = "item";
    private static final String EXTRA_HEAT_CATEGORY = "heat";
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
    public final CachedLongValue absoluteThermodynamicConductornCapacity;
    public final CachedLongValue absoluteThermodynamicConductornInsulation;
    public final CachedLongValue supremeThermodynamicConductorConduction;
    public final CachedLongValue supremeThermodynamicConductornCapacity;
    public final CachedLongValue supremeThermodynamicConductornInsulation;
    public final CachedLongValue cosmicThermodynamicConductorConduction;
    public final CachedLongValue cosmicThermodynamicConductornCapacity;
    public final CachedLongValue cosmicThermodynamicConductornInsulation;
    public final CachedLongValue infiniteThermodynamicConductorConduction;
    public final CachedLongValue infiniteThermodynamicConductornCapacity;
    public final CachedLongValue infiniteThermodynamicConductornInsulation;

    public ExtraTierConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("Tier Config. This config is synced from server to client.").push("tier");
        //Items
        addItemsCategory(builder);
        //Blocks
        addStoragesCategory(builder);
        //Transmitters
        builder.comment("Transmitters").push(EXTRA_TRANSMITTER_CATEGORY);
        final String noteUC1 = "Internal buffer in Joules of each ";
        final String noteUC2 = " universal cable";
        builder.comment("Universal Cables").push(EXTRA_ENERGY_CATEGORY);
        this.absoluteUniversalCableCapacity = CachedLongValue.define(this, builder, noteUC1 + "absolute" + noteUC2, "absoluteUniversalCable", 65536000L, 1, Long.MAX_VALUE);
        this.supremeUniversalCableCapacity = CachedLongValue.define(this, builder, noteUC1 + "supreme" + noteUC2, "supremeUniversalCable", 524288000L, 1, Long.MAX_VALUE);
        this.cosmicUniversalCableCapacity = CachedLongValue.define(this, builder, noteUC1 + "cosmic" + noteUC2, "cosmicUniversalCable", 4194304000L, 1, Long.MAX_VALUE);
        this.infiniteUniversalCableCapacity = CachedLongValue.define(this, builder, noteUC1 + "infinite" + noteUC2, "infiniteUniversalCable", 33554432000L, 1, Long.MAX_VALUE);
        builder.pop();

        final String noteMP1 = "Capacity of ";
        final String noteMP11 = " mechanical pipes in mB.";
        final String noteMP2 = "Pump rate of ";
        final String noteMP21 = " mechanical pipes in mB/t.";
        builder.comment("Mechanical Pipes").push(EXTRA_FLUID_CATEGORY);
        this.absoluteMechanicalPipeCapacity = CachedLongValue.define(this, builder, noteMP1 + "absolute" + noteMP11, "absoluteMechanicalPipesCapacity", 512000L, 1, Long.MAX_VALUE);
        this.absoluteMechanicalPipePullAmount = CachedLongValue.define(this, builder, noteMP2 + "absolute" + noteMP21, "absoluteMechanicalPipesPullAmount", 128000, 1, Integer.MAX_VALUE);
        this.supremeMechanicalPipeCapacity = CachedLongValue.define(this, builder, noteMP1 + "supreme" + noteMP11, "supremeMechanicalPipesCapacity", 2048000L, 1, Long.MAX_VALUE);
        this.supremeMechanicalPipePullAmount = CachedLongValue.define(this, builder, noteMP2 + "supreme" + noteMP21, "supremeMechanicalPipesPullAmount", 512000, 1, Integer.MAX_VALUE);
        this.cosmicMechanicalPipeCapacity = CachedLongValue.define(this, builder, noteMP1 + "cosmic" + noteMP11, "cosmicMechanicalPipesCapacity", 8192000L, 1, Long.MAX_VALUE);
        this.cosmicMechanicalPipePullAmount = CachedLongValue.define(this, builder, noteMP2 + "cosmic" + noteMP21, "cosmicMechanicalPipesPullAmount", 2048000, 1, Integer.MAX_VALUE);
        this.infiniteMechanicalPipeCapacity = CachedLongValue.define(this, builder, noteMP1 + "infinite" + noteMP11, "infiniteMechanicalPipesCapacity", 32768000L, 1, Long.MAX_VALUE);
        this.infiniteMechanicalPipePullAmount = CachedLongValue.define(this, builder, noteMP2 + "infinite" + noteMP21, "infiniteMechanicalPipesPullAmount", 8192000, 1, Integer.MAX_VALUE);
        builder.pop();

        final String notePT1 = "Capacity of ";
        final String notePT11 = " pressurized tubes in mB.";
        final String notePT2 = "Pump rate of ";
        final String notePT21 = " pressurized tubes in mB/t.";
        builder.comment("Pressurized Tubes").push(EXTRA_CHEMICAL_CATEGORY);
        this.absolutePressurizedTubeCapacity = CachedLongValue.define(this, builder, notePT1 + "absolute" + notePT11, "absolutePressurizedTubesCapacity", 4096000L, 1, Long.MAX_VALUE);
        this.absolutePressurizedTubePullAmount = CachedLongValue.define(this, builder, notePT2 + "absolute" + notePT21, "absolutePressurizedTubesPullAmount", 1024000L, 1, Long.MAX_VALUE);
        this.supremePressurizedTubeCapacity = CachedLongValue.define(this, builder, notePT1 + "supreme" + notePT11, "supremePressurizedTubesCapacity", 16384000L, 1, Long.MAX_VALUE);
        this.supremePressurizedTubePullAmount = CachedLongValue.define(this, builder, notePT2 + "supreme" + notePT21, "supremePressurizedTubesPullAmount", 4096000L, 1, Long.MAX_VALUE);
        this.cosmicPressurizedTubeCapacity = CachedLongValue.define(this, builder, notePT1 + "cosmic" + notePT11, "cosmicPressurizedTubesCapacity", 65536000L, 1, Long.MAX_VALUE);
        this.cosmicPressurizedTubePullAmount = CachedLongValue.define(this, builder, notePT2 + "cosmic" + notePT21, "cosmicPressurizedTubesPullAmount", 16384000L, 1, Long.MAX_VALUE);
        this.infinitePressurizedTubeCapacity = CachedLongValue.define(this, builder, notePT1 + "infinite" + notePT11, "infinitePressurizedTubesCapacity", 262144000L, 1, Long.MAX_VALUE);
        this.infinitePressurizedTubePullAmount = CachedLongValue.define(this, builder, notePT2 + "infinite" + notePT21, "infinitePressurizedTubesPullAmount", 65536000L, 1, Long.MAX_VALUE);
        builder.pop();

        final String noteLT1 = "Five times the travel speed in m/s of ";
        final String noteLT11 = " logistical transporter.";
        final String noteLT2 = "Item throughput rate of ";
        final String noteLT21 = " logistical transporters in items/half second.";
        builder.comment("Logistical Transporters").push(EXTRA_ITEM_CATEGORY);
        this.absoluteLogisticalTransporterSpeed = CachedLongValue.define(this, builder, noteLT1 + "absolute" + noteLT11, "absoluteLogisticalTransporterSpeed", 55, 1, Integer.MAX_VALUE);
        this.absoluteLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, noteLT2 + "absolute" + noteLT21, "absoluteLogisticalTransporterPullAmount", 128, 1, Integer.MAX_VALUE);
        this.supremeLogisticalTransporterSpeed = CachedLongValue.define(this, builder, noteLT1 + "supreme" + noteLT11, "supremeLogisticalTransporterSpeed", 60, 1, Integer.MAX_VALUE);
        this.supremeLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, noteLT2 + "supreme" + noteLT21, "supremeLogisticalTransporterPullAmount", 256, 1, Integer.MAX_VALUE);
        this.cosmicLogisticalTransporterSpeed = CachedLongValue.define(this, builder, noteLT1 + "cosmic" + noteLT11, "cosmicLogisticalTransporterSpeed", 70, 1, Integer.MAX_VALUE);
        this.cosmicLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, noteLT2 + "cosmic" + noteLT21, "cosmicLogisticalTransporterPullAmount", 512, 1, Integer.MAX_VALUE);
        this.infiniteLogisticalTransporterSpeed = CachedLongValue.define(this, builder, noteLT1 + "infinite" + noteLT11, "infiniteLogisticalTransporterSpeed", 100, 1, Integer.MAX_VALUE);
        this.infiniteLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, noteLT2 + "infinite" + noteLT21, "infiniteLogisticalTransporterPullAmount", 1024, 1, Integer.MAX_VALUE);
        builder.pop();

        final String noteTC1 = "Conduction value of ";//热导
        final String noteTC11 = " thermodynamic conductors.";
        final String noteTC2 = "Heat capacity of ";//热容
        final String noteTC21 = " thermodynamic conductors.";
        final String noteTC3 = "Insulation value of ";//热阻
        final String noteTC31 = " thermodynamic conductor.";
        builder.comment("Thermodynamic Conductors").push(EXTRA_HEAT_CATEGORY);
        this.absoluteThermodynamicConductorConduction = CachedLongValue.define(this, builder, noteTC1 + "absolute" + noteTC11, "absoluteThermodynamicConductorConduction", 10L, 1, Long.MAX_VALUE);
        this.absoluteThermodynamicConductornCapacity = CachedLongValue.define(this, builder, noteTC2 + "absolute" + noteTC21, "absoluteThermodynamicConductornCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.absoluteThermodynamicConductornInsulation = CachedLongValue.define(this, builder, noteTC3 + "absolute" + noteTC31, "absoluteThermodynamicConductornInsulation", 400000L, 1, Long.MAX_VALUE);
        this.supremeThermodynamicConductorConduction = CachedLongValue.define(this, builder, noteTC1 + "supreme" + noteTC11, "supremeThermodynamicConductorConduction", 15L, 1, Long.MAX_VALUE);
        this.supremeThermodynamicConductornCapacity = CachedLongValue.define(this, builder, noteTC2 + "supreme" + noteTC21, "supremeThermodynamicConductornCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.supremeThermodynamicConductornInsulation = CachedLongValue.define(this, builder, noteTC3 + "supreme" + noteTC31, "supremeThermodynamicConductornInsulation", 800000L, 1, Long.MAX_VALUE);
        this.cosmicThermodynamicConductorConduction = CachedLongValue.define(this, builder, noteTC1 + "cosmic" + noteTC11, "cosmicThermodynamicConductorConduction", 20L, 1, Long.MAX_VALUE);
        this.cosmicThermodynamicConductornCapacity = CachedLongValue.define(this, builder, noteTC2 + "cosmic" + noteTC21, "cosmicThermodynamicConductornCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.cosmicThermodynamicConductornInsulation = CachedLongValue.define(this, builder, noteTC3 + "cosmic" + noteTC31, "cosmicThermodynamicConductornInsulation", 1000000L, 1, Long.MAX_VALUE);
        this.infiniteThermodynamicConductorConduction = CachedLongValue.define(this, builder, noteTC1 + "infinite" + noteTC11, "infiniteThermodynamicConductorConduction", 25L, 1, Long.MAX_VALUE);
        this.infiniteThermodynamicConductornCapacity = CachedLongValue.define(this, builder, noteTC2 + "infinite" + noteTC21, "infiniteThermodynamicConductornCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.infiniteThermodynamicConductornInsulation = CachedLongValue.define(this, builder, noteTC3 + "infinite" + noteTC31, "infiniteThermodynamicConductornInsulation", 4000000L, 1, Long.MAX_VALUE);
        builder.pop();
        builder.pop();

        builder.pop();
        configSpec = builder.build();
    }

    private void addItemsCategory(ModConfigSpec.Builder builder) {
        builder.comment("Items").push(EXTRA_ITEMS_CATEGORY);
        addQIODriverCategory(builder);
        builder.pop();
    }

    private void addQIODriverCategory(ModConfigSpec.Builder builder) {
        builder.comment("QIO Drivers").push(QIO_DRIVER_CATEGORY);
        for (QIODriveAdvanceTier tier : ExtraEnumUtils.QIO_DRIVE_TIERS) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            CachedLongValue countReference = CachedLongValue.wrap(this, builder.comment("The number of items that the " + tierName + " QIO Drive can store.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Count", tier.getMaxCount(), 1, Long.MAX_VALUE));
            CachedIntValue typesReference = CachedIntValue.wrap(this, builder.comment("The number of types that the " + tierName + " QIO Drive can store.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Types", tier.getMaxTypes(), 1, Integer.MAX_VALUE));
            tier.setConfigReference(countReference, typesReference);
        }
        builder.pop();
    }

    private void addStoragesCategory(ModConfigSpec.Builder builder) {
        builder.comment("Storages").push(EXTRA_STORAGES_CATEGORY);
        addBinCategory(builder);
        addInductionCategory(builder);
        addEnergyCubeCategory(builder);
        addFluidTankCategory(builder);
        addGasTankCategory(builder);
        addRadioactiveBarrelCategory(builder);
        builder.pop();
    }

    private void addBinCategory(ModConfigSpec.Builder builder) {
        builder.comment("Bins").push(BIN_CATEGORY);
        for (BTier tier : ExtraEnumUtils.BIN_TIERS) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            CachedIntValue storageReference = CachedIntValue.wrap(this, builder.comment("The number of items " + tierName + " bins can store.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Storage", tier.getAdvanceStorage(), 1, Integer.MAX_VALUE));
            tier.setConfigReference(storageReference);
        }
        builder.pop();
    }

    private void addInductionCategory(ModConfigSpec.Builder builder) {
        builder.comment("Induction").push(INDUCTION_CATEGORY);
        for (ICTier tier : ExtraEnumUtils.INDUCTION_CELL_TIERS) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            CachedLongValue storageReference = CachedLongValue.wrap(this, builder.comment("Maximum number of Joules " + tierName + " induction cells can store.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Storage", tier.getAdvanceMaxEnergy(), 1, Long.MAX_VALUE));
            tier.setConfigReference(storageReference);
        }
        for (IPTier tier : ExtraEnumUtils.INDUCTION_PROVIDER_TIERS) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            CachedLongValue outputReference = CachedLongValue.wrap(this, builder.comment("Maximum number of Joules " + tierName + " induction providers can output or accept.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Output", tier.getAdvanceOutput(), 1, Long.MAX_VALUE));
            tier.setConfigReference(outputReference);
        }
        builder.pop();
    }

    private void addEnergyCubeCategory(ModConfigSpec.Builder builder) {
        builder.comment("Energy Cubes").push(ENERGY_CUBE_CATEGORY);
        for (ECTier tier : ExtraEnumUtils.ENERGY_CUBE_TIERS) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            CachedLongValue storageReference = CachedLongValue.wrap(this, builder.comment("Maximum number of Joules " + tierName + " energy cubes can store.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Storage", tier.getAdvanceMaxEnergy(), 1, Long.MAX_VALUE));
            CachedLongValue outputReference = CachedLongValue.wrap(this, builder.comment("Output rate in Joules of " + tierName + " energy cubes.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Output", tier.getAdvanceOutput(), 1, Long.MAX_VALUE));
            tier.setConfigReference(storageReference, outputReference);
        }
        builder.pop();
    }

    private void addFluidTankCategory(ModConfigSpec.Builder builder) {
        builder.comment("Fluid Tanks").push(FLUID_TANK_CATEGORY);
        for (FTTier tier : ExtraEnumUtils.FLUID_TANK_TIERS) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            CachedIntValue storageReference = CachedIntValue.wrap(this, builder.comment("Storage size of " + tierName + " fluid tanks in mB.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Storage", tier.getAdvanceStorage(), 1, Integer.MAX_VALUE));
            CachedIntValue outputReference = CachedIntValue.wrap(this, builder.comment("Output rate of " + tierName + " fluid tanks in mB.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Output", tier.getAdvanceOutput(), 1, Integer.MAX_VALUE));
            tier.setConfigReference(storageReference, outputReference);
        }
        builder.pop();
    }

    private void addGasTankCategory(ModConfigSpec.Builder builder) {
        builder.comment("Chemical Tanks").push(CHEMICAL_TANK_CATEGORY);
        for (CTTier tier : ExtraEnumUtils.CHEMICAL_TANK_TIERS) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            CachedLongValue storageReference = CachedLongValue.wrap(this, builder.comment("Storage size of " + tierName + " chemical tanks in mB.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Storage", tier.getAdvanceStorage(), 1, Long.MAX_VALUE));
            CachedLongValue outputReference = CachedLongValue.wrap(this, builder.comment("Output rate of " + tierName + " chemical tanks in mB.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Output", tier.getAdvanceOutput(), 1, Long.MAX_VALUE));
            tier.setConfigReference(storageReference, outputReference);
        }
        builder.pop();
    }

    private void addRadioactiveBarrelCategory(ModConfigSpec.Builder builder) {
        builder.comment("Radioactive Barrel").push(RADIOACTIVE_CATEGORY);
        for (RWBTier tier : ExtraEnumUtils.RADIOACTIVE_BARREL_TIER) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            CachedLongValue storageReference = CachedLongValue.wrap(this, builder.comment("Amount of gas (mB) that can be stored in " + tierName + " Radioactive Waste Barrel.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "Storage", tier.getAdvanceStorage(), 1, Long.MAX_VALUE));
            CachedIntValue tickReference = CachedIntValue.wrap(this, builder.comment("Number of ticks required for radioactive gas stored in " + tierName + " Radioactive Waste Barrel to decay radioactiveWasteBarrelDecayAmount mB.")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "ProcessTicks", tier.getAdvanceProcessTicks(), 1, Integer.MAX_VALUE));
            CachedLongValue amountReference = CachedLongValue.wrap(this, builder.comment("Number of mB of gas that decay every radioactiveWasteBarrelProcessTicks ticks when stored in " + tierName + " Radioactive Waste Barrel. Set to zero to disable decay all together. (Gases in the mekanism:waste_barrel_decay_blacklist tag will not decay).")
                    .defineInRange(tierName.toLowerCase(Locale.ROOT) + "DecayAmount", tier.getAdvanceDecayAmount(), 0, Long.MAX_VALUE));
            tier.setConfigReference(storageReference, tickReference, amountReference);
        }
        builder.pop();
    }

    @Override
    public String getFileName() {
        return "TierConfig";
    }

    @Override
    public String getTranslation() {
        return null;
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
