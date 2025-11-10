package com.jerry.mekanism_extras.common.tile.transmitter;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.common.content.network.transmitter.ExtraThermodynamicConductor;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;

import mekanism.api.heat.IHeatCapacitor;
import mekanism.api.heat.IMekanismHeatHandler;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.block.states.TransmitterType;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.resolver.manager.HeatHandlerManager;
import mekanism.common.lib.transmitter.ConnectionType;
import mekanism.common.util.EnumUtils;
import mekanism.common.util.WorldUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ExtraTileEntityThermodynamicConductor extends ExtraTileEntityTransmitter {

    private final HeatHandlerManager heatHandlerManager;

    public ExtraTileEntityThermodynamicConductor(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        addCapabilityResolver(heatHandlerManager = new HeatHandlerManager(direction -> {
            ExtraThermodynamicConductor conductor = getTransmitter();
            if (direction != null && (conductor.getConnectionTypeRaw(direction) == ConnectionType.NONE) || conductor.isRedstoneActivated()) {
                // If we actually have a side, and our connection type on that side is none, or we are currently
                // activated by redstone,
                // then return that we have no capacitors
                return Collections.emptyList();
            }
            return conductor.getHeatCapacitors(direction);
        }, new IMekanismHeatHandler() {

            @NotNull
            @Override
            public List<IHeatCapacitor> getHeatCapacitors(@Nullable Direction side) {
                return heatHandlerManager.getContainers(side);
            }

            @Override
            public void onContentsChanged() {}
        }));
    }

    @Override
    protected ExtraThermodynamicConductor createTransmitter(IBlockProvider blockProvider) {
        return new ExtraThermodynamicConductor(blockProvider, this);
    }

    @Override
    public ExtraThermodynamicConductor getTransmitter() {
        return (ExtraThermodynamicConductor) super.getTransmitter();
    }

    @Override
    public TransmitterType getTransmitterType() {
        return TransmitterType.THERMODYNAMIC_CONDUCTOR;
    }

    @NotNull
    @Override
    protected BlockState upgradeResult(@NotNull BlockState current, @NotNull AdvancedTier tier) {
        return BlockStateHelper.copyStateData(current, switch (tier) {
            case ABSOLUTE -> ExtraBlock.ABSOLUTE_THERMODYNAMIC_CONDUCTOR;
            case SUPREME -> ExtraBlock.SUPREME_THERMODYNAMIC_CONDUCTOR;
            case COSMIC -> ExtraBlock.COSMIC_THERMODYNAMIC_CONDUCTOR;
            case INFINITE -> ExtraBlock.INFINITE_THERMODYNAMIC_CONDUCTOR;
        });
    }

    @Override
    public void sideChanged(@NotNull Direction side, @NotNull ConnectionType old, @NotNull ConnectionType type) {
        super.sideChanged(side, old, type);
        if (type == ConnectionType.NONE) {
            invalidateCapability(Capabilities.HEAT_HANDLER, side);
            // Notify the neighbor on that side our state changed and we no longer have a capability
            WorldUtils.notifyNeighborOfChange(level, side, worldPosition);
        } else if (old == ConnectionType.NONE) {
            // Notify the neighbor on that side our state changed, and we now do have a capability
            WorldUtils.notifyNeighborOfChange(level, side, worldPosition);
        }
    }

    @Override
    public void redstoneChanged(boolean powered) {
        super.redstoneChanged(powered);
        if (powered) {
            // The transmitter now is powered by redstone and previously was not
            // Note: While at first glance the below invalidation may seem over aggressive, it is not actually that
            // aggressive as
            // if a cap has not been initialized yet on a side then invalidating it will just NO-OP
            invalidateCapability(Capabilities.HEAT_HANDLER, EnumUtils.DIRECTIONS);
        }
        // Note: We do not have to invalidate any caps if we are going from powered to unpowered as all the caps would
        // already be "empty"
    }
}
