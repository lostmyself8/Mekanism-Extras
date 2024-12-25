package com.jerry.mekanism_extras.mixin;

import com.jerry.generator_extras.common.genregistry.ExtraGenGases;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.chemical.gas.Gas;
import mekanism.common.capabilities.chemical.multiblock.MultiblockChemicalTankBuilder;
import mekanism.common.registries.MekanismGases;
import mekanism.generators.common.content.turbine.TurbineGasTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.BiPredicate;
import java.util.function.LongSupplier;
import java.util.function.Predicate;

@Mixin(value = TurbineGasTank.class, remap = false)
public abstract class MixinTurbineGasTank extends MultiblockChemicalTankBuilder.MultiblockGasTank {

    protected MixinTurbineGasTank(LongSupplier capacity, BiPredicate<@NotNull Gas, @NotNull AutomationType> canExtract, BiPredicate<@NotNull Gas, @NotNull AutomationType> canInsert, Predicate<@NotNull Gas> validator, @Nullable ChemicalAttributeValidator attributeValidator, @Nullable IContentsListener listener) {
        super(capacity, canExtract, canInsert, validator, attributeValidator, listener);
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lmekanism/common/capabilities/chemical/multiblock/MultiblockChemicalTankBuilder$MultiblockGasTank;<init>(Ljava/util/function/LongSupplier;Ljava/util/function/BiPredicate;Ljava/util/function/BiPredicate;Ljava/util/function/Predicate;Lmekanism/api/chemical/attribute/ChemicalAttributeValidator;Lmekanism/api/IContentsListener;)V"), index = 3)
    private static Predicate<@NotNull Gas> TurbineGasTank(Predicate<@NotNull Gas> validator) {
        return gas -> gas == MekanismGases.STEAM.getChemical() || gas == ExtraGenGases.POLONIUM_CONTAINING_STEAM.getChemical();
    }
}
