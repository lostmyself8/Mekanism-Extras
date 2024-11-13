package com.jerry.mekanism_extras.common.item.block;

import com.jerry.mekanism_extras.common.registration.impl.ExtraItemDeferredRegister;
import mekanism.common.item.block.ItemBlockMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * Copy from class ItemBlockMultipartAble which was created by Thiakil on 19/11/2017.
 * Hhhh,I can't be blamed for that.
 */
//TODO: Re-evaluate this class
public class ExtraItemBlockMultipartAble<BLOCK extends Block> extends ItemBlockMekanism<BLOCK> {

    public ExtraItemBlockMultipartAble(@NotNull BLOCK block) {
        super(block, ExtraItemDeferredRegister.getMekBaseProperties());
    }

    /**
     * Reimplementation of onItemUse that will divert to MCMultipart placement functions if applicable
     */
    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) {
            return InteractionResult.PASS;
        }
        ItemStack stack = context.getItemInHand();
        if (stack.isEmpty()) {
            return InteractionResult.FAIL;//WTF
        }
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (!WorldUtils.isValidReplaceableBlock(world, pos)) {
            pos = pos.relative(context.getClickedFace());
        }
        if (player.mayUseItemAt(pos, context.getClickedFace(), stack)) {
            BlockPlaceContext blockItemUseContext = new BlockPlaceContext(context);
            BlockState state = getPlacementState(blockItemUseContext);
            if (state == null) {
                return InteractionResult.FAIL;
            }
            if (placeBlock(blockItemUseContext, state)) {
                state = world.getBlockState(pos);
                SoundType soundtype = state.getSoundType(world, pos, player);
                world.playSound(player, pos, soundtype.getPlaceSound(), SoundSource.BLOCKS, (soundtype.getVolume() + 1) / 2F, soundtype.getPitch() * 0.8F);
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public boolean placeBlock(@NotNull BlockPlaceContext context, @NotNull BlockState state) {
        if (WorldUtils.isValidReplaceableBlock(context.getLevel(), context.getClickedPos())) {
            return super.placeBlock(context, state);
        }
        return false;
    }
}
