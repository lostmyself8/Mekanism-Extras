package com.jerry.mekanism_extras.integration.mekgenerators.genregistry;

import com.jerry.generator_extras.common.block.reactor.BlockLeadCoatedLaserFocusMatrix;
import com.jerry.generator_extras.common.tile.TileEntityLeadCoatedGlass;
import com.jerry.generator_extras.common.tile.reactor.*;
import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.block.basic.BlockStructuralGlass;
import mekanism.common.block.interfaces.IHasDescription;
import mekanism.common.block.prefab.BlockBasicMultiblock;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.generators.common.registries.GeneratorsBlockTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Supplier;

public class ExtraGenBlocks {
    public static final BlockDeferredRegister EXTRA_GEN_BLOCK = new BlockDeferredRegister(MekanismExtras.MODID);
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityNaquadahReactorController>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityNaquadahReactorController>>> NAQUADAH_REACTOR_CONTROLLER = registerBlock("naquadah_reactor_controller", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.NAQUADAH_REACTOR_CONTROLLER, properties -> properties.mapColor(MapColor.COLOR_ORANGE)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityNaquadahReactorCasing>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityNaquadahReactorCasing>>> NAQUADAH_REACTOR_CASING = registerBlock("naquadah_reactor_casing", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.NAQUADAH_REACTOR_CASING, properties -> properties.mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityNaquadahReactorPort>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityNaquadahReactorPort>>> NAQUADAH_REACTOR_PORT = registerBlock("naquadah_reactor_port", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.NAQUADAH_REACTOR_PORT, properties -> properties.mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final BlockRegistryObject<BlockBasicMultiblock<TileEntityNaquadahReactorLogicAdapter>, ItemBlockTooltip<BlockBasicMultiblock<TileEntityNaquadahReactorLogicAdapter>>> NAQUADAH_REACTOR_LOGIC_ADAPTER = registerBlock("naquadah_reactor_logic_adapter", () -> new BlockBasicMultiblock<>(ExtraGenBlockTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER, properties -> properties.mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final BlockRegistryObject<BlockStructuralGlass<TileEntityLeadCoatedGlass>, ItemBlockTooltip<BlockStructuralGlass<TileEntityLeadCoatedGlass>>> LEAD_COATED_GLASS = registerTooltipBlock("lead_coated_glass", () -> new BlockStructuralGlass<>(ExtraGenBlockTypes.LEAD_COATED_GLASS));
    public static final BlockRegistryObject<BlockLeadCoatedLaserFocusMatrix, ItemBlockTooltip<BlockLeadCoatedLaserFocusMatrix>> LEAD_COATED_LASER_FOCUS_MATRIX = registerTooltipBlock("lead_coated_laser_focus_matrix", BlockLeadCoatedLaserFocusMatrix::new);
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
