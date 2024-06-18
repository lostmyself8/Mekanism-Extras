package com.jerry.mekanism_extras.client.events;

import com.google.common.collect.Table;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.client.bin.ExtraRenderBin;
import com.jerry.mekanism_extras.client.energycube.ExtraEnergyCubeModelLoader;
import com.jerry.mekanism_extras.client.energycube.ExtraModelEnergyCore;
import com.jerry.mekanism_extras.client.energycube.ExtraRenderEnergyCube;
import com.jerry.mekanism_extras.client.energycube.ExtraRenderEnergyCubeItem;
import com.jerry.mekanism_extras.client.fluidtank.ExtraRenderFluidTank;
import com.jerry.mekanism_extras.client.item.*;
import com.jerry.mekanism_extras.common.block.storage.energycube.ECTier;
import com.jerry.mekanism_extras.common.block.storage.energycube.ExtraItemBlockEnergyCube;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.ExtraItemBlockFluidTank;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.FTTier;
import com.jerry.mekanism_extras.registry.ExtraBlock;
import com.jerry.mekanism_extras.registry.ExtraTileEntityTypes;
import mekanism.api.tier.BaseTier;
import mekanism.client.ClientRegistrationUtil;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.item.TransmitterTypeDecorator;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registries.*;
import mekanism.common.resource.IResource;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.tier.EnergyCubeTier;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = MekanismExtras.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRender {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //universal cable
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderUniversalCable::new, ExtraTileEntityTypes.ABSOLUTE_UNIVERSAL_CABLE,
                ExtraTileEntityTypes.SUPREME_UNIVERSAL_CABLE, ExtraTileEntityTypes.COSMIC_UNIVERSAL_CABLE, ExtraTileEntityTypes.INFINITE_UNIVERSAL_CABLE);
        //logistical transporter
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderLogisticalTransporter::new, ExtraTileEntityTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER,
                ExtraTileEntityTypes.SUPREME_LOGISTICAL_TRANSPORTER, ExtraTileEntityTypes.COSMIC_LOGISTICAL_TRANSPORTER, ExtraTileEntityTypes.INFINITE_LOGISTICAL_TRANSPORTER);
        //mechanical pipe
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderMechanicalPipe::new, ExtraTileEntityTypes.ABSOLUTE_MECHANICAL_PIPE,
                ExtraTileEntityTypes.SUPREME_MECHANICAL_PIPE, ExtraTileEntityTypes.COSMIC_MECHANICAL_PIPE, ExtraTileEntityTypes.INFINITE_MECHANICAL_PIPE);
        //pressurized tube
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderPressurizedTube::new, ExtraTileEntityTypes.ABSOLUTE_PRESSURIZED_TUBE,
                ExtraTileEntityTypes.SUPREME_PRESSURIZED_TUBE, ExtraTileEntityTypes.COSMIC_PRESSURIZED_TUBE, ExtraTileEntityTypes.INFINITE_PRESSURIZED_TUBE);
        //thermodynamic conductor
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderThermodynamicConductor::new, ExtraTileEntityTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR,
                ExtraTileEntityTypes.SUPREME_THERMODYNAMIC_CONDUCTOR, ExtraTileEntityTypes.COSMIC_THERMODYNAMIC_CONDUCTOR, ExtraTileEntityTypes.INFINITE_THERMODYNAMIC_CONDUCTOR);
        //bin
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderBin::new, ExtraTileEntityTypes.ABSOLUTE_BIN, ExtraTileEntityTypes.SUPREME_BIN, ExtraTileEntityTypes.COSMIC_BIN,
                ExtraTileEntityTypes.INFINITE_BIN);
        //fluid tank
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderFluidTank::new, ExtraTileEntityTypes.ABSOLUTE_FLUID_TANK, ExtraTileEntityTypes.SUPREME_FLUID_TANK,
                ExtraTileEntityTypes.COSMIC_FLUID_TANK, ExtraTileEntityTypes.INFINITE_FLUID_TANK);
        //energy cube
        ClientRegistrationUtil.bindTileEntityRenderer(event, ExtraRenderEnergyCube::new, ExtraTileEntityTypes.ABSOLUTE_ENERGY_CUBE, ExtraTileEntityTypes.SUPREME_ENERGY_CUBE,
                ExtraTileEntityTypes.COSMIC_ENERGY_CUBE, ExtraTileEntityTypes.INFINITE_ENERGY_CUBE);
    }

    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Post event) {
        TextureAtlas map = event.getAtlas();
        ExtraRenderLogisticalTransporter.onStitch(map);
    }

    @SubscribeEvent
    public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
        ClientRegistrationUtil.registerClientReloadListeners(event, ExtraRenderEnergyCubeItem.RENDERER);
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
                BaseTier tier = Attribute.getBaseTier(state.getBlock());
                if (tier != null) {
                    float[] color = FTTier.getColor(tier);
                    return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
                }
            }
            return -1;
        }, ExtraBlock.ABSOLUTE_FLUID_TANK, ExtraBlock.SUPREME_FLUID_TANK, ExtraBlock.COSMIC_FLUID_TANK, ExtraBlock.INFINITE_FLUID_TANK);

        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, index) -> {
                    if (index == 1) {
                        EnergyCubeTier tier = Attribute.getTier(state.getBlock(), EnergyCubeTier.class);
                        if (tier != null) {
                            float[] color = ECTier.getColor(tier);
                            return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
                        }
                    }
                    return -1;
                }, ExtraBlock.ABSOLUTE_ENERGY_CUBE, ExtraBlock.SUPREME_ENERGY_CUBE, ExtraBlock.COSMIC_ENERGY_CUBE,
                ExtraBlock.INFINITE_ENERGY_CUBE);
    }

    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        //fluid tank
        ClientRegistrationUtil.registerItemColorHandler(event, (stack, tintIndex) -> {
            Item item = stack.getItem();
            if (tintIndex == 1 && item instanceof ExtraItemBlockFluidTank tank) {
                float[] color = FTTier.getColor(tank.getTier().getBaseTier());
                return MekanismRenderer.getColorARGB(color[0], color[1], color[2], 1);
            }
            return -1;
        }, ExtraBlock.ABSOLUTE_FLUID_TANK, ExtraBlock.SUPREME_FLUID_TANK, ExtraBlock.COSMIC_FLUID_TANK, ExtraBlock.INFINITE_FLUID_TANK);
        ClientRegistrationUtil.registerBucketColorHandler(event, MekanismFluids.FLUIDS);

        //energy cube
        ClientRegistrationUtil.registerItemColorHandler(event, (stack, tintIndex) -> {
            Item item = stack.getItem();
            if (tintIndex == 1 && item instanceof ExtraItemBlockEnergyCube cube) {
                float[] color = ECTier.getColor(cube.getTier());
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
