package com.jerry.mekextras.mixin;

import com.jerry.genextras.common.registries.GenExtraChemicals;

import mekanism.api.IContentsListener;
import mekanism.api.chemical.ChemicalStack;
import mekanism.common.capabilities.chemical.VariableCapacityChemicalTank;
import mekanism.common.registries.MekanismChemicals;
import mekanism.generators.common.content.turbine.TurbineChemicalTank;
import mekanism.generators.common.content.turbine.TurbineMultiblockData;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Predicate;

/** Allows the industrial turbine to accept Mekanism Extras' polonium-containing steam. */
@Mixin(value = TurbineChemicalTank.class, remap = false)
public abstract class MixinTurbineChemicalTank extends VariableCapacityChemicalTank {

    protected MixinTurbineChemicalTank(TurbineMultiblockData multiblock, @Nullable IContentsListener listener) {
        super(multiblock::getSteamCapacity, multiblock.notExternalFormedBiPred(), multiblock.formedBiPred(),
                chemical -> chemical.is(MekanismChemicals.STEAM) || chemical.is(GenExtraChemicals.POLONIUM_CONTAINING_STEAM), null, listener);
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lmekanism/common/capabilities/chemical/VariableCapacityChemicalTank;<init>(Ljava/util/function/LongSupplier;Ljava/util/function/BiPredicate;Ljava/util/function/BiPredicate;Ljava/util/function/Predicate;Lmekanism/api/chemical/attribute/ChemicalAttributeValidator;Lmekanism/api/IContentsListener;)V"), index = 3)
    private static Predicate<ChemicalStack> mekextras$allowPoloniumContainingSteam(Predicate<ChemicalStack> validator) {
        return chemical -> chemical.is(MekanismChemicals.STEAM) || chemical.is(GenExtraChemicals.POLONIUM_CONTAINING_STEAM);
    }
}
