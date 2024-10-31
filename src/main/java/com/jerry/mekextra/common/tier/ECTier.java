package com.jerry.mekextra.common.tier;

import com.jerry.mekextra.api.tier.AdvanceTier;
import com.jerry.mekextra.api.tier.IAdvanceTier;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.config.value.CachedLongValue;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

@NothingNullByDefault
public enum ECTier implements IAdvanceTier, StringRepresentable {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 1_024_000_000, 1_024_000),
    SUPREME(AdvanceTier.SUPREME, 4_096_000_000L, 4_096_000),
    COSMIC(AdvanceTier.COSMIC, 16_384_000_000L, 16_384_000),
    INFINITE(AdvanceTier.INFINITE, 65_536_000_000L, 65_536_000);

    private final long advanceMaxEnergy;
    private final long advanceOutput;
    private final AdvanceTier advanceTier;
    @Nullable
    private CachedLongValue storageReference;
    @Nullable
    private CachedLongValue outputReference;

    ECTier(AdvanceTier tier, long max, long out) {
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

    public long getMaxEnergy() {
        return storageReference == null ? getAdvanceMaxEnergy() : storageReference.getOrDefault();
    }

    public long getOutput() {
        return outputReference == null ? getAdvanceOutput() : outputReference.getOrDefault();
    }

    public long getAdvanceMaxEnergy() {
        return advanceMaxEnergy;
    }

    public long getAdvanceOutput() {
        return advanceOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the EnergyCubeTier a reference to the actual config value object
     */
    public void setConfigReference(CachedLongValue storageReference, CachedLongValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
