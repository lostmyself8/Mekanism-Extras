package com.jerry.mekextra.common.attachments.containers.fluid;

import com.jerry.mekextra.common.item.block.machine.ExtraItemBlockFluidTank;
import com.jerry.mekextra.common.tier.FTTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.fluid.AttachedFluids;
import mekanism.common.attachments.containers.fluid.ComponentBackedFluidTank;
import mekanism.common.capabilities.fluid.BasicFluidTank;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

@NothingNullByDefault
public class ExtraComponentBackedFluidTankFluidTank extends ComponentBackedFluidTank {

    private final boolean isCreative;

    public static ExtraComponentBackedFluidTankFluidTank create(ContainerType<?, ?, ?> ignored, ItemStack attachedTo, int tankIndex) {
        if (!(attachedTo.getItem() instanceof ExtraItemBlockFluidTank item)) {
            throw new IllegalStateException("Attached to should always be a fluid tank item");
        }
        return new ExtraComponentBackedFluidTankFluidTank(attachedTo, tankIndex, item.getAdvanceTier());
    }

    private ExtraComponentBackedFluidTankFluidTank(ItemStack attachedTo, int tankIndex, FTTier tier) {
        super(attachedTo, tankIndex, BasicFluidTank.alwaysTrueBi, BasicFluidTank.alwaysTrueBi, BasicFluidTank.alwaysTrue, tier::getOutput, tier::getStorage);
        isCreative = false;
    }

    @Override
    public @NotNull FluidStack insert(@NotNull FluidStack stack, Action action, @NotNull AutomationType automationType) {
        return super.insert(stack, action.combine(!isCreative), automationType);
    }

    @Override
    public @NotNull FluidStack extract(@NotNull AttachedFluids attachedFluids, @NotNull FluidStack stored, int amount, Action action, @NotNull AutomationType automationType) {
        return super.extract(attachedFluids, stored, amount, action.combine(!isCreative), automationType);
    }

    /**
     * {@inheritDoc}
     *
     * Note: We are only patching {@link #setStackSize(AttachedFluids, FluidStack, int, Action)}, as both {@link #growStack(int, Action)} and
     * {@link #shrinkStack(int, Action)} are wrapped through this method.
     */
    @Override
    public int setStackSize(@NotNull AttachedFluids attachedFluids, @NotNull FluidStack stored, int amount, Action action) {
        return super.setStackSize(attachedFluids, stored, amount, action.combine(!isCreative));
    }
}
