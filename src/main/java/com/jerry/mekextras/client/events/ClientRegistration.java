package com.jerry.mekextras.client.events;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.client.gui.*;
import com.jerry.mekextras.client.gui.machine.GuiAdvanceElectricPump;
import com.jerry.mekextras.client.gui.machine.GuiExtraAdvancedFactory;
import com.jerry.mekextras.client.gui.machine.GuiExtraFactory;
import com.jerry.mekextras.client.gui.machine.GuiExtraMoreMachineFactory;
import com.jerry.mekextras.client.model.ColorModelEnergyCore;
import com.jerry.mekextras.client.model.energycube.ExtraEnergyCubeModelLoader;
import com.jerry.mekextras.client.render.item.block.RenderExtraEnergyCubeItem;
import com.jerry.mekextras.client.render.item.block.RenderExtraFluidTankItem;
import com.jerry.mekextras.client.render.tileentity.RenderExtraBin;
import com.jerry.mekextras.client.render.tileentity.RenderExtraEnergyCube;
import com.jerry.mekextras.client.render.tileentity.RenderExtraFluidTank;
import com.jerry.mekextras.client.render.transmitter.*;
import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryContainerTypes;
import com.jerry.mekextras.common.integration.mekmm.registries.ExtraMoreMachineContainerTypes;
import com.jerry.mekextras.common.item.block.ItemBlockExtraEnergyCube;
import com.jerry.mekextras.common.item.block.machine.ItemBlockExtraFluidTank;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.registries.ExtraContainerTypes;
import com.jerry.mekextras.common.registries.ExtraFluids;
import com.jerry.mekextras.common.registries.ExtraTileEntityTypes;
import com.jerry.mekextras.common.tier.ECTier;
import com.jerry.mekextras.common.tier.FTTier;
import com.jerry.mekextras.common.tier.TierColor;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraLogisticalTransporter;

import com.jerry.genextras.client.gui.*;
import com.jerry.genextras.client.render.RenderNaquadahReactor;
import com.jerry.genextras.common.registries.GenExtraBlocks;
import com.jerry.genextras.common.registries.GenExtraContainerTypes;
import com.jerry.genextras.common.registries.GenExtraFluids;
import com.jerry.genextras.common.registries.GenExtraTileEntityTypes;

import mekanism.api.text.EnumColor;
import mekanism.client.ClientRegistrationUtil;
import mekanism.client.render.RenderPropertiesProvider;
import mekanism.client.render.item.TransmitterTypeDecorator;
import mekanism.common.util.WorldUtils;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;

