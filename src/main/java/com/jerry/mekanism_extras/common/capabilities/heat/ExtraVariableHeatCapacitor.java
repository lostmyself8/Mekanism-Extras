package com.jerry.mekanism_extras.common.capabilities.heat;

import mekanism.api.IContentsListener;
import mekanism.common.capabilities.heat.BasicHeatCapacitor;

import org.jetbrains.annotations.Nullable;

import java.util.function.DoubleSupplier;

public class ExtraVariableHeatCapacitor extends BasicHeatCapacitor {

    private final double conductionCoefficientSupplier;
    private final double insulationCoefficientSupplier;

    protected ExtraVariableHeatCapacitor(double heatCapacity, double inverseConductionCoefficient, double inverseInsulationCoefficient, @Nullable DoubleSupplier ambientTempSupplier, @Nullable IContentsListener listener) {
        super(heatCapacity, inverseConductionCoefficient, inverseInsulationCoefficient, ambientTempSupplier, listener);
        this.conductionCoefficientSupplier = inverseConductionCoefficient;
        this.insulationCoefficientSupplier = inverseInsulationCoefficient;
    }

    public static ExtraVariableHeatCapacitor create(double heatCapacity, double conductionCoefficient, double insulationCoefficient, @Nullable DoubleSupplier ambientTempSupplier, @Nullable IContentsListener listener) {
        return new ExtraVariableHeatCapacitor(heatCapacity, conductionCoefficient, insulationCoefficient, ambientTempSupplier, listener);
    }

    @Override
    public double getInverseConduction() {
        return Math.max(1, conductionCoefficientSupplier);
    }

    @Override
    public double getInverseInsulation() {
        return insulationCoefficientSupplier;
    }
}
