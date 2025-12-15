package com.jerry.mekextras.common.integration.mekaf.inventory.slot;

import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraItemToChemicalFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraLiquifyingFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraPRCFactory;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExtraAdvancedFactoryInputInventorySlot extends InputInventorySlot {

    public static ExtraAdvancedFactoryInputInventorySlot create(TileEntityExtraItemToChemicalFactory<?> factory, int process, IChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Chemical output tank cannot be null");
        return new ExtraAdvancedFactoryInputInventorySlot(factory, process, outputTank, listener, x, y);
    }

    private ExtraAdvancedFactoryInputInventorySlot(TileEntityExtraItemToChemicalFactory<?> factory, int process, IChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputTank, false),
                factory::isValidInputItem, listener, x, y);
    }

    public static ExtraAdvancedFactoryInputInventorySlot create(TileEntityExtraPRCFactory factory, int process, IInventorySlot outputSlot, IChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Chemical output tank cannot be null");
        return new ExtraAdvancedFactoryInputInventorySlot(factory, process, outputSlot, outputTank, listener, x, y);
    }

    private ExtraAdvancedFactoryInputInventorySlot(TileEntityExtraPRCFactory factory, int process, IInventorySlot outputSlot, IChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputSlot, outputTank, false),
                factory::isValidInputItem, listener, x, y);
    }

    public static ExtraAdvancedFactoryInputInventorySlot create(TileEntityExtraLiquifyingFactory factory, int process, IInventorySlot outputSlot, IExtendedFluidTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Fluid output tank cannot be null");
        return new ExtraAdvancedFactoryInputInventorySlot(factory, process, outputSlot, outputTank, listener, x, y);
    }

    private ExtraAdvancedFactoryInputInventorySlot(TileEntityExtraLiquifyingFactory factory, int process, IInventorySlot outputSlot, IExtendedFluidTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputSlot, outputTank, false),
                factory::isValidInputItem, listener, x, y);
    }
}
