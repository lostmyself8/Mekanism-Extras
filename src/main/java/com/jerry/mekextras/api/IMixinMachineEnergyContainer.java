package com.jerry.mekextras.api;

import mekanism.api.Upgrade;

public interface IMixinMachineEnergyContainer {

    void mekanism_Extras$extraUpdateMaxEnergy();

    void mekanism_Extras$extraRecalculateUpgrades(Upgrade upgrade);

}
