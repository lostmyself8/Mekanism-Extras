package com.jerry.mekanism_extras.common.integration.mekaf.block.prefab;

import com.jerry.mekanism_extras.common.block.prefab.BlockExtraFactoryMachine.BlockExtraFactoryMachineModel;
import com.jerry.mekanism_extras.common.integration.mekaf.content.blocktype.ExtraAdvancedFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;

import mekanism.common.resource.BlockResourceInfo;

public class BlockExtraAdvancedFactory<TILE extends TileEntityExtraAdvancedFactoryBase<?>> extends BlockExtraFactoryMachineModel<TILE, ExtraAdvancedFactory<TILE>> {

    public BlockExtraAdvancedFactory(ExtraAdvancedFactory<TILE> factoryType) {
        super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
    }
}
