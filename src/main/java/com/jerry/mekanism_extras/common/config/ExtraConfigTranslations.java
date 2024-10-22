package com.jerry.mekanism_extras.common.config;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.config.IConfigTranslation;
import mekanism.common.config.TranslationPreset;
import net.minecraft.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ExtraConfigTranslations implements IConfigTranslation {
    ABSOLUTE_UNIVERSAL_CABLE_CAPACITY("tier.cable", "absolute", "Internal buffer in Joules of Absolute Universal Cable."),
    SUPREME_UNIVERSAL_CABLE_CAPACITY("tier.cable", "supreme", "Internal buffer in Joules of Supreme Universal Cable."),
    COSMIC_UNIVERSAL_CABLE_CAPACITY("tier.cable", "cosmic", "Internal buffer in Joules of Cosmic Universal Cable."),
    INFINITE_UNIVERSAL_CABLE_CAPACITY("tier.cable", "infinite", "Internal buffer in Joules of Infinite Universal Cable."),

    ABSOLUTE_MECHANICAL_PIPE_CAPACITY("tier.pipe", "absolute", "Capacity of Absolute Mechanical Pipe in mb."),
    ABSOLUTE_MECHANICAL_PIPE_PULL_AMOUNT("tier.pipe", "absolute", "Pump rate of Absolute Mechanical Pipe in mb."),
    SUPREME_MECHANICAL_PIPE_CAPACITY("tier.pipe", "supreme", "Capacity of Supreme Mechanical Pipe in mb."),
    SUPREME_MECHANICAL_PIPE_PULL_AMOUNT("tier.pipe", "supreme", "Pump rate of Supreme Mechanical Pipe in mb."),
    COSMIC_MECHANICAL_PIPE_CAPACITY("tier.pipe", "cosmic", "Capacity of Cosmic Mechanical Pipe in mb."),
    COSMIC_MECHANICAL_PIPE_PULL_AMOUNT("tier.pipe", "cosmic", "Pump rate of Cosmic Mechanical Pipe in mb."),
    INFINITE_MECHANICAL_PIPE_CAPACITY("tier.pipe", "infinite", "Capacity of Infinite Mechanical Pipe in mb."),
    INFINITE_MECHANICAL_PIPE_PULL_AMOUNT("tier.pipe", "infinite", "Pump rate of Infinite Mechanical Pipe in mb."),

    ABSOLUTE_PRESSURIZED_TUBE_CAPACITY("tier.tube", "absolute", "Capacity of Absolute Pressurized Tube in mb."),
    ABSOLUTE_PRESSURIZED_TUBE_PULL_AMOUNT("tier.tube", "absolute", "Pump rate of Absolute Pressurized Tube in mb."),
    SUPREME_PRESSURIZED_TUBE_CAPACITY("tier.tube", "supreme", "Capacity of Supreme Pressurized Tube in mb."),
    SUPREME_PRESSURIZED_TUBE_PULL_AMOUNT("tier.tube", "supreme", "Pump rate of Supreme Pressurized Tube in mb."),
    COSMIC_PRESSURIZED_TUBE_CAPACITY("tier.tube", "cosmic", "Capacity of Cosmic Pressurized Tube in mb."),
    COSMIC_PRESSURIZED_TUBE_PULL_AMOUNT("tier.tube", "cosmic", "Pump rate of Cosmic Pressurized Tube in mb."),
    INFINITE_PRESSURIZED_TUBE_CAPACITY("tier.tube", "infinite", "Capacity of Infinite Pressurized Tube in mb."),
    INFINITE_PRESSURIZED_TUBE_PULL_AMOUNT("tier.tube", "infinite", "Pump rate of Infinite Pressurized Tube in mb."),

    ABSOLUTE_LOGISTICAL_TRANSPORTER_SPEED("tier.transporter", "absolute", "Five times the travel speed in m/s of Absolute Logistical Transporter."),
    ABSOLUTE_LOGISTICAL_TRANSPORTER_PULL_AMOUNT("tier.transporter", "absolute", "Item throughput rate of Absolute Logistical Transporter in items/half second."),
    SUPREME_LOGISTICAL_TRANSPORTER_SPEED("tier.transporter", "supreme", "Five times the travel speed in m/s of Supreme Logistical Transporter."),
    SUPREME_LOGISTICAL_TRANSPORTER_PULL_AMOUNT("tier.transporter", "supreme", "Item throughput rate of Supreme Logistical Transporter in items/half second."),
    COSMIC_LOGISTICAL_TRANSPORTER_SPEED("tier.transporter", "cosmic", "Five times the travel speed in m/s of Cosmic Logistical Transporter."),
    COSMIC_LOGISTICAL_TRANSPORTER_PULL_AMOUNT("tier.transporter", "cosmic", "Item throughput rate of Cosmic Logistical Transporter in items/half second."),
    INFINITE_LOGISTICAL_TRANSPORTER_SPEED("tier.transporter", "infinite", "Five times the travel speed in m/s of Infinite Logistical Transporter."),
    INFINITE_LOGISTICAL_TRANSPORTER_PULL_AMOUNT("tier.transporter", "infinite", "Item throughput rate of Infinite Logistical Transporter in items/half second."),

    ABSOLUTE_THERMODYNAMIC_CONDUCTOR_CONDUCTION("tier.conductorn", "absolute", "Conduction value of Absolute Thermodynamic Conductor."),
    ABSOLUTE_THERMODYNAMIC_CONDUCTORN_CAPACITY("tier.conductorn", "absolute", "Heat capacity of Absolute Thermodynamic Conductor."),
    ABSOLUTE_THERMODYNAMIC_CONDUCTORN_INSULATION("tier.conductorn", "absolute", "Insulation value of Absolute Thermodynamic Conductor."),
    SUPREME_THERMODYNAMIC_CONDUCTOR_CONDUCTION("tier.conductorn", "supreme", "Conduction value of Supreme Thermodynamic Conductor."),
    SUPREME_THERMODYNAMIC_CONDUCTORN_CAPACITY("tier.conductorn", "supreme", "Heat capacity of Supreme Thermodynamic Conductor."),
    SUPREME_THERMODYNAMIC_CONDUCTORN_INSULATION("tier.conductorn", "supreme", "Insulation value of Supreme Thermodynamic Conductor."),
    COSMIC_THERMODYNAMIC_CONDUCTOR_CONDUCTION("tier.conductorn", "cosmic", "Conduction value of Cosmic Thermodynamic Conductor."),
    COSMIC_THERMODYNAMIC_CONDUCTORN_CAPACITY("tier.conductorn", "cosmic", "Heat capacity of Cosmic Thermodynamic Conductor."),
    COSMIC_THERMODYNAMIC_CONDUCTORN_INSULATION("tier.conductorn", "cosmic", "Insulation value of Cosmic Thermodynamic Conductor."),
    INFINITE_THERMODYNAMIC_CONDUCTOR_CONDUCTION("tier.conductorn", "infinite", "Conduction value of Infinite Thermodynamic Conductor."),
    INFINITE_THERMODYNAMIC_CONDUCTORN_CAPACITY("tier.conductorn", "infinite", "Heat capacity of Infinite Thermodynamic Conductor."),
    INFINITE_THERMODYNAMIC_CONDUCTORN_INSULATION("tier.conductorn", "infinite", "Insulation value of Infinite Thermodynamic Conductor.");

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
}
