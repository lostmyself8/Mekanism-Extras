package com.jerry.mekanism_extras.common.capabilities.chemical;

import com.jerry.mekanism_extras.common.tier.CTTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.LongSupplier;

@NothingNullByDefault
public class ExtraChemicalTankChemicalTank extends BasicChemicalTank {
    public static ExtraChemicalTankChemicalTank create(CTTier tier, @Nullable IContentsListener listener) {
        Objects.requireNonNull(tier, "Chemical tank tier cannot be null");
        return new ExtraChemicalTankChemicalTank(tier, listener);
    }

    private final boolean isCreative;
    private final LongSupplier rate;

    private ExtraChemicalTankChemicalTank(CTTier tier, @Nullable IContentsListener listener) {
        super(tier.getStorage(), alwaysTrueBi, alwaysTrueBi, alwaysTrue, null, listener);
        isCreative = false;
        rate = tier::getOutput;
    }

    @Override
    protected long getInsertRate(@Nullable AutomationType automationType) {
        //Only limit the internal rate to change the speed at which this can be filled from an item
        return automationType == AutomationType.INTERNAL ? rate.getAsLong() : super.getInsertRate(automationType);
    }

    @Override
    protected long getExtractRate(@Nullable AutomationType automationType) {
        //Only limit the internal rate to change the speed at which this can be filled from an item
        return automationType == AutomationType.INTERNAL ? rate.getAsLong() : super.getExtractRate(automationType);
    }

    @Override
    public ChemicalStack insert(ChemicalStack stack, Action action, AutomationType automationType) {
        if (isCreative && isEmpty() && action.execute() && automationType != AutomationType.EXTERNAL) {
            //If a player manually inserts into a creative tank (or internally, via a GasInventorySlot), that is empty we need to allow setting the type,
            // Note: We check that it is not external insertion because an empty creative tanks acts as a "void" for automation
            ChemicalStack simulatedRemainder = super.insert(stack, Action.SIMULATE, automationType);
            if (simulatedRemainder.isEmpty()) {
                //If we are able to insert it then set perform the action of setting it to full
                setStackUnchecked(stack.copyWithAmount(getCapacity()));
            }
            return simulatedRemainder;
        }
        return super.insert(stack, action.combine(!isCreative), automationType);
    }

    @Override
    public ChemicalStack extract(long amount, Action action, AutomationType automationType) {
        return super.extract(amount, action.combine(!isCreative), automationType);
    }

    /**
     * {@inheritDoc}
     *
     * Note: We are only patching {@link #setStackSize(long, Action)}, as both {@link #growStack(long, Action)} and {@link #shrinkStack(long, Action)} are wrapped through
     * this method.
     */
    @Override
    public long setStackSize(long amount, Action action) {
        return super.setStackSize(amount, action.combine(!isCreative));
    }
}
