package com.jerry.mekextras.api.tier;

import com.jerry.mekextras.common.tier.TierColor;

import mekanism.api.SupportsColorMap;
import mekanism.api.math.MathUtils;

import net.minecraft.network.chat.TextColor;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.FastColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.IntFunction;

public enum AdvancedTier implements IAdvancedTier, StringRepresentable, SupportsColorMap {

    ABSOLUTE("Absolute", new int[] { 237, 238, 70 }, MapColor.COLOR_LIGHT_GREEN),
    SUPREME("Supreme", new int[] { 166, 0, 2 }, MapColor.TERRACOTTA_PINK),
    COSMIC("Cosmic", new int[] { 75, 248, 255 }, MapColor.DIAMOND),
    INFINITE("Infinite", new int[] { 247, 135, 255 }, MapColor.COLOR_MAGENTA);

    public static final IntFunction<AdvancedTier> BY_ID = ByIdMap.continuous(AdvancedTier::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    public static final StreamCodec<ByteBuf, AdvancedTier> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, AdvancedTier::ordinal);

    private static final AdvancedTier[] TIERS = values();

    private final String name;
    @Getter
    private final MapColor mapColor;
    private TextColor textColor;
    private int[] rgbCode;
    private int argb;

    AdvancedTier(String name, int[] rgbCode, MapColor mapColor) {
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

    @Override
    public AdvancedTier getAdvanceTier() {
        return this;
    }

    @Override
    public int getPackedColor() {
        if (this == COSMIC || this == INFINITE) {
            return TierColor.getPackedColor(this);
        }
        return argb;
    }

    @Override
    public int[] getRgbCode() {
        if (this == COSMIC || this == INFINITE) {
            return TierColor.getRgb(this);
        }
        return rgbCode;
    }

    @Override
    public void setColorFromAtlas(int[] color) {
        rgbCode = color;
        argb = FastColor.ARGB32.color(rgbCode[0], rgbCode[1], rgbCode[2]);
        textColor = TextColor.fromRgb(argb);
    }

    public TextColor getColor() {
        if (this == COSMIC || this == INFINITE) {
            return TextColor.fromRgb(getPackedColor());
        }
        return textColor;
    }

    @NotNull
    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static AdvancedTier byIndexStatic(int index) {
        return MathUtils.getByIndexMod(TIERS, index);
    }
}
