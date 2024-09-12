package com.jerry.mekextras.common.content.matrix;

import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionPort;
import com.jerry.mekextras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekextras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import mekanism.api.math.FloatingLong;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.inventory.container.sync.dynamic.ContainerSync;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.lib.multiblock.IValveHandler;
import mekanism.common.lib.multiblock.MultiblockCache;
import mekanism.common.lib.multiblock.MultiblockData;
import mekanism.common.lib.multiblock.Structure;
import mekanism.common.util.CableUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.WorldUtils;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReinforcedMatrixMultiblockData extends MultiblockData {
    public static final String STATS_TAB = "stats";

    private final List<EnergyOutputTarget> energyOutputTargets = new ArrayList<>();
    @NotNull
    private final ReinforcedMatrixEnergyContainer energyContainer;

    @ContainerSync(getter = "getLastOutput")
    private FloatingLong clientLastOutput = FloatingLong.ZERO;
    @ContainerSync(getter = "getLastInput")
    private FloatingLong clientLastInput = FloatingLong.ZERO;

    @ContainerSync(getter = "getEnergy")
    private FloatingLong clientEnergy = FloatingLong.ZERO;

    @ContainerSync(tags = STATS_TAB, getter = "getTransferCap")
    private FloatingLong clientMaxTransfer = FloatingLong.ZERO;

    @ContainerSync(getter = "getStorageCap")
    private FloatingLong clientMaxEnergy = FloatingLong.ZERO;

    @ContainerSync(tags = STATS_TAB, getter = "getProviderCount")
    private int clientProviders;
    @ContainerSync(tags = STATS_TAB, getter = "getCellCount")
    private int clientCells;

    @NotNull
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getInputItem", docPlaceholder = "input slot")
    final EnergyInventorySlot energyInputSlot;
    @NotNull
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getOutputItem", docPlaceholder = "output slot")
    final EnergyInventorySlot energyOutputSlot;

    public ReinforcedMatrixMultiblockData(TileEntityReinforcedInductionCasing tile) {
        super(tile);
        energyContainers.add(energyContainer = new ReinforcedMatrixEnergyContainer(this));
        inventorySlots.add(energyInputSlot = EnergyInventorySlot.drain(energyContainer, this, 146, 21));
        inventorySlots.add(energyOutputSlot = EnergyInventorySlot.fillOrConvert(energyContainer, tile::getLevel, this, 146, 51));
        energyInputSlot.setSlotOverlay(SlotOverlay.PLUS);
        energyOutputSlot.setSlotOverlay(SlotOverlay.MINUS);
    }

    @Override
    protected int getMultiblockRedstoneLevel() {
        return MekanismUtils.redstoneLevelFromContents(getEnergy(), getStorageCap());
    }

    @Override
    protected boolean shouldCap(MultiblockCache.CacheSubstance<?, ?> type) {
        return type != MultiblockCache.CacheSubstance.ENERGY;
    }

    public void addCell(ExtraTileEntityInductionCell cell) {
        energyContainer.addCell(cell.getBlockPos(), cell);
    }

    public void addProvider(ExtraTileEntityInductionProvider provider) {
        energyContainer.addProvider(provider.getBlockPos(), provider);
    }

    @NotNull
    public ReinforcedMatrixEnergyContainer getEnergyContainer() {
        return energyContainer;
    }

    public FloatingLong getEnergy() {
        return isRemote() ? clientEnergy : energyContainer.getEnergy();
    }

    @Override
    public boolean tick(Level world) {
        boolean ret = super.tick(world);
        energyContainer.tick();
        // We tick the main energy container before adding/draining from the slots, so that we make sure
        // they get first "pickings" at attempting to get or give power, without having to worry about the
        // rate limit of the structure being used up by the ports
        energyInputSlot.drainContainer();
        energyOutputSlot.fillContainerOrConvert();
        if (!energyOutputTargets.isEmpty() && !energyContainer.isEmpty()) {
            CableUtils.emit(getActiveOutputs(energyOutputTargets), energyContainer, energyContainer.getMaxTransfer());
        }
        if (!getLastInput().isZero() || !getLastOutput().isZero()) {
            // If the stored energy changed, update the comparator
            markDirtyComparator(world);
        }
        return ret;
    }

    @Override
    public void remove(Level world, Structure oldStructure) {
        energyContainer.invalidate();
        super.remove(world, oldStructure);
    }

    @Override
    protected void updateEjectors(Level world) {
        energyOutputTargets.clear();
        for (IValveHandler.ValveData valve : valves) {
            TileEntityReinforcedInductionPort tile = WorldUtils.getTileEntity(TileEntityReinforcedInductionPort.class, world, valve.location);
            if (tile != null) {
                tile.addEnergyTargetCapability(energyOutputTargets, valve.side);
            }
        }
    }

    public FloatingLong getStorageCap() {
        return isRemote() ? clientMaxEnergy : energyContainer.getMaxEnergy();
    }

    @ComputerMethod
    public FloatingLong getTransferCap() {
        return isRemote() ? clientMaxTransfer : energyContainer.getMaxTransfer();
    }

    @ComputerMethod
    public FloatingLong getLastInput() {
        return isRemote() ? clientLastInput : energyContainer.getLastInput();
    }

    @ComputerMethod
    public FloatingLong getLastOutput() {
        return isRemote() ? clientLastOutput : energyContainer.getLastOutput();
    }

    @ComputerMethod(nameOverride = "getInstalledCells")
    public int getCellCount() {
        return isRemote() ? clientCells : energyContainer.getCells();
    }

    @ComputerMethod(nameOverride = "getInstalledProviders")
    public int getProviderCount() {
        return isRemote() ? clientProviders : energyContainer.getProviders();
    }
}
