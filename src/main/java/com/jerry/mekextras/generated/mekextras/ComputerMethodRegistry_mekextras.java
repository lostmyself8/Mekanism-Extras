package com.jerry.mekextras.generated.mekextras;

import com.jerry.genextras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.genextras.common.content.naquadah.NaquadahReactorMultiblockData$ComputerHandler;
import com.jerry.genextras.common.tile.naquadah.*;
import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.content.matrix.ReinforcedMatrixMultiblockData;
import com.jerry.mekextras.common.content.matrix.ReinforcedMatrixMultiblockData$ComputerHandler;
import com.jerry.mekextras.common.tile.*;
import com.jerry.mekextras.common.tile.factory.*;
import com.jerry.mekextras.common.tile.machine.TileEntityAdvancedElectricPump;
import com.jerry.mekextras.common.tile.machine.TileEntityAdvancedElectricPump$ComputerHandler;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionPort;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionPort$ComputerHandler;
import com.jerry.mekextras.common.tile.transmitter.*;
import mekanism.common.integration.computer.FactoryRegistry;
import mekanism.common.integration.computer.IComputerMethodRegistry;
import mekanism.common.lib.multiblock.MultiblockData;
import mekanism.common.tile.base.CapabilityTileEntity;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.base.TileEntityUpdateable;
import mekanism.common.tile.factory.TileEntityFactory;
import mekanism.common.tile.factory.TileEntityItemToItemFactory;
import mekanism.common.tile.prefab.TileEntityConfigurableMachine;
import mekanism.common.tile.prefab.TileEntityMultiblock;
import mekanism.common.tile.transmitter.TileEntityTransmitter;

public class ComputerMethodRegistry_mekextras implements IComputerMethodRegistry {
    @Override
    public void register() {
        //Normal Tile
        FactoryRegistry.register(TileEntityExtraFactory.class, TileEntityExtraFactory$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class);
        FactoryRegistry.register(TileEntityExtraItemStackChemicalToItemStackFactory.class, TileEntityExtraItemStackChemicalToItemStackFactory$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class, TileEntityFactory.class, TileEntityItemToItemFactory.class);
        FactoryRegistry.register(TileEntityExtraCombiningFactory.class, TileEntityExtraCombiningFactory$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class, TileEntityFactory.class, TileEntityItemToItemFactory.class);
        FactoryRegistry.register(TileEntityExtraSawingFactory.class, TileEntityExtraSawingFactory$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class, TileEntityFactory.class, TileEntityItemToItemFactory.class);
        FactoryRegistry.register(TileEntityExtraBin.class, TileEntityExtraBin$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class, TileEntityFactory.class, TileEntityItemToItemFactory.class);
        FactoryRegistry.register(TileEntityExtraChemicalTank.class, TileEntityExtraChemicalTank$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class);
        FactoryRegistry.register(TileEntityExtraEnergyCube.class, TileEntityExtraEnergyCube$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class);
        FactoryRegistry.register(TileEntityExtraFluidTank.class, TileEntityExtraFluidTank$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class);
        FactoryRegistry.register(TileEntityLargeCapRadioactiveWasteBarrel.class, TileEntityLargeCapRadioactiveWasteBarrel$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class);
        FactoryRegistry.register(TileEntityAdvancedElectricPump.class, TileEntityAdvancedElectricPump$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class);

        //MutiBlock Tile
        FactoryRegistry.register(TileEntityReinforcedInductionPort.class, TileEntityReinforcedInductionPort$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityMultiblock.class, TileEntityReinforcedInductionCasing.class);
        FactoryRegistry.register(ReinforcedMatrixMultiblockData.class, ReinforcedMatrixMultiblockData$ComputerHandler::new, MultiblockData.class);

        //Transmitter Tile
        FactoryRegistry.register(TileEntityExtraMechanicalPipe.class, TileEntityExtraMechanicalPipe$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityTransmitter.class);
        FactoryRegistry.register(TileEntityExtraPressurizedTube.class, TileEntityExtraPressurizedTube$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityTransmitter.class);
        FactoryRegistry.register(TileEntityExtraUniversalCable.class, TileEntityExtraUniversalCable$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityTransmitter.class);

        //Generator Extras
        if (MekanismExtras.hooks.mekanismGenerators.isLoaded()) {
            FactoryRegistry.register(NaquadahReactorMultiblockData.class, NaquadahReactorMultiblockData$ComputerHandler::new, MultiblockData.class);
            FactoryRegistry.register(TileEntityNaquadahReactorPort.class, TileEntityNaquadahReactorPort$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityMultiblock.class, TileEntityNaquadahReactorCasing.class);
            FactoryRegistry.register(TileEntityNaquadahReactorLogicAdapter.class, TileEntityNaquadahReactorLogicAdapter$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityMultiblock.class, TileEntityNaquadahReactorCasing.class);
        }
    }
}
