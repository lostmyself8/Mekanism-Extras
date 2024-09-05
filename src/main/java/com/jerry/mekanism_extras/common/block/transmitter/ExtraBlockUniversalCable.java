package com.jerry.mekanism_extras.common.block.transmitter;

import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityUniversalCable;
import com.jerry.mekanism_extras.common.registry.ExtraTileEntityTypes;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.CableTier;

public class ExtraBlockUniversalCable extends ExtraBlockSmallTransmitter implements ITypeBlock, IHasTileEntity<ExtraTileEntityUniversalCable> {
    private final CableTier tier;

    public ExtraBlockUniversalCable(CableTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    @Override
    public BlockType getType() {
        return AttributeTier.getPassthroughType(tier);
    }

    @Override
    public TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> getTileType() {
        return switch (tier) {
            case BASIC -> ExtraTileEntityTypes.ABSOLUTE_UNIVERSAL_CABLE;
            case ADVANCED -> ExtraTileEntityTypes.SUPREME_UNIVERSAL_CABLE;
            case ELITE -> ExtraTileEntityTypes.COSMIC_UNIVERSAL_CABLE;
            case ULTIMATE -> ExtraTileEntityTypes.INFINITE_UNIVERSAL_CABLE;
        };
    }
}
