package com.jerry.mekanism_extras.common.capabilities.energy;

import com.jerry.mekanism_extras.common.tier.ECTier;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.energy.BasicEnergyContainer;

import net.minecraft.MethodsReturnNonnullByDefault;

import java.util.Objects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@NothingNullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ExtraEnergyCubeEnergyContainer extends BasicEnergyContainer {

    private final FloatingLong rate;

    public static ExtraEnergyCubeEnergyContainer create(ECTier tier, @Nullable IContentsListener listener) {
        Objects.requireNonNull(tier, "Energy cube tier cannot be null");
        return new ExtraEnergyCubeEnergyContainer(tier, listener);
    }

    private ExtraEnergyCubeEnergyContainer(ECTier tier, @Nullable IContentsListener listener) {
        super(tier.getMaxEnergy(), alwaysTrue, alwaysTrue, listener);
        Objects.requireNonNull(tier);
        this.rate = tier.getOutput();
    }

    protected FloatingLong getRate(@Nullable AutomationType automationType) {
        return automationType == AutomationType.INTERNAL ? this.rate : super.getRate(automationType);
    }

    public FloatingLong insert(FloatingLong amount, Action action, AutomationType automationType) {
        return super.insert(amount, action.combine(true), automationType);
    }

    public FloatingLong extract(FloatingLong amount, Action action, AutomationType automationType) {
        return super.extract(amount, action.combine(true), automationType);
    }
}
