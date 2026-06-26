package com.jerry.mekextras.common.item.block.transmitter;

import com.jerry.mekextras.common.tier.transmitter.ExtraTransmitterTier;
import com.jerry.mekextras.common.tier.transmitter.TCTier;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraThermodynamicConductor;

import mekanism.api.text.EnumColor;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.tier.ConductorTier;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ItemBlockExtraThermodynamicConductor extends ItemBlockTooltip<BlockSmallTransmitter<TileEntityExtraThermodynamicConductor>> {

    public ItemBlockExtraThermodynamicConductor(BlockSmallTransmitter<TileEntityExtraThermodynamicConductor> block, Properties properties) {
        super(block, true, properties);
    }

    @NotNull
    @Override
    public ConductorTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(getBlock(), ConductorTier.class));
    }

    public @NotNull Component getName(@NotNull ItemStack stack) {
        return TextComponentUtil.build(ExtraTransmitterTier.getAdvancedTier(getTier()).getColor(), super.getName(stack));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return TextComponentUtil.build(ExtraTransmitterTier.getAdvancedTier(getTier()).getColor(), super.getName(stack));
    }

    @Override
    protected void addDetails(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.addDetails(stack, context, tooltip, flag);
        tooltip.add(MekanismLang.CAPABLE_OF_TRANSFERRING.translateColored(EnumColor.DARK_GRAY));
        tooltip.add(MekanismLang.HEAT.translateColored(EnumColor.PURPLE, MekanismLang.MEKANISM));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.addStats(stack, context, tooltip, flag);
        ConductorTier tier = getTier();
        tooltip.add(MekanismLang.CONDUCTION.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TCTier.getConduction(tier)));
        tooltip.add(MekanismLang.INSULATION.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TCTier.getConductionInsulation(tier)));
        tooltip.add(MekanismLang.HEAT_CAPACITY.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TCTier.getHeatCapacity(tier)));
    }
}
