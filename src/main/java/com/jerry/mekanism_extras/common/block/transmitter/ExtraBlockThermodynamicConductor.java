package com.jerry.mekanism_extras.common.block.transmitter;

import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityThermodynamicConductor;
import com.jerry.mekanism_extras.common.registry.ExtraTileEntityTypes;
import mekanism.api.tier.BaseTier;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.ConductorTier;

public class ExtraBlockThermodynamicConductor extends BlockSmallTransmitter implements ITypeBlock, IHasTileEntity<ExtraTileEntityThermodynamicConductor> {

    private final ConductorTier tier;

    public ExtraBlockThermodynamicConductor(ConductorTier tier) {
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
    public TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> getTileType() {
        return switch (tier) {
            case BASIC -> ExtraTileEntityTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR;
            case ADVANCED -> ExtraTileEntityTypes.SUPREME_THERMODYNAMIC_CONDUCTOR;
            case ELITE -> ExtraTileEntityTypes.COSMIC_THERMODYNAMIC_CONDUCTOR;
            case ULTIMATE -> ExtraTileEntityTypes.INFINITE_THERMODYNAMIC_CONDUCTOR;
        };
    }
}
