package com.jerry.mekanism_extras.api.tier;

import mekanism.api.SupportsColorMap;
import mekanism.api.math.MathUtils;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum AdvanceTier implements StringRepresentable, SupportsColorMap {
    ABSOLUTE("Absolute", new int[]{95, 255, 184}, MapColor.COLOR_LIGHT_GREEN),
    SUPREME("Supreme", new int[]{255, 128, 106}, MapColor.TERRACOTTA_PINK),
    COSMIC("Cosmic", new int[]{75, 248, 255}, MapColor.DIAMOND),
    INFINITE("Infinite", new int[]{247, 135, 255}, MapColor.COLOR_MAGENTA);

    private static final AdvanceTier[] TIERS = values();

    private final String name;
    private final MapColor mapColor;
    private TextColor textColor;
    private int[] rgbCode;

    AdvanceTier(String name, int[] rgbCode, MapColor mapColor) {
        this.name = name;
        this.mapColor = mapColor;
        setColorFromAtlas(rgbCode);
    }

    public String getSimpleName() {
        return name;
    }

    public String getLowerName() {
        return getSimpleName().toLowerCase(Locale.ROOT);
    }

    public MapColor getMapColor() {
        return mapColor;
    }

    @Override
    public int[] getRgbCode() {
        return rgbCode;
    }

    @Override
    public void setColorFromAtlas(int[] color) {
        this.rgbCode = color;
        this.textColor = TextColor.fromRgb(rgbCode[0] << 16 | rgbCode[1] << 8 | rgbCode[2]);
    }

    public TextColor getColor() {
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
