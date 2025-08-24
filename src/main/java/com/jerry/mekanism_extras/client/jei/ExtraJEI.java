package com.jerry.mekanism_extras.client.jei;

import com.jerry.generator_extras.common.ExtraGenLang;
import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.ExtraLang;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import com.jerry.mekanism_extras.common.registry.ExtraFluids;
import com.jerry.mekanism_extras.integration.Addons;
import com.jerry.generator_extras.common.genregistry.ExtraGenFluids;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.registries.MekanismBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
@NothingNullByDefault
public class ExtraJEI implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return MekanismExtras.rl("jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(ExtraFluids.RICH_NAQUADAH_LIQUID_FUEL.getFluidStack(FluidType.BUCKET_VOLUME), ForgeTypes.FLUID_STACK,
                ExtraLang.JEI_INFO_RICH_NAQUADAH_FUEL.translate());
        registration.addIngredientInfo(ExtraFluids.RICH_URANIUM_LIQUID_FUEL.getFluidStack(FluidType.BUCKET_VOLUME), ForgeTypes.FLUID_STACK,
                ExtraLang.JEI_INFO_RICH_URANIUM_FUEL.translate());
        if (Addons.MEKANISMGENERATORS.isLoaded()) {
            registration.addIngredientInfo(ExtraGenFluids.POLONIUM_CONTAINING_SOLUTION.getFluidStack(FluidType.BUCKET_VOLUME), ForgeTypes.FLUID_STACK,
                    ExtraGenLang.JEI_INFO_POLONIUM_CONTAINING_SOLUTION.translate());
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        // 只是添加JEI的侧面栏的显示
        ExtraCatalystRegistryHelper.register(registry, MekanismBlocks.ENRICHMENT_CHAMBER);
        ExtraCatalystRegistryHelper.register(registry, MekanismBlocks.CRUSHER);
        ExtraCatalystRegistryHelper.register(registry, MekanismBlocks.COMBINER);
        ExtraCatalystRegistryHelper.register(registry, MekanismBlocks.PURIFICATION_CHAMBER, MekanismJEIRecipeType.GAS_CONVERSION);
        ExtraCatalystRegistryHelper.register(registry, MekanismBlocks.OSMIUM_COMPRESSOR, MekanismJEIRecipeType.GAS_CONVERSION);
        ExtraCatalystRegistryHelper.register(registry, MekanismBlocks.CHEMICAL_INJECTION_CHAMBER, MekanismJEIRecipeType.GAS_CONVERSION);
        ExtraCatalystRegistryHelper.register(registry, MekanismBlocks.PRECISION_SAWMILL);
        ExtraCatalystRegistryHelper.register(registry, MekanismBlocks.METALLURGIC_INFUSER, MekanismJEIRecipeType.INFUSION_CONVERSION);
        ExtraCatalystRegistryHelper.registerRecipeItem(registry, MekanismBlocks.ENERGIZED_SMELTER, MekanismJEIRecipeType.SMELTING, RecipeTypes.SMELTING);

        ExtraCatalystRegistryHelper.register(registry, MekanismJEIRecipeType.ENERGY_CONVERSION, ExtraBlock.ABSOLUTE_ENERGY_CUBE, ExtraBlock.SUPREME_ENERGY_CUBE,
                ExtraBlock.COSMIC_ENERGY_CUBE, ExtraBlock.INFINITE_ENERGY_CUBE);
    }
}
