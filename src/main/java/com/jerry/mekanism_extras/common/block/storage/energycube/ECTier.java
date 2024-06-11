package com.jerry.mekanism_extras.common.block.storage.energycube;

import mekanism.api.math.FloatingLong;
import mekanism.api.tier.ITier;
import mekanism.common.tier.EnergyCubeTier;

public class ECTier {

    private static int[] cosmicColor = new int[]{255, 255, 255};
    private static int[] infiniteColor = new int[]{1, 2, 3};

    public static FloatingLong getOutput(EnergyCubeTier tier) {
        return switch (tier) {
            case BASIC -> FloatingLong.create(1024000f);
            case ADVANCED -> FloatingLong.create(4096000f);
            case ELITE -> FloatingLong.create(16384000f);
            case ULTIMATE -> FloatingLong.create(65536000f);
            case CREATIVE -> null;
        };
    }

    public static FloatingLong getMaxEnergy(EnergyCubeTier tier) {
        return switch (tier) {
            case BASIC -> FloatingLong.create(1024000000f);
            case ADVANCED -> FloatingLong.create(4096000000f);
            case ELITE -> FloatingLong.create(16384000000f);
            case ULTIMATE -> FloatingLong.create(65536000000f);
            case CREATIVE -> null;
        };
    }

    public static float[] getColor(EnergyCubeTier tier) {
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
}
