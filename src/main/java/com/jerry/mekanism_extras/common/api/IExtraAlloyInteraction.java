package com.jerry.mekanism_extras.common.api;

import com.jerry.mekanism_extras.common.api.tier.ExtraAlloyTier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import org.jetbrains.annotations.NotNull;

@AutoRegisterCapability
public interface IExtraAlloyInteraction {
    void onExtraAlloyInteraction(Player player, ItemStack stack, @NotNull ExtraAlloyTier tier);
}
