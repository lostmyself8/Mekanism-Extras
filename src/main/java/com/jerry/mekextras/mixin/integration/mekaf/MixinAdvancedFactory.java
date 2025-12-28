package com.jerry.mekextras.mixin.integration.mekaf;

import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;

import mekanism.api.text.ILangEntry;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.tier.FactoryTier;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactory;
import com.jerry.mekmm.common.content.blocktype.MoreMachineMachine.MoreMachineFactoryMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(value = AdvancedFactory.class, remap = false)
public abstract class MixinAdvancedFactory extends BlockType {

    public MixinAdvancedFactory(ILangEntry description) {
        super(description);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectFactoryUltimateToAbsolute(Supplier<?> tileEntityRegistrar, Supplier<?> containerRegistrar, MoreMachineFactoryMachine<?> origMachine, FactoryTier tier, CallbackInfo ci) {
        if (tier == FactoryTier.ULTIMATE) {
            add(new ExtraAttributeUpgradeable(() -> ExtraAdvancedFactoryBlocks.getAdvancedFactory(ExtraFactoryTier.ABSOLUTE, origMachine.getAdvancedFactoryType())));
        }
    }
}
