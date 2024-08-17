package com.jerry.mekanism_extras.common.block.storage.chemicaltank;

import com.jerry.mekanism_extras.common.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.common.api.tier.IAdvanceTier;
import mekanism.common.config.value.CachedLongValue;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum CTTier implements IAdvanceTier, StringRepresentable {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 32_768_000, 2_048_000),
    SUPREME(AdvanceTier.SUPREME, 131_072_000, 8_192_000),
    COSMIC(AdvanceTier.COSMIC, 524_288_000, 32_768_000),
    INFINITE(AdvanceTier.INFINITE, 4_194_304_000L, 131_072_000);

    private final long advanceStorage;
    private final long advanceOutput;
    private final AdvanceTier advanceTier;
    private CachedLongValue storageReference;
    private CachedLongValue outputReference;

    CTTier(AdvanceTier tier, long s, long o) {
        advanceStorage = s;
        advanceOutput = o;
        advanceTier = tier;
    }

    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
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

    public long getAdvanceStorage() {
        return advanceStorage;
    }

    public long getAdvanceOutput() {
        return advanceOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the GasTankTier a reference to the actual config value object
     */
    public void setConfigReference(CachedLongValue storageReference, CachedLongValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
