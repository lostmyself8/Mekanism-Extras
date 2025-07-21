package com.jerry.mekextras.common.inventory.container.tile;

import com.jerry.mekextras.common.registries.ExtraContainerTypes;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekextras.common.tile.factory.TileEntitySawingExtraFactory;
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
        } else if (tile instanceof TileEntitySawingExtraFactory) {
            return 105;
        }
        return 85;
    }

    @Override
    protected int getInventoryXOffset() {
        int index = tile.tier.ordinal();
        return (22 * (index + 2)) - (3 * index);
    }
}
