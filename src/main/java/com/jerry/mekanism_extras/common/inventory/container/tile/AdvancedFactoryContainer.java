package com.jerry.mekanism_extras.common.inventory.container.tile;

import com.jerry.mekanism_extras.common.registry.ExtraContainerTypes;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityAdvancedFactory;
import com.jerry.mekanism_extras.common.tile.factory.TileEntitySawingAdvancedFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;

public class AdvancedFactoryContainer extends MekanismTileContainer<TileEntityAdvancedFactory<?>> {

    public AdvancedFactoryContainer(int id, Inventory inv, TileEntityAdvancedFactory<?> tile) {
        super(ExtraContainerTypes.FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        if (tile.hasSecondaryResourceBar()) {
            return 95;
        } else if (tile instanceof TileEntitySawingAdvancedFactory) {
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
