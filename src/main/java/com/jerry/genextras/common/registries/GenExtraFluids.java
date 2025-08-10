package com.jerry.genextras.common.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.ExtraChemicalConstants;
import mekanism.common.Mekanism;
import mekanism.common.registration.impl.FluidDeferredRegister;
import mekanism.common.registration.impl.FluidRegistryObject;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public class GenExtraFluids {

    private GenExtraFluids() {
    }

    public static final FluidDeferredRegister GEN_EXTRA_FLUIDS = new FluidDeferredRegister(MekanismExtras.MOD_ID);

    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, LiquidBlock, BucketItem> POLONIUM_CONTAINING_SOLUTION = GEN_EXTRA_FLUIDS.register("polonium_containing_solution", properties -> properties.temperature(330).density(5_230),
            renderProperties -> renderProperties.texture(Mekanism.rl("liquid/liquid"), Mekanism.rl("liquid/steam_flow")).tint(0xFF47c3a2));
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, LiquidBlock, BucketItem> POLONIUM208 = GEN_EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.POLONIUM208);

}
