package com.jerry.mekextras.mixin;

import mekanism.common.registries.MekanismBlockTypes;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = MekanismBlockTypes.class, remap = false)
public class MixinBlock {
}
