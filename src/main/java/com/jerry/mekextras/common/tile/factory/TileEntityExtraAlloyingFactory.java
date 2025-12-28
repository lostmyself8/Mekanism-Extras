package com.jerry.mekextras.common.tile.factory;

import mekanism.api.inventory.IInventorySlot;
import mekanism.api.math.MathUtils;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.common.Mekanism;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.recipe.IMekanismRecipeTypeProvider;
import mekanism.common.recipe.lookup.cache.type.ItemInputCache;
import mekanism.common.tile.component.config.ConfigInfo;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.InventorySlotInfo;
import mekanism.common.upgrade.IUpgradeData;
import mekanism.common.util.InventoryUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import fr.iglee42.evolvedmekanism.interfaces.EMInputRecipeCache.IFindRecipes;
import fr.iglee42.evolvedmekanism.interfaces.EMInputRecipeCache.TripleItem;
import fr.iglee42.evolvedmekanism.interfaces.IGetEnergySlot;
import fr.iglee42.evolvedmekanism.interfaces.ThreeInputCachedRecipe;
import fr.iglee42.evolvedmekanism.interfaces.TripleItemRecipeLookupHandler;
import fr.iglee42.evolvedmekanism.recipes.AlloyerRecipe;
import fr.iglee42.evolvedmekanism.registries.EMRecipeType;
import fr.iglee42.evolvedmekanism.tiles.LimitedInputInventorySlot;
import fr.iglee42.evolvedmekanism.tiles.upgrade.AlloyerUpgradeData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class TileEntityExtraAlloyingFactory extends TileEntityExtraItemToItemFactory<AlloyerRecipe> implements TripleItemRecipeLookupHandler<AlloyerRecipe> {

    private static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_INPUT,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT,
            RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);
    private static final Set<RecipeError> GLOBAL_ERROR_TYPES = Set.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT);

    LimitedInputInventorySlot extraSlot;
    LimitedInputInventorySlot secondExtraSlot;

    private final IInputHandler<@NotNull ItemStack> extraInputHandler;
    private final IInputHandler<@NotNull ItemStack> secondExtraInputHandler;

    public TileEntityExtraAlloyingFactory(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, GLOBAL_ERROR_TYPES);

        ConfigInfo itemConfig = configComponent.getConfig(TransmissionType.ITEM);
        if (itemConfig != null) {
            itemConfig.addSlotInfo(DataType.EXTRA, new InventorySlotInfo(true, true, List.of(extraSlot, secondExtraSlot)));
        }

        extraInputHandler = InputHelper.getInputHandler(extraSlot, RecipeError.NOT_ENOUGH_SECONDARY_INPUT);
        secondExtraInputHandler = InputHelper.getInputHandler(secondExtraSlot, RecipeError.NOT_ENOUGH_SECONDARY_INPUT);
    }

    @Override
    protected boolean isCachedRecipeValid(@Nullable CachedRecipe<AlloyerRecipe> cached, @NotNull ItemStack stack) {
        if (cached != null) {
            AlloyerRecipe cachedRecipe = cached.getRecipe();
            return cachedRecipe.getMainInput().testType(stack) && (extraSlot.isEmpty() || cachedRecipe.getExtraInput().testType(extraSlot.getStack()));
        }
        return false;
    }

    @Override
    protected @Nullable AlloyerRecipe findRecipe(int process, @NotNull ItemStack fallbackInput, @NotNull IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot) {
        ItemStack extra = extraSlot.getStack();
        ItemStack secondExtra = secondExtraSlot.getStack();
        ItemStack output = outputSlot.getStack();
        return ((IFindRecipes<ItemStack, ItemStackIngredient, ItemStack, ItemStackIngredient, ItemStack, ItemStackIngredient, AlloyerRecipe, ItemInputCache<AlloyerRecipe>, ItemInputCache<AlloyerRecipe>, ItemInputCache<AlloyerRecipe>>) getRecipeType().getInputCache())
                .findTypeBasedRecipe(level, fallbackInput, extra, secondExtra, recipe -> InventoryUtils.areItemsStackable(recipe.getOutput(fallbackInput, extra, secondExtra), output));
    }

    @Override
    protected int getNeededInput(AlloyerRecipe recipe, ItemStack inputStack) {
        return MathUtils.clampToInt(recipe.getMainInput().getNeededAmount(inputStack));
    }

    @Override
    public boolean isItemValidForSlot(@NotNull ItemStack stack) {
        return containsRecipeA(stack);
    }

    @Override
    public boolean isValidInputItem(@NotNull ItemStack stack) {
        return containsRecipeA(stack);
    }

    @Override
    public @NotNull IMekanismRecipeTypeProvider<?, AlloyerRecipe, TripleItem<AlloyerRecipe>> getRecipeType() {
        return EMRecipeType.ALLOYING;
    }

    @Override
    public @Nullable AlloyerRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(inputHandlers[cacheIndex], extraInputHandler, secondExtraInputHandler);
    }

    @Override
    public @NotNull CachedRecipe<AlloyerRecipe> createNewCachedRecipe(@NotNull AlloyerRecipe recipe, int cacheIndex) {
        return ThreeInputCachedRecipe.alloyer(recipe, recheckAllRecipeErrors[cacheIndex], inputHandlers[cacheIndex], extraInputHandler, secondExtraInputHandler, outputHandlers[cacheIndex])
                .setErrorsChanged(errors -> errorTracker.onErrorsChanged(errors, cacheIndex))
                .setCanHolderFunction(this::canFunction)
                .setActive(active -> setActiveState(active, cacheIndex))
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setRequiredTicks(this::getTicksRequired)
                .setOnFinish(this::markForSave)
                .setBaselineMaxOperations(this::getOperationsPerTick)
                .setOperatingTicksChanged(operatingTicks -> progress[cacheIndex] = operatingTicks);
    }

    public void parseUpgradeData(HolderLookup.Provider provider, @NotNull IUpgradeData upgradeData) {
        if (upgradeData instanceof AlloyerUpgradeData data) {
            super.parseUpgradeData(provider, upgradeData);
            extraSlot.deserializeNBT(provider, data.extraSlot.serializeNBT(provider));
            secondExtraSlot.deserializeNBT(provider, data.secondaryExtraSlot.serializeNBT(provider));
        } else {
            Mekanism.logger.warn("Unhandled upgrade data.", new Throwable());
        }
    }

    public @NotNull AlloyerUpgradeData getUpgradeData(HolderLookup.Provider provider) {
        return new AlloyerUpgradeData(provider, redstone, getControlType(), getEnergyContainer(), progress, ((IGetEnergySlot) this).getEnergySlot(), extraSlot, secondExtraSlot, inputSlots, outputSlots, isSorting(), getComponents());
    }
}
