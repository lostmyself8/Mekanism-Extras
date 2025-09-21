package com.jerry.generator_extras.common;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.text.ILangEntry;
import net.minecraft.Util;
import org.jetbrains.annotations.NotNull;

public enum ExtraGenLang implements ILangEntry {
    // Naquadah
    NAQUADAH_REACTOR("reactor", "naquadah_reactor"),
    PCS_PRODUCTION("reactor", "stats.pcs_production"),

    // Plasma Evaporation Plant
    PLASMA_EVAPORATION("plasma", "plasma_evaporation"),
    PLASMA_CONSUMPTION("plasma", "plasma_consumption"),
    PLASMA_PORT_MODE("plasma", "plasma_port_mode"),

    // JEI
    JEI_INFO_POLONIUM_CONTAINING_SOLUTION("info", "jei.polonium_containing_solution"),

    // Description
    DESCRIPTION_NAQUADAH_REACTOR_FRAME("description", "naquadah_reactor_casing"),
    DESCRIPTION_NAQUADAH_REACTOR_PORT("description", "naquadah_reactor_port"),
    DESCRIPTION_NAQUADAH_REACTOR_CONTROLLER("description", "naquadah_reactor_controller"),
    DESCRIPTION_LEAD_COATED_LASER_FOCUS_MATRIX("description", "lead_coated_laser_focus_matrix"),
    DESCRIPTION_LEAD_COATED_GLASS("description", "lead_coated_glass"),

    DESCRIPTION_PLASMA_EVAPORATION_BLOCK("description", "plasma_evaporation_block"),
    DESCRIPTION_PLASMA_EVAPORATION_CONTROLLER("description", "plasma_evaporation_controller"),
    DESCRIPTION_PLASMA_EVAPORATION_VALVE("description", "plasma_evaporation_valve"),
    DESCRIPTION_PLASMA_INSULATION_LAYER("description", "plasma_insulation_layer"),
    DESCRIPTION_PLASMA_EVAPORATION_VENT("description", "plasma_evaporation_vent"),
    DESCRIPTION_FUSION_PLASMA_EXTRACTING_PORT("description", "fusion_reactor_plasma_extracting_port"),

    // Chemical Attributes
    CHEMICAL_ATTRIBUTE_HEATANT("chemical", "attribute.heatant"),

    // Multiblock fail messages
    PLASMA_VENT_NOT_AT_TOP("plasma", "vent_not_at_top"),
    PLASMA_VENTS_NOT_FULFILL_TOP("plasma", "vents_not_fulfill_top"),
    PLASMA_BAD_INSULATION_LAYER("plasma", "bad_insulation_layer"),
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
