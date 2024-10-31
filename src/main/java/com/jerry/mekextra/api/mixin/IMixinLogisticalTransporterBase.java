package com.jerry.mekextra.api.mixin;

import mekanism.common.content.transporter.TransporterStack;

public interface IMixinLogisticalTransporterBase {
    void mekanismExtras$getEntity(TransporterStack stack, int progress);
}
