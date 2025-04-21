package com.jerry.mekextras.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mekanism.api.SerializationConstants;
import mekanism.api.SerializerHelper;
import mekanism.api.Upgrade;
import mekanism.common.attachments.component.UpgradeAware;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;

@Mixin(value = UpgradeAware.class, remap = false)
public class MixinUpgradeAware {

    @Mutable
    @Shadow
    @Final
    public static Codec<UpgradeAware> CODEC;

    @Mutable
    @Shadow
    @Final
    public static StreamCodec<RegistryFriendlyByteBuf, UpgradeAware> STREAM_CODEC;

    @Inject(method = "<clinit>",at = @At("TAIL"))
    private static void upgradeClinit(CallbackInfo ci) {
        CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.unboundedMap(Upgrade.CODEC, ExtraCodecs.POSITIVE_INT).fieldOf(SerializationConstants.UPGRADES).forGetter(UpgradeAware::upgrades),
                SerializerHelper.LENIENT_OPTIONAL_STACK_CODEC.fieldOf(SerializationConstants.INPUT).forGetter(UpgradeAware::inputSlot),
                SerializerHelper.LENIENT_OPTIONAL_STACK_CODEC.fieldOf(SerializationConstants.OUTPUT).forGetter(UpgradeAware::outputSlot)
        ).apply(instance, UpgradeAware::new));

        STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.map(size -> new EnumMap<>(Upgrade.class), Upgrade.STREAM_CODEC, ByteBufCodecs.VAR_INT), UpgradeAware::upgrades,
                ItemStack.OPTIONAL_STREAM_CODEC, UpgradeAware::inputSlot,
                ItemStack.OPTIONAL_STREAM_CODEC, UpgradeAware::outputSlot,
                UpgradeAware::new);
    }
}
