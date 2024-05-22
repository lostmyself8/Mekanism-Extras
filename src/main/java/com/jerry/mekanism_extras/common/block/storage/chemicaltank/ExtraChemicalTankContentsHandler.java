package com.jerry.mekanism_extras.common.block.storage.chemicaltank;

import mekanism.api.NBTConstants;
import mekanism.api.chemical.merged.MergedChemicalTank;
import mekanism.common.capabilities.DynamicHandler;
import mekanism.common.capabilities.chemical.dynamic.DynamicChemicalHandler;
import mekanism.common.capabilities.merged.MergedTankContentsHandler;

import java.util.Objects;

public class ExtraChemicalTankContentsHandler extends MergedTankContentsHandler<MergedChemicalTank> {
    public static ExtraChemicalTankContentsHandler create(CTTier tier) {
        Objects.requireNonNull(tier, "Chemical tank tier cannot be null");
        return new ExtraChemicalTankContentsHandler(tier);
    }

    private ExtraChemicalTankContentsHandler(CTTier tier) {
        mergedTank = MergedChemicalTank.create(
                new ExtraChemicalTankRateLimitChemicalTank.GasTankRateLimitChemicalTank(tier, gasHandler = new DynamicChemicalHandler.DynamicGasHandler(side -> gasTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE, DynamicHandler.InteractPredicate.ALWAYS_TRUE,
                        () -> onContentsChanged(NBTConstants.GAS_TANKS, gasTanks))),
                new ExtraChemicalTankRateLimitChemicalTank.InfusionTankRateLimitChemicalTank(tier, infusionHandler = new DynamicChemicalHandler.DynamicInfusionHandler(side -> infusionTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE,
                        DynamicHandler.InteractPredicate.ALWAYS_TRUE, () -> onContentsChanged(NBTConstants.INFUSION_TANKS, infusionTanks))),
                new ExtraChemicalTankRateLimitChemicalTank.PigmentTankRateLimitChemicalTank(tier, pigmentHandler = new DynamicChemicalHandler.DynamicPigmentHandler(side -> pigmentTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE,
                        DynamicHandler.InteractPredicate.ALWAYS_TRUE, () -> onContentsChanged(NBTConstants.PIGMENT_TANKS, pigmentTanks))),
                new ExtraChemicalTankRateLimitChemicalTank.SlurryTankRateLimitChemicalTank(tier, slurryHandler = new DynamicChemicalHandler.DynamicSlurryHandler(side -> slurryTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE,
                        DynamicHandler.InteractPredicate.ALWAYS_TRUE, () -> onContentsChanged(NBTConstants.SLURRY_TANKS, slurryTanks)))
        );
    }
}
