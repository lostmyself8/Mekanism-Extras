package com.jerry.mekextras.common.item.block;

import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.tier.IPTier;
import com.jerry.mekextras.common.tile.multiblock.TileEntityExtraInductionProvider;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ItemBlockExtraInductionProvider extends ItemBlockExtraTooltip<BlockTile<TileEntityExtraInductionProvider, BlockTypeTile<TileEntityExtraInductionProvider>>> {
    public ItemBlockExtraInductionProvider(BlockTile<TileEntityExtraInductionProvider, BlockTypeTile<TileEntityExtraInductionProvider>> block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    @NotNull
    public IPTier getAdvancedTier() {
        return Objects.requireNonNull(ExtraAttribute.getAdvancedTier(getBlock(), IPTier.class));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, @Nullable TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        IPTier tier = getAdvancedTier();
        tooltip.add(MekanismLang.INDUCTION_PORT_OUTPUT_RATE.translateColored(tier.getAdvanceTier().getColor(), EnumColor.GRAY, EnergyDisplay.of(tier.getOutput())));
    }
}
