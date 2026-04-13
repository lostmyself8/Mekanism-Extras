package com.jerry.mekextras.common.block.prefab;

import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekextras.common.integration.mekaf.content.blocktype.ExtraAdvancedFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;

import mekanism.common.block.prefab.BlockTile;
import mekanism.common.block.states.IStateFluidLoggable;
import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.tile.base.TileEntityMekanism;

import java.util.function.UnaryOperator;

public class BlockExtraAdvancedFactoryMachine<TILE extends TileEntityMekanism, MACHINE extends ExtraFactoryMachine<TILE>> extends BlockTile<TILE, MACHINE> {

    public BlockExtraAdvancedFactoryMachine(MACHINE machine, UnaryOperator<Properties> propertiesModifier) {
        super(machine, propertiesModifier);
    }

    public static class MoreMachineBlockAdvancedFactoryMachineModel<TILE extends TileEntityMekanism, MACHINE extends ExtraFactoryMachine<TILE>> extends BlockExtraAdvancedFactoryMachine<TILE, MACHINE> implements IStateFluidLoggable {

        public MoreMachineBlockAdvancedFactoryMachineModel(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
            super(machineType, propertiesModifier);
        }
    }

    public static class BlockExtraAdvancedFactory<TILE extends TileEntityExtraAdvancedFactoryBase<?>> extends MoreMachineBlockAdvancedFactoryMachineModel<TILE, ExtraAdvancedFactory<TILE>> {

        public BlockExtraAdvancedFactory(ExtraAdvancedFactory<TILE> factoryType) {
            super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
        }
    }
}
