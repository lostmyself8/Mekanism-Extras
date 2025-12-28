package com.jerry.mekextras.mixin;

import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;

import mekanism.api.text.ILangEntry;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.content.blocktype.Factory;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.tier.FactoryTier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(value = Factory.class, remap = false)
public abstract class MixinFactory extends BlockType {

    public MixinFactory(ILangEntry description) {
        super(description);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectFactoryUltimateToAbsolute(Supplier<?> tileEntityRegistrar, Supplier<?> containerRegistrar, Machine.FactoryMachine<?> origMachine, FactoryTier tier, CallbackInfo ci) {
        if (tier == FactoryTier.ULTIMATE) {
            add(new ExtraAttributeUpgradeable(() -> ExtraBlocks.getExtraFactory(ExtraFactoryTier.ABSOLUTE, origMachine.getFactoryType())));
        }
    }
}
