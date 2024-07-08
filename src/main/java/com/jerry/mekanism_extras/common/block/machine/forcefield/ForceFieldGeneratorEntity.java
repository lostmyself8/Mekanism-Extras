package com.jerry.mekanism_extras.common.block.machine.forcefield;

import com.jerry.mekanism_extras.registry.ExtraBlock;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class ForceFieldGeneratorEntity extends TileEntityMekanism {
    private String freq = "";
    private boolean available = false;

    public ForceFieldGeneratorEntity(BlockPos pos, BlockState state) {
        super(ExtraBlock.FORCEFIELD_GENERATOR, pos, state);
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
        this.setChanged();
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
        this.setChanged();
    }
}
