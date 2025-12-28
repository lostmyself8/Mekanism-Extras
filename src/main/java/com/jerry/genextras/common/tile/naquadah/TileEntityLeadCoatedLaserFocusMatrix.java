package com.jerry.genextras.common.tile.naquadah;

import com.jerry.genextras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.genextras.common.registries.GenExtraBlocks;

import mekanism.api.lasers.ILaserReceptor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityLeadCoatedLaserFocusMatrix extends TileEntityNaquadahReactorCasing implements ILaserReceptor {

    public TileEntityLeadCoatedLaserFocusMatrix(BlockPos pos, BlockState state) {
        super(GenExtraBlocks.LEAD_COATED_LASER_FOCUS_MATRIX, pos, state);
    }

    @Override
    public void receiveLaserEnergy(long energy) {
        NaquadahReactorMultiblockData multiblock = getMultiblock();
        if (multiblock.isFormed()) {
            multiblock.addTemperatureFromEnergyInput(energy);
        }
    }

    @Override
    public InteractionResult onRightClick(Player player) {
        if (!isRemote() && player.isCreative()) {
            NaquadahReactorMultiblockData multiblock = getMultiblock();
            if (multiblock.isFormed()) {
                multiblock.setPlasmaTemp(1_000_000_000);
                return InteractionResult.sidedSuccess(isRemote());
            }
        }
        return super.onRightClick(player);
    }

    @Override
    public boolean canLasersDig() {
        return false;
    }
}
