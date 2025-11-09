package com.jerry.generator_extras.client.event;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.integration.Addons;

import com.jerry.generator_extras.client.gui.naquadah.*;
import com.jerry.generator_extras.client.gui.plasma.GuiPlasmaEvaporationController;
import com.jerry.generator_extras.client.render.RenderNaquadahReactor;
import com.jerry.generator_extras.client.render.RenderPlasmaEvaporationPlant;
import com.jerry.generator_extras.common.genregistry.ExtraGenContainerTypes;
import com.jerry.generator_extras.common.genregistry.ExtraGenTileEntityTypes;

import mekanism.client.ClientRegistrationUtil;

import net.minecraft.core.registries.Registries;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegisterEvent;

@EventBusSubscriber(modid = MekanismExtras.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientGUIRegister {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            event.registerBlockEntityRenderer(ExtraGenTileEntityTypes.NAQUADAH_REACTOR_CONTROLLER.get(), RenderNaquadahReactor::new);
            event.registerBlockEntityRenderer(ExtraGenTileEntityTypes.PLASMA_EVAPORATION_CONTROLLER.get(), RenderPlasmaEvaporationPlant::new);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerContainers(RegisterEvent event) {
        event.register(Registries.MENU, helper -> {
            if (Addons.MEKANISMGENERATORS.isLoaded()) {
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_CONTROLLER, GuiNaquadahReactorController::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_FUEL, GuiNaquadahReactorFuel::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_HEAT, GuiNaquadahReactorHeat::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER, GuiNaquadahReactorLogicAdapter::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_STATS, GuiNaquadahReactorStats::new);

                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.PLASMA_EVAPORATION_CONTROLLER, GuiPlasmaEvaporationController::new);
            }
        });
    }
}
