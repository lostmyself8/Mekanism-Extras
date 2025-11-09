package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.common.ExtraTags;

import com.jerry.generator_extras.common.genregistry.ExtraGenFluids;
import com.jerry.generator_extras.common.genregistry.ExtraGenGases;

import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.math.FloatingLong;
import mekanism.api.math.MathUtils;
import mekanism.common.lib.multiblock.MultiblockData;
import mekanism.common.tags.MekanismTags;
import mekanism.generators.common.config.MekanismGeneratorsConfig;
import mekanism.generators.common.content.turbine.TurbineMultiblockData;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Predicate;

@Mixin(value = TurbineMultiblockData.class, remap = false)
public abstract class MixinTurbineMultiblockData extends MultiblockData {

    @Shadow
    public int condensers;
    @Shadow
    public IGasTank gasTank;

    @Unique
    private double mekanismExtras$rateMixin;

    public MixinTurbineMultiblockData(BlockEntity tile) {
        super(tile);
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lmekanism/common/capabilities/fluid/VariableCapacityFluidTank;output(Lmekanism/common/lib/multiblock/MultiblockData;Ljava/util/function/IntSupplier;Ljava/util/function/Predicate;Lmekanism/api/IContentsListener;)Lmekanism/common/capabilities/fluid/VariableCapacityFluidTank;"), index = 2)
    public Predicate<@NotNull FluidStack> TurbineMultiblockData(Predicate<@NotNull FluidStack> validator) {
        return fluid -> MekanismTags.Fluids.WATER_LOOKUP.contains(fluid.getFluid()) || ExtraTags.Fluids.LAZY_POLONIUM_CONTAINING_SOLUTION.contains(fluid.getFluid());
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(DD)D", shift = At.Shift.BY, ordinal = 1), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void getRate(Level world, CallbackInfoReturnable<Boolean> cir, boolean needsPacket, long stored, double flowRate, FloatingLong energyNeeded, FloatingLong energyMultiplier, double rate) {
        mekanismExtras$rateMixin = rate;
    }

    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lmekanism/api/fluid/IExtendedFluidTank;setStack(Lnet/minecraftforge/fluids/FluidStack;)V"), index = 0)
    public FluidStack tickMixin(FluidStack stack) {
        if (gasTank.getStack().getType() == ExtraGenGases.POLONIUM_CONTAINING_STEAM.getChemical()) {
            return new FluidStack(ExtraGenFluids.POLONIUM_CONTAINING_SOLUTION.getFluid(), Math.min(MathUtils.clampToInt(mekanismExtras$rateMixin), condensers * MekanismGeneratorsConfig.generators.condenserRate.get()) / 1000);
        }
        return new FluidStack(Fluids.WATER, Math.min(MathUtils.clampToInt(mekanismExtras$rateMixin), condensers * MekanismGeneratorsConfig.generators.condenserRate.get()));
    }
}
