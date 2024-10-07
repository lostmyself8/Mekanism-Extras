package com.jerry.generator_extras.common.genregistry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.registry.ExtraChemicalConstants;
import mekanism.common.Mekanism;
import mekanism.common.registration.impl.FluidDeferredRegister;
import mekanism.common.registration.impl.FluidRegistryObject;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class ExtraGenFluids {

    public ExtraGenFluids() {

    }
    public static final FluidDeferredRegister EXTRA_GEN_FLUIDS = new FluidDeferredRegister(MekanismExtras.MODID);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> POLONIUM_CONTAINING_SOLUTION = EXTRA_GEN_FLUIDS.register("polonium_containing_solution", properties -> properties.temperature(373).density(0),
            renderProperties -> renderProperties.texture(Mekanism.rl("liquid/liquid"), Mekanism.rl("liquid/steam_flow")).tint(0xFF47c3a2));
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> POLONIUM208 = EXTRA_GEN_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.POLONIUM208);

    public static void register(IEventBus eventBus) {
        EXTRA_GEN_FLUIDS.register(eventBus);
    }
}
