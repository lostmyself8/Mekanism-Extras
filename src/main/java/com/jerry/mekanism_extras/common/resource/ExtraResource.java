package com.jerry.mekanism_extras.common.resource;

import com.jerry.mekanism_extras.common.ExtraTag;
import mekanism.common.resource.IResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum ExtraResource implements IResource {
    NAQUADAH("naquadah", 0x051602, ExtraTag.Items.NAQUADAH, ExtraBlockResourceInfo.NAQUADAH, ExtraBlockResourceInfo.RAW_NAQUADAH),
    END_NAQUADAH("end_naquadah", 0x051602, ExtraTag.Items.END_NAQUADAH);

    private final String name;
    private final int tint;
    //Note: This is a supplier because of the chicken and egg of referencing OreType and OreType referencing PrimaryResource
    private final Supplier<TagKey<Item>> oreTag;
    private final boolean isVanilla;
    private final ExtraBlockResourceInfo resourceBlockInfo;
    private final ExtraBlockResourceInfo rawResourceBlockInfo;

    ExtraResource(String name, int tint, TagKey<Item> oreTag) {
        this(name, tint, () -> oreTag, true, null, null);
    }

    ExtraResource(String name, int tint, TagKey<Item> oreTag, ExtraBlockResourceInfo resourceBlockInfo, ExtraBlockResourceInfo rawResourceBlockInfo) {
        this(name, tint, () -> oreTag, false, resourceBlockInfo, rawResourceBlockInfo);
    }

    ExtraResource(String name, int tint, Supplier<TagKey<Item>> oreTag, boolean isVanilla, ExtraBlockResourceInfo resourceBlockInfo, ExtraBlockResourceInfo rawResourceBlockInfo) {
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
