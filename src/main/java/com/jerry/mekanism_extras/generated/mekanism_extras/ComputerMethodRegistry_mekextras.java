package com.jerry.mekanism_extras.generated.mekanism_extras;

import com.jerry.generator_extras.common.content.reactor.NaquadahReactorMultiblockData;
import com.jerry.generator_extras.common.content.reactor.NaquadahReactorMultiblockData$ComputerHandler;
import com.jerry.generator_extras.common.tile.reactor.*;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData$ComputerHandler;
import com.jerry.mekanism_extras.common.integration.Addons;
import com.jerry.mekanism_extras.common.tile.*;
import com.jerry.mekanism_extras.common.tile.factory.*;
import com.jerry.mekanism_extras.common.tile.machine.*;
import com.jerry.mekanism_extras.common.tile.multiblock.*;
import com.jerry.mekanism_extras.common.tile.transmitter.*;
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
        FactoryRegistry.register(TileEntityItemStackGasToItemStackExtraFactory.class, TileEntityItemStackGasToItemStackExtraFactory$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class, TileEntityFactory.class, TileEntityItemToItemFactory.class);
        FactoryRegistry.register(TileEntityCombiningExtraFactory.class, TileEntityCombiningExtraFactory$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class, TileEntityFactory.class, TileEntityItemToItemFactory.class);
        FactoryRegistry.register(TileEntitySawingExtraFactory.class, TileEntitySawingExtraFactory$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class, TileEntityFactory.class, TileEntityItemToItemFactory.class);
        FactoryRegistry.register(ExtraTileEntityBin.class, ExtraTileEntityBin$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class, TileEntityFactory.class, TileEntityItemToItemFactory.class);
        FactoryRegistry.register(ExtraTileEntityChemicalTank.class, ExtraTileEntityChemicalTank$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class);
        FactoryRegistry.register(ExtraTileEntityEnergyCube.class, ExtraTileEntityEnergyCube$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class);
        FactoryRegistry.register(ExtraTileEntityFluidTank.class, ExtraTileEntityFluidTank$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityConfigurableMachine.class);
        FactoryRegistry.register(ExtraTileEntityRadioactiveWasteBarrel.class, ExtraTileEntityRadioactiveWasteBarrel$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class);
        FactoryRegistry.register(TileEntityAdvancedElectricPump.class, TileEntityAdvancedElectricPump$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class);

        //MutiBlock Tile
        FactoryRegistry.register(TileEntityReinforcedInductionPort.class, TileEntityReinforcedInductionPort$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityMultiblock.class, TileEntityReinforcedInductionCasing.class);
        FactoryRegistry.register(ExtraMatrixMultiblockData.class, ExtraMatrixMultiblockData$ComputerHandler::new, MultiblockData.class);

        //Transmitter Tile
        FactoryRegistry.register(ExtraTileEntityMechanicalPipe.class, ExtraTileEntityMechanicalPipe$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityTransmitter.class);
        FactoryRegistry.register(ExtraTileEntityPressurizedTube.class, ExtraTileEntityPressurizedTube$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityTransmitter.class);
        FactoryRegistry.register(ExtraTileEntityUniversalCable.class, ExtraTileEntityUniversalCable$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityTransmitter.class);

        //Generator Extras
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            FactoryRegistry.register(NaquadahReactorMultiblockData.class, NaquadahReactorMultiblockData$ComputerHandler::new, MultiblockData.class);
            FactoryRegistry.register(TileEntityNaquadahReactorPort.class, TileEntityNaquadahReactorPort$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityMultiblock.class, TileEntityNaquadahReactorCasing.class);
            FactoryRegistry.register(TileEntityNaquadahReactorLogicAdapter.class, TileEntityNaquadahReactorLogicAdapter$ComputerHandler::new, TileEntityUpdateable.class, CapabilityTileEntity.class, TileEntityMekanism.class, TileEntityMultiblock.class, TileEntityNaquadahReactorCasing.class);
        }
    }
}
