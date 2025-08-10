package com.jerry.genextras.common.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.ExtraChemicalConstants;
import mekanism.api.chemical.Chemical;
import mekanism.common.registration.impl.ChemicalDeferredRegister;
import mekanism.common.registration.impl.DeferredChemical;

public class GenExtraChemicals {

    private GenExtraChemicals() {

    }

    public static final ChemicalDeferredRegister EXTRA_GEN_CHEMICALS = new ChemicalDeferredRegister(MekanismExtras.MOD_ID);

    public static final DeferredChemical<Chemical> POLONIUM_CONTAINING_STEAM = EXTRA_GEN_CHEMICALS.register("polonium_containing_steam", 0x1B9E7B);
    public static final DeferredChemical<Chemical> POLONIUM208 = EXTRA_GEN_CHEMICALS.register(ExtraChemicalConstants.POLONIUM208);

}
