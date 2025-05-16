package com.jerry.mekextras.mixin;

import com.jerry.mekextras.api.text.APIExtraLang;
import com.jerry.mekextras.api.ExtraUpgrade;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import mekanism.api.Upgrade;
import mekanism.api.text.EnumColor;
import mekanism.api.text.ILangEntry;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;

import static mekanism.api.Upgrade.CHEMICAL;

@Mixin(value = Upgrade.class, remap = false)
public class MixinUpgrade {

    @Shadow
    @Final
    @Mutable
    private static Upgrade[] $VALUES;

    @Mutable
    @Shadow
    @Final
    public static Codec<Upgrade> CODEC;

    @Mutable
    @Shadow
    @Final
    public static StreamCodec<ByteBuf, Upgrade> STREAM_CODEC;

    @Mutable
    @Shadow
    @Final
    public static IntFunction<Upgrade> BY_ID;

    public MixinUpgrade() {
    }

    @Invoker("<init>")
    public static Upgrade upgrade$initInvoker(String internalName, int internalId, String name, ILangEntry langKey, ILangEntry descLangKey, int maxStack, EnumColor color) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>",at = @At("TAIL"))
    private static void upgradeClinit(CallbackInfo ci) {
        ExtraUpgrade.STACK = mekanismExtras$addVariant("STACK", APIExtraLang.UPGRADE_STACK, APIExtraLang.UPGRADE_STACK_DESCRIPTION, 10, EnumColor.BRIGHT_PINK);
        ExtraUpgrade.IONIC_MEMBRANE = mekanismExtras$addVariant("IONIC_MEMBRANE", APIExtraLang.UPGRADE_IONIC_MEMBRANE, APIExtraLang.UPGRADE_IONIC_MEMBRANE_DESCRIPTION, 1, EnumColor.WHITE);
        ExtraUpgrade.CREATIVE = mekanismExtras$addVariant("CREATIVE", APIExtraLang.UPGRADE_CREATIVE, APIExtraLang.UPGRADE_CREATIVE_DESCRIPTION, 1, EnumColor.PURPLE);

        // 重新初始化静态参数，这非常重要
        mekanismExtras$reinitializeByIdMap();
    }

    @Unique
    private static Upgrade mekanismExtras$addVariant(String internalName, ILangEntry langKey, ILangEntry descLangKey, int maxStack, EnumColor color) {
        ArrayList<Upgrade> variants = new ArrayList<>(Arrays.asList($VALUES));
        Upgrade upgrade = upgrade$initInvoker(internalName,
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

    @Unique
    private static void mekanismExtras$reinitializeByIdMap() {
        Upgrade[] values = $VALUES;
        Function<String, Upgrade> nameLookup = StringRepresentable.createNameLookup(values, Function.identity());
        Function<String, Upgrade> remapper = it -> "gas".equals(it) ? CHEMICAL : nameLookup.apply(it);
        CODEC = new StringRepresentable.EnumCodec<>(values, remapper);
        BY_ID = ByIdMap.continuous(Upgrade::ordinal, values, ByIdMap.OutOfBoundsStrategy.WRAP);
        STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Upgrade::ordinal);
    }

}
