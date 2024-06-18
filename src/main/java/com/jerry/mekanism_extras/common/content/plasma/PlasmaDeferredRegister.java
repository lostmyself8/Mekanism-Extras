package com.jerry.mekanism_extras.common.content.plasma;

import com.jerry.mekanism_extras.api.MekanismExtraAPI;
import mekanism.api.chemical.attribute.ChemicalAttribute;
import mekanism.common.base.IChemicalConstant;
import mekanism.common.registration.WrappedDeferredRegister;

import java.util.function.Supplier;

public class PlasmaDeferredRegister extends WrappedDeferredRegister<Plasma> {

    public PlasmaDeferredRegister(String modid) {
        super(modid, MekanismExtraAPI.PLASMA_REGISTRY_NAME);
    }

    public PlasmaRegistryObject<Plasma> register(IChemicalConstant constants, ChemicalAttribute... attributes) {
        return register(constants.getName(), constants.getColor(), attributes);
    }

    public PlasmaRegistryObject<Plasma> register(String name, int color, ChemicalAttribute... attributes) {
        return register(name, () -> {
            PlasmaBuilder builder = PlasmaBuilder.builder().tint(color);
            for (ChemicalAttribute attribute : attributes) {
                builder.with(attribute);
            }
            return new Plasma(builder);
        });
    }

    public <PLASMA extends Plasma> PlasmaRegistryObject<PLASMA> register(String name, Supplier<? extends PLASMA> sup) {
        return register(name, sup, PlasmaRegistryObject::new);
    }

}
