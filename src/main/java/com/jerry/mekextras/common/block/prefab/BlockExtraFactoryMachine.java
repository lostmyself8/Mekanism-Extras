package com.jerry.mekextras.common.block.prefab;

import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.block.states.IStateFluidLoggable;
import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.UnaryOperator;

public class BlockExtraFactoryMachine<TILE extends TileEntityMekanism, MACHINE extends ExtraFactoryMachine<TILE>> extends BlockTile<TILE, MACHINE> {

    public BlockExtraFactoryMachine(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
        super(machineType, propertiesModifier);
    }

    public static class BlockExtraFactoryMachineModel<TILE extends TileEntityMekanism, MACHINE extends ExtraFactoryMachine<TILE>> extends BlockExtraFactoryMachine<TILE, MACHINE> implements IStateFluidLoggable {

        public BlockExtraFactoryMachineModel(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
            super(machineType, propertiesModifier);
        }
    }

    public static class BlockExtraFactory<TILE extends TileEntityExtraFactory<?>> extends BlockExtraFactoryMachineModel<TILE, ExtraFactory<TILE>> {

        public BlockExtraFactory(ExtraFactory<TILE> factoryType) {
            super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
        }
    }
}
