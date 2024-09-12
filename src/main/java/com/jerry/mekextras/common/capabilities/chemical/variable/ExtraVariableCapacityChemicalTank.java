package com.jerry.mekextras.common.capabilities.chemical.variable;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

@NothingNullByDefault
public abstract class ExtraVariableCapacityChemicalTank <CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>> extends BasicChemicalTank<CHEMICAL, STACK> {
    private final long capacity;

    protected ExtraVariableCapacityChemicalTank(long capacity, BiPredicate<@NotNull CHEMICAL, @NotNull AutomationType> canExtract,
                                           BiPredicate<@NotNull CHEMICAL, @NotNull AutomationType> canInsert, Predicate<@NotNull CHEMICAL> validator,
                                           @Nullable ChemicalAttributeValidator attributeValidator, @Nullable IContentsListener listener) {
        super(capacity, canExtract, canInsert, validator, attributeValidator, listener);
        this.capacity = capacity;
    }

    @Override
    public long getCapacity() {
        return capacity;
    }

    @Override
    public long setStackSize(long amount, @NotNull Action action) {
        if (isEmpty()) {
            return 0;
        } else if (amount <= 0) {
            if (action.execute()) {
                setEmpty();
            }
            return 0;
        }
        long maxStackSize = getCapacity();
        //Our capacity should never actually be zero, and given we fake it being zero
        // until we finish building the network, we need to override this method to bypass the upper limit check
        // when our upper limit is zero
        if (maxStackSize > 0 && amount > maxStackSize) {
            amount = maxStackSize;
        }
        if (getStored() == amount || action.simulate()) {
            //If our size is not changing, or we are only simulating the change, don't do anything
            return amount;
        }
        stored.setAmount(amount);
        onContentsChanged();
        return amount;
    }
}
