package com.jerry.mekanism_extras.mixin;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekanism_extras.api.APIExtraLang;
import mekanism.api.Upgrade;
import mekanism.api.text.APILang;
import mekanism.api.text.EnumColor;
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

    @Shadow
    @Final
    @Mutable
    private static Upgrade[] UPGRADES;

    public MixinUpgrade() {
    }

    @Invoker("<init>")
    public static Upgrade upgrade$initInvoker(String internalName, int internalId, String name, APILang langKey, APILang descLangKey, int maxStack, EnumColor color) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void upgradeClinit(CallbackInfo ci) {
        ExtraUpgrade.STACK = mekanismExtras$addVariant("STACK", APIExtraLang.UPGRADE_STACK, APIExtraLang.UPGRADE_STACK_DESCRIPTION, 6, EnumColor.BRIGHT_PINK);
        ExtraUpgrade.IONIC_MEMBRANE = mekanismExtras$addVariant("IONIC_MEMBRANE", APIExtraLang.UPGRADE_IONIC_MEMBRANE, APIExtraLang.UPGRADE_IONIC_MEMBRANE_DESCRIPTION, 1, EnumColor.WHITE);
        ExtraUpgrade.CREATIVE = mekanismExtras$addVariant("CREATIVE", APIExtraLang.UPGRADE_CREATIVE, APIExtraLang.UPGRADE_CREATIVE_DESCRIPTION, 1, EnumColor.PURPLE);

        // 重新初始化静态参数，这非常重要
        UPGRADES = $VALUES;
    }

    @Unique
    private static Upgrade mekanismExtras$addVariant(String internalName, APILang langKey, APILang descLangKey, int maxStack, EnumColor color) {
        ArrayList<Upgrade> variants = new ArrayList<>(Arrays.asList($VALUES));
        Upgrade upgrade = upgrade$initInvoker(internalName,
                variants.get(variants.size() - 1).ordinal() + 1,
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
