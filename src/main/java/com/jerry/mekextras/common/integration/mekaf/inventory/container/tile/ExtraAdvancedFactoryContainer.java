package com.jerry.mekextras.common.integration.mekaf.inventory.container.tile;

import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryContainerTypes;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityChemicalToChemicalExtraFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraAdvancedFactoryBase;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityLiquifyingExtraFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityPressurizedReactingExtraFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;

public class ExtraAdvancedFactoryContainer extends MekanismTileContainer<TileEntityExtraAdvancedFactoryBase<?>> {

    public ExtraAdvancedFactoryContainer(int id, Inventory inv, TileEntityExtraAdvancedFactoryBase<?> tile) {
        super(ExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        if (tile.hasExtraResourceBar()) {
            return tile instanceof TileEntityChemicalToChemicalExtraFactory<?> ? 121 : tile instanceof TileEntityPressurizedReactingExtraFactory ? 103 : 108;
        }
        return tile instanceof TileEntityChemicalToChemicalExtraFactory<?> ? 112 : tile instanceof TileEntityLiquifyingExtraFactory ? 85 : 98;
    }

    @Override
    protected int getInventoryXOffset() {
        int index = tile.tier.ordinal();
        return (22 * (index + 2)) - (3 * index);
    }
}
