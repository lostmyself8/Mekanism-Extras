package com.jerry.mekextras.common.registry;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.block.basic.ExtraBlockBin;
import com.jerry.mekextras.common.item.block.ExtraItemBlockBin;
import com.jerry.mekextras.common.item.block.ItemBlockLargeCapRadioactiveWasteBarrel;
import com.jerry.mekextras.common.tile.*;
import com.jerry.mekextras.common.item.block.ExtraItemBlockChemicalTank;
import com.jerry.mekextras.common.block.ExtraBlockEnergyCube;
import com.jerry.mekextras.common.item.block.ExtraItemBlockEnergyCube;
import com.jerry.mekextras.common.block.basic.ExtraBlockFluidTank;
import com.jerry.mekextras.common.item.block.machine.ExtraItemBlockFluidTank;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityUniversalCable;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporterBase;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityMechanicalPipe;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityThermodynamicConductor;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityPressurizedTube;
import com.jerry.mekextras.common.capabilities.ExtraCapabilities;
import com.jerry.mekextras.common.tile.machine.TileEntityAdvanceElectricPump;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionPort;
import com.jerry.mekextras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekextras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import mekanism.api.functions.ConstantPredicates;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.Mekanism;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.integration.computer.ComputerCapabilityHelper;
import mekanism.common.integration.energy.EnergyCompatUtils;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.CapabilityTileEntity;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;

