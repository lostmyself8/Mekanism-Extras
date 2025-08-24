package com.jerry.mekanism_extras.common.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import mekanism.common.registration.impl.FluidDeferredRegister;
import mekanism.common.registration.impl.FluidRegistryObject;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class ExtraFluids {
    private ExtraFluids() {

    }

    public static final FluidDeferredRegister EXTRA_FLUIDS = new FluidDeferredRegister(MekanismExtras.MODID);

    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> NAQUADAH_TETRAFLUORIDE = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.NAQUADAH_HEXAFLUORIDE);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> FLUORINATED_NAQUADAH_URANIUM_FUEL = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.FLUORINATED_NAQUADAH_URANIUM_FUEL);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> RICH_NAQUADAH_LIQUID_FUEL = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.RICH_NAQUADAH_FUEL);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> RICH_URANIUM_LIQUID_FUEL = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.RICH_URANIUM_FUEL);
    public static void register(IEventBus eventBus) {
        EXTRA_FLUIDS.register(eventBus);
    }
}
