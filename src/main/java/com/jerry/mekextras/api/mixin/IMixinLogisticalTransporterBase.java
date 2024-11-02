package com.jerry.mekextras.api.mixin;

import mekanism.common.content.transporter.TransporterStack;

public interface IMixinLogisticalTransporterBase {
    void mekanismExtras$getEntity(TransporterStack stack, int progress);
}
