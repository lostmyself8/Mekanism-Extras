package com.jerry.mekanism_extras.common.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.tile.machine.ExtraTileEntityElectricPump;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCasing;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraContainerTypes {
    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismExtras.MODID);
    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityFluidTank>> FLUID_TANK = CONTAINER_TYPES.custom("extra_fluid_tank", ExtraTileEntityFluidTank.class).armorSideBar().build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityEnergyCube>> ENERGY_CUBE = CONTAINER_TYPES.custom("extra_energy_cube", ExtraTileEntityEnergyCube.class).armorSideBar(180, 41, 0).build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityChemicalTank>> CHEMICAL_TANK = CONTAINER_TYPES.custom("extra_chemical_tank", ExtraTileEntityChemicalTank.class).armorSideBar().build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityElectricPump>> ADVANCE_ELECTRIC_PUMP = CONTAINER_TYPES.register("extra_electric_pump", ExtraTileEntityElectricPump.class);
    public static final ContainerTypeRegistryObject<EmptyTileContainer<ExtraTileEntityInductionCasing>> MATRIX_STATS = CONTAINER_TYPES.registerEmpty("extra_matrix_stats", ExtraTileEntityInductionCasing.class);
    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityInductionCasing>> INDUCTION_MATRIX = CONTAINER_TYPES.custom("extra_induction_matrix", ExtraTileEntityInductionCasing.class).armorSideBar(-20, 41, 0).build();

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}
