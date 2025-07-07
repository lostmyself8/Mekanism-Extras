package com.jerry.mekanism_extras.api;

import mekanism.api.Upgrade;

public interface IMixinMachineEnergyContainer {

    void mekanism_Extras$extraUpdateMaxEnergy();

    void mekanism_Extras$extraRecalculateUpgrades(Upgrade upgrade);
}
