package com.jerry.generator_extras.client.event;

import com.jerry.generator_extras.client.gui.GuiNaquadahReactorController;
import com.jerry.generator_extras.client.gui.GuiNaquadahReactorFuel;
import com.jerry.generator_extras.client.gui.GuiNaquadahReactorHeat;
import com.jerry.generator_extras.client.gui.GuiNaquadahReactorStats;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.integration.Addons;
import com.jerry.mekanism_extras.integration.mekgenerators.genregistry.ExtraGenContainerTypes;
import mekanism.client.ClientRegistrationUtil;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = MekanismExtras.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientGUIRegister {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerContainers(RegisterEvent event) {
        event.register(Registries.MENU, helper -> {
            if (Addons.MEKANISMGENERATORS.isLoaded()){
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_CONTROLLER, GuiNaquadahReactorController::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_FUEL, GuiNaquadahReactorFuel::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_HEAT, GuiNaquadahReactorHeat::new);
//                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER, GuiNaquadahReactorLogicAdapter::new);
                ClientRegistrationUtil.registerScreen(ExtraGenContainerTypes.NAQUADAH_REACTOR_STATS, GuiNaquadahReactorStats::new);
            }
        });
    }
}
