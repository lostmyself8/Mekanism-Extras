package com.jerry.mekanism_extras.registery;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.block.storage.bin.ExtraTileEntityBin;
import com.jerry.mekanism_extras.common.block.storage.chemicaltank.ExtraTileEntityChemicalTank;
import com.jerry.mekanism_extras.common.block.storage.energycube.ExtraTileEntityEnergyCube;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.ExtraTileEntityFluidTank;
import com.jerry.mekanism_extras.common.block.storage.radioactivewastebarrel.ExtraTileEntityRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.common.block.transmitter.cable.ExtraTileEntityUniversalCable;
import com.jerry.mekanism_extras.common.block.transmitter.logisticaltransporter.ExtraTileEntityLogisticalTransporter;
import com.jerry.mekanism_extras.common.block.transmitter.logisticaltransporter.ExtraTileEntityLogisticalTransporterBase;
import com.jerry.mekanism_extras.common.block.transmitter.logisticaltransporter.ExtraTileEntityTransmitter;
import com.jerry.mekanism_extras.common.block.transmitter.pipe.ExtraTileEntityMechanicalPipe;
import com.jerry.mekanism_extras.common.block.transmitter.thermodynamicconductor.ExtraTileEntityThermodynamicConductor;
import com.jerry.mekanism_extras.common.block.transmitter.tube.ExtraTileEntityPressurizedTube;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.transmitter.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;

public class ExtraTileEntityTypes {
    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MODID);

    private static <BE extends TileEntityTransmitter> TileEntityTypeRegistryObject<BE> registerTransmitter(BlockRegistryObject<?, ?> block, BlockEntityType.BlockEntitySupplier<? extends BE> factory) {
        //Note: There is no data fixer type as forge does not currently have a way exposing data fixers to mods yet
        return TILE_ENTITY_TYPES.<BE>builder(block, factory).serverTicker(TileEntityTransmitter::tickServer).build();
    }

    public static final TileEntityTypeRegistryObject<ExtraTileEntityRadioactiveWasteBarrel> EXPAND_RADIOACTIVE_WASTE_BARREL = TILE_ENTITY_TYPES.register(ExtraBlock.EXPAND_RADIOACTIVE_WASTE_BARREL, ExtraTileEntityRadioactiveWasteBarrel::new);
    //bin
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> ABSOLUTE_BIN = TILE_ENTITY_TYPES.register(ExtraBlock.ABSOLUTE_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlock.ABSOLUTE_BIN, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> SUPREME_BIN = TILE_ENTITY_TYPES.register(ExtraBlock.SUPREME_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlock.SUPREME_BIN, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> COSMIC_BIN = TILE_ENTITY_TYPES.register(ExtraBlock.COSMIC_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlock.COSMIC_BIN, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> INFINITE_BIN = TILE_ENTITY_TYPES.register(ExtraBlock.INFINITE_BIN, (pos, state) -> new ExtraTileEntityBin(ExtraBlock.INFINITE_BIN, pos, state));
    //fluid tank
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> ABSOLUTE_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.ABSOLUTE_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlock.ABSOLUTE_FLUID_TANK, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> SUPREME_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.SUPREME_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlock.SUPREME_FLUID_TANK, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> COSMIC_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.COSMIC_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlock.COSMIC_FLUID_TANK, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> INFINITE_FLUID_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.INFINITE_FLUID_TANK, (pos, state) -> new ExtraTileEntityFluidTank(ExtraBlock.INFINITE_FLUID_TANK, pos, state));
    //energy cube
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> ABSOLUTE_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlock.ABSOLUTE_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlock.ABSOLUTE_ENERGY_CUBE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> SUPREME_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlock.SUPREME_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlock.SUPREME_ENERGY_CUBE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> COSMIC_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlock.COSMIC_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlock.COSMIC_ENERGY_CUBE, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> INFINITE_ENERGY_CUBE = TILE_ENTITY_TYPES.register(ExtraBlock.INFINITE_ENERGY_CUBE, (pos, state) -> new ExtraTileEntityEnergyCube(ExtraBlock.INFINITE_ENERGY_CUBE, pos, state));
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
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> ABSOLUTE_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.ABSOLUTE_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlock.ABSOLUTE_CHEMICAL_TANK, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> SUPREME_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.SUPREME_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlock.SUPREME_CHEMICAL_TANK, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> COSMIC_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.COSMIC_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlock.COSMIC_CHEMICAL_TANK, pos, state));
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> INFINITE_CHEMICAL_TANK = TILE_ENTITY_TYPES.register(ExtraBlock.INFINITE_CHEMICAL_TANK, (pos, state) -> new ExtraTileEntityChemicalTank(ExtraBlock.INFINITE_CHEMICAL_TANK, pos, state));

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
