package com.jerry.mekextras.common.resource.ore;

import mekanism.common.block.BlockOre;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.registration.impl.BlockRegistryObject;

//虽然也可以使用OreBlockType，但我还是想区分一下
public record EndOreBlockType(BlockRegistryObject<BlockOre, ItemBlockTooltip<BlockOre>> stone,
                              BlockRegistryObject<BlockOre, ItemBlockTooltip<BlockOre>> end) {
}
