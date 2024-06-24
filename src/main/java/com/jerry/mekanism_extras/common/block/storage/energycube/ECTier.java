package com.jerry.mekanism_extras.common.block.storage.energycube;

import mekanism.api.math.FloatingLong;
import mekanism.api.tier.BaseTier;
import mekanism.api.tier.ITier;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public enum ECTier implements ITier, StringRepresentable {

    ABSOLUTE(BaseTier.BASIC, FloatingLong.createConst(1_024_000_000), FloatingLong.createConst(1_024_000)),
    SUPREME(BaseTier.ADVANCED, FloatingLong.createConst(4_096_000_000L), FloatingLong.createConst(4_096_000)),
    COSMIC(BaseTier.ELITE, FloatingLong.createConst(16_384_000_000L), FloatingLong.createConst(16_384_000)),
    INFINITE(BaseTier.ULTIMATE, FloatingLong.createConst(65_536_000_000L), FloatingLong.createConst(65_536_000));

    private final FloatingLong baseMaxEnergy;
    private final FloatingLong baseOutput;
    private final BaseTier baseTier;
    @Nullable
    private CachedFloatingLongValue storageReference;
    @Nullable
    private CachedFloatingLongValue outputReference;

    ECTier(BaseTier tier, FloatingLong max, FloatingLong out) {
        baseMaxEnergy = max;
        baseOutput = out;
        baseTier = tier;
    }

    @Override
    public BaseTier getBaseTier() {
        return baseTier;
    }

    @Override
    @NotNull
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public FloatingLong getMaxEnergy() {
        return storageReference == null ? getBaseMaxEnergy() : storageReference.getOrDefault();
    }

    public FloatingLong getOutput() {
        return outputReference == null ? getBaseOutput() : outputReference.getOrDefault();
    }

    public FloatingLong getBaseMaxEnergy() {
        return baseMaxEnergy;
    }

    public FloatingLong getBaseOutput() {
        return baseOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the EnergyCubeTier a reference to the actual config value object
     */
    public void setConfigReference(CachedFloatingLongValue storageReference, CachedFloatingLongValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
