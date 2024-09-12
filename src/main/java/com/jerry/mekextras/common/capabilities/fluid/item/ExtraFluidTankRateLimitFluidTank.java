package com.jerry.mekextras.common.capabilities.fluid.item;

import com.jerry.mekextras.common.tier.FTTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.common.capabilities.fluid.BasicFluidTank;
import mekanism.common.capabilities.fluid.item.RateLimitFluidTank;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExtraFluidTankRateLimitFluidTank extends RateLimitFluidTank {

    public static ExtraFluidTankRateLimitFluidTank create(FTTier tier) {
        Objects.requireNonNull(tier, "Fluid tank tier cannot be null");
        return new ExtraFluidTankRateLimitFluidTank(tier, null);
    }

    private final boolean isCreative;

    private ExtraFluidTankRateLimitFluidTank(FTTier tier, @Nullable IContentsListener listener) {
        super(tier::getOutput, tier::getStorage, BasicFluidTank.alwaysTrueBi, BasicFluidTank.alwaysTrueBi, BasicFluidTank.alwaysTrue, listener);
        isCreative = false;
    }

    @Override
    public @NotNull FluidStack insert(@NotNull FluidStack stack, Action action, @NotNull AutomationType automationType) {
        return super.insert(stack, action.combine(!isCreative), automationType);
    }

    @Override
    public @NotNull FluidStack extract(int amount, Action action, @NotNull AutomationType automationType) {
        return super.extract(amount, action.combine(!isCreative), automationType);
    }

    @Override
    public int setStackSize(int amount, Action action) {
        return super.setStackSize(amount, action.combine(!isCreative));
    }
}
