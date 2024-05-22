package com.jerry.mekanism_extras.config;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.heat.HeatAPI;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.*;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class ExtraConfig extends BaseMekanismConfig {
    private final ForgeConfigSpec configSpec;
    public final CachedBooleanValue transmitterAlloyUpgrade;
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
    //Radiation
    public final CachedLongValue radioactiveWasteBarrelMaxGas;
    public final CachedIntValue radioactiveWasteBarrelProcessTicks;
    public final CachedLongValue radioactiveWasteBarrelDecayAmount;

    public ExtraConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        final String noteUC = "Internal buffer in Joules of each 'TIER' universal cable";
        builder.comment("Universal Cables").push(MekanismExtras.MODID);
        this.absoluteUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC, "absoluteUniversalCable", FloatingLong.createConst(65536000L));
        this.supremeUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC, "supremeUniversalCable", FloatingLong.createConst(524288000L));
        this.cosmicUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC, "cosmicUniversalCable", FloatingLong.createConst(4194304000L));
        this.infiniteUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC, "infiniteUniversalCable", FloatingLong.createConst(33554432000L));

        final String noteMP = "Capacity of 'TIER' mechanical pipes in mB.";
        final String noteMP2 = "Pump rate of 'TIER' mechanical pipes in mB/t.";
        builder.comment("Mechanical Pipes").push(MekanismExtras.MODID);
        this.absoluteMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "absoluteMechanicalPipesCapacity", FloatingLong.createConst(512000L));
        this.absoluteMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2, "absoluteMechanicalPipesPullAmount", FloatingLong.createConst(128000));
        this.supremeMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "supremeMechanicalPipesCapacity", FloatingLong.createConst(2048000L));
        this.supremeMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2, "supremeMechanicalPipesPullAmount", FloatingLong.createConst(512000));
        this.cosmicMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "cosmicMechanicalPipesCapacity", FloatingLong.createConst(8192000L));
        this.cosmicMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2, "cosmicMechanicalPipesPullAmount", FloatingLong.createConst(2048000));
        this.infiniteMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "infiniteMechanicalPipesCapacity", FloatingLong.createConst(32768000L));
        this.infiniteMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2, "infiniteMechanicalPipesPullAmount", FloatingLong.createConst(8192000));

        final String notePT = "Capacity of 'TIER' pressurized tubes in mB.";
        final String notePT2 = "Pump rate of 'TIER' pressurized tubes in mB/t.";
        builder.comment("Pressurized Tubes").push(MekanismExtras.MODID);
        this.absolutePressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "absolutePressurizedTubesCapacity", FloatingLong.createConst(4096000L));
        this.absolutePressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "absolutePressurizedTubesPullAmount", FloatingLong.createConst(1024000L));
        this.supremePressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "supremePressurizedTubesCapacity", FloatingLong.createConst(16384000L));
        this.supremePressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "supremePressurizedTubesPullAmount", FloatingLong.createConst(4096000L));
        this.cosmicPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "cosmicPressurizedTubesCapacity", FloatingLong.createConst(65536000L));
        this.cosmicPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "cosmicPressurizedTubesPullAmount", FloatingLong.createConst(16384000L));
        this.infinitePressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "infinitePressurizedTubesCapacity", FloatingLong.createConst(262144000L));
        this.infinitePressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "infinitePressurizedTubesPullAmount", FloatingLong.createConst(65536000L));

        final String noteLT = "Five times the travel speed in m/s of 'TIER' logistical transporter.";
        final String noteLT2 = "Item throughput rate of 'TIER' logistical transporters in items/half second.";
        builder.comment("Logistical Transporters").push(MekanismExtras.MODID);
        this.absoluteLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "absoluteLogisticalTransporterSpeed", FloatingLong.createConst(100));
        this.absoluteLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "absoluteLogisticalTransporterPullAmount", FloatingLong.createConst(128));
        this.supremeLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "supremeLogisticalTransporterSpeed", FloatingLong.createConst(200));
        this.supremeLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "supremeLogisticalTransporterPullAmount", FloatingLong.createConst(256));
        this.cosmicLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "cosmicLogisticalTransporterSpeed", FloatingLong.createConst(400));
        this.cosmicLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "cosmicLogisticalTransporterPullAmount", FloatingLong.createConst(512));
        this.infiniteLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "infiniteLogisticalTransporterSpeed", FloatingLong.createConst(800));
        this.infiniteLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "infiniteLogisticalTransporterPullAmount", FloatingLong.createConst(1024));

        final String noteTC = "Conduction value of 'TIER' thermodynamic conductors.";//热导
        final String noteTC2 = "Heat capacity of 'TIER' thermodynamic conductors.";//热容
        final String noteTC3 = "Insulation value of 'TIER' thermodynamic conductor.";//热阻
        builder.comment("Thermodynamic Conductors").push(MekanismExtras.MODID);
        this.absoluteThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "absoluteThermodynamicConductorConduction", FloatingLong.createConst(10L));
        this.absoluteThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "absoluteThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        this.absoluteThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "absoluteThermodynamicConductornInsulation", FloatingLong.createConst(400000L));
        this.supremeThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "supremeThermodynamicConductorConduction", FloatingLong.createConst(15L));
        this.supremeThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "supremeThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        this.supremeThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "supremeThermodynamicConductornInsulation", FloatingLong.createConst(800000L));
        this.cosmicThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "cosmicThermodynamicConductorConduction", FloatingLong.createConst(20L));
        this.cosmicThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "cosmicThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        this.cosmicThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "cosmicThermodynamicConductornInsulation", FloatingLong.createConst(1000000L));
        this.infiniteThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "infiniteThermodynamicConductorConduction", FloatingLong.createConst(25L));
        this.infiniteThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "infiniteThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        this.infiniteThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "infiniteThermodynamicConductornInsulation", FloatingLong.createConst(4000000L));

        this.radioactiveWasteBarrelMaxGas = CachedLongValue.wrap(this, builder.comment("Amount of gas (mB) that can be stored in a Radioactive Waste Barrel.")
                .defineInRange("radioactiveWasteBarrelMaxGas", 2_048_000, 1, Long.MAX_VALUE));
        this.radioactiveWasteBarrelProcessTicks = CachedIntValue.wrap(this, builder.comment("Number of ticks required for radioactive gas stored in a Radioactive Waste Barrel to decay radioactiveWasteBarrelDecayAmount mB.")
                .defineInRange("radioactiveWasteBarrelProcessTicks", 5, 1, Integer.MAX_VALUE));
        this.radioactiveWasteBarrelDecayAmount = CachedLongValue.wrap(this, builder.comment("Number of mB of gas that decay every radioactiveWasteBarrelProcessTicks ticks when stored in a Radioactive Waste Barrel. Set to zero to disable decay all together. (Gases in the mekanism:waste_barrel_decay_blacklist tag will not decay).")
                .defineInRange("radioactiveWasteBarrelDecayAmount", 4, 0, Long.MAX_VALUE));
