package com.jerry.mekanism_extras.common.block.transmitter;

import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityMechanicalPipe;
import com.jerry.mekanism_extras.common.registry.ExtraTileEntityTypes;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.PipeTier;

public class ExtraBlockMechanicalPipe extends BlockLargeTransmitter implements ITypeBlock, IHasTileEntity<ExtraTileEntityMechanicalPipe> {

    private final PipeTier tier;

    public ExtraBlockMechanicalPipe(PipeTier tier) {
        this.tier = tier;
    }

    public BlockType getType() {
        return AttributeTier.getPassthroughType(this.tier);
    }

    public TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> getTileType() {

        return switch (this.tier) {
            case BASIC -> ExtraTileEntityTypes.ABSOLUTE_MECHANICAL_PIPE;
            case ADVANCED -> ExtraTileEntityTypes.SUPREME_MECHANICAL_PIPE;
            case ELITE -> ExtraTileEntityTypes.COSMIC_MECHANICAL_PIPE;
            case ULTIMATE -> ExtraTileEntityTypes.INFINITE_MECHANICAL_PIPE;
        };
    }
}
