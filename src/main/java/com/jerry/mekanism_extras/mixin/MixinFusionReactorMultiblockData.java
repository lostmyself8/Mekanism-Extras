package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.api.ExtraNBTConstants;

import com.jerry.generator_extras.common.genregistry.ExtraGenGases;
import com.jerry.generator_extras.common.tile.plasma.IFusionPlasmaHolder;

import mekanism.api.Action;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.common.capabilities.chemical.multiblock.MultiblockChemicalTankBuilder;
import mekanism.common.lib.multiblock.MultiblockData;
import mekanism.common.util.NBTUtils;
import mekanism.generators.common.content.fusion.FusionReactorMultiblockData;
import mekanism.generators.common.tile.fusion.TileEntityFusionReactorBlock;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FusionReactorMultiblockData.class, remap = false)
@Implements(@Interface(iface = IFusionPlasmaHolder.class, prefix = "meke_iface$"))
public abstract class MixinFusionReactorMultiblockData extends MultiblockData {

    @Unique
    private IGasTank meke$plasmaTank;
    @Unique
    private static final long MAX_PLASMA = 100_000;
    @Unique
    private boolean meke$outputPlasma = false;

    @Shadow
    private int injectionRate;
    @Shadow
    @Final
    public static int MAX_INJECTION;

    public MixinFusionReactorMultiblockData(BlockEntity tile) {
        super(tile);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void meke$initPlasmaTank(TileEntityFusionReactorBlock tile, CallbackInfo ci) {
        gasTanks.add(meke$plasmaTank = MultiblockChemicalTankBuilder.GAS.output(this, ((IFusionPlasmaHolder) this)::getMaxPlasma, gas -> gas == ExtraGenGases.HELIUM_PLASMA.getChemical(), this));
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void meke$modifyFuelConsumption(Level world, CallbackInfoReturnable<Boolean> cir) {
        if (meke$outputPlasma) {
	        if (injectionRate != MAX_INJECTION) {
		        markDirty();
	        }
	        injectionRate = MAX_INJECTION;
        }
    }

    @Inject(method = "burnFuel", at = @At("TAIL"))
    private void meke$growPlasmaTank(CallbackInfoReturnable<Long> cir) {
        if (meke$plasmaTank.isEmpty()) {
            meke$plasmaTank.setStack(ExtraGenGases.HELIUM_PLASMA.getStack(MAX_INJECTION));
        } else {
            meke$plasmaTank.growStack(MAX_INJECTION, Action.EXECUTE);
        }
    }

    @Inject(method = "readUpdateTag", at = @At("TAIL"))
    private void meke$updateOutputPlasmaFromNBT(CompoundTag tag, CallbackInfo ci) {
        NBTUtils.setBooleanIfPresent(tag, ExtraNBTConstants.OUTPUT_PLASMA, ((IFusionPlasmaHolder) this)::setOutputPlasma);
    }

    @Inject(method = "writeUpdateTag", at = @At("TAIL"))
    private void meke$updateOutputPlasmaToNBT(CompoundTag tag, CallbackInfo ci) {
        tag.putBoolean(ExtraNBTConstants.OUTPUT_PLASMA, ((IFusionPlasmaHolder) this).canOutputPlasma());
    }

    @Unique
    public IGasTank meke_iface$getPlasmaTank() {
        return meke$plasmaTank;
    }

    @Unique
    public long meke_iface$getMaxPlasma() {
        return MAX_PLASMA;
    }

    @Unique
    public boolean meke_iface$canOutputPlasma() {
        return this.meke$outputPlasma;
    }

    @Unique
    public void meke_iface$setOutputPlasma(boolean b) {
        this.meke$outputPlasma = b;
    }
}
