package com.jerry.mekanism_extras.common.registries;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityBin;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.common.tile.factory.*;
import com.jerry.mekanism_extras.common.tile.machine.TileEntityAdvancedElectricPump;
import com.jerry.mekanism_extras.common.tile.multiblock.*;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityLogisticalTransporterBase;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityMechanicalPipe;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityPressurizedTube;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityThermodynamicConductor;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityTransmitter;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityUniversalCable;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.transmitter.*;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ExtraTileEntityTypes {

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MOD_ID);

    private static final Table<ExtraFactoryTier, FactoryType, TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            FACTORIES.put(tier, FactoryType.COMBINING, TILE_ENTITY_TYPES.register(ExtraBlocks.getAdvancedFactory(tier, FactoryType.COMBINING), (pos, state) -> new TileEntityExtraCombiningFactory(ExtraBlocks.getAdvancedFactory(tier, FactoryType.COMBINING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.COMPRESSING, TILE_ENTITY_TYPES.register(ExtraBlocks.getAdvancedFactory(tier, FactoryType.COMPRESSING), (pos, state) -> new TileEntityExtraItemStackGasToItemStackFactory(ExtraBlocks.getAdvancedFactory(tier, FactoryType.COMPRESSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.CRUSHING, TILE_ENTITY_TYPES.register(ExtraBlocks.getAdvancedFactory(tier, FactoryType.CRUSHING), (pos, state) -> new TileEntityExtraItemStackToItemStackFactory(ExtraBlocks.getAdvancedFactory(tier, FactoryType.CRUSHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.ENRICHING, TILE_ENTITY_TYPES.register(ExtraBlocks.getAdvancedFactory(tier, FactoryType.ENRICHING), (pos, state) -> new TileEntityExtraItemStackToItemStackFactory(ExtraBlocks.getAdvancedFactory(tier, FactoryType.ENRICHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.INFUSING, TILE_ENTITY_TYPES.register(ExtraBlocks.getAdvancedFactory(tier, FactoryType.INFUSING), (pos, state) -> new TileEntityExtraMetallurgicInfuserFactory(ExtraBlocks.getAdvancedFactory(tier, FactoryType.INFUSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.INJECTING, TILE_ENTITY_TYPES.register(ExtraBlocks.getAdvancedFactory(tier, FactoryType.INJECTING), (pos, state) -> new TileEntityExtraItemStackGasToItemStackFactory(ExtraBlocks.getAdvancedFactory(tier, FactoryType.INJECTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.PURIFYING, TILE_ENTITY_TYPES.register(ExtraBlocks.getAdvancedFactory(tier, FactoryType.PURIFYING), (pos, state) -> new TileEntityExtraItemStackGasToItemStackFactory(ExtraBlocks.getAdvancedFactory(tier, FactoryType.PURIFYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.SAWING, TILE_ENTITY_TYPES.register(ExtraBlocks.getAdvancedFactory(tier, FactoryType.SAWING), (pos, state) -> new TileEntityExtraSawingFactory(ExtraBlocks.getAdvancedFactory(tier, FactoryType.SAWING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.SMELTING, TILE_ENTITY_TYPES.register(ExtraBlocks.getAdvancedFactory(tier, FactoryType.SMELTING), (pos, state) -> new TileEntityExtraItemStackToItemStackFactory(ExtraBlocks.getAdvancedFactory(tier, FactoryType.SMELTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
    }

    private static <BE extends TileEntityTransmitter> TileEntityTypeRegistryObject<BE> registerTransmitter(BlockRegistryObject<?, ?> block, BlockEntityType.BlockEntitySupplier<? extends BE> factory) {
        // Note: There is no data fixer type as forge does not currently have a way exposing data fixers to mods yet
        return TILE_ENTITY_TYPES.<BE>builder(block, factory).serverTicker(TileEntityTransmitter::tickServer).build();
    }

    public static final TileEntityTypeRegistryObject<ExtraTileEntityRadioactiveWasteBarrel> EXPAND_RADIOACTIVE_WASTE_BARREL = TILE_ENTITY_TYPES.register(ExtraBlocks.EXPAND_RADIOACTIVE_WASTE_BARREL, ExtraTileEntityRadioactiveWasteBarrel::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityReinforcedInductionCasing> REINFORCED_INDUCTION_CASING = TILE_ENTITY_TYPES.register(ExtraBlocks.REINFORCED_INDUCTION_CASING, TileEntityReinforcedInductionCasing::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityReinforcedInductionPort> REINFORCED_INDUCTION_PORT = TILE_ENTITY_TYPES.register(ExtraBlocks.REINFORCED_INDUCTION_PORT, TileEntityReinforcedInductionPort::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // bin
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> ABSOLUTE_BIN = TILE_ENTITY_TYPES.register(ExtraBlocks.ABSOLUTE_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlocks.ABSOLUTE_BIN, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> SUPREME_BIN = TILE_ENTITY_TYPES.register(ExtraBlocks.SUPREME_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlocks.SUPREME_BIN, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> COSMIC_BIN = TILE_ENTITY_TYPES.register(ExtraBlocks.COSMIC_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlocks.COSMIC_BIN, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> INFINITE_BIN = TILE_ENTITY_TYPES.register(ExtraBlocks.INFINITE_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlocks.INFINITE_BIN, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // Induction Cells
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> ABSOLUTE_INDUCTION_CELL = TILE_ENTITY_TYPES.register(ExtraBlocks.ABSOLUTE_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlocks.ABSOLUTE_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> SUPREME_INDUCTION_CELL = TILE_ENTITY_TYPES.register(ExtraBlocks.SUPREME_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlocks.SUPREME_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> COSMIC_INDUCTION_CELL = TILE_ENTITY_TYPES.register(ExtraBlocks.COSMIC_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlocks.COSMIC_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> INFINITE_INDUCTION_CELL = TILE_ENTITY_TYPES.register(ExtraBlocks.INFINITE_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlocks.INFINITE_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // Induction Providers
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> ABSOLUTE_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(ExtraBlocks.ABSOLUTE_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlocks.ABSOLUTE_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> SUPREME_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(ExtraBlocks.SUPREME_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlocks.SUPREME_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> COSMIC_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(ExtraBlocks.COSMIC_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlocks.COSMIC_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> INFINITE_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(ExtraBlocks.INFINITE_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlocks.INFINITE_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // fluid tank
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> ABSOLUTE_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlocks.ABSOLUTE_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlocks.ABSOLUTE_FLUID_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> SUPREME_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlocks.SUPREME_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlocks.SUPREME_FLUID_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> COSMIC_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlocks.COSMIC_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlocks.COSMIC_FLUID_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> INFINITE_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlocks.INFINITE_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlocks.INFINITE_FLUID_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // energy cube
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> ABSOLUTE_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlocks.ABSOLUTE_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlocks.ABSOLUTE_ENERGY_CUBE, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> SUPREME_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlocks.SUPREME_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlocks.SUPREME_ENERGY_CUBE, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> COSMIC_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlocks.COSMIC_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlocks.COSMIC_ENERGY_CUBE, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> INFINITE_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlocks.INFINITE_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlocks.INFINITE_ENERGY_CUBE, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // universal cables
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = registerTransmitter(ExtraBlocks.ABSOLUTE_UNIVERSAL_CABLE, (pos, state) -> new ExtraTileEntityUniversalCable(ExtraBlocks.ABSOLUTE_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> SUPREME_UNIVERSAL_CABLE = registerTransmitter(ExtraBlocks.SUPREME_UNIVERSAL_CABLE, (pos, state) -> new ExtraTileEntityUniversalCable(ExtraBlocks.SUPREME_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> COSMIC_UNIVERSAL_CABLE = registerTransmitter(ExtraBlocks.COSMIC_UNIVERSAL_CABLE, (pos, state) -> new ExtraTileEntityUniversalCable(ExtraBlocks.COSMIC_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> INFINITE_UNIVERSAL_CABLE = registerTransmitter(ExtraBlocks.INFINITE_UNIVERSAL_CABLE, (pos, state) -> new ExtraTileEntityUniversalCable(ExtraBlocks.INFINITE_UNIVERSAL_CABLE, pos, state));
    // mechanical pipes
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = registerTransmitter(ExtraBlocks.ABSOLUTE_MECHANICAL_PIPE, (pos, state) -> new ExtraTileEntityMechanicalPipe(ExtraBlocks.ABSOLUTE_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> SUPREME_MECHANICAL_PIPE = registerTransmitter(ExtraBlocks.SUPREME_MECHANICAL_PIPE, (pos, state) -> new ExtraTileEntityMechanicalPipe(ExtraBlocks.SUPREME_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> COSMIC_MECHANICAL_PIPE = registerTransmitter(ExtraBlocks.COSMIC_MECHANICAL_PIPE, (pos, state) -> new ExtraTileEntityMechanicalPipe(ExtraBlocks.COSMIC_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> INFINITE_MECHANICAL_PIPE = registerTransmitter(ExtraBlocks.INFINITE_MECHANICAL_PIPE, (pos, state) -> new ExtraTileEntityMechanicalPipe(ExtraBlocks.INFINITE_MECHANICAL_PIPE, pos, state));
    // pressurized tubes
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = registerTransmitter(ExtraBlocks.ABSOLUTE_PRESSURIZED_TUBE, (pos, state) -> new ExtraTileEntityPressurizedTube(ExtraBlocks.ABSOLUTE_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> SUPREME_PRESSURIZED_TUBE = registerTransmitter(ExtraBlocks.SUPREME_PRESSURIZED_TUBE, (pos, state) -> new ExtraTileEntityPressurizedTube(ExtraBlocks.SUPREME_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> COSMIC_PRESSURIZED_TUBE = registerTransmitter(ExtraBlocks.COSMIC_PRESSURIZED_TUBE, (pos, state) -> new ExtraTileEntityPressurizedTube(ExtraBlocks.COSMIC_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> INFINITE_PRESSURIZED_TUBE = registerTransmitter(ExtraBlocks.INFINITE_PRESSURIZED_TUBE, (pos, state) -> new ExtraTileEntityPressurizedTube(ExtraBlocks.INFINITE_PRESSURIZED_TUBE, pos, state));
    // logistic transporters
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(ExtraBlocks.ABSOLUTE_LOGISTICAL_TRANSPORTER, (pos, state) -> new ExtraTileEntityLogisticalTransporter(ExtraBlocks.ABSOLUTE_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(ExtraTileEntityLogisticalTransporterBase::tickClient).serverTicker(ExtraTileEntityTransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(ExtraBlocks.SUPREME_LOGISTICAL_TRANSPORTER, (pos, state) -> new ExtraTileEntityLogisticalTransporter(ExtraBlocks.SUPREME_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(ExtraTileEntityLogisticalTransporterBase::tickClient).serverTicker(ExtraTileEntityTransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(ExtraBlocks.COSMIC_LOGISTICAL_TRANSPORTER, (pos, state) -> new ExtraTileEntityLogisticalTransporter(ExtraBlocks.COSMIC_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(ExtraTileEntityLogisticalTransporterBase::tickClient).serverTicker(ExtraTileEntityTransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(ExtraBlocks.INFINITE_LOGISTICAL_TRANSPORTER, (pos, state) -> new ExtraTileEntityLogisticalTransporter(ExtraBlocks.INFINITE_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(ExtraTileEntityLogisticalTransporterBase::tickClient).serverTicker(ExtraTileEntityTransmitter::extraTickServer).build();
    // thermodynamic conductors
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = registerTransmitter(ExtraBlocks.ABSOLUTE_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new ExtraTileEntityThermodynamicConductor(ExtraBlocks.ABSOLUTE_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = registerTransmitter(ExtraBlocks.SUPREME_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new ExtraTileEntityThermodynamicConductor(ExtraBlocks.SUPREME_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = registerTransmitter(ExtraBlocks.COSMIC_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new ExtraTileEntityThermodynamicConductor(ExtraBlocks.COSMIC_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = registerTransmitter(ExtraBlocks.INFINITE_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new ExtraTileEntityThermodynamicConductor(ExtraBlocks.INFINITE_THERMODYNAMIC_CONDUCTOR, pos, state));
    // chemical tank
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> ABSOLUTE_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlocks.ABSOLUTE_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlocks.ABSOLUTE_CHEMICAL_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> SUPREME_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlocks.SUPREME_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlocks.SUPREME_CHEMICAL_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> COSMIC_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlocks.COSMIC_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlocks.COSMIC_CHEMICAL_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> INFINITE_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlocks.INFINITE_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlocks.INFINITE_CHEMICAL_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);

    public static final TileEntityTypeRegistryObject<TileEntityAdvancedElectricPump> ADVANCED_ELECTRIC_PUMP = TILE_ENTITY_TYPES.register(ExtraBlocks.ADVANCED_ELECTRIC_PUMP, TileEntityAdvancedElectricPump::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);

    public static TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>> getExtraFactoryTile(ExtraFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>>[] getExtraFactoryTiles() {
        return FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
