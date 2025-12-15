package com.jerry.mekextras.common.integration.mekmm.inventory.container.tile;

import com.jerry.mekextras.common.integration.mekmm.registries.ExtraMoreMachineContainerTypes;
import com.jerry.mekextras.common.integration.mekmm.tile.factory.TileEntityExtraMoreMachineFactory;
import com.jerry.mekextras.common.integration.mekmm.tile.factory.TileEntityExtraPlantingFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;

public class ExtraMoreMachineFactoryContainer extends MekanismTileContainer<TileEntityExtraMoreMachineFactory<?>> {

    public ExtraMoreMachineFactoryContainer(int id, Inventory inv, TileEntityExtraMoreMachineFactory<?> tile) {
        super(ExtraMoreMachineContainerTypes.MORE_MACHINE_FACTORY, id, inv, tile);
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
