package com.jerry.mekanism_extras.common.tier;

import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.api.tier.IAdvancedTier;

import mekanism.common.config.value.CachedLongValue;

import net.minecraft.util.StringRepresentable;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum CTTier implements IAdvancedTier, StringRepresentable {

    ABSOLUTE(AdvancedTier.ABSOLUTE, 131_072_000, 65_536_000),// x16 1/2
    SUPREME(AdvancedTier.SUPREME, 4_194_304_000L, 2_097_150_000),// x32 1/2
    COSMIC(AdvancedTier.COSMIC, 268_435_456_000L, 134_217_728_000L),// x64 3/4
    INFINITE(AdvancedTier.INFINITE, 34_359_738_368_000L, 17_179_869_184_000L); // x128 3/4

    @Getter
    private final long advanceStorage;
    @Getter
    private final long advanceOutput;
    private final AdvancedTier advancedTier;
    private CachedLongValue storageReference;
    private CachedLongValue outputReference;

    CTTier(AdvancedTier tier, long s, long o) {
        advanceStorage = s;
        advanceOutput = o;
        advancedTier = tier;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
    }

    @NotNull
    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public long getStorage() {
        return storageReference == null ? getAdvanceStorage() : storageReference.getOrDefault();
    }

    public long getOutput() {
        return outputReference == null ? getAdvanceOutput() : outputReference.getOrDefault();
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the GasTankTier a reference to the actual config value object
     */
    public void setConfigReference(CachedLongValue storageReference, CachedLongValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
