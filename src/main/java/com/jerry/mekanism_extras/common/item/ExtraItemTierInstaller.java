package com.jerry.mekanism_extras.common.item;

import com.jerry.mekanism_extras.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.interfaces.ITierUpgradable;
import mekanism.common.tile.interfaces.ITileDirectional;
import mekanism.common.upgrade.IUpgradeData;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtraItemTierInstaller extends Item {

    @Nullable
    private final AdvanceTier fromTier;
    @NotNull
    private final AdvanceTier toTier;

    public ExtraItemTierInstaller(@Nullable AdvanceTier fromTier, @NotNull AdvanceTier toTier, Properties properties) {
        super(properties);
        this.fromTier = fromTier;
        this.toTier = toTier;
    }

    @Nullable
    public AdvanceTier getFromTier() {
        return fromTier;
    }

    @NotNull
    public AdvanceTier getToTier() {
        return toTier;
    }

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        return TextComponentUtil.build(toTier.getColor(), super.getName(stack));
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        if (world.isClientSide || player == null) {
            return InteractionResult.PASS;
        }
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        ExtraAttributeUpgradeable upgradeableBlock = Attribute.get(block, ExtraAttributeUpgradeable.class);
        if (upgradeableBlock != null) {
            AdvanceTier baseTier = ExtraAttribute.getAdvanceTier(block);
            if (baseTier == fromTier && baseTier != toTier) {
                BlockState upgradeState = upgradeableBlock.upgradeResult(state, toTier);
                if (state == upgradeState) {
                    return InteractionResult.PASS;
                }
                BlockEntity tile = WorldUtils.getTileEntity(world, pos);
                if (tile instanceof ITierUpgradable tierUpgradable) {
                    if (tile instanceof TileEntityMekanism tileMek && !tileMek.playersUsing.isEmpty()) {
                        return InteractionResult.FAIL;
                    }
                    IUpgradeData upgradeData = tierUpgradable.getUpgradeData();
                    if (upgradeData == null) {
                        if (tierUpgradable.canBeUpgraded()) {
                            Mekanism.logger.warn("Got no upgrade data for block {} at position: {} in {} but it said it would be able to provide some.", block, pos, world);
                            return InteractionResult.FAIL;
                        }
                    } else {
                        world.setBlockAndUpdate(pos, upgradeState);
                        //TODO: Make it so it doesn't have to be a TileEntityMekanism?
                        TileEntityMekanism upgradedTile = WorldUtils.getTileEntity(TileEntityMekanism.class, world, pos);
                        if (upgradedTile == null) {
                            Mekanism.logger.warn("Error upgrading block at position: {} in {}.", pos, world);
                            return InteractionResult.FAIL;
                        } else {
                            if (tile instanceof ITileDirectional directional && directional.isDirectional()) {
                                upgradedTile.setFacing(directional.getDirection());
                            }
                            upgradedTile.parseUpgradeData(upgradeData);
                            upgradedTile.sendUpdatePacket();
                            upgradedTile.setChanged();
                            if (!player.isCreative()) {
                                context.getItemInHand().shrink(1);
                            }
                            return InteractionResult.sidedSuccess(world.isClientSide);
                        }
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }
}
