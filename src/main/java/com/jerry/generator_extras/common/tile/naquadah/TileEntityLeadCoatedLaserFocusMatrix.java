package com.jerry.generator_extras.common.tile.naquadah;

import com.jerry.generator_extras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.generator_extras.common.genregistry.ExtraGenBlocks;

import mekanism.api.lasers.ILaserReceptor;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.resolver.BasicCapabilityResolver;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

public class TileEntityLeadCoatedLaserFocusMatrix extends TileEntityNaquadahReactorCasing implements ILaserReceptor {

    public TileEntityLeadCoatedLaserFocusMatrix(BlockPos pos, BlockState state) {
        super(ExtraGenBlocks.LEAD_COATED_LASER_FOCUS_MATRIX, pos, state);
        addCapabilityResolver(BasicCapabilityResolver.constant(Capabilities.LASER_RECEPTOR, this));
    }

    @Override
    public void receiveLaserEnergy(@NotNull FloatingLong energy) {
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
