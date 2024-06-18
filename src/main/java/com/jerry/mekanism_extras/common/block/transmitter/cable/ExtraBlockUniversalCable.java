package com.jerry.mekanism_extras.common.block.transmitter.cable;

import com.jerry.mekanism_extras.registry.ExtraTileEntityTypes;
import mekanism.api.tier.BaseTier;
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
    protected BaseTier getBaseTier() {
        return this.tier.getBaseTier();
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
