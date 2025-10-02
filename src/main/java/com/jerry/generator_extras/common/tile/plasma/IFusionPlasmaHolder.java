package com.jerry.generator_extras.common.tile.plasma;

import mekanism.api.chemical.gas.IGasTank;

public interface IFusionPlasmaHolder {

    IGasTank getPlasmaTank();

    long getMaxPlasma();

    boolean canOutputPlasma();

    void setOutputPlasma(boolean b);
}
