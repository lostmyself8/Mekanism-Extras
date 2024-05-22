package com.jerry.mekanism_extras.common.block.transmitter.pipe;

import mekanism.api.Action;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.content.network.transmitter.MechanicalPipe;
import mekanism.common.lib.transmitter.ConnectionType;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import net.minecraft.core.Direction;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Set;

public class ExtraMechanicalPipe extends MechanicalPipe {
    public ExtraMechanicalPipe(IBlockProvider blockProvider, TileEntityTransmitter tile) {
        super(blockProvider, tile);
    }

    @Override
    public void pullFromAcceptors() {
        Set<Direction> connections = this.getConnections(ConnectionType.PULL);
        if (!connections.isEmpty()) {

            for (IFluidHandler connectedAcceptor : this.getAcceptorCache().getConnectedAcceptors(connections)) {
                FluidStack bufferWithFallback = this.getBufferWithFallback();
                FluidStack received;
                if (bufferWithFallback.isEmpty()) {
                    received = connectedAcceptor.drain(this.getAvailablePull(), FluidAction.SIMULATE);
                } else {
                    received = connectedAcceptor.drain(new FluidStack(bufferWithFallback, this.getAvailablePull()), FluidAction.SIMULATE);
                }

                if (!received.isEmpty() && this.takeFluid(received, Action.SIMULATE).isEmpty()) {
                    this.takeFluid(connectedAcceptor.drain(received, FluidAction.EXECUTE), Action.EXECUTE);
                }
            }
        }

    }

    private int getAvailablePull() {
        return this.hasTransmitterNetwork() ? Math.min(PTier.getPipePullAmount(this.tier), this.getTransmitterNetwork().fluidTank.getNeeded()) : Math.min(PTier.getPipePullAmount(this.tier), this.buffer.getNeeded());
    }

    @Override
    public long getCapacity() {
        return PTier.getPipeCapacity(this.tier);
    }
}
