package com.jerry.mekanism_extras.common.capabilities.fluid;

import com.jerry.mekanism_extras.common.tile.ExtraTileEntityFluidTank;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.capabilities.fluid.BasicFluidTank;
import mekanism.common.tile.TileEntityFluidTank;
import mekanism.common.util.WorldUtils;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.IntSupplier;

@NothingNullByDefault
public class ExtraFluidTankFluidTank extends BasicFluidTank {
    public static ExtraFluidTankFluidTank create(ExtraTileEntityFluidTank tile, @Nullable IContentsListener listener) {
        Objects.requireNonNull(tile, "Fluid tank tile entity cannot be null");
        return new ExtraFluidTankFluidTank(tile, listener);
    }

    private final ExtraTileEntityFluidTank tile;
    private final boolean isCreative;
    private final IntSupplier rate;

    private ExtraFluidTankFluidTank(ExtraTileEntityFluidTank tile, @Nullable IContentsListener listener) {
        super(tile.tier.getStorage(), alwaysTrueBi, alwaysTrueBi, alwaysTrue, listener);
        this.tile = tile;
        rate = tile.tier::getOutput;
        isCreative = false;
    }

    @Override
    protected int getRate(@Nullable AutomationType automationType) {
        //Only limit the internal rate to change the speed at which this can be filled from an item
        return automationType == AutomationType.INTERNAL ? rate.getAsInt() : super.getRate(automationType);
    }

    @Override
    public FluidStack insert(FluidStack stack, Action action, AutomationType automationType) {
        FluidStack remainder;
        if (isCreative && isEmpty() && action.execute() && automationType != AutomationType.EXTERNAL) {
            //If a player manually inserts into a creative tank (or internally, via a FluidInventorySlot), that is empty we need to allow setting the type,
            // Note: We check that it is not external insertion because an empty creative tanks acts as a "void" for automation
            remainder = super.insert(stack, Action.SIMULATE, automationType);
            if (remainder.isEmpty()) {
                //If we are able to insert it then set perform the action of setting it to full
                setStackUnchecked(new FluidStack(stack, getCapacity()));
            }
        } else {
            remainder = super.insert(stack, action.combine(!isCreative), automationType);
        }
        if (!remainder.isEmpty()) {
            //If we have any leftover check if we can send it to the tank that is above
            TileEntityFluidTank tileAbove = WorldUtils.getTileEntity(TileEntityFluidTank.class, this.tile.getLevel(), this.tile.getBlockPos().above());
            if (tileAbove != null) {
                //Note: We do external so that it is not limited by the internal rate limits
                remainder = tileAbove.fluidTank.insert(remainder, action, AutomationType.EXTERNAL);
            }
        }
        return remainder;
    }

    @Override
    public int growStack(int amount, Action action) {
        int grownAmount = super.growStack(amount, action);
        if (amount > 0 && grownAmount < amount) {
            //If we grew our stack less than we tried to, and we were actually growing and not shrinking it
            // try inserting into above tiles
            if (!tile.getActive()) {
                ExtraTileEntityFluidTank tileAbove = WorldUtils.getTileEntity(ExtraTileEntityFluidTank.class, this.tile.getLevel(), this.tile.getBlockPos().above());
                if (tileAbove != null) {
                    int leftOverToInsert = amount - grownAmount;
                    //Note: We do external so that it is not limited by the internal rate limits
                    FluidStack remainder = tileAbove.fluidTank.insert(new FluidStack(stored, leftOverToInsert), action, AutomationType.EXTERNAL);
                    grownAmount += leftOverToInsert - remainder.getAmount();
                }
            }
        }
        return grownAmount;
    }

    @Override
    public FluidStack extract(int amount, Action action, AutomationType automationType) {
        return super.extract(amount, action.combine(!isCreative), automationType);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Note: We are only patching {@link #setStackSize(int, Action)}, as both {@link #growStack(int, Action)} and {@link #shrinkStack(int, Action)} are wrapped through
     * this method.
     */
    @Override
    public int setStackSize(int amount, Action action) {
        return super.setStackSize(amount, action.combine(!isCreative));
    }
}
