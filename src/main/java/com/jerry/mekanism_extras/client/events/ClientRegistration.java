package com.jerry.mekanism_extras.client.events;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.client.gui.*;
import com.jerry.mekanism_extras.client.gui.machine.GuiAdvancedElectricPump;
import com.jerry.mekanism_extras.client.gui.machine.GuiAdvancedFactory;
import com.jerry.mekanism_extras.client.model.ExtraModelEnergyCore;
import com.jerry.mekanism_extras.client.model.energycube.ExtraEnergyCubeModelLoader;
import com.jerry.mekanism_extras.client.render.item.block.ExtraRenderEnergyCubeItem;
import com.jerry.mekanism_extras.client.render.tileentity.ExtraRenderBin;
import com.jerry.mekanism_extras.client.render.tileentity.ExtraRenderEnergyCube;
import com.jerry.mekanism_extras.client.render.tileentity.ExtraRenderFluidTank;
import com.jerry.mekanism_extras.client.render.transmitter.*;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockEnergyCube;
import com.jerry.mekanism_extras.common.item.block.machine.ExtraItemBlockFluidTank;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import com.jerry.mekanism_extras.common.registry.ExtraContainerTypes;
import com.jerry.mekanism_extras.common.registry.ExtraFluids;
import com.jerry.mekanism_extras.common.registry.ExtraTileEntityTypes;
import com.jerry.mekanism_extras.common.tier.ECTier;
import com.jerry.mekanism_extras.common.tier.FTTier;
import com.jerry.mekanism_extras.common.tier.TierColor;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;

import mekanism.api.text.EnumColor;
import mekanism.client.ClientRegistrationUtil;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.item.TransmitterTypeDecorator;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registries.*;
import mekanism.common.resource.IResource;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.util.WorldUtils;

import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import com.google.common.collect.Table;

import java.util.Map;

