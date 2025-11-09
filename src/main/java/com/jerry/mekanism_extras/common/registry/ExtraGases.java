package com.jerry.mekanism_extras.common.registry;

import com.jerry.mekanism_extras.MekanismExtras;

import mekanism.api.chemical.gas.Gas;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGases {

    private ExtraGases() {}

    public static final GasDeferredRegister EXTRA_GASES = new GasDeferredRegister(MekanismExtras.MODID);
    public static final GasRegistryObject<Gas> MOLTEN_THERMONUCLEAR = EXTRA_GASES.register(ExtraChemicalConstants.MOLTEN_THERMONUCLEAR);
    public static final GasRegistryObject<Gas> NAQUADAH_HEXAFLUORIDE = ExtraGases.EXTRA_GASES.register(ExtraChemicalConstants.NAQUADAH_HEXAFLUORIDE);
    public static final GasRegistryObject<Gas> FLUORINATED_NAQUADAH_URANIUM_FUEL = ExtraGases.EXTRA_GASES.register(ExtraChemicalConstants.FLUORINATED_NAQUADAH_URANIUM_FUEL);
    public static final GasRegistryObject<Gas> NAQUADAH_URANIUM_FUEL = ExtraGases.EXTRA_GASES.register(ExtraChemicalConstants.NAQUADAH_URANIUM_FUEL);
    public static final GasRegistryObject<Gas> RICH_NAQUADAH_FUEL = ExtraGases.EXTRA_GASES.register(ExtraChemicalConstants.RICH_NAQUADAH_FUEL);
    public static final GasRegistryObject<Gas> RICH_URANIUM_FUEL = ExtraGases.EXTRA_GASES.register(ExtraChemicalConstants.RICH_URANIUM_FUEL);
    public static final GasRegistryObject<Gas> TUNGSTEN_HEXAFLUORIDE = EXTRA_GASES.register(ExtraChemicalConstants.TUNGSTEN_HEXAFLUORIDE);

    public static void register(IEventBus eventBus) {
        EXTRA_GASES.register(eventBus);
    }
}
