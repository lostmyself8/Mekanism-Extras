package com.jerry.mekanism_extras.api.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import mekanism.api.JsonConstants;
import mekanism.api.SerializerHelper;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient.*;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.Mekanism;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Input: GasStack
 * <br>
 * Input: FluidStack
 * <br>
 * Fluid Output: FluidStack
 * <br>
 * Gas Output: GasStack
 *
 * @apiNote Plasma Evaporation Plant can process this recipe type.
 */
public abstract class PlasmaEvaporationRecipe
        extends MekanismRecipe
        implements BiPredicate<@NotNull FluidStack, @NotNull GasStack> {

    private final GasStackIngredient inputGas;
    private final FluidStackIngredient inputFluid;
    private final GasStack outputGas;
    private final FluidStack outputFluid;

    public PlasmaEvaporationRecipe(ResourceLocation id, GasStackIngredient inputGas, FluidStackIngredient inputFluid,
                                   GasStack outputGas, FluidStack outputFluid) {
        super(id);
        this.inputGas = Objects.requireNonNull(inputGas, "Input gas cannot be null.");
        this.inputFluid = Objects.requireNonNull(inputFluid, "Input fluid cannot be null.");
        Objects.requireNonNull(outputGas, "Output gas cannot be null");
        if (outputGas.isEmpty())
            throw new IllegalArgumentException("Output gas cannot be empty.");
        this.outputGas = outputGas.copy();
        Objects.requireNonNull(outputFluid, "Output gas cannot be null");
        if (outputFluid.isEmpty())
            throw new IllegalArgumentException("Output fluid cannot be empty.");
        this.outputFluid = outputFluid.copy();
    }

    public GasStackIngredient getInputGas() {
        return inputGas;
    }

    public FluidStackIngredient getInputFluid() {
        return inputFluid;
    }

    public GasStack getOutputGas() {
        return outputGas;
    }

    public FluidStack getOutputFluid() {
        return outputFluid;
    }

    @Override
    public boolean test(@NotNull FluidStack fluidStack, @NotNull GasStack gasStack) {
        return this.inputGas.test(gasStack) && this.inputFluid.test(fluidStack);
    }

    /**
     * For JEI, gets the output representations to display.
     *
     * @return Representation of the output, <strong>MUST NOT</strong> be modified.
     */
    public List<PlasmaEvaporationOutput> getOutputDefinition() {
        return Collections.singletonList(new PlasmaEvaporationOutput(this.outputGas, this.outputFluid));
    }

    /**
     * Gets a new output based on the given inputs.
     *
     * @param gas    Specific gas input.
     * @param liquid Specific fluid input.
     * @return New output.
     * @apiNote While we do not currently make use of the inputs, it is important
     * to support it and pass the proper value in case any addons define input, based
     * outputs where things like NBT may be different.
     * @implNote The passed in inputs should <strong>NOT</strong> be modified.
     */
    @Contract(value = "_, _ -> new", pure = true)
    @SuppressWarnings("unused")
    public PlasmaEvaporationOutput getOutput(GasStack gas, FluidStack liquid) {
        return new PlasmaEvaporationOutput(this.outputGas.copy(), this.outputFluid.copy());
    }

    @Override
    public boolean isIncomplete() {
        return inputFluid.hasNoMatchingInstances() || inputGas.hasNoMatchingInstances();
    }

    @Override
    public void logMissingTags() {
        inputFluid.logMissingTags();
        inputGas.logMissingTags();
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        inputGas.write(buffer);
        inputFluid.write(buffer);
        outputGas.writeToPacket(buffer);
        outputFluid.writeToPacket(buffer);
    }

    public record PlasmaEvaporationOutput(@NotNull GasStack gas, @NotNull FluidStack fluid) {

        public PlasmaEvaporationOutput {
            Objects.requireNonNull(gas, "Output gas cannot be null.");
            Objects.requireNonNull(fluid, "Output fluid cannot be null");
            if (gas.isEmpty())
                throw new IllegalArgumentException("Output gas cannot be empty.");
            if (fluid.isEmpty())
                throw new IllegalArgumentException("Output fluid cannot be empty");
        }
    }

    public static class Serializer<RECIPE extends PlasmaEvaporationRecipe>
            implements RecipeSerializer<RECIPE> {

        private final IFactory<RECIPE> factory;

        public Serializer(IFactory<RECIPE> factory) {
            this.factory = factory;
        }

        @NotNull
        @Override
        public RECIPE fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            JsonElement inputGas = GsonHelper.isArrayNode(json, JsonConstants.GAS_INPUT) ?
                    GsonHelper.getAsJsonArray(json, JsonConstants.GAS_INPUT) :
                    GsonHelper.getAsJsonObject(json, JsonConstants.GAS_INPUT);
            GasStackIngredient inputGasIngredient = IngredientCreatorAccess.gas().deserialize(inputGas);
            JsonElement inputFluid = GsonHelper.isArrayNode(json, JsonConstants.FLUID_INPUT) ?
                    GsonHelper.getAsJsonArray(json, JsonConstants.FLUID_INPUT) :
                    GsonHelper.getAsJsonObject(json, JsonConstants.FLUID_INPUT);
            FluidStackIngredient inputFluidIngredient = IngredientCreatorAccess.fluid().deserialize(inputFluid);

            GasStack gasOutput = SerializerHelper.getGasStack(json, JsonConstants.GAS_OUTPUT);
            FluidStack fluidOutput = SerializerHelper.getFluidStack(json, JsonConstants.FLUID_OUTPUT);
            if (gasOutput.isEmpty() || fluidOutput.isEmpty()) {
                throw new JsonSyntaxException("Plasma evaporation recipe outputs must not be empty.");
            }
            return this.factory.create(recipeId, inputGasIngredient, inputFluidIngredient, gasOutput, fluidOutput);
        }

        @Override
        public RECIPE fromNetwork(@NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf buffer) {
            try {
                GasStackIngredient inputGas = IngredientCreatorAccess.gas().read(buffer);
                FluidStackIngredient inputFluid = IngredientCreatorAccess.fluid().read(buffer);
                GasStack outputGas = GasStack.readFromPacket(buffer);
                FluidStack outputFluid = FluidStack.readFromPacket(buffer);
                return this.factory.create(recipeId, inputGas, inputFluid, outputGas, outputFluid);
            } catch (Exception e) {
                Mekanism.logger.error("Error reading plasma evaporation recipe from packet.", e);
                throw e;
            }
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buffer, @NotNull RECIPE recipe) {
            try {
                recipe.write(buffer);
            } catch (Exception e) {
                Mekanism.logger.error("Error writing plasma evaporation recipe to packet.", e);
                throw e;
            }
        }

        @FunctionalInterface
        public interface IFactory<RECIPE extends PlasmaEvaporationRecipe> {

            RECIPE create(ResourceLocation id, GasStackIngredient inputGas, FluidStackIngredient inputFluid, GasStack outputGas, FluidStack outputFluid);
        }
    }
}
