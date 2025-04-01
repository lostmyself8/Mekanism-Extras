package com.jerry.mekanism_extras.common.block.prefab;

import com.jerry.mekanism_extras.common.content.blocktype.AdvancedFactory;
import com.jerry.mekanism_extras.common.content.blocktype.AdvancedMachine;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityAdvancedFactory;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.block.states.IStateFluidLoggable;
import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.tile.base.TileEntityMekanism;

import java.util.function.UnaryOperator;

public class BlockAdvancedFactoryMachine<TILE extends TileEntityMekanism, MACHINE extends AdvancedMachine.AdvancedFactoryMachine<TILE>> extends BlockTile<TILE, MACHINE> {

    public BlockAdvancedFactoryMachine(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
        super(machineType, propertiesModifier);
    }

    public static class BlockAdvancedFactoryMachineModel<TILE extends TileEntityMekanism, MACHINE extends AdvancedMachine.AdvancedFactoryMachine<TILE>> extends BlockAdvancedFactoryMachine<TILE, MACHINE> implements IStateFluidLoggable {

        public BlockAdvancedFactoryMachineModel(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
            super(machineType, propertiesModifier);
        }
    }

    public static class BlockAdvancedFactory<TILE extends TileEntityAdvancedFactory<?>> extends BlockAdvancedFactoryMachineModel<TILE, AdvancedFactory<TILE>> {

        public BlockAdvancedFactory(AdvancedFactory<TILE> factoryType) {
            super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
        }
    }
}
