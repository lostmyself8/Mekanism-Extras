package com.jerry.genextras.common.registries;

import com.jerry.genextras.common.GenExtraLang;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorCasing;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorController;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorLogicAdapter;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorPort;
import mekanism.common.block.attribute.Attributes;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.registries.GeneratorsSounds;

public class GenExtraBlockTypes {

    private GenExtraBlockTypes() {

    }

    // Naquadah Reactor Controller
    public static final BlockTypeTile<TileEntityNaquadahReactorController> NAQUADAH_REACTOR_CONTROLLER = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> GenExtraTileEntityTypes.NAQUADAH_REACTOR_CONTROLLER, GenExtraLang.DESCRIPTION_NAQUADAH_REACTOR_CONTROLLER)
            .withGui(() -> GenExtraContainerTypes.NAQUADAH_REACTOR_CONTROLLER, GenExtraLang.NAQUADAH_REACTOR)
            .withSound(GeneratorsSounds.FUSION_REACTOR)
            .with(Attributes.ACTIVE, Attributes.INVENTORY)
            .externalMultiblock()
            .build();
    // Naquadah Reactor Casing
    public static final BlockTypeTile<TileEntityNaquadahReactorCasing> NAQUADAH_REACTOR_CASING = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> GenExtraTileEntityTypes.NAQUADAH_REACTOR_CASING, GenExtraLang.DESCRIPTION_NAQUADAH_REACTOR_CASING)
            .externalMultiblock()
            .build();
    // Naquadah Reactor Port
    public static final BlockTypeTile<TileEntityNaquadahReactorPort> NAQUADAH_REACTOR_PORT = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> GenExtraTileEntityTypes.NAQUADAH_REACTOR_PORT, GenExtraLang.DESCRIPTION_NAQUADAH_REACTOR_PORT)
            .with(Attributes.ACTIVE)
            .externalMultiblock()
            .withComputerSupport("naquadahReactorPort")
            .build();
    // Naquadah Reactor Logic Adapter
    public static final BlockTypeTile<TileEntityNaquadahReactorLogicAdapter> NAQUADAH_REACTOR_LOGIC_ADAPTER = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> GenExtraTileEntityTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER, GeneratorsLang.DESCRIPTION_FUSION_REACTOR_LOGIC_ADAPTER)
            .withGui(() -> GenExtraContainerTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER)
            .with(new Attributes.AttributeRedstoneEmitter<>(TileEntityNaquadahReactorLogicAdapter::getRedstoneLevel))
            .externalMultiblock()
            .withComputerSupport("naquadahReactorLogicAdapter")
            .build();
}
