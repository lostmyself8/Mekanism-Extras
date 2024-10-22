package com.jerry.mekanism_extras.common.tile.multiblock;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.content.matrix.ReinforcedMatrixMultiblockData;
import com.jerry.mekanism_extras.common.registry.ExtraBlocks;
import com.jerry.mekanism_extras.common.registry.ExtraContainerTypes;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.sync.dynamic.SyncMapper;
import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.common.tile.prefab.TileEntityMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityReinforcedInductionCasing extends TileEntityMultiblock<ReinforcedMatrixMultiblockData> {

    public TileEntityReinforcedInductionCasing(BlockPos pos, BlockState state) {
        this(ExtraBlocks.REINFORCED_INDUCTION_CASING, pos, state);
    }

    public TileEntityReinforcedInductionCasing(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    public ReinforcedMatrixMultiblockData createMultiblock() {
        return new ReinforcedMatrixMultiblockData(this);
    }

    @Override
    public MultiblockManager<ReinforcedMatrixMultiblockData> getManager() {
        return MekanismExtras.matrixManager;
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        if (container.getType() == ExtraContainerTypes.REINFORCED_MATRIX_STATS.get()) {
            SyncMapper.INSTANCE.setup(container, ReinforcedMatrixMultiblockData.class, this::getMultiblock, ReinforcedMatrixMultiblockData.STATS_TAB);
        }
    }
}
