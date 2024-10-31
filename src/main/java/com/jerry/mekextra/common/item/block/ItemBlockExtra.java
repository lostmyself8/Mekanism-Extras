package com.jerry.mekextra.common.item.block;

import com.jerry.mekextra.api.tier.IAdvanceTier;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.block.interfaces.IColoredBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class ItemBlockExtra <BLOCK extends Block> extends BlockItem implements IBlockProvider {

    public ItemBlockExtra(Block block, Properties properties) {
        super(block, properties);
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public BLOCK getBlock() {
        return (BLOCK) super.getBlock();
    }

    public IAdvanceTier getAdvanceTier() {
        return null;
    }

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        if (getBlock() instanceof IColoredBlock coloredBlock) {
            return TextComponentUtil.build(coloredBlock.getColor(), super.getName(stack));
        }
        IAdvanceTier tier = getAdvanceTier();
        if (tier == null) {
            return super.getName(stack);
        }
        return TextComponentUtil.build(tier.getAdvanceTier().getColor(), super.getName(stack));
    }
}
