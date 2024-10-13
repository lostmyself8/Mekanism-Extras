package com.jerry.mekanism_extras.common.resource;

import mekanism.common.resource.IResource;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;

public enum ExtraBlockResourceInfo implements IResource {
    NAQUADAH("naquadah", 7.5F, 12, MaterialColor.COLOR_CYAN),
    RAW_NAQUADAH("raw_naquadah", 7.5F, 12, MaterialColor.COLOR_CYAN, Material.STONE);

    private final String registrySuffix;
    private final MaterialColor mapColor;
    private final PushReaction pushReaction;
    private final boolean portalFrame;
    private final boolean burnsInFire;
    private final Material material;
    private final float resistance;
    private final float hardness;
    private final int burnTime;
    //Number between 0 and 15
    private final int lightValue;

    ExtraBlockResourceInfo(String registrySuffix, float hardness, float resistance, MaterialColor mapColor) {
        this(registrySuffix, hardness, resistance, mapColor, null);
    }

    ExtraBlockResourceInfo(String registrySuffix, float hardness, float resistance, MaterialColor mapColor, @Nullable Material material) {
        this(registrySuffix, hardness, resistance, mapColor, material, -1);
    }

    ExtraBlockResourceInfo(String registrySuffix, float hardness, float resistance, MaterialColor mapColor, @Nullable Material material, int burnTime) {
        this(registrySuffix, hardness, resistance, mapColor, material, burnTime, 0);
    }

    ExtraBlockResourceInfo(String registrySuffix, float hardness, float resistance, MaterialColor mapColor, @Nullable Material material, int burnTime, int lightValue) {
        this(registrySuffix, hardness, resistance, mapColor, material, burnTime, lightValue, true, false, PushReaction.NORMAL);
    }

    ExtraBlockResourceInfo(String registrySuffix, float hardness, float resistance, MaterialColor mapColor, @Nullable Material material, int burnTime, int lightValue,
                      boolean burnsInFire, boolean portalFrame, PushReaction pushReaction) {
        this.registrySuffix = registrySuffix;
        this.pushReaction = pushReaction;
        this.portalFrame = portalFrame;
        this.burnsInFire = burnsInFire;
        this.burnTime = burnTime;
        this.lightValue = lightValue;
        this.resistance = resistance;
        this.hardness = hardness;
        this.material = material;
        this.mapColor = mapColor;
    }

    @Override
    public String getRegistrySuffix() {
        return registrySuffix;
    }

    public float getHardness() {
        return hardness;
    }

    public float getResistance() {
        return resistance;
    }

    public int getLightValue() {
        return lightValue;
    }

    public boolean isPortalFrame() {
        return portalFrame;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public boolean burnsInFire() {
        return burnsInFire;
    }

    public PushReaction getPushReaction() {
        return pushReaction;
    }

    public Material getMaterial() {
        return material;
    }

    public MaterialColor getMaterialColor() {
        return mapColor;
    }

}
