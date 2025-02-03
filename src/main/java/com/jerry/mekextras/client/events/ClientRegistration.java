package com.jerry.mekextras.client.events;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.client.gui.*;
import com.jerry.mekextras.client.gui.machine.GuiAdvanceElectricPump;
import com.jerry.mekextras.client.model.ColorModelEnergyCore;
import com.jerry.mekextras.client.model.energycube.ExtraEnergyCubeModelLoader;
import com.jerry.mekextras.client.render.item.block.ExtraRenderEnergyCubeItem;
import com.jerry.mekextras.client.render.item.block.ExtraRenderFluidTankItem;
import com.jerry.mekextras.client.render.tileentity.ExtraRenderBin;
import com.jerry.mekextras.client.render.tileentity.ExtraRenderEnergyCube;
import com.jerry.mekextras.client.render.tileentity.ExtraRenderFluidTank;
import com.jerry.mekextras.client.render.transmitter.*;
import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.tier.TierColor;
import com.jerry.mekextras.common.tier.ECTier;
import com.jerry.mekextras.common.item.block.ExtraItemBlockEnergyCube;
import com.jerry.mekextras.common.item.block.machine.ExtraItemBlockFluidTank;
import com.jerry.mekextras.common.tier.FTTier;
import com.jerry.mekextras.common.registry.ExtraBlocks;
import com.jerry.mekextras.common.registry.ExtraContainerTypes;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;
import mekanism.api.text.EnumColor;
import mekanism.client.ClientRegistrationUtil;
import com.jerry.mekextras.common.registry.ExtraTileEntityTypes;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.RenderPropertiesProvider;
import mekanism.client.render.item.TransmitterTypeDecorator;
import mekanism.common.registries.*;
import mekanism.common.util.WorldUtils;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;

