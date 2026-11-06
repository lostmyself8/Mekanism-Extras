package com.jerry.mekextras.mixin;

import mekanism.common.content.network.InventoryNetwork;
import mekanism.common.content.transporter.TransporterPathfinder.Pathfinder;

import net.minecraft.world.level.chunk.ChunkAccess;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Pathfinder.class, remap = false)
public abstract class MixinTransporterPathfinder {

    @Shadow
    @Final
    private InventoryNetwork network;

    @Unique
    private boolean mekextras$hasSubUnitCost;

    @Inject(method = "find", at = @At("HEAD"))
    private void mekextras$checkForSubUnitCost(Long2ObjectMap<ChunkAccess> chunkMap, CallbackInfoReturnable<Boolean> cir) {
        mekextras$hasSubUnitCost = network.getTransmitters().stream().anyMatch(transmitter -> transmitter.getCost() < 1);
    }

    @ModifyExpressionValue(method = "find", at = @At(value = "INVOKE", target = "Lmekanism/common/util/WorldUtils;distanceBetween(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)D", ordinal = 2, remap = false))
    private double mekextras$useZeroHeuristicForSubUnitCosts(double distance) {
        return mekextras$hasSubUnitCost ? 0 : distance;
    }
}
