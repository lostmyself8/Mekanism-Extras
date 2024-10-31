package com.jerry.mekextra.common.item;

import com.jerry.mekextra.api.IExtraAlloyInteraction;
import com.jerry.mekextra.api.tier.ExtraAlloyTier;
import com.jerry.mekextra.common.capabilities.ExtraCapabilities;
import mekanism.common.config.MekanismConfig;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ExtraItemAlloy extends Item {

    private final ExtraAlloyTier tier;
    public ExtraItemAlloy(ExtraAlloyTier tier, Properties properties) {
        super(properties);
        this.tier = tier;
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null && MekanismConfig.general.transmitterAlloyUpgrade.get()) {
            Level world = context.getLevel();
            BlockPos pos = context.getClickedPos();
            IExtraAlloyInteraction alloyInteraction = WorldUtils.getCapability(world, ExtraCapabilities.EXTRA_ALLOY_INTERACTION, pos, context.getClickedFace());
            if (alloyInteraction != null) {
                if (!world.isClientSide) {
                    alloyInteraction.onExtraAlloyInteraction(player, context.getItemInHand(), tier);
                }
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    public ExtraAlloyTier getTier() {
        return tier;
    }
}
