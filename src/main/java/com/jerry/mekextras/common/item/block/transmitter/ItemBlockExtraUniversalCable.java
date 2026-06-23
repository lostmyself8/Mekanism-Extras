package com.jerry.mekextras.common.item.block.transmitter;

import com.jerry.mekextras.common.tier.transmitter.CTier;
import com.jerry.mekextras.common.tier.transmitter.ExtraTransmitterTier;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraUniversalCable;

import mekanism.api.text.EnumColor;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.tier.CableTier;
import mekanism.common.util.text.EnergyDisplay;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ItemBlockExtraUniversalCable extends ItemBlockTooltip<BlockSmallTransmitter<TileEntityExtraUniversalCable>> {

    public ItemBlockExtraUniversalCable(BlockSmallTransmitter<TileEntityExtraUniversalCable> block, Properties properties) {
        super(block, true, properties);
    }

    @NotNull
    @Override
    public CableTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(getBlock(), CableTier.class));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return TextComponentUtil.build(ExtraTransmitterTier.getAdvancedTier(getTier()).getColor(), super.getName(stack));
    }

    @Override
    protected void addDetails(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.addDetails(stack, context, tooltip, flag);
        tooltip.add(MekanismLang.CAPABLE_OF_TRANSFERRING.translateColored(EnumColor.DARK_GRAY));
        tooltip.add(MekanismLang.GENERIC_TRANSFER.translateColored(EnumColor.PURPLE, MekanismLang.ENERGY_FORGE_SHORT, MekanismLang.FORGE));
        tooltip.add(MekanismLang.GENERIC_TRANSFER.translateColored(EnumColor.PURPLE, MekanismLang.ENERGY_JOULES_PLURAL, MekanismLang.MEKANISM));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.addStats(stack, context, tooltip, flag);
        CableTier tier = getTier();
        tooltip.add(MekanismLang.CAPACITY_PER_TICK.translateColored(EnumColor.INDIGO, EnumColor.GRAY, EnergyDisplay.of(CTier.getCapacityAsLong(tier))));
    }
}
