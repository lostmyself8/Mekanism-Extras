package com.jerry.mekanism_extras.common.block.transmitter.logisticaltransporter;

import mekanism.api.providers.IBlockProvider;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ExtraTileEntityTransmitter extends TileEntityTransmitter {

    public ExtraTileEntityTransmitter(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    public static void extraTickServer(Level level, BlockPos blockPos, BlockState blockState, ExtraTileEntityLogisticalTransporter extraTileEntityLogisticalTransporter) {
        extraTileEntityLogisticalTransporter.onUpdateServer();
    }
}
