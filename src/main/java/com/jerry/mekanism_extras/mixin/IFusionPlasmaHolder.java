package com.jerry.mekanism_extras.mixin;

import mekanism.api.chemical.gas.IGasTank;

public interface IFusionPlasmaHolder {

    IGasTank getPlasmaTank();

    long getMaxPlasma();

    boolean canOutputPlasma();

    void setOutputPlasma(boolean b);
}
