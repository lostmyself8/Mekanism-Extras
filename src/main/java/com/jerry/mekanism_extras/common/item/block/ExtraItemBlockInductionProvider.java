package com.jerry.mekanism_extras.common.item.block;

import com.jerry.mekanism_extras.common.tier.IPTier;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionProvider;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ExtraItemBlockInductionProvider extends ItemBlockTooltip<BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>>> {

    public ExtraItemBlockInductionProvider(BlockTile<ExtraTileEntityInductionProvider, BlockTypeTile<ExtraTileEntityInductionProvider>> block) {
        super(block, new Item.Properties());
    }

    @Override
    @NotNull
    public IPTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(getBlock(), IPTier.class));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        IPTier tier = getTier();
        tooltip.add(MekanismLang.INDUCTION_PORT_OUTPUT_RATE.translateColored(tier.getBaseTier().getColor(), EnumColor.GRAY, EnergyDisplay.of(tier.getOutput())));
    }
}
