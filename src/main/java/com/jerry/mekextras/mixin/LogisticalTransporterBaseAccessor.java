package com.jerry.mekextras.mixin;

import mekanism.common.content.network.transmitter.LogisticalTransporterBase;
import mekanism.common.content.transporter.TransporterStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = LogisticalTransporterBase.class, remap = false)
public interface LogisticalTransporterBaseAccessor {

    @Invoker("entityEntering")
    void mekextras$entityEntering(TransporterStack stack, int progress);
}
