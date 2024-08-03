package com.jerry.mekanism_extras.common.block.storage.energycube;

import com.jerry.mekanism_extras.common.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.common.api.tier.IAdvanceTier;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public enum ECTier implements IAdvanceTier, StringRepresentable {

    ABSOLUTE(AdvanceTier.ABSOLUTE, FloatingLong.createConst(1_024_000_000), FloatingLong.createConst(1_024_000)),
    SUPREME(AdvanceTier.SUPREME, FloatingLong.createConst(4_096_000_000L), FloatingLong.createConst(4_096_000)),
    COSMIC(AdvanceTier.COSMIC, FloatingLong.createConst(16_384_000_000L), FloatingLong.createConst(16_384_000)),
    INFINITE(AdvanceTier.INFINITE, FloatingLong.createConst(65_536_000_000L), FloatingLong.createConst(65_536_000));

    private final FloatingLong advanceMaxEnergy;
    private final FloatingLong advanceOutput;
    private final AdvanceTier advanceTier;
    @Nullable
    private CachedFloatingLongValue storageReference;
    @Nullable
    private CachedFloatingLongValue outputReference;

    ECTier(AdvanceTier tier, FloatingLong max, FloatingLong out) {
        advanceMaxEnergy = max;
        advanceOutput = out;
        advanceTier = tier;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
    }

    @Override
    @NotNull
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public FloatingLong getMaxEnergy() {
        return storageReference == null ? getAdvanceMaxEnergy() : storageReference.getOrDefault();
    }

    public FloatingLong getOutput() {
        return outputReference == null ? getAdvanceOutput() : outputReference.getOrDefault();
    }

    public FloatingLong getAdvanceMaxEnergy() {
        return advanceMaxEnergy;
    }

    public FloatingLong getAdvanceOutput() {
        return advanceOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the EnergyCubeTier a reference to the actual config value object
     */
    public void setConfigReference(CachedFloatingLongValue storageReference, CachedFloatingLongValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
