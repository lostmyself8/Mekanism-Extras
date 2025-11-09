package com.jerry.mekanism_extras.common.block.transmitter;

import com.jerry.mekanism_extras.common.registry.ExtraTileEntityTypes;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityPressurizedTube;

import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.TubeTier;

public class ExtraBlockPressurizedTube extends BlockSmallTransmitter implements ITypeBlock, IHasTileEntity<ExtraTileEntityPressurizedTube> {

    private final TubeTier tier;

    public ExtraBlockPressurizedTube(TubeTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    @Override
    public BlockType getType() {
        return AttributeTier.getPassthroughType(tier);
    }

    @Override
    public TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> getTileType() {
        return switch (tier) {
            case BASIC -> ExtraTileEntityTypes.ABSOLUTE_PRESSURIZED_TUBE;
            case ADVANCED -> ExtraTileEntityTypes.SUPREME_PRESSURIZED_TUBE;
            case ELITE -> ExtraTileEntityTypes.COSMIC_PRESSURIZED_TUBE;
            case ULTIMATE -> ExtraTileEntityTypes.INFINITE_PRESSURIZED_TUBE;
        };
    }
}
