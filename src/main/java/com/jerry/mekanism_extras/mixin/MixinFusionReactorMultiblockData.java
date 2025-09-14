package com.jerry.mekanism_extras.mixin;

import com.jerry.generator_extras.common.genregistry.ExtraGenGases;
import com.jerry.mekanism_extras.api.ExtraNBTConstants;
import mekanism.api.Action;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.common.capabilities.chemical.multiblock.MultiblockChemicalTankBuilder;
import mekanism.common.inventory.container.sync.dynamic.ContainerSync;
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
@Implements(@Interface(iface = IFusionPlasmaHolder.class, prefix = "meke$"))
public abstract class MixinFusionReactorMultiblockData extends MultiblockData {

    @Unique
    @ContainerSync
    private IGasTank meke$plasmaTank;
    @Unique
    private static final long MAX_PLASMA = 100_000;
    @Unique
    @ContainerSync
    private boolean meke$outputPlasma = false;

    @Shadow private int injectionRate;
    @Shadow @Final public static int MAX_INJECTION;

    public MixinFusionReactorMultiblockData(BlockEntity tile) {
        super(tile);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void initPlasmaTank(TileEntityFusionReactorBlock tile, CallbackInfo ci) {
        gasTanks.add(meke$plasmaTank = MultiblockChemicalTankBuilder.GAS.output(this, this::meke$getMaxPlasma, gas -> gas == ExtraGenGases.HELIUM_PLASMA.getChemical(), this));
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void modifyFuelConsumption(Level world, CallbackInfoReturnable<Boolean> cir) {
        if (injectionRate != MAX_INJECTION) {
            markDirty();
        }
        injectionRate = MAX_INJECTION;
    }

    @Inject(method = "burnFuel", at = @At("TAIL"))
    private void growPlasmaTank(CallbackInfoReturnable<Long> cir) {
        if (meke$plasmaTank.isEmpty()) {
            meke$plasmaTank.setStack(ExtraGenGases.HELIUM_PLASMA.getStack(MAX_INJECTION));
        } else {
            meke$plasmaTank.growStack(MAX_INJECTION, Action.EXECUTE);
        }
    }

    @Inject(method = "readUpdateTag", at = @At("TAIL"))
    private void updateOutputPlasmaFromNBT(CompoundTag tag, CallbackInfo ci) {
        NBTUtils.setBooleanIfPresent(tag, ExtraNBTConstants.OUTPUT_PLASMA, this::meke$setOutputPlasma);
    }

    @Inject(method = "writeUpdateTag", at = @At("TAIL"))
    private void updateOutputPlasmaToNBT(CompoundTag tag, CallbackInfo ci) {
        tag.putBoolean(ExtraNBTConstants.OUTPUT_PLASMA, meke$canOutputPlasma());
    }

    @Unique
    public IGasTank meke$getPlasmaTank() {
        return meke$plasmaTank;
    }

    @Unique
    public long meke$getMaxPlasma() {
        return MAX_PLASMA;
    }

    @Unique
    public boolean meke$canOutputPlasma() {
        return this.meke$outputPlasma;
    }

    @Unique
    public void meke$setOutputPlasma(boolean b) {
        this.meke$outputPlasma = b;
    }
}
