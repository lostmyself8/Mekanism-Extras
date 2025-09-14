package com.jerry.generator_extras.common.genregistry;

import com.jerry.generator_extras.common.block.reactor.BlockLeadCoatedLaserFocusMatrix;
import com.jerry.generator_extras.common.tile.TileEntityLeadCoatedGlass;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorCasing;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorController;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorLogicAdapter;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorPort;
import com.jerry.generator_extras.common.tile.plasma.*;
import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.block.basic.BlockStructuralGlass;
import mekanism.common.block.interfaces.IHasDescription;
import mekanism.common.block.prefab.BlockBasicMultiblock;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Supplier;

public class ExtraGenBlocks {

    public static final BlockDeferredRegister EXTRA_GEN_BLOCK = new BlockDeferredRegister(MekanismExtras.MODID);

    // Naquadah Reactor
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityNaquadahReactorController>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityNaquadahReactorController>>> NAQUADAH_REACTOR_CONTROLLER = registerBlock("naquadah_reactor_controller", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.NAQUADAH_REACTOR_CONTROLLER, properties -> properties.mapColor(MapColor.COLOR_ORANGE)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityNaquadahReactorCasing>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityNaquadahReactorCasing>>> NAQUADAH_REACTOR_CASING = registerBlock("naquadah_reactor_casing", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.NAQUADAH_REACTOR_CASING, properties -> properties.mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityNaquadahReactorPort>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityNaquadahReactorPort>>> NAQUADAH_REACTOR_PORT = registerBlock("naquadah_reactor_port", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.NAQUADAH_REACTOR_PORT, properties -> properties.mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityNaquadahReactorLogicAdapter>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityNaquadahReactorLogicAdapter>>> NAQUADAH_REACTOR_LOGIC_ADAPTER = registerBlock("naquadah_reactor_logic_adapter", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER, properties -> properties.mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final BlockRegistryObject<BlockStructuralGlass<TileEntityLeadCoatedGlass>, ItemBlockTooltip<BlockStructuralGlass<TileEntityLeadCoatedGlass>>> LEAD_COATED_GLASS = registerTooltipBlock("lead_coated_glass", () -> new BlockStructuralGlass<>(ExtraGenBlockTypes.LEAD_COATED_GLASS));
    public static final BlockRegistryObject<BlockLeadCoatedLaserFocusMatrix, ItemBlockTooltip<BlockLeadCoatedLaserFocusMatrix>> LEAD_COATED_LASER_FOCUS_MATRIX = registerTooltipBlock("lead_coated_laser_focus_matrix", BlockLeadCoatedLaserFocusMatrix::new);

    // Plasma Evaporation Plant
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityPlasmaEvaporationBlock>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityPlasmaEvaporationBlock>>> PLASMA_EVAPORATION_BLOCK = registerTooltipBlock("plasma_evaporation_block", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.PLASMA_EVAPORATION_BLOCK, properties -> properties.mapColor(MapColor.GOLD)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityPlasmaEvaporationController>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityPlasmaEvaporationController>>> PLASMA_EVAPORATION_CONTROLLER = registerTooltipBlock("plasma_evaporation_controller", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.PLASMA_EVAPORATION_CONTROLLER, properties -> properties.mapColor(MapColor.GOLD)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityPlasmaEvaporationValve>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityPlasmaEvaporationValve>>> PLASMA_EVAPORATION_VALVE = registerTooltipBlock("plasma_evaporation_valve", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.PLASMA_EVAPORATION_VALVE, properties -> properties.mapColor(MapColor.GOLD)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityPlasmaInsulationLayer>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityPlasmaInsulationLayer>>> PLASMA_INSULATION_LAYER = registerTooltipBlock("plasma_insulation_layer", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.PLASMA_INSULATION_LAYER, properties -> properties.mapColor(MapColor.GOLD)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityPlasmaEvaporationVent>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityPlasmaEvaporationVent>>> PLASMA_EVAPORATION_VENT = registerTooltipBlock("plasma_evaporation_vent", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.PLASMA_EVAPORATION_VENT, properties -> properties.mapColor(MapColor.GOLD)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityFusionReactorPlasmaExtractingPort>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityFusionReactorPlasmaExtractingPort>>> FUSION_REACTOR_PLASMA_EXTRACTING_PORT = registerTooltipBlock("fusion_reactor_plasma_extracting_port", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.FUSION_REACTOR_PLASMA_EXTRACTING_PORT, properties -> properties.mapColor(MapColor.TERRACOTTA_BROWN)));

    private static <BLOCK extends Block & IHasDescription> BlockRegistryObject<BLOCK, ItemBlockTooltip<BLOCK>> registerBlock(String name, Supplier<? extends BLOCK> blockSupplier) {
        return EXTRA_GEN_BLOCK.registerDefaultProperties(name, blockSupplier, ItemBlockTooltip::new);
    }
    private static <BLOCK extends Block & IHasDescription> BlockRegistryObject<BLOCK, ItemBlockTooltip<BLOCK>> registerTooltipBlock(String name, Supplier<BLOCK> blockCreator) {
        return EXTRA_GEN_BLOCK.registerDefaultProperties(name, blockCreator, ItemBlockTooltip::new);
    }
    public static void register(IEventBus eventBus) {
        EXTRA_GEN_BLOCK.register(eventBus);
    }
}
