package com.jerry.mekextras.common.tier;

import com.jerry.mekextras.api.tier.IAdvancedTier;

import net.minecraft.util.FastColor;

public class TierColor {

    private static final int[] cosmicColor = new int[] { 255, 255, 255 };
    private static final int[] infiniteColor = new int[] { 1, 2, 3 };

    public static int getPackedColor(IAdvancedTier tier) {
        return getPackedColor(tier, 1.0F);
    }

    public static int getPackedColor(IAdvancedTier tier, float alpha) {
        int[] rgb = getRgb(tier);
        return FastColor.ARGB32.color(FastColor.as8BitChannel(alpha), rgb[0], rgb[1], rgb[2]);
    }

    public static int[] getRgb(IAdvancedTier tier) {
        return switch (tier.getAdvanceTier()) {
            case ABSOLUTE -> new int[] { 255, 255, 0 };
            case SUPREME -> new int[] { 255, 0, 0 };
            case COSMIC -> cosmicColor;
            case INFINITE -> infiniteColor;
        };
    }

    public static float[] getColor(IAdvancedTier tier) {
        int[] rgb = getRgb(tier);
        return new float[] { rgb[0] / 255.0F, rgb[1] / 255.0F, rgb[2] / 255.0F };
    }

    public static int[] getColor(int tier) {
        return switch (tier) {
            case 0 -> new int[] { 255, 255, 0 };
            case 1 -> new int[] { 255, 0, 0 };
            case 2 -> cosmicColor;
            case 3 -> infiniteColor;
            default -> throw new IllegalStateException("Unexpected value: " + tier);
        };
    }

    public static void tick() {
        editCosmicColor();
        editInfiniteColor();
    }

    private static final int[] startColor = new int[] { 255, 255, 255 };
    private static final int[] endColor = new int[] { 255, 70, 235 };
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
