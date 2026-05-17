package com.jerry.mekanism_extras.common.integration.mekmm.inventory.container;

import com.jerry.mekanism_extras.common.integration.mekmm.registries.ExtraMoreMachineContainerTypes;
import com.jerry.mekanism_extras.common.integration.mekmm.tile.TileEntityExtraMoreMachineFactory;
import com.jerry.mekanism_extras.common.integration.mekmm.tile.TileEntityExtraPlantingFactory;

import mekanism.common.inventory.container.tile.MekanismTileContainer;

import net.minecraft.world.entity.player.Inventory;

import org.jetbrains.annotations.NotNull;

public class ExtraMoreMachineFactoryContainer extends MekanismTileContainer<TileEntityExtraMoreMachineFactory<?>> {

    public ExtraMoreMachineFactoryContainer(int id, Inventory inv, @NotNull TileEntityExtraMoreMachineFactory<?> tile) {
        super(ExtraMoreMachineContainerTypes.FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        if (tile.hasSecondaryResourceBar()) {
            return tile instanceof TileEntityExtraPlantingFactory ? 115 : 95;
        }
        return 85;
    }

    @Override
    protected int getInventoryXOffset() {
        int index = tile.tier.ordinal();
        return (22 * (index + 2)) - (3 * index);
    }
}