//        this.radioactiveWasteBarrelMaxGas = CachedLongValue.wrap(this, builder.comment("Amount of gas (mB) that can be stored in a Radioactive Waste Barrel.")
//                .defineInRange("radioactiveWasteBarrelMaxGas", 1_024_000, 1, Long.MAX_VALUE));
//        this.radioactiveWasteBarrelProcessTicks = CachedIntValue.wrap(this, builder.comment("Number of ticks required for radioactive gas stored in a Radioactive Waste Barrel to decay radioactiveWasteBarrelDecayAmount mB.")
//                .defineInRange("radioactiveWasteBarrelProcessTicks", 10, 1, Integer.MAX_VALUE));
//        this.radioactiveWasteBarrelDecayAmount = CachedLongValue.wrap(this, builder.comment("Number of mB of gas that decay every radioactiveWasteBarrelProcessTicks ticks when stored in a Radioactive Waste Barrel. Set to zero to disable decay all together. (Gases in the mekanism:waste_barrel_decay_blacklist tag will not decay).")
//                .defineInRange("radioactiveWasteBarrelDecayAmount", 1, 0, Long.MAX_VALUE));
//        this.radioactiveWasteBarrelMaxGas = CachedLongValue.wrap(this, builder.comment("Amount of gas (mB) that can be stored in a Radioactive Waste Barrel.")
//                .defineInRange("radioactiveWasteBarrelMaxGas", 1_024_000, 1, Long.MAX_VALUE));
//        this.radioactiveWasteBarrelProcessTicks = CachedIntValue.wrap(this, builder.comment("Number of ticks required for radioactive gas stored in a Radioactive Waste Barrel to decay radioactiveWasteBarrelDecayAmount mB.")
//                .defineInRange("radioactiveWasteBarrelProcessTicks", 10, 1, Integer.MAX_VALUE));
//        this.radioactiveWasteBarrelDecayAmount = CachedLongValue.wrap(this, builder.comment("Number of mB of gas that decay every radioactiveWasteBarrelProcessTicks ticks when stored in a Radioactive Waste Barrel. Set to zero to disable decay all together. (Gases in the mekanism:waste_barrel_decay_blacklist tag will not decay).")
//                .defineInRange("radioactiveWasteBarrelDecayAmount", 1, 0, Long.MAX_VALUE));
//        this.radioactiveWasteBarrelMaxGas = CachedLongValue.wrap(this, builder.comment("Amount of gas (mB) that can be stored in a Radioactive Waste Barrel.")
//                .defineInRange("radioactiveWasteBarrelMaxGas", 1_024_000, 1, Long.MAX_VALUE));
//        this.radioactiveWasteBarrelProcessTicks = CachedIntValue.wrap(this, builder.comment("Number of ticks required for radioactive gas stored in a Radioactive Waste Barrel to decay radioactiveWasteBarrelDecayAmount mB.")
//                .defineInRange("radioactiveWasteBarrelProcessTicks", 10, 1, Integer.MAX_VALUE));
//        this.radioactiveWasteBarrelDecayAmount = CachedLongValue.wrap(this, builder.comment("Number of mB of gas that decay every radioactiveWasteBarrelProcessTicks ticks when stored in a Radioactive Waste Barrel. Set to zero to disable decay all together. (Gases in the mekanism:waste_barrel_decay_blacklist tag will not decay).")
//                .defineInRange("radioactiveWasteBarrelDecayAmount", 1, 0, Long.MAX_VALUE));

        this.transmitterAlloyUpgrade = CachedBooleanValue.wrap(this, builder.comment("Allow right clicking on Cables/Pipes/Tubes with alloys to upgrade the tier.")
                .define("transmitterAlloyUpgrade", true));

        builder.pop();
        this.configSpec = builder.build();
    }

    public String getFileName() {
        return MekanismExtras.MODID;
    }

    public ForgeConfigSpec getConfigSpec() {
        return this.configSpec;
    }

    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
