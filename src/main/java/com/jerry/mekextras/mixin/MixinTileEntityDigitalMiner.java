package com.jerry.mekextras.mixin;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.jerry.mekextras.api.mixin.IMixinMachineEnergyContainer;
import com.jerry.mekextras.common.util.ExtraUpgradeUtils;

import mekanism.api.Upgrade;
import mekanism.common.capabilities.energy.MinerEnergyContainer;
import mekanism.common.content.miner.MinerFilter;
import mekanism.common.lib.chunkloading.IChunkLoader;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.interfaces.IBoundingBlock;
import mekanism.common.tile.interfaces.IHasVisualization;
import mekanism.common.tile.interfaces.ITileFilterHolder;
import mekanism.common.tile.machine.TileEntityDigitalMiner;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = TileEntityDigitalMiner.class, remap = false)
public abstract class MixinTileEntityDigitalMiner extends TileEntityMekanism implements IChunkLoader, IBoundingBlock, ITileFilterHolder<MinerFilter<?>>, IHasVisualization, IMixinMachineEnergyContainer {

    public MixinTileEntityDigitalMiner(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Shadow
    public abstract MinerEnergyContainer getEnergyContainer();

    @ModifyReturnValue(method = "getDelay", at = @At(value = "RETURN"))
    public int mekextras$getDelay(int delayLength) {
        return upgradeComponent.isUpgradeInstalled(ExtraUpgrade.CREATIVE) ? 0 : delayLength;
    }

    @ModifyReturnValue(method = "getInfo", at = @At("RETURN"))
    private List<Component> mekextras$getInfo(List<Component> original, @NotNull Upgrade upgrade) {
        return ExtraUpgradeUtils.getExpScaledInfo(original, this, upgrade);
    }

    @Inject(method = "recalculateUpgrades", at = @At(value = "HEAD"))
    public void mekextras$recalculateUpgrades(Upgrade upgrade, CallbackInfo ci) {
        ((IMixinMachineEnergyContainer) getEnergyContainer()).mekextras$extraRecalculateUpgrades(upgrade);
    }
}
