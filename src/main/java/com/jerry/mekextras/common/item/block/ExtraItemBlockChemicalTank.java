package com.jerry.mekextras.common.item.block;

import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.tier.CTTier;
import com.jerry.mekextras.common.tile.ExtraTileEntityChemicalTank;
import mekanism.api.functions.ConstantPredicates;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.util.ChemicalUtil;
import mekanism.common.util.StorageUtils;
import mekanism.common.util.text.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtraItemBlockChemicalTank extends ExtraItemBlockTooltip<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>> {
    public ExtraItemBlockChemicalTank(BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>> block) {
        super(block);
    }

    @Override
    public CTTier getAdvanceTier() {
        return ExtraAttribute.getAdvanceTier(getBlock(), CTTier.class);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        CTTier tier = getAdvanceTier();
        StorageUtils.addStoredSubstance(stack, tooltip, false);
        tooltip.add(MekanismLang.CAPACITY_MB.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(tier.getStorage())));
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        // No bar for empty or stacked containers as bars are drawn on top of stack count number
        if (stack.getCount() > 1) {
            //Note: Technically this is handled by the below checks as the capability isn't exposed,
            // but we may as well short circuit it here
            return false;
        }
        return ChemicalUtil.hasGas(stack) ||
                ChemicalUtil.hasChemical(stack, ConstantPredicates.alwaysTrue(), Capabilities.INFUSION.item()) ||
                ChemicalUtil.hasChemical(stack, ConstantPredicates.alwaysTrue(), Capabilities.PIGMENT.item()) ||
                ChemicalUtil.hasChemical(stack, ConstantPredicates.alwaysTrue(), Capabilities.SLURRY.item());
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        return StorageUtils.getBarWidth(stack);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return ChemicalUtil.getRGBDurabilityForDisplay(stack);
    }
}
