package com.jerry.mekanism_extras.common.inventory.container.tile;

import com.jerry.mekanism_extras.common.registries.ExtraContainerTypes;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraSawingFactory;

import mekanism.common.inventory.container.tile.MekanismTileContainer;

import net.minecraft.world.entity.player.Inventory;

public class ExtraFactoryContainer extends MekanismTileContainer<TileEntityExtraFactory<?>> {

    public ExtraFactoryContainer(int id, Inventory inv, TileEntityExtraFactory<?> tile) {
        super(ExtraContainerTypes.FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        if (tile.hasSecondaryResourceBar()) {
            return 95;
        } else if (tile instanceof TileEntityExtraSawingFactory) {
            return 105;
        }
        return 85;
    }

    @Override
    protected int getInventoryXOffset() {
        // 这个公式似乎并非完美，在index过大时可能会导致有细微的便宜，但未得到验证
        int index = tile.tier.ordinal();
        return (22 * (index + 2)) - (3 * index);
    }
}
