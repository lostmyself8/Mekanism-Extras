package com.jerry.mekanism_extras.common.item.block;

import com.jerry.mekanism_extras.common.registration.impl.ExtraItemDeferredRegister;
import com.jerry.mekanism_extras.common.tier.ICTier;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCell;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.util.StorageUtils;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ExtraItemBlockInductionCell extends ItemBlockTooltip<BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>>> {
    public ExtraItemBlockInductionCell(BlockTile<ExtraTileEntityInductionCell, BlockTypeTile<ExtraTileEntityInductionCell>> block) {
        super(block, ExtraItemDeferredRegister.getMekBaseProperties());
    }

    @NotNull
    @Override
    public ICTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(getBlock(), ICTier.class));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        ICTier tier = getTier();
        tooltip.add(MekanismLang.CAPACITY.translateColored(tier.getBaseTier().getColor(), EnumColor.GRAY, EnergyDisplay.of(tier.getMaxEnergy())));
        tooltip.add(MekanismLang.STORED_ENERGY.translateColored(EnumColor.BRIGHT_GREEN, EnumColor.GRAY, EnergyDisplay.of(StorageUtils.getStoredEnergyFromNBT(stack),
                tier.getMaxEnergy())));
    }
}
