package com.jerry.mekanism_extras.common;

import com.jerry.mekanism_extras.MekanismExtras;

import mekanism.api.text.ILangEntry;

import net.minecraft.Util;

import org.jetbrains.annotations.NotNull;

public enum ExtraLang implements ILangEntry {

    EXTRA_TAB("constants", "mod_name"),
    STOP_FLASHING("pack", "pack_name"),
    STOP_FLASHING_DESC("pack", "pack_description"),

    // JEI
    JEI_INFO_RICH_NAQUADAH_FUEL("info", "jei.rich_naquadah_fuel"),
    JEI_INFO_RICH_URANIUM_FUEL("info", "jei.rich_uranium_fuel"),

    // Upgrades
    UPGRADES_STACK("gui", "upgrades.stack"),
    ENERGY_CONSUMPTION("gui", "energy_consumption"),

    // Reinforced Induction Matrix
    REINFORCED_MATRIX("matrix", "reinforced_induction_matrix"),

    // Description
    DESCRIPTION_TUNGSTEN_CASING("description", "tungsten_casing");

    private final String key;

    ExtraLang(String type, String path) {
        this(Util.makeDescriptionId(type, MekanismExtras.rl(path)));
    }

    ExtraLang(String key) {
        this.key = key;
    }

    @Override
    public @NotNull String getTranslationKey() {
        return key;
    }
}
