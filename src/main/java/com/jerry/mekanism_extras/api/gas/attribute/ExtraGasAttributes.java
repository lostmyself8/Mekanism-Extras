package com.jerry.mekanism_extras.api.gas.attribute;

import com.jerry.generator_extras.common.ExtraGenLang;
import mekanism.api.chemical.attribute.ChemicalAttribute;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.providers.IGasProvider;
import mekanism.api.text.EnumColor;
import mekanism.common.util.text.TextUtils;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ExtraGasAttributes {

    private ExtraGasAttributes() {}

    /**
     * Defines a plasma with its heat, for use in Plasma Evaporation Plant.
     * This can be regarded as the "hotter" variant of a superheated chemical,
     * so the superheated variant must be provided. If it doesn't exist, use
     * its origin variant, like Polonium Plasma - Polonium. The temperature
     * is measured in K/10,000mB.
     */
    public static class Heatant extends ChemicalAttribute {

        private final IGasProvider superheatedGas;
        private final double temperature;

        public Heatant(IGasProvider superheatedGas, double temperature) {
            this.superheatedGas = superheatedGas;
            if (temperature <= 0) {
                throw new IllegalArgumentException("Temperature must be greater than 0, got " + temperature);
            }
            this.temperature = temperature;
        }

        public Gas getSuperheatedGas() {
            return superheatedGas.getChemical();
        }

        public double getTemperature() {
            return temperature;
        }

        @Override
        public List<Component> addTooltipText(List<Component> list) {
            super.addTooltipText(list);
            list.add(ExtraGenLang.CHEMICAL_ATTRIBUTE_HEATANT.translateColored(EnumColor.GRAY, EnumColor.ORANGE, TextUtils.format(temperature)));
            return list;
        }
    }
}
