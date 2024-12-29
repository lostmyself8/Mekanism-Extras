package com.jerry.mekanism_extras.mixin.item.block;

import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.item.block.ItemBlockChemicalTank;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.item.interfaces.IItemSustainedInventory;
import mekanism.common.tile.TileEntityChemicalTank;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemBlockChemicalTank.class, remap = false)
public abstract class MixinItemBlockChemicalTank extends ItemBlockTooltip<BlockTile.BlockTileModel<TileEntityChemicalTank, Machine<TileEntityChemicalTank>>> implements IItemSustainedInventory {
    public MixinItemBlockChemicalTank(BlockTile.BlockTileModel<TileEntityChemicalTank, Machine<TileEntityChemicalTank>> block, Properties properties) {
        super(block, properties);
    }

    @Inject(method = "isBarVisible", at = @At(value = "HEAD"), cancellable = true)
    public void isBarVisible(@NotNull ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getCount() > 1) {
            cir.setReturnValue(false);
        }
    }
}
