package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekanism_extras.api.IMixinMachineEnergyContainer;
import com.jerry.mekanism_extras.common.util.ExtraWorldUtils;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.NBTConstants;
import mekanism.api.Upgrade;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.MekanismUtils;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(value = MachineEnergyContainer.class, remap = false)
public abstract class MixinMachineEnergyContainer<TILE extends TileEntityMekanism> extends BasicEnergyContainer implements IMixinMachineEnergyContainer {

    @Shadow
    @Final
    protected TILE tile;

    @Shadow
    protected FloatingLong currentEnergyPerTick;

    @Shadow
    public abstract void setMaxEnergy(FloatingLong maxEnergy);

    @Shadow public abstract FloatingLong getBaseMaxEnergy();

    @Shadow public abstract void updateMaxEnergy();

    @Shadow public abstract void updateEnergyPerTick();

    protected MixinMachineEnergyContainer(FloatingLong maxEnergy, Predicate<@NotNull AutomationType> canExtract, Predicate<@NotNull AutomationType> canInsert, @Nullable IContentsListener listener) {
        super(maxEnergy, canExtract, canInsert, listener);
    }

    @Inject(method = "getEnergyPerTick", at = @At(value = "RETURN"), cancellable = true)
    public void mixinGetEnergyPerTick(CallbackInfoReturnable<FloatingLong> cir) {
        if (tile.supportsUpgrade(ExtraUpgrade.CREATIVE)) {
            cir.setReturnValue(tile.getComponent().isUpgradeInstalled(ExtraUpgrade.CREATIVE) ? FloatingLong.ZERO : currentEnergyPerTick);
        }
    }

    @Override
    public void mekanism_Extras$extraUpdateMaxEnergy() {
        if (tile.supportsUpgrade(Upgrade.ENERGY) || tile.supportsUpgrade(ExtraUpgrade.CREATIVE)) {
            if (tile.getComponent().isUpgradeInstalled(ExtraUpgrade.CREATIVE)) {
                setMaxEnergy(FloatingLong.MAX_VALUE);
            } else {
                setMaxEnergy(MekanismUtils.getMaxEnergy(tile, getBaseMaxEnergy()));
            }
        }
    }

    @Override
    public void mekanism_Extras$extraRecalculateUpgrades(Upgrade upgrade) {
        CompoundTag upgradesTag = tile.serializeNBT().getCompound(NBTConstants.UPGRADES);
        if (upgrade == ExtraUpgrade.CREATIVE) {
            mekanism_Extras$extraUpdateMaxEnergy();
            if (ExtraWorldUtils.isWorldLoaded(tile.getTileWorld()) && !upgradesTag.isEmpty() || getMaxEnergy().equals(FloatingLong.MAX_VALUE)) {
                setEnergy(FloatingLong.MAX_VALUE);
            }
        } else if (upgrade == Upgrade.ENERGY) {
            if (!getMaxEnergy().equals(FloatingLong.MAX_VALUE)) {
                updateMaxEnergy();
                updateEnergyPerTick();
            }
        }
    }
}
