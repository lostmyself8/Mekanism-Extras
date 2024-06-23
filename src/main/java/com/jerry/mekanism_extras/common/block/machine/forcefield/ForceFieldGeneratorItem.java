package com.jerry.mekanism_extras.common.block.machine.forcefield;

import com.jerry.mekanism_extras.util.FrequencyTypeHelper;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.item.block.machine.ItemBlockMachine;
import mekanism.common.util.MekanismUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ForceFieldGeneratorItem extends ItemBlockMachine {

    public ForceFieldGeneratorItem(BlockTile<?, ?> block) {
        super(block);
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        MekanismUtils.addFrequencyToTileTooltip(stack, FrequencyTypeHelper.FORCEFIELD, tooltip);
    }
}
