package com.jerry.mekanism_extras.api.text;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.text.ILangEntry;
import net.minecraft.Util;

@NothingNullByDefault
public enum APIExtraLang implements ILangEntry {
    //Upgrades
    UPGRADE_STACK("upgrade", "stack"),
    UPGRADE_STACK_DESCRIPTION("upgrade", "stack.description"),
    UPGRADE_IONIC_MEMBRANE("upgrade", "ionic_membrane"),
    UPGRADE_IONIC_MEMBRANE_DESCRIPTION("upgrade", "ionic_membrane.description"),
    UPGRADE_CREATIVE("upgrade", "creative"),
    UPGRADE_CREATIVE_DESCRIPTION("upgrade", "creative.description");

    private final String key;

    APIExtraLang(String type, String path) {
        this(Util.makeDescriptionId(type, MekanismExtras.rl(path)));
    }

    APIExtraLang(String key) {
        this.key = key;
    }

    @Override
    public String getTranslationKey() {
        return key;
    }
}
