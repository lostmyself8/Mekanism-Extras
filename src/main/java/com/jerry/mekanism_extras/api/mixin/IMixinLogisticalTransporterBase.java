package com.jerry.mekanism_extras.api.mixin;

import mekanism.common.content.transporter.TransporterStack;
import net.minecraft.core.BlockPos;

public interface IMixinLogisticalTransporterBase {
    void mekanismExtras$getEntity(TransporterStack stack, int progress);

    boolean mekanismExtras$getRecalculate(int stackId, TransporterStack stack, BlockPos from);
}
