package com.jerry.mekanism_extras.common.capabilities.chemical.item;

import com.jerry.mekanism_extras.common.capabilities.chemical.variable.ExtraRateLimitChemicalTank;
import com.jerry.mekanism_extras.common.tier.CTTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasHandler;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.chemical.infuse.IInfusionHandler;
import mekanism.api.chemical.infuse.IInfusionTank;
import mekanism.api.chemical.infuse.InfuseType;
import mekanism.api.chemical.infuse.InfusionStack;
import mekanism.api.chemical.pigment.IPigmentHandler;
import mekanism.api.chemical.pigment.IPigmentTank;
import mekanism.api.chemical.pigment.Pigment;
import mekanism.api.chemical.pigment.PigmentStack;
import mekanism.api.chemical.slurry.ISlurryHandler;
import mekanism.api.chemical.slurry.ISlurryTank;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ExtraChemicalTankRateLimitChemicalTank<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>> extends ExtraRateLimitChemicalTank<CHEMICAL, STACK> {
    private final boolean isCreative;

    private ExtraChemicalTankRateLimitChemicalTank(CTTier tier, ChemicalTankBuilder<CHEMICAL, STACK, ?> tankBuilder, @Nullable IContentsListener listener) {
        super(tier::getOutput, tier::getStorage, tankBuilder.alwaysTrueBi, tankBuilder.alwaysTrueBi, tankBuilder.alwaysTrue, null, listener);
        isCreative = false;
    }

    @Override
    public @NotNull STACK insert(@NotNull STACK stack, Action action, @NotNull AutomationType automationType) {
        return super.insert(stack, action.combine(!isCreative), automationType);
    }

    @Override
    public @NotNull STACK extract(long amount, Action action, @NotNull AutomationType automationType) {
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

    public static class GasTankRateLimitChemicalTank extends ExtraChemicalTankRateLimitChemicalTank<Gas, GasStack> implements IGasHandler, IGasTank {

        public GasTankRateLimitChemicalTank(CTTier tier, @Nullable IContentsListener listener) {
            super(tier, ChemicalTankBuilder.GAS, listener);
        }
    }

    public static class InfusionTankRateLimitChemicalTank extends ExtraChemicalTankRateLimitChemicalTank<InfuseType, InfusionStack> implements IInfusionHandler, IInfusionTank {

        public InfusionTankRateLimitChemicalTank(CTTier tier, @Nullable IContentsListener listener) {
            super(tier, ChemicalTankBuilder.INFUSION, listener);
        }
    }

    public static class PigmentTankRateLimitChemicalTank extends ExtraChemicalTankRateLimitChemicalTank<Pigment, PigmentStack> implements IPigmentHandler, IPigmentTank {

        public PigmentTankRateLimitChemicalTank(CTTier tier, @Nullable IContentsListener listener) {
            super(tier, ChemicalTankBuilder.PIGMENT, listener);
        }
    }

    public static class SlurryTankRateLimitChemicalTank extends ExtraChemicalTankRateLimitChemicalTank<Slurry, SlurryStack> implements ISlurryHandler, ISlurryTank {

        public SlurryTankRateLimitChemicalTank(CTTier tier, @Nullable IContentsListener listener) {
            super(tier, ChemicalTankBuilder.SLURRY, listener);
        }
    }
}