public class ExtraTileEntityTypes {
    public static final TileEntityTypeDeferredRegister EXTRA_TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MOD_ID);

    public static final TileEntityTypeRegistryObject<TileEntityAdvanceElectricPump> ADVANCE_ELECTRIC_PUMP = EXTRA_TILE_ENTITY_TYPES
            .mekBuilder(ExtraBlocks.ADVANCE_ELECTRIC_PUMP, TileEntityAdvanceElectricPump::new)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIG_CARD)
            .withSimple(Capabilities.CONFIGURABLE)
            .build();

    //Tiered Tiles
    //Bins
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> ABSOLUTE_BIN = registerBin(ExtraBlocks.ABSOLUTE_BIN);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> SUPREME_BIN = registerBin(ExtraBlocks.SUPREME_BIN);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> COSMIC_BIN = registerBin(ExtraBlocks.COSMIC_BIN);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityBin> INFINITE_BIN = registerBin(ExtraBlocks.INFINITE_BIN);

    private static TileEntityTypeRegistryObject<ExtraTileEntityBin> registerBin(BlockRegistryObject<ExtraBlockBin, ExtraItemBlockBin> block) {
        return EXTRA_TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> new ExtraTileEntityBin(block, pos, state))
                .serverTicker(TileEntityMekanism::tickServer)
                .withSimple(Capabilities.CONFIGURABLE)
                .build();
    }

    //Energy Cubes
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> ABSOLUTE_ENERGY_CUBE = registerEnergyCube(ExtraBlocks.ABSOLUTE_ENERGY_CUBE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> SUPREME_ENERGY_CUBE = registerEnergyCube(ExtraBlocks.SUPREME_ENERGY_CUBE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> COSMIC_ENERGY_CUBE = registerEnergyCube(ExtraBlocks.COSMIC_ENERGY_CUBE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> INFINITE_ENERGY_CUBE = registerEnergyCube(ExtraBlocks.INFINITE_ENERGY_CUBE);

    private static TileEntityTypeRegistryObject<ExtraTileEntityEnergyCube> registerEnergyCube(BlockRegistryObject<ExtraBlockEnergyCube, ExtraItemBlockEnergyCube> block) {
        return EXTRA_TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> new ExtraTileEntityEnergyCube(block, pos, state))
                .serverTicker(TileEntityMekanism::tickServer)
                .withSimple(Capabilities.CONFIG_CARD)
                .build();
    }

    //Fluid Tanks
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> ABSOLUTE_FLUID_TANK = registerFluidTank(ExtraBlocks.ABSOLUTE_FLUID_TANK);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> SUPREME_FLUID_TANK = registerFluidTank(ExtraBlocks.SUPREME_FLUID_TANK);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> COSMIC_FLUID_TANK = registerFluidTank(ExtraBlocks.COSMIC_FLUID_TANK);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> INFINITE_FLUID_TANK = registerFluidTank(ExtraBlocks.INFINITE_FLUID_TANK);

    private static TileEntityTypeRegistryObject<ExtraTileEntityFluidTank> registerFluidTank(BlockRegistryObject<ExtraBlockFluidTank, ExtraItemBlockFluidTank> block) {
        return EXTRA_TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> new ExtraTileEntityFluidTank(block, pos, state))
                .clientTicker(TileEntityMekanism::tickClient)
                .serverTicker(TileEntityMekanism::tickServer)
                .withSimple(Capabilities.CONFIG_CARD)
                .withSimple(Capabilities.CONFIGURABLE)
                .build();
    }

    //Chemical Tanks
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> ABSOLUTE_CHEMICAL_TANK = registerChemicalTank(ExtraBlocks.ABSOLUTE_CHEMICAL_TANK);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> SUPREME_CHEMICAL_TANK = registerChemicalTank(ExtraBlocks.SUPREME_CHEMICAL_TANK);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> COSMIC_CHEMICAL_TANK = registerChemicalTank(ExtraBlocks.COSMIC_CHEMICAL_TANK);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> INFINITE_CHEMICAL_TANK = registerChemicalTank(ExtraBlocks.INFINITE_CHEMICAL_TANK);

    private static TileEntityTypeRegistryObject<ExtraTileEntityChemicalTank> registerChemicalTank(BlockRegistryObject<?, ExtraItemBlockChemicalTank> block) {
        return EXTRA_TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> new ExtraTileEntityChemicalTank(block, pos, state))
                .serverTicker(TileEntityMekanism::tickServer)
                .withSimple(Capabilities.CONFIG_CARD)
                .build();
    }

    public static final TileEntityTypeRegistryObject<TileEntityLargeCapRadioactiveWasteBarrel> ABSOLUTE_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlocks.ABSOLUTE_RADIOACTIVE_WASTE_BARREL);
    public static final TileEntityTypeRegistryObject<TileEntityLargeCapRadioactiveWasteBarrel> SUPREME_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlocks.SUPREME_RADIOACTIVE_WASTE_BARREL);
    public static final TileEntityTypeRegistryObject<TileEntityLargeCapRadioactiveWasteBarrel> COSMIC_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlocks.COSMIC_RADIOACTIVE_WASTE_BARREL);
    public static final TileEntityTypeRegistryObject<TileEntityLargeCapRadioactiveWasteBarrel> INFINITE_RADIOACTIVE_WASTE_BARREL = registryWasteBarrel(ExtraBlocks.INFINITE_RADIOACTIVE_WASTE_BARREL);

    private static TileEntityTypeRegistryObject<TileEntityLargeCapRadioactiveWasteBarrel> registryWasteBarrel(BlockRegistryObject<?, ItemBlockLargeCapRadioactiveWasteBarrel> block) {
        return EXTRA_TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> new TileEntityLargeCapRadioactiveWasteBarrel(block, pos, state))
                .serverTicker(TileEntityMekanism::tickServer)
                .build();
    }

    //Transmitters
    //Universal Cables
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = registerCable(ExtraBlocks.ABSOLUTE_UNIVERSAL_CABLE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> SUPREME_UNIVERSAL_CABLE = registerCable(ExtraBlocks.SUPREME_UNIVERSAL_CABLE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> COSMIC_UNIVERSAL_CABLE = registerCable(ExtraBlocks.COSMIC_UNIVERSAL_CABLE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> INFINITE_UNIVERSAL_CABLE = registerCable(ExtraBlocks.INFINITE_UNIVERSAL_CABLE);
    //Mechanical Pipes
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = registerPipe(ExtraBlocks.ABSOLUTE_MECHANICAL_PIPE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> SUPREME_MECHANICAL_PIPE = registerPipe(ExtraBlocks.SUPREME_MECHANICAL_PIPE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> COSMIC_MECHANICAL_PIPE = registerPipe(ExtraBlocks.COSMIC_MECHANICAL_PIPE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> INFINITE_MECHANICAL_PIPE = registerPipe(ExtraBlocks.INFINITE_MECHANICAL_PIPE);
    //Pressurized Tubes
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = registerTube(ExtraBlocks.ABSOLUTE_PRESSURIZED_TUBE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> SUPREME_PRESSURIZED_TUBE = registerTube(ExtraBlocks.SUPREME_PRESSURIZED_TUBE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> COSMIC_PRESSURIZED_TUBE = registerTube(ExtraBlocks.COSMIC_PRESSURIZED_TUBE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> INFINITE_PRESSURIZED_TUBE = registerTube(ExtraBlocks.INFINITE_PRESSURIZED_TUBE);
    //Logistic Transporters
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = registerTransporter(ExtraBlocks.ABSOLUTE_LOGISTICAL_TRANSPORTER, ExtraTileEntityLogisticalTransporter::new);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = registerTransporter(ExtraBlocks.SUPREME_LOGISTICAL_TRANSPORTER, ExtraTileEntityLogisticalTransporter::new);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = registerTransporter(ExtraBlocks.COSMIC_LOGISTICAL_TRANSPORTER, ExtraTileEntityLogisticalTransporter::new);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = registerTransporter(ExtraBlocks.INFINITE_LOGISTICAL_TRANSPORTER, ExtraTileEntityLogisticalTransporter::new);
    //Thermodynamic Conductors
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = registerConductor(ExtraBlocks.ABSOLUTE_THERMODYNAMIC_CONDUCTOR);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = registerConductor(ExtraBlocks.SUPREME_THERMODYNAMIC_CONDUCTOR);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = registerConductor(ExtraBlocks.COSMIC_THERMODYNAMIC_CONDUCTOR);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = registerConductor(ExtraBlocks.INFINITE_THERMODYNAMIC_CONDUCTOR);

    private static TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> registerCable(BlockRegistryObject<?, ?> block) {
        TileEntityTypeDeferredRegister.BlockEntityTypeBuilder<ExtraTileEntityUniversalCable> builder = transmitterBuilder(block, ExtraTileEntityUniversalCable::new);
        EnergyCompatUtils.addBlockCapabilities(builder);
        if (Mekanism.hooks.computerCompatEnabled()) {
            ComputerCapabilityHelper.addComputerCapabilities(builder, ConstantPredicates.ALWAYS_TRUE);
        }
        return builder.build();
    }
    private static TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> registerPipe(BlockRegistryObject<?, ?> block) {
        TileEntityTypeDeferredRegister.BlockEntityTypeBuilder<ExtraTileEntityMechanicalPipe> builder = transmitterBuilder(block, ExtraTileEntityMechanicalPipe::new)
                .with(Capabilities.FLUID.block(), CapabilityTileEntity.FLUID_HANDLER_PROVIDER);
        if (Mekanism.hooks.computerCompatEnabled()) {
            ComputerCapabilityHelper.addComputerCapabilities(builder, ConstantPredicates.ALWAYS_TRUE);
        }
        return builder.build();
    }
    private static TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> registerTube(BlockRegistryObject<?, ?> block) {
        TileEntityTypeDeferredRegister.BlockEntityTypeBuilder<ExtraTileEntityPressurizedTube> builder = transmitterBuilder(block, ExtraTileEntityPressurizedTube::new)
                .with(Capabilities.CHEMICAL.block(), CapabilityTileEntity.CHEMICAL_HANDLER_PROVIDER);
        if (Mekanism.hooks.computerCompatEnabled()) {
            ComputerCapabilityHelper.addComputerCapabilities(builder, ConstantPredicates.ALWAYS_TRUE);
        }
        return builder.build();
    }
    private static <BE extends ExtraTileEntityLogisticalTransporterBase> TileEntityTypeRegistryObject<BE> registerTransporter(BlockRegistryObject<?, ?> block, BlockEntityFactory<BE> factory) {
        return transporterBuilder(block, factory).build();
    }
    private static <BE extends ExtraTileEntityLogisticalTransporterBase> TileEntityTypeDeferredRegister.BlockEntityTypeBuilder<BE> transporterBuilder(BlockRegistryObject<?, ?> block, BlockEntityFactory<BE> factory) {
        return transmitterBuilder(block, factory)
                .clientTicker(ExtraTileEntityLogisticalTransporterBase::tickClient)
                .with(Capabilities.ITEM.block(), CapabilityTileEntity.ITEM_HANDLER_PROVIDER);
    }
    private static TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> registerConductor(BlockRegistryObject<?, ?> block) {
        return transmitterBuilder(block, ExtraTileEntityThermodynamicConductor::new)
                .with(Capabilities.HEAT, CapabilityTileEntity.HEAT_HANDLER_PROVIDER)
                .build();
    }
    private static <BE extends TileEntityTransmitter> TileEntityTypeDeferredRegister.BlockEntityTypeBuilder<BE> transmitterBuilder(BlockRegistryObject<?, ?> block, BlockEntityFactory<BE> factory) {
        return EXTRA_TILE_ENTITY_TYPES.builder(block, (pos, state) -> factory.create(block, pos, state))
                .serverTicker(TileEntityTransmitter::tickServer)
                .withSimple(ExtraCapabilities.EXTRA_ALLOY_INTERACTION)
                .with(Capabilities.CONFIGURABLE, TileEntityTransmitter.CONFIGURABLE_PROVIDER);
    }
    public static final TileEntityTypeRegistryObject<TileEntityReinforcedInductionCasing> REINFORCED_INDUCTION_CASING = EXTRA_TILE_ENTITY_TYPES
            .mekBuilder(ExtraBlocks.REINFORCED_INDUCTION_CASING, TileEntityReinforcedInductionCasing::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIGURABLE)
            //Disable item handler caps if we are the induction casing (but not the port)
            .without(Capabilities.ITEM.block())
            .build();
    public static final TileEntityTypeRegistryObject<TileEntityReinforcedInductionPort> REINFORCED_INDUCTION_PORT = EXTRA_TILE_ENTITY_TYPES
            .mekBuilder(ExtraBlocks.REINFORCED_INDUCTION_PORT, TileEntityReinforcedInductionPort::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIGURABLE)
            .build();
    //Induction Cells
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> ABSOLUTE_INDUCTION_CELL = EXTRA_TILE_ENTITY_TYPES.builder(ExtraBlocks.ABSOLUTE_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlocks.ABSOLUTE_INDUCTION_CELL, pos, state)).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> SUPREME_INDUCTION_CELL = EXTRA_TILE_ENTITY_TYPES.builder(ExtraBlocks.SUPREME_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlocks.SUPREME_INDUCTION_CELL, pos, state)).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> COSMIC_INDUCTION_CELL = EXTRA_TILE_ENTITY_TYPES.builder(ExtraBlocks.COSMIC_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlocks.COSMIC_INDUCTION_CELL, pos, state)).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionCell> INFINITE_INDUCTION_CELL = EXTRA_TILE_ENTITY_TYPES.builder(ExtraBlocks.INFINITE_INDUCTION_CELL, (pos, state) -> new ExtraTileEntityInductionCell(ExtraBlocks.INFINITE_INDUCTION_CELL, pos, state)).build();
    //Induction Providers
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> ABSOLUTE_INDUCTION_PROVIDER = EXTRA_TILE_ENTITY_TYPES.builder(ExtraBlocks.ABSOLUTE_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlocks.ABSOLUTE_INDUCTION_PROVIDER, pos, state)).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> SUPREME_INDUCTION_PROVIDER = EXTRA_TILE_ENTITY_TYPES.builder(ExtraBlocks.SUPREME_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlocks.SUPREME_INDUCTION_PROVIDER, pos, state)).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> COSMIC_INDUCTION_PROVIDER = EXTRA_TILE_ENTITY_TYPES.builder(ExtraBlocks.COSMIC_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlocks.COSMIC_INDUCTION_PROVIDER, pos, state)).build();
    public static final TileEntityTypeRegistryObject<ExtraTileEntityInductionProvider> INFINITE_INDUCTION_PROVIDER = EXTRA_TILE_ENTITY_TYPES.builder(ExtraBlocks.INFINITE_INDUCTION_PROVIDER, (pos, state) -> new ExtraTileEntityInductionProvider(ExtraBlocks.INFINITE_INDUCTION_PROVIDER, pos, state)).build();
    @FunctionalInterface
    private interface BlockEntityFactory<BE extends BlockEntity> {

        BE create(IBlockProvider block, BlockPos pos, BlockState state);
    }
    public static void register(IEventBus eventBus) {
        EXTRA_TILE_ENTITY_TYPES.register(eventBus);
    }
}
