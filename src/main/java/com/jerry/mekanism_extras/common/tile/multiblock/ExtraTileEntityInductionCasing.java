package com.jerry.mekanism_extras.common.tile.multiblock;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData;
import com.jerry.mekanism_extras.registry.ExtraBlock;
import com.jerry.mekanism_extras.registry.ExtraContainerTypes;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.sync.dynamic.SyncMapper;
import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.common.tile.prefab.TileEntityMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;

public class ExtraTileEntityInductionCasing extends TileEntityMultiblock<ExtraMatrixMultiblockData> {

    public ExtraTileEntityInductionCasing(BlockPos pos, BlockState state) {
        this(ExtraBlock.REINFORCED_INDUCTION_CASING, pos, state);
        //Disable item handler caps if we are the induction casing, don't disable it for the subclassed port though
        addDisabledCapabilities(ForgeCapabilities.ITEM_HANDLER);
    }

    public ExtraTileEntityInductionCasing(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @NotNull
    @Override
    public ExtraMatrixMultiblockData createMultiblock() {
        return new ExtraMatrixMultiblockData(this);
    }

    @Override
    public MultiblockManager<ExtraMatrixMultiblockData> getManager() {
        return MekanismExtras.extraMatrixManager;
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        if (container.getType() == ExtraContainerTypes.MATRIX_STATS.get()) {
            SyncMapper.INSTANCE.setup(container, ExtraMatrixMultiblockData.class, this::getMultiblock, ExtraMatrixMultiblockData.STATS_TAB);
        }
    }
}