@EventBusSubscriber(modid = MekanismExtras.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistration {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.register(new ClientTick());
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderBin::new, ExtraTileEntityTypes.ABSOLUTE_BIN, ExtraTileEntityTypes.SUPREME_BIN, ExtraTileEntityTypes.COSMIC_BIN,
                ExtraTileEntityTypes.INFINITE_BIN);
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderEnergyCube::new, ExtraTileEntityTypes.ABSOLUTE_ENERGY_CUBE, ExtraTileEntityTypes.SUPREME_ENERGY_CUBE,
                ExtraTileEntityTypes.COSMIC_ENERGY_CUBE, ExtraTileEntityTypes.INFINITE_ENERGY_CUBE);
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderFluidTank::new, ExtraTileEntityTypes.ABSOLUTE_FLUID_TANK, ExtraTileEntityTypes.SUPREME_FLUID_TANK,
                ExtraTileEntityTypes.COSMIC_FLUID_TANK, ExtraTileEntityTypes.INFINITE_FLUID_TANK);
        //Transmitters
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderLogisticalTransporter::new, ExtraTileEntityTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER, ExtraTileEntityTypes.SUPREME_LOGISTICAL_TRANSPORTER,
                ExtraTileEntityTypes.COSMIC_LOGISTICAL_TRANSPORTER, ExtraTileEntityTypes.INFINITE_LOGISTICAL_TRANSPORTER);
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderMechanicalPipe::new, ExtraTileEntityTypes.ABSOLUTE_MECHANICAL_PIPE,
                ExtraTileEntityTypes.SUPREME_MECHANICAL_PIPE, ExtraTileEntityTypes.COSMIC_MECHANICAL_PIPE, ExtraTileEntityTypes.INFINITE_MECHANICAL_PIPE);
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderPressurizedTube::new, ExtraTileEntityTypes.ABSOLUTE_PRESSURIZED_TUBE,
                ExtraTileEntityTypes.SUPREME_PRESSURIZED_TUBE, ExtraTileEntityTypes.COSMIC_PRESSURIZED_TUBE, ExtraTileEntityTypes.INFINITE_PRESSURIZED_TUBE);
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderUniversalCable::new, ExtraTileEntityTypes.ABSOLUTE_UNIVERSAL_CABLE,
                ExtraTileEntityTypes.SUPREME_UNIVERSAL_CABLE, ExtraTileEntityTypes.COSMIC_UNIVERSAL_CABLE, ExtraTileEntityTypes.INFINITE_UNIVERSAL_CABLE);
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderThermodynamicConductor::new, ExtraTileEntityTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR,
                ExtraTileEntityTypes.SUPREME_THERMODYNAMIC_CONDUCTOR, ExtraTileEntityTypes.COSMIC_THERMODYNAMIC_CONDUCTOR, ExtraTileEntityTypes.INFINITE_THERMODYNAMIC_CONDUCTOR);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ColorModelEnergyCore.CORE_LAYER, ColorModelEnergyCore::createLayerDefinition);
    }

    @SubscribeEvent
    public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
        ClientRegistrationUtil.registerClientReloadListeners(event, ExtraRenderEnergyCubeItem.EXTRA_RENDERER);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.ADVANCE_ELECTRIC_PUMP, GuiAdvanceElectricPump::new);

        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.EXTRA_ENERGY_CUBE,ExtraGuiEnergyCube::new);
        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.EXTRA_FLUID_TANK, ExtraGuiFluidTank::new);
        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.EXTRA_CHEMICAL_TANK, ExtraGuiChemicalTank::new);

        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX, GuiReinforcedInductionMatrix::new);
        ClientRegistrationUtil.registerScreen(event, ExtraContainerTypes.REINFORCED_MATRIX_STATS, GuiReinforcedMatrixStats::new);
    }

    @SubscribeEvent
    public static void registerModelLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(MekanismExtras.rl("energy_cube"), ExtraEnergyCubeModelLoader.INSTANCE);
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, tintIndex) -> {
            if (tintIndex == 1) {
                FTTier tier = ExtraAttribute.getAdvanceTier(state.getBlock(), FTTier.class);
                if (tier != null) {
                    float[] color = TierColor.getColor(tier);
                    return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
                }
            }
            return -1;
        }, ExtraBlocks.ABSOLUTE_FLUID_TANK, ExtraBlocks.SUPREME_FLUID_TANK, ExtraBlocks.COSMIC_FLUID_TANK, ExtraBlocks.INFINITE_FLUID_TANK);

        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, index) -> {
                    if (index == 1) {
                        ECTier tier = ExtraAttribute.getAdvanceTier(state.getBlock(), ECTier.class);
                        if (tier != null) {
                            float[] color = TierColor.getColor(tier);
                            return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
                        }
                    }
                    return -1;
                }, ExtraBlocks.ABSOLUTE_ENERGY_CUBE, ExtraBlocks.SUPREME_ENERGY_CUBE, ExtraBlocks.COSMIC_ENERGY_CUBE,
                ExtraBlocks.INFINITE_ENERGY_CUBE);

        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, tintIndex) -> {
                    if (tintIndex == 1 && pos != null) {
                        ExtraTileEntityLogisticalTransporter transporter = WorldUtils.getTileEntity(ExtraTileEntityLogisticalTransporter.class, world, pos);
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
        //fluid tank
        ClientRegistrationUtil.registerItemColorHandler(event, (stack, tintIndex) -> {
            Item item = stack.getItem();
            if (tintIndex == 1 && item instanceof ExtraItemBlockFluidTank tank) {
                float[] color = TierColor.getColor(tank.getAdvanceTier());
                return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
            }
            return -1;
        }, ExtraBlocks.ABSOLUTE_FLUID_TANK, ExtraBlocks.SUPREME_FLUID_TANK, ExtraBlocks.COSMIC_FLUID_TANK, ExtraBlocks.INFINITE_FLUID_TANK);
        ClientRegistrationUtil.registerBucketColorHandler(event, MekanismFluids.FLUIDS);

        //energy cube
        ClientRegistrationUtil.registerItemColorHandler(event, (stack, tintIndex) -> {
            Item item = stack.getItem();
            if (tintIndex == 1 && item instanceof ExtraItemBlockEnergyCube cube) {
                float[] color = TierColor.getColor(cube.getAdvanceTier());
                return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
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
        ClientRegistrationUtil.registerItemExtensions(event, new RenderPropertiesProvider.MekRenderProperties(ExtraRenderEnergyCubeItem.EXTRA_RENDERER), ExtraBlocks.ABSOLUTE_ENERGY_CUBE,
                ExtraBlocks.SUPREME_ENERGY_CUBE, ExtraBlocks.COSMIC_ENERGY_CUBE, ExtraBlocks.INFINITE_ENERGY_CUBE);
        ClientRegistrationUtil.registerItemExtensions(event, new RenderPropertiesProvider.MekRenderProperties(ExtraRenderFluidTankItem.EXTRA_RENDERER), ExtraBlocks.ABSOLUTE_FLUID_TANK,
                ExtraBlocks.SUPREME_FLUID_TANK, ExtraBlocks.COSMIC_FLUID_TANK, ExtraBlocks.INFINITE_FLUID_TANK);
    }
}
