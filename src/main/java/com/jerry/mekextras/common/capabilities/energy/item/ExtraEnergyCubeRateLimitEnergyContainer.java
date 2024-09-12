package com.jerry.mekextras.common.capabilities.energy.item;

import com.jerry.mekextras.common.tier.ECTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import mekanism.common.capabilities.energy.item.RateLimitEnergyContainer;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@NothingNullByDefault
public class ExtraEnergyCubeRateLimitEnergyContainer extends RateLimitEnergyContainer {
    public static ExtraEnergyCubeRateLimitEnergyContainer create(ECTier tier) {
        Objects.requireNonNull(tier, "Energy cube tier cannot be null");
        return new ExtraEnergyCubeRateLimitEnergyContainer(tier, null);
    }

    private final boolean isCreative;

    private ExtraEnergyCubeRateLimitEnergyContainer(ECTier tier, @Nullable IContentsListener listener) {
        super(tier::getOutput, tier::getMaxEnergy, BasicEnergyContainer.alwaysTrue, BasicEnergyContainer.alwaysTrue, listener);
        isCreative = false;
    }

    @Override
    public FloatingLong insert(FloatingLong amount, Action action, AutomationType automationType) {
        return super.insert(amount, action.combine(!isCreative), automationType);
    }

    @Override
    public FloatingLong extract(FloatingLong amount, Action action, AutomationType automationType) {
        return super.extract(amount, action.combine(!isCreative), automationType);
    }
}
