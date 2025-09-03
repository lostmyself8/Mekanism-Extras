package com.jerry.mekextras.common.config;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import com.jerry.mekextras.common.tier.*;
import mekanism.common.config.IConfigTranslation;
import mekanism.common.config.TranslationPreset;
import net.minecraft.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public enum ExtraConfigTranslations implements IConfigTranslation {
    GENERAL_ADVANCED_PUMP_HEAVY_WATER("general.pump.heavy_water", "Heavy Water Amount",
            "Amount of Heavy Water in mB that is extracted per block of Water by the Advanced Electric Pump with a Filter Upgrade."),

    TIER_QIO_DRIVER("tier.qio", "QIO Drivers", "Settings for configuring QIO Drivers", true),
    TIER_RADIOACTIVE_BARREL("tier.radioactive.barrel", "Radioactive Waste Barrel", "Settings for configuring Radioactive Waste Barrels", true),

    ABSOLUTE_UNIVERSAL_CABLE_CAPACITY("tier.cable.absolute.capacity", "Absolute", "Internal buffer in Joules of Absolute Universal Cable."),
    SUPREME_UNIVERSAL_CABLE_CAPACITY("tier.cable.supreme.capacity", "Supreme", "Internal buffer in Joules of Supreme Universal Cable."),
    COSMIC_UNIVERSAL_CABLE_CAPACITY("tier.cable.cosmic.capacity", "Cosmic", "Internal buffer in Joules of Cosmic Universal Cable."),
    INFINITE_UNIVERSAL_CABLE_CAPACITY("tier.cable.infinite.capacity", "Infinite", "Internal buffer in Joules of Infinite Universal Cable."),

    ABSOLUTE_MECHANICAL_PIPE_CAPACITY("tier.pipe.absolute.capacity", "Absolute", "Capacity of Absolute Mechanical Pipe in mb."),
    ABSOLUTE_MECHANICAL_PIPE_PULL_AMOUNT("tier.pipe.absolute.pull_amount", "Absolute", "Pump rate of Absolute Mechanical Pipe in mb."),
    SUPREME_MECHANICAL_PIPE_CAPACITY("tier.pipe.supreme.capacity", "Supreme", "Capacity of Supreme Mechanical Pipe in mb."),
    SUPREME_MECHANICAL_PIPE_PULL_AMOUNT("tier.pipe.supreme.pull_amount", "Supreme", "Pump rate of Supreme Mechanical Pipe in mb."),
    COSMIC_MECHANICAL_PIPE_CAPACITY("tier.pipe.cosmic.capacity", "Cosmic", "Capacity of Cosmic Mechanical Pipe in mb."),
    COSMIC_MECHANICAL_PIPE_PULL_AMOUNT("tier.pipe.cosmic.pull_amount", "Cosmic", "Pump rate of Cosmic Mechanical Pipe in mb."),
    INFINITE_MECHANICAL_PIPE_CAPACITY("tier.pipe.infinite.capacity", "Infinite", "Capacity of Infinite Mechanical Pipe in mb."),
    INFINITE_MECHANICAL_PIPE_PULL_AMOUNT("tier.pipe.infinite.pull_amount", "Infinite", "Pump rate of Infinite Mechanical Pipe in mb."),

    ABSOLUTE_PRESSURIZED_TUBE_CAPACITY("tier.tube.absolute.capacity", "Absolute", "Capacity of Absolute Pressurized Tube in mb."),
    ABSOLUTE_PRESSURIZED_TUBE_PULL_AMOUNT("tier.tube.absolute.pull_amount", "Absolute", "Pump rate of Absolute Pressurized Tube in mb."),
    SUPREME_PRESSURIZED_TUBE_CAPACITY("tier.tube.supreme.capacity", "Supreme", "Capacity of Supreme Pressurized Tube in mb."),
    SUPREME_PRESSURIZED_TUBE_PULL_AMOUNT("tier.tube.supreme.pull_amount", "Supreme", "Pump rate of Supreme Pressurized Tube in mb."),
    COSMIC_PRESSURIZED_TUBE_CAPACITY("tier.tube.cosmic.capacity", "Cosmic", "Capacity of Cosmic Pressurized Tube in mb."),
    COSMIC_PRESSURIZED_TUBE_PULL_AMOUNT("tier.tube.cosmic.pull_amount", "Cosmic", "Pump rate of Cosmic Pressurized Tube in mb."),
    INFINITE_PRESSURIZED_TUBE_CAPACITY("tier.tube.infinite.capacity", "Infinite", "Capacity of Infinite Pressurized Tube in mb."),
    INFINITE_PRESSURIZED_TUBE_PULL_AMOUNT("tier.tube.infinite.pull_amount", "Infinite", "Pump rate of Infinite Pressurized Tube in mb."),

    ABSOLUTE_LOGISTICAL_TRANSPORTER_SPEED("tier.transporter.absolute.speed", "Absolute", "Five times the travel speed in m/s of Absolute Logistical Transporter."),
    ABSOLUTE_LOGISTICAL_TRANSPORTER_PULL_AMOUNT("tier.transporter.absolute.pull_amount", "Absolute", "Item throughput rate of Absolute Logistical Transporter in items/half second."),
    SUPREME_LOGISTICAL_TRANSPORTER_SPEED("tier.transporter.supreme.speed", "Supreme", "Five times the travel speed in m/s of Supreme Logistical Transporter."),
    SUPREME_LOGISTICAL_TRANSPORTER_PULL_AMOUNT("tier.transporter.supreme.pull_amount", "Supreme", "Item throughput rate of Supreme Logistical Transporter in items/half second."),
    COSMIC_LOGISTICAL_TRANSPORTER_SPEED("tier.transporter.cosmic.speed", "Cosmic", "Five times the travel speed in m/s of Cosmic Logistical Transporter."),
    COSMIC_LOGISTICAL_TRANSPORTER_PULL_AMOUNT("tier.transporter.cosmic.pull_amount", "Cosmic", "Item throughput rate of Cosmic Logistical Transporter in items/half second."),
    INFINITE_LOGISTICAL_TRANSPORTER_SPEED("tier.transporter.infinite.speed", "Infinite", "Five times the travel speed in m/s of Infinite Logistical Transporter."),
    INFINITE_LOGISTICAL_TRANSPORTER_PULL_AMOUNT("tier.transporter.infinite.pull_amount", "Infinite", "Item throughput rate of Infinite Logistical Transporter in items/half second."),

    ABSOLUTE_THERMODYNAMIC_CONDUCTOR_CONDUCTION("tier.conductor.absolute.conduction", "Absolute", "Conduction value of Absolute Thermodynamic Conductor."),
    ABSOLUTE_THERMODYNAMIC_CONDUCTOR_CAPACITY("tier.conductor.absolute.capacity", "Absolute", "Heat capacity of Absolute Thermodynamic Conductor."),
    ABSOLUTE_THERMODYNAMIC_CONDUCTOR_INSULATION("tier.conductor.absolute.insulation", "Absolute", "Insulation value of Absolute Thermodynamic Conductor."),
    SUPREME_THERMODYNAMIC_CONDUCTOR_CONDUCTION("tier.conductor.supreme.conduction", "Supreme", "Conduction value of Supreme Thermodynamic Conductor."),
    SUPREME_THERMODYNAMIC_CONDUCTOR_CAPACITY("tier.conductor.supreme.capacity", "Supreme", "Heat capacity of Supreme Thermodynamic Conductor."),
    SUPREME_THERMODYNAMIC_CONDUCTOR_INSULATION("tier.conductor.supreme.insulation", "Supreme", "Insulation value of Supreme Thermodynamic Conductor."),
    COSMIC_THERMODYNAMIC_CONDUCTOR_CONDUCTION("tier.conductor.cosmic.conduction", "Cosmic", "Conduction value of Cosmic Thermodynamic Conductor."),
    COSMIC_THERMODYNAMIC_CONDUCTOR_CAPACITY("tier.conductor.cosmic.capacity", "Cosmic", "Heat capacity of Cosmic Thermodynamic Conductor."),
    COSMIC_THERMODYNAMIC_CONDUCTOR_INSULATION("tier.conductor.cosmic.insulation", "Cosmic", "Insulation value of Cosmic Thermodynamic Conductor."),
    INFINITE_THERMODYNAMIC_CONDUCTOR_CONDUCTION("tier.conductor.infinite.conduction", "Infinite", "Conduction value of Infinite Thermodynamic Conductor."),
    INFINITE_THERMODYNAMIC_CONDUCTOR_CAPACITY("tier.conductor.infinite.capacity", "Infinite", "Heat capacity of Infinite Thermodynamic Conductor."),
    INFINITE_THERMODYNAMIC_CONDUCTOR_INSULATION("tier.conductor.infinite.insulation", "Infinite", "Insulation value of Infinite Thermodynamic Conductor.");

    private final String key;
    private final String title;
    private final String tooltip;
    @Nullable
    private final String button;

    ExtraConfigTranslations(TranslationPreset preset, String type) {
        this(preset.path(type), preset.title(type), preset.tooltip(type));
    }

    ExtraConfigTranslations(TranslationPreset preset, String type, String tooltipSuffix) {
        this(preset.path(type), preset.title(type), preset.tooltip(type) + tooltipSuffix);
    }

    ExtraConfigTranslations(String path, String title, String tooltip) {
        this(path, title, tooltip, false);
    }

    ExtraConfigTranslations(String path, String title, String tooltip, boolean isSection) {
        this(path, title, tooltip, IConfigTranslation.getSectionTitle(title, isSection));
    }

    ExtraConfigTranslations(String path, String title, String tooltip, @Nullable String button) {
        this.key = Util.makeDescriptionId("configuration", MekanismExtras.rl(path));
        this.title = title;
        this.tooltip = tooltip;
        this.button = button;
    }

    @NotNull
    @Override
    public String getTranslationKey() {
        return key;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String tooltip() {
        return tooltip;
    }

    @Nullable
    @Override
    public String button() {
        return button;
    }

    public record AdvancedTierTranslations(@Nullable IConfigTranslation first, @Nullable IConfigTranslation second, @Nullable IConfigTranslation third) {

        public AdvancedTierTranslations {
            if (first == null && second == null) {
                throw new IllegalArgumentException("Tier Translations must have at least a first, second, or third tooltip");
            }
        }

        public IConfigTranslation[] toArray() {
            return Stream.of(first, second, third).filter(Objects::nonNull).toArray(IConfigTranslation[]::new);
        }

        @NotNull
        @Override
        public IConfigTranslation first() {
            if (first == null) {
                throw new IllegalStateException("This method should not be called when first is null. Define first");
            }
            return first;
        }

        @NotNull
        @Override
        public IConfigTranslation second() {
            if (second == null) {
                throw new IllegalStateException("This method should not be called when storage is null. Define second");
            }
            return second;
        }

        @NotNull
        @Override
        public IConfigTranslation third() {
            if (third == null) {
                throw new IllegalStateException("This method should not be called when third is null. Define third");
            }
            return third;
        }

        private static String getKey(String type, String tier, String path) {
            return Util.makeDescriptionId("configuration", MekanismExtras.rl("tier." + type + "." + tier + "." + path));
        }

        public static AdvancedTierTranslations create(IAdvancedTier tier, String type, @Nullable UnaryOperator<String> storageTooltip, @Nullable UnaryOperator<String> outputTooltip) {
            return create(tier, type, storageTooltip, outputTooltip, " Output Rate");
        }

        public static AdvancedTierTranslations create(IAdvancedTier tier, String type, @Nullable UnaryOperator<String> storageTooltip, @Nullable UnaryOperator<String> outputTooltip,
                                                                         String rateSuffix) {
            String tierName = tier.getAdvanceTier().getSimpleName();
            String key = tierName.toLowerCase(Locale.ROOT);
            return new AdvancedTierTranslations(
                    storageTooltip == null ? null : new ConfigTranslation(getKey(type, key, "storage"), tierName + " Storage", storageTooltip.apply(tierName)),
                    outputTooltip == null ? null : new ConfigTranslation(getKey(type, key, "rate"), tierName + rateSuffix, outputTooltip.apply(tierName)),
                    null
            );
        }

        public static AdvancedTierTranslations create(ECTier tier) {
            return create(tier, "energy_cube", name -> "Maximum number of Joules " + name + " energy cubes can store.",
                    name -> "Output rate in Joules of " + name + " energy cubes."
            );
        }

        public static AdvancedTierTranslations create(FTTier tier) {
            return create(tier, "fluid_tank", name -> "Storage size of " + name + " fluid tanks in mB.",
                    name -> "Output rate of " + name + " fluid tanks in mB."
            );
        }

        public static AdvancedTierTranslations create(CTTier tier) {
            return create(tier, "chemical_tank", name -> "Storage size of " + name + " chemical tanks in mB.",
                    name -> "Output rate of " + name + " chemical tanks in mB."
            );
        }

        public static AdvancedTierTranslations create(BTier tier) {
            return create(tier, "bin", name -> "The number of items " + name + " bins can store.", null);
        }

        public static AdvancedTierTranslations create(ICTier tier) {
            return create(tier, "induction.cell", name -> "Maximum number of Joules " + name + " induction cells can store.", null);
        }

        public static AdvancedTierTranslations create(IPTier tier) {
            return create(tier, "induction.provider", null, name -> "Maximum number of Joules " + name + " induction providers can output or accept.");
        }

        public static AdvancedTierTranslations create(ExtraQIODriveTier tier) {
            String type = "qio";
            String tierName = tier.getAdvanceTier().getSimpleName();
            String key = tierName.toLowerCase(Locale.ROOT);
            return new AdvancedTierTranslations(new ConfigTranslation(getKey(type, key, "count"), tierName + " Count",
                    "The number of items that the " + tierName + " QIO Drive can store."
            ), new ConfigTranslation(getKey(type, key, "type"), tierName + " Types",
                    "The number of types that the " + tierName + " QIO Drive can store."
            ), null);
        }

        public static AdvancedTierTranslations create(RWBTier tier) {
            String type = "radioactive.barrel";
            String tierName = tier.getAdvanceTier().getSimpleName();
            String key = tierName.toLowerCase(Locale.ROOT);
            return new AdvancedTierTranslations(new ConfigTranslation(getKey(type, key, "storage"), tierName + " Storage",
                    "Amount of gas (mB) that can be stored in " + tierName + " Radioactive Waste Barrel."
            ), new ConfigTranslation(getKey(type, key, "process_ticks"), tierName + " Process Ticks",
                    "Number of ticks required for radioactive gas stored in " + tierName + " Radioactive Waste Barrel to decay radioactiveWasteBarrelDecayAmount mB."
            ), new ConfigTranslation(getKey(type, key, "decay_amount"), tierName + " Decay Amount",
                    "Number of mB of gas that decay every radioactiveWasteBarrelProcessTicks ticks when stored in " + tierName + " Radioactive Waste Barrel. Set to zero to disable decay all together. (Gases in the mekanism:waste_barrel_decay_blacklist tag will not decay)."
            ));
        }
    }
}
