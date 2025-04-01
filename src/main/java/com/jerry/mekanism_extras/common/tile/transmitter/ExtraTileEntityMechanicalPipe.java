package com.jerry.mekanism_extras.common.tile.transmitter;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.common.content.network.transmitter.ExtraMechanicalPipe;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import mekanism.api.NBTConstants;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.block.states.TransmitterType;
import mekanism.common.capabilities.fluid.DynamicFluidHandler;
import mekanism.common.capabilities.resolver.manager.FluidHandlerManager;
import mekanism.common.content.network.FluidNetwork;
import mekanism.common.integration.computer.ComputerCapabilityHelper;
import mekanism.common.integration.computer.IComputerTile;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.lib.transmitter.ConnectionType;
import mekanism.common.util.EnumUtils;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ExtraTileEntityMechanicalPipe extends ExtraTileEntityTransmitter implements IComputerTile {
    private final FluidHandlerManager fluidHandlerManager;

    public ExtraTileEntityMechanicalPipe(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        addCapabilityResolver(fluidHandlerManager = new FluidHandlerManager(direction -> {
            ExtraMechanicalPipe pipe = getTransmitter();
            if (direction != null && (pipe.getConnectionTypeRaw(direction) == ConnectionType.NONE) || pipe.isRedstoneActivated()) {
                //If we actually have a side, and our connection type on that side is none, or we are currently activated by redstone,
                // then return that we have no tanks
                return Collections.emptyList();
            }
            return pipe.getFluidTanks(direction);
        }, new DynamicFluidHandler(this::getFluidTanks, getExtractPredicate(), getInsertPredicate(), null)));
        ComputerCapabilityHelper.addComputerCapabilities(this, this::addCapabilityResolver);
    }

    @Override
    protected ExtraMechanicalPipe createTransmitter(IBlockProvider blockProvider) {
        return new ExtraMechanicalPipe(blockProvider, this);
    }

    @Override
    public ExtraMechanicalPipe getTransmitter() {
        return (ExtraMechanicalPipe) super.getTransmitter();
    }

    @Override
    protected void onUpdateServer() {
        getTransmitter().pullFromAcceptors();
        super.onUpdateServer();
    }

    @Override
    public TransmitterType getTransmitterType() {
        return TransmitterType.MECHANICAL_PIPE;
    }

    @NotNull
    @Override
    protected BlockState upgradeResult(@NotNull BlockState current, @NotNull AdvancedTier tier) {
        return BlockStateHelper.copyStateData(current, switch (tier) {
            case ABSOLUTE -> ExtraBlock.ABSOLUTE_MECHANICAL_PIPE;
            case SUPREME -> ExtraBlock.SUPREME_MECHANICAL_PIPE;
            case COSMIC -> ExtraBlock.COSMIC_MECHANICAL_PIPE;
            case INFINITE -> ExtraBlock.INFINITE_MECHANICAL_PIPE;
        });
    }

    @NotNull
    @Override
    public CompoundTag getUpdateTag() {
        //Note: We add the stored information to the initial update tag and not to the one we sync on side changes which uses getReducedUpdateTag
        CompoundTag updateTag = super.getUpdateTag();
        if (getTransmitter().hasTransmitterNetwork()) {
            FluidNetwork network = getTransmitter().getTransmitterNetwork();
            updateTag.put(NBTConstants.FLUID_STORED, network.lastFluid.writeToNBT(new CompoundTag()));
            updateTag.putFloat(NBTConstants.SCALE, network.currentScale);
        }
        return updateTag;
    }

    private List<IExtendedFluidTank> getFluidTanks(@Nullable Direction side) {
        return fluidHandlerManager.getContainers(side);
    }

    @Override
    public void sideChanged(@NotNull Direction side, @NotNull ConnectionType old, @NotNull ConnectionType type) {
        super.sideChanged(side, old, type);
        if (type == ConnectionType.NONE) {
            invalidateCapability(ForgeCapabilities.FLUID_HANDLER, side);
            //Notify the neighbor on that side our state changed and we no longer have a capability
            WorldUtils.notifyNeighborOfChange(level, side, worldPosition);
        } else if (old == ConnectionType.NONE) {
            //Notify the neighbor on that side our state changed, and we now do have a capability
            WorldUtils.notifyNeighborOfChange(level, side, worldPosition);
        }
    }

    @Override
    public void redstoneChanged(boolean powered) {
        super.redstoneChanged(powered);
        if (powered) {
            //The transmitter now is powered by redstone and previously was not
            //Note: While at first glance the below invalidation may seem over aggressive, it is not actually that aggressive as
            // if a cap has not been initialized yet on a side then invalidating it will just NO-OP
            invalidateCapability(ForgeCapabilities.FLUID_HANDLER, EnumUtils.DIRECTIONS);
        }
        //Note: We do not have to invalidate any caps if we are going from powered to unpowered as all the caps would already be "empty"
    }

    //Methods relating to IComputerTile
    @Override
    public String getComputerName() {
        return getTransmitter().getTier().getBaseTier().getLowerName() + "MechanicalPipe";
    }

    @ComputerMethod
    FluidStack getBuffer() {
        return getTransmitter().getBufferWithFallback();
    }

    @ComputerMethod
    long getCapacity() {
        ExtraMechanicalPipe pipe = getTransmitter();
        return pipe.hasTransmitterNetwork() ? pipe.getTransmitterNetwork().getCapacity() : pipe.getCapacity();
    }

    @ComputerMethod
    long getNeeded() {
        return getCapacity() - getBuffer().getAmount();
    }

    @ComputerMethod
    double getFilledPercentage() {
        return getBuffer().getAmount() / (double) getCapacity();
    }
    //End methods IComputerTile
}
