package com.jerry.mekextras.common.capabilities.energy;

import com.jerry.mekextras.common.tier.ECTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.math.FloatingLong;
import mekanism.api.math.FloatingLongSupplier;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExtraEnergyCubeEnergyContainer extends BasicEnergyContainer {

    public static ExtraEnergyCubeEnergyContainer create(ECTier tier, @Nullable IContentsListener listener) {
        Objects.requireNonNull(tier, "Energy cube tier cannot be null");
        return new ExtraEnergyCubeEnergyContainer(tier, listener);
    }

    private final boolean isCreative;
    private final FloatingLongSupplier rate;

    protected ExtraEnergyCubeEnergyContainer(ECTier tier, @Nullable IContentsListener listener) {
        super(tier.getMaxEnergy(), alwaysTrue, alwaysTrue, listener);
        isCreative = false;
        rate = tier::getOutput;
    }

    @Override
    protected @NotNull FloatingLong getRate(@Nullable AutomationType automationType) {
        //Only limit the internal rate to change the speed at which this can be filled from an item
        return automationType == AutomationType.INTERNAL ? rate.get() : super.getRate(automationType);
    }

    @Override
    public @NotNull FloatingLong insert(@NotNull FloatingLong amount, Action action, @NotNull AutomationType automationType) {
        //Note: Unlike other creative items, the creative energy cube does not allow changing it to always full
        return super.insert(amount, action.combine(!isCreative), automationType);
    }

    @Override
    public @NotNull FloatingLong extract(@NotNull FloatingLong amount, Action action, @NotNull AutomationType automationType) {
        return super.extract(amount, action.combine(!isCreative), automationType);
    }
}
