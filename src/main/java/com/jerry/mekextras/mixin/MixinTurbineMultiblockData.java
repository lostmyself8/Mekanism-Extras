package com.jerry.mekextras.mixin;

import com.jerry.genextras.common.registries.GenExtraChemicals;
import com.jerry.genextras.common.registries.GenExtraFluids;

import mekanism.api.chemical.IChemicalTank;
import mekanism.api.math.MathUtils;
import mekanism.generators.common.config.MekanismGeneratorsConfig;
import mekanism.generators.common.content.turbine.TurbineMultiblockData;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;

import com.llamalad7.mixinextras.sugar.Local;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Predicate;

/** Extends turbine condensate handling and derives output from the actual processed steam rate. */
@Mixin(value = TurbineMultiblockData.class, remap = false)
public abstract class MixinTurbineMultiblockData {

    @Shadow
    public IChemicalTank chemicalTank;

    @Shadow
    public int condensers;

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lmekanism/common/capabilities/fluid/VariableCapacityFluidTank;output(Lmekanism/common/lib/multiblock/MultiblockData;Ljava/util/function/IntSupplier;Ljava/util/function/Predicate;Lmekanism/api/IContentsListener;)Lmekanism/common/capabilities/fluid/VariableCapacityFluidTank;"), index = 2)
    private Predicate<@NotNull FluidStack> mekextras$allowPoloniumContainingSolution(Predicate<@NotNull FluidStack> validator) {
        return fluid -> fluid.is(FluidTags.WATER) || fluid.is(GenExtraFluids.POLONIUM_CONTAINING_SOLUTION.get());
    }

    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lmekanism/api/fluid/IExtendedFluidTank;insert(Lnet/neoforged/neoforge/fluids/FluidStack;Lmekanism/api/Action;Lmekanism/api/AutomationType;)Lnet/neoforged/neoforge/fluids/FluidStack;"), index = 0)
    private FluidStack mekextras$createCondensate(@NotNull FluidStack original, @Local(name = "rate") double rate) {
        int amount = Math.min(MathUtils.clampToInt(rate), condensers * MekanismGeneratorsConfig.generators.condenserRate.get());
        if (chemicalTank.getStack().getChemical() == GenExtraChemicals.POLONIUM_CONTAINING_STEAM.get()) {
            return new FluidStack(GenExtraFluids.POLONIUM_CONTAINING_SOLUTION.get(), amount / 1_000);
        }
        return new FluidStack(Fluids.WATER, amount);
    }
}
