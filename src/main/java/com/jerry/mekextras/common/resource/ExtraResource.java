package com.jerry.mekextras.common.resource;

import com.jerry.mekextras.common.tags.ExtraTags;

import mekanism.common.resource.IResource;
import mekanism.common.resource.ResourceType;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum ExtraResource implements IResource {

    NAQUADAH("naquadah", 0x051602, () -> ExtraTags.Items.NAQUADAH, BlockExtraResourceInfo.NAQUADAH, BlockExtraResourceInfo.RAW_NAQUADAH);

    private final String name;
    private final int tint;
    // Note: This is a supplier because of the chicken and egg of referencing OreType and OreType referencing
    // PrimaryResource
    private final Supplier<TagKey<Item>> oreTag;
    private final boolean isVanilla;
    private final BlockExtraResourceInfo resourceBlockInfo;
    private final BlockExtraResourceInfo rawResourceBlockInfo;

    ExtraResource(String name, int tint, TagKey<Item> oreTag) {
        this(name, tint, () -> oreTag, true, null, null);
    }

    ExtraResource(String name, int tint, Supplier<TagKey<Item>> oreTag, BlockExtraResourceInfo resourceBlockInfo, BlockExtraResourceInfo rawResourceBlockInfo) {
        this(name, tint, oreTag, false, resourceBlockInfo, rawResourceBlockInfo);
    }

    ExtraResource(String name, int tint, Supplier<TagKey<Item>> oreTag, boolean isVanilla, BlockExtraResourceInfo resourceBlockInfo, BlockExtraResourceInfo rawResourceBlockInfo) {
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

    // 非富集且不是原版或不是锭、粗矿和粒
    public boolean has(ResourceType type) {
        return type != ResourceType.ENRICHED && (!isVanilla || !type.isVanilla());
    }

    public boolean isVanilla() {
        return isVanilla;
    }

    @Nullable
    public BlockExtraResourceInfo getResourceBlockInfo() {
        return resourceBlockInfo;
    }

    @Nullable
    public BlockExtraResourceInfo getRawResourceBlockInfo() {
        return rawResourceBlockInfo;
    }
}
