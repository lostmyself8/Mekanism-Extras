package com.jerry.mekanism_extras.common.integration.mekaf.inventory.slot;

import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.TileEntityExtraPressurizedReactingFactory;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.*;

import mekanism.api.IContentsListener;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.chemical.merged.MergedChemicalTank;
import mekanism.api.chemical.pigment.IPigmentTank;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExtraAdvancedFactoryInputInventorySlot extends InputInventorySlot {

    private final TileEntityExtraAdvancedFactoryBase<?> factory;

    public static ExtraAdvancedFactoryInputInventorySlot create(TileEntityExtraItemToPigmentFactory<?> factory, int process, IPigmentTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Pigment output tank cannot be null");
        return new ExtraAdvancedFactoryInputInventorySlot(factory, process, outputTank, listener, x, y);
    }

    private ExtraAdvancedFactoryInputInventorySlot(TileEntityExtraItemToPigmentFactory<?> factory, int process, IPigmentTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputTank, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static ExtraAdvancedFactoryInputInventorySlot create(TileEntityExtraItemToGasFactory<?> factory, int process, IGasTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Gas output tank cannot be null");
        return new ExtraAdvancedFactoryInputInventorySlot(factory, process, outputTank, listener, x, y);
    }

    private ExtraAdvancedFactoryInputInventorySlot(TileEntityExtraItemToGasFactory<?> factory, int process, IGasTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputTank, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static ExtraAdvancedFactoryInputInventorySlot create(TileEntityExtraItemToMergedFactory<?> factory, int process, MergedChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Chemical output tank cannot be null");
        return new ExtraAdvancedFactoryInputInventorySlot(factory, process, outputTank, listener, x, y);
    }

    private ExtraAdvancedFactoryInputInventorySlot(TileEntityExtraItemToMergedFactory<?> factory, int process, MergedChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputTank, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static ExtraAdvancedFactoryInputInventorySlot create(TileEntityExtraItemToFluidFactory<?> factory, int process, IExtendedFluidTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Fluid output tank cannot be null");
        return new ExtraAdvancedFactoryInputInventorySlot(factory, process, outputTank, listener, x, y);
    }

    private ExtraAdvancedFactoryInputInventorySlot(TileEntityExtraItemToFluidFactory<?> factory, int process, IExtendedFluidTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputTank, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static ExtraAdvancedFactoryInputInventorySlot create(TileEntityExtraItemToItemAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        return new ExtraAdvancedFactoryInputInventorySlot(factory, process, outputSlot, listener, x, y);
    }

    private ExtraAdvancedFactoryInputInventorySlot(TileEntityExtraItemToItemAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputSlot, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static ExtraAdvancedFactoryInputInventorySlot create(TileEntityExtraPressurizedReactingFactory factory, int process, IInventorySlot outputSlot, IGasTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Fluid output tank cannot be null");
        return new ExtraAdvancedFactoryInputInventorySlot(factory, process, outputSlot, outputTank, listener, x, y);
    }

    private ExtraAdvancedFactoryInputInventorySlot(TileEntityExtraPressurizedReactingFactory factory, int process, IInventorySlot outputSlot, IGasTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputSlot, outputTank, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public void setStackUnchecked(@NotNull ItemStack stack) {
        super.setStackUnchecked(stack);
    }

    @Override
    public int getLimit(@NotNull ItemStack stack) {
        try {
            return Math.multiplyExact(super.getLimit(stack), 8 << factory.tier.ordinal());
        } catch (ArithmeticException ignored) {
            return Integer.MAX_VALUE;
        }
    }
}
