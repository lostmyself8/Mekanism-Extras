package com.jerry.mekextra.api;

import com.jerry.mekextra.api.tier.ExtraAlloyTier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IExtraAlloyInteraction {
    void onExtraAlloyInteraction(Player player, ItemStack stack, @NotNull ExtraAlloyTier tier);
}
