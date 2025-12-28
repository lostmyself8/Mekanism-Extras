package com.jerry.mekextras.common.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.inventory.container.tile.ExtraFactoryContainer;
import com.jerry.mekextras.common.tile.TileEntityExtraChemicalTank;
import com.jerry.mekextras.common.tile.TileEntityExtraEnergyCube;
import com.jerry.mekextras.common.tile.TileEntityExtraFluidTank;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekextras.common.tile.machine.TileEntityAdvancedElectricPump;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.neoforged.bus.api.IEventBus;

public class ExtraContainerTypes {
    private ExtraContainerTypes() {

    }
    public static final ContainerTypeDeferredRegister EXTRA_CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismExtras.MOD_ID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityAdvancedElectricPump>> ADVANCE_ELECTRIC_PUMP = EXTRA_CONTAINER_TYPES.register(ExtraBlocks.ADVANCED_ELECTRIC_PUMP, TileEntityAdvancedElectricPump.class);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityExtraEnergyCube>> EXTRA_ENERGY_CUBE = EXTRA_CONTAINER_TYPES.custom("extra_energy_cube", TileEntityExtraEnergyCube.class).armorSideBar(180, 41, 0).build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityExtraFluidTank>> EXTRA_FLUID_TANK = EXTRA_CONTAINER_TYPES.custom("extra_fluid_tank", TileEntityExtraFluidTank.class).armorSideBar().build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityExtraChemicalTank>> EXTRA_CHEMICAL_TANK = EXTRA_CONTAINER_TYPES.custom("extra_chemical_tank", TileEntityExtraChemicalTank.class).armorSideBar().build();
    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityReinforcedInductionCasing>> REINFORCED_INDUCTION_MATRIX = EXTRA_CONTAINER_TYPES.custom("reinforced_induction_matrix", TileEntityReinforcedInductionCasing.class).armorSideBar(-20, 41, 0).build();
    public static final ContainerTypeRegistryObject<EmptyTileContainer<TileEntityReinforcedInductionCasing>> REINFORCED_MATRIX_STATS = EXTRA_CONTAINER_TYPES.registerEmpty("reinforced_matrix_stats", TileEntityReinforcedInductionCasing.class);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityExtraFactory<?>>> FACTORY = EXTRA_CONTAINER_TYPES.register("factory", factoryClass(), ExtraFactoryContainer::new);

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Class<TileEntityExtraFactory<?>> factoryClass() {
        return (Class) TileEntityExtraFactory.class;
    }

    public static void register(IEventBus eventBus) {
        EXTRA_CONTAINER_TYPES.register(eventBus);
    }
}
