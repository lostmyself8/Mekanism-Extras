package com.jerry.mekanism_extras.common.tile.multiblock;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.tier.ICTier;
import mekanism.api.IContentsListener;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.tile.prefab.TileEntityInternalMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ExtraTileEntityInductionCell extends TileEntityInternalMultiblock {
    private MachineEnergyContainer<ExtraTileEntityInductionCell> energyContainer;
    public ICTier tier;

    public ExtraTileEntityInductionCell(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
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
        tier = ExtraAttribute.getAdvanceTier(getBlockType(), ICTier.class);
    }

    public MachineEnergyContainer<ExtraTileEntityInductionCell> getEnergyContainer() {
        return energyContainer;
    }
}
