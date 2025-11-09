package com.jerry.mekanism_extras.common.capabilities.chemical.variable;

import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.common.capabilities.chemical.variable.VariableCapacityChemicalTank;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;
import java.util.function.LongSupplier;
import java.util.function.Predicate;

public abstract class ExtraRateLimitChemicalTank<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>> extends VariableCapacityChemicalTank<CHEMICAL, STACK> {

    private final LongSupplier rate;

    public ExtraRateLimitChemicalTank(LongSupplier rate, LongSupplier capacity, BiPredicate<@NotNull CHEMICAL, @NotNull AutomationType> canExtract,
                                      BiPredicate<@NotNull CHEMICAL, @NotNull AutomationType> canInsert, Predicate<@NotNull CHEMICAL> isValid,
                                      @Nullable ChemicalAttributeValidator attributeValidator, @Nullable IContentsListener listener) {
        super(capacity, canExtract, canInsert, isValid, attributeValidator, listener);
        this.rate = rate;
    }

    @Override
    protected long getRate(@Nullable AutomationType automationType) {
        // Allow unknown or manual interaction to bypass rate limit for the item
        return automationType == null || automationType == AutomationType.MANUAL ? super.getRate(automationType) : rate.getAsLong();
    }
}
