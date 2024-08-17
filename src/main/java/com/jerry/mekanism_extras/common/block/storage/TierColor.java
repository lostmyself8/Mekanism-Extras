package com.jerry.mekanism_extras.common.block.storage;

import com.jerry.mekanism_extras.common.api.tier.IAdvanceTier;

public class TierColor {
    private static final int[] cosmicColor = new int[]{255, 255, 255};
    private static final int[] infiniteColor = new int[]{1, 2, 3};

    public static float[] getColor(IAdvanceTier tier) {
        return switch (tier.getAdvanceTier()) {
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
}
