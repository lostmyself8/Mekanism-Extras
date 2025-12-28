package com.jerry.mekextras.mixin.resource.ore;

import com.jerry.mekextras.common.resource.ExtraResource;
import com.jerry.mekextras.common.resource.ore.ExtraOreType;

import mekanism.common.resource.IResource;
import mekanism.common.resource.ore.BaseOreConfig;
import mekanism.common.resource.ore.OreAnchor;
import mekanism.common.resource.ore.OreType;
import mekanism.common.world.height.HeightShape;

import net.minecraft.util.StringRepresentable;

import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(value = OreType.class, remap = false)
public class MixinOreType {

    @Shadow
    @Final
    @Mutable
    private static OreType[] $VALUES;

    @Mutable
    @Shadow
    public static Codec<OreType> CODEC;

    public MixinOreType() {}

    @Invoker("<init>")
    public static OreType oreType$initInvoker(String internalName, int internalId, IResource resource, BaseOreConfig... configs) {
        throw new AssertionError();
    }

    @Invoker("<init>")
    public static OreType oreType$initInvoker(String internalName, int internalId, IResource resource, int exp, BaseOreConfig... configs) {
        throw new AssertionError();
    }

    @Invoker("<init>")
    public static OreType oreType$initInvoker(String internalName, int internalId, IResource resource, int minExp, int maxExp, BaseOreConfig... configs) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void oreTypeClinit(CallbackInfo ci) {
        ExtraOreType.NAQUADAH = mekanismExtras$addVariant("NAQUADAH", ExtraResource.NAQUADAH,
                new BaseOreConfig("small", 8, 0, 4, HeightShape.TRAPEZOID, OreAnchor.absolute(-62), OreAnchor.absolute(-59)),
                new BaseOreConfig("middle", 24, 0, 6, HeightShape.TRAPEZOID, OreAnchor.absolute(10), OreAnchor.absolute(30)));

        // 重新初始化静态参数，这非常重要
        mekanismExtras$reinitializeByIdMap();
    }

    @Unique
    private static OreType mekanismExtras$addVariant(String internalName, IResource resource, BaseOreConfig... configs) {
        ArrayList<OreType> variants = new ArrayList<>(Arrays.asList($VALUES));
        OreType upgrade = oreType$initInvoker(internalName,
                variants.getLast().ordinal() + 1,
                resource,
                configs);
        variants.add(upgrade);
        MixinOreType.$VALUES = variants.toArray(new OreType[0]);
        return upgrade;
    }

    @Unique
    private static void mekanismExtras$reinitializeByIdMap() {
        CODEC = StringRepresentable.fromEnum(OreType::values);
    }
}
