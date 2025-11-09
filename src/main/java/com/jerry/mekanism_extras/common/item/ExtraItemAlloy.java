package com.jerry.mekanism_extras.common.item;

import com.jerry.mekanism_extras.api.IExtraAlloyInteraction;
import com.jerry.mekanism_extras.api.tier.ExtraAlloyTier;
import com.jerry.mekanism_extras.common.capabilities.ExtraCapabilities;

import mekanism.common.config.MekanismConfig;
import mekanism.common.util.CapabilityUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.WorldUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;

import org.jetbrains.annotations.NotNull;

public class ExtraItemAlloy extends Item {

    private final ExtraAlloyTier tier;

    public ExtraItemAlloy(ExtraAlloyTier tier, Properties properties) {
        super(properties);
        this.tier = tier;
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null && MekanismConfig.general.transmitterAlloyUpgrade.get()) {
            Level world = context.getLevel();
            BlockPos pos = context.getClickedPos();
            BlockEntity tile = WorldUtils.getTileEntity(world, pos);
            LazyOptional<IExtraAlloyInteraction> capability = CapabilityUtils.getCapability(tile, ExtraCapabilities.EXTRA_ALLOY_INTERACTION, context.getClickedFace());
            if (capability.isPresent()) {
                if (!world.isClientSide) {
                    capability.orElseThrow(MekanismUtils.MISSING_CAP_ERROR).onExtraAlloyInteraction(player, context.getItemInHand(), tier);
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
