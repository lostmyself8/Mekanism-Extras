package com.jerry.mekextras.common.inventory.slot.chemical;

import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalHandler;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.functions.ConstantPredicates;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

@NothingNullByDefault
public class ExtraFactoryChemicalInventorySlot extends ChemicalInventorySlot {

    private final TileEntityExtraFactory<?> factory;

    protected ExtraFactoryChemicalInventorySlot(TileEntityExtraFactory<?> factory, IChemicalTank chemicalTank, Supplier<Level> worldSupplier, Predicate<@NotNull ItemStack> canExtract, Predicate<@NotNull ItemStack> canInsert, @Nullable IContentsListener listener, int x, int y) {
        super(chemicalTank, worldSupplier, canExtract, canInsert, ConstantPredicates.alwaysTrue(), listener, x, y);
        this.factory = factory;
    }

    public static ExtraFactoryChemicalInventorySlot fillOrConverts(TileEntityExtraFactory<?> factory, IChemicalTank gasTank, Supplier<Level> worldSupplier, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(gasTank, "Gas tank cannot be null");
        Objects.requireNonNull(worldSupplier, "World supplier cannot be null");
        return new ExtraFactoryChemicalInventorySlot(factory, gasTank, worldSupplier, getFillOrConvertExtractPredicate(gasTank, worldSupplier), getFillOrConvertInsertPredicate(gasTank, worldSupplier), listener, x, y);
    }

    private static Predicate<@NotNull ItemStack> getFillOrConvertExtractPredicate(IChemicalTank chemicalTank, Supplier<Level> levelSupplier) {
        return stack -> {
            IChemicalHandler handler = Capabilities.CHEMICAL.getCapability(stack);
            if (handler != null) {
                for (int tank = 0; tank < handler.getChemicalTanks(); tank++) {
                    if (chemicalTank.isValid(handler.getChemicalInTank(tank))) {
                        // False if the items contents are still valid
                        return false;
                    }
                }
                // Only allow extraction if our item is out of chemical, and doesn't have a valid conversion for it
            }
            // Always allow extraction if something went horribly wrong, and we are not a chemical item AND we can't
            // provide a valid type of chemical
            // This might happen after a reload for example
            ChemicalStack conversion = getPotentialConversion(levelSupplier.get(), stack);
            return conversion.isEmpty() || !chemicalTank.isValid(conversion);
        };
    }

    private static Predicate<@NotNull ItemStack> getFillOrConvertInsertPredicate(IChemicalTank chemicalTank, Supplier<Level> levelSupplier) {
        return stack -> {
            if (fillInsertCheck(chemicalTank, stack)) {
                return true;
            }
            ChemicalStack conversion = getPotentialConversion(levelSupplier.get(), stack);
            // Note: We recheck about this being empty and that it is still valid as the conversion list might have
            // changed, such as after a reload
            if (conversion.isEmpty()) {
                return false;
            }
            if (chemicalTank.insert(conversion, Action.SIMULATE, AutomationType.INTERNAL).getAmount() < conversion.getAmount()) {
                // If we can insert the converted substance into the tank allow insertion
                return true;
            }
            // If we can't because the tank is full, we do a slightly less accurate check and validate that the type
            // matches the stored type
            // and that it is still actually valid for the tank, as a reload could theoretically make it no longer be
            // valid while there is still some stored
            return chemicalTank.getNeeded() == 0 && chemicalTank.isTypeEqual(conversion) && chemicalTank.isValid(conversion);
        };
    }

    @Override
    public int getLimit(ItemStack stack) {
        if (!stack.isEmpty() && Capabilities.CHEMICAL.hasCapability(stack)) {
            return super.getLimit(stack);
        } else {
            int processes = factory.tier.processes;
            return switch (factory.tier) {
                case ABSOLUTE -> super.getLimit(stack) * 8 * processes;
                case SUPREME -> super.getLimit(stack) * 16 * processes;
                case COSMIC -> super.getLimit(stack) * 32 * processes;
                case INFINITE -> super.getLimit(stack) * 64 * processes;
            };
        }
    }
}
