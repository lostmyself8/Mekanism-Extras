package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.config.value.CachedLongValue;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

@NothingNullByDefault
public enum ECTier implements IAdvancedTier, StringRepresentable {
    ABSOLUTE(AdvancedTier.ABSOLUTE, 1_024_000_000, 1_024_000),
    SUPREME(AdvancedTier.SUPREME, 4_096_000_000L, 4_096_000),
    COSMIC(AdvancedTier.COSMIC, 16_384_000_000L, 16_384_000),
    INFINITE(AdvancedTier.INFINITE, 65_536_000_000L, 65_536_000);

    private final long advanceMaxEnergy;
    private final long advanceOutput;
    private final AdvancedTier advancedTier;
    @Nullable
    private CachedLongValue storageReference;
    @Nullable
    private CachedLongValue outputReference;

    ECTier(AdvancedTier tier, long max, long out) {
        advanceMaxEnergy = max;
        advanceOutput = out;
        advancedTier = tier;
    }

    @Override
    public AdvancedTier getAdvanceTier() {
        return advancedTier;
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
