package com.jerry.mekextras.common.content.network.transmitter;

import com.jerry.mekextras.common.tier.CTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.SerializationConstants;
import mekanism.api.energy.IMekanismStrictEnergyHandler;
import mekanism.api.energy.IStrictEnergyHandler;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.content.network.EnergyNetwork;
import mekanism.common.content.network.transmitter.UniversalCable;
import mekanism.common.lib.transmitter.ConnectionType;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import mekanism.common.upgrade.transmitter.UniversalCableUpgradeData;
import mekanism.common.util.NBTUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ExtraUniversalCable extends UniversalCable implements IMekanismStrictEnergyHandler,
        IExtraUpgradeableTransmitter<UniversalCableUpgradeData> {
    public ExtraUniversalCable(IBlockProvider blockProvider, TileEntityTransmitter tile) {
        super(blockProvider, tile);
    }

    @Override
    public void pullFromAcceptors() {
        Set<Direction> connections = getConnections(ConnectionType.PULL);
        if (!connections.isEmpty()) {
            for (IStrictEnergyHandler connectedAcceptor : getAcceptorCache().getConnectedAcceptors(connections)) {
                long received = connectedAcceptor.extractEnergy(getAvailablePull(), Action.SIMULATE);
                if (received > 0L && takeEnergy(received, Action.SIMULATE) == 0L) {
                    long remainder = takeEnergy(received, Action.EXECUTE);
                    connectedAcceptor.extractEnergy(received - remainder, Action.EXECUTE);
                }
            }
        }
    }

    private long getAvailablePull() {
        if (hasTransmitterNetwork()) {
            return Math.min(getCapacity(), getTransmitterNetwork().energyContainer.getNeeded());
        }
        return Math.min(getCapacity(), buffer.getNeeded());
    }

    @NotNull
    public long getCapacityAsFloatingLong() {
        return CTier.getCapacityAsLong(tier);
    }

    @Override
    public long getCapacity() {
        return CTier.getCapacityAsLong(tier);
    }

    private long takeEnergy(long amount, Action action) {
        if (hasTransmitterNetwork()) {
            return getTransmitterNetwork().energyContainer.insert(amount, action, AutomationType.INTERNAL);
        }
        return buffer.insert(amount, action, AutomationType.INTERNAL);
    }

    @Override
    protected void handleContentsUpdateTag(@NotNull EnergyNetwork network, @NotNull CompoundTag tag, @NotNull HolderLookup.Provider provider) {
        super.handleContentsUpdateTag(network, tag, provider);
        NBTUtils.setLegacyEnergyIfPresent(tag, SerializationConstants.ENERGY, network.energyContainer::setEnergy);
        NBTUtils.setFloatIfPresent(tag, SerializationConstants.SCALE, scale -> network.currentScale = scale);
    }

    @Override
    public @Nullable UniversalCableUpgradeData getUpgradeData() {
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
