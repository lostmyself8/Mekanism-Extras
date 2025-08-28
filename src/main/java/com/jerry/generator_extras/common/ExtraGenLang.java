package com.jerry.generator_extras.common;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.text.ILangEntry;
import net.minecraft.Util;
import org.jetbrains.annotations.NotNull;

public enum ExtraGenLang implements ILangEntry {
    NAQUADAH_REACTOR("reactor", "naquadah_reactor"),
    PCS_PRODUCTION("reactor", "stats.pcs_production"),

    // JEI
    JEI_INFO_POLONIUM_CONTAINING_SOLUTION("info", "jei.polonium_containing_solution"),

    // Description
    DESCRIPTION_NAQUADAH_REACTOR_FRAME("description", "naquadah_reactor_casing"),
    DESCRIPTION_NAQUADAH_REACTOR_PORT("description", "naquadah_reactor_port"),
    DESCRIPTION_NAQUADAH_REACTOR_CONTROLLER("description", "naquadah_reactor_controller"),
    DESCRIPTION_LEAD_COATED_LASER_FOCUS_MATRIX("description", "lead_coated_laser_focus_matrix"),
    DESCRIPTION_LEAD_COATED_GLASS("description", "lead_coated_glass"),

    DESCRIPTION_PLASMA_EVAPORATION_BLOCK("description", "plasma_evaporation_block"),

    // Chemical Attributes
    CHEMICAL_ATTRIBUTE_HEATANT("chemical", "attribute.heatant"),
    ;

    private final String key;

    ExtraGenLang(String type, String path) {
        this(Util.makeDescriptionId(type, MekanismExtras.rl(path)));
    }

    ExtraGenLang(String key) {
        this.key = key;
    }

    @Override
    public @NotNull String getTranslationKey() {
        return key;
    }
}
