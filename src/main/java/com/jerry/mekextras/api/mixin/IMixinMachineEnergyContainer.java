package com.jerry.mekextras.api.mixin;

import mekanism.api.Upgrade;

public interface IMixinMachineEnergyContainer {

    void mekextras$extraUpdateMaxEnergy();

    void mekextras$extraRecalculateUpgrades(Upgrade upgrade);
}
