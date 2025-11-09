package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.api.APIExtraLang;

import mekanism.api.text.APILang;
import mekanism.api.text.ILangEntry;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(value = APILang.class, remap = false)
public abstract class MixinAPILang implements ILangEntry {

    @Shadow(remap = false)
    @Final
    @Mutable
    private static APILang[] $VALUES;

    public MixinAPILang() {}

    @Invoker("<init>")
    public static APILang lang$initInvoker(String internalName, int internalId, String type, String path) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void langClinit(CallbackInfo ci) {
        APIExtraLang.UPGRADE_STACK = mekanismExtras$addVariant("UPGRADE_STACK", "upgrade", "stack");
        APIExtraLang.UPGRADE_STACK_DESCRIPTION = mekanismExtras$addVariant("UPGRADE_STACK_DESCRIPTION", "upgrade", "stack.description");
        APIExtraLang.UPGRADE_IONIC_MEMBRANE = mekanismExtras$addVariant("UPGRADE_IONIC_MEMBRANE", "upgrade", "ionic_membrane");
        APIExtraLang.UPGRADE_IONIC_MEMBRANE_DESCRIPTION = mekanismExtras$addVariant("UPGRADE_IONIC_MEMBRANE_DESCRIPTION", "upgrade", "ionic_membrane.description");
        APIExtraLang.UPGRADE_CREATIVE = mekanismExtras$addVariant("UPGRADE_CREATIVE", "upgrade", "creative");
        APIExtraLang.UPGRADE_CREATIVE_DESCRIPTION = mekanismExtras$addVariant("UPGRADE_CREATIVE_DESCRIPTION", "upgrade", "creative.description");
    }

    @Unique
    private static APILang mekanismExtras$addVariant(String internalName, String type, String path) {
        ArrayList<APILang> variants = new ArrayList<>(Arrays.asList($VALUES));
        APILang upgrade = lang$initInvoker(internalName,
                variants.get(variants.size() - 1).ordinal() + 1,
                type,
                path);
        variants.add(upgrade);
        $VALUES = variants.toArray(new APILang[0]);
        return upgrade;
    }
}
