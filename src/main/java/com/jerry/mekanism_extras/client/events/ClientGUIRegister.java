package com.jerry.mekanism_extras.client.events;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.client.chemicaltank.gui.ExtraGuiChemicalTank;
import com.jerry.mekanism_extras.client.energycube.gui.ExtraGuiEnergyCube;
import com.jerry.mekanism_extras.client.fluidtank.gui.ExtraGuiFluidTank;
import com.jerry.mekanism_extras.registery.ExtraContainerTypes;
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
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.FLUID_TANK, ExtraGuiFluidTank::new);
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.ENERGY_CUBE, ExtraGuiEnergyCube::new);
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.CHEMICAL_TANK, ExtraGuiChemicalTank::new);
        });
    }
}
