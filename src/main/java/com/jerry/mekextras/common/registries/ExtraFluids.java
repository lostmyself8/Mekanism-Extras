package com.jerry.mekextras.common.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.ExtraChemicalConstants;
import mekanism.common.registration.impl.FluidDeferredRegister;
import mekanism.common.registration.impl.FluidRegistryObject;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public class ExtraFluids {

    private ExtraFluids() {
    }

    public static final FluidDeferredRegister EXTRA_FLUIDS = new FluidDeferredRegister(MekanismExtras.MOD_ID);

    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, LiquidBlock, BucketItem> NAQUADAH_HEXAFLUORIDE = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.NAQUADAH_HEXAFLUORIDE);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, LiquidBlock, BucketItem> FLUORINATED_NAQUADAH_URANIUM_FUEL = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.FLUORINATED_NAQUADAH_URANIUM_FUEL);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, LiquidBlock, BucketItem> RICH_NAQUADAH_FUEL = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.RICH_NAQUADAH_FUEL);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, BaseFlowingFluid.Source, BaseFlowingFluid.Flowing, LiquidBlock, BucketItem> RICH_URANIUM_FUEL = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.RICH_URANIUM_FUEL);

    public static void register(IEventBus eventBus) {
        EXTRA_FLUIDS.register(eventBus);
    }
}
