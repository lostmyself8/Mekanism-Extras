package com.jerry.mekanism_extras.common.integration;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

public enum Addons {

    MEKANISMGENERATORS("Mekanism Generators"),
    EVOLVEDMEKANISM("EvolvedMekanism");

    private final String modName;

    Addons(String modName) {
        this.modName = modName;
    }

    public String getModId() {
        return name().toLowerCase();
    }

    public String getModName() {
        return modName;
    }

    public boolean isLoaded() {
        return ModList.get() != null ? ModList.get().isLoaded(getModId()) : LoadingModList.get().getMods().stream().map(ModInfo::getModId).anyMatch(getModId()::equals);
    }
}
