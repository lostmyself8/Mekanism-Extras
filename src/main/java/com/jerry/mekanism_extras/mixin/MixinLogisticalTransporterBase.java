package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.api.IMixinLogisticalTransporterBase;
import mekanism.common.content.network.InventoryNetwork;
import mekanism.common.content.network.transmitter.LogisticalTransporterBase;
import mekanism.common.content.network.transmitter.Transmitter;
import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import net.minecraft.core.BlockPos;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = LogisticalTransporterBase.class, remap = false)
public abstract class MixinLogisticalTransporterBase extends Transmitter<IItemHandler, InventoryNetwork, LogisticalTransporterBase> implements IMixinLogisticalTransporterBase {
    public MixinLogisticalTransporterBase(TileEntityTransmitter transmitterTile, TransmissionType... transmissionTypes) {
        super(transmitterTile, transmissionTypes);
    }

    @Shadow
    protected abstract void entityEntering(TransporterStack stack, int progress);

    @Shadow
    protected abstract boolean recalculate(int stackId, TransporterStack stack, BlockPos from);

    public void mekanismExtras$getEntering(TransporterStack stack, int progress) {
        entityEntering(stack, progress);
    }

    public boolean mekanismExtras$getRecalculate(int stackId, TransporterStack stack, BlockPos from) {
        return recalculate(stackId, stack, from);
    }
}
