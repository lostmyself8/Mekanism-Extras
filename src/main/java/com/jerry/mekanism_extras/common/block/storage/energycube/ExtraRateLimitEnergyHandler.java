package com.jerry.mekanism_extras.common.block.storage.energycube;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.jerry.mekanism_extras.common.util.ExtraFloatingLong;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.energy.IMekanismStrictEnergyHandler;
import mekanism.api.math.FloatingLong;
import mekanism.api.math.FloatingLongSupplier;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import mekanism.common.capabilities.energy.VariableCapacityEnergyContainer;
import mekanism.common.capabilities.energy.item.ItemStackEnergyHandler;
import net.minecraft.MethodsReturnNonnullByDefault;
import org.checkerframework.checker.nullness.qual.NonNull;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ExtraRateLimitEnergyHandler extends ItemStackEnergyHandler {
    public static ExtraRateLimitEnergyHandler create(ECTier tier) {
        Objects.requireNonNull(tier, "Energy cube tier cannot be null");
        return new ExtraRateLimitEnergyHandler(handler -> new EnergyCubeRateLimitEnergyContainer(tier, handler));
    }

    public static ExtraRateLimitEnergyHandler create(FloatingLongSupplier capacity, Predicate<@NonNull AutomationType> canExtract, Predicate<@NonNull AutomationType> canInsert) {
        return create(() -> capacity.get().multiply(0.005), capacity, canExtract, canInsert);
    }

    public static ExtraRateLimitEnergyHandler create(FloatingLongSupplier rate, FloatingLongSupplier capacity, Predicate<@NonNull AutomationType> canExtract,
                                                     Predicate<@NonNull AutomationType> canInsert) {
        Objects.requireNonNull(rate, "Rate supplier cannot be null");
        Objects.requireNonNull(capacity, "Capacity supplier cannot be null");
        Objects.requireNonNull(canExtract, "Extraction validity check cannot be null");
        Objects.requireNonNull(canInsert, "Insertion validity check cannot be null");
        return new ExtraRateLimitEnergyHandler(handler -> new RateLimitEnergyContainer(rate, capacity, canExtract, canInsert, handler));
    }

    private final IEnergyContainer energyContainer;

    private ExtraRateLimitEnergyHandler(Function<IMekanismStrictEnergyHandler, IEnergyContainer> energyContainerProvider) {
        this.energyContainer = energyContainerProvider.apply(this);
    }

    @Override
    protected List<IEnergyContainer> getInitialContainers() {
        return Collections.singletonList(energyContainer);
    }

    private static class RateLimitEnergyContainer extends VariableCapacityEnergyContainer {

        private final FloatingLongSupplier rate;

        private RateLimitEnergyContainer(FloatingLongSupplier rate, FloatingLongSupplier capacity, Predicate<@NonNull AutomationType> canExtract,
                                         Predicate<@NonNull AutomationType> canInsert, @Nullable IContentsListener listener) {
            super(capacity, canExtract, canInsert, listener);
            this.rate = rate;
        }

        @Override
        protected FloatingLong getRate(@Nullable AutomationType automationType) {
            //Allow unknown or manual interaction to bypass rate limit for the item
            return automationType == null || automationType == AutomationType.MANUAL ? super.getRate(automationType) : rate.get();
        }
    }

    private static class EnergyCubeRateLimitEnergyContainer extends RateLimitEnergyContainer {

        private final boolean isCreative;

        private EnergyCubeRateLimitEnergyContainer(ECTier tier, @Nullable IContentsListener listener) {
            super(new ExtraFloatingLong(tier.getOutput()), new ExtraFloatingLong(tier.getMaxEnergy()), BasicEnergyContainer.alwaysTrue, BasicEnergyContainer.alwaysTrue, listener);
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
}
