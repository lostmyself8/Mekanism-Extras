package com.jerry.mekanism_extras.common.resource;

import com.jerry.mekanism_extras.common.ExtraTags;

import mekanism.common.resource.IResource;
import mekanism.common.resource.ResourceType;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum ExtraResource implements IResource {

    NAQUADAH("naquadah", 0x051602, () -> ExtraTags.Items.NAQUADAH, false, ExtraBlockResourceInfo.NAQUADAH, ExtraBlockResourceInfo.RAW_NAQUADAH),
    END_NAQUADAH("end_naquadah", 0x051602, () -> ExtraTags.Items.END_NAQUADAH, false, ExtraBlockResourceInfo.NAQUADAH, ExtraBlockResourceInfo.RAW_NAQUADAH),
    TUNGSTEN("tungsten", 0x372700, () -> ExtraTags.Items.TUNGSTEN, false, ExtraBlockResourceInfo.TUNGSTEN, null),
    ;

    @NotNull
    private final String name;
    private final int tint;
    // Note: This is a supplier because of the chicken and egg of referencing OreType and OreType referencing
    // PrimaryResource
    @NotNull
    private final Supplier<TagKey<Item>> oreTag;
    private final boolean isVanilla;
    @Nullable
    private final ExtraBlockResourceInfo resourceBlockInfo;
    @Nullable
    private final ExtraBlockResourceInfo rawResourceBlockInfo;

    ExtraResource(@NotNull String name,
                  int tint,
                  @NotNull Supplier<TagKey<Item>> oreTag, // 一定要用 Supplier，这非常重要，否则崩溃
                  boolean isVanilla,
                  @Nullable ExtraBlockResourceInfo resourceBlockInfo,
                  @Nullable ExtraBlockResourceInfo rawResourceBlockInfo) {
        this.name = name;
        this.tint = tint;
        this.oreTag = oreTag;
        this.isVanilla = isVanilla;
        this.resourceBlockInfo = resourceBlockInfo;
        this.rawResourceBlockInfo = rawResourceBlockInfo;
    }

    @Override
    public String getRegistrySuffix() {
        return name;
    }

    public int getTint() {
        return tint;
    }

    public TagKey<Item> getOreTag() {
        return oreTag.get();
    }

    public boolean has(ResourceType type) {
        return type != ResourceType.ENRICHED && (!isVanilla || !type.isVanilla());
    }

    public boolean isVanilla() {
        return isVanilla;
    }

    @Nullable
    public ExtraBlockResourceInfo getResourceBlockInfo() {
        return resourceBlockInfo;
    }

    @Nullable
    public ExtraBlockResourceInfo getRawResourceBlockInfo() {
        return rawResourceBlockInfo;
    }
}
