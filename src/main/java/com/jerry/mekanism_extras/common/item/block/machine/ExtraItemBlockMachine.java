package com.jerry.mekanism_extras.common.item.block.machine;

import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockTooltip;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.item.interfaces.IItemSustainedInventory;

public class ExtraItemBlockMachine extends ExtraItemBlockTooltip<BlockTile<?, ?>> implements IItemSustainedInventory {

    public ExtraItemBlockMachine(BlockTile<?, ?> block, Properties properties) {
        super(block, true, properties);
    }
}
