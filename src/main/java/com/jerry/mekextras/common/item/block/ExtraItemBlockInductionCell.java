package com.jerry.mekextras.common.item.block;

import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.tier.ICTier;
import com.jerry.mekextras.common.tile.multiblock.ExtraTileEntityInductionCell;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.util.StorageUtils;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ExtraItemBlockInductionCell extends ExtraItemBlockTooltip<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>> {
    public ExtraItemBlockInductionCell(BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>> block, Item.Properties properties) {
        super(block, properties);
    }

    @NotNull
    @Override
    public ICTier getAdvanceTier() {
        return Objects.requireNonNull(ExtraAttribute.getAdvanceTier(getBlock(), ICTier.class));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, @Nullable TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        ICTier tier = getAdvanceTier();
        tooltip.add(MekanismLang.CAPACITY.translateColored(tier.getAdvanceTier().getColor(), EnumColor.GRAY, EnergyDisplay.of(tier.getMaxEnergy())));
        StorageUtils.addStoredEnergy(stack, tooltip, false);
    }

    @Override
    protected boolean exposesEnergyCap() {
        return false;
    }
}
