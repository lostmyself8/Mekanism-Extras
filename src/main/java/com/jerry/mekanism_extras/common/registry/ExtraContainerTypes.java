package com.jerry.mekanism_extras.common.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.inventory.container.tile.AdvancedFactoryContainer;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekanism_extras.common.tile.machine.TileEntityAdvancedElectricPump;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
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
    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityAdvancedElectricPump>> ADVANCED_ELECTRIC_PUMP = CONTAINER_TYPES.register("extra_electric_pump", TileEntityAdvancedElectricPump.class);
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityReinforcedInductionCasing>> MATRIX_STATS = CONTAINER_TYPES.registerEmpty("extra_matrix_stats", TileEntityReinforcedInductionCasing.class);
    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityReinforcedInductionCasing>> REINFORCED_INDUCTION_MATRIX = CONTAINER_TYPES.custom("extra_induction_matrix", TileEntityReinforcedInductionCasing.class).armorSideBar(-20, 41, 0).build();

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityExtraFactory<?>>> FACTORY = CONTAINER_TYPES.register("factory", factoryClass(), AdvancedFactoryContainer::new);

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Class<TileEntityExtraFactory<?>> factoryClass() {
        return (Class) TileEntityExtraFactory.class;
    }

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}
