package com.jerry.mekextras.common.content.network.transmitter;

import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityTransmitter;
import com.jerry.mekextras.common.tier.TTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.chemical.*;
import mekanism.api.chemical.merged.BoxedChemicalStack;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.capabilities.chemical.BoxedChemicalHandler;
import mekanism.common.capabilities.chemical.dynamic.IGasTracker;
import mekanism.common.capabilities.chemical.dynamic.IInfusionTracker;
import mekanism.common.capabilities.chemical.dynamic.IPigmentTracker;
import mekanism.common.capabilities.chemical.dynamic.ISlurryTracker;
import mekanism.common.content.network.transmitter.BoxedPressurizedTube;
import mekanism.common.lib.transmitter.ConnectionType;
import mekanism.common.upgrade.transmitter.PressurizedTubeUpgradeData;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import mekanism.common.util.ChemicalUtil;
import mekanism.common.util.EnumUtils;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ExtraBoxedPressurizedTube extends BoxedPressurizedTube implements IGasTracker, IInfusionTracker, IPigmentTracker, ISlurryTracker, IExtraUpgradeableTransmitter<PressurizedTubeUpgradeData> {
    public ExtraBoxedPressurizedTube(IBlockProvider blockProvider, ExtraTileEntityTransmitter tile) {
        super(blockProvider, tile);
    }

    @Override
    public void pullFromAcceptors() {
        Set<Direction> connections = getConnections(ConnectionType.PULL);
        if (!connections.isEmpty()) {
            for (BoxedChemicalHandler connectedAcceptor : getAcceptorCache().getConnectedAcceptors(connections)) {
                BoxedChemicalStack bufferWithFallback = getBufferWithFallback();
                if (bufferWithFallback.isEmpty()) {
                    for (ChemicalType chemicalType : EnumUtils.CHEMICAL_TYPES) {
                        if (pullFromAcceptor(connectedAcceptor, chemicalType, bufferWithFallback, true)) {
                            break;
                        }
                    }
                } else {
                    pullFromAcceptor(connectedAcceptor, bufferWithFallback.getChemicalType(), bufferWithFallback, false);
                }
            }
        }
    }

    private boolean pullFromAcceptor(BoxedChemicalHandler acceptor, ChemicalType chemicalType, BoxedChemicalStack bufferWithFallback, boolean bufferIsEmpty) {
        IChemicalHandler<?, ?> handler = acceptor.getHandlerFor(chemicalType);
        if (handler != null) {
            return pullFromAcceptor(handler, bufferWithFallback, chemicalType, bufferIsEmpty);
        }
        return false;
    }

    private <CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>, HANDLER extends IChemicalHandler<CHEMICAL, STACK>>
    boolean pullFromAcceptor(HANDLER connectedAcceptor, BoxedChemicalStack bufferWithFallback, ChemicalType chemicalType, boolean bufferIsEmpty) {
        long availablePull = getAvailablePull(chemicalType);
        STACK received;
        if (bufferIsEmpty) {
            received = connectedAcceptor.extractChemical(availablePull, Action.SIMULATE);
        } else {
            received = connectedAcceptor.extractChemical(ChemicalUtil.copyWithAmount((STACK) bufferWithFallback.getChemicalStack(), availablePull), Action.SIMULATE);
        }
        if (!received.isEmpty() && takeChemical(chemicalType, received, Action.SIMULATE).isEmpty()) {
            takeChemical(chemicalType, connectedAcceptor.extractChemical(received, Action.EXECUTE), Action.EXECUTE);
            return true;
        }
        return false;
    }

    private long getAvailablePull(ChemicalType chemicalType) {
        if (hasTransmitterNetwork()) {
            return Math.min(TTier.getTubePullAmount(tier), getTransmitterNetwork().chemicalTank.getTankForType(chemicalType).getNeeded());
        }
        return Math.min(TTier.getTubePullAmount(tier), chemicalTank.getTankForType(chemicalType).getNeeded());
    }

    @Override
    public long getCapacity() {
        return TTier.getTubeCapacity(tier);
    }

    @NotNull
    @SuppressWarnings("unchecked")
    private <CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>> STACK takeChemical(ChemicalType type, STACK stack, Action action) {
        IChemicalTank<CHEMICAL, STACK> tank;
        if (hasTransmitterNetwork()) {
            tank = (IChemicalTank<CHEMICAL, STACK>) getTransmitterNetwork().chemicalTank.getTankForType(type);
        } else {
            tank = (IChemicalTank<CHEMICAL, STACK>) chemicalTank.getTankForType(type);
        }
        return tank.insert(stack, action, AutomationType.INTERNAL);
    }

    @Nullable
    @Override
    public PressurizedTubeUpgradeData getUpgradeData() {
        return super.getUpgradeData();
    }

    @Override
    public boolean dataTypeMatches(@NotNull TransmitterUpgradeData data) {
        return super.dataTypeMatches(data);
    }

    @Override
    public void parseUpgradeData(@NotNull PressurizedTubeUpgradeData data) {
        super.parseUpgradeData(data);
    }
}
