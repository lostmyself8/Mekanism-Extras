package com.jerry.mekextra.common.content.network.transmitter;

import com.jerry.mekextra.common.tier.transmitter.TTier;
import com.jerry.mekextra.common.tile.transmitter.ExtraTileEntityTransmitter;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalHandler;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.content.network.transmitter.PressurizedTube;
import mekanism.common.lib.transmitter.ConnectionType;
import mekanism.common.lib.transmitter.acceptor.AcceptorCache;
import mekanism.common.upgrade.transmitter.PressurizedTubeUpgradeData;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import mekanism.common.util.EnumUtils;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtraPressurizedTube extends PressurizedTube {
    public ExtraPressurizedTube(IBlockProvider blockProvider, ExtraTileEntityTransmitter tile) {
        super(blockProvider, tile);
    }

    @Override
    public void pullFromAcceptors() {
        if (!hasPullSide || getAvailablePull() <= 0) {
            return;
        }
        AcceptorCache<IChemicalHandler> acceptorCache = getAcceptorCache();
        for (Direction side : EnumUtils.DIRECTIONS) {
            if (!isConnectionType(side, ConnectionType.PULL)) {
                continue;
            }
            IChemicalHandler connectedAcceptor = acceptorCache.getConnectedAcceptor(side);
            if (connectedAcceptor != null) {
                //Note: We recheck the buffer each time in case we ended up accepting chemical somewhere
                // and our buffer changed and is no longer empty
                ChemicalStack bufferWithFallback = getBufferWithFallback();
                pullFromAcceptor(connectedAcceptor, bufferWithFallback, bufferWithFallback.isEmpty());
            }
        }
    }

    private boolean pullFromAcceptor(IChemicalHandler connectedAcceptor, ChemicalStack bufferWithFallback, boolean bufferIsEmpty) {
        if (connectedAcceptor == null) {
            return false;
        }
        long availablePull = getAvailablePull();
        ChemicalStack received;
        if (bufferIsEmpty) {
            //If we don't have a chemical stored try pulling as much as we are able to
            received = connectedAcceptor.extractChemical(availablePull, Action.SIMULATE);
        } else {
            //Otherwise, try draining the same type of chemical we have stored requesting up to as much as we are able to pull
            // We do this to better support multiple tanks in case the chemical we have stored we could pull out of a block's
            // second tank but just asking to drain a specific amount
            received = connectedAcceptor.extractChemical(bufferWithFallback.copyWithAmount(availablePull), Action.SIMULATE);
        }
        if (!received.isEmpty() && takeChemical(received, Action.SIMULATE).isEmpty()) {
            //If we received some chemical and are able to insert it all, then actually extract it and insert it into our thing.
            // Note: We extract first after simulating ourselves because if the target gave a faulty simulation value, we want to handle it properly
            // and not accidentally dupe anything, and we know our simulation we just performed on taking it is valid
            takeChemical(connectedAcceptor.extractChemical(received, Action.EXECUTE), Action.EXECUTE);
            return true;
        }
        return false;
    }

    private long getAvailablePull() {
        if (hasTransmitterNetwork()) {
            return Math.min(tier.getTubePullAmount(), getTransmitterNetwork().chemicalTank.getNeeded());
        }
        return Math.min(tier.getTubePullAmount(), chemicalTank.getNeeded());
    }

    @Nullable
    @Override
    public PressurizedTubeUpgradeData getUpgradeData() {
        return super.getUpgradeData();
    }

    @Override
    public boolean dataTypeMatches(@NotNull TransmitterUpgradeData data) {
        return data instanceof PressurizedTubeUpgradeData;
    }

    @Override
    public void parseUpgradeData(@NotNull PressurizedTubeUpgradeData data) {
        redstoneReactive = data.redstoneReactive;
        setConnectionTypesRaw(data.connectionTypes);
        takeChemical(data.contents, Action.EXECUTE);
    }

    @Override
    public long getCapacity() {
        return TTier.getTubeCapacity(tier);
    }

    private ChemicalStack takeChemical(ChemicalStack stack, Action action) {
        IChemicalTank tank;
        if (hasTransmitterNetwork()) {
            tank = getTransmitterNetwork().chemicalTank;
        } else {
            tank = chemicalTank;
        }
        return tank.insert(stack, action, AutomationType.INTERNAL);
    }
}
