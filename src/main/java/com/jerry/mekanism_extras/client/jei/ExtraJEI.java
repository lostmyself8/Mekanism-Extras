package com.jerry.mekanism_extras.client.jei;

import com.jerry.generator_extras.common.ExtraGenLang;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.ExtraLang;
import com.jerry.mekanism_extras.common.registry.ExtraFluids;
import com.jerry.mekanism_extras.integration.Addons;
import com.jerry.generator_extras.common.genregistry.ExtraGenFluids;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class ExtraJEI implements IModPlugin {
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
}
