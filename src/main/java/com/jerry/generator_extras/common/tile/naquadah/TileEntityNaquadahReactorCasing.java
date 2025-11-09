package com.jerry.generator_extras.common.tile.naquadah;

import com.jerry.mekanism_extras.MekanismExtras;

import com.jerry.generator_extras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;
import com.jerry.generator_extras.common.genregistry.ExtraGenContainerTypes;

import mekanism.api.providers.IBlockProvider;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.sync.dynamic.SyncMapper;
import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.common.tile.prefab.TileEntityMultiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityNaquadahReactorCasing extends TileEntityMultiblock<NaquadahReactorMultiblockData> {

    public TileEntityNaquadahReactorCasing(BlockPos pos, BlockState state) {
        this(ExtraGenBlocks.NAQUADAH_REACTOR_CASING, pos, state);
    }

    public TileEntityNaquadahReactorCasing(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    public NaquadahReactorMultiblockData createMultiblock() {
        return new NaquadahReactorMultiblockData(this);
    }

    @Override
    public MultiblockManager<NaquadahReactorMultiblockData> getManager() {
        return MekanismExtras.naquadahReactorManager;
    }

    @Override
    public boolean canBeMaster() {
        return false;
    }

    public void setInjectionRateFromPacket(int rate) {
        NaquadahReactorMultiblockData multiblock = getMultiblock();
        if (multiblock.isFormed()) {
            multiblock.setInjectionRate(Mth.clamp(rate - (rate % 2), 0, NaquadahReactorMultiblockData.MAX_INJECTION));
            markForSave();
        }
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        if (container.getType() == ExtraGenContainerTypes.NAQUADAH_REACTOR_FUEL.get()) {
            addTabContainerTracker(container, NaquadahReactorMultiblockData.FUEL_TAB);
        } else if (container.getType() == ExtraGenContainerTypes.NAQUADAH_REACTOR_HEAT.get()) {
            addTabContainerTracker(container, NaquadahReactorMultiblockData.HEAT_TAB);
        } else if (container.getType() == ExtraGenContainerTypes.NAQUADAH_REACTOR_STATS.get()) {
            addTabContainerTracker(container, NaquadahReactorMultiblockData.STATS_TAB);
        }
    }

    private void addTabContainerTracker(MekanismContainer container, String tab) {
        SyncMapper.INSTANCE.setup(container, NaquadahReactorMultiblockData.class, this::getMultiblock, tab);
    }
}
