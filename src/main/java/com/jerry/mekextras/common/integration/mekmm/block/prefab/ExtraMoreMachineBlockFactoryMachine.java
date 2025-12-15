package com.jerry.mekextras.common.integration.mekmm.block.prefab;

import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekextras.common.integration.mekmm.content.blocktype.ExtraMoreMachineFactory;
import com.jerry.mekextras.common.integration.mekmm.tile.factory.TileEntityExtraMoreMachineFactory;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.block.states.IStateFluidLoggable;
import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.tile.base.TileEntityMekanism;

import java.util.function.UnaryOperator;

public class ExtraMoreMachineBlockFactoryMachine<TILE extends TileEntityMekanism, MACHINE extends ExtraFactoryMachine<TILE>> extends BlockTile<TILE, MACHINE> {

    public ExtraMoreMachineBlockFactoryMachine(MACHINE machine, UnaryOperator<Properties> propertiesModifier) {
        super(machine, propertiesModifier);
    }

    public static class ExtraMoreMachineBlockFactoryMachineModel<TILE extends TileEntityMekanism, MACHINE extends ExtraFactoryMachine<TILE>> extends ExtraMoreMachineBlockFactoryMachine<TILE, MACHINE> implements IStateFluidLoggable {

        public ExtraMoreMachineBlockFactoryMachineModel(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
            super(machineType, propertiesModifier);
        }
    }

    public static class BlockExtraMoreMachineFactory<TILE extends TileEntityExtraMoreMachineFactory<?>> extends ExtraMoreMachineBlockFactoryMachineModel<TILE, ExtraMoreMachineFactory<TILE>> {

        public BlockExtraMoreMachineFactory(ExtraMoreMachineFactory<TILE> factoryType) {
            super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
        }
    }
}
