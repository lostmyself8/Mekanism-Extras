package com.jerry.mekextra.common.content.network.transmitter;

import com.jerry.mekextra.common.util.IExtraUpgradeableTransmitter;
import com.jerry.mekextra.common.tier.transmitter.CTier;
import com.jerry.mekextra.common.tile.transmitter.ExtraTileEntityTransmitter;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.SerializationConstants;
import mekanism.api.energy.IMekanismStrictEnergyHandler;
import mekanism.api.energy.IStrictEnergyHandler;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.content.network.EnergyNetwork;
import mekanism.common.content.network.transmitter.UniversalCable;
import mekanism.common.lib.transmitter.ConnectionType;
import mekanism.common.lib.transmitter.acceptor.EnergyAcceptorCache;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import mekanism.common.upgrade.transmitter.UniversalCableUpgradeData;
import mekanism.common.util.EnumUtils;
import mekanism.common.util.NBTUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtraUniversalCable extends UniversalCable implements IMekanismStrictEnergyHandler,
        IExtraUpgradeableTransmitter<UniversalCableUpgradeData> {
    public ExtraUniversalCable(IBlockProvider blockProvider, ExtraTileEntityTransmitter tile) {
        super(blockProvider, tile);
    }

    @Override
    public void pullFromAcceptors() {
        if (!hasPullSide || getAvailablePull() <= 0) {
            return;
        }
        EnergyAcceptorCache acceptorCache = getAcceptorCache();
        for (Direction side : EnumUtils.DIRECTIONS) {
            if (!isConnectionType(side, ConnectionType.PULL)) {
                continue;
            }
            IStrictEnergyHandler connectedAcceptor = acceptorCache.getConnectedAcceptor(side);
            if (connectedAcceptor != null) {
                long received = connectedAcceptor.extractEnergy(getAvailablePull(), Action.SIMULATE);
                if (received > 0L && takeEnergy(received, Action.SIMULATE) == 0L) {
                    //If we received some energy and are able to insert it all
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
