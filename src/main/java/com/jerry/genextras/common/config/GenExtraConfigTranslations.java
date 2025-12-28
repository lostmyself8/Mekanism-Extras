package com.jerry.genextras.common.config;

import com.jerry.mekextras.MekanismExtras;

import mekanism.common.config.IConfigTranslation;
import mekanism.common.config.TranslationPreset;

import net.minecraft.Util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum GenExtraConfigTranslations implements IConfigTranslation {

    SERVER_HOHLRAUM("server.hohlraum", "Hohlraum", "Settings for configuring Hohlraum", true),
    SERVER_HOHLRAUM_CAPACITY("server.hohlraum.capacity", "Capacity", "Hohlraum capacity in mB."),
    SERVER_HOHLRAUM_FILL_RATE("server.hohlraum.fill_rate", "Fill Rate", "Rate in mB/t at which Hohlraum can accept Nq-U Fuel."),

    SERVER_REACTOR("server.reactor", "Naquadah Reactor", "Settings for configuring Naquadah Reactors", "Edit Naquadah Reactor Settings"),
    SERVER_REACTOR_FUEL_ENERGY("server.reactor.fuel_energy", "Energy Per Nq-U Fuel", "Affects the Injection Rate, Max Temp, and Ignition Temp."),
    SERVER_REACTOR_THERMOCOUPLE_EFFICIENCY("server.reactor.thermocouple_efficiency", "Thermocouple Efficiency",
            "The fraction of the heat dissipated from the case that is converted to Joules."),
    SERVER_REACTOR_THERMAL_CONDUCTIVITY("server.reactor.casing_thermal_conductivity", "Casing Thermal Conductivity",
            "The fraction of heat from the casing that can be transferred to all sources that are not water. Will impact max heat, heat transfer to " + "thermodynamic conductors, and power generation."),
    SERVER_REACTOR_HEATING_RATE("server.reactor.water_heating_ratio", "Water Heating Ratio",
            "The fraction of the heat from the casing that is dissipated to water when water cooling is in use. Will impact max heat, and steam generation."),
    SERVER_REACTOR_FUEL_CAPACITY("server.reactor.capacity.fuel", "Fuel Capacity", "Amount of fuel (mB) that the naquadah reactor can store."),
    SERVER_REACTOR_ENERGY_CAPACITY("server.reactor.capacity.energy", "Energy Capacity", "Amount of energy (Joules) the naquadah reactor can store."),
    SERVER_REACTOR_WATER_INJECTION("server.reactor.injection.water", "Water Per Injection",
            "Amount of water (mB) per injection rate that the naquadah reactor can store. Max = injectionRate * waterPerInjection"),
    SERVER_REACTOR_STEAM_INJECTION("server.reactor.injection.steam", "Steam Per Injection",
            "Amount of steam (mB) per injection rate that the naquadah reactor can store. Max = injectionRate * steamPerInjection");

    private final String key;
    private final String title;
    private final String tooltip;
    @Nullable
    private final String button;

    GenExtraConfigTranslations(TranslationPreset preset, String type) {
        this(preset.path(type), preset.title(type), preset.tooltip(type));
    }

    GenExtraConfigTranslations(String path, String title, String tooltip) {
        this(path, title, tooltip, false);
    }

    GenExtraConfigTranslations(String path, String title, String tooltip, boolean isSection) {
        this(path, title, tooltip, IConfigTranslation.getSectionTitle(title, isSection));
    }

    GenExtraConfigTranslations(String path, String title, String tooltip, @Nullable String button) {
        this.key = Util.makeDescriptionId("configuration", MekanismExtras.rl(path));
        this.title = title;
        this.tooltip = tooltip;
        this.button = button;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String tooltip() {
        return tooltip;
    }

    @Override
    public @NotNull String getTranslationKey() {
        return key;
    }

    @Override
    public @Nullable String button() {
        return button;
    }
}
