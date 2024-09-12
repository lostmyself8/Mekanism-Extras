package com.jerry.mekextras.common.config;

import com.jerry.mekextras.common.tier.*;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import mekanism.api.heat.HeatAPI;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;
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

    public final CachedFloatingLongValue absoluteUniversalCableCapacity;
    public final CachedFloatingLongValue supremeUniversalCableCapacity;
    public final CachedFloatingLongValue cosmicUniversalCableCapacity;
    public final CachedFloatingLongValue infiniteUniversalCableCapacity;

    public final CachedFloatingLongValue absoluteMechanicalPipeCapacity;
    public final CachedFloatingLongValue absoluteMechanicalPipePullAmount;
    public final CachedFloatingLongValue supremeMechanicalPipeCapacity;
    public final CachedFloatingLongValue supremeMechanicalPipePullAmount;
    public final CachedFloatingLongValue cosmicMechanicalPipeCapacity;
    public final CachedFloatingLongValue cosmicMechanicalPipePullAmount;
    public final CachedFloatingLongValue infiniteMechanicalPipeCapacity;
    public final CachedFloatingLongValue infiniteMechanicalPipePullAmount;

    public final CachedFloatingLongValue absolutePressurizedTubeCapacity;
    public final CachedFloatingLongValue absolutePressurizedTubePullAmount;
    public final CachedFloatingLongValue supremePressurizedTubeCapacity;
    public final CachedFloatingLongValue supremePressurizedTubePullAmount;
    public final CachedFloatingLongValue cosmicPressurizedTubeCapacity;
    public final CachedFloatingLongValue cosmicPressurizedTubePullAmount;
    public final CachedFloatingLongValue infinitePressurizedTubeCapacity;
    public final CachedFloatingLongValue infinitePressurizedTubePullAmount;

    public final CachedFloatingLongValue absoluteLogisticalTransporterSpeed;
    public final CachedFloatingLongValue absoluteLogisticalTransporterPullAmount;
    public final CachedFloatingLongValue supremeLogisticalTransporterSpeed;
    public final CachedFloatingLongValue supremeLogisticalTransporterPullAmount;
    public final CachedFloatingLongValue cosmicLogisticalTransporterSpeed;
    public final CachedFloatingLongValue cosmicLogisticalTransporterPullAmount;
    public final CachedFloatingLongValue infiniteLogisticalTransporterSpeed;
    public final CachedFloatingLongValue infiniteLogisticalTransporterPullAmount;

    public final CachedFloatingLongValue absoluteThermodynamicConductorConduction;
    public final CachedFloatingLongValue absoluteThermodynamicConductornCapacity;
    public final CachedFloatingLongValue absoluteThermodynamicConductornInsulation;
    public final CachedFloatingLongValue supremeThermodynamicConductorConduction;
    public final CachedFloatingLongValue supremeThermodynamicConductornCapacity;
    public final CachedFloatingLongValue supremeThermodynamicConductornInsulation;
    public final CachedFloatingLongValue cosmicThermodynamicConductorConduction;
    public final CachedFloatingLongValue cosmicThermodynamicConductornCapacity;
    public final CachedFloatingLongValue cosmicThermodynamicConductornInsulation;
    public final CachedFloatingLongValue infiniteThermodynamicConductorConduction;
    public final CachedFloatingLongValue infiniteThermodynamicConductornCapacity;
    public final CachedFloatingLongValue infiniteThermodynamicConductornInsulation;

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
        this.absoluteUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC1 + "absolute" + noteUC2, "absoluteUniversalCable", FloatingLong.createConst(65536000L));
        this.supremeUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC1 + "supreme" + noteUC2, "supremeUniversalCable", FloatingLong.createConst(524288000L));
        this.cosmicUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC1 + "cosmic" + noteUC2, "cosmicUniversalCable", FloatingLong.createConst(4194304000L));
        this.infiniteUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC1 + "infinite" + noteUC2, "infiniteUniversalCable", FloatingLong.createConst(33554432000L));
        builder.pop();

        final String noteMP1 = "Capacity of ";
        final String noteMP11 = " mechanical pipes in mB.";
        final String noteMP2 = "Pump rate of ";
        final String noteMP21 = " mechanical pipes in mB/t.";
        builder.comment("Mechanical Pipes").push(EXTRA_FLUID_CATEGORY);
        this.absoluteMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP1 + "absolute" + noteMP11, "absoluteMechanicalPipesCapacity", FloatingLong.createConst(512000L));
        this.absoluteMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2 + "absolute" + noteMP21, "absoluteMechanicalPipesPullAmount", FloatingLong.createConst(128000));
        this.supremeMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP1 + "supreme" + noteMP11, "supremeMechanicalPipesCapacity", FloatingLong.createConst(2048000L));
        this.supremeMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2 + "supreme" + noteMP21, "supremeMechanicalPipesPullAmount", FloatingLong.createConst(512000));
        this.cosmicMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP1 + "cosmic" + noteMP11, "cosmicMechanicalPipesCapacity", FloatingLong.createConst(8192000L));
        this.cosmicMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2 + "cosmic" + noteMP21, "cosmicMechanicalPipesPullAmount", FloatingLong.createConst(2048000));
        this.infiniteMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP1 + "infinite" + noteMP11, "infiniteMechanicalPipesCapacity", FloatingLong.createConst(32768000L));
        this.infiniteMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2 + "infinite" + noteMP21, "infiniteMechanicalPipesPullAmount", FloatingLong.createConst(8192000));
        builder.pop();

        final String notePT1 = "Capacity of ";
        final String notePT11 = " pressurized tubes in mB.";
        final String notePT2 = "Pump rate of ";
        final String notePT21 = " pressurized tubes in mB/t.";
        builder.comment("Pressurized Tubes").push(EXTRA_CHEMICAL_CATEGORY);
        this.absolutePressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT1 + "absolute" + notePT11, "absolutePressurizedTubesCapacity", FloatingLong.createConst(4096000L));
        this.absolutePressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2 + "absolute" + notePT21, "absolutePressurizedTubesPullAmount", FloatingLong.createConst(1024000L));
        this.supremePressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT1 + "supreme" + notePT11, "supremePressurizedTubesCapacity", FloatingLong.createConst(16384000L));
        this.supremePressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2 + "supreme" + notePT21, "supremePressurizedTubesPullAmount", FloatingLong.createConst(4096000L));
        this.cosmicPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT1 + "cosmic" + notePT11, "cosmicPressurizedTubesCapacity", FloatingLong.createConst(65536000L));
        this.cosmicPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2 + "cosmic" + notePT21, "cosmicPressurizedTubesPullAmount", FloatingLong.createConst(16384000L));
        this.infinitePressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT1 + "infinite" + notePT11, "infinitePressurizedTubesCapacity", FloatingLong.createConst(262144000L));
        this.infinitePressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2 + "infinite" + notePT21, "infinitePressurizedTubesPullAmount", FloatingLong.createConst(65536000L));
        builder.pop();

        final String noteLT1 = "Five times the travel speed in m/s of ";
        final String noteLT11 = " logistical transporter.";
        final String noteLT2 = "Item throughput rate of ";
        final String noteLT21 = " logistical transporters in items/half second.";
        builder.comment("Logistical Transporters").push(EXTRA_ITEM_CATEGORY);
        this.absoluteLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT1 + "absolute" + noteLT11, "absoluteLogisticalTransporterSpeed", FloatingLong.createConst(55));
        this.absoluteLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2 + "absolute" + noteLT21, "absoluteLogisticalTransporterPullAmount", FloatingLong.createConst(128));
        this.supremeLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT1 + "supreme" + noteLT11, "supremeLogisticalTransporterSpeed", FloatingLong.createConst(60));
        this.supremeLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2 + "supreme" + noteLT21, "supremeLogisticalTransporterPullAmount", FloatingLong.createConst(256));
        this.cosmicLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT1 + "cosmic" + noteLT11, "cosmicLogisticalTransporterSpeed", FloatingLong.createConst(70));
        this.cosmicLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2 + "cosmic" + noteLT21, "cosmicLogisticalTransporterPullAmount", FloatingLong.createConst(512));
        this.infiniteLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT1 + "infinite" + noteLT11, "infiniteLogisticalTransporterSpeed", FloatingLong.createConst(100));
        this.infiniteLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2 + "infinite" + noteLT21, "infiniteLogisticalTransporterPullAmount", FloatingLong.createConst(1024));
        builder.pop();

        final String noteTC1 = "Conduction value of ";//热导
        final String noteTC11 = " thermodynamic conductors.";
        final String noteTC2 = "Heat capacity of ";//热容
        final String noteTC21 = " thermodynamic conductors.";
        final String noteTC3 = "Insulation value of ";//热阻
        final String noteTC31 = " thermodynamic conductor.";
        builder.comment("Thermodynamic Conductors").push(EXTRA_HEAT_CATEGORY);
        this.absoluteThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC1 + "absolute" + noteTC11, "absoluteThermodynamicConductorConduction", FloatingLong.createConst(10L));
        this.absoluteThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2 + "absolute" + noteTC21, "absoluteThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        this.absoluteThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3 + "absolute" + noteTC31, "absoluteThermodynamicConductornInsulation", FloatingLong.createConst(400000L));
        this.supremeThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC1 + "supreme" + noteTC11, "supremeThermodynamicConductorConduction", FloatingLong.createConst(15L));
        this.supremeThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2 + "supreme" + noteTC21, "supremeThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        this.supremeThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3 + "supreme" + noteTC31, "supremeThermodynamicConductornInsulation", FloatingLong.createConst(800000L));
        this.cosmicThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC1 + "cosmic" + noteTC11, "cosmicThermodynamicConductorConduction", FloatingLong.createConst(20L));
        this.cosmicThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2 + "cosmic" + noteTC21, "cosmicThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        this.cosmicThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3 + "cosmic" + noteTC31, "cosmicThermodynamicConductornInsulation", FloatingLong.createConst(1000000L));
        this.infiniteThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC1 + "infinite" + noteTC11, "infiniteThermodynamicConductorConduction", FloatingLong.createConst(25L));
        this.infiniteThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2 + "infinite" + noteTC21, "infiniteThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        this.infiniteThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3 + "infinite" + noteTC31, "infiniteThermodynamicConductornInsulation", FloatingLong.createConst(4000000L));
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
            CachedFloatingLongValue storageReference = CachedFloatingLongValue.define(this, builder, "Maximum number of Joules " + tierName + " induction cells can store.",
                    tierName.toLowerCase(Locale.ROOT) + "Storage", tier.getAdvanceMaxEnergy(), CachedFloatingLongValue.POSITIVE);
            tier.setConfigReference(storageReference);
        }
        for (IPTier tier : ExtraEnumUtils.INDUCTION_PROVIDER_TIERS) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            CachedFloatingLongValue outputReference = CachedFloatingLongValue.define(this, builder, "Maximum number of Joules " + tierName + " induction providers can output or accept.",
                    tierName.toLowerCase(Locale.ROOT) + "Output", tier.getAdvanceOutput(), CachedFloatingLongValue.POSITIVE);
            tier.setConfigReference(outputReference);
        }
        builder.pop();
    }

    private void addEnergyCubeCategory(ModConfigSpec.Builder builder) {
        builder.comment("Energy Cubes").push(ENERGY_CUBE_CATEGORY);
        for (ECTier tier : ExtraEnumUtils.ENERGY_CUBE_TIERS) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            CachedFloatingLongValue storageReference = CachedFloatingLongValue.define(this, builder,
                    "Maximum number of Joules " + tierName + " energy cubes can store.", tierName.toLowerCase(Locale.ROOT) + "Storage", tier.getAdvanceMaxEnergy(),
                    CachedFloatingLongValue.POSITIVE);
            CachedFloatingLongValue outputReference = CachedFloatingLongValue.define(this, builder,
                    "Output rate in Joules of " + tierName + " energy cubes.", tierName.toLowerCase(Locale.ROOT) + "Output", tier.getAdvanceOutput(),
                    CachedFloatingLongValue.POSITIVE);
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
    public ModConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
