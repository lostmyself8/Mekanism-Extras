package com.jerry.mekanism_extras.common.item.block;

import com.jerry.mekanism_extras.common.block.basic.ExtraBlockResource;

import mekanism.common.item.block.ItemBlockMekanism;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import org.jetbrains.annotations.Nullable;

public class ExtraItemBlockResource extends ItemBlockMekanism<ExtraBlockResource> {

    public ExtraItemBlockResource(ExtraBlockResource block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return getBlock().getResourceInfo().getBurnTime();
    }
}
