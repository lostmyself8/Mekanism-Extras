package com.jerry.mekextras.common.integration.mekaf.inventory.container.tile;

import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryContainerTypes;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraChemicalToChemicalFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraAdvancedBase;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraLiquifyingFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraPRCFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;

public class ExtraAdvancedFactoryContainer extends MekanismTileContainer<TileEntityExtraAdvancedBase<?>> {

    public ExtraAdvancedFactoryContainer(int id, Inventory inv, TileEntityExtraAdvancedBase<?> tile) {
        super(ExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        if (tile.hasExtraResourceBar()) {
            return tile instanceof TileEntityExtraChemicalToChemicalFactory<?> ? 121 : tile instanceof TileEntityExtraPRCFactory ? 103 : 108;
        }
        return tile instanceof TileEntityExtraChemicalToChemicalFactory<?> ? 112 : tile instanceof TileEntityExtraLiquifyingFactory ? 85 : 98;
    }

    @Override
    protected int getInventoryXOffset() {
        int index = tile.tier.ordinal();
        return (22 * (index + 2)) - (3 * index);
    }
}
