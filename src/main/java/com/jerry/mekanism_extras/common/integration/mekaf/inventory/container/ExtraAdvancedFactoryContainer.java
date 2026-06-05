package com.jerry.mekanism_extras.common.integration.mekaf.inventory.container;

import com.jerry.mekanism_extras.common.integration.mekaf.registries.ExtraAdvancedFactoryContainerTypes;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraPressurizedReactingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraGasToGasFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraItemToItemAdvancedFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraSlurryToSlurryFactory;

import mekanism.common.inventory.container.tile.MekanismTileContainer;

import net.minecraft.world.entity.player.Inventory;

import org.jetbrains.annotations.NotNull;

public class ExtraAdvancedFactoryContainer extends MekanismTileContainer<TileEntityExtraAdvancedFactoryBase<?>> {

    public ExtraAdvancedFactoryContainer(int id, Inventory inv, @NotNull TileEntityExtraAdvancedFactoryBase<?> tile) {
        super(ExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY, id, inv, tile);
    }

    protected int getInventoryYOffset() {
        if (tile.hasExtrasResourceBar()) {
            if (tile instanceof TileEntityExtraGasToGasFactory<?> || tile instanceof TileEntityExtraSlurryToSlurryFactory<?>) {
                return 121;
            }
            if (tile instanceof TileEntityExtraItemToItemAdvancedFactory<?>) {
                return 95;
            } else {
                return tile instanceof TileEntityExtraPressurizedReactingFactory ? 103 : 108;
            }
        }
        if (tile instanceof TileEntityExtraGasToGasFactory<?> || tile instanceof TileEntityExtraSlurryToSlurryFactory<?>) {
            return 112;
        } else {
            return 98;
        }
    }

    protected int getInventoryXOffset() {
        int index = this.tile.tier.ordinal();
        return 22 * (index + 2) - 3 * index;
    }
}
