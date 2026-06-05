package com.jerry.mekanism_extras.common.integration.mekmm.block.prefab;

import com.jerry.mekanism_extras.common.block.prefab.BlockExtraFactoryMachine.BlockExtraFactoryMachineModel;
import com.jerry.mekanism_extras.common.integration.mekmm.content.blocktype.ExtraMoreMachineFactory;
import com.jerry.mekanism_extras.common.integration.mekmm.tile.TileEntityExtraMoreMachineFactory;

import mekanism.common.resource.BlockResourceInfo;

public class BlockExtraMoreMachineFactory<TILE extends TileEntityExtraMoreMachineFactory<?>> extends BlockExtraFactoryMachineModel<TILE, ExtraMoreMachineFactory<TILE>> {

    public BlockExtraMoreMachineFactory(ExtraMoreMachineFactory<TILE> factoryType) {
        super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
    }
}
