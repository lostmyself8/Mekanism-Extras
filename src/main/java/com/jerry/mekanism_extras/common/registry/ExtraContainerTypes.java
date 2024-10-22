package com.jerry.mekanism_extras.common.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.tile.machine.TileEntityAdvanceElectricPump;
import com.jerry.mekanism_extras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.neoforged.bus.api.IEventBus;

public class ExtraContainerTypes {
    private ExtraContainerTypes() {

    }
    public static final ContainerTypeDeferredRegister EXTRA_CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismExtras.MOD_ID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityAdvanceElectricPump>> ADVANCE_ELECTRIC_PUMP = EXTRA_CONTAINER_TYPES.register(ExtraBlocks.ADVANCE_ELECTRIC_PUMP, TileEntityAdvanceElectricPump.class);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityEnergyCube>> EXTRA_ENERGY_CUBE = EXTRA_CONTAINER_TYPES.custom("extra_energy_cube", ExtraTileEntityEnergyCube.class).armorSideBar(180, 41, 0).build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityFluidTank>> EXTRA_FLUID_TANK = EXTRA_CONTAINER_TYPES.custom("extra_fluid_tank", ExtraTileEntityFluidTank.class).armorSideBar().build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<ExtraTileEntityChemicalTank>> EXTRA_CHEMICAL_TANK = EXTRA_CONTAINER_TYPES.custom("extra_chemical_tank", ExtraTileEntityChemicalTank.class).armorSideBar().build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityReinforcedInductionCasing>> REINFORCED_INDUCTION_MATRIX = EXTRA_CONTAINER_TYPES.custom("reinforced_induction_matrix", TileEntityReinforcedInductionCasing.class).armorSideBar(-20, 41, 0).build();
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityReinforcedInductionCasing>> REINFORCED_MATRIX_STATS = EXTRA_CONTAINER_TYPES.registerEmpty("reinforced_matrix_stats", TileEntityReinforcedInductionCasing.class);

    public static void register(IEventBus eventBus) {
        EXTRA_CONTAINER_TYPES.register(eventBus);
    }
}
