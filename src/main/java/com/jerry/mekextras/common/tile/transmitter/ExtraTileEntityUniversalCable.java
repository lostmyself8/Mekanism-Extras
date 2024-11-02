package com.jerry.mekextras.common.tile.transmitter;

import com.jerry.mekextras.api.tier.AdvanceTier;
import com.jerry.mekextras.common.content.network.transmitter.ExtraUniversalCable;
import com.jerry.mekextras.common.registry.ExtraBlocks;
import mekanism.api.SerializationConstants;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.math.MathUtils;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.block.states.TransmitterType;
import mekanism.common.capabilities.energy.DynamicStrictEnergyHandler;
import mekanism.common.capabilities.resolver.manager.EnergyHandlerManager;
import mekanism.common.content.network.EnergyNetwork;
import mekanism.common.integration.computer.IComputerTile;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.integration.energy.EnergyCompatUtils;
import mekanism.common.lib.transmitter.ConnectionType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ExtraTileEntityUniversalCable extends ExtraTileEntityTransmitter implements IComputerTile {

    private final EnergyHandlerManager energyHandlerManager;
    public ExtraTileEntityUniversalCable(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        addCapabilityResolver(energyHandlerManager = new EnergyHandlerManager(direction -> {
            ExtraUniversalCable cable = getTransmitter();
            if (direction != null && (cable.getConnectionTypeRaw(direction) == ConnectionType.NONE) || cable.isRedstoneActivated()) {
                return Collections.emptyList();
            }
            return cable.getEnergyContainers(direction);
        }, new DynamicStrictEnergyHandler(this::getEnergyContainers, getExtractPredicate(), getInsertPredicate(), null)));
    }

    @Override
    protected ExtraUniversalCable createTransmitter(IBlockProvider blockProvider) {
        return new ExtraUniversalCable(blockProvider, this);
    }

    @Override
    public ExtraUniversalCable getTransmitter() {
        return (ExtraUniversalCable) super.getTransmitter();
    }

    @Override
    protected void onUpdateServer() {
        getTransmitter().pullFromAcceptors();
        super.onUpdateServer();
    }

    @Override
    public TransmitterType getTransmitterType() {
        return TransmitterType.UNIVERSAL_CABLE;
    }

    @NotNull
    @Override
    protected BlockState upgradeResult(@NotNull BlockState current, @NotNull AdvanceTier tier) {
        return BlockStateHelper.copyStateData(current, switch (tier) {
            case ABSOLUTE -> ExtraBlocks.ABSOLUTE_UNIVERSAL_CABLE;
            case SUPREME -> ExtraBlocks.SUPREME_UNIVERSAL_CABLE;
            case COSMIC -> ExtraBlocks.COSMIC_UNIVERSAL_CABLE;
            case INFINITE -> ExtraBlocks.INFINITE_UNIVERSAL_CABLE;
        });
    }

    @NotNull
    @Override
    public CompoundTag getUpdateTag(HolderLookup.@NotNull Provider provider) {
        //Note: We add the stored information to the initial update tag and not to the one we sync on side changes which uses getReducedUpdateTag
        CompoundTag updateTag = super.getUpdateTag(provider);
        if (getTransmitter().hasTransmitterNetwork()) {
            EnergyNetwork network = getTransmitter().getTransmitterNetwork();
            updateTag.putLong(SerializationConstants.ENERGY, network.energyContainer.getEnergy());
            updateTag.putFloat(SerializationConstants.SCALE, network.currentScale);
        }
        return updateTag;
    }

    private List<IEnergyContainer> getEnergyContainers(@Nullable Direction side) {
        return energyHandlerManager.getContainers(side);
    }

    @Override
    public void sideChanged(@NotNull Direction side, @NotNull ConnectionType old, @NotNull ConnectionType type) {
        super.sideChanged(side, old, type);
        if (type == ConnectionType.NONE) {
            //We no longer have a capability, invalidate it, which will also notify the level
            invalidateCapabilities(EnergyCompatUtils.getLoadedEnergyCapabilities(), side);
        } else if (old == ConnectionType.NONE) {
            //Notify any listeners to our position that we now do have a capability
            //Note: We don't invalidate our impls because we know they are already invalid, so we can short circuit setting them to null from null
            invalidateCapabilities();
        }
    }

    @Override
    public void redstoneChanged(boolean powered) {
        super.redstoneChanged(powered);
        if (powered) {
            invalidateCapabilitiesAll(EnergyCompatUtils.getLoadedEnergyCapabilities());
        } else {
            invalidateCapabilities();
        }
    }

    //Methods relating to IComputerTile
    @Override
    public String getComputerName() {
        return getTransmitter().getTier().getBaseTier().getLowerName() + "UniversalCable";
    }

    @ComputerMethod
    long getBuffer() {
        return getTransmitter().getBufferWithFallback();
    }

    @ComputerMethod
    long getCapacity() {
        ExtraUniversalCable cable = getTransmitter();
        return cable.hasTransmitterNetwork() ? cable.getTransmitterNetwork().getCapacity() : cable.getCapacity();
    }

    @ComputerMethod
    long getNeeded() {
        return getCapacity() - getBuffer();
    }

    @ComputerMethod
    double getFilledPercentage() {
        return MathUtils.divideToLevel(getBuffer(), getCapacity());
    }
}
