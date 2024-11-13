package com.jerry.mekanism_extras.client.jei;

import com.jerry.generator_extras.common.ExtraGenLang;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.ExtraLang;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import com.jerry.mekanism_extras.common.registry.ExtraFluids;
import com.jerry.mekanism_extras.common.registry.ExtraItem;
import com.jerry.mekanism_extras.integration.Addons;
import com.jerry.generator_extras.common.genregistry.ExtraGenFluids;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalHandler;
import mekanism.api.energy.IStrictEnergyHandler;
import mekanism.api.math.FloatingLong;
import mekanism.api.providers.IItemProvider;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.util.RegistryUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

@JeiPlugin
public class ExtraJEI implements IModPlugin {

    private static final IIngredientSubtypeInterpreter<ItemStack> MEKANISM_NBT_INTERPRETER = (stack, context) -> {
        if (context == UidContext.Ingredient && stack.hasTag()) {
            String nbtRepresentation = getChemicalComponent(stack, Capabilities.GAS_HANDLER);
            nbtRepresentation = addInterpretation(nbtRepresentation, getChemicalComponent(stack, Capabilities.INFUSION_HANDLER));
            nbtRepresentation = addInterpretation(nbtRepresentation, getChemicalComponent(stack, Capabilities.PIGMENT_HANDLER));
            nbtRepresentation = addInterpretation(nbtRepresentation, getChemicalComponent(stack, Capabilities.SLURRY_HANDLER));
            nbtRepresentation = addInterpretation(nbtRepresentation, getFluidComponent(stack));
            nbtRepresentation = addInterpretation(nbtRepresentation, getEnergyComponent(stack));
            return nbtRepresentation;
        }
        return IIngredientSubtypeInterpreter.NONE;
    };

    private static String addInterpretation(String nbtRepresentation, String component) {
        return nbtRepresentation.isEmpty() ? component : nbtRepresentation + ":" + component;
    }

    private static String getChemicalComponent(ItemStack stack, Capability<? extends IChemicalHandler<?, ?>> capability) {
        Optional<? extends IChemicalHandler<?, ?>> cap = stack.getCapability(capability).resolve();
        if (cap.isPresent()) {
            IChemicalHandler<?, ?> handler = cap.get();
            String component = "";
            for (int tank = 0, tanks = handler.getTanks(); tank < tanks; tank++) {
                ChemicalStack<?> chemicalStack = handler.getChemicalInTank(tank);
                if (!chemicalStack.isEmpty()) {
                    component = addInterpretation(component, chemicalStack.getTypeRegistryName().toString());
                } else if (tanks > 1) {
                    component = addInterpretation(component, "empty");
                }
            }
            return component;
        }
        return IIngredientSubtypeInterpreter.NONE;
    }

    private static String getFluidComponent(ItemStack stack) {
        Optional<IFluidHandlerItem> cap = FluidUtil.getFluidHandler(stack).resolve();
        if (cap.isPresent()) {
            IFluidHandlerItem handler = cap.get();
            String component = "";
            for (int tank = 0, tanks = handler.getTanks(); tank < tanks; tank++) {
                FluidStack fluidStack = handler.getFluidInTank(tank);
                if (!fluidStack.isEmpty()) {
                    component = addInterpretation(component, RegistryUtils.getName(fluidStack.getFluid()).toString());
                } else if (tanks > 1) {
                    component = addInterpretation(component, "empty");
                }
            }
            return component;
        }
        return IIngredientSubtypeInterpreter.NONE;
    }

    private static String getEnergyComponent(ItemStack stack) {
        Optional<IStrictEnergyHandler> capability = stack.getCapability(Capabilities.STRICT_ENERGY).resolve();
        if (capability.isPresent()) {
            IStrictEnergyHandler energyHandlerItem = capability.get();
            String component = "";
            int containers = energyHandlerItem.getEnergyContainerCount();
            for (int container = 0; container < containers; container++) {
                FloatingLong neededEnergy = energyHandlerItem.getNeededEnergy(container);
                if (neededEnergy.isZero()) {
                    component = addInterpretation(component, "filled");
                } else if (containers > 1) {
                    component = addInterpretation(component, "empty");
                }
            }
            return component;
        }
        return IIngredientSubtypeInterpreter.NONE;
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return MekanismExtras.rl("jei_plugin");
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        registration.addIngredientInfo(ExtraFluids.RICH_SILICON_LIQUID_FUEL.getFluidStack(FluidType.BUCKET_VOLUME), ForgeTypes.FLUID_STACK,
                ExtraLang.JEI_INFO_RICH_SILICON_FUEL.translate());
        registration.addIngredientInfo(ExtraFluids.RICH_URANIUM_LIQUID_FUEL.getFluidStack(FluidType.BUCKET_VOLUME), ForgeTypes.FLUID_STACK,
                ExtraLang.JEI_INFO_RICH_URANIUM_FUEL.translate());
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            registration.addIngredientInfo(ExtraGenFluids.POLONIUM_CONTAINING_SOLUTION.getFluidStack(FluidType.BUCKET_VOLUME), ForgeTypes.FLUID_STACK,
                    ExtraGenLang.JEI_INFO_POLONIUM_CONTAINING_SOLUTION.translate());
        }
    }

    public static void registerItemSubtypes(ISubtypeRegistration registry, List<? extends IItemProvider> itemProviders) {
        for (IItemProvider itemProvider : itemProviders) {
            //Handle items
            ItemStack itemStack = itemProvider.getItemStack();
            if (itemStack.getCapability(Capabilities.STRICT_ENERGY).isPresent() || itemStack.getCapability(Capabilities.GAS_HANDLER).isPresent() ||
                    itemStack.getCapability(Capabilities.INFUSION_HANDLER).isPresent() || itemStack.getCapability(Capabilities.PIGMENT_HANDLER).isPresent() ||
                    itemStack.getCapability(Capabilities.SLURRY_HANDLER).isPresent() || FluidUtil.getFluidHandler(itemStack).isPresent()) {
                registry.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, itemProvider.asItem(), MEKANISM_NBT_INTERPRETER);
            }
        }
    }

    @Override
    public void registerItemSubtypes(@NotNull ISubtypeRegistration registry) {
        registerItemSubtypes(registry, ExtraItem.EXTRA_ITEM.getAllItems());
        registerItemSubtypes(registry, ExtraBlock.EXTRA_BLOCK.getAllBlocks());
    }
}