@Mod.EventBusSubscriber(modid = MekanismExtras.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // universal cable
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderUniversalCable::new, ExtraTileEntityTypes.ABSOLUTE_UNIVERSAL_CABLE,
                ExtraTileEntityTypes.SUPREME_UNIVERSAL_CABLE, ExtraTileEntityTypes.COSMIC_UNIVERSAL_CABLE, ExtraTileEntityTypes.INFINITE_UNIVERSAL_CABLE);
        // logistical transporter
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderLogisticalTransporter::new, ExtraTileEntityTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER,
                ExtraTileEntityTypes.SUPREME_LOGISTICAL_TRANSPORTER, ExtraTileEntityTypes.COSMIC_LOGISTICAL_TRANSPORTER, ExtraTileEntityTypes.INFINITE_LOGISTICAL_TRANSPORTER);
        // mechanical pipe
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderMechanicalPipe::new, ExtraTileEntityTypes.ABSOLUTE_MECHANICAL_PIPE,
                ExtraTileEntityTypes.SUPREME_MECHANICAL_PIPE, ExtraTileEntityTypes.COSMIC_MECHANICAL_PIPE, ExtraTileEntityTypes.INFINITE_MECHANICAL_PIPE);
        // pressurized tube
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderPressurizedTube::new, ExtraTileEntityTypes.ABSOLUTE_PRESSURIZED_TUBE,
                ExtraTileEntityTypes.SUPREME_PRESSURIZED_TUBE, ExtraTileEntityTypes.COSMIC_PRESSURIZED_TUBE, ExtraTileEntityTypes.INFINITE_PRESSURIZED_TUBE);
        // thermodynamic conductor
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderThermodynamicConductor::new, ExtraTileEntityTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR,
                ExtraTileEntityTypes.SUPREME_THERMODYNAMIC_CONDUCTOR, ExtraTileEntityTypes.COSMIC_THERMODYNAMIC_CONDUCTOR, ExtraTileEntityTypes.INFINITE_THERMODYNAMIC_CONDUCTOR);
        // bin
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderBin::new, ExtraTileEntityTypes.ABSOLUTE_BIN, ExtraTileEntityTypes.SUPREME_BIN, ExtraTileEntityTypes.COSMIC_BIN,
                ExtraTileEntityTypes.INFINITE_BIN);
        // fluid tank
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderFluidTank::new, ExtraTileEntityTypes.ABSOLUTE_FLUID_TANK, ExtraTileEntityTypes.SUPREME_FLUID_TANK,
                ExtraTileEntityTypes.COSMIC_FLUID_TANK, ExtraTileEntityTypes.INFINITE_FLUID_TANK);
        // energy cube
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderEnergyCube::new, ExtraTileEntityTypes.ABSOLUTE_ENERGY_CUBE, ExtraTileEntityTypes.SUPREME_ENERGY_CUBE,
                ExtraTileEntityTypes.COSMIC_ENERGY_CUBE, ExtraTileEntityTypes.INFINITE_ENERGY_CUBE);
    }

    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Post event) {
        TextureAtlas map = event.getAtlas();
        ExtraRenderLogisticalTransporter.onStitch(map);
        ExtraRenderFluidTank.resetCachedModels();
        ExtraRenderMechanicalPipe.onStitch();
    }

    @SubscribeEvent
    public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
        ClientRegistrationUtil.registerClientReloadListeners(event, ExtraRenderEnergyCubeItem.EXTRA_RENDERER);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerContainers(RegisterEvent event) {
        event.register(Registries.MENU, helper -> {
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.FLUID_TANK, ExtraGuiFluidTank::new);
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.ENERGY_CUBE, ExtraGuiEnergyCube::new);
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.CHEMICAL_TANK, ExtraGuiChemicalTank::new);
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.ADVANCED_ELECTRIC_PUMP, GuiAdvancedElectricPump::new);
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX, GuiReinforcedInductionMatrix::new);
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.MATRIX_STATS, GuiReinforcedMatrixStats::new);

            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.FACTORY, GuiAdvancedFactory::new);
        });
    }

    @SubscribeEvent
    public static void registerModelLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register("energy_cube", ExtraEnergyCubeModelLoader.INSTANCE);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ExtraModelEnergyCore.CORE_LAYER, ExtraModelEnergyCore::createLayerDefinition);
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, tintIndex) -> {
            if (tintIndex == 1) {
                FTTier tier = ExtraAttribute.getTier(state.getBlock(), FTTier.class);
                if (tier != null) {
                    float[] color = TierColor.getColor(tier);
                    return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
                }
            }
            return -1;
        }, ExtraBlock.ABSOLUTE_FLUID_TANK, ExtraBlock.SUPREME_FLUID_TANK, ExtraBlock.COSMIC_FLUID_TANK, ExtraBlock.INFINITE_FLUID_TANK);

        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, index) -> {
            if (index == 1) {
                ECTier tier = ExtraAttribute.getTier(state.getBlock(), ECTier.class);
                if (tier != null) {
                    float[] color = TierColor.getColor(tier);
                    return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
                }
            }
            return -1;
        }, ExtraBlock.ABSOLUTE_ENERGY_CUBE, ExtraBlock.SUPREME_ENERGY_CUBE, ExtraBlock.COSMIC_ENERGY_CUBE,
                ExtraBlock.INFINITE_ENERGY_CUBE);

        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, tintIndex) -> {
            if (tintIndex == 1 && pos != null) {
                ExtraTileEntityLogisticalTransporter transporter = WorldUtils.getTileEntity(ExtraTileEntityLogisticalTransporter.class, world, pos);
                if (transporter != null) {
                    EnumColor renderColor = transporter.getTransmitter().getColor();
                    if (renderColor != null) {
                        return MekanismRenderer.getColorARGB(renderColor, 1);
                    }
                }
            }
            return -1;
        }, ExtraBlock.ABSOLUTE_LOGISTICAL_TRANSPORTER, ExtraBlock.SUPREME_LOGISTICAL_TRANSPORTER, ExtraBlock.COSMIC_LOGISTICAL_TRANSPORTER,
                ExtraBlock.INFINITE_LOGISTICAL_TRANSPORTER);
    }

    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        // Bucket Item Color
        ClientRegistrationUtil.registerBucketColorHandler(event, ExtraFluids.EXTRA_FLUIDS);
        // Fluid Tank
        ClientRegistrationUtil.registerItemColorHandler(event, (stack, tintIndex) -> {
            Item item = stack.getItem();
            if (tintIndex == 1 && item instanceof ExtraItemBlockFluidTank tank) {
                float[] color = TierColor.getColor(tank.getAdvanceTier());
                return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
            }
            return -1;
        }, ExtraBlock.ABSOLUTE_FLUID_TANK, ExtraBlock.SUPREME_FLUID_TANK, ExtraBlock.COSMIC_FLUID_TANK, ExtraBlock.INFINITE_FLUID_TANK);

        // Energy Cube
        ClientRegistrationUtil.registerItemColorHandler(event, (stack, tintIndex) -> {
            Item item = stack.getItem();
            if (tintIndex == 1 && item instanceof ExtraItemBlockEnergyCube cube) {
                float[] color = TierColor.getColor(cube.getAdvanceTier());
                return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
            }
            return -1;
        }, ExtraBlock.ABSOLUTE_ENERGY_CUBE, ExtraBlock.SUPREME_ENERGY_CUBE, ExtraBlock.COSMIC_ENERGY_CUBE, ExtraBlock.INFINITE_ENERGY_CUBE);

        for (Table.Cell<ResourceType, PrimaryResource, ItemRegistryObject<Item>> item : MekanismItems.PROCESSED_RESOURCES.cellSet()) {
            int tint = item.getColumnKey().getTint();
            ClientRegistrationUtil.registerItemColorHandler(event, (stack, index) -> index == 1 ? tint : -1, item.getValue());
        }
        for (Map.Entry<IResource, BlockRegistryObject<?, ?>> entry : MekanismBlocks.PROCESSED_RESOURCE_BLOCKS.entrySet()) {
            if (entry.getKey() instanceof PrimaryResource primaryResource) {
                int tint = primaryResource.getTint();
                ClientRegistrationUtil.registerItemColorHandler(event, (stack, index) -> index == 1 ? tint : -1, entry.getValue());
            }
        }
    }

    @SubscribeEvent
    public static void registerItemDecorations(RegisterItemDecorationsEvent event) {
        TransmitterTypeDecorator.registerDecorators(event, ExtraBlock.ABSOLUTE_PRESSURIZED_TUBE, ExtraBlock.SUPREME_PRESSURIZED_TUBE,
                ExtraBlock.COSMIC_PRESSURIZED_TUBE, ExtraBlock.INFINITE_PRESSURIZED_TUBE, ExtraBlock.ABSOLUTE_THERMODYNAMIC_CONDUCTOR,
                ExtraBlock.SUPREME_THERMODYNAMIC_CONDUCTOR, ExtraBlock.COSMIC_THERMODYNAMIC_CONDUCTOR, ExtraBlock.INFINITE_THERMODYNAMIC_CONDUCTOR,
                ExtraBlock.ABSOLUTE_UNIVERSAL_CABLE, ExtraBlock.SUPREME_UNIVERSAL_CABLE, ExtraBlock.COSMIC_UNIVERSAL_CABLE, ExtraBlock.INFINITE_UNIVERSAL_CABLE);
    }
}
