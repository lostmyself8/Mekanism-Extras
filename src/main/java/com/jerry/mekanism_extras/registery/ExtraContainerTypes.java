package com.jerry.mekanism_extras.registery;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.block.storage.chemicaltank.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.block.storage.energycube.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.ExtraTileEntityFluidTank;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraContainerTypes {
    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismExtras.MODID);
    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityFluidTank>> FLUID_TANK = CONTAINER_TYPES.custom("extra_fluid_tank", ExtraTileEntityFluidTank.class).armorSideBar().build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityEnergyCube>> ENERGY_CUBE = CONTAINER_TYPES.custom("extra_energy_cube", ExtraTileEntityEnergyCube.class).armorSideBar(180, 41, 0).build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityChemicalTank>> CHEMICAL_TANK = CONTAINER_TYPES.custom("extra_chemical_tank", ExtraTileEntityChemicalTank.class).armorSideBar().build();

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}
