package com.jerry.mekextras.common.content.network.transmitter;

import com.jerry.mekextras.common.capabilities.heat.ExtraVariableHeatCapacitor;
import com.jerry.mekextras.common.tier.transmitter.TCTier;
import mekanism.api.SerializationConstants;
import mekanism.api.heat.IHeatCapacitor;
import mekanism.api.heat.IHeatHandler;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.capabilities.heat.CachedAmbientTemperature;
import mekanism.common.capabilities.heat.ITileHeatHandler;
import mekanism.common.content.network.HeatNetwork;
import mekanism.common.content.network.transmitter.ThermodynamicConductor;
import mekanism.common.lib.transmitter.acceptor.AbstractAcceptorCache;
import mekanism.common.lib.transmitter.acceptor.AcceptorCache;
import mekanism.common.tier.ConductorTier;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import mekanism.common.upgrade.transmitter.ThermodynamicConductorUpgradeData;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import mekanism.common.util.NBTUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ExtraThermodynamicConductor extends ThermodynamicConductor implements ITileHeatHandler,
        IExtraUpgradeableTransmitter<ThermodynamicConductorUpgradeData> {
    private final CachedAmbientTemperature ambientTemperature = new CachedAmbientTemperature(this::getLevel, this::getBlockPos);
    public final ConductorTier tier;
    //Default to negative one, so we know we need to calculate it when needed
    private double clientTemperature = -1;
    private final List<IHeatCapacitor> capacitors;
    public final ExtraVariableHeatCapacitor buffer;
    public ExtraThermodynamicConductor(IBlockProvider blockProvider, TileEntityTransmitter tile) {
        super(blockProvider, tile);
        this.tier = Attribute.getTier(blockProvider, ConductorTier.class);
        buffer = ExtraVariableHeatCapacitor.create(TCTier.getHeatCapacity(tier), TCTier.getConduction(tier), TCTier.getConductionInsulation(tier), ambientTemperature, this);
        capacitors = Collections.singletonList(buffer);
    }

    @Override
    protected AbstractAcceptorCache<IHeatHandler, ?> createAcceptorCache() {
        return super.createAcceptorCache();
    }

    @Override
    public AcceptorCache<IHeatHandler> getAcceptorCache() {
        return super.getAcceptorCache();
    }

    @Override
    public void takeShare() {
        super.takeShare();
    }

    @Override
    protected boolean isValidAcceptor(@Nullable BlockEntity tile, Direction side) {
        //Note: We intentionally do not call super here as other elements in the network are intentionally acceptors
        return getAcceptorCache().getConnectedAcceptor(side) != null;
    }

    @NotNull
    @Override
    public CompoundTag write(HolderLookup.Provider provider, @NotNull CompoundTag tag) {
        super.write(provider, tag);
        ContainerType.HEAT.saveTo(provider, tag, getHeatCapacitors(null));
        return tag;
    }

    @Override
    public void read(HolderLookup.Provider provider, @NotNull CompoundTag tag) {
        super.read(provider, tag);
        ContainerType.HEAT.readFrom(provider, tag, getHeatCapacitors(null));
    }

    @NotNull
    @Override
    public CompoundTag getReducedUpdateTag(HolderLookup.@NotNull Provider provider, CompoundTag updateTag) {
        updateTag = super.getReducedUpdateTag(provider, updateTag);
        updateTag.putDouble(SerializationConstants.TEMPERATURE, buffer.getHeat());
        return updateTag;
    }

    @NotNull
    @Override
    public List<IHeatCapacitor> getHeatCapacitors(Direction side) {
        return capacitors;
    }

    @Override
    public void handleUpdateTag(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.handleUpdateTag(tag, provider);
        NBTUtils.setDoubleIfPresent(tag, SerializationConstants.TEMPERATURE, buffer::setHeat);
    }

    @Override
    public void onContentsChanged() {
        if (!isRemote()) {
            if (clientTemperature == -1) {
                clientTemperature = ambientTemperature.getAsDouble();
            }
            if (Math.abs(buffer.getTemperature() - clientTemperature) > (buffer.getTemperature() / 20)) {
                clientTemperature = buffer.getTemperature();
                getTransmitterTile().sendUpdatePacket();
            }
        }
        getTransmitterTile().setChanged();
    }

    @Override
    public double getAmbientTemperature(@NotNull Direction side) {
        return ambientTemperature.getTemperature(side);
    }

    @Nullable
    @Override
    public IHeatHandler getAdjacent(@NotNull Direction side) {
        if (connectionMapContainsSide(getAllCurrentConnections(), side)) {
            //Note: We use the acceptor cache as the heat network is different and the transmitters count the other transmitters in the
            // network as valid acceptors, which means we don't have to differentiate between acceptors and other transmitters here
            return getAcceptorCache().getConnectedAcceptor(side);
        }
        return null;
    }

    @Override
    public double incrementAdjacentTransfer(double currentAdjacentTransfer, double tempToTransfer, @NotNull Direction side) {
        if (tempToTransfer > 0 && hasTransmitterNetwork()) {
            HeatNetwork transmitterNetwork = getTransmitterNetwork();
            ThermodynamicConductor adjacent = transmitterNetwork.getTransmitter(getBlockPos().relative(side));
            if (adjacent != null) {
                //Heat transmitter to heat transmitter, don't count as "adjacent transfer"
                return currentAdjacentTransfer;
            }
        }
        return super.incrementAdjacentTransfer(currentAdjacentTransfer, tempToTransfer, side);
    }

    @Nullable
    @Override
    public ThermodynamicConductorUpgradeData getUpgradeData() {
        return super.getUpgradeData();
    }

    @Override
    public boolean dataTypeMatches(@NotNull TransmitterUpgradeData data) {
        return super.dataTypeMatches(data);
    }

    @Override
    public void parseUpgradeData(@NotNull ThermodynamicConductorUpgradeData data) {
        super.parseUpgradeData(data);
    }
}