@EventBusSubscriber(modid = MekanismExtras.MOD_ID, value = Dist.CLIENT)
public class ClientRegistration {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.register(new ClientTick());
        event.enqueueWork(() -> {
            for (Holder<Fluid> fluid : ExtraFluids.EXTRA_FLUIDS.getFluidEntries()) {
                ItemBlockRenderTypes.setRenderLayer(fluid.value(), RenderType.translucent());
            }
            if (MekanismExtras.hooks.mekanismGenerators.isLoaded()) {
                for (Holder<Fluid> fluid : GenExtraFluids.GEN_EXTRA_FLUIDS.getFluidEntries()) {
                    ItemBlockRenderTypes.setRenderLayer(fluid.value(), RenderType.translucent());
                }
            }
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderExtraBin::new, ExtraTileEntityTypes.ABSOLUTE_BIN, ExtraTileEntityTypes.SUPREME_BIN, ExtraTileEntityTypes.COSMIC_BIN,
                ExtraTileEntityTypes.INFINITE_BIN);
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderExtraEnergyCube::new, ExtraTileEntityTypes.ABSOLUTE_ENERGY_CUBE, ExtraTileEntityTypes.SUPREME_ENERGY_CUBE,
                ExtraTileEntityTypes.COSMIC_ENERGY_CUBE, ExtraTileEntityTypes.INFINITE_ENERGY_CUBE);
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderExtraFluidTank::new, ExtraTileEntityTypes.ABSOLUTE_FLUID_TANK, ExtraTileEntityTypes.SUPREME_FLUID_TANK,
                ExtraTileEntityTypes.COSMIC_FLUID_TANK, ExtraTileEntityTypes.INFINITE_FLUID_TANK);
        // Transmitters
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderExtraLogisticalTransporter::new, ExtraTileEntityTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER, ExtraTileEntityTypes.SUPREME_LOGISTICAL_TRANSPORTER,
                ExtraTileEntityTypes.COSMIC_LOGISTICAL_TRANSPORTER, ExtraTileEntityTypes.INFINITE_LOGISTICAL_TRANSPORTER);
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderExtraMechanicalPipe::new, ExtraTileEntityTypes.ABSOLUTE_MECHANICAL_PIPE,
                ExtraTileEntityTypes.SUPREME_MECHANICAL_PIPE, ExtraTileEntityTypes.COSMIC_MECHANICAL_PIPE, ExtraTileEntityTypes.INFINITE_MECHANICAL_PIPE);
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderExtraPressurizedTube::new, ExtraTileEntityTypes.ABSOLUTE_PRESSURIZED_TUBE,
                ExtraTileEntityTypes.SUPREME_PRESSURIZED_TUBE, ExtraTileEntityTypes.COSMIC_PRESSURIZED_TUBE, ExtraTileEntityTypes.INFINITE_PRESSURIZED_TUBE);
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderExtraUniversalCable::new, ExtraTileEntityTypes.ABSOLUTE_UNIVERSAL_CABLE,
                ExtraTileEntityTypes.SUPREME_UNIVERSAL_CABLE, ExtraTileEntityTypes.COSMIC_UNIVERSAL_CABLE, ExtraTileEntityTypes.INFINITE_UNIVERSAL_CABLE);
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderExtraThermodynamicConductor::new, ExtraTileEntityTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR,
                ExtraTileEntityTypes.SUPREME_THERMODYNAMIC_CONDUCTOR, ExtraTileEntityTypes.COSMIC_THERMODYNAMIC_CONDUCTOR, ExtraTileEntityTypes.INFINITE_THERMODYNAMIC_CONDUCTOR);

        // Generator Extras
        if (MekanismExtras.hooks.mekanismGenerators.isLoaded()) {
            event.registerBlockEntityRenderer(GenExtraTileEntityTypes.NAQUADAH_REACTOR_CONTROLLER.get(), RenderNaquadahReactor::new);
        }
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ColorModelEnergyCore.CORE_LAYER, ColorModelEnergyCore::createLayerDefinition);
    }

    @SubscribeEvent
    public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
        ClientRegistrationUtil.registerClientReloadListeners(event, RenderExtraEnergyCubeItem.EXTRA_RENDERER);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.ADVANCE_ELECTRIC_PUMP, GuiAdvanceElectricPump::new);

        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.EXTRA_ENERGY_CUBE, GuiExtraEnergyCube::new);
        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.EXTRA_FLUID_TANK, GuiExtraFluidTank::new);
        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.EXTRA_CHEMICAL_TANK, GuiExtraChemicalTank::new);

        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.FACTORY, GuiExtraFactory::new);

        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX, GuiReinforcedInductionMatrix::new);
        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.REINFORCED_MATRIX_STATS, GuiReinforcedMatrixStats::new);

        // Generator Extras
        if (MekanismExtras.hooks.mekanismGenerators.isLoaded()) {
            ClientRegistrationUtil.registerScreen(event, GenExtraContainerTypes.NAQUADAH_REACTOR_CONTROLLER, GuiNaquadahReactorController::new);
            ClientRegistrationUtil.registerScreen(event, GenExtraContainerTypes.NAQUADAH_REACTOR_FUEL, GuiNaquadahReactorFuel::new);
            ClientRegistrationUtil.registerScreen(event, GenExtraContainerTypes.NAQUADAH_REACTOR_HEAT, GuiNaquadahReactorHeat::new);
            ClientRegistrationUtil.registerScreen(event, GenExtraContainerTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER, GuiNaquadahReactorLogicAdapter::new);
            ClientRegistrationUtil.registerScreen(event, GenExtraContainerTypes.NAQUADAH_REACTOR_STATS, GuiNaquadahReactorStats::new);
        }

        // MoreMachine
        if (MekanismExtras.hooks.mekmm.isLoaded()) {
            ClientRegistrationUtil.registerScreen(event, ExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY, GuiExtraAdvancedFactory::new);
            ClientRegistrationUtil.registerScreen(event, ExtraMoreMachineContainerTypes.MORE_MACHINE_FACTORY, GuiExtraMoreMachineFactory::new);
        }
    }

    @SubscribeEvent
    public static void registerModelLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(MekanismExtras.rl("energy_cube"), ExtraEnergyCubeModelLoader.INSTANCE);
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, tintIndex) -> {
            if (tintIndex == 1) {
                FTTier tier = ExtraAttribute.getAdvancedTier(state.getBlock(), FTTier.class);
                if (tier != null) {
                    return TierColor.getPackedColor(tier);
                }
            }
            return -1;
        }, ExtraBlocks.ABSOLUTE_FLUID_TANK, ExtraBlocks.SUPREME_FLUID_TANK, ExtraBlocks.COSMIC_FLUID_TANK, ExtraBlocks.INFINITE_FLUID_TANK);

        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, index) -> {
            if (index == 1) {
                ECTier tier = ExtraAttribute.getAdvancedTier(state.getBlock(), ECTier.class);
                if (tier != null) {
                    return TierColor.getPackedColor(tier);
                }
            }
            return -1;
        }, ExtraBlocks.ABSOLUTE_ENERGY_CUBE, ExtraBlocks.SUPREME_ENERGY_CUBE, ExtraBlocks.COSMIC_ENERGY_CUBE,
                ExtraBlocks.INFINITE_ENERGY_CUBE);

        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, tintIndex) -> {
            if (tintIndex == 1 && pos != null) {
                TileEntityExtraLogisticalTransporter transporter = WorldUtils.getTileEntity(TileEntityExtraLogisticalTransporter.class, world, pos);
                if (transporter != null) {
                    EnumColor renderColor = transporter.getTransmitter().getColor();
                    if (renderColor != null) {
                        return renderColor.getPackedColor();
                    }
                }
            }
            return -1;
        }, ExtraBlocks.ABSOLUTE_LOGISTICAL_TRANSPORTER, ExtraBlocks.SUPREME_LOGISTICAL_TRANSPORTER, ExtraBlocks.COSMIC_LOGISTICAL_TRANSPORTER,
                ExtraBlocks.INFINITE_LOGISTICAL_TRANSPORTER);
    }

    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        ClientRegistrationUtil.registerBucketColorHandler(event, ExtraFluids.EXTRA_FLUIDS);
        if (MekanismExtras.hooks.mekanismGenerators.isLoaded()) {
            ClientRegistrationUtil.registerBucketColorHandler(event, GenExtraFluids.GEN_EXTRA_FLUIDS);
        }
        // fluid tank
        ClientRegistrationUtil.registerItemColorHandler(event, (stack, tintIndex) -> {
            Item item = stack.getItem();
            if (tintIndex == 1 && item instanceof ItemBlockExtraFluidTank tank) {
                return TierColor.getPackedColor(tank.getAdvancedTier());
            }
            return -1;
        }, ExtraBlocks.ABSOLUTE_FLUID_TANK, ExtraBlocks.SUPREME_FLUID_TANK, ExtraBlocks.COSMIC_FLUID_TANK, ExtraBlocks.INFINITE_FLUID_TANK);

        // energy cube
        ClientRegistrationUtil.registerItemColorHandler(event, (stack, tintIndex) -> {
            Item item = stack.getItem();
            if (tintIndex == 1 && item instanceof ItemBlockExtraEnergyCube cube) {
                return TierColor.getPackedColor(cube.getAdvancedTier());
            }
            return -1;
        }, ExtraBlocks.ABSOLUTE_ENERGY_CUBE, ExtraBlocks.SUPREME_ENERGY_CUBE, ExtraBlocks.COSMIC_ENERGY_CUBE, ExtraBlocks.INFINITE_ENERGY_CUBE);
    }

    @SubscribeEvent
    public static void registerItemDecorations(RegisterItemDecorationsEvent event) {
        TransmitterTypeDecorator.registerDecorators(event, ExtraBlocks.ABSOLUTE_PRESSURIZED_TUBE, ExtraBlocks.SUPREME_PRESSURIZED_TUBE,
                ExtraBlocks.COSMIC_PRESSURIZED_TUBE, ExtraBlocks.INFINITE_PRESSURIZED_TUBE, ExtraBlocks.ABSOLUTE_THERMODYNAMIC_CONDUCTOR,
                ExtraBlocks.SUPREME_THERMODYNAMIC_CONDUCTOR, ExtraBlocks.COSMIC_THERMODYNAMIC_CONDUCTOR, ExtraBlocks.INFINITE_THERMODYNAMIC_CONDUCTOR,
                ExtraBlocks.ABSOLUTE_UNIVERSAL_CABLE, ExtraBlocks.SUPREME_UNIVERSAL_CABLE, ExtraBlocks.COSMIC_UNIVERSAL_CABLE, ExtraBlocks.INFINITE_UNIVERSAL_CABLE);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        ClientRegistrationUtil.registerItemExtensions(event, new RenderPropertiesProvider.MekRenderProperties(RenderExtraEnergyCubeItem.EXTRA_RENDERER), ExtraBlocks.ABSOLUTE_ENERGY_CUBE,
                ExtraBlocks.SUPREME_ENERGY_CUBE, ExtraBlocks.COSMIC_ENERGY_CUBE, ExtraBlocks.INFINITE_ENERGY_CUBE);
        ClientRegistrationUtil.registerItemExtensions(event, new RenderPropertiesProvider.MekRenderProperties(RenderExtraFluidTankItem.EXTRA_RENDERER), ExtraBlocks.ABSOLUTE_FLUID_TANK,
                ExtraBlocks.SUPREME_FLUID_TANK, ExtraBlocks.COSMIC_FLUID_TANK, ExtraBlocks.INFINITE_FLUID_TANK);
        ClientRegistrationUtil.registerBlockExtensions(event, ExtraBlocks.EXTRA_BLOCKS);
        ClientRegistrationUtil.registerFluidExtensions(event, ExtraFluids.EXTRA_FLUIDS);
        if (MekanismExtras.hooks.mekanismGenerators.isLoaded()) {
            ClientRegistrationUtil.registerBlockExtensions(event, GenExtraBlocks.GEN_EXTRA_BLOCKS);
            ClientRegistrationUtil.registerFluidExtensions(event, GenExtraFluids.GEN_EXTRA_FLUIDS);
        }
    }
}
