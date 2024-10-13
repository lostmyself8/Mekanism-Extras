package com.jerry.mekanism_extras.api.tier;

import mekanism.api.math.MathUtils;
import mekanism.api.text.EnumColor;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum AdvanceTier implements StringRepresentable {
    ABSOLUTE("Absolute", EnumColor.BRIGHT_GREEN, EnumColor.BRIGHT_GREEN),
    SUPREME("Supreme", EnumColor.RED, EnumColor.RED),
    COSMIC("Cosmic", EnumColor.INDIGO, EnumColor.INDIGO),
    INFINITE("Infinite", EnumColor.PURPLE, EnumColor.PURPLE);

    private static final AdvanceTier[] TIERS = values();

    private final String name;
    private final EnumColor color;
    private final EnumColor textColor;
    private int[] rgbCode;

    AdvanceTier(String name, EnumColor color, EnumColor textColor) {
        this.name = name;
        this.color = color;
        this.textColor = textColor;
    }

    public String getSimpleName() {
        return name;
    }

    public String getLowerName() {
        return getSimpleName().toLowerCase(Locale.ROOT);
    }

    public EnumColor getColor() {
        return color;
    }

    public EnumColor getTextColor() {
        return this.textColor;
    }

    @NotNull
    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static AdvanceTier byIndexStatic(int index) {
        return MathUtils.getByIndexMod(TIERS, index);
    }
}
