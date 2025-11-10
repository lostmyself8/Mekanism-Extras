package com.jerry.mekanism_extras.common.tile.multiblock;

import com.jerry.mekanism_extras.common.tier.ICTier;

import mekanism.api.IContentsListener;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.integration.energy.EnergyCompatUtils;
import mekanism.common.tile.prefab.TileEntityInternalMultiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public class ExtraTileEntityInductionCell extends TileEntityInternalMultiblock {

    @Getter
    private MachineEnergyContainer<ExtraTileEntityInductionCell> energyContainer;
    public ICTier tier;

    public ExtraTileEntityInductionCell(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        // Never externally expose the energy capability
        addDisabledCapabilities(EnergyCompatUtils.getEnabledEnergyCapabilities());
    }

    @NotNull
    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSide(this::getDirection);
        builder.addContainer(energyContainer = MachineEnergyContainer.internal(this, listener));
        return builder.build();
    }

    @Override
    protected void presetVariables() {
        super.presetVariables();
        tier = Attribute.getTier(getBlockType(), ICTier.class);
    }
}
