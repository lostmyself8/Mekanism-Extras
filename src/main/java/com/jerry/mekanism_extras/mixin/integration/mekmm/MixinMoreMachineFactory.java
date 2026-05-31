package com.jerry.mekanism_extras.mixin.integration.mekmm;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.integration.mekmm.registries.ExtraMoreMachineBlocks;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;

import mekanism.api.text.ILangEntry;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.tier.FactoryTier;

import com.jerry.mekmm.common.content.blocktype.MoreMachineFactory;
import com.jerry.mekmm.common.content.blocktype.MoreMachineMachine.MoreMachineFactoryMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(value = MoreMachineFactory.class, remap = false)
public abstract class MixinMoreMachineFactory extends BlockType {

    public MixinMoreMachineFactory(ILangEntry description) {
        super(description);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectFactoryUltimateToAbsolute(Supplier<?> tileEntityRegistrar, Supplier<?> containerRegistrar, MoreMachineFactoryMachine<?> origMachine,
                                                 FactoryTier tier, CallbackInfo ci) {
        if (tier == FactoryTier.ULTIMATE) {
            add(new ExtraAttributeUpgradeable(() -> ExtraMoreMachineBlocks.getExtraMoreMachineFactory(ExtraFactoryTier.ABSOLUTE, origMachine.getMMFactoryType())));
        }
    }
}
