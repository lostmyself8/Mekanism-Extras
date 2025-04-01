package com.jerry.mekanism_extras.common.inventory.slot;

import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.IChemicalHandler;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasHandler;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.inventory.slot.chemical.GasInventorySlot;
import mekanism.common.recipe.MekanismRecipeType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@NothingNullByDefault
public class AdvancedFactoryChemicalInventorySlot extends ChemicalInventorySlot<Gas, GasStack> {

    private static AdvancedFactoryTier isTier = AdvancedFactoryTier.ABSOLUTE;


    protected AdvancedFactoryChemicalInventorySlot(AdvancedFactoryTier tier, IChemicalTank chemicalTank, Supplier<Level> worldSupplier, Predicate<@NotNull ItemStack> canExtract, Predicate<@NotNull ItemStack> canInsert, Predicate<@NotNull ItemStack> validator, @Nullable IContentsListener listener, int x, int y) {
        super(chemicalTank, worldSupplier, canExtract, canInsert, canInsert, listener, x, y);
        isTier = tier;
    }

    /**
     * Gets the GasStack from ItemStack conversion, ignoring the size of the item stack.
     */
    private static GasStack getPotentialConversion(@Nullable Level world, ItemStack itemStack) {
        return getPotentialConversion(MekanismRecipeType.GAS_CONVERSION, world, itemStack, GasStack.EMPTY);
    }

    /**
     * Fills the tank from this item OR converts the given item to a gas
     */
    public static AdvancedFactoryChemicalInventorySlot fillOrConvert(AdvancedFactoryTier tier, IGasTank gasTank, Supplier<Level> worldSupplier, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(gasTank, "Gas tank cannot be null");
        Objects.requireNonNull(worldSupplier, "World supplier cannot be null");
        Function<ItemStack, GasStack> potentialConversionSupplier = stack -> getPotentialConversion(worldSupplier.get(), stack);
        return new AdvancedFactoryChemicalInventorySlot(tier, gasTank, worldSupplier, getFillOrConvertExtractPredicate(gasTank, GasInventorySlot::getCapability, potentialConversionSupplier),
                getFillOrConvertInsertPredicate(gasTank, GasInventorySlot::getCapability, potentialConversionSupplier), stack -> {
            if (stack.getCapability(Capabilities.GAS_HANDLER).isPresent()) {
                //Note: we mark all gas items as valid and have a more restrictive insert check so that we allow full tanks when they are done being filled
                return true;
            }
            //Allow gas conversion of items that have a gas that is valid
            GasStack gasConversion = getPotentialConversion(worldSupplier.get(), stack);
            return !gasConversion.isEmpty() && gasTank.isValid(gasConversion);
        }, listener, x, y);
    }

    @Override
    public int getLimit(ItemStack stack) {
        return !stack.isEmpty() && stack.getCapability(Capabilities.GAS_HANDLER).isPresent() ? stack.getMaxStackSize() : Item.MAX_STACK_SIZE * isTier.processes;
    }

    public static @Nullable IGasHandler getCapability(ItemStack stack) {
        return getCapability(stack, Capabilities.GAS_HANDLER);
    }

    @Override
    protected @Nullable IChemicalHandler<Gas, GasStack> getCapability() {
        return getCapability(current);
    }
}
