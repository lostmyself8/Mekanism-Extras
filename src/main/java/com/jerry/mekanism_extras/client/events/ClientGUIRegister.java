package com.jerry.mekanism_extras.client.events;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.client.gui.ElectricPump.ExtraGuiElectricPump;
import com.jerry.mekanism_extras.client.gui.chemicaltank.ExtraGuiChemicalTank;
import com.jerry.mekanism_extras.client.gui.energycube.ExtraGuiEnergyCube;
import com.jerry.mekanism_extras.client.gui.fluidtank.ExtraGuiFluidTank;
import com.jerry.mekanism_extras.client.gui.GuiReinforcedInductionMatrix;
import com.jerry.mekanism_extras.client.gui.GuiReinforcedMatrixStats;
import com.jerry.mekanism_extras.client.gui.machine.GuiAdvancedFactory;
import com.jerry.mekanism_extras.common.registry.ExtraContainerTypes;
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
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.ADVANCED_ELECTRIC_PUMP, ExtraGuiElectricPump::new);
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX, GuiReinforcedInductionMatrix::new);
            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.MATRIX_STATS, GuiReinforcedMatrixStats::new);

            ClientRegistrationUtil.registerScreen(ExtraContainerTypes.FACTORY, GuiAdvancedFactory::new);
        });
    }
}
