package com.jerry.generator_extras.common.genregistry;

import com.jerry.generator_extras.common.ExtraGenLang;
import com.jerry.generator_extras.common.tile.TileEntityLeadCoatedGlass;
import com.jerry.generator_extras.common.tile.naquadah.*;
import com.jerry.generator_extras.common.tile.plasma.*;

import mekanism.common.block.attribute.AttributeMultiblock;
import mekanism.common.block.attribute.AttributeStateFacing;
import mekanism.common.block.attribute.Attributes;
import mekanism.common.block.attribute.Attributes.AttributeCustomResistance;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.registries.GeneratorsSounds;

public class ExtraGenBlockTypes {

    ///////////////////////////////////////////////////////////////////////////
    // Naquadah Reactor
    ///////////////////////////////////////////////////////////////////////////

    // Naquadah Reactor Controller
    public static final BlockTypeTile<TileEntityNaquadahReactorController> NAQUADAH_REACTOR_CONTROLLER = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.NAQUADAH_REACTOR_CONTROLLER, ExtraGenLang.DESCRIPTION_NAQUADAH_REACTOR_CONTROLLER)
            .withGui(() -> ExtraGenContainerTypes.NAQUADAH_REACTOR_CONTROLLER, ExtraGenLang.NAQUADAH_REACTOR)
            .withSound(GeneratorsSounds.FUSION_REACTOR)
            .with(Attributes.ACTIVE, Attributes.INVENTORY)
            .externalMultiblock()
            .build();
    // Naquadah Reactor Port
    public static final BlockTypeTile<TileEntityNaquadahReactorPort> NAQUADAH_REACTOR_PORT = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.NAQUADAH_REACTOR_PORT, ExtraGenLang.DESCRIPTION_NAQUADAH_REACTOR_PORT)
            .with(Attributes.ACTIVE)
            .externalMultiblock()
            .withComputerSupport("naquadahReactorPort")
            .build();
    // Collider Casing
    public static final BlockTypeTile<TileEntityNaquadahReactorCasing> NAQUADAH_REACTOR_CASING = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.NAQUADAH_REACTOR_CASING, ExtraGenLang.DESCRIPTION_NAQUADAH_REACTOR_FRAME)
            .externalMultiblock()
            .build();
    // Naquadah Reactor Logic Adapter
    public static final BlockTypeTile<TileEntityNaquadahReactorLogicAdapter> NAQUADAH_REACTOR_LOGIC_ADAPTER = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER, GeneratorsLang.DESCRIPTION_FUSION_REACTOR_LOGIC_ADAPTER)
            .withGui(() -> ExtraGenContainerTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER)
            .with(new Attributes.AttributeRedstoneEmitter<>(TileEntityNaquadahReactorLogicAdapter::getRedstoneLevel))
            .externalMultiblock()
            .withComputerSupport("naquadahReactorLogicAdapter")
            .build();
    // Reactor Glass
    public static final BlockTypeTile<TileEntityLeadCoatedGlass> LEAD_COATED_GLASS = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.LEAD_COATED_GLASS, ExtraGenLang.DESCRIPTION_LEAD_COATED_GLASS)
            .with(AttributeMultiblock.STRUCTURAL, Attributes.AttributeMobSpawn.NEVER)
            .build();
    // Laser Focus Matrix
    public static final BlockTypeTile<TileEntityLeadCoatedLaserFocusMatrix> LEAD_COATED_LASER_FOCUS_MATRIX = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.LEAD_COATED_LASER_FOCUS_MATRIX, ExtraGenLang.DESCRIPTION_LEAD_COATED_LASER_FOCUS_MATRIX)
            .with(AttributeMultiblock.EXTERNAL, Attributes.AttributeMobSpawn.NEVER)
            .build();

    ///////////////////////////////////////////////////////////////////////////
    // Plasma Evaporation Plant
    ///////////////////////////////////////////////////////////////////////////

    // Plasma Evaporation Block
    public static final BlockTypeTile<TileEntityPlasmaEvaporationBlock> PLASMA_EVAPORATION_BLOCK = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.PLASMA_EVAPORATION_BLOCK, ExtraGenLang.DESCRIPTION_PLASMA_EVAPORATION_BLOCK)
            .externalMultiblock()
            .build();
    public static final BlockTypeTile<TileEntityPlasmaEvaporationController> PLASMA_EVAPORATION_CONTROLLER = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.PLASMA_EVAPORATION_CONTROLLER, ExtraGenLang.DESCRIPTION_PLASMA_EVAPORATION_CONTROLLER)
            .withGui(() -> ExtraGenContainerTypes.PLASMA_EVAPORATION_CONTROLLER, ExtraGenLang.PLASMA_EVAPORATION)
            .with(Attributes.INVENTORY, Attributes.ACTIVE, new AttributeStateFacing(), new AttributeCustomResistance(18))
            .externalMultiblock()
            .build();
    public static final BlockTypeTile<TileEntityPlasmaEvaporationValve> PLASMA_EVAPORATION_VALVE = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.PLASMA_EVAPORATION_VALVE, ExtraGenLang.DESCRIPTION_PLASMA_EVAPORATION_VALVE)
            .with(Attributes.COMPARATOR, new AttributeCustomResistance(18), Attributes.ACTIVE)
            .externalMultiblock()
            .build();
    public static final BlockTypeTile<TileEntityPlasmaInsulationLayer> PLASMA_INSULATION_LAYER = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.PLASMA_INSULATION_LAYER, ExtraGenLang.DESCRIPTION_PLASMA_INSULATION_LAYER)
            .internalMultiblock()
            .build();
    public static final BlockTypeTile<TileEntityPlasmaEvaporationVent> PLASMA_EVAPORATION_VENT = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.PLASMA_EVAPORATION_VENT, ExtraGenLang.DESCRIPTION_PLASMA_EVAPORATION_VENT)
            .externalMultiblock()
            .build();
    public static final BlockTypeTile<TileEntityFusionReactorPlasmaExtractingPort> FUSION_REACTOR_PLASMA_EXTRACTING_PORT = BlockTypeTile.BlockTileBuilder
            .createBlock(() -> ExtraGenTileEntityTypes.FUSION_REACTOR_PLASMA_EXTRACTING_PORT, ExtraGenLang.DESCRIPTION_FUSION_REACTOR_PLASMA_EXTRACTING_PORT)
            .with(Attributes.ACTIVE)
            .externalMultiblock()
            .build();
}
