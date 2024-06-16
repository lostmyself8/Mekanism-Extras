package com.jerry.mekanism_extras.common.content.plasma;

import mekanism.common.registration.WrappedRegistryObject;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class PlasmaRegistryObject<PLASMA extends Plasma>
        extends WrappedRegistryObject<PLASMA>
        implements IPlasmaProvider {

    public PlasmaRegistryObject(RegistryObject<PLASMA> registryObject) {
        super(registryObject);
    }

    @NotNull
    @Override
    public PLASMA getChemical() {
        return get();
    }

}
