package com.jerry.mekanism_extras.common.resource.ore;

import com.jerry.mekanism_extras.common.block.ExtraBlockOre;

import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.registration.impl.BlockRegistryObject;

public record ExtraOreBlockType(BlockRegistryObject<ExtraBlockOre, ItemBlockTooltip<ExtraBlockOre>> stone) {

    public ExtraOreBlockType(BlockRegistryObject<ExtraBlockOre, ItemBlockTooltip<ExtraBlockOre>> stone) {
        this.stone = stone;
    }

    public ExtraBlockOre stoneBlock() {
        return (ExtraBlockOre) this.stone.getBlock();
    }

    public BlockRegistryObject<ExtraBlockOre, ItemBlockTooltip<ExtraBlockOre>> stone() {
        return this.stone;
    }
}
