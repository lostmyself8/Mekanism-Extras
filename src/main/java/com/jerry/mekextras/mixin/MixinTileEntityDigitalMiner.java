package com.jerry.mekextras.mixin;

import com.jerry.mekextras.api.ExtraUpgrade;
import mekanism.api.Upgrade;
import mekanism.api.energy.IEnergyContainer;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.content.miner.MinerFilter;
import mekanism.common.lib.chunkloading.IChunkLoader;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.interfaces.IBoundingBlock;
import mekanism.common.tile.interfaces.IHasVisualization;
import mekanism.common.tile.interfaces.ITileFilterHolder;
import mekanism.common.tile.machine.TileEntityDigitalMiner;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityDigitalMiner.class, remap = false)
public abstract class MixinTileEntityDigitalMiner extends TileEntityMekanism implements IChunkLoader, IBoundingBlock, ITileFilterHolder<MinerFilter<?>>, IHasVisualization {

    @Shadow
    private int delayLength;

    public MixinTileEntityDigitalMiner(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Inject(method = "getDelay", at = @At(value = "RETURN"), cancellable = true)
    public void getDelay(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(upgradeComponent.isUpgradeInstalled(ExtraUpgrade.CREATIVE) ? 0 : delayLength);
    }

    @Inject(method = "recalculateUpgrades", at = @At(value = "TAIL"))
    public void recalculateUpgrades(Upgrade upgrade, CallbackInfo ci) {
        if (upgrade == ExtraUpgrade.CREATIVE) {
            for (IEnergyContainer energyContainer : getEnergyContainers(null)) {
                if (energyContainer instanceof MachineEnergyContainer<?> machineEnergy) {
                    machineEnergy.updateMaxEnergy();
                    machineEnergy.setEnergy(Long.MAX_VALUE);
                }
            }
        }
    }
}
