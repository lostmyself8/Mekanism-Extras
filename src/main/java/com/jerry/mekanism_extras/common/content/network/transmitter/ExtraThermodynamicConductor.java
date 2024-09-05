package com.jerry.mekanism_extras.common.content.network.transmitter;

import com.jerry.mekanism_extras.common.capabilities.heat.ExtraVariableHeatCapacitor;
import com.jerry.mekanism_extras.common.tier.transmitter.TCTier;
import mekanism.api.DataHandlerUtils;
import mekanism.api.NBTConstants;
import mekanism.api.heat.IHeatCapacitor;
import mekanism.api.heat.IHeatHandler;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.heat.CachedAmbientTemperature;
import mekanism.common.content.network.transmitter.ThermodynamicConductor;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.lib.transmitter.acceptor.AcceptorCache;
import mekanism.common.tier.ConductorTier;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import mekanism.common.upgrade.transmitter.ThermodynamicConductorUpgradeData;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import mekanism.common.util.NBTUtils;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ExtraThermodynamicConductor extends ThermodynamicConductor implements IExtraUpgradeableTransmitter<ThermodynamicConductorUpgradeData> {
    private final CachedAmbientTemperature ambientTemperature = new CachedAmbientTemperature(this::getTileWorld, this::getTilePos);
    //Default to negative one, so we know we need to calculate it when needed
    public final ConductorTier tier;
    private final List<IHeatCapacitor> capacitors;
    public final ExtraVariableHeatCapacitor buffer;
    private double clientTemperature = -1;

    public ExtraThermodynamicConductor(IBlockProvider blockProvider, TileEntityTransmitter tile) {
        super(blockProvider, tile);
        this.tier = Attribute.getTier(blockProvider, ConductorTier.class);
        buffer = ExtraVariableHeatCapacitor.create(TCTier.getHeatCapacity(tier), TCTier.getConduction(tier), TCTier.getConductionInsulation(tier), ambientTemperature, this);
        capacitors = Collections.singletonList(buffer);
    }

    @Override
    public AcceptorCache<IHeatHandler> getAcceptorCache() {
        //Cast it here to make things a bit easier, as we know createAcceptorCache by default returns an object of type AcceptorCache
        return super.getAcceptorCache();
    }

    @Override
    public boolean isValidAcceptor(BlockEntity tile, Direction side) {
        return getAcceptorCache().isAcceptorAndListen(tile, side, Capabilities.HEAT_HANDLER);
    }

    @NotNull
    @Override
    public CompoundTag write(@NotNull CompoundTag tag) {
        super.write(tag);
        tag.put(NBTConstants.HEAT_CAPACITORS, DataHandlerUtils.writeContainers(getHeatCapacitors(null)));
        return tag;
    }

    @Override
    public void read(@NotNull CompoundTag tag) {
        super.read(tag);
        DataHandlerUtils.readContainers(getHeatCapacitors(null), tag.getList(NBTConstants.HEAT_CAPACITORS, Tag.TAG_COMPOUND));
    }

    @NotNull
    @Override
    public CompoundTag getReducedUpdateTag(CompoundTag updateTag) {
        updateTag = super.getReducedUpdateTag(updateTag);
        updateTag.putDouble(NBTConstants.TEMPERATURE, buffer.getHeat());
        return updateTag;
    }

    @NotNull
    @Override
    public List<IHeatCapacitor> getHeatCapacitors(Direction side) {
        return capacitors;
    }

    @Override
    public void handleUpdateTag(@NotNull CompoundTag tag) {
        super.handleUpdateTag(tag);
        NBTUtils.setDoubleIfPresent(tag, NBTConstants.TEMPERATURE, buffer::setHeat);
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
            // network as valid acceptors
            return getAcceptorCache().getConnectedAcceptor(side).resolve().orElse(null);
        }
        return null;
    }

    @Override
    public double incrementAdjacentTransfer(double currentAdjacentTransfer, double tempToTransfer, @NotNull Direction side) {
        if (tempToTransfer > 0) {
            //Look up the adjacent tile from the acceptor cache and then do the type checking
            BlockEntity sink = getAcceptorCache().getConnectedAcceptorTile(side);
            if (sink instanceof TileEntityTransmitter transmitter && TransmissionType.HEAT.checkTransmissionType(transmitter)) {
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
