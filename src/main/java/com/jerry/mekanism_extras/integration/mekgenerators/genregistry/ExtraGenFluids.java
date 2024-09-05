package com.jerry.mekanism_extras.integration.mekgenerators.genregistry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.registry.ExtraChemicalConstants;
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
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> FISSILE_FUEL = EXTRA_GEN_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.FISSILE_FUEL);

    public static void register(IEventBus eventBus) {
        EXTRA_GEN_FLUIDS.register(eventBus);
    }
}
