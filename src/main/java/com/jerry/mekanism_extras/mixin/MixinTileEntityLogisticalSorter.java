package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityLogisticalTransporterBase;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.text.EnumColor;
import mekanism.common.content.network.transmitter.LogisticalTransporterBase;
import mekanism.common.content.transporter.SorterFilter;
import mekanism.common.lib.inventory.TransitRequest;
import mekanism.common.tile.TileEntityLogisticalSorter;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.interfaces.ISustainedData;
import mekanism.common.tile.interfaces.ITileFilterHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityLogisticalSorter.class, remap = false)
public abstract class MixinTileEntityLogisticalSorter extends TileEntityMekanism implements ISustainedData, ITileFilterHolder<SorterFilter<?>> {

    @Shadow
    private boolean roundRobin;

    public MixinTileEntityLogisticalSorter(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Inject(method = "emitItemToTransporter", at = @At(value = "HEAD"), cancellable = true)
    private void emitItemToTransporter(BlockEntity front, TransitRequest request, EnumColor filterColor, int min, CallbackInfoReturnable<TransitRequest.TransitResponse> cir) {
        if (front instanceof ExtraTileEntityLogisticalTransporterBase transporterBase) {
            LogisticalTransporterBase transporter = transporterBase.getTransmitter();
            if (roundRobin) {
                cir.setReturnValue(transporter.insertRR((TileEntityLogisticalSorter) (Object) this, request, filterColor, true, min));
                //必须要return不然代码还会往后执行？？？
                return;
            }
            cir.setReturnValue(transporter.insert((TileEntityLogisticalSorter) (Object) this, request, filterColor, true, min));
        }
    }
}
