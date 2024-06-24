package com.jerry.mekanism_extras.common.block.storage.energycube;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import net.minecraft.MethodsReturnNonnullByDefault;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

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

    protected @NotNull FloatingLong getRate(@Nullable AutomationType automationType) {
        return automationType == AutomationType.INTERNAL ? this.rate : super.getRate(automationType);
    }

    public @NotNull FloatingLong insert(@NotNull FloatingLong amount, Action action, @NotNull AutomationType automationType) {
        return super.insert(amount, action.combine(true), automationType);
    }

    public @NotNull FloatingLong extract(@NotNull FloatingLong amount, Action action, @NotNull AutomationType automationType) {
        return super.extract(amount, action.combine(true), automationType);
    }
}
