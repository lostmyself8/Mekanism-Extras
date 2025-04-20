package com.jerry.mekextras.mixin;

import com.jerry.mekextras.api.text.APIExtraLang;
import com.jerry.mekextras.api.ExtraUpgrade;
import mekanism.api.Upgrade;
import mekanism.api.text.EnumColor;
import mekanism.api.text.ILangEntry;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(value = Upgrade.class, remap = false)
public class MixinUpgrade {

    @Shadow
    @Final
    @Mutable
    private static Upgrade[] $VALUES;

    public MixinUpgrade() {
    }

    @Invoker("<init>")
    public static Upgrade evolutionism$initInvoker(String internalName, int internalId, String name, ILangEntry langKey, ILangEntry descLangKey, int maxStack, EnumColor color) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>",at = @At("TAIL"))
    private static void upgradeClinit(CallbackInfo ci) {
        ExtraUpgrade.STACK = mekanismExtras$addVariant("STACK", APIExtraLang.UPGRADE_STACK, APIExtraLang.UPGRADE_STACK_DESCRIPTION, 8, EnumColor.RED);
        ExtraUpgrade.IONIC_MEMBRANE = mekanismExtras$addVariant("IONIC_MEMBRANE", APIExtraLang.UPGRADE_IONIC_MEMBRANE, APIExtraLang.UPGRADE_IONIC_MEMBRANE_DESCRIPTION, 1, EnumColor.WHITE);
        ExtraUpgrade.CREATIVE = mekanismExtras$addVariant("CREATIVE", APIExtraLang.UPGRADE_CREATIVE, APIExtraLang.UPGRADE_CREATIVE_DESCRIPTION, 1, EnumColor.PURPLE);
    }

    @Unique
    private static Upgrade mekanismExtras$addVariant(String internalName, ILangEntry langKey, ILangEntry descLangKey, int maxStack, EnumColor color) {
        ArrayList<Upgrade> variants = new ArrayList<>(Arrays.asList($VALUES));
        Upgrade upgrade = evolutionism$initInvoker(internalName,
                variants.getLast().ordinal() + 1,
                internalName.toLowerCase(),
                langKey,
                descLangKey,
                maxStack,
                color);
        variants.add(upgrade);
        MixinUpgrade.$VALUES = variants.toArray(new Upgrade[0]);
        return upgrade;
    }
}
