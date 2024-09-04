package com.jerry.mekanism_extras.registry;

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

    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> SILICON_TETRAFLUORIDE = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.SILICON_TETRAFLUORIDE);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> FLUORINATED_SILICON_URANIUM_FUEL = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.FLUORINATED_SILICON_URANIUM_FUEL);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> RICH_SILICON_LIQUID_FUEL = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.RICH_SILICON_LIQUID_FUEL);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> RICH_URANIUM_LIQUID_FUEL = EXTRA_FLUIDS.registerLiquidChemical(ExtraChemicalConstants.RICH_URANIUM_LIQUID_FUEL);
    public static void register(IEventBus eventBus) {
        EXTRA_FLUIDS.register(eventBus);
    }
}
