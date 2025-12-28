package com.jerry.mekanism_extras.common.content.network.transmitter;

import com.jerry.mekanism_extras.common.tier.transmitter.CTier;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.NBTConstants;
import mekanism.api.energy.IStrictEnergyHandler;
import mekanism.api.math.FloatingLong;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.content.network.EnergyNetwork;
import mekanism.common.content.network.transmitter.UniversalCable;
import mekanism.common.lib.transmitter.ConnectionType;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import mekanism.common.upgrade.transmitter.UniversalCableUpgradeData;
import mekanism.common.util.NBTUtils;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ExtraUniversalCable extends UniversalCable implements IExtraUpgradeableTransmitter<UniversalCableUpgradeData> {

    public ExtraUniversalCable(IBlockProvider blockProvider, TileEntityTransmitter tile) {
        super(blockProvider, tile);
    }

    @Override
    public void pullFromAcceptors() {
        Set<Direction> connections = getConnections(ConnectionType.PULL);
        if (!connections.isEmpty()) {
            for (IStrictEnergyHandler connectedAcceptor : getAcceptorCache().getConnectedAcceptors(connections)) {
                FloatingLong received = connectedAcceptor.extractEnergy(getAvailablePull(), Action.SIMULATE);
                if (!received.isZero() && takeEnergy(received, Action.SIMULATE).isZero()) {
                    // If we received some energy and are able to insert it all
                    FloatingLong remainder = takeEnergy(received, Action.EXECUTE);
                    connectedAcceptor.extractEnergy(received.subtract(remainder), Action.EXECUTE);
                }
            }
        }
    }

    private FloatingLong getAvailablePull() {
        if (hasTransmitterNetwork()) {
            return getCapacityAsFloatingLong().min(getTransmitterNetwork().energyContainer.getNeeded());
        }
        return getCapacityAsFloatingLong().min(buffer.getNeeded());
    }

    @NotNull
    public FloatingLong getCapacityAsFloatingLong() {
        return CTier.getCapacityAsFloatingLong(tier);
    }

    @Override
    public long getCapacity() {
        return this.getCapacityAsFloatingLong().longValue();
    }

    /**
     * @return remainder
     */
    private FloatingLong takeEnergy(FloatingLong amount, Action action) {
        if (hasTransmitterNetwork()) {
            return getTransmitterNetwork().energyContainer.insert(amount, action, AutomationType.INTERNAL);
        }
        return buffer.insert(amount, action, AutomationType.INTERNAL);
    }

    @Override
    protected void handleContentsUpdateTag(@NotNull EnergyNetwork network, @NotNull CompoundTag tag) {
        super.handleContentsUpdateTag(network, tag);
        NBTUtils.setFloatingLongIfPresent(tag, NBTConstants.ENERGY_STORED, network.energyContainer::setEnergy);
        NBTUtils.setFloatIfPresent(tag, NBTConstants.SCALE, scale -> network.currentScale = scale);
    }

    @Nullable
    @Override
    public UniversalCableUpgradeData getUpgradeData() {
        return super.getUpgradeData();
    }

    @Override
    public boolean dataTypeMatches(@NotNull TransmitterUpgradeData data) {
        return super.dataTypeMatches(data);
    }

    @Override
    public void parseUpgradeData(@NotNull UniversalCableUpgradeData data) {
        super.parseUpgradeData(data);
    }
}
