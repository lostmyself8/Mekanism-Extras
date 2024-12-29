package com.jerry.mekanism_extras.mixin.item.block.machine;

import mekanism.common.block.prefab.BlockTile;
import mekanism.common.item.block.machine.ItemBlockFluidTank;
import mekanism.common.item.block.machine.ItemBlockMachine;
import mekanism.common.item.interfaces.IModeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemBlockFluidTank.class, remap = false)
public abstract class MixinItemBlockFluidTank extends ItemBlockMachine implements IModeItem {
    public MixinItemBlockFluidTank(BlockTile<?, ?> block) {
        super(block);
    }

    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lmekanism/api/security/ISecurityUtils;canAccessOrDisplayError(Lnet/minecraft/world/entity/player/Player;Lnet/minecraftforge/common/capabilities/ICapabilityProvider;)Z", shift = At.Shift.AFTER), cancellable = true)
    public void use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getCount() > 1) {
            cir.setReturnValue(InteractionResultHolder.pass(stack));
        }
    }

    @Mixin(value = ItemBlockFluidTank.FluidTankItemDispenseBehavior.class, remap = false)
    public abstract static class MixinFluidTankItemDispenseBehavior extends DefaultDispenseItemBehavior {

        @Inject(method = "execute", at = @At(value = "HEAD"), cancellable = true)
        public void execute(BlockSource source, @NotNull ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
            if (stack.getCount() != 1) cir.setReturnValue(super.execute(source, stack));
        }
    }

    @Mixin(value = ItemBlockFluidTank.BasicCauldronInteraction.class, remap = false)
    public abstract static class MixinBasicCauldronInteraction implements CauldronInteraction {

        @Inject(method = "interact(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/InteractionResult;", at = @At(value = "HEAD"), cancellable = true)
        public final void interact(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, @NotNull ItemStack stack, CallbackInfoReturnable<InteractionResult> cir) {
            if (stack.getCount() != 1) cir.setReturnValue(InteractionResult.PASS);
        }
    }
}
