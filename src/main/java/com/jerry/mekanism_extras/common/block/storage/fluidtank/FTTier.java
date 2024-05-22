package com.jerry.mekanism_extras.common.block.storage.fluidtank;

import mekanism.api.tier.BaseTier;
import mekanism.api.tier.ITier;
import mekanism.common.config.value.CachedIntValue;

public enum FTTier implements ITier {
    ABSOLUTE(BaseTier.BASIC, 512_000, 128_000),
    SUPREME(BaseTier.ADVANCED, 1_024_000, 512_000),
    COSMIC(BaseTier.ELITE, 2_048_000, 2_048_000),
    INFINITE(BaseTier.ULTIMATE, 4_096_000, 8_192_00);
    private final int baseStorage;
    private final int baseOutput;
    private final BaseTier baseTier;
    private CachedIntValue storageReference;
    private CachedIntValue outputReference;
    private static int[] cosmicColor = new int[]{255, 255, 255};
    private static int[] infiniteColor = new int[]{1, 2, 3};

    FTTier(BaseTier tier, int s, int o) {
        baseStorage = s;
        baseOutput = o;
        baseTier = tier;
    }
    @Override
    public BaseTier getBaseTier() {
        return baseTier;
    }

    public int getStorage() {
        return storageReference == null ? getBaseStorage() : storageReference.getOrDefault();
    }

    public int getOutput() {
        return outputReference == null ? getBaseOutput() : outputReference.getOrDefault();
    }

    public int getBaseStorage() {
        return baseStorage;
    }

    public int getBaseOutput() {
        return baseOutput;
    }

    public static float[] getColor(BaseTier tier) {
        return switch (tier) {
            case BASIC -> new float[]{255/255.0F, 255/255.0F, 0/255.0F};
            case ADVANCED -> new float[]{255/255.0F, 0/255.0F, 0/255.0F};
            case ELITE -> new float[]{cosmicColor[0]/255.0F, cosmicColor[1]/255.0F, cosmicColor[2]/255.0F};
            case ULTIMATE -> new float[]{infiniteColor[0]/255.0F, infiniteColor[1]/255.0F, infiniteColor[2]/255.0F};
            case CREATIVE -> null;
        };
    }

    public static void tick() {
        editCosmicColor();
        editInfiniteColor();
    }

    private static int[] startColor = new int[]{255, 255, 255};
    private static int[] endColor = new int[]{255, 70, 235};
    private static double tCosmic = 0.0;
    private static double deltaTCosmic = 0.01;
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
    private static double deltaTInfinite = 0.01;

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
