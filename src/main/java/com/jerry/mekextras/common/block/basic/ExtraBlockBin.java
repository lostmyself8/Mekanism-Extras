package com.jerry.mekextras.common.block.basic;

import com.jerry.mekextras.common.inventory.slot.ExtraBinInventorySlot;
import com.jerry.mekextras.common.tile.ExtraTileEntityBin;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

public class ExtraBlockBin extends BlockTile<ExtraTileEntityBin, BlockTypeTile<ExtraTileEntityBin>> {
    public ExtraBlockBin(BlockTypeTile<ExtraTileEntityBin> type, UnaryOperator<Properties> propertiesModifier) {
        super(type, propertiesModifier);
    }

    @Override
    @Deprecated
    public void attack(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player player) {
        if (!world.isClientSide) {
            ExtraTileEntityBin bin = WorldUtils.getTileEntity(ExtraTileEntityBin.class, world, pos);
            if (bin != null) {
                BlockHitResult mop = MekanismUtils.rayTrace(player);
                if (mop.getType() != HitResult.Type.MISS && mop.getDirection() == bin.getDirection()) {
                    ExtraBinInventorySlot binSlot = bin.getBinSlot();
                    if (!binSlot.isEmpty() && bin.removeTicks == 0) {
                        bin.removeTicks = 3;
                        ItemStack stack;
                        if (player.isShiftKeyDown()) {
                            stack = binSlot.getBottomStack();
                            if (!stack.isEmpty()) {
                                MekanismUtils.logMismatchedStackSize(binSlot.shrinkStack(stack.getCount(), Action.EXECUTE), stack.getCount());
                            }
                        } else {
                            stack = binSlot.getStack().copyWithCount(1);
                            MekanismUtils.logMismatchedStackSize(binSlot.shrinkStack(1, Action.EXECUTE), 1);
                        }
                        if (!player.getInventory().add(stack)) {
                            BlockPos dropPos = pos.relative(bin.getDirection());
                            Entity item = new ItemEntity(world, dropPos.getX() + .5f, dropPos.getY() + .3f, dropPos.getZ() + .5f, stack);
                            Vec3 motion = item.getDeltaMovement();
                            item.push(-motion.x(), -motion.y(), -motion.z());
                            world.addFreshEntity(item);
                        } else {
                            world.playSound(null, pos.getX() + .5f, pos.getY() + .5f, pos.getZ() + .5f, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS,
                                    0.2F, ((world.random.nextFloat() - world.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                        }
                    }
                }
            }
        }
    }

    @NotNull
    @Override
    @Deprecated
    public ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player player,
                                           @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        ExtraTileEntityBin bin = WorldUtils.getTileEntity(ExtraTileEntityBin.class, world, pos);
        if (bin == null) {
            return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        }
        ItemInteractionResult wrenchResult = bin.tryWrench(state, player, stack).getInteractionResult();
        if (wrenchResult != ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION) {
            return wrenchResult;
        }
        if (stack.isEmpty() && player.isShiftKeyDown() && hit.getDirection() == bin.getDirection()) {
            return bin.toggleLock() ? ItemInteractionResult.sidedSuccess(world.isClientSide) : ItemInteractionResult.FAIL;
        } else if (!world.isClientSide) {
            ExtraBinInventorySlot binSlot = bin.getBinSlot();
            ItemStack storedStack = binSlot.isLocked() ? binSlot.getLockStack() : binSlot.getStack();
            int binMaxSize = binSlot.getLimit(storedStack);
            if (binSlot.getCount() < binMaxSize) {
                if (bin.addTicks == 0) {
                    if (!stack.isEmpty()) {
                        ItemStack remain = binSlot.insertItem(stack, Action.EXECUTE, AutomationType.MANUAL);
                        player.setItemInHand(hand, remain);
                    }
                    //Note: We set the add ticks regardless so that we can allow double right-clicking to insert items from the player's inventory
                    // without requiring them to first be holding the same item
                    bin.addTicks = 5;
                } else if (bin.addTicks > 0 && !storedStack.isEmpty()) {
                    NonNullList<ItemStack> inv = player.getInventory().items;
                    for (int i = 0; i < inv.size(); i++) {
                        if (binSlot.getCount() == binMaxSize) {
                            break;
                        }
                        ItemStack stackToAdd = inv.get(i);
                        if (!stackToAdd.isEmpty()) {
                            ItemStack remain = binSlot.insertItem(stackToAdd, Action.EXECUTE, AutomationType.MANUAL);
                            inv.set(i, remain);
                            bin.addTicks = 5;
                        }
                        player.containerMenu.sendAllDataToRemote();
                    }
                }
            }
        }
        return ItemInteractionResult.sidedSuccess(world.isClientSide);
    }
}
