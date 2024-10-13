package com.jerry.generator_extras.client.event;

import com.jerry.generator_extras.client.gui.*;
import com.jerry.generator_extras.client.render.RenderNaquadahReactor;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.integration.Addons;
import com.jerry.generator_extras.common.genregistry.ExtraGenContainerTypes;
import com.jerry.generator_extras.common.genregistry.ExtraGenTileEntityTypes;
import mekanism.client.ClientRegistrationUtil;
import net.minecraft.core.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = MekanismExtras.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientGUIRegister {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            event.registerBlockEntityRenderer(ExtraGenTileEntityTypes.NAQUADAH_REACTOR_CONTROLLER.get(), RenderNaquadahReactor::new);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerContainers(RegisterEvent event) {
        event.register(Registry.MENU_REGISTRY, helper -> {
            if (Addons.MEKANISMGENERATORS.isLoaded()){
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_CONTROLLER, GuiNaquadahReactorController::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_FUEL, GuiNaquadahReactorFuel::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_HEAT, GuiNaquadahReactorHeat::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER, GuiNaquadahReactorLogicAdapter::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_STATS, GuiNaquadahReactorStats::new);
            }
        });
    }
}
