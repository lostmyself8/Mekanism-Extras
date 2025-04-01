package com.jerry.mekanism_extras.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import com.jerry.mekanism_extras.common.tile.factory.*;
import com.jerry.mekanism_extras.common.tile.machine.TileEntityAdvancedElectricPump;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityBin;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityUniversalCable;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityLogisticalTransporterBase;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityTransmitter;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityMechanicalPipe;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityThermodynamicConductor;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityPressurizedTube;
import com.jerry.mekanism_extras.common.tile.multiblock.*;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.transmitter.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraTileEntityTypes {
    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MODID);

    private static final Table<AdvancedFactoryTier, FactoryType, TileEntityTypeRegistryObject<? extends TileEntityAdvancedFactory<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (AdvancedFactoryTier tier : ExtraEnumUtils.ADVANCED_FACTORY_TIERS) {
            FACTORIES.put(tier, FactoryType.COMBINING, TILE_ENTITY_TYPES.register(ExtraBlock.getAdvancedFactory(tier, FactoryType.COMBINING), (pos, state) -> new TileEntityCombiningAdvancedFactory(ExtraBlock.getAdvancedFactory(tier, FactoryType.COMBINING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.COMPRESSING, TILE_ENTITY_TYPES.register(ExtraBlock.getAdvancedFactory(tier, FactoryType.COMPRESSING), (pos, state) -> new TileEntityItemStackGasToItemStackAdvancedFactory(ExtraBlock.getAdvancedFactory(tier, FactoryType.COMPRESSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.CRUSHING, TILE_ENTITY_TYPES.register(ExtraBlock.getAdvancedFactory(tier, FactoryType.CRUSHING), (pos, state) -> new TileEntityItemStackToItemStackAdvancedFactory(ExtraBlock.getAdvancedFactory(tier, FactoryType.CRUSHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.ENRICHING, TILE_ENTITY_TYPES.register(ExtraBlock.getAdvancedFactory(tier, FactoryType.ENRICHING), (pos, state) -> new TileEntityItemStackToItemStackAdvancedFactory(ExtraBlock.getAdvancedFactory(tier, FactoryType.ENRICHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.INFUSING, TILE_ENTITY_TYPES.register(ExtraBlock.getAdvancedFactory(tier, FactoryType.INFUSING), (pos, state) -> new TileEntityMetallurgicInfuserAdvancedFactory(ExtraBlock.getAdvancedFactory(tier, FactoryType.INFUSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.INJECTING, TILE_ENTITY_TYPES.register(ExtraBlock.getAdvancedFactory(tier, FactoryType.INJECTING), (pos, state) -> new TileEntityItemStackGasToItemStackAdvancedFactory(ExtraBlock.getAdvancedFactory(tier, FactoryType.INJECTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.PURIFYING, TILE_ENTITY_TYPES.register(ExtraBlock.getAdvancedFactory(tier, FactoryType.PURIFYING), (pos, state) -> new TileEntityItemStackGasToItemStackAdvancedFactory(ExtraBlock.getAdvancedFactory(tier, FactoryType.PURIFYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.SAWING, TILE_ENTITY_TYPES.register(ExtraBlock.getAdvancedFactory(tier, FactoryType.SAWING), (pos, state) -> new TileEntitySawingAdvancedFactory(ExtraBlock.getAdvancedFactory(tier, FactoryType.SAWING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.SMELTING, TILE_ENTITY_TYPES.register(ExtraBlock.getAdvancedFactory(tier, FactoryType.SMELTING), (pos, state) -> new TileEntityItemStackToItemStackAdvancedFactory(ExtraBlock.getAdvancedFactory(tier, FactoryType.SMELTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
    }

    private static <BE extends TileEntityTransmitter> TileEntityTypeRegistryObject<BE> registerTransmitter(BlockRegistryObject<?, ?> block, BlockEntityType.BlockEntitySupplier<? extends BE> factory) {
        //Note: There is no data fixer type as forge does not currently have a way exposing data fixers to mods yet
        return TILE_ENTITY_TYPES.<BE>builder(block, factory).serverTicker(TileEntityTransmitter::tickServer).build();
    }

    public static final TileEntityTypeRegistryObject<ExtraTileEntityRadioactiveWasteBarrel> EXPAND_RADIOACTIVE_WASTE_BARREL = TILE_ENTITY_TYPES.register(ExtraBlock.EXPAND_RADIOACTIVE_WASTE_BARREL, ExtraTileEntityRadioactiveWasteBarrel::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityReinforcedInductionCasing> REINFORCED_INDUCTION_CASING = TILE_ENTITY_TYPES.register(ExtraBlock.REINFORCED_INDUCTION_CASING, TileEntityReinforcedInductionCasing::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityReinforcedInductionPort> REINFORCED_INDUCTION_PORT = TILE_ENTITY_TYPES.register(ExtraBlock.REINFORCED_INDUCTION_PORT, TileEntityReinforcedInductionPort::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    //bin
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> ABSOLUTE_BIN = TILE_ENTITY_TYPES.register(ExtraBlock.ABSOLUTE_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlock.ABSOLUTE_BIN, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> SUPREME_BIN = TILE_ENTITY_TYPES.register(ExtraBlock.SUPREME_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlock.SUPREME_BIN, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> COSMIC_BIN = TILE_ENTITY_TYPES.register(ExtraBlock.COSMIC_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlock.COSMIC_BIN, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> INFINITE_BIN = TILE_ENTITY_TYPES.register(ExtraBlock.INFINITE_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlock.INFINITE_BIN, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    //Induction Cells
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> ABSOLUTE_INDUCTION_CELL = TILE_ENTITY_TYPES.register(ExtraBlock.ABSOLUTE_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlock.ABSOLUTE_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> SUPREME_INDUCTION_CELL = TILE_ENTITY_TYPES.register(ExtraBlock.SUPREME_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlock.SUPREME_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> COSMIC_INDUCTION_CELL = TILE_ENTITY_TYPES.register(ExtraBlock.COSMIC_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlock.COSMIC_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> INFINITE_INDUCTION_CELL = TILE_ENTITY_TYPES.register(ExtraBlock.INFINITE_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlock.INFINITE_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    //Induction Providers
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> ABSOLUTE_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(ExtraBlock.ABSOLUTE_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlock.ABSOLUTE_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> SUPREME_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(ExtraBlock.SUPREME_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlock.SUPREME_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> COSMIC_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(ExtraBlock.COSMIC_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlock.COSMIC_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> INFINITE_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(ExtraBlock.INFINITE_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlock.INFINITE_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    //fluid tank
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> ABSOLUTE_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.ABSOLUTE_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlock.ABSOLUTE_FLUID_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> SUPREME_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.SUPREME_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlock.SUPREME_FLUID_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> COSMIC_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.COSMIC_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlock.COSMIC_FLUID_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> INFINITE_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.INFINITE_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlock.INFINITE_FLUID_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    //energy cube
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> ABSOLUTE_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlock.ABSOLUTE_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlock.ABSOLUTE_ENERGY_CUBE, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> SUPREME_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlock.SUPREME_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlock.SUPREME_ENERGY_CUBE, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> COSMIC_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlock.COSMIC_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlock.COSMIC_ENERGY_CUBE, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> INFINITE_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlock.INFINITE_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlock.INFINITE_ENERGY_CUBE, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    //universal cables
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = registerTransmitter(ExtraBlock.ABSOLUTE_UNIVERSAL_CABLE, (pos, state) -> new ExtraTileEntityUniversalCable(ExtraBlock.ABSOLUTE_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> SUPREME_UNIVERSAL_CABLE = registerTransmitter(ExtraBlock.SUPREME_UNIVERSAL_CABLE, (pos, state) -> new ExtraTileEntityUniversalCable(ExtraBlock.SUPREME_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> COSMIC_UNIVERSAL_CABLE = registerTransmitter(ExtraBlock.COSMIC_UNIVERSAL_CABLE, (pos, state) -> new ExtraTileEntityUniversalCable(ExtraBlock.COSMIC_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> INFINITE_UNIVERSAL_CABLE = registerTransmitter(ExtraBlock.INFINITE_UNIVERSAL_CABLE, (pos, state) -> new ExtraTileEntityUniversalCable(ExtraBlock.INFINITE_UNIVERSAL_CABLE, pos, state));
    //mechanical pipes
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = registerTransmitter(ExtraBlock.ABSOLUTE_MECHANICAL_PIPE, (pos, state) -> new ExtraTileEntityMechanicalPipe(ExtraBlock.ABSOLUTE_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> SUPREME_MECHANICAL_PIPE = registerTransmitter(ExtraBlock.SUPREME_MECHANICAL_PIPE, (pos, state) -> new ExtraTileEntityMechanicalPipe(ExtraBlock.SUPREME_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> COSMIC_MECHANICAL_PIPE = registerTransmitter(ExtraBlock.COSMIC_MECHANICAL_PIPE, (pos, state) -> new ExtraTileEntityMechanicalPipe(ExtraBlock.COSMIC_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> INFINITE_MECHANICAL_PIPE = registerTransmitter(ExtraBlock.INFINITE_MECHANICAL_PIPE, (pos, state) -> new ExtraTileEntityMechanicalPipe(ExtraBlock.INFINITE_MECHANICAL_PIPE, pos, state));
    //pressurized tubes
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = registerTransmitter(ExtraBlock.ABSOLUTE_PRESSURIZED_TUBE, (pos, state) -> new ExtraTileEntityPressurizedTube(ExtraBlock.ABSOLUTE_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> SUPREME_PRESSURIZED_TUBE = registerTransmitter(ExtraBlock.SUPREME_PRESSURIZED_TUBE, (pos, state) -> new ExtraTileEntityPressurizedTube(ExtraBlock.SUPREME_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> COSMIC_PRESSURIZED_TUBE = registerTransmitter(ExtraBlock.COSMIC_PRESSURIZED_TUBE, (pos, state) -> new ExtraTileEntityPressurizedTube(ExtraBlock.COSMIC_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> INFINITE_PRESSURIZED_TUBE = registerTransmitter(ExtraBlock.INFINITE_PRESSURIZED_TUBE, (pos, state) -> new ExtraTileEntityPressurizedTube(ExtraBlock.INFINITE_PRESSURIZED_TUBE, pos, state));
    //logistic transporters
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(ExtraBlock.ABSOLUTE_LOGISTICAL_TRANSPORTER, (pos, state) -> new ExtraTileEntityLogisticalTransporter(ExtraBlock.ABSOLUTE_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(ExtraTileEntityLogisticalTransporterBase::tickClient).serverTicker(ExtraTileEntityTransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(ExtraBlock.SUPREME_LOGISTICAL_TRANSPORTER, (pos, state) -> new ExtraTileEntityLogisticalTransporter(ExtraBlock.SUPREME_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(ExtraTileEntityLogisticalTransporterBase::tickClient).serverTicker(ExtraTileEntityTransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(ExtraBlock.COSMIC_LOGISTICAL_TRANSPORTER, (pos, state) -> new ExtraTileEntityLogisticalTransporter(ExtraBlock.COSMIC_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(ExtraTileEntityLogisticalTransporterBase::tickClient).serverTicker(ExtraTileEntityTransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(ExtraBlock.INFINITE_LOGISTICAL_TRANSPORTER, (pos, state) -> new ExtraTileEntityLogisticalTransporter(ExtraBlock.INFINITE_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(ExtraTileEntityLogisticalTransporterBase::tickClient).serverTicker(ExtraTileEntityTransmitter::extraTickServer).build();
    //thermodynamic conductors
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = registerTransmitter(ExtraBlock.ABSOLUTE_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new ExtraTileEntityThermodynamicConductor(ExtraBlock.ABSOLUTE_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = registerTransmitter(ExtraBlock.SUPREME_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new ExtraTileEntityThermodynamicConductor(ExtraBlock.SUPREME_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = registerTransmitter(ExtraBlock.COSMIC_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new ExtraTileEntityThermodynamicConductor(ExtraBlock.COSMIC_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = registerTransmitter(ExtraBlock.INFINITE_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new ExtraTileEntityThermodynamicConductor(ExtraBlock.INFINITE_THERMODYNAMIC_CONDUCTOR, pos, state));
    //chemical tank
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> ABSOLUTE_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.ABSOLUTE_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlock.ABSOLUTE_CHEMICAL_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> SUPREME_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.SUPREME_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlock.SUPREME_CHEMICAL_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> COSMIC_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.COSMIC_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlock.COSMIC_CHEMICAL_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> INFINITE_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.INFINITE_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlock.INFINITE_CHEMICAL_TANK, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);

    public static final TileEntityTypeRegistryObject<TileEntityAdvancedElectricPump> ADVANCED_ELECTRIC_PUMP = TILE_ENTITY_TYPES.register(ExtraBlock.ADVANCED_ELECTRIC_PUMP, TileEntityAdvancedElectricPump::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);

    public static TileEntityTypeRegistryObject<? extends TileEntityAdvancedFactory<?>> getAdvancedFactoryTile(AdvancedFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityAdvancedFactory<?>>[] getAdvancedFactoryTiles() {
        return FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
