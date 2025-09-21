package com.jerry.generator_extras.api.recipes.impl;

import com.jerry.generator_extras.common.genregistry.ExtraGenRecipeSerializers;
import com.jerry.generator_extras.common.recipe.ExtraGenRecipeType;
import com.jerry.generator_extras.api.recipes.PlasmaEvaporationRecipe;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient.*;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;

@NothingNullByDefault
public class PlasmaEvaporationIRecipe extends PlasmaEvaporationRecipe {

    public PlasmaEvaporationIRecipe(ResourceLocation id, GasStackIngredient inputGas, FluidStackIngredient inputFluid,
                                    GasStack outputGas, FluidStack outputFluid) {
        super(id, inputGas, inputFluid, outputGas, outputFluid);
    }

    @Override
    public RecipeType<?> getType() {
        return ExtraGenRecipeType.PLASMA_EVAPORATION.get();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ExtraGenRecipeSerializers.PLASMA_EVAPORATION.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return super.getToastSymbol();
    }

    @Override
    public String getGroup() {
        return super.getGroup();
    }
}
