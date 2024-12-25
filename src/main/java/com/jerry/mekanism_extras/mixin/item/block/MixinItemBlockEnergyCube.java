package com.jerry.mekanism_extras.mixin.item.block;

import mekanism.common.block.BlockEnergyCube;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.item.interfaces.IItemSustainedInventory;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;

public abstract class MixinItemBlockEnergyCube extends ItemBlockTooltip<BlockEnergyCube> implements IItemSustainedInventory, CreativeTabDeferredRegister.ICustomCreativeTabContents {
    public MixinItemBlockEnergyCube(BlockEnergyCube block, Properties properties) {
        super(block, properties);
    }

//    @Overwrite
//    public ItemBlockEnergyCube(BlockEnergyCube block) {
//        super(block);
//    }
}
