package com.jerry.mekanism_extras.common.block.storage.fluidtank;

import com.jerry.mekanism_extras.common.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.common.api.tier.IAdvanceTier;
import mekanism.common.config.value.CachedIntValue;

public enum FTTier implements IAdvanceTier {
    ABSOLUTE(AdvanceTier.ABSOLUTE, 512_000, 128_000),
    SUPREME(AdvanceTier.SUPREME, 1_024_000, 512_000),
    COSMIC(AdvanceTier.COSMIC, 2_048_000, 2_048_000),
    INFINITE(AdvanceTier.INFINITE, 4_096_000, 8_192_00);
    private final int advanceStorage;
    private final int advanceOutput;
    private final AdvanceTier advanceTier;
    private CachedIntValue storageReference;
    private CachedIntValue outputReference;
    private static final int[] cosmicColor = new int[]{255, 255, 255};
    private static final int[] infiniteColor = new int[]{1, 2, 3};

    FTTier(AdvanceTier tier, int s, int o) {
        advanceStorage = s;
        advanceOutput = o;
        advanceTier = tier;
    }
    @Override
    public AdvanceTier getAdvanceTier() {
        return advanceTier;
    }

    public int getStorage() {
        return storageReference == null ? getAdvanceStorage() : storageReference.getOrDefault();
    }

    public int getOutput() {
        return outputReference == null ? getAdvanceOutput() : outputReference.getOrDefault();
    }

    public int getAdvanceStorage() {
        return advanceStorage;
    }

    public int getAdvanceOutput() {
        return advanceOutput;
    }

    public static float[] getColor(AdvanceTier tier) {
        return switch (tier) {
            case ABSOLUTE -> new float[]{255/255.0F, 255/255.0F, 0/255.0F};
            case SUPREME -> new float[]{255/255.0F, 0/255.0F, 0/255.0F};
            case COSMIC -> new float[]{cosmicColor[0]/255.0F, cosmicColor[1]/255.0F, cosmicColor[2]/255.0F};
            case INFINITE -> new float[]{infiniteColor[0]/255.0F, infiniteColor[1]/255.0F, infiniteColor[2]/255.0F};
        };
    }

    public static void tick() {
        editCosmicColor();
        editInfiniteColor();
    }

    private static final int[] startColor = new int[]{255, 255, 255};
    private static final int[] endColor = new int[]{255, 70, 235};
    private static double tCosmic = 0.0;
    private static final double deltaTCosmic = 0.01;
    private static int direction = 1;

    private static void editCosmicColor() {
        cosmicColor[0] = (int) ((1 - tCosmic) * startColor[0] + tCosmic * endColor[0]);
        cosmicColor[1] = (int) ((1 - tCosmic) * startColor[1] + tCosmic * endColor[1]);
        cosmicColor[2] = (int) ((1 - tCosmic) * startColor[2] + tCosmic * endColor[2]);

        tCosmic += deltaTCosmic * direction;
        if (tCosmic > 1) {
            tCosmic = 1;
            direction = -1;
        } else if (tCosmic < 0) {
            tCosmic = 0;
            direction = 1;
        }
    }


    private static double tInfinite = 0.0;
    private static final double deltaTInfinite = 0.01;

    private static void editInfiniteColor() {
        infiniteColor[0] = (int) (Math.sin(2 * Math.PI * tInfinite) * 127 + 128);
        infiniteColor[1] = (int) (Math.sin(2 * Math.PI * (tInfinite + 0.33)) * 127 + 128);
        infiniteColor[2] = (int) (Math.sin(2 * Math.PI * (tInfinite + 0.67)) * 127 + 128);

        tInfinite += deltaTInfinite;
        if (tInfinite > 1) {
            tInfinite = 0;
        }
    }
    public void setConfigReference(CachedIntValue storageReference, CachedIntValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
