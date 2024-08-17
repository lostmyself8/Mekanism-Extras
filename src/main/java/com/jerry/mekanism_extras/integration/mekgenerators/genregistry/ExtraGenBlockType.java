package com.jerry.mekanism_extras.integration.mekgenerators.genregistry;

import com.jerry.generator_extras.common.tile.reactor.TileEntityNaquadahReactorCasing;
import com.jerry.generator_extras.common.tile.reactor.TileEntityNaquadahReactorController;
import com.jerry.generator_extras.common.tile.reactor.TileEntityNaquadahReactorLogicAdapter;
import com.jerry.generator_extras.common.tile.reactor.TileEntityNaquadahReactorPort;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attributes;
import mekanism.common.content.blocktype.BlockTypeTile;

public class ExtraGenBlockType {

    // Naquadah Reactor Controller
    public static final BlockTypeTile<TileEntityNaquadahReactorController> NAQUADAH_REACTOR_CONTROLLER = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.NAQUADAH_REACTOR_CONTROLLER, MekanismLang.DESCRIPTION_BOILER_CASING)
            .withGui(() -> ExtraGenContainerTypes.NAQUADAH_REACTOR_CONTROLLER, MekanismLang.DESCRIPTION_BOILER_CASING)
//            .withSound(GeneratorsSounds.NAQUADAH_REACTOR)
            .with(Attributes.ACTIVE, Attributes.INVENTORY)
            .externalMultiblock()
            .build();
    // Naquadah Reactor Port
    public static final BlockTypeTile<TileEntityNaquadahReactorPort> NAQUADAH_REACTOR_PORT = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.NAQUADAH_REACTOR_PORT, MekanismLang.DESCRIPTION_BOILER_CASING)
            .with(Attributes.ACTIVE)
            .externalMultiblock()
            .withComputerSupport("naquadahReactorPort")
            .build();
    // Collider Casing
    public static final BlockTypeTile<TileEntityNaquadahReactorCasing> NAQUADAH_REACTOR_CASING = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.NAQUADAH_REACTOR_CASING, MekanismLang.DESCRIPTION_BOILER_CASING)
            .externalMultiblock()
            .build();
    // Naquadah Reactor Logic Adapter
    public static final BlockTypeTile<TileEntityNaquadahReactorLogicAdapter> NAQUADAH_REACTOR_LOGIC_ADAPTER = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER, MekanismLang.DESCRIPTION_BOILER_CASING)
            .withGui(() -> ExtraGenContainerTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER)
            .with(new Attributes.AttributeRedstoneEmitter<>(TileEntityNaquadahReactorLogicAdapter::getRedstoneLevel))
            .externalMultiblock()
            .withComputerSupport("naquadahReactorLogicAdapter")
            .build();
}
