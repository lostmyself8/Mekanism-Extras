package com.jerry.mekextras.api;

import com.jerry.mekextras.api.tier.IAdvancedTier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IExtraAlloyInteraction {
    <TIER extends IAdvancedTier> void onExtraAlloyInteraction(Player player, ItemStack stack, @NotNull TIER tier);
}
