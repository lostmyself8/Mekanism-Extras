package com.jerry.mekextras.common.integration.mekaf.inventory.container.tile;

import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryContainerTypes;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;

import mekanism.common.inventory.container.tile.MekanismTileContainer;

import net.minecraft.world.entity.player.Inventory;

public class ExtraAdvancedFactoryContainer extends MekanismTileContainer<TileEntityExtraAdvancedFactoryBase<?>> {

    public ExtraAdvancedFactoryContainer(int id, Inventory inv, TileEntityExtraAdvancedFactoryBase<?> tile) {
        super(ExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        int invY = 85 + 13 * tile.getTankCount();
        if (tile.hasExtraResourceBar()) {
            invY += 10 + 8 * (tile.getBarCount() - 1);
        }
        return invY;
    }

    @Override
    protected int getInventoryXOffset() {
        int index = tile.tier.ordinal();
        return (22 * (index + 2)) - (3 * index);
    }
}
