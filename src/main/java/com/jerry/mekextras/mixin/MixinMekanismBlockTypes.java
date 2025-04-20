package com.jerry.mekextras.mixin;

import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekextras.common.registry.ExtraBlocks;
import mekanism.api.tier.BaseTier;
import mekanism.api.tier.ITier;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlockTypes;
import mekanism.common.tier.BinTier;
import mekanism.common.tier.ChemicalTankTier;
import mekanism.common.tier.EnergyCubeTier;
import mekanism.common.tier.FluidTankTier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(value = MekanismBlockTypes.class, remap = false)
public abstract class MixinMekanismBlockTypes {
    @Inject(method = "createBin", at = @At("RETURN"))
    private static void injectBinUltimateToAbsolute(BinTier tier, Supplier<?> tile, Supplier<?> upgradeBlock, CallbackInfoReturnable<Machine<?>> cir) {
        mekanism_extras$addUltimateToAbsolute(tier, cir.getReturnValue(), () -> ExtraBlocks.ABSOLUTE_BIN);
    }

    @Inject(method = "createEnergyCube", at = @At("RETURN"))
    private static void injectEnergyCubeUltimateToAbsolute(EnergyCubeTier tier, Supplier<?> tile, Supplier<?> upgradeBlock, CallbackInfoReturnable<Machine<?>> cir) {
        mekanism_extras$addUltimateToAbsolute(tier, cir.getReturnValue(), () -> ExtraBlocks.ABSOLUTE_ENERGY_CUBE);
    }

    @Inject(method = "createFluidTank", at = @At("RETURN"))
    private static void injectFluidTankUltimateToAbsolute(FluidTankTier tier, Supplier<?> tile, Supplier<?> upgradeBlock, CallbackInfoReturnable<Machine<?>> cir) {
        mekanism_extras$addUltimateToAbsolute(tier, cir.getReturnValue(), () -> ExtraBlocks.ABSOLUTE_FLUID_TANK);
    }

    @Inject(method = "createChemicalTank", at = @At("RETURN"))
    private static void injectChemicalTankUltimateToAbsolute(ChemicalTankTier tier, Supplier<?> tile, Supplier<?> upgradeBlock, CallbackInfoReturnable<Machine<?>> cir) {
        mekanism_extras$addUltimateToAbsolute(tier, cir.getReturnValue(), () -> ExtraBlocks.ABSOLUTE_CHEMICAL_TANK);
    }

    @Unique
    private static void mekanism_extras$addUltimateToAbsolute(ITier tier, BlockType blockType, Supplier<BlockRegistryObject<?, ?>> upgradeBlock) {
        if (tier.getBaseTier() == BaseTier.ULTIMATE) {
            blockType.add(new ExtraAttributeUpgradeable(upgradeBlock));
        }
    }
}
