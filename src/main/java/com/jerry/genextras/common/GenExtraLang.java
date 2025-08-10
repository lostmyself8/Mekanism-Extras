package com.jerry.genextras.common;

import com.jerry.mekextras.MekanismExtras;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.text.ILangEntry;
import net.minecraft.Util;

@NothingNullByDefault
public enum GenExtraLang implements ILangEntry {
    NAQUADAH_REACTOR("reactor", "naquadah_reactor"),

    //JEI
    RECIPE_VIEWER_INFO_POLONIUM_CONTAINING_SOLUTION("info", "jei.polonium_containing_solution"),

    //Description
    DESCRIPTION_NAQUADAH_REACTOR_CONTROLLER("description", "naquadah_reactor_controller"),
    DESCRIPTION_NAQUADAH_REACTOR_CASING("description", "naquadah_reactor_casing"),
    DESCRIPTION_NAQUADAH_REACTOR_PORT("description", "naquadah_reactor_port");

    private final String key;

    GenExtraLang(String type, String path) {
        this(Util.makeDescriptionId(type, MekanismExtras.rl(path)));
    }

    GenExtraLang(String key) {
        this.key = key;
    }

    @Override
    public String getTranslationKey() {
        return key;
    }
}
