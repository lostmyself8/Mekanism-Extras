package com.jerry.mekextras.common;

import com.jerry.mekextras.MekanismExtras;
import mekanism.api.text.ILangEntry;
import net.minecraft.Util;
import org.jetbrains.annotations.NotNull;

public enum ExtraLang implements ILangEntry {
    MEK_EXTRAS("constants", "mod_name"),
    //Pack
    STOP_FLASHING("pack", "pack_name"),
//    STOP_FLASHING_DESC("pack", "pack_description"),

    //JEI
    RECIPE_VIEWER_INFO_RICH_NAQUADAH_FUEL("info", "jei.rich_naquadah_fuel"),
    RECIPE_VIEWER_INFO_RICH_URANIUM_FUEL("info", "jei.rich_uranium_fuel"),

    //Upgrades
    UPGRADES_STACK("gui", "upgrades.stack"),
    ENERGY_CONSUMPTION("gui", "energy_consumption"),

    //Reinforced Induction Matrix
    REINFORCED_MATRIX("matrix", "reinforced_induction_matrix"),
    NAQUADAH_REACTOR("reactor", "naquadah_reactor");
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
