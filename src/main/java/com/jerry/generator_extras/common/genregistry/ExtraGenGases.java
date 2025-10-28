package com.jerry.generator_extras.common.genregistry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.api.gas.attribute.ExtraGasAttributes.*;
import com.jerry.mekanism_extras.common.registry.ExtraChemicalConstants;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.attribute.GasAttributes.*;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;
import mekanism.common.registries.MekanismGases;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraGenGases {


    private ExtraGenGases() {
    }

    public static final GasDeferredRegister EXTRA_GEN_GASES = new GasDeferredRegister(MekanismExtras.MODID);

    public static final GasRegistryObject<Gas> POLONIUM_CONTAINING_STEAM = EXTRA_GEN_GASES.register("polonium_containing_steam", 0x1B9E7B);
    public static final GasRegistryObject<Gas> POLONIUM208 = EXTRA_GEN_GASES.register(ExtraChemicalConstants.POLONIUM208);
    public static final GasRegistryObject<Gas> HELIUM_PLASMA = EXTRA_GEN_GASES.register(ExtraChemicalConstants.HELIUM_PLASMA, ExtraHeatants.HELIUM_PLASMA_HEATANT);
    public static final GasRegistryObject<Gas> EXCITED_HELIUM_PLASMA = EXTRA_GEN_GASES.register(ExtraChemicalConstants.EXCITED_HELIUM_PLASMA, ExtraHeatants.EXCITED_HELIUM_PLASMA_HEATANT);
    public static final GasRegistryObject<Gas> LITHIUM_PLASMA = EXTRA_GEN_GASES.register(ExtraChemicalConstants.LITHIUM_PLASMA, ExtraHeatants.LITHIUM_PLASMA_HEATANT);
    public static final GasRegistryObject<Gas> EXCITED_LITHIUM_PLASMA = EXTRA_GEN_GASES.register(ExtraChemicalConstants.EXCITED_LITHIUM_PLASMA, ExtraHeatants.EXCITED_LITHIUM_PLASMA_HEATANT);
    public static final GasRegistryObject<Gas> IRON_PLASMA = EXTRA_GEN_GASES.register(ExtraChemicalConstants.IRON_PLASMA, ExtraHeatants.IRON_PLASMA_HEATANT);
    public static final GasRegistryObject<Gas> EXCITED_IRON_PLASMA = EXTRA_GEN_GASES.register(ExtraChemicalConstants.EXCITED_IRON_PLASMA, ExtraHeatants.EXCITED_IRON_PLASMA_HEATANT);
    public static final GasRegistryObject<Gas> OSMIUM_PLASMA = EXTRA_GEN_GASES.register(ExtraChemicalConstants.OSMIUM_PLASMA, ExtraHeatants.OSMIUM_PLASMA_HEATANT);
    public static final GasRegistryObject<Gas> EXCITED_OSMIUM_PLASMA = EXTRA_GEN_GASES.register(ExtraChemicalConstants.EXCITED_OSMIUM_PLASMA, ExtraHeatants.EXCITED_OSMIUM_PLASMA_HEATANT);
    public static final GasRegistryObject<Gas> PLUTONIUM_PLASMA = EXTRA_GEN_GASES.register(ExtraChemicalConstants.PLUTONIUM_PLASMA, new Radiation(0.15), ExtraHeatants.PLUTONIUM_PLASMA_HEATANT);
    public static final GasRegistryObject<Gas> EXCITED_PLUTONIUM_PLASMA = EXTRA_GEN_GASES.register(ExtraChemicalConstants.EXCITED_PLUTONIUM_PLASMA, new Radiation(0.20), ExtraHeatants.EXCITED_PLUTONIUM_PLASMA_HEATANT);
    public static final GasRegistryObject<Gas> HELIUM = EXTRA_GEN_GASES.register(ExtraChemicalConstants.HELIUM, ExtraCoolants.HELIUM_COOLANT);
    public static final GasRegistryObject<Gas> SUPERHEATED_HELIUM = EXTRA_GEN_GASES.register(ExtraChemicalConstants.SUPERHEATED_HELIUM, ExtraCoolants.SUPERHEATED_HELIUM_COOLANT);
    public static final GasRegistryObject<Gas> VAPORIZED_IRON = EXTRA_GEN_GASES.register(ExtraChemicalConstants.VAPORIZED_IRON);

    public static void register(IEventBus eventBus) {
        EXTRA_GEN_GASES.register(eventBus);
    }

    public static class ExtraCoolants {

        private ExtraCoolants() {}

        // Do not change this to directly reference IGasProvider objects of this registry. This prevents a circular reference loop.
        public static final CooledCoolant HELIUM_COOLANT = new CooledCoolant(() -> SUPERHEATED_HELIUM.get(), 20, 1);
        public static final HeatedCoolant SUPERHEATED_HELIUM_COOLANT = new HeatedCoolant(() -> HELIUM.get(), 20, 1);
    }

    public static class ExtraHeatants {

        private ExtraHeatants() {}

        // Do not change this to directly reference IGasProvider objects of this registry. This prevents a circular reference loop.
        public static final Heatant HELIUM_PLASMA_HEATANT = new Heatant(() -> SUPERHEATED_HELIUM.get(), 12_000);
        public static final Heatant EXCITED_HELIUM_PLASMA_HEATANT = new Heatant(() -> SUPERHEATED_HELIUM.get(), 15_000);
        public static final Heatant LITHIUM_PLASMA_HEATANT = new Heatant(MekanismGases.LITHIUM, 18_000);
        public static final Heatant EXCITED_LITHIUM_PLASMA_HEATANT = new Heatant(MekanismGases.LITHIUM, 20_000);
        public static final Heatant IRON_PLASMA_HEATANT = new Heatant(() -> VAPORIZED_IRON.get(), 30_000);
        public static final Heatant EXCITED_IRON_PLASMA_HEATANT = new Heatant(() -> VAPORIZED_IRON.get(), 32_000);
        public static final Heatant OSMIUM_PLASMA_HEATANT = new Heatant(MekanismGases.OSMIUM, 40_000);
        public static final Heatant EXCITED_OSMIUM_PLASMA_HEATANT = new Heatant(MekanismGases.OSMIUM, 42_000);
        public static final Heatant PLUTONIUM_PLASMA_HEATANT = new Heatant(MekanismGases.PLUTONIUM, 64_000);
        public static final Heatant EXCITED_PLUTONIUM_PLASMA_HEATANT = new Heatant(MekanismGases.PLUTONIUM, 70_000);
    }
}
